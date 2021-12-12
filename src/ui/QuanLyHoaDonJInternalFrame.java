/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import Entity.HoaDon;
import dao.HoaDonCTDao;
import dao.HoaDonDao;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import utils.Auth;
import utils.MsgBox;
import utils.XDate;
import utils.XImage;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.Date;

/**
 *
 * @author haoca
 */
public class QuanLyHoaDonJInternalFrame extends javax.swing.JInternalFrame {
    
    HoaDonCTDao hdctDAO = new HoaDonCTDao();
    HoaDonDao hdDAO = new HoaDonDao();
    int row = -1;
    boolean check = false;
    double tienHienTai = 0;
    double tinhTien = 0;
    String maHoaDon, tenNV, tenKH, SDT, TongTien,tenKhachHang;
    float giamGia;
    Date ngayTT;
    String spoil,spoil1;
    DefaultTableModel model;
    List<HoaDon> lst;
    static int maHD;
    /**
     * Creates new form QuanLyHoaDonJInternalFrame
     */
    public QuanLyHoaDonJInternalFrame() {
        initComponents();
        init();
        fillTableDanhSach();
    }
    
    void init() {
        this.setFrameIcon(new ImageIcon(XImage.getAppIcon()));
        setTitle("HỆ THỐNG QUẢN LÝ HÓA ĐƠN"); 
        cboTrangThai.removeAllItems();
        cboTrangThai.addItem("");
        cboTrangThai.addItem("Chờ xác nhận");
        cboTrangThai.addItem("Đã xác nhận");
        cboTrangThai.addItem("Đã hủy");
    }
     
    void fillTableDanhSach() {
        model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);
        
