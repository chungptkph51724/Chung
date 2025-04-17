/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.raven.model.Model_DoanhThu;
import com.raven.repository.repository_DoanhThu;
import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;

import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author admin
 */
public class Form_BC extends javax.swing.JPanel {

    private repository_DoanhThu rpdt = new repository_DoanhThu();
    DefaultTableModel model_sanpham_DT;

    /**
     * Creates new form Form_SP
     */
    public Form_BC() {
        initComponents();
        showBarChart("2025");
        showLineChart();// Hiển thị biểu đồ cột
        cbo_ngayThang.setModel(new DefaultComboBoxModel<>(new String[]{"Năm", "Tháng"}));

        jcalen_Thang.setVisible(false);

        Jcalender_Nam.setVisible(true);
        click_Cbo_ngayThang1();
        fillToTable_DT_SP(rpdt.getSOLuongNam(2025, "Tang"));
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        LocalDate today = LocalDate.now();
        int year = today.getYear();

// Tổng doanh thu năm

        double tongNam = rpdt.getDoanhThuTrongNam(2025);
        lbl_tongDoanhthu.setText(vndFormat.format(tongNam));

// Doanh thu trong ngày
        double ngay = rpdt.getDoanhThuTrongNgay(today);
        lbl_tienTrongNgay.setText(vndFormat.format(ngay));

// Tổng sản phẩm đã bán trong năm
        int soLuongNam = rpdt.getTongSoLuongBanTrongNam(year);
        lbl_tongDaban.setText(String.valueOf(soLuongNam));

// Tổng sản phẩm bán trong ngày
        int spTrongNgay = rpdt.getSoLuongBanTrongNgay(today);
        lbl_SPbantrongNgay.setText(String.valueOf(spTrongNgay));

    }

    void fillToTable_DT_SP(ArrayList<Model_DoanhThu> ds) {
        model_sanpham_DT = (DefaultTableModel) tbl_SanPhamBC.getModel();
        model_sanpham_DT.setRowCount(0);
        for (Model_DoanhThu d : ds) {
            model_sanpham_DT.addRow((Object[]) d.todata_SanPham_DT());
        }

    }

    public void click_Cbo_ngayThang1() {
        cbo_ngayThang.addActionListener(e -> {
            String selected = (String) cbo_ngayThang.getSelectedItem();
            if (selected != null && selected.equals("Tháng")) {

                jcalen_Thang.setVisible(true);
                Jcalender_Nam.setVisible(true);
            } else if (selected != null && selected.equals("Năm")) {
                jcalen_Thang.setVisible(false);

                Jcalender_Nam.setVisible(true);
            }
        });
    }

// Gọi phương thức
    public void showLineChart() {
        //create dataset for the graph
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(200, "VND", "january");
        dataset.setValue(150, "VND", "february");
        dataset.setValue(18, "VND", "march");
        dataset.setValue(100, "VND", "april");
        dataset.setValue(80, "VND", "may");
        dataset.setValue(250, "VND", "june");

        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("Doanh thu theo tháng", "Tháng", "VND",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        //create plot object
        CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);

        //create render object to change the moficy the line properties like color
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);

