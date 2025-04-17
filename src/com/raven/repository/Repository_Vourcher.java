package com.raven.repository;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.raven.model.Model_Vourcher;

public class Repository_Vourcher {
    public static ArrayList<Model_Vourcher> list = new ArrayList<>();

    public ArrayList<Model_Vourcher> getAll() {
        String sql = """
               SELECT [id]
                        ,[maVoucher]
                        ,[moTa]
                        ,[giamGia]
                        ,[giamGiaToiDa]
                        ,[hinhThucGiamGia]
                        ,[ngayBD]
                        ,[ngayKT]
                        ,[dieuKienApDung]
                    FROM [dbo].[Voucher]
                     WHERE [trangThai] = 1
                """;
        try {
            Connection conn = DBConnect.DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Model_Vourcher vc = new Model_Vourcher();
                vc.setId(rs.getInt(1));
                vc.setMaVoucher(rs.getString(2));
                vc.setMota(rs.getString(3));
                vc.setGiamGia(rs.getFloat(4));
                vc.setGiamGiaToiDa(rs.getBigDecimal(5));
                vc.setHinhThucGiamGia(rs.getInt(6));
                vc.setNgayBatDau(rs.getDate(7));
                vc.setNgayKetThuc(rs.getDate(8));
                vc.setDieuKienApDung(rs.getString(9));
                list.add(vc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean AddVoucher(Model_Vourcher vc) {
        String sql = """
                     INSERT INTO [dbo].[Voucher]
                                ([maVoucher]
                                ,[moTa]
                                ,[giamGia]
                                ,[giamGiaToiDa]
                                ,[hinhThucGiamGia]
                                ,[ngayBD]
                                ,[ngayKT]
                                ,[dieuKienApDung])
                          VALUES(?,?,?,?,?,?,?,?)
                     """;

        try (Connection conn = DBConnect.DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, vc.getMaVoucher());
            ps.setString(2, vc.getMota());
            ps.setFloat(3, vc.getGiamGia());
            ps.setBigDecimal(4, vc.getGiamGiaToiDa());
            ps.setInt(5, vc.getHinhThucGiamGia());
            ps.setDate(6, new java.sql.Date(vc.getNgayBatDau().getTime()));
            ps.setDate(7, new java.sql.Date(vc.getNgayKetThuc().getTime()));
            ps.setString(8, vc.getDieuKienApDung());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UpdateVoucher(Model_Vourcher vc) {
        String sql = """
                     UPDATE [dbo].[Voucher]
                             SET [maVoucher] = ?
                                ,[moTa] = ?
                                ,[giamGia] = ?
                                ,[giamGiaToiDa] = ?
                                ,[hinhThucGiamGia] = ?
                                ,[ngayBD] = ?
                                ,[ngayKT] = ?
                                ,[dieuKienApDung] = ?
                           WHERE [id]=?
                     """;

        try (Connection conn = DBConnect.DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, vc.getMaVoucher());
            ps.setString(2, vc.getMota());
            ps.setFloat(3, vc.getGiamGia());
            ps.setBigDecimal(4, vc.getGiamGiaToiDa());
            ps.setInt(5, vc.getHinhThucGiamGia());
            ps.setDate(6, new java.sql.Date(vc.getNgayBatDau().getTime()));
            ps.setDate(7, new java.sql.Date(vc.getNgayKetThuc().getTime()));
            ps.setString(8, vc.getDieuKienApDung());
            ps.setInt(9, vc.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
      public boolean DeleteVoucher(Model_Vourcher vc) {
        String sql = """
                     UPDATE [dbo].[Voucher]
                             SET 
                                [trangThai]=0
                           WHERE [id]=?
                     """;

        try (Connection conn = DBConnect.DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vc.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        Repository_Vourcher repo = new Repository_Vourcher();
        list = repo.getAll();

        for (Model_Vourcher gv : list) {
            System.out.println(gv.getId() + " " + gv.getMaVoucher() + " " + gv.getMota());
        }
    }
}
