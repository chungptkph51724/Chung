/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.repository;


import com.raven.model.Model_NV;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author macbook
 */
public class repository_NV {

    public List<Model_NV> getAll() {
        List<Model_NV> lst = new ArrayList();
        String sql = "SELECT id_nhanVien,ten,soDienThoai,taiKhoan, matKhau,trangThai FROM NhanVien";
        try (Connection con = DBConnect.DBConnect.getConnection()) {
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model_NV nv = new Model_NV(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                lst.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lst;
    }

    public boolean add(Model_NV nv) {
        int check = 0;
        String sql = "INSERT INTO NhanVien (id_nhanVien, ten, soDienThoai, taiKhoan, matKhau, trangThai)\n"
                + "VALUES(?,?,?,?,?,?) ";
        try (Connection con = DBConnect.DBConnect.getConnection()) {
            PreparedStatement ps = con.prepareCall(sql);
            ps.setObject(1, nv.getMaNV());
            ps.setObject(2, nv.getTenNV());
            ps.setObject(3, nv.getSdt());
            ps.setObject(4, nv.getTK());
            ps.setObject(5, nv.getMK());
            ps.setObject(6, nv.isTrangThai());
            check = ps.executeUpdate();
            if (check > 0) {
                JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            if(check<=0) {
                JOptionPane.showMessageDialog(null, "Thêm không thành công: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return check > 0;
    }

    public boolean update(Model_NV nv, String ma) {
        int check = 0;
        String sql = "Update NhanVien set ten = ?, soDienThoai=?, taiKhoan=?, matKhau=?, trangThai=? where id_nhanVien=? ";
        try (Connection con = DBConnect.DBConnect.getConnection()) {
            PreparedStatement ps = con.prepareCall(sql);
            ps.setObject(1, nv.getTenNV());
            ps.setObject(2, nv.getSdt());
            ps.setObject(3, nv.getTK());
            ps.setObject(4, nv.getMK());
            ps.setObject(5, nv.isTrangThai());
            ps.setObject(6, nv.getMaNV());
            check = ps.executeUpdate();
            if (check > 0) {
                JOptionPane.showMessageDialog(null, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            if(check<=0) {
                JOptionPane.showMessageDialog(null, "Sửa không thành công: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        return check > 0;
    }

    public boolean del(String ma) {
        int check = 0;
        String sql = "Delete from NhanVien where id_nhanVien =?";
        try (Connection con = DBConnect.DBConnect.getConnection()) {
            PreparedStatement ps = con.prepareCall(sql);
            ps.setObject(1, ma);
            check = ps.executeUpdate();
            if (check > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            if(check<=0) {
                JOptionPane.showMessageDialog(null, "Xóa không thành công: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return check > 0;

    }

}
