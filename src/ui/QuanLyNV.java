/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import Entity.KhachHang;
import Entity.NhanVien;
import dao.NhanVienDao;
import static java.awt.Color.white;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.Auth;
import utils.MsgBox;
import utils.XDate;
import utils.XImage;

/**
 *
 * @author PC
 */
public class QuanLyNV extends javax.swing.JInternalFrame {

    List<NhanVien> listNV=new ArrayList<>();
    DefaultTableModel mol;
    int index;
    static String manhanvien;
    public QuanLyNV() {
        initComponents();
        init();
        load();
    }

    void init(){
        this.setFrameIcon(new ImageIcon(XImage.getAppIcon()));
    }
    void load() {
        mol=(DefaultTableModel)tblDanhSachNV.getModel();
        mol.setRowCount(0);
        NhanVienDao dao=new NhanVienDao();
        try {
            listNV = dao.selectAll();   
            for(NhanVien x:listNV){
            mol.addRow(new Object[]{x.getMaNV(),
                    matKhauToSao(x.getMatKhau()),
                    x.getHoTen(),
                    x.getDiaChi(),
                    x.getVaiTro() ? "Quản lý" : "Nhân viên",
                    x.getTrangThai()? "Đi làm" : "Nghỉ việc"
            });
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
   
    void edit() {
        setTrang();
        NhanVienDao dao=new NhanVienDao();
        try {
            String manv = (String) tblDanhSachNV.getValueAt(this.index, 0);  //lấy maNV từ bảng theo index
            NhanVien model = dao.selectById(manv); //dùng maNV tìm đối tượng nhanVien
            if (model != null) {
                this.setModel(model);   //điền thông tin đt nhanVien lên form
                this.setStatus(false);  //chuyển sang editable
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    public void setTrang(){
        txtMaNV.setBackground(white);
        txtMatKhau.setBackground(white);
        txtDiaChi.setBackground(white);
        txtHoTen.setBackground(white);
    }
    void setModel(NhanVien model) {
        txtMaNV.setText(model.getMaNV());
        txtHoTen.setText(model.getHoTen());
        txtMatKhau.setText(model.getMatKhau());
        txtDiaChi.setText(model.getDiaChi());
        rdoQuanLy.setSelected(model.getVaiTro());
        rdoNhanVien.setSelected(!model.getVaiTro());
        rdoDiLam.setSelected(model.getTrangThai());
        rdoNghiViec.setSelected(!model.getTrangThai());
    }

    void setStatus(boolean insertable) {
        txtMaNV.setEditable(insertable);    //enable txtMaNV
        btnThem.setEnabled(insertable);   //enable btnInsert
        btnSua.setEnabled(!insertable);  //disable btnUpdate
        boolean first = this.index > 0;
        boolean last = this.index < tblDanhSachNV.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);//enable 4 nút này khi ở editable
        btnPrev.setEnabled(!insertable && first); //disable khi First, Prev khi ở bản ghi đầu (index = 0)
        btnNext.setEnabled(!insertable && last);  //disable khi Next, Last khi ở bản ghi cuối
        btnLast.setEnabled(!insertable && last);  //index = tblGridView.getRowCount() - 1
    }
    
    //lấy thông tin trên form cho vào đt nhanVien
    NhanVien getModel() {
        NhanVien model = new NhanVien();
        model.setMaNV(txtMaNV.getText());
        model.setHoTen(txtHoTen.getText());
        model.setMatKhau(new String(txtMatKhau.getPassword()));  //chuyển char[] thành String
        model.setDiaChi(txtDiaChi.getText());
        model.setVaiTro(rdoQuanLy.isSelected()?true:false);
        model.setTrangThai(rdoDiLam.isSelected()?true:false);
        return model;
    }
    public String matKhauToSao(String pass){
        String sao="";
        for(int i=0;i<pass.length();i++){
            sao+="*";
        }
        return sao;
    }
    void them(){
        if(Auth.isManager()){
        NhanVien model=getModel();
        NhanVienDao dao=new NhanVienDao();
        dao.insert(model);
        MsgBox.alert(this, "Đã thêm thành công");
        load();
        }else{
            MsgBox.alert(this, "Chỉ quản lý mới được thêm ");
        }
    }
    void sua(){
        if(Auth.isManager()){
        NhanVien model=getModel();
        NhanVienDao dao=new NhanVienDao();
        dao.update(model);
        MsgBox.alert(this, "Đã sửa thành công");
        load();
        }else{
            MsgBox.alert(this, "Chỉ quản lý mới được sửa ");
        }
    }
    void clear() {
        setTrang();
        this.setModel(new NhanVien());  //xóa trắng form
        this.setStatus(true);
    }
    void tim(){
        String tennv=txtTimNV.getText();
        NhanVienDao dao=new NhanVienDao();
        listNV=dao.selectByKeyword(tennv);
        if(listNV.size()>0){
        MsgBox.alert(this, "Đã tìm thấy");
        mol=(DefaultTableModel)tblDanhSachNV.getModel();
        mol.setRowCount(0);
        for(NhanVien x:listNV){
            mol.addRow(new Object[]{x.getMaNV(),
                    matKhauToSao(x.getMatKhau()),
                    x.getHoTen(),
                    x.getDiaChi(),
                    x.getVaiTro() ? "Quản lý" : "Nhân viên",
                    x.getTrangThai()? "Đi làm" : "Nghỉ việc"});
        }
        }else{
            MsgBox.alert(this, "Chưa tìm thấy");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        rdoDiLam = new javax.swing.JRadioButton();
        rdoNghiViec = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        rdoQuanLy = new javax.swing.JRadioButton();
        rdoNhanVien = new javax.swing.JRadioButton();
        btnXemTTChiTiet = new javax.swing.JButton();
        txtMatKhau = new javax.swing.JPasswordField();
        txtHoTen = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTimNV = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSachNV = new javax.swing.JTable();

        jLabel2.setText("Mã nhân viên");

        jLabel3.setText("Mật khẩu");

        jLabel5.setText("Họ tên");

        jLabel6.setText("Địa chỉ");

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-tag-24.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-24.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-update-tag-24.png"))); // NOI18N
        btnSua.setText("Sửa");
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

        jLabel8.setText("Trạng Thái");

        buttonGroup2.add(rdoDiLam);
        rdoDiLam.setSelected(true);
        rdoDiLam.setText("Đi Làm");

        buttonGroup2.add(rdoNghiViec);
        rdoNghiViec.setText("Nghỉ Việc");
        rdoNghiViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNghiViecActionPerformed(evt);
            }
        });

        jLabel1.setText("Vai trò");

        buttonGroup1.add(rdoQuanLy);
        rdoQuanLy.setSelected(true);
        rdoQuanLy.setText("Quản Lý");

        buttonGroup1.add(rdoNhanVien);
        rdoNhanVien.setText("Nhân Viên");

        btnXemTTChiTiet.setText("Xem thông tin chi tiết");
        btnXemTTChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemTTChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(38, 616, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaChi)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(82, 82, 82)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rdoQuanLy)
                                            .addComponent(rdoDiLam, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(93, 93, 93)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rdoNghiViec, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rdoNhanVien))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThem)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua)
                                .addGap(18, 18, 18)
                                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnXemTTChiTiet))
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoQuanLy)
                        .addComponent(rdoNhanVien)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoNghiViec)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(rdoDiLam)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNext, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLast))
                        .addComponent(btnFirst, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(btnPrev)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem)
                        .addComponent(btnSua)
                        .addComponent(btnMoi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXemTTChiTiet)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        tabs.addTab("Cập Nhật", jPanel1);

        jLabel7.setText("Tìm Kiếm");

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-24_1.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimNV)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimNV)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tblDanhSachNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Mật khẩu", "Họ tên", "Địa chỉ", "Vai Trò", "Trạng Thái"
            }
        ));
        tblDanhSachNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSachNV);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        tabs.addTab("Danh Sách", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabs)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 451, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        index=listNV.size()-1;
        edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        index++;
        edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        sua();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void rdoNghiViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNghiViecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNghiViecActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        them();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblDanhSachNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachNVMouseClicked
        // TODO add your handling code here:
        
            this.index = tblDanhSachNV.getSelectedRow();//lấy vị trí dòng được chọn
            if (this.index >= 0) {
                this.edit();
                tabs.setSelectedIndex(0);
            }
        
    }//GEN-LAST:event_tblDanhSachNVMouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        index=0;
        edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        index--;
        edit();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        tim();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnXemTTChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemTTChiTietActionPerformed
        // TODO add your handling code here:
        if(!txtMaNV.getText().trim().isEmpty()){
            manhanvien=txtMaNV.getText().trim();
            ThongTinChiTietNV formtt=new ThongTinChiTietNV(manhanvien);
            formtt.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Chưa chọn đối tượng");
        }
    }//GEN-LAST:event_btnXemTTChiTietActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXemTTChiTiet;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoDiLam;
    private javax.swing.JRadioButton rdoNghiViec;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoQuanLy;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblDanhSachNV;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTimNV;
    // End of variables declaration//GEN-END:variables
}