        //create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        panel_DT_Thang.removeAll();
        panel_DT_Thang.add(lineChartPanel, BorderLayout.CENTER);
        panel_DT_Thang.validate();
    }

    private void showLineChart(int year, int month) {
        // Tạo dataset cho biểu đồ
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Lấy dữ liệu từ cơ sở dữ liệu
        ArrayList<Model_DoanhThu> ds = rpdt.getDataByDay(year, month);

        // Đảm bảo rằng tất cả các ngày (1-31) đều có dữ liệu
        for (int day = 1; day <= 31; day++) {
            // Mặc định số lượng là 0
            int soLuong = 0;

            // Kiểm tra xem dữ liệu cho ngày này đã có chưa
            for (Model_DoanhThu data : ds) {
                int dayFromData = Integer.parseInt(data.getThang()); // Chuyển ngày từ String sang int
                if (dayFromData == day) {
                    soLuong = data.getSoLuong(); // Cập nhật nếu có dữ liệu cho ngày này
                    break; // Nếu tìm thấy thì không cần kiểm tra thêm
                }
            }

            dataset.addValue(soLuong, "Số lượng", "Ngày " + day);
        }

        // Tạo biểu đồ đường
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Số lượng sản phẩm bán theo ngày trong tháng " + month + " năm " + year,
                "Ngày", // Trục X
                "Số lượng", // Trục Y
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        // Xoay nhãn trục X để tránh chồng lấn
        CategoryPlot plot = lineChart.getCategoryPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(45.0f)); // Xoay nhãn 45 độ

        // Hiển thị biểu đồ
        ChartPanel chartPanel = new ChartPanel(lineChart);
        panel_topSPBanChay.removeAll();
        panel_topSPBanChay.setLayout(new BorderLayout());
        panel_topSPBanChay.add(chartPanel, BorderLayout.CENTER);
        panel_topSPBanChay.revalidate();
        panel_topSPBanChay.repaint();
    }

    private void showBarChart(String nam) {
        // Lấy dữ liệu từ cơ sở dữ liệu
        ArrayList<Model_DoanhThu> salesData = rpdt.getAll(nam);

        // Tạo dataset cho biểu đồ
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Đảm bảo rằng tất cả các tháng (1-12) đều có dữ liệu
        for (int month = 1; month <= 12; month++) {
            // Mặc định số lượng là 0
            int soLuong = 0;

            // Kiểm tra xem dữ liệu cho tháng này đã có chưa
            for (Model_DoanhThu dt : salesData) {
                int monthFromData = Integer.parseInt(dt.getThang()); // Chuyển tháng từ String sang int
                if (monthFromData == month) {
                    soLuong = dt.getSoLuong(); // Cập nhật nếu có dữ liệu cho tháng này
                    break; // Nếu tìm thấy thì không cần kiểm tra thêm
                }
            }

            // Thêm dữ liệu vào dataset (Tháng 1-12)
            dataset.addValue(soLuong, "Số lượng", "Tháng " + month);
        }

        // Tạo biểu đồ cột
        JFreeChart barChart = ChartFactory.createBarChart(
                "Số lượng bán của sản phẩm theo tháng trong năm " + nam, // Tiêu đề biểu đồ
                "Tháng", // Nhãn trục X
                "Số lượng", // Nhãn trục Y
                dataset, // Dữ liệu
                PlotOrientation.VERTICAL, // Hướng biểu đồ
                false, // Hiển thị chú thích
                true, // Hiển thị công cụ
                false // Không tạo URL
        );

        // Tạo Panel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(barChart);

        // Xóa các thành phần cũ nếu có
        panel_topSPBanChay.removeAll();

        // Thêm biểu đồ vào panel
        panel_topSPBanChay.setLayout(new BorderLayout());
        panel_topSPBanChay.add(chartPanel, BorderLayout.CENTER);

        // Làm mới giao diện
        panel_topSPBanChay.revalidate();
        panel_topSPBanChay.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lbl_MaNV = new javax.swing.JLabel();
        lbl_Gio = new javax.swing.JLabel();
        lbl_Ngay = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Panel_SPBanChay = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panel_topSPBanChay = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbo_sapxep = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_SanPhamBC = new javax.swing.JTable();
        jcalen_Thang = new com.toedter.calendar.JMonthChooser();
        cbo_ngayThang = new javax.swing.JComboBox<>();
        Jcalender_Nam = new com.toedter.calendar.JYearChooser();
        btn_Loc = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        Panel_h_l = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_tongDaban = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_SPbantrongNgay = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lbl_tongDoanhthu = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbl_tienTrongNgay = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        panel_DT_Thang = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(1100, 60));
        jPanel2.setLayout(new java.awt.BorderLayout());

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 823, Short.MAX_VALUE)
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

        jPanel2.add(jPanel9, java.awt.BorderLayout.PAGE_START);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        Panel_SPBanChay.setLayout(new java.awt.BorderLayout());

        panel_topSPBanChay.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel_topSPBanChay.setPreferredSize(new java.awt.Dimension(500, 300));
        panel_topSPBanChay.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_topSPBanChay, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_topSPBanChay, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
        );

        Panel_SPBanChay.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setPreferredSize(new java.awt.Dimension(630, 220));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Thống Kê Số Lượng Sản Phẩm ");

        jLabel2.setText("Thời gian:");

        cbo_sapxep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tăng dần", "Giảm dần", " " }));
        cbo_sapxep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_sapxepActionPerformed(evt);
            }
        });

        jLabel3.setText("Sắp xếp:");

        tbl_SanPhamBC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Đã bán"
            }
        ));
        jScrollPane1.setViewportView(tbl_SanPhamBC);

        cbo_ngayThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_ngayThangActionPerformed(evt);
            }
        });

        btn_Loc.setText("Lọc");
        btn_Loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132)
                        .addComponent(jLabel2)
                        .addGap(38, 38, 38)
                        .addComponent(cbo_ngayThang, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbo_sapxep, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Loc)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
                .addComponent(jcalen_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Jcalender_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(cbo_ngayThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jcalen_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Jcalender_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbo_sapxep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(btn_Loc))))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Panel_SPBanChay.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jTabbedPane1.addTab("Số lượng", Panel_SPBanChay);

        jPanel5.setPreferredSize(new java.awt.Dimension(800, 616));
        jPanel5.setLayout(new java.awt.BorderLayout(10, 10));

        jPanel6.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        jPanel7.setBackground(new java.awt.Color(153, 102, 0));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Đã bán");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Số Lượng:");

        lbl_tongDaban.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_tongDaban.setForeground(new java.awt.Color(255, 0, 102));
        lbl_tongDaban.setText("40");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Hôm nay:");

        lbl_SPbantrongNgay.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_SPbantrongNgay.setForeground(new java.awt.Color(255, 255, 255));
        lbl_SPbantrongNgay.setText("12 sản phẩm");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_SPbantrongNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addComponent(lbl_tongDaban)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_tongDaban)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel5)))
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbl_SPbantrongNgay))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(153, 102, 0));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Doanh Thu");

        lbl_tongDoanhthu.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lbl_tongDoanhthu.setForeground(new java.awt.Color(255, 0, 102));
        lbl_tongDoanhthu.setText("157.090.199");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Hôm nay:");

        lbl_tienTrongNgay.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tienTrongNgay.setForeground(new java.awt.Color(255, 255, 255));
        lbl_tienTrongNgay.setText("7.000.000");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Coins.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(lbl_tongDoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lbl_tienTrongNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_tongDoanhthu)
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lbl_tienTrongNgay))
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout Panel_h_lLayout = new javax.swing.GroupLayout(Panel_h_l);
        Panel_h_l.setLayout(Panel_h_lLayout);
        Panel_h_lLayout.setHorizontalGroup(
            Panel_h_lLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_h_lLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Panel_h_lLayout.setVerticalGroup(
            Panel_h_lLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_h_lLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_h_lLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, Short.MAX_VALUE)))
        );

        jPanel6.add(Panel_h_l);

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        panel_DT_Thang.setPreferredSize(new java.awt.Dimension(528, 500));
        panel_DT_Thang.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_DT_Thang, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(panel_DT_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel11, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 631, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Doanh Thu", jPanel4);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
void click_Cbo_ngayThang() {
        cbo_ngayThang.addActionListener(e -> {
            String selected = (String) cbo_ngayThang.getSelectedItem();
            if (selected != null && !selected.equals("Tháng")) {
                jcalen_Thang.setVisible(true); // Ẩn JCalendar khi chọn tháng
                Jcalender_Nam.setVisible(true); // Ẩn JCalendar khi chọn tháng
            } else {
                jcalen_Thang.setVisible(true);
                Jcalender_Nam.setVisible(false);// Hiện JCalendar khi chọn "Chọn ngày cụ thể"
            }
        });

//        // Thêm sự kiện cho JCalendar
//        calendar.addPropertyChangeListener("calendar", evt -> {
//            comboBox.setVisible(false); // Ẩn ComboBox khi chọn ngày từ JCalendar
//        });
    }
    private void cbo_ngayThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_ngayThangActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbo_ngayThangActionPerformed

    private void btn_LocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LocActionPerformed
        String loc = "";
        if (cbo_sapxep.getSelectedItem().equals("Tăng dần")) {
            loc = "Tang";
        } else {
            loc = "Giam";
        }
        int selectedDate = Jcalender_Nam.getYear();
        int month = jcalen_Thang.getMonth() + 1;
        if (cbo_ngayThang.getSelectedItem().equals("Tháng")) {
            showLineChart(selectedDate, month);
            fillToTable_DT_SP(rpdt.getSOLuongThang(selectedDate, month, loc));
        } else {
            showBarChart(String.valueOf(selectedDate));
            fillToTable_DT_SP(rpdt.getSOLuongNam(selectedDate, loc));
            // Truyền năm vào phương thức hiển thị biểu đồ
        }

    }//GEN-LAST:event_btn_LocActionPerformed

    private void cbo_sapxepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_sapxepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_sapxepActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JYearChooser Jcalender_Nam;
    private javax.swing.JPanel Panel_SPBanChay;
    private javax.swing.JPanel Panel_h_l;
    private javax.swing.JButton btn_Loc;
    private javax.swing.JComboBox<String> cbo_ngayThang;
    private javax.swing.JComboBox<String> cbo_sapxep;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JMonthChooser jcalen_Thang;
    private javax.swing.JLabel lbl_Gio;
    private javax.swing.JLabel lbl_MaNV;
    private javax.swing.JLabel lbl_Ngay;
    private javax.swing.JLabel lbl_SPbantrongNgay;
    private javax.swing.JLabel lbl_tienTrongNgay;
    private javax.swing.JLabel lbl_tongDaban;
    private javax.swing.JLabel lbl_tongDoanhthu;
    private javax.swing.JPanel panel_DT_Thang;
    private javax.swing.JPanel panel_topSPBanChay;
    private javax.swing.JTable tbl_SanPhamBC;
    // End of variables declaration//GEN-END:variables

}
