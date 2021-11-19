/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.Timer;
import ui.DangNhap;
import utils.Auth;
import utils.MsgBox;
import utils.XImage;

/**
 *
 * @author sonho
 */
public class FormChinh extends javax.swing.JFrame {

    /**
     * Creates new form FormChinh
     */
    public FormChinh() {
        initComponents();
        init();
        
    }

    void dangnhap(){
        new DangNhap(this, true).setVisible(true);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ToolBar = new javax.swing.JToolBar();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnDangXuat = new javax.swing.JButton();
        Separator02 = new javax.swing.JToolBar.Separator();
        btnTrangChu = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnQLSan = new javax.swing.JButton();
        Separator01 = new javax.swing.JToolBar.Separator();
        btnDangNhap2 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        btnDangKy = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JToolBar.Separator();
        btnQuenMatKhau = new javax.swing.JButton();
        Separator3 = new javax.swing.JToolBar.Separator();
        btnDangNhap = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        DesktopPane = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnithongTinCaNhan = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnidangNhap = new javax.swing.JMenuItem();
        mniDangKy = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        mniDangXuat = new javax.swing.JMenuItem();
        mniketThuc = new javax.swing.JMenuItem();
        MenuQuanLy = new javax.swing.JMenu();
        mniNhanVien = new javax.swing.JMenuItem();
        mniKhachHang = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        mniQLsanBong = new javax.swing.JMenuItem();
        mniHoaDon = new javax.swing.JMenuItem();
        mniQLLichSuDat = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mniQLThongKe = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mniLienHe = new javax.swing.JMenuItem();
        mniGioiThieu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trang Chủ");

        ToolBar.setRollover(true);
        ToolBar.add(jSeparator2);

        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-exit-48 (1).png"))); // NOI18N
        btnDangXuat.setText("Đăng Xuất");
        btnDangXuat.setFocusable(false);
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        ToolBar.add(btnDangXuat);
        ToolBar.add(Separator02);

        btnTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-home-48.png"))); // NOI18N
        btnTrangChu.setText("Trang Chủ");
        btnTrangChu.setFocusable(false);
        btnTrangChu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTrangChu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ToolBar.add(btnTrangChu);
        ToolBar.add(jSeparator3);

        btnQLSan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-football-48.png"))); // NOI18N
        btnQLSan.setText("Quản Lý Đặt Sân ");
        btnQLSan.setFocusable(false);
        btnQLSan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLSan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ToolBar.add(btnQLSan);
        ToolBar.add(Separator01);

        btnDangNhap2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-order-history-48.png"))); // NOI18N
        btnDangNhap2.setText("Quản Lý Lịch Sử Đặt Sân");
        btnDangNhap2.setFocusable(false);
        btnDangNhap2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangNhap2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ToolBar.add(btnDangNhap2);
        ToolBar.add(jSeparator10);

        btnDangKy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-sign-up-48.png"))); // NOI18N
        btnDangKy.setText("Đăng Ký");
        btnDangKy.setFocusable(false);
        btnDangKy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangKy.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKyActionPerformed(evt);
            }
        });
        ToolBar.add(btnDangKy);
        ToolBar.add(jSeparator11);

        btnQuenMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-password-reset-48.png"))); // NOI18N
        btnQuenMatKhau.setText("Quên Mật Khẩu");
        btnQuenMatKhau.setFocusable(false);
        btnQuenMatKhau.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQuenMatKhau.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQuenMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuenMatKhauActionPerformed(evt);
            }
        });
        ToolBar.add(btnQuenMatKhau);
        ToolBar.add(Separator3);

        btnDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-login-48.png"))); // NOI18N
        btnDangNhap.setText("Đăng Nhập");
        btnDangNhap.setFocusable(false);
        btnDangNhap.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangNhap.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        ToolBar.add(btnDangNhap);
        ToolBar.add(jSeparator4);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fa529f0341f489aad0e5 (1).png"))); // NOI18N

        DesktopPane.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DesktopPaneLayout = new javax.swing.GroupLayout(DesktopPane);
        DesktopPane.setLayout(DesktopPaneLayout);
        DesktopPaneLayout.setHorizontalGroup(
            DesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DesktopPaneLayout.setVerticalGroup(
            DesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblDongHo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-clock-16.png"))); // NOI18N
        lblDongHo.setText("11:41:17 AM");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/i.png"))); // NOI18N
        jLabel3.setText("Hệ Thống Quản Lý ");

        jMenu1.setText("Hệ Thống ");

        mnithongTinCaNhan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnithongTinCaNhan.setText("Thông Tin Cá Nhân");
        jMenu1.add(mnithongTinCaNhan);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Đổi Mật Khẩu");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Quên Mật Khẩu");
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        mnidangNhap.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnidangNhap.setText("Đăng Nhập");
        mnidangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnidangNhapActionPerformed(evt);
            }
        });
        jMenu1.add(mnidangNhap);

        mniDangKy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniDangKy.setText("Đăng Ký");
        jMenu1.add(mniDangKy);
        jMenu1.add(jSeparator7);

        mniDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniDangXuat.setText("Đăng Xuất");
        jMenu1.add(mniDangXuat);

        mniketThuc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniketThuc.setText("Kết Thúc");
        jMenu1.add(mniketThuc);

        MenuBar.add(jMenu1);

        MenuQuanLy.setText("Quản Lý ");

        mniNhanVien.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniNhanVien.setText("Quản Lý Nhân Viên");
        MenuQuanLy.add(mniNhanVien);

        mniKhachHang.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniKhachHang.setText("Quản Lý Khách Hàng");
        mniKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKhachHangActionPerformed(evt);
            }
        });
        MenuQuanLy.add(mniKhachHang);
        MenuQuanLy.add(jSeparator8);

        mniQLsanBong.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_7, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniQLsanBong.setText("Quản Lý Sân Bóng");
        mniQLsanBong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLsanBongActionPerformed(evt);
            }
        });
        MenuQuanLy.add(mniQLsanBong);

        mniHoaDon.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        mniHoaDon.setText("Quản Lý Hóa Đơn");
        MenuQuanLy.add(mniHoaDon);

        mniQLLichSuDat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        mniQLLichSuDat.setText("Quản Lý Lịch Sử Đặt Sân");
        MenuQuanLy.add(mniQLLichSuDat);

        MenuBar.add(MenuQuanLy);

        jMenu4.setText("Thống Kê");

        mniQLThongKe.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniQLThongKe.setText("Quản Lý Thống Kê");
        jMenu4.add(mniQLThongKe);

        MenuBar.add(jMenu4);

        jMenu3.setText("Trợ Giúp ");

        mniLienHe.setText("Liên Hệ ");
        jMenu3.add(mniLienHe);

        mniGioiThieu.setText("Giới Thiệu Sản Phẩm");
        jMenu3.add(mniGioiThieu);

        MenuBar.add(jMenu3);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDongHo))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DesktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DesktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDongHo)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKhachHangActionPerformed
        // TODO add your handling code here:
        new QLKH(this, true).setVisible(true);
    }//GEN-LAST:event_mniKhachHangActionPerformed

    private void mnidangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnidangNhapActionPerformed
        // TODO add your handling code here:
        new DangNhap(this, true).setVisible(true);
    }//GEN-LAST:event_mnidangNhapActionPerformed

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        // TODO add your handling code here:
        dangnhap();
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        Auth.user1=null;
        Auth.user2=null;
        dangnhap();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnQuenMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuenMatKhauActionPerformed
        // TODO add your handling code here:
        new QuenMatKhauJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnQuenMatKhauActionPerformed

    private void mniQLsanBongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLsanBongActionPerformed
        // TODO add your handling code here:
        new QLSanBong(this, true).setVisible(true);
    }//GEN-LAST:event_mniQLsanBongActionPerformed

    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKyActionPerformed
        // TODO add your handling code here:
        new DangKyTaiKhoanMoiJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnDangKyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormChinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DesktopPane;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenu MenuQuanLy;
    private javax.swing.JToolBar.Separator Separator01;
    private javax.swing.JToolBar.Separator Separator02;
    private javax.swing.JToolBar.Separator Separator3;
    private javax.swing.JToolBar ToolBar;
    private javax.swing.JButton btnDangKy;
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JButton btnDangNhap2;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnQLSan;
    private javax.swing.JButton btnQuenMatKhau;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JMenuItem mniDangKy;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniGioiThieu;
    private javax.swing.JMenuItem mniHoaDon;
    private javax.swing.JMenuItem mniKhachHang;
    private javax.swing.JMenuItem mniLienHe;
    private javax.swing.JMenuItem mniNhanVien;
    private javax.swing.JMenuItem mniQLLichSuDat;
    private javax.swing.JMenuItem mniQLThongKe;
    private javax.swing.JMenuItem mniQLsanBong;
    private javax.swing.JMenuItem mnidangNhap;
    private javax.swing.JMenuItem mniketThuc;
    private javax.swing.JMenuItem mnithongTinCaNhan;
    // End of variables declaration//GEN-END:variables
    void init (){
        setLocationRelativeTo(null);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setIconImage(XImage.getAppIcon());
        new ChaoJDialog(this, true).setVisible(true);
        new DangNhap(this, true).setVisible(true);
        new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                SimpleDateFormat date = new SimpleDateFormat("hh:mm:ss a");
                String text = date.format(now);
                lblDongHo.setText(text);
               }
        }).start();
    }
}