        try {
            if (txtTimKiem.getText().trim().length()==0) {
                lst = hdDAO.selectAll();
            } else {
                lst = (List<HoaDon>) hdDAO.selectByIdNew(txtTimKiem.getText().trim());
            }
            for (HoaDon x : lst) {
                model.addRow(new Object[]{
                    x.getMaHD(),
                    x.getHoTenKH(),
                    x.getSoDienThoai(),
                    x.getEmail(),
                    x.getGiamGia(),
                    x.getTongTien(),
                    x.getNgayThanhToan(),
                    x.getTrangThaiHD() == 0 ? "Chờ xác nhận" : x.getTrangThaiHD() == 1 ? "Đã xác nhận" : "Đã hủy",
                    x.getTrangThaiTT() == 0 ? "Chờ thanh toán" : "Đã thanh toán"});
                System.out.println(x.toString() + "\n");
                tblDanhSach.setRowSelectionInterval(0, 0);
            }
        } catch (ClassCastException e) {
            MsgBox.alert(this, "Không có thông tin");
            e.printStackTrace();
        }
    }

    void fillTableDanhSach1() {
        model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);
        try {
            if (cboTrangThai.getSelectedIndex() == 1) {
                lst = hdDAO.selectAllByTTHD(0);
            } else if (cboTrangThai.getSelectedIndex() == 2) {
                lst = hdDAO.selectAllByTTHD(1);
            } else if (cboTrangThai.getSelectedIndex() == 3) {
                lst = hdDAO.selectAllByTTHD(2);
            } else{
                lst = hdDAO.selectAll();
                txtTimKiem.setText("");
            }
            for (HoaDon x : lst) {
                model.addRow(new Object[]{
                    x.getMaHD(),
                    x.getHoTenKH(),
                    x.getSoDienThoai(),
                    x.getEmail(),
                    x.getGiamGia(),
                    x.getTongTien(),
                    x.getNgayThanhToan(),
                    x.getTrangThaiHD() == 0 ? "Chờ xác nhận" : x.getTrangThaiHD() == 1 ? "Đã xác nhận" : "Đã hủy",
                    x.getTrangThaiTT() == 0 ? "Chờ thanh toán" : "Đã thanh toán"});
                System.out.println(x.toString() + "\n");
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
            MsgBox.alert(this, "Không có thông tin");
        }
    }

    void capNhat() {
        HoaDon model = getForm();
        try {
            hdDAO.update(model);
            this.fillTableDanhSach1();
            System.out.println("->" + (Object) model);
            MsgBox.alert(this, "Cập nhật thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bạn ( lỗi CSDL )");
            e.printStackTrace();
        }
    }

    void setCapNhat() {
        while(tblDanhSach.getValueAt(row, 7).equals("Đã hủy")){
            row+=1;            
        }    
    }

    double getTongTien(String id) {
        double tien = hdctDAO.getTongTien(id);
        return tien;
    }

    String maKH(Integer id) {
        String maKH = null;
        HoaDon hd = new HoaDon();
        hd = hdDAO.selectById(id);
        maKH = hd.getMaKH();
        return maKH;
    }

    String getAnh() {
        String anh = null;
        HoaDon hd = new HoaDon();
        hd = hdDAO.selectById(Integer.valueOf(txtMaHoaDon.getText()));
        anh = hd.getAnhDatCoc();
        return anh;
    }

    HoaDon getForm() {
        //this.row = 0;
        HoaDon hd = new HoaDon();
        hd.setMaHD(Integer.valueOf(txtMaHoaDon.getText()));
        hd.setHoTenKH(txtTenKH.getText());
        hd.setSoDienThoai(txtSDT.getText().trim());
        hd.setEmail(txtEmail.getText());
        if (rdo0.isSelected()) {
            hd.setGiamGia(0);
        } else if (rdo5.isSelected()) {
            hd.setGiamGia(5);
        } else if (rdo15.isSelected()) {
            hd.setGiamGia(15);
        } else {
            hd.setGiamGia(20);
        }
        hd.setTongTien(Float.parseFloat(txtTongTien.getText()));
        if (maKH(Integer.valueOf(txtMaHoaDon.getText())) == null) {
            hd.setMaKH("KHbot");
        } else {
            hd.setMaKH(maKH(Integer.valueOf(txtMaHoaDon.getText())));
        }
        hd.setMaNV(Auth.user1.getMaNV());
        hd.setAnhDatCoc(getAnh());
        if (rdoCho.isSelected()) {
            hd.setTrangThaiHD(0);
        } else if (rdoDa.isSelected()) {
            hd.setTrangThaiHD(1);
        } else {
            hd.setTrangThaiHD(2);
        }
        if (rdoChoThanhToan.isSelected()) {
            hd.setTrangThaiTT(0);
        } else {
            hd.setTrangThaiTT(1);
        }
        hd.setNgayThanhToan(XDate.now());
        return hd;
    }

    void setForm(HoaDon hd) {
        try {
            txtMaHoaDon.setText(String.valueOf(hd.getMaHD()));
            txtTenKH.setText(hd.getHoTenKH());
            txtSDT.setText(hd.getSoDienThoai().trim());
            txtEmail.setText(hd.getEmail());
            txtTongTien.setText(hd.getTongTien() + "");
            if (hd.getGiamGia() == 0) {
                rdo0.setSelected(true);
            } else if (hd.getGiamGia() == 5) {
                rdo5.setSelected(true);
            } else if (hd.getGiamGia() == 15) {
                rdo15.setSelected(true);
            } else {
                rdo20.setSelected(true);
            }
            if (hd.getTrangThaiHD() == 0) {
                rdoCho.setSelected(true);
            } else if (hd.getTrangThaiHD() == 1) {
                rdoDa.setSelected(true);
            } else {
                rdoHuy.setSelected(true);
            }
            if (hd.getTrangThaiTT() == 0) {
                rdoChoThanhToan.setSelected(true);
            } else {
                rdoDaThanhToan.setSelected(true);
            }
            if (hd.getAnhDatCoc() != null) {
                ImageIcon icon = new ImageIcon(XImage.read(hd.getAnhDatCoc()).getImage().getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), 5));
                lblAnh.setIcon(icon);

            }
            if(tblDanhSach.getValueAt(row, 8).equals("Đã thanh toán")){
                btnCapNhat.setEnabled(false);
            }else{
                btnCapNhat.setEnabled(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void clearForm() {
        txtMaHoaDon.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtTenKH.setText("");
        txtTongTien.setText("");
        txtTongTien.setText("");
        rdo0.setSelected(true);
        rdoCho.setSelected(true);
        rdoChoThanhToan.setSelected(true);
        this.updateStatus();
        row = -1;
        updateStatus();
    }

    void edit() {
        try {
            int maHD = (Integer) tblDanhSach.getValueAt(this.row, 0);
            HoaDon nh = hdDAO.selectById(maHD);
            if (nh != null) {
                setForm(nh);
                updateStatus();
                tabs1.setSelectedIndex(1);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn CSDL !" + e);
            e.printStackTrace();
        }
    }

    void dau() {
        row = 0;
        setCapNhat();
        edit();
    }

    void cuoi() {
        row = tblDanhSach.getRowCount() - 1;
        setCapNhat();
        edit();
    }

    void lui() {
        if (row > 0) {
            row--;
            while(tblDanhSach.getValueAt(row, 7).equals("Đã hủy")){
            row-=1;            
        }
            edit();
        }
    }

    void tien() {
        if (row < tblDanhSach.getRowCount() - 1) {
            row++;
            setCapNhat();
            edit();
        }
    }

    void updateStatus() {
        boolean edit = this.row >= 0;
        boolean dau = this.row == 0;
        boolean cuoi = this.row == tblDanhSach.getRowCount() - 1;

        // khi insert thi except to update and delate
        //btnCapNhat.setEnabled(!edit);
        btnDau.setEnabled(edit && !dau);
        btnLui.setEnabled(edit && !dau);
        btnTien.setEnabled(edit && !cuoi);
        btnCuoi.setEnabled(edit && !cuoi);
    }

    void check() {
        String email_regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String sdt_regex = "0[0-9\\s.-]{9,13}";
        String hoTen_regex = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{3,50}$";

        if (txtMaHoaDon.getText().length() == 0) {
            txtMaHoaDon.requestFocus();
            MsgBox.alert(this, "Chưa có mã hóa đơn");
            check = false;
            return;
//        } else if (txtMaHoaDon.getText().length() > 20) {
//            txtMaHoaDon.requestFocus();
//            txtMaHoaDon.setBackground(Color.YELLOW);
//            MsgBox.alert(this, "mã hóa đơn cho phép tối đa 20 ký tự");
//            check = false;
//            return;
        } else {
            txtMaHoaDon.setBackground(Color.white);
            check = true;
        }

        if (txtTenKH.getText().length() == 0) {
            txtTenKH.requestFocus();
            MsgBox.alert(this, "họ tên đang để trống");
            check = false;
            return;
        } else if (txtTenKH.getText().equals(" ")) {
            txtTenKH.requestFocus();
            MsgBox.alert(this, "họ tên không để khoảng trống");
            check = false;
            return;
        } else if (!txtTenKH.getText().matches(hoTen_regex)) {
            txtTenKH.requestFocus();
            MsgBox.alert(this, "Sai định dạng họ tên");
            check = false;
            return;
        } else {
            check = true;
        }

        if (txtSDT.getText().length() == 0) {
            txtSDT.requestFocus();
//            txtSDT.setBackground(Color.YELLOW);
            MsgBox.alert(this, "Số điện thoại không được để trống ");
            check = false;
            return;
        } else if (!txtSDT.getText().matches(sdt_regex)) {
            txtSDT.requestFocus();
//            txtSDT.setBackground(Color.YELLOW);
            MsgBox.alert(this, "Số điện thoại không đúng định dạng( ký tự số; 9->13)");
            check = false;
            return;
//        } else if (txtSDT.getText().length() < 10 || txtSDT.getText().length() > 11) {
//            txtSDT.requestFocus();
//            txtSDT.setBackground(Color.YELLOW);
//            MsgBox.alert(this, "Độ dài số điện thoại cho phép");
//            check = false;
//            return;
        } else {
            //           txtSDT.setBackground(Color.white);
            check = true;
        }

        if (txtEmail.getText().length() == 0) {
            txtEmail.requestFocus();
//            txtEmail.setBackground(Color.YELLOW);
            MsgBox.alert(this, "Email đang để trống");
            check = false;
            return;
        } else if (!txtEmail.getText().matches(email_regex)) {
            txtEmail.requestFocus();
            //           txtEmail.setBackground(Color.YELLOW);
            MsgBox.alert(this, "Email bạn vừa nhập không đúng định dạng");
            check = false;
        } else {
//            txtEmail.setBackground(Color.white);
            check = true;
        }

        if (txtTongTien.getText().length() == 0) {
            txtTongTien.requestFocus();
//            txtTongTien.setBackground(Color.YELLOW);
            MsgBox.alert(this, "Đang để trống tổng tiền");
            check = false;
            return;
        } else {
//            txtTongTien.setBackground(Color.YELLOW);
            check = true;
        }

        try {
            float tien = Float.parseFloat(txtTongTien.getText());
            System.out.println(tien);
//            txtTongTien.setBackground(Color.YELLOW);
            check = true;
        } catch (Exception e) {
            txtTongTien.requestFocus();
//            txtTongTien.setBackground(Color.YELLOW);
            MsgBox.alert(this, "Tổng tiền chỉ nhập ký tự số");
            check = false;
            return;
        }
    }

    void xuatHoaDon() {
        Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("HoaDonSanBong-" + tenKhachHang + ".pdf"));
            document.open();

            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
            Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 28, BaseColor.RED);
            Font font2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, BaseColor.BLACK);

            Chunk chunk = new Chunk("          Invoice for payment of yard booking", font1);
            Chunk chunkA = new Chunk(" ");
            Chunk chunkB = new Chunk(" ");
            Chunk chunk1 = new Chunk("Invoice code :              " + maHoaDon, font);
            Chunk chunk2 = new Chunk("Staff's name :              " + tenNV, font);
            Chunk chunk3 = new Chunk("Date of payment :       " + ngayTT, font);
            Chunk chunk4 = new Chunk("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            Chunk chunk5 = new Chunk("Customer's name :       " + tenKH, font);
            Chunk chunk6 = new Chunk(".Phone number :           " + SDT, font);
            Chunk chunk7 = new Chunk("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            Chunk chunk8 = new Chunk("discount :     " + giamGia + "%", font);
            Chunk chunk9 = new Chunk(" ");
            Chunk chunk10 = new Chunk(" ");
            Chunk chunk11 = new Chunk(" ");
            Chunk chunk12 = new Chunk(" ");
            Chunk chunk13 = new Chunk("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            Chunk chunk14 = new Chunk("                                                                             Total payment : " + TongTien + "(Dong)", font);
            Chunk chunk15 = new Chunk("Confirm", font2);
            document.add(new Paragraph(chunk));
            document.add(new Paragraph(chunkA));
            document.add(new Paragraph(chunkB));
            document.add(new Paragraph(chunk1));
            document.add(new Paragraph(chunk2));
            document.add(new Paragraph(chunk3));
            document.add(new Paragraph(chunk4));
            document.add(new Paragraph(chunk5));
            document.add(new Paragraph(chunk6));
            document.add(new Paragraph(chunk7));
            document.add(new Paragraph(chunk8));
            document.add(new Paragraph(chunk9));
            document.add(new Paragraph(chunk10));
            document.add(new Paragraph(chunk11));
            document.add(new Paragraph(chunk12));
            document.add(new Paragraph(chunk13));
            document.add(new Paragraph(chunk14));
            document.add(new Paragraph(chunkA));
            document.add(new Paragraph(chunkB));
            document.add(new Paragraph(chunkA));
            document.add(new Paragraph(chunkB));
            document.add(new Paragraph(chunk15));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        tabs1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        btnXuatHoaDon = new javax.swing.JButton();
        cboTrangThai = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnDau = new javax.swing.JButton();
        btnLui = new javax.swing.JButton();
        btnTien = new javax.swing.JButton();
        btnCuoi = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnDong = new javax.swing.JButton();
        btnMoHoaDonCT = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        rdo20 = new javax.swing.JRadioButton();
        rdo15 = new javax.swing.JRadioButton();
        rdo5 = new javax.swing.JRadioButton();
        rdo0 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        rdoDaThanhToan = new javax.swing.JRadioButton();
        rdoChoThanhToan = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        rdoHuy = new javax.swing.JRadioButton();
        rdoDa = new javax.swing.JRadioButton();
        rdoCho = new javax.swing.JRadioButton();
        txtTongTien = new javax.swing.JTextField();

        setClosable(true);

        tabs1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabs1MouseClicked(evt);
            }
        });

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Tên khách hàng", "Số điện thoại", "Email", "Giảm giá", "Tổng tiền", "Ngày thanh toán", "Trạng thái HĐ", "Trạng thái TT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDanhSachMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSach);

        btnXuatHoaDon.setText("Xuất hóa đơn");
        btnXuatHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatHoaDonActionPerformed(evt);
            }
        });

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem)
                        .addGap(26, 26, 26)
                        .addComponent(cboTrangThai, 0, 166, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXuatHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTimKiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnXuatHoaDon)
                .addGap(20, 20, 20))
        );

        tabs1.addTab("Danh sách hóa đơn", jPanel1);

        lblAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0), 2));

        jLabel2.setText("Mã hóa đơn");

        jLabel3.setText("Tên khách hàng");

        jLabel4.setText("Số điện thoại");

        jLabel5.setText("Email");

        jLabel7.setText("Tổng tiền ");

        jLabel8.setText("Trạng thái hóa đơn");

        jLabel9.setText("Trạng thái thanh toán");

        txtMaHoaDon.setEditable(false);
        txtMaHoaDon.setBackground(new java.awt.Color(204, 204, 204));

        btnDau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-skip-to-start-24.png"))); // NOI18N
        btnDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDauActionPerformed(evt);
            }
        });

        btnLui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-rewind_1.png"))); // NOI18N
        btnLui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuiActionPerformed(evt);
            }
        });

        btnTien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-rewind-2.png"))); // NOI18N
        btnTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTienActionPerformed(evt);
            }
        });

        btnCuoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-skip-to-start.png"))); // NOI18N
        btnCuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuoiActionPerformed(evt);
            }
        });

        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-pencil-24.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-delete-24_1.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnDong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exit_2.png"))); // NOI18N
        btnDong.setText("Đóng");
        btnDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongActionPerformed(evt);
            }
        });

        btnMoHoaDonCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-next-page-24.png"))); // NOI18N
        btnMoHoaDonCT.setText("Mở hóa đơn chi tiết");
        btnMoHoaDonCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoHoaDonCTActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));

        buttonGroup1.add(rdo20);
        rdo20.setText("20%");
        rdo20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo20ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo15);
        rdo15.setText("15%");
        rdo15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo15ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo5);
        rdo5.setText("5%");
        rdo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo5ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo0);
        rdo0.setText("0%");
        rdo0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo0ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdo0)
                .addGap(67, 67, 67)
                .addComponent(rdo5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdo15)
                .addGap(68, 68, 68)
                .addComponent(rdo20)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo0)
                    .addComponent(rdo5)
                    .addComponent(rdo15)
                    .addComponent(rdo20))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 51), 2));

        buttonGroup3.add(rdoDaThanhToan);
        rdoDaThanhToan.setText("Đã thanh toán");

        buttonGroup3.add(rdoChoThanhToan);
        rdoChoThanhToan.setText("Chờ thanh toán");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(rdoChoThanhToan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdoDaThanhToan)
                .addGap(42, 42, 42))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoDaThanhToan)
                    .addComponent(rdoChoThanhToan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Giảm giá");

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 51), 2));

        buttonGroup2.add(rdoHuy);
        rdoHuy.setText("Đã hủy");
        rdoHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHuyActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoDa);
        rdoDa.setText("Đã xác nhận");

        buttonGroup2.add(rdoCho);
        rdoCho.setText("Chờ xác nhận");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdoCho)
                .addGap(76, 76, 76)
                .addComponent(rdoDa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdoHuy)
                .addGap(14, 14, 14))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoCho)
                    .addComponent(rdoDa)
                    .addComponent(rdoHuy))
                .addGap(10, 10, 10))
        );

        txtTongTien.setEditable(false);
        txtTongTien.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaHoaDon))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenKH))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnDau)
                                .addGap(71, 71, 71)
                                .addComponent(btnLui)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTien)
                                .addGap(56, 56, 56)
                                .addComponent(btnCuoi))
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSDT)
                            .addComponent(txtEmail)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnCapNhat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMoi)
                        .addGap(38, 38, 38)
                        .addComponent(btnMoHoaDonCT, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDong)))
                .addGap(184, 184, 184))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnTien)
                            .addComponent(btnCuoi)
                            .addComponent(btnLui)
                            .addComponent(btnDau))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCapNhat)
                                    .addComponent(btnMoi)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnDong)
                                    .addComponent(btnMoHoaDonCT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        tabs1.addTab("Cập nhật", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDauActionPerformed
        // TODO add your handling code here:
        dau();
    }//GEN-LAST:event_btnDauActionPerformed

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed
        // TODO add your handling code here:
        lui();
    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienActionPerformed
        // TODO add your handling code here:
        tien();
    }//GEN-LAST:event_btnTienActionPerformed

    private void btnCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoiActionPerformed
        // TODO add your handling code here:
        cuoi();
    }//GEN-LAST:event_btnCuoiActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        check();
        if (check == true) {
            capNhat();
            tienHienTai = 0;
            tinhTien = 0;
            //cboTrangThai.setSelectedIndex(0);
        }

    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
        tienHienTai = 0;
        tinhTien = 0;
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnDongActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        fillTableDanhSach();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblDanhSachMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblDanhSach.rowAtPoint(evt.getPoint());
            if (!tblDanhSach.getValueAt(row, 7).equals("Đã hủy")) {
                this.maHD = Integer.parseInt(String.valueOf(tblDanhSach.getValueAt(row, 0)));
                edit();
            } 
        }
    }//GEN-LAST:event_tblDanhSachMousePressed

    private void btnMoHoaDonCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoHoaDonCTActionPerformed
        // TODO add your handling code here:
        QLHoaDonChiTiet ql = new QLHoaDonChiTiet();
        this.getDesktopPane().add(ql);
        ql.setVisible(true);
    }//GEN-LAST:event_btnMoHoaDonCTActionPerformed

    private void rdo0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo0ActionPerformed
        // TODO add your handling code here:
        tienHienTai = hdctDAO.getTongTien(txtMaHoaDon.getText());
        tinhTien = tienHienTai;
        txtTongTien.setText(String.valueOf(tinhTien));
    }//GEN-LAST:event_rdo0ActionPerformed

    private void rdo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo5ActionPerformed
        // TODO add your handling code here:
        tienHienTai = hdctDAO.getTongTien(txtMaHoaDon.getText());
        tinhTien = (tienHienTai * 95) / 100;
        System.out.println(tinhTien);
        txtTongTien.setText(String.valueOf(tinhTien));
    }//GEN-LAST:event_rdo5ActionPerformed

    private void rdo15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo15ActionPerformed
        // TODO add your handling code here:
        tienHienTai = hdctDAO.getTongTien(txtMaHoaDon.getText());
        tinhTien = (tienHienTai * 85) / 100;
        System.out.println(tinhTien);
        txtTongTien.setText(String.valueOf(tinhTien));
    }//GEN-LAST:event_rdo15ActionPerformed

    private void rdo20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo20ActionPerformed
        // TODO add your handling code here:
        tienHienTai = hdctDAO.getTongTien(txtMaHoaDon.getText());
        tinhTien = (tienHienTai * 80) / 100;
        System.out.println(tinhTien);
        txtTongTien.setText(String.valueOf(tinhTien));
    }//GEN-LAST:event_rdo20ActionPerformed

    private void rdoHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoHuyActionPerformed

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.row = tblDanhSach.rowAtPoint(evt.getPoint());
            tenKhachHang = (String)tblDanhSach.getValueAt(row, 1);
        }
        spoil = (String) tblDanhSach.getValueAt(row, 7);
        spoil1 = (String) tblDanhSach.getValueAt(row, 8);
    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void btnXuatHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatHoaDonActionPerformed
        // TODO add your handling code here:
        System.out.println(spoil1);
        try {
            if (spoil.equals("Đã xác nhận") && spoil1.equals("Đã thanh toán")) {
                maHoaDon = (String) tblDanhSach.getValueAt(row, 0);
                tenKH = (String) tblDanhSach.getValueAt(row, 1);
                SDT = (String) tblDanhSach.getValueAt(row, 2);
                giamGia = (float) tblDanhSach.getValueAt(row, 4);
                TongTien = tblDanhSach.getValueAt(row, 5) + "";
                tenNV = hdDAO.getTenNV(Auth.user1.getMaNV());
                ngayTT = (Date) tblDanhSach.getValueAt(row, 6);
                System.out.println(maHD + tenNV + ngayTT + tenKH + SDT + giamGia + TongTien);
                xuatHoaDon();
                MsgBox.alert(this, "Đã xuất hóa đơn, hãy kiểm tra");
            } else {
                MsgBox.alert(this, "Chỉ hóa đơn đã thanh toán");
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Chưa chọn thông tin");
        }

    }//GEN-LAST:event_btnXuatHoaDonActionPerformed

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
        fillTableDanhSach1();
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void tabs1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabs1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabs1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnCuoi;
    private javax.swing.JButton btnDau;
    private javax.swing.JButton btnDong;
    private javax.swing.JButton btnLui;
    private javax.swing.JButton btnMoHoaDonCT;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnTien;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXuatHoaDon;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JRadioButton rdo0;
    private javax.swing.JRadioButton rdo15;
    private javax.swing.JRadioButton rdo20;
    private javax.swing.JRadioButton rdo5;
    private javax.swing.JRadioButton rdoCho;
    private javax.swing.JRadioButton rdoChoThanhToan;
    private javax.swing.JRadioButton rdoDa;
    private javax.swing.JRadioButton rdoDaThanhToan;
    private javax.swing.JRadioButton rdoHuy;
    private javax.swing.JTabbedPane tabs1;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
