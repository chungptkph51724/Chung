/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.repository;

import com.raven.model.Model_SanPham;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class reponsitory_SanPham {

    private Connection con = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<Model_SanPham> gettAll_Sp() {

        ArrayList<Model_SanPham> ds = new ArrayList<Model_SanPham>();
        sql = "	select id, maSP,tenSP,moTa,trangThai  from SanPham";
        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            int id = 1;
            while (rs.next()) {
                Model_SanPham sp = new Model_SanPham(rs.getInt("id"),rs.getString("maSP"), rs.getString("tenSP"), rs.getString("moTa"), rs.getBoolean("trangThai"));
                id++;
                ds.add(sp);
            }
            return ds;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public ArrayList<Model_SanPham> gettAll_SpChiTiet() {
        ArrayList<Model_SanPham> ds = new ArrayList<>();
        sql = "SELECT ct.id, sp.maSP, sp.tenSP, ct.soLuongTon, ms.tenMau, kt.tenKichThuoc, lsp.tenLoaiSanPham, ct.giaBan, ct.trangThai\n"
                + "FROM SPChiTiet ct\n"
                + "JOIN SanPham sp ON ct.id_sanPham = sp.id\n"
                + "JOIN MauSac ms ON ct.id_mauSac = ms.id\n"
                + "JOIN KichThuoc kt ON ct.id_kichThuoc = kt.id\n"
                + "JOIN LoaiSanPham lsp ON sp.id_loaiSanPham = lsp.id\n"
                + "WHERE ct.soLuongTon > 0;";

        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();

            while (rs.next()) {

                Model_SanPham sp;
                sp = new Model_SanPham(
                        rs.getInt("id"),
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getInt("soLuongTon"),
                        rs.getString("tenMau"),
                        rs.getString("tenKichThuoc"),
                        rs.getString("tenLoaiSanPham"),
                        rs.getDouble("giaBan"),
                        rs.getBoolean("trangThai")
                );

                ds.add(sp);
            }
            return ds;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        } finally {
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
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    public ArrayList<Model_SanPham> search_SanPham(String keyword) {
        ArrayList<Model_SanPham> ds = new ArrayList<>();
        sql = "SELECT id, maSP, tenSP, moTa, trangThai FROM SanPham "
                + "WHERE maSP LIKE ? OR tenSP LIKE ?";

        try {
            con = DBConnect.DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, "%" + keyword + "%");
            pr.setString(2, "%" + keyword + "%");
            rs = pr.executeQuery();

            int id = 1;
            while (rs.next()) {
                Model_SanPham sp;
                sp = new Model_SanPham(
                        rs.getInt("id"),
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getString("moTa"),
                        rs.getBoolean("trangThai")
                );
                ds.add(sp);
            }
            return ds;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        } finally {
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
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

}
