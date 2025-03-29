/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.raven.model.Voucher;
import com.raven.repo.VoucherRepo;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class Form_KM extends javax.swing.JPanel {

    /**
     * Creates new form Form_SP
     */
    DefaultTableModel defaultTableModel = new DefaultTableModel();
    VoucherRepo repo = new VoucherRepo();
    ArrayList<Voucher> list = new ArrayList<>();

    public Form_KM() {
        initComponents();
        loadDataTable();
    }

    public void loadDataTable() {
        defaultTableModel = (DefaultTableModel) tbl_khuyenMai.getModel();
        defaultTableModel.setRowCount(0);
        list.clear();
        list = repo.getAll();
        
        String hinhthuc;
        for (Voucher vc : list) {
            if(vc.getHinhThucGiamGia()==1){
                hinhthuc = "giảm trực tiếp trên bill";
            }else {
                hinhthuc = "nhận voucher cho lần sau";
            }
            defaultTableModel.addRow(new Object[]{
                vc.getId(), vc.getMaVoucher(), vc.getGiamGia(),  vc.getGiamGiaToiDa(),  vc.getNgayBatDau(), vc.getNgayKetThuc(), vc.getDieuKienApDung(),hinhthuc,vc.getMota()

            });
        }
    }

    public void fillData(int index) {
    Voucher vc = list.get(index);

    txt_MaKM.setText(vc.getMaVoucher());
    txt_mucGiamGia.setText(String.valueOf(vc.getGiamGia()));
    txt_giamgiaTD.setText(String.valueOf(vc.getGiamGiaToiDa()));
    txt_dieuKien.setText(vc.getDieuKienApDung());
    txt_moTa.setText(vc.getMota());
    
    // Sử dụng SimpleDateFormat để định dạng ngày tháng
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Định dạng ngày bắt đầu
    if (vc.getNgayBatDau() != null) {
        txt_ngayBD.setText(dateFormat.format(vc.getNgayBatDau()));
    }

    // Định dạng ngày kết thúc
    if (vc.getNgayKetThuc() != null) {
        txt_NgayKT.setText(dateFormat.format(vc.getNgayKetThuc()));
    }

    // Thiết lập hình thức giảm giá
    if (vc.getHinhThucGiamGia() == 1) {
        rdoHinhThuc1.setSelected(true);
    } else {
        rdoHinhThuc2.setSelected(true);
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_mucGiamGia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_ngayBD = new javax.swing.JTextField();
        txt_NgayKT = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        btn_lammoi = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_giamgiaTD = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_dieuKien = new javax.swing.JTextField();
        txt_MaKM = new javax.swing.JTextField();
        Header = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lbl_MaNV = new javax.swing.JLabel();
        lbl_Gio = new javax.swing.JLabel();
        lbl_Ngay = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_moTa = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        rdoHinhThuc1 = new javax.swing.JRadioButton();
        rdoHinhThuc2 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_khuyenMai = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(998, 300));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lý khuyến mãi");

        jLabel2.setText("Mã khuyến mãi:");

        txt_mucGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mucGiamGiaActionPerformed(evt);
            }
        });

        jLabel3.setText("Mức giảm giá(%):");

        jLabel4.setText("Ngày bắt đầu:");

        jLabel5.setText("Ngày kết thúc:");

        txt_ngayBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ngayBDActionPerformed(evt);
            }
        });

        txt_NgayKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NgayKTActionPerformed(evt);
            }
        });

        btn_them.setBackground(new java.awt.Color(255, 102, 102));
        btn_them.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(255, 102, 102));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btn_xoa.setBackground(new java.awt.Color(255, 102, 102));
        btn_xoa.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_xoa.setText("Xoá");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_lammoi.setText("Làm mới");
        btn_lammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoiActionPerformed(evt);
            }
        });

        jLabel6.setText("Giảm tối đa:");

        txt_giamgiaTD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_giamgiaTDActionPerformed(evt);
            }
        });

        jLabel7.setText("Mô tả:");

        jLabel8.setText("Điều kiện:");

        txt_dieuKien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dieuKienActionPerformed(evt);
            }
        });

        txt_MaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MaKMActionPerformed(evt);
            }
        });

        Header.setBackground(new java.awt.Color(255, 204, 204));
        Header.setForeground(new java.awt.Color(255, 204, 204));
        Header.setPreferredSize(new java.awt.Dimension(1000, 60));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User.png"))); // NOI18N

        lbl_MaNV.setText("admin");

        lbl_Gio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_Gio.setText("19:01:02");

        lbl_Ngay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_Ngay.setText("19:01:02");

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lbl_Gio)
                .addGap(26, 26, 26)
                .addComponent(lbl_Ngay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_MaNV)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addGap(36, 36, 36))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_MaNV)
                        .addComponent(lbl_Gio)
                        .addComponent(lbl_Ngay))
                    .addComponent(jLabel15))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        txt_moTa.setColumns(20);
        txt_moTa.setRows(5);
        jScrollPane1.setViewportView(txt_moTa);

        jLabel9.setText("Hình Thức Giảm Giá");

        buttonGroup1.add(rdoHinhThuc1);
        rdoHinhThuc1.setText("giảm trực tiếp trên bill");

        buttonGroup1.add(rdoHinhThuc2);
        rdoHinhThuc2.setText("nhận voucher cho lần sau");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_MaKM, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(txt_NgayKT)
                                    .addComponent(txt_ngayBD)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                .addComponent(txt_mucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_dieuKien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txt_giamgiaTD)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoHinhThuc2)
                            .addComponent(rdoHinhThuc1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_them, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate)
                        .addComponent(btn_lammoi))
                    .addComponent(btn_xoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_MaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txt_giamgiaTD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_dieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoHinhThuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoHinhThuc2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel9)))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(33, 33, 33))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(btn_them)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUpdate)
                            .addComponent(jLabel3)
                            .addComponent(txt_mucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(txt_ngayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(txt_NgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel4)
                                        .addGap(73, 73, 73))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_xoa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(btn_lammoi))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbl_khuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Mã Voucher", "Mức giảm", "Giảm tối đa", "Ngày bắt đầu", "Ngày kết thúc", "Điều kiện đơn", "hình thức giảm giá", "Mô tả"
            }
        ));
        tbl_khuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_khuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_khuyenMai);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(42, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_mucGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mucGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mucGiamGiaActionPerformed

    private void txt_ngayBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ngayBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ngayBDActionPerformed

    private void txt_NgayKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NgayKTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NgayKTActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        String maVoucher = txt_MaKM.getText();
        String moTa = txt_moTa.getText();
        Float giamGia = Float.valueOf(txt_mucGiamGia.getText());
        BigDecimal giamGiaToiDa = BigDecimal.valueOf(Double.valueOf(txt_giamgiaTD.getText()));
        int hinhThucGiamGia;
        if (rdoHinhThuc1.isSelected()) {
            hinhThucGiamGia = 1;
        } else {
            hinhThucGiamGia = 0;
        }

        String ngayBDString = txt_ngayBD.getText();
        Date ngayBD = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            ngayBD = dateFormat.parse(ngayBDString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        String ngayKTString = txt_NgayKT.getText();
        Date ngayKT = null;

        try {
            ngayKT = dateFormat.parse(ngayKTString);
        } catch (ParseException e) {
            e.printStackTrace();
            
        }
        String dieuKienApDung = txt_dieuKien.getText();
        Voucher vc = new Voucher(maVoucher, moTa, giamGia, giamGiaToiDa, hinhThucGiamGia, ngayBD, ngayKT, dieuKienApDung);
        if(maVoucher.isEmpty() || moTa.isEmpty()|| dieuKienApDung.isEmpty()){
            JOptionPane.showMessageDialog(this, "vui lòng nhập đầy đủ thông tin");
            return;
    }else if( repo.AddVoucher(vc)){
         JOptionPane.showMessageDialog(this, "thêm thành công");
    }
       
        loadDataTable();

    }//GEN-LAST:event_btn_themActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
            String maVoucher = txt_MaKM.getText();
    String moTa = txt_moTa.getText();
    Float giamGia = Float.valueOf(txt_mucGiamGia.getText());
    BigDecimal giamGiaToiDa = BigDecimal.valueOf(Double.valueOf(txt_giamgiaTD.getText()));
    int hinhThucGiamGia;
    if (rdoHinhThuc1.isSelected()) {
       hinhThucGiamGia = 1;
    } else {
       hinhThucGiamGia = 0;
    }
    String ngayBDString = txt_ngayBD.getText();
    Date ngayBD = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    try {
        ngayBD = dateFormat.parse(ngayBDString);
    } catch (ParseException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Ngày bắt đầu không hợp lệ");
        return;
    }
    String ngayKTString = txt_NgayKT.getText();
    Date ngayKT = null;

    try {
        ngayKT = dateFormat.parse(ngayKTString);
    } catch (ParseException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Ngày kết thúc không hợp lệ");
        return;
    }
    String dieuKienApDung = txt_dieuKien.getText();

    if (maVoucher.isEmpty() || moTa.isEmpty() || dieuKienApDung.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
        return;
    }

    int id = (int) tbl_khuyenMai.getValueAt(tbl_khuyenMai.getSelectedRow(), 0); 
    Voucher vc = new Voucher(id, maVoucher, moTa, giamGia, giamGiaToiDa, hinhThucGiamGia, ngayBD, ngayKT, dieuKienApDung);

    if (repo.UpdateVoucher(vc)) {
        loadDataTable();
        JOptionPane.showMessageDialog(this, "Cập nhật thành công");

    } else {
        JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
        loadDataTable();
    }
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
    int selectedRow = tbl_khuyenMai.getSelectedRow();
    
    if (selectedRow != -1) {  // Kiểm tra xem có hàng nào được chọn không
        int id = (int) tbl_khuyenMai.getValueAt(selectedRow, 0); 
        Voucher vc = new Voucher(id);

        // Thực hiện xóa
//        if (repo.DeleteVoucher(vc)) {
//            JOptionPane.showMessageDialog(this, "Xóa thành công");
//        } else {
//            JOptionPane.showMessageDialog(this, "Xóa thất bại");
//        }
//    } else {
//        // Thông báo người dùng nếu không chọn hàng nào
//        JOptionPane.showMessageDialog(this, "Vui lòng chọn một khuyến mãi để xóa");
//    }
            repo.DeleteVoucher(vc);
            System.out.println("id:" + id);
        }

    loadDataTable();  // Tải lại bảng để phản ánh sự thay đổi
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_lammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lammoiActionPerformed

    }//GEN-LAST:event_btn_lammoiActionPerformed

    private void txt_giamgiaTDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_giamgiaTDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_giamgiaTDActionPerformed

    private void txt_dieuKienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dieuKienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dieuKienActionPerformed

    private void tbl_khuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_khuyenMaiMouseClicked
        int index = tbl_khuyenMai.getSelectedRow();
        fillData(index);
    }//GEN-LAST:event_tbl_khuyenMaiMouseClicked

    private void txt_MaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MaKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MaKMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btn_lammoi;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_Gio;
    private javax.swing.JLabel lbl_MaNV;
    private javax.swing.JLabel lbl_Ngay;
    private javax.swing.JRadioButton rdoHinhThuc1;
    private javax.swing.JRadioButton rdoHinhThuc2;
    private javax.swing.JTable tbl_khuyenMai;
    private javax.swing.JTextField txt_MaKM;
    private javax.swing.JTextField txt_NgayKT;
    private javax.swing.JTextField txt_dieuKien;
    private javax.swing.JTextField txt_giamgiaTD;
    private javax.swing.JTextArea txt_moTa;
    private javax.swing.JTextField txt_mucGiamGia;
    private javax.swing.JTextField txt_ngayBD;
    // End of variables declaration//GEN-END:variables
}
