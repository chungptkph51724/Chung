package com.raven.repository;

import com.raven.model.Model_DoanhThu;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class repository_DoanhThu {

    // Phương thức đóng tài nguyên
    private void closeResources(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức lấy doanh thu theo năm
    public ArrayList<Model_DoanhThu> getAll(String nam) {
        ArrayList<Model_DoanhThu> ds = new ArrayList<>();
        // Câu truy vấn sửa lại, đảm bảo sử dụng tên bảng và cột đúng
        String sql = "SELECT FORMAT(HoaDon.ngayThanhToan, 'MM') AS Thang, SUM(ChiTietHoaDon.soLuong) AS TongSoLuong\n" +
"                   FROM ChiTietHoaDon \n" +
"                   JOIN HoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon\n" +
"                   WHERE YEAR(HoaDon.ngayThanhToan) = ? \n" +
"                   GROUP BY FORMAT(HoaDon.ngayThanhToan, 'MM') \n" +
"                   ORDER BY FORMAT(HoaDon.ngayThanhToan, 'MM');";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nam);  // Đảm bảo truyền năm dưới dạng String
            rs = ps.executeQuery();
            while (rs.next()) {
                String thang = rs.getString("Thang");
                int soLuong = rs.getInt("TongSoLuong");
                Model_DoanhThu dt = new Model_DoanhThu(thang, soLuong);
                ds.add(dt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            closeResources(con, ps, rs);
        }
        return ds;
    }

    // Phương thức lấy doanh thu theo ngày trong tháng
    public ArrayList<Model_DoanhThu> getDataByDay(int year, int month) {
        ArrayList<Model_DoanhThu> ds = new ArrayList<>();
        String sql = "SELECT FORMAT(HoaDon.ngayThanhToan, 'dd') AS Ngay, SUM(ChiTietHoaDon.soLuong) AS TongSoLuong "
                   + "FROM ChiTietHoaDon "
                   + "JOIN HoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon "
                   + "WHERE YEAR(HoaDon.ngayThanhToan) = ? AND MONTH(HoaDon.ngayThanhToan) = ? "
                   + "GROUP BY FORMAT(HoaDon.ngayThanhToan, 'dd') "
                   + "ORDER BY FORMAT(HoaDon.ngayThanhToan, 'dd');";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, year); // Truyền year dưới dạng số
            ps.setInt(2, month); // Truyền month dưới dạng số
            rs = ps.executeQuery();
            while (rs.next()) {
                String ngay = rs.getString("Ngay");
                int soLuong = rs.getInt("TongSoLuong");
                ds.add(new Model_DoanhThu(ngay, soLuong));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            closeResources(con, ps, rs);
        }
        return ds;
    }

    // Phương thức lấy doanh thu theo sản phẩm trong năm
    public ArrayList<Model_DoanhThu> getSOLuongNam(int year, String tang_Giam) {
        ArrayList<Model_DoanhThu> dataList = new ArrayList<>();
        // Câu truy vấn sửa lại, đảm bảo sử dụng tên bảng và cột đúng
        String sql = "SELECT SanPham.maSP, SanPham.tenSP, SUM(ChiTietHoaDon.soLuong) AS TongSoLuong "
                   + "FROM ChiTietHoaDon "
                   + "JOIN HoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon "
                   + "JOIN SanPham ON SanPham.maSP = ChiTietHoaDon.maSP "
                   + "WHERE YEAR(HoaDon.ngayThanhToan) = ? "
                   + "GROUP BY SanPham.maSP, SanPham.tenSP "
                   + "ORDER BY TongSoLuong " + (tang_Giam.equals("Tang") ? "ASC" : "DESC");

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, year);  // Truyền year dưới dạng số
            rs = ps.executeQuery();
            while (rs.next()) {
                String maSanPham = rs.getString("maSP");
                String tenSanPham = rs.getString("tenSP");
                int tongSoLuong = rs.getInt("TongSoLuong");

                Model_DoanhThu data = new Model_DoanhThu(tongSoLuong, maSanPham, tenSanPham);
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(con, ps, rs);
        }
        return dataList;
    }

    // Phương thức lấy doanh thu theo sản phẩm trong tháng
    public ArrayList<Model_DoanhThu> getSOLuongThang(int year, int month, String loc) {
        ArrayList<Model_DoanhThu> dataList = new ArrayList<>();
        // Câu truy vấn sửa lại, đảm bảo sử dụng tên bảng và cột đúng
        String sql = "SELECT SanPham.maSP, SanPham.tenSP, SUM(ChiTietHoaDon.soLuong) AS TongSoLuong \n" +
             "FROM ChiTietHoaDon\n" +
             "JOIN HoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon\n" +
             "JOIN SanPham ON SanPham.maSP = ChiTietHoaDon.maSP\n" +
             "WHERE YEAR(HoaDon.ngayThanhToan) = ? AND MONTH(HoaDon.ngayThanhToan) = ?\n" +
             "GROUP BY SanPham.maSP, SanPham.tenSP\n" +
             "ORDER BY TongSoLuong " + (loc.equals("Tang") ? "ASC" : "DESC");


        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, year);  // Truyền year dưới dạng số
            ps.setInt(2, month);  // Truyền month dưới dạng số
            rs = ps.executeQuery();
            while (rs.next()) {
                String maSanPham = rs.getString("maSP");
                String tenSanPham = rs.getString("tenSP");
                int tongSoLuong = rs.getInt("TongSoLuong");

                Model_DoanhThu data = new Model_DoanhThu(tongSoLuong, maSanPham, tenSanPham);
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(con, ps, rs);
        }
        return dataList;
    }
    public double getDoanhThuTrongNgay(LocalDate ngay) {
    double tong = 0;
    String sql = "SELECT SUM(cthd.soLuong * spct.giaBan) AS TongDoanhThu " +
                 "FROM ChiTietHoaDon cthd " +
                 "JOIN HoaDon hd ON hd.maHoaDon = cthd.maHoaDon " +
                 "JOIN SPChiTiet spct ON spct.maSPChiTiet = cthd.maSP " +
                 "WHERE CAST(hd.ngayThanhToan AS DATE) = ?";
    try (Connection con = DBConnect.DBConnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setDate(1, Date.valueOf(ngay));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            tong = rs.getDouble("TongDoanhThu");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return tong;
}

public double getDoanhThuTrongNam(int nam) {
    double tong = 0;
    String sql = "SELECT SUM(cthd.soLuong * spct.giaBan) AS TongDoanhThu " +
                 "FROM ChiTietHoaDon cthd " +
                 "JOIN HoaDon hd ON hd.id = cthd.id " +
                 "JOIN SPChiTiet spct ON spct.id = cthd.id " +
                 "WHERE YEAR(hd.ngayThanhToan) = ?";

    try (Connection con = DBConnect.DBConnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, nam);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            tong = rs.getDouble("TongDoanhThu");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return tong;
}

public int getSoLuongBanTrongNgay(LocalDate ngay) {
    int tong = 0;
    String sql = "SELECT SUM(cthd.soLuong) AS SoLuong " +
                 "FROM ChiTietHoaDon cthd " +
                 "JOIN HoaDon hd ON hd.maHoaDon = cthd.maHoaDon " +
                 "WHERE CAST(hd.ngayThanhToan AS DATE) = ?";
    try (Connection con = DBConnect.DBConnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setDate(1, Date.valueOf(ngay));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            tong = rs.getInt("SoLuong");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return tong;
}

public int getTongSoLuongBanTrongNam(int nam) {
    int tong = 0;
    String sql = "SELECT SUM(cthd.soLuong) AS SoLuong " +
                 "FROM ChiTietHoaDon cthd " +
                 "JOIN HoaDon hd ON hd.maHoaDon = cthd.maHoaDon " +
                 "WHERE YEAR(hd.ngayThanhToan) = ?";
    try (Connection con = DBConnect.DBConnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, nam);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            tong = rs.getInt("SoLuong");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return tong;
}



}
