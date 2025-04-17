/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.raven.model.Model_SPChiTiet;
import com.raven.model.Model_SanPham;
import com.raven.repository.Repository_SPChiTiet;
import com.raven.repository.reponsitory_SanPham;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class Form_SP extends javax.swing.JPanel {

    DefaultTableModel model;
    DefaultTableModel model_SPChitiet;
    private reponsitory_SanPham rp = new reponsitory_SanPham();
    private Repository_SPChiTiet rp_spct = new Repository_SPChiTiet();

    /**
     * Creates new form Form_SP
     */
    public Form_SP() {
        initComponents();
        loadKichThuoc();
        loadLoaiSP();
        fillToTable(rp.gettAll_Sp());
        fillToTable1(rp_spct.getData());
    }

    void fillToTable(ArrayList<Model_SanPham> ds) {
        model = (DefaultTableModel) tbl_sp.getModel();
        model.setRowCount(0);
        for (Model_SanPham d : ds) {
            model.addRow((Object[]) d.toDataSP());
        }
    }

    void fillToTable1(ArrayList<Model_SPChiTiet> ds) {
        model_SPChitiet = (DefaultTableModel) tbl_CTSP.getModel();
        model_SPChitiet.setRowCount(0);
        for (Model_SPChiTiet d : ds) {
            model_SPChitiet.addRow((Object[]) d.toDataRow());
        }
    }

    private void loadKichThuoc() {
        String[] kichThuoc = {"XS", "S", "M", "L", "XL", "XXL"};
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(kichThuoc);
        cbo_kichthuoc.setModel(model);
    }

    private void loadLoaiSP() {
        String[] loaiSanPham = {"Áo thun", "Quần jeans", "Váy", "Áo sơ mi", "Quần short"};
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(loaiSanPham);
        cbo_loc.setModel(model);
    }

    public void showD(int index) {
        Model_SPChiTiet spct = rp_spct.getData().get(index);
        txt_masp.setText(spct.getMaSPChiTiet());
        txt_tensp.setText(spct.getTenSP());
        txt_giasp.setText(String.valueOf(spct.getGiaBan()));
        txt_soluongton.setText(String.valueOf(spct.getSoLuongTon()));
        txt_loaisp.setText(String.valueOf(spct.getTenLoaiSP()));
        txt_mau.setText(String.valueOf(spct.getTenMau()));  // Đã đóng ngoặc đúng chỗ
        cbo_kichthuoc.setSelectedItem(spct.getTenKichThuoc());
        // Cập nhật trạng thái
        rdo_con.setSelected(spct.isTrangThai());  // Sửa 'sp' thành 'spct'
        rdo_het.setSelected(!spct.isTrangThai());  // Sửa 'sp' thành 'spct'

    }

    private void loadData(ArrayList<Model_SPChiTiet> data) {
        fillToTable1(data);  // Hoặc tùy chỉnh lại theo cách bạn muốn làm mới bảng
    }

    public void them() throws SQLException {
        // Kiểm tra xem có trường nào trống không
        if (!checkEmpty()) {
            return; // Nếu có trường trống thì không thực hiện thêm
        }

        // Lưu sản phẩm chi tiết mới
        boolean x = rp_spct.save(getForm());
        if (x) {
            JOptionPane.showMessageDialog(this, "Đã thêm thành công");
            loadData(rp_spct.getData());
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }

    private boolean checkEmpty() {
        // Kiểm tra rỗng
        if (txt_masp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm");
            return false;
        }
        if (txt_tensp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm");
            return false;
        }
        if (txt_giasp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá sản phẩm");
            return false;
        }
        if (txt_soluongton.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng tồn");
            return false;
        }

        // Kiểm tra định dạng số và giá trị
        try {
            double gia = Double.parseDouble(txt_giasp.getText().trim());
            if (gia <= 0) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm phải lớn hơn 0");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá sản phẩm phải là số");
            return false;
        }

        try {
            int soLuong = Integer.parseInt(txt_soluongton.getText().trim());
            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn phải >= 0");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn phải là số nguyên");
            return false;
        }

        return true;
    }

    private Model_SPChiTiet getForm() {
        String ma = txt_masp.getText().trim();
        String ten = txt_tensp.getText().trim();
        String mau = txt_mau.getText().trim();
        String kichThuoc = (String) cbo_kichthuoc.getSelectedItem();
        String loaiSP = txt_loaisp.getText().trim();

        // Kiểm tra rỗng
        if (ma.isEmpty() || ten.isEmpty() || mau.isEmpty() || loaiSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
            return null;
        }

        double gia;
        int soLuong;

        try {
            gia = Double.parseDouble(txt_giasp.getText().trim());
            if (gia <= 0) {
                JOptionPane.showMessageDialog(this, "Giá bán phải > 0");
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá bán không hợp lệ");
            return null;
        }

        try {
            soLuong = Integer.parseInt(txt_soluongton.getText().trim());
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn phải > 0");
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn không hợp lệ");
            return null;
        }

        boolean trangThai = rdo_con.isSelected();

        return new Model_SPChiTiet(ma, ten, mau, kichThuoc, loaiSP, (int) gia, soLuong, trangThai);
    }

    public void ngaygio() {
        LocalDateTime now = LocalDateTime.now();
        // Định dạng ngày
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = now.format(dateFormatter);

        // Đặt ngày vào JLabel
        lbl_Ngay.setText(formattedDate);

        // Lấy giờ hiện tại
        LocalTime now1 = LocalTime.now();
        // Định dạng giờ
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = now1.format(timeFormatter);

        // Đặt giờ vào JLabel
        lbl_Gio.setText(formattedTime);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_sp = new javax.swing.JTable();
        btn_TimKiem = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_tensp_TimKiem = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txt_masp = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbo_kichthuoc = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txt_mau = new javax.swing.JTextField();
        txt_loaisp = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        rdo_con = new javax.swing.JRadioButton();
        rdo_het = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        txt_tensp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_giasp = new javax.swing.JTextField();
        btn_themSP = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_soluongton = new javax.swing.JTextField();
        Panel_duoi = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_CTSP = new javax.swing.JTable();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        cbo_loc = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        btn_loc = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lbl_MaNV = new javax.swing.JLabel();
        lbl_Gio = new javax.swing.JLabel();
        lbl_Ngay = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1100, 780));
        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1000, 720));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(1000, 595));

        tbl_sp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Mo ta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_sp);

        btn_TimKiem.setBackground(new java.awt.Color(255, 102, 102));
        btn_TimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_TimKiem.setText("Tìm Kiếm");
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tên SP:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tensp_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 605, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1134, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_tensp_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1156, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(1075, 685));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel7.setPreferredSize(new java.awt.Dimension(1000, 200));

        txt_masp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maspActionPerformed(evt);
            }
        });

        jLabel10.setText("Mã:");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(1000, 121));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Màu sắc");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Kích thước");

        cbo_kichthuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_kichthuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_kichthuocActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Loại sản phẩm");

        txt_mau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mauActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Trạng thái");

        buttonGroup1.add(rdo_con);
        rdo_con.setText("Còn hàng");
        rdo_con.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_conActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_het);
        rdo_het.setText("Hết hàng");
        rdo_het.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_hetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdo_con, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_mau, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbo_kichthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_loaisp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(rdo_het)))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(cbo_kichthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_mau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_loaisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(rdo_con)
                    .addComponent(rdo_het))
                .addGap(35, 35, 35))
        );

        jLabel13.setText("Tên:");

        jLabel5.setText("Giá");

        btn_themSP.setText("Thêm");
        btn_themSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themSPActionPerformed(evt);
            }
        });

        jLabel9.setText("Số lượng tồn");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1025, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 143, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_masp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(txt_tensp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_giasp)
                        .addGap(144, 144, 144))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_soluongton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(btn_themSP)
                .addGap(134, 134, 134))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txt_giasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_themSP))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txt_masp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(txt_tensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_soluongton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel2.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        Panel_duoi.setPreferredSize(new java.awt.Dimension(1000, 471));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(574, 300));

        tbl_CTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Ma san pham", "Ten san pham", "Mau sac", "Kich thuoc", "Loai san pham", "Gia ban", "So luong ton", "Trang thai"
            }
        ));
        tbl_CTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_CTSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_CTSP);

        btn_update.setBackground(new java.awt.Color(102, 255, 255));
        btn_update.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_update.setText("SỬA");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(102, 255, 255));
        btn_delete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_delete.setText("XÓA");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_update)
                .addGap(18, 18, 18)
                .addComponent(btn_delete)
                .addGap(182, 182, 182))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1063, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 89, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_update)
                    .addComponent(btn_delete))
                .addContainerGap())
        );

        cbo_loc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Loại sản phẩm");

        btn_loc.setText("Lọc");
        btn_loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_locActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_duoiLayout = new javax.swing.GroupLayout(Panel_duoi);
        Panel_duoi.setLayout(Panel_duoiLayout);
        Panel_duoiLayout.setHorizontalGroup(
            Panel_duoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_duoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1162, Short.MAX_VALUE))
            .addGroup(Panel_duoiLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_loc, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_loc)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Panel_duoiLayout.setVerticalGroup(
            Panel_duoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_duoiLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(Panel_duoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_loc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(btn_loc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );

        jPanel2.add(Panel_duoi, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Sản Phẩm Chi Tiết", jPanel2);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jPanel9.setBackground(new java.awt.Color(255, 204, 204));
        jPanel9.setForeground(new java.awt.Color(255, 204, 204));
        jPanel9.setPreferredSize(new java.awt.Dimension(1000, 60));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User.png"))); // NOI18N

        lbl_MaNV.setText("admin");

        lbl_Gio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_Gio.setText("19:01:02");

        lbl_Ngay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_Ngay.setText("19:01:02");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lbl_Gio)
                .addGap(26, 26, 26)
                .addComponent(lbl_Ngay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 894, Short.MAX_VALUE)
                .addComponent(lbl_MaNV)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addGap(36, 36, 36))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_MaNV)
                        .addComponent(lbl_Gio)
                        .addComponent(lbl_Ngay))
                    .addComponent(jLabel15))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        add(jPanel9, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_CTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_CTSPMouseClicked
        // TODO add your handling code here:
        // Lấy chỉ số dòng được chọn
        int i = tbl_CTSP.getSelectedRow();

        // Kiểm tra xem có dòng nào được chọn không
        if (i >= 0) {
            // Lấy dữ liệu từ các cột trong dòng đã chọn và hiển thị lên các trường nhập liệu
            txt_masp.setText(tbl_CTSP.getValueAt(i, 1).toString());  // Giả sử cột 0 là mã sản phẩm
            txt_tensp.setText(tbl_CTSP.getValueAt(i, 2).toString());  // Giả sử cột 1 là tên sản phẩm
            txt_loaisp.setText(tbl_CTSP.getValueAt(i, 5).toString()); // Giả sử cột 2 là loại sản phẩm
            cbo_kichthuoc.setSelectedItem(tbl_CTSP.getValueAt(i, 4).toString());  // Giả sử cột 3 là kích thước
            txt_mau.setText(tbl_CTSP.getValueAt(i, 3).toString());  // Giả sử cột 4 là màu sắc
            txt_soluongton.setText(tbl_CTSP.getValueAt(i, 6).toString()); // Giả sử cột 5 là số lượng tồn
            txt_giasp.setText(tbl_CTSP.getValueAt(i, 7).toString());  // Giả sử cột 6 là giá bán

            // Nếu cột trạng thái (ví dụ cột 7) có giá trị "Còn", chọn rdo_con, nếu không chọn rdo_het
            if ("con".equals(tbl_CTSP.getValueAt(i, 8).toString())) {
                rdo_con.setSelected(true);
            } else {
                rdo_het.setSelected(true);
            }
        }


    }//GEN-LAST:event_tbl_CTSPMouseClicked

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        Model_SPChiTiet sp = getForm();
        if (sp == null) {
            return;
        }

        if (rp_spct.update(sp)) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            fillToTable1(rp_spct.getData());
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        }


    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        // TODO add your handling code here:
        String keyword = txt_tensp_TimKiem.getText().trim();

        // Kiểm tra nếu người dùng chưa nhập gì thì không làm gì
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập từ khóa tìm kiếm!");
            return;
        }

        // Gọi phương thức tìm kiếm từ repository
        ArrayList<Model_SanPham> ketQua = new reponsitory_SanPham().search_SanPham(keyword);

        // Nếu có kết quả, hiển thị lên bảng (giả sử bạn có một phương thức fillToTable để hiển thị)
        if (ketQua != null && !ketQua.isEmpty()) {
            fillToTable(ketQua);  // Gọi phương thức fillToTable để đổ dữ liệu vào bảng
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm phù hợp!");
        }
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void txt_mauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mauActionPerformed

    private void rdo_conActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_conActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_conActionPerformed

    private void txt_maspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maspActionPerformed

    private void cbo_kichthuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_kichthuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_kichthuocActionPerformed

    private void rdo_hetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_hetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_hetActionPerformed

    private void btn_themSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themSPActionPerformed
        // TODO add your handling code here:

        Model_SPChiTiet sp = getForm();
        if (sp == null) {
            return; // Nếu dữ liệu không hợp lệ → stop luôn
        }

        try {
            boolean result = new Repository_SPChiTiet().save(sp);
            if (result) {
                JOptionPane.showMessageDialog(this, "Thêm mới thành công!");
                fillToTable1(rp_spct.getData());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm mới thất bại!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm: " + ex.getMessage());
        }

    }//GEN-LAST:event_btn_themSPActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        // Lấy mã sản phẩm chi tiết từ TextField
        String maSPChiTiet = txt_masp.getText().trim();  // Loại bỏ khoảng trắng dư thừa

        // Kiểm tra nếu mã sản phẩm chi tiết trống
        if (maSPChiTiet.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mã sản phẩm chi tiết không thể trống!");
            return;
        }

        // Gọi phương thức delete của Repository_SPChiTiet để xóa
        Repository_SPChiTiet repo = new Repository_SPChiTiet();
        boolean result = repo.delete(maSPChiTiet);

        // Thông báo kết quả
        if (result) {
            JOptionPane.showMessageDialog(null, "Xóa thành công!");
            // Làm mới bảng dữ liệu sau khi xóa
            fillToTable1(rp_spct.getData());
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thất bại! Kiểm tra lại mã sản phẩm chi tiết.");
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_locActionPerformed
        // TODO add your handling code here:
        String tenLoaiSP = cbo_loc.getSelectedItem().toString();  // Lấy loại sản phẩm được chọn
        Repository_SPChiTiet repo = new Repository_SPChiTiet();
        ArrayList<Model_SPChiTiet> list = repo.filterByLoaiSanPham(tenLoaiSP);  // Gọi hàm lọc

        DefaultTableModel model = (DefaultTableModel) tbl_CTSP.getModel();
        model.setRowCount(0);  // Xóa dữ liệu cũ

        if (list != null && !list.isEmpty()) {
            for (Model_SPChiTiet sp : list) {
                model.addRow(sp.toDataRow());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm thuộc loại: " + tenLoaiSP);
        }
    }//GEN-LAST:event_btn_locActionPerformed
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_duoi;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_loc;
    private javax.swing.JButton btn_themSP;
    private javax.swing.JButton btn_update;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbo_kichthuoc;
    private javax.swing.JComboBox<String> cbo_loc;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_Gio;
    private javax.swing.JLabel lbl_MaNV;
    private javax.swing.JLabel lbl_Ngay;
    private javax.swing.JRadioButton rdo_con;
    private javax.swing.JRadioButton rdo_het;
    private javax.swing.JTable tbl_CTSP;
    private javax.swing.JTable tbl_sp;
    private javax.swing.JTextField txt_giasp;
    private javax.swing.JTextField txt_loaisp;
    private javax.swing.JTextField txt_masp;
    private javax.swing.JTextField txt_mau;
    private javax.swing.JTextField txt_soluongton;
    private javax.swing.JTextField txt_tensp;
    private javax.swing.JTextField txt_tensp_TimKiem;
    // End of variables declaration//GEN-END:variables
}
