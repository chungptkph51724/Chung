package com.raven.repository;

import com.raven.model.Model_HDCT;
import com.raven.model.Model_SanPham;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Repository_HDCT {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    // Thêm sản phẩm vào Giỏ Hàng Tạm Thời
//    public void ThemGioHang(Model_SanPham sp, String maHoaDon) {
//        try {
//            sql = "INSERT INTO GioHangTamThoi(soLuong, donGia, maHoaDon, maSP) VALUES (?, ?, ?, ?)";
//            con = DBConnect.DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, sp.getSoLuongTon());
//            ps.setDouble(2, sp.getGia());
//            ps.setString(3, maHoaDon);
//            ps.setString(4, sp.getMaSP());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Lỗi thêm giỏ hàng: " + e.getMessage());
//        }
//    }

    // Lấy danh sách giỏ hàng theo mã hóa đơn
//    public ArrayList<Model_SanPham> getAllGioHangTamThoi(String maHD) {
//        ArrayList<Model_SanPham> list_HD = new ArrayList<>();
//        sql = "SELECT maGioHangTamThoi, maSP, donGia, soLuong FROM GioHangTamThoi WHERE maHoaDon = ?";
//
//        try {
//            double thanhTien = 0;
//            con = DBConnect.DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, maHD);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                String maSP = rs.getString("maSP");
//                double donGia = rs.getDouble("donGia");
//                int soLuong = rs.getInt("soLuong");
//                thanhTien += donGia * soLuong;
//
//                //Model_SanPham sp = new Model_SanPham(maSP, donGia, soLuong, thanhTien);
//                list_HD.add(sp);
//            }
//
//            return list_HD;
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Lỗi lấy giỏ hàng: " + e.getMessage());
//            return null;
//        }
//    }

    // Lấy toàn bộ hóa đơn chi tiết
    public ArrayList<Model_HDCT> getAll() {
        ArrayList<Model_HDCT> list_HDCT = new ArrayList<>();
        sql = "SELECT maHDCT, maHoaDon, maSP, donGia, soLuong FROM ChiTietHoaDon";

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String maHDCT = rs.getString("maHDCT");
                String maHD = rs.getString("maHoaDon");
                String maSP = rs.getString("maSP");
                double donGia = rs.getDouble("donGia");
                int soLuong = rs.getInt("soLuong");

                Model_HDCT hdct = new Model_HDCT(maHDCT, maHD, maSP, soLuong, donGia);
                list_HDCT.add(hdct);
            }

            return list_HDCT;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn HDCT: " + e.getMessage());
            return null;
        }
    }

    // Tìm kiếm chi tiết hóa đơn theo mã hóa đơn
    public ArrayList<Model_HDCT> timkiemHDCT(String maHDcantim) {
        ArrayList<Model_HDCT> list_HDCT = new ArrayList<>();
        sql = "SELECT maHDCT, maHoaDon, maSP, donGia, soLuong FROM ChiTietHoaDon WHERE maHoaDon = ?";

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maHDcantim);
            rs = ps.executeQuery();

            while (rs.next()) {
                String maHDCT = rs.getString("maHDCT");
                String maHD = rs.getString("maHoaDon");
                String maSP = rs.getString("maSP");
                double donGia = rs.getDouble("donGia");
                int soLuong = rs.getInt("soLuong");

                Model_HDCT hdct = new Model_HDCT(maHDCT, maHD, maSP, soLuong, donGia);
                list_HDCT.add(hdct);
            }

            return list_HDCT;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi tìm HDCT: " + e.getMessage());
            return null;
        }
    }

    // Thêm danh sách sản phẩm vào bảng HoaDonChiTiet
//    public void ThemHDCT(ArrayList<Model_SanPham> danhSachSanPham, String maHD) {
//        try {
//            sql = "INSERT INTO ChiTietHoaDon(soLuong, donGia, maHoaDon, maSanPham) VALUES (?, ?, ?, ?)";
//            con = DBConnect.DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//
//            for (Model_SanPham sp : danhSachSanPham) {
//                ps.setInt(1, sp.getSoLuongTon());
//                ps.setDouble(2, sp.getGia());
//                ps.setString(3, maHD);
//                ps.setString(4, sp.getMaSP());
//                ps.addBatch();
//            }
//
//            ps.executeBatch();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Lỗi thêm HDCT: " + e.getMessage());
//        }
//    }

    // Xóa sản phẩm khỏi giỏ hàng theo mã sản phẩm
    public void XoaGioHang(String maSP) {
        sql = "DELETE FROM GioHangTamThoi WHERE maSP = ?";
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maSP);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa khỏi giỏ hàng: " + e.getMessage());
        }
    }
    public void xoaToanBoGioHang(String maHD) {
    sql = "DELETE FROM GioHangTamThoi WHERE maHoaDon = ?";
    try {
        con = DBConnect.DBConnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, maHD);
        ps.executeUpdate();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi xóa toàn bộ giỏ hàng: " + e.getMessage());
    }
}
public boolean sanPhamDaTonTaiTrongGio(String maSP, String maHD) {
    sql = "SELECT 1 FROM GioHangTamThoi WHERE maSP = ? AND maHoaDon = ?";
    try {
        con = DBConnect.DBConnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, maSP);
        ps.setString(2, maHD);
        rs = ps.executeQuery();
        return rs.next();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi kiểm tra sản phẩm trong giỏ: " + e.getMessage());
        return false;
    }
}
public double tinhTongTienTamThoi(String maHD) {
    double tong = 0;
    sql = "SELECT SUM(soLuong * donGia) FROM GioHangTamThoi WHERE maHoaDon = ?";
    try {
        con = DBConnect.DBConnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, maHD);
        rs = ps.executeQuery();
        if (rs.next()) {
            tong = rs.getDouble(1);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi tính tổng tiền tạm: " + e.getMessage());
    }
    return tong;
}

}
