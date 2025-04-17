package com.raven.repository;


import com.raven.model.Model_KhachHang;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class repository_KhachHang {

    private Connection conn = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String query = null;

    public ArrayList<Model_KhachHang> getData() {
    ArrayList<Model_KhachHang> lst = new ArrayList<>();
    try {
        conn = DBConnect.DBConnect.getConnection();
        query = "SELECT id, maKhachHang, ten, soDienThoai, email, diaChi, gioiTinh, trangThai FROM KhachHang where trangThai=1";
        pr = conn.prepareStatement(query);
        rs = pr.executeQuery();
        while (rs.next()) {
            Model_KhachHang kh = new Model_KhachHang();
            kh.setId(rs.getInt("id"));
            kh.setMaKhachHang(rs.getString("maKhachHang"));
            kh.setTen(rs.getString("ten"));
            kh.setSoDienThoai(rs.getString("soDienThoai"));
            kh.setEmail(rs.getString("email"));
            kh.setDiaChi(rs.getString("diaChi"));
            kh.setGioiTinh(rs.getBoolean("gioiTinh"));
            kh.setTrangThai(rs.getBoolean("trangThai"));
            lst.add(kh);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Lỗi lấy dữ liệu khách hàng: " + e.getMessage());
        return null;
    } finally {
        try {
            if (rs != null) rs.close();
            if (pr != null) pr.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return lst;
}


    public int save(Model_KhachHang kh) throws SQLException {
        query = "INSERT INTO KhachHang(maKhachHang, ten, soDienThoai, email, diaChi, gioiTinh, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        conn = DBConnect.DBConnect.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, kh.getMaKhachHang());
        ps.setString(2, kh.getTen());
        ps.setString(3, kh.getSoDienThoai());
        ps.setString(4, kh.getEmail());
        ps.setString(5, kh.getDiaChi());
        ps.setBoolean(6, kh.isGioiTinh());
        ps.setBoolean(7, kh.isTrangThai());
        return ps.executeUpdate();
    }

    public boolean update(Model_KhachHang kh, String maKH) {
        query = "UPDATE KhachHang SET ten = ?, soDienThoai = ?, email = ?, diaChi = ?, gioiTinh = ?, trangThai = ? WHERE maKhachHang = ?";
        try {
            conn = DBConnect.DBConnect.getConnection();
            pr = conn.prepareStatement(query);
            pr.setString(1, kh.getTen());
            pr.setString(2, kh.getSoDienThoai());
            pr.setString(3, kh.getEmail());
            pr.setString(4, kh.getDiaChi());
            pr.setBoolean(5, kh.isGioiTinh());
            pr.setBoolean(6, kh.isTrangThai());
            pr.setString(7, kh.getMaKhachHang());
            return pr.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi sửa: " + ex.getMessage());
            return false;
        }
    }

    public boolean delete(String ma) {
        query = "UPDATE KhachHang SET trangThai = 0 WHERE maKhachHang = ?";
        try {
            conn = DBConnect.DBConnect.getConnection();
            pr = conn.prepareStatement(query);
            pr.setString(1, ma);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Xoá lỗi: " + e.getMessage());
            return false;
        }
    }
    // Tìm kiếm khách hàng theo số điện thoại
public ArrayList<Model_KhachHang> tim(String sdt) {
    ArrayList<Model_KhachHang> lst = new ArrayList<>();
    String sql = "SELECT id, maKhachHang, ten, soDienThoai, email, diaChi, gioiTinh, trangThai FROM KhachHang WHERE soDienThoai = ?";
    try {
        conn = DBConnect.DBConnect.getConnection();
        pr = conn.prepareStatement(sql);
        pr.setString(1, sdt);
        rs = pr.executeQuery();
        while (rs.next()) {
            Model_KhachHang kh = new Model_KhachHang();
            kh.setId(rs.getInt("id"));
            kh.setMaKhachHang(rs.getString("maKhachHang"));
            kh.setTen(rs.getString("ten"));
            kh.setSoDienThoai(rs.getString("soDienThoai"));
            kh.setEmail(rs.getString("email"));
            kh.setDiaChi(rs.getString("diaChi"));
            kh.setGioiTinh(rs.getBoolean("gioiTinh"));
            kh.setTrangThai(rs.getBoolean("trangThai"));
            lst.add(kh);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    return lst;
}


    // Tính tổng tiền khách đã thanh toán theo mã khách hàng
public double tong(String maKH) {
    String sql = "SELECT SUM(hd.tongTienSauKM) " +
                 "FROM HoaDon hd " +
                 "JOIN KhachHang kh ON hd.id_khachHang = kh.id " +
                 "WHERE kh.maKhachHang = ? " +
                 "GROUP BY kh.maKhachHang";
    double tong = 0.0;
    try {
        conn = DBConnect.DBConnect.getConnection();
        pr = conn.prepareStatement(sql);
        pr.setString(1, maKH);
        rs = pr.executeQuery();
        if (rs.next()) {
            tong = rs.getDouble(1);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    return tong;
}

    // Tự động cập nhật khách hàng vào hóa đơn theo số điện thoại
public String[] capNhatThongTinKH(String sdt, String maHD) {
    String[] thongTinKH = null;
    String query = "SELECT id, maKhachHang, ten FROM KhachHang WHERE soDienThoai = ?";
    try {
        conn = DBConnect.DBConnect.getConnection();
        pr = conn.prepareStatement(query);
        pr.setString(1, sdt);
        rs = pr.executeQuery();
        if (rs.next()) {
            thongTinKH = new String[2];
            int idKH = rs.getInt("id");
            thongTinKH[0] = rs.getString("maKhachHang");
            thongTinKH[1] = rs.getString("ten");

            // Cập nhật vào hóa đơn
            String updateHD = "UPDATE HoaDon SET id_khachHang = ? WHERE maHoaDon = ?";
            PreparedStatement ps = conn.prepareStatement(updateHD);
            ps.setInt(1, idKH);
            ps.setString(2, maHD);
            ps.executeUpdate();
            ps.close();
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với số điện thoại: " + sdt);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pr != null) pr.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    return thongTinKH;
}

}
