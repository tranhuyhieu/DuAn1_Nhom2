/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ui;

import Entity.DichVu;
import Entity.HoaDonChiTiet;
import dao.DichVuDao;
import dao.HoaDonCTDao;
import dao.KhungGioDao;
import dao.SanBongDao;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import utils.MsgBox;
import utils.XDate;
import utils.XImage;

/**
 *
 * @author ASUS
 */
public class QLHoaDonChiTiet extends javax.swing.JInternalFrame {

    /**
     * Creates new form QLHoaDon
     */
    public QLHoaDonChiTiet() {
        initComponents();
        init();
    }

    DefaultTableModel mol;
    HoaDonCTDao dao = new HoaDonCTDao();
    KhungGioDao kgDao = new KhungGioDao();
    SanBongDao sbDao = new SanBongDao();
    DichVuDao dvdao = new DichVuDao();

    int index = 0;
    int row = -1;
    static int maHDCT;

    void init() {
        setFrameIcon(new ImageIcon(XImage.getAppIcon()));
        updateStatus();
        this.fillTable();
        this.fillDV();
        tabs.setSelectedIndex(1);
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblHDCT.getModel();
        model.setRowCount(0);
        try {
            List<HoaDonChiTiet> list = dao.selectByIdHD(QuanLyHoaDonJInternalFrame.maHD);
            for (HoaDonChiTiet hdct: list) {
                Object[] row = {hdct.getMaHDCT(), hdct.getMaHD(), hdct.getMaSan(),
                    hdct.getMaKG(), hdct.getNgayDat(), hdct.getTrongTai(),hdct.getGiaTien(), hdct.getTrangThai() == 2 ? "Hủy" : "Xác nhận"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void fillDV(){
       DefaultTableModel model = (DefaultTableModel) tblDichVu.getModel();
       model.setRowCount(0);
       model.addRow(new Object[]{"Đồ uống", 15000});
       model.addRow(new Object[]{"Thuê giày", 20000});
       model.addRow(new Object[]{"Thuê áo lưới", 10000});
    }
    
    void setForm(HoaDonChiTiet hdct) {
        txtHDCT.setText(String.valueOf(hdct.getMaHDCT()));
        txtHD.setText(String.valueOf(hdct.getMaHD()));
        txtMaSan.setText(hdct.getMaSan());
        txtMaKG.setText(hdct.getMaKG());
        txtNgayDat.setText(XDate.toString(hdct.getNgayDat(), "dd/MM/yyyy"));        
        if (hdct.getTrongTai() == null || hdct.getTrongTai().equals("")){
            rdoThueTrongTai.setSelected(false);
        }else{
            rdoThueTrongTai.setSelected(true);
        }
        txtTongTien.setText(String.valueOf(hdct.getGiaTien()));
        if (hdct.getTrangThai() == 2) {
            rdoHuy.setSelected(true);
        } else {
            rdoXacNhan.setSelected(true);
        }
    }

    HoaDonChiTiet getModel() {
        HoaDonChiTiet hdct = dao.selectById(Integer.parseInt(txtHDCT.getText()));
        hdct.setMaSan(txtMaSan.getText());      
        hdct.setMaKG(txtMaKG.getText());
        if (rdoThueTrongTai.isSelected()) {
            hdct.setTrongTai("Có");
        } else {
            hdct.setTrongTai("");
        }
        hdct.setGiaTien(Float.parseFloat(txtTongTien.getText()));
        if (rdoXacNhan.isSelected()) {
            hdct.setTrangThai(1);
        } else {
            hdct.setTrangThai(2);
        }
        return hdct;
    }

    void clearForm() {
        txtHDCT.setText("");
        txtHD.setText("");
        txtMaSan.setText("");
        txtMaKG.setText("");
        txtNgayDat.setText("");
        txtTongTien.setText("");
        txtSoLuong.setText("");
        txtTTDV.setText("");
        rdoXacNhan.setSelected(true);
        this.updateStatus();
        row = -1;
        updateStatus();
    }

    void edit() {
        HoaDonChiTiet hdct = dao.selectById(this.maHDCT);
        this.setForm(hdct);
        tabs.setSelectedIndex(0);
        this.updateStatus();
    }

    void first() {
        row = 0;
        edit();
    }

    void last() {
        row = tblHDCT.getRowCount() - 1;
        edit();
    }

    void prev() {
        if (row > 0) {
            row--;
            edit();
        }
    }

    void next() {
        if (row < tblHDCT.getRowCount() - 1) {
            row++;
            edit();
        }
    }

    void updateStatus() {
        boolean edit = this.row >= 0;
        boolean dau = this.row == 0;
        boolean cuoi = this.row == tblHDCT.getRowCount() - 1;

        // khi insert thi except to update and delate
        btnSua.setEnabled(edit);

        btnFirst.setEnabled(edit && !dau);
        btnPrev.setEnabled(edit && !dau);
        btnNext.setEnabled(edit && !cuoi);
        btnLast.setEnabled(edit && !cuoi);
    }


    void update() {
        HoaDonChiTiet hdct = getModel();
        dao.update(hdct);
        fillTable();
    }

    DichVu getModeldv() {
        this.index = tblDichVu.getSelectedRow();
        DichVu dv = new DichVu();
        dv.setMaHDCT(Integer.parseInt(txtHDCT.getText()));
        dv.setTenDV(String.valueOf(tblDichVu.getValueAt(this.index, 0)));
        dv.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        dv.setGiaTien(Float.parseFloat(String.valueOf(tblDichVu.getValueAt(this.index, 1))));
        dv.setTrangThaiDichVu(true);
        return dv;
    }

    void insertDV() {
        DichVu model = getModeldv();
        dvdao = new DichVuDao();
        dvdao.insert(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtHD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNgayDat = new javax.swing.JTextField();
        btnMoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtMaKG = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDichVu = new javax.swing.JTable();
        lbla = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rdoXacNhan = new javax.swing.JRadioButton();
        txtMaSan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtHDCT = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        txtTTDV = new javax.swing.JTextField();
        rdoHuy = new javax.swing.JRadioButton();
        btnXemDV = new javax.swing.JButton();
        rdoThueTrongTai = new javax.swing.JRadioButton();
        btnThemDV = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();

        setClosable(true);
        setTitle("Quản lý hóa đơn chi tiết");

        tabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabsMouseClicked(evt);
            }
        });

        jLabel2.setText("Mã hóa đơn:");

        txtHD.setEnabled(false);

        jLabel3.setText("Mã sân:");

        jLabel5.setText("Mã khung giờ:");

        jLabel6.setText("Ngày đặt:");

        txtNgayDat.setEnabled(false);

        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-delete-24_1.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-update-tag-24.png"))); // NOI18N
        btnSua.setText("Cập nhật");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-skip-to-start-24.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-rewind.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-rewind-2_1.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-skip-to-start_1.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        jLabel1.setText("Danh sách dịch vụ:");

        txtMaKG.setEnabled(false);

        tblDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Đồ uống", "15000"},
                {"Thuê giày", "20000"},
                {"Thuê áo lưới", "10000"}
            },
            new String [] {
                "Tên dịch vụ", "Giá tiên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDichVuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDichVu);

        lbla.setText("Tổng tiền dịch vụ:");

        jLabel9.setText("Tổng tiền: ");

        txtTongTien.setEnabled(false);

        jLabel4.setText("Trạng thái hóa đơn:");

        buttonGroup1.add(rdoXacNhan);
        rdoXacNhan.setText("Xác nhận");

        txtMaSan.setEnabled(false);

        jLabel10.setText("Mã hóa đơn chi tiết:");

        txtHDCT.setEnabled(false);

        jLabel11.setText("Số lương:");

        txtSoLuong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSoLuongFocusLost(evt);
            }
        });

