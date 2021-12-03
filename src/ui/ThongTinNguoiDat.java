/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import Entity.HoaDon;
import Entity.HoaDonChiTiet;
import Entity.SanBong;
import dao.HoaDonCTDao;
import dao.HoaDonDao;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import utils.MsgBox;
import ui.QuanLyDatSan;
import utils.Auth;
import utils.XDate;
import utils.XImage;

/**
 *
 * @author sonho
 */
public class ThongTinNguoiDat extends javax.swing.JInternalFrame {

    HoaDonCTDao hdctdao = new HoaDonCTDao();
    HoaDonDao hddao = new HoaDonDao();
    int check = 1;

    /**
     * Creates new form ThongTinNguoiDat
     */
    public ThongTinNguoiDat() {
        initComponents();
        init();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNguoiDat = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtGiaSan = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        rd01 = new javax.swing.JRadioButton();
        rd02 = new javax.swing.JRadioButton();
        rd03 = new javax.swing.JRadioButton();
        rd04 = new javax.swing.JRadioButton();
        rd08 = new javax.swing.JRadioButton();
        rd05 = new javax.swing.JRadioButton();
        rd06 = new javax.swing.JRadioButton();
        rd07 = new javax.swing.JRadioButton();
        btnXacNhan = new javax.swing.JButton();
        btnDong = new javax.swing.JButton();
        txtSDT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtngayThanhToan = new javax.swing.JTextField();

        setTitle("Thông Tin Người Đặt");

        jLabel1.setText("Tên Người Đặt");

        jLabel2.setText("Số Điện Thoại");

        jLabel3.setText("Email");

        jLabel4.setText("Giờ Đá");

        jLabel5.setText("Giá Sân");

        txtGiaSan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaSanActionPerformed(evt);
            }
        });

        buttonGroup1.add(rd01);
        rd01.setText("7h-9h");

        buttonGroup1.add(rd02);
        rd02.setText("9h-11h");

        buttonGroup1.add(rd03);
        rd03.setText("11h-13h");

        buttonGroup1.add(rd04);
        rd04.setText("13h-15h");

        buttonGroup1.add(rd08);
        rd08.setText("21h-23h");

        buttonGroup1.add(rd05);
        rd05.setText("15h-17h");

        buttonGroup1.add(rd06);
        rd06.setText("17h-19h");

        buttonGroup1.add(rd07);
        rd07.setText("19h-21h");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rd01)
                        .addGap(29, 29, 29)
                        .addComponent(rd02)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rd03)
                        .addGap(20, 20, 20)
                        .addComponent(rd04))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rd05)
                        .addGap(29, 29, 29)
                        .addComponent(rd06)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rd07)
                        .addGap(20, 20, 20)
                        .addComponent(rd08)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rd01)
                    .addComponent(rd02)
                    .addComponent(rd03)
                    .addComponent(rd04))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rd05)
                    .addComponent(rd06)
                    .addComponent(rd07)
                    .addComponent(rd08))
                .addContainerGap())
        );

        btnXacNhan.setText("XÁC NHẬN");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        btnDong.setText("ĐÓNG");
        btnDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongActionPerformed(evt);
            }
        });

        jLabel6.setText("Ngày Thanh Toán");

        txtngayThanhToan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtngayThanhToanFocusLost(evt);
            }
        });
        txtngayThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtngayThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGap(56, 56, 56))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(39, 39, 39)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(38, 38, 38))
                    .addComponent(jLabel5))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtGiaSan)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnXacNhan)
                            .addGap(49, 49, 49)
                            .addComponent(btnDong))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtngayThanhToan)
                                .addComponent(txtNguoiDat)
                                .addComponent(txtSDT)
                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(8, 8, 8))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNguoiDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtngayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtGiaSan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDong)
                    .addComponent(btnXacNhan))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        // TODO add your handling code here:
        if (MsgBox.confirm(this, "Bạn có muốn thoát không")) {
            QuanLyDatSan datsan = new QuanLyDatSan();
            datsan.setVisible(true);
            this.getDesktopPane().add(datsan);
            this.dispose();
        }
    }//GEN-LAST:event_btnDongActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        CheckDL();
        if (check == 1) {
            xacNhan();
            moi();
        }


    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void moi() {
        txtEmail.setText("");
        txtNguoiDat.setText("");
        txtSDT.setText("");
        txtngayThanhToan.setText("");
        rd01.setEnabled(true);
        rd02.setEnabled(true);
        rd03.setEnabled(true);
        rd04.setEnabled(true);
        rd05.setEnabled(true);
        rd06.setEnabled(true);
        rd07.setEnabled(true);
        rd08.setEnabled(true);
    }

    private void txtGiaSanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaSanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaSanActionPerformed

    private void txtngayThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtngayThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtngayThanhToanActionPerformed

    private void txtngayThanhToanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtngayThanhToanFocusLost
        // TODO add your handling code here:
        
        List<HoaDonChiTiet>list = hdctdao.selectHoaDonChiTiet(QuanLyDatSan.sb.getMaSan(),XDate.toDate(txtngayThanhToan.getText(),"yyyy/MM/dd"));
        for (HoaDonChiTiet x : list) {
            if (x.getMaKG().equals("KG01")) {
                rd01.setEnabled(false);
            }
            else if (x.getMaKG().equals("KG02")) {
                rd02.setEnabled(false);
            }
            else if (x.getMaKG().equals("KG03")) {
                rd03.setEnabled(false);
            }
            else if (x.getMaKG().equals("KG04")) {
                rd04.setEnabled(false);
            }
            else if (x.getMaKG().equals("KG05")) {
                rd05.setEnabled(false);
            }
            else if (x.getMaKG().equals("KG06")) {
                rd06.setEnabled(false);
            }
            else if (x.getMaKG().equals("KG07")) {
                rd07.setEnabled(false);
            }
            else if (x.getMaKG().equals("KG08")) {
                rd08.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txtngayThanhToanFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDong;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rd01;
    private javax.swing.JRadioButton rd02;
    private javax.swing.JRadioButton rd03;
    private javax.swing.JRadioButton rd04;
    private javax.swing.JRadioButton rd05;
    private javax.swing.JRadioButton rd06;
    private javax.swing.JRadioButton rd07;
    private javax.swing.JRadioButton rd08;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGiaSan;
    private javax.swing.JTextField txtNguoiDat;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtngayThanhToan;
    // End of variables declaration//GEN-END:variables

    void init() {
        System.out.println(QuanLyDatSan.sb.getMaSan());
        this.setFrameIcon(new ImageIcon(XImage.getAppIcon()));
        txtGiaSan.setEnabled(false);
        txtGiaSan.setText(QuanLyDatSan.sb.getGiaSan() + "");
        rd01.setEnabled(true);
        rd02.setEnabled(true);
        rd03.setEnabled(true);
        rd04.setEnabled(true);
        rd05.setEnabled(true);
        rd06.setEnabled(true);
        rd07.setEnabled(true);
        rd08.setEnabled(true);

    }

    
    void xacNhan() {
        String Email = txtEmail.getText();
        Date NgayThanhToan = XDate.toDate(txtngayThanhToan.getText());
        HoaDon hd = hddao.selecthoaDon(Email, NgayThanhToan);

        if (hd != null) {
            HoaDonChiTiet model1 = new HoaDonChiTiet();
            model1.setMaHD(hd.getMaHD());
            model1.setMaSan(QuanLyDatSan.sb.getMaSan());
            String maKG = null;
            if (rd01.isSelected()) {
                maKG = "KG01";
            }
            if (rd02.isSelected()) {
                maKG = "KG02";
            }
            if (rd03.isSelected()) {
                maKG = "KG03";
            }
            if (rd04.isSelected()) {
                maKG = "KG04";
            }
            if (rd05.isSelected()) {
                maKG = "KG05";
            }
            if (rd06.isSelected()) {
                maKG = "KG06";
            }
            if (rd07.isSelected()) {
                maKG = "KG07";
            }
            if (rd08.isSelected()) {
                maKG = "KG08";
            }
            model1.setMaKG(maKG);
            model1.setNgayDat(XDate.now());
            model1.setGiaTien(QuanLyDatSan.sb.getGiaSan());
            model1.setTrangThai(0);
            hdctdao.insert(model1);
            MsgBox.alert(this, "Bạn đã đặt thêm sân thành công.");
        } else {
            HoaDon model = getModel();
            hddao.insert(model);
            HoaDon hd2 = hddao.selectByEmailHoaDon(model.getEmail());
            HoaDonChiTiet model1 = new HoaDonChiTiet();
            model1.setMaHD(hd2.getMaHD());
            model1.setMaSan(QuanLyDatSan.sb.getMaSan());
            String maKG1 = null;
            if (rd01.isSelected()) {
                maKG1 = "KG01";
            }
            if (rd02.isSelected()) {
                maKG1 = "KG02";
            }
            if (rd03.isSelected()) {
                maKG1 = "KG03";
            }
            if (rd04.isSelected()) {
                maKG1 = "KG04";
            }
            if (rd05.isSelected()) {
                maKG1 = "KG05";
            }
            if (rd06.isSelected()) {
                maKG1 = "KG06";
            }
            if (rd07.isSelected()) {
                maKG1 = "KG07";
            }
            if (rd08.isSelected()) {
                maKG1 = "KG08";
            }
            model1.setMaKG(maKG1);
            model1.setNgayDat(XDate.now());
            model1.setGiaTien(QuanLyDatSan.sb.getGiaSan());
            model1.setTrangThai(0);
            hdctdao.insert(model1);
            MsgBox.alert(this, "Bạn đã đặt sân thành công. Vui lòng đặt cọc.");
        }
    }

    HoaDon getModel() {
        HoaDon model = new HoaDon();
        if (Auth.user1 == null) {
            model.setMaNV("NV01");
        } else {
            model.setMaNV(Auth.user1.getMaNV());
        }

        if (Auth.user2 == null) {
            model.setMaKH("KHbot");
        } else {
            model.setMaKH(Auth.user2.getMaKH());
        }
        model.setHoTenKH(txtNguoiDat.getText());
        model.setSoDienThoai(txtSDT.getText());
        model.setEmail(txtEmail.getText());
        model.setGiamGia(0);
        model.setTongTien(Float.parseFloat(txtGiaSan.getText()));
        model.setAnhDatCoc(null);
        model.setNgayThanhToan(XDate.toDate(txtngayThanhToan.getText()));
        model.setTrangThaiHD(0);
        model.setTrangThaiTT(0);
        return model;
    }

    void CheckDL() {
        String hoten = "[a-zA-Z]+";
        String sdt = "((09|03|07|08|05)+([0-9]{8}))";
        String email = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        // check hoten
        if (txtNguoiDat.getText().length() == 0) {
            MsgBox.alert(this, "Bạn chưa điền thông tin họ tên");
            txtNguoiDat.requestFocus();
            check = 0;
            return;
        } else if (txtNguoiDat.getText().matches(hoten) == false) {
            MsgBox.alert(this, "Bạn điền sai thông tin email");
            txtNguoiDat.requestFocus();
            check = 0;
            return;
        } else {
            check = 1;
        }
        // check sdt
        if (txtSDT.getText().length() == 0) {
            MsgBox.alert(this, "Bạn chưa điền thông tin Số ĐT");
            txtSDT.requestFocus();
            check = 0;
            return;
        } else if (txtSDT.getText().matches(sdt) == false) {
            MsgBox.alert(this, "Bạn điền sai thông tin Số ĐT");
            txtSDT.requestFocus();
            check = 0;
            return;
        } else {
            check = 1;
        }
        // check email
        if (txtEmail.getText().length() == 0) {
            MsgBox.alert(this, "Bạn chưa điền thông tin Email");
            txtEmail.requestFocus();
            check = 0;
            return;
        } else if (txtEmail.getText().matches(email) == false) {
            MsgBox.alert(this, "Bạn điền sai thông tin Email");
            txtEmail.requestFocus();
            check = 0;
            return;
        } else {
            check = 1;
        }
        //check ngày đặt 
        java.util.Date ns;
        SimpleDateFormat df;
        if (txtngayThanhToan.getText().length() == 0) {
            MsgBox.alert(this, "chưa có dữ liệu ngày sinh");
            txtngayThanhToan.requestFocus();
            check = 0;
            return;
        } else if (txtngayThanhToan.getText() != null) {
            df = new SimpleDateFormat("yyyy/MM/dd");
            Date a = XDate.toDate(txtngayThanhToan.getText(), "yyyy/MM/dd");
            Date b = XDate.now();
            try {
                ns = df.parse(txtngayThanhToan.getText());
                if (a.before(b)) {
                    MsgBox.alert(this, "Ngày thanh toán không hợp lệ");
                    check = 0;
                    return;
                }
            } catch (Exception e) {
                MsgBox.alert(this, "Ngày thanh toán không đúng ");
                txtngayThanhToan.requestFocus();
                check = 0;
                return;
            }
        } else {
            check = 1;
        }
    }
}
