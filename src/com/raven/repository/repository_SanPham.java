package com.raven.repository;

import com.raven.model.Model_SanPham;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class repository_SanPham {

    private Connection con = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String sql = null;

    // Lấy toàn bộ danh sách sản phẩm
    public ArrayList<Model_SanPham> getData() {
        ArrayList<Model_SanPham> list = new ArrayList<>();
        sql = "SELECT sp.id, sp.maSP, sp.tenSP, lsp.tenLoaiSanPham, sp.moTa, sp.trangThai "
                + "FROM SanPham sp JOIN LoaiSanPham lsp ON sp.id_loaiSanPham = lsp.id "
                + "WHERE sp.trangThai = 1";

        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) {
                Model_SanPham sp = new Model_SanPham();
                sp.setId(rs.getInt("id"));
                sp.setMaSP(rs.getString("maSP"));
                sp.setTenSP(rs.getString("tenSP"));
                sp.setLoaiSanPham(rs.getString("tenLoaiSanPham"));
                sp.setMoTa(rs.getString("moTa"));
                sp.setTrangThai(rs.getBoolean("trangThai"));
                list.add(sp);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy dữ liệu: " + e.getMessage());
            return null;
        } finally {
            closeResources();
        }
        return list;
    }

    // Thêm sản phẩm
    public boolean save(Model_SanPham sp) {
        sql = "INSERT INTO SanPham (maSP, tenSP, moTa, id_loaiSanPham, trangThai) "
                + "VALUES (?, ?, ?, (SELECT TOP 1 id FROM LoaiSanPham WHERE tenLoaiSanPham = ?), ?)";

        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, sp.getMaSP());
            pr.setString(2, sp.getTenSP());
            pr.setString(3, sp.getMoTa());
            pr.setString(4, sp.getLoaiSanPham());
            pr.setBoolean(5, sp.isTrangThai());

            return pr.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm mới: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    // Cập nhật sản phẩm
    public boolean update(Model_SanPham sp) {
        sql = "UPDATE SanPham SET maSP = ?, tenSP = ?, moTa = ?, "
                + "id_loaiSanPham = (SELECT TOP 1 id FROM LoaiSanPham WHERE tenLoaiSanPham = ?), "
                + "trangThai = ? WHERE id = ?";  // dùng id để định danh sản phẩm

        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, sp.getMaSP());
            pr.setString(2, sp.getTenSP());
            pr.setString(3, sp.getMoTa());
            pr.setString(4, sp.getLoaiSanPham());
            pr.setBoolean(5, sp.isTrangThai());
            pr.setInt(6, sp.getId());  

            return pr.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật sản phẩm: " + ex.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    // Xóa mềm sản phẩm
    public boolean delete(String maSP) {
        sql = "UPDATE SanPham SET trangThai = 0 WHERE maSP = ?";
        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, maSP);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa: " + e.getMessage());
            return false;
        } finally {
            closeResources();
        }
    }

    // Tìm kiếm sản phẩm
    public ArrayList<Model_SanPham> search_SanPham(String keyword) {
        ArrayList<Model_SanPham> ds = new ArrayList<>();
        sql = "SELECT sp.id, sp.maSP, sp.tenSP, lsp.tenLoaiSanPham, sp.moTa, sp.trangThai "
                + "FROM SanPham sp JOIN LoaiSanPham lsp ON sp.id_loaiSanPham = lsp.id "
                + "WHERE (sp.maSP LIKE ? OR sp.tenSP LIKE ?) AND sp.trangThai = 1";

        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, "%" + keyword + "%");
            pr.setString(2, "%" + keyword + "%");
            rs = pr.executeQuery();

            while (rs.next()) {
                Model_SanPham sp = new Model_SanPham();
                sp.setId(rs.getInt("id"));
                sp.setMaSP(rs.getString("maSP"));
                sp.setTenSP(rs.getString("tenSP"));
                sp.setLoaiSanPham(rs.getString("tenLoaiSanPham"));
                sp.setMoTa(rs.getString("moTa"));
                sp.setTrangThai(rs.getBoolean("trangThai"));
                ds.add(sp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi tìm kiếm: " + e.getMessage());
        } finally {
            closeResources();
        }
        return ds;
    }

    // Đóng tài nguyên chung
    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pr != null) {
                pr.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