        txtTTDV.setEnabled(false);

        buttonGroup1.add(rdoHuy);
        rdoHuy.setText("Hủy");

        btnXemDV.setText("Xem dịch vụ");
        btnXemDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemDVActionPerformed(evt);
            }
        });

        rdoThueTrongTai.setText("Thuê trọng tài");
        rdoThueTrongTai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoThueTrongTaiActionPerformed(evt);
            }
        });

        btnThemDV.setText("Thêm dịch vụ");
        btnThemDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbla, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTTDV, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoThueTrongTai)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaSan, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHD, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaKG, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(61, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdoXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoHuy)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXemDV)
                                .addGap(18, 18, 18)
                                .addComponent(btnThemDV))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSua)
                                .addGap(11, 11, 11)
                                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)
                        .addGap(6, 6, 6)
                        .addComponent(txtHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(txtMaSan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(txtHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(6, 6, 6)
                        .addComponent(txtMaKG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(6, 6, 6)
                .addComponent(txtNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lbla)
                            .addGap(6, 6, 6)
                            .addComponent(txtTTDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel9)
                            .addGap(6, 6, 6)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoHuy)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(6, 6, 6)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(rdoThueTrongTai)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXemDV)
                            .addComponent(btnThemDV))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast)
                    .addComponent(btnSua)
                    .addComponent(btnMoi))
                .addContainerGap())
        );

        tabs.addTab("Cập Nhật", jPanel1);

        jPanel2.setLayout(null);

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn chi tiết", "Mã hóa đơn", "Mã sân", "Khung giờ", "Ngày đặt", "Trọng tài", "Tổng tiền", "Trạng Thái"
            }
        ));
        tblHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDCTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHDCT);
        if (tblHDCT.getColumnModel().getColumnCount() > 0) {
            tblHDCT.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 680, 530);

        tabs.addTab("Danh Sách", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if(txtSoLuong.getText().equals("")){
            if (rdoHuy.isSelected()) {
                txtTongTien.setText("0");
                update();
                MsgBox.alert(this, "Cập nhật trạng thái thành công");
            } else {
                update();
                MsgBox.alert(this, "Cập nhật trạng thái thành công");
            }
        }else {
            MsgBox.alert(this, "Cập nhật thất bại.");
            txtSoLuong.setText("");
            return;
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCTMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblHDCT.rowAtPoint(evt.getPoint());
            this.maHDCT = Integer.parseInt(String.valueOf(tblHDCT.getValueAt(row, 0)));
            edit();
            updateStatus();
            if (rdoHuy.isSelected()) {
                btnSua.setEnabled(false);
                btnXemDV.setEnabled(false);
                btnThemDV.setEnabled(false);
            } else {
                btnXemDV.setEnabled(true);
                btnThemDV.setEnabled(true);
            }
        }
    }//GEN-LAST:event_tblHDCTMouseClicked

    int index1 = -1;
    private void tblDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDichVuMouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {
            txtSoLuong.setText(1 + "");
            this.index = tblDichVu.getSelectedRow();
            tblDichVu.setRowSelectionInterval(index, index);
                    
            
            
            if (this.index != index1) {
                if (index1 >= 0) {
                    float x2 = Float.parseFloat(tblDichVu.getValueAt(index1, 1) + "");
                    float tongTien2 = Float.parseFloat(txtTongTien.getText());
                    txtTongTien.setText(String.valueOf(tongTien2 - x2));
                }
            }else{
                MsgBox.alert(this, "Bạn đã chọn dịch vụ này. Vui lòng chọn số lượng");
                return;
            }

            
            if (this.index >= 0) {
                x1 = Float.parseFloat(tblDichVu.getValueAt(this.index, 1) + "");
                txtTTDV.setText(String.valueOf(x1));
                tongTien = Float.parseFloat(txtTongTien.getText());
                txtTongTien.setText(String.valueOf(tongTien + x1));

                index1 = this.index;
            }
        }
        

    }//GEN-LAST:event_tblDichVuMouseClicked

    private void rdoThueTrongTaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoThueTrongTaiActionPerformed
        // TODO add your handling code here:
        if (rdoThueTrongTai.isSelected()) {
            float tt = Float.parseFloat(txtTongTien.getText());
            txtTongTien.setText(String.valueOf(tt + 100000));
        } else {
            float tt = Float.parseFloat(txtTongTien.getText());
            txtTongTien.setText(String.valueOf(tt - 100000));
        }
    }//GEN-LAST:event_rdoThueTrongTaiActionPerformed

    float x1;
    float tongTien;

    private void btnXemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemDVActionPerformed
        // TODO add your handling code here:
        QLDichVu dv = new QLDichVu();
        this.getDesktopPane().add(dv);
        dv.setVisible(true);
    }//GEN-LAST:event_btnXemDVActionPerformed

    private void txtSoLuongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLuongFocusLost
        // TODO add your handling code here:
        int sl = Integer.parseInt(txtSoLuong.getText());
        txtTongTien.setText(String.valueOf(this.tongTien + (this.x1 * sl)));
    }//GEN-LAST:event_txtSoLuongFocusLost

    private void btnThemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDVActionPerformed
        // TODO add your handling code here:
        String p_sl = "[0-9]{1,100}";
        if (txtTTDV.getText().equals("")) {
            MsgBox.alert(this, "Vui lòng chọn dịch vụ");
            return;
        } else {
            if (txtSoLuong.getText().matches(p_sl) == false) {
                txtSoLuong.setText(1+"");
                MsgBox.alert(this, "Số lượng không đúng định dạng");
                
                return;
            } else {
                insertDV();
                update();
                MsgBox.alert(this, "Thêm dịch vụ thành công.");
                this.fillDV();
                clearForm();         
            }
        }
    }//GEN-LAST:event_btnThemDVActionPerformed

    private void tabsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabsMouseClicked
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_tabsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThemDV;
    private javax.swing.JButton btnXemDV;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbla;
    private javax.swing.JRadioButton rdoHuy;
    private javax.swing.JRadioButton rdoThueTrongTai;
    private javax.swing.JRadioButton rdoXacNhan;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblDichVu;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTextField txtHD;
    private javax.swing.JTextField txtHDCT;
    private javax.swing.JTextField txtMaKG;
    private javax.swing.JTextField txtMaSan;
    private javax.swing.JTextField txtNgayDat;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTTDV;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
