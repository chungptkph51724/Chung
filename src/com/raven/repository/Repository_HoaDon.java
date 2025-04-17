package com.raven.repository;

import com.raven.model.Model_HoaDon;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Repository_HoaDon {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    // Lấy tất cả hóa đơn
    public ArrayList<Model_HoaDon> getAll() {
        ArrayList<Model_HoaDon> list = new ArrayList<>();
        String sql = """
            SELECT hd.maHoaDon, kh.maKhachHang, kh.ten, kh.soDienThoai, 
                   hd.id_nhanVien, hd.ngayThanhToan, hd.tongTienBanDau, 
                   hd.tongKhuyenMai, vc.maVoucher, hd.tongTienSauKM, hd.trangThai
            FROM HoaDon hd
            LEFT JOIN KhachHang kh ON kh.id = hd.id_khachHang
            LEFT JOIN Voucher vc ON vc.id = hd.id_voucher
        """;

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Model_HoaDon hd = new Model_HoaDon(
                    rs.getString("maHoaDon"),
                    rs.getString("maKhachHang"),
                    rs.getString("ten"),
                    rs.getString("soDienThoai"),
                    rs.getString("id_nhanVien"),
                    rs.getString("ngayThanhToan"),
                    rs.getString("maVoucher"),
                    rs.getDouble("tongTienBanDau"),
                    rs.getDouble("tongKhuyenMai"),
                    rs.getDouble("tongTienSauKM"),
                    rs.getBoolean("trangThai")
                );
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    // Lọc hóa đơn theo trạng thái
    public ArrayList<Model_HoaDon> loc(boolean trangThai) {
        ArrayList<Model_HoaDon> list = new ArrayList<>();
        String sql = """
            SELECT hd.maHoaDon, kh.maKhachHang, kh.ten, kh.soDienThoai, 
                   hd.id_nhanVien, hd.ngayThanhToan, hd.tongTienBanDau, 
                   hd.tongKhuyenMai, vc.maVoucher, hd.tongTienSauKM, hd.trangThai
            FROM HoaDon hd
            LEFT JOIN KhachHang kh ON kh.id = hd.id_khachHang
            LEFT JOIN Voucher vc ON vc.id = hd.id_voucher
            WHERE hd.trangThai = ?
        """;

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, trangThai);
            rs = ps.executeQuery();
            while (rs.next()) {
                Model_HoaDon hd = new Model_HoaDon(
                    rs.getString("maHoaDon"),
                    rs.getString("maKhachHang"),
                    rs.getString("ten"),
                    rs.getString("soDienThoai"),
                    rs.getString("id_nhanVien"),
                    rs.getString("ngayThanhToan"),
                    rs.getString("maVoucher"),
                    rs.getDouble("tongTienBanDau"),
                    rs.getDouble("tongKhuyenMai"),
                    rs.getDouble("tongTienSauKM"),
                    rs.getBoolean("trangThai")
                );
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    // Tìm kiếm theo mã hóa đơn
    public ArrayList<Model_HoaDon> timkiemHD(String maHD) {
        ArrayList<Model_HoaDon> list = new ArrayList<>();
        String sql = """
            SELECT hd.maHoaDon, kh.maKhachHang, kh.ten, kh.soDienThoai, 
                   hd.id_nhanVien, hd.ngayThanhToan, hd.tongTienBanDau, 
                   hd.tongKhuyenMai, vc.maVoucher, hd.tongTienSauKM, hd.trangThai
            FROM HoaDon hd
            LEFT JOIN KhachHang kh ON kh.id = hd.id_khachHang
            LEFT JOIN Voucher vc ON vc.id = hd.id_voucher
            WHERE hd.maHoaDon = ?
        """;

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                Model_HoaDon hd = new Model_HoaDon(
                    rs.getString("maHoaDon"),
                    rs.getString("maKhachHang"),
                    rs.getString("ten"),
                    rs.getString("soDienThoai"),
                    rs.getString("id_nhanVien"),
                    rs.getString("ngayThanhToan"),
                    rs.getString("maVoucher"),
                    rs.getDouble("tongTienBanDau"),
                    rs.getDouble("tongKhuyenMai"),
                    rs.getDouble("tongTienSauKM"),
                    rs.getBoolean("trangThai")
                );
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    public int xoa(String maHD) {
        String sql1 = "DELETE FROM HoaDonChiTiet WHERE maHoaDon = ?";
        String sql2 = "DELETE FROM HoaDon WHERE maHoaDon = ?";
        int rows = 0;

        try {
            con = DBConnect.DBConnect.getConnection();

            ps = con.prepareStatement(sql1);
            ps.setString(1, maHD);
            ps.executeUpdate();

            ps = con.prepareStatement(sql2);
            ps.setString(1, maHD);
            rows = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return rows;
    }

    public void CapNhatHoaDonThanhToan(Model_HoaDon hd, String maHD) {
        String sql = """
            UPDATE HoaDon
            SET tongTienBanDau = ?, tongKhuyenMai = ?, tongTienSauKM = ?, id_voucher = ?, trangThai = 1, ngayThanhToan = GETDATE()
            WHERE maHoaDon = ?
        """;

        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, hd.getTongTienBanDau());
            ps.setDouble(2, hd.getTongKhuyenMai());
            ps.setDouble(3, hd.getTongTienSauKM());
            ps.setInt(4, Integer.parseInt(hd.getMaVoucher()));
            ps.setString(5, maHD);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật hóa đơn thành công.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật: " + e.getMessage());
        } finally {
            close();
        }
    }

    public void taoHoaDonCho(String maHoaDon, String idNhanVien) {
        String sql = "INSERT INTO HoaDon (maHoaDon, id_nhanVien, trangThai) VALUES (?, ?, 0)";
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maHoaDon);
            ps.setString(2, idNhanVien);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi tạo hóa đơn chờ: " + e.getMessage());
        } finally {
            close();
        }
    }

    public ArrayList<Model_HoaDon> getAllChoHoaDonTT() {
        ArrayList<Model_HoaDon> list = new ArrayList<>();
        String sql = "SELECT maHoaDon, id_nhanVien, ngayThanhToan, trangThai FROM HoaDon WHERE trangThai = 0";
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Model_HoaDon hd = new Model_HoaDon(
                    rs.getString("maHoaDon"),
                    rs.getString("id_nhanVien"),
                    rs.getString("ngayThanhToan"),
                    rs.getBoolean("trangThai")
                );
                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    public void themMaKH(String maHD, String maKH) {
        String sql = "UPDATE HoaDon SET id_khachHang = ? WHERE maHoaDon = ?";
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maKH);
            ps.setString(2, maHD);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm khách hàng: " + e.getMessage());
        } finally {
            close();
        }
    }

    private void close() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    
}
