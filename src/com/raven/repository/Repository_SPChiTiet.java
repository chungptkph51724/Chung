/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.repository;

import com.raven.model.Model_SPChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class Repository_SPChiTiet {

    private Connection conn = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String query = null;

    public ArrayList<Model_SPChiTiet> getData() {
        ArrayList<Model_SPChiTiet> list = new ArrayList<>();
        try {
            conn = DBConnect.DBConnect.getConnection();
            query = "SELECT \n"
                    + "                spct.id,\n"
                    + "                spct.maSPChiTiet,\n"
                    + "                sp.tenSP,\n"
                    + "                lsp.tenLoaiSanPham,\n"
                    + "                ms.tenMau,\n"
                    + "                kt.tenKichThuoc,\n"
                    + "                spct.soLuongTon,\n"
                    + "                spct.giaBan,\n"
                    + "                spct.trangThai\n"
                    + "            FROM SPChiTiet spct\n"
                    + "            JOIN SanPham sp ON spct.id_sanPham = sp.id\n"
                    + "            JOIN LoaiSanPham lsp ON sp.id_loaiSanPham = lsp.id\n"
                    + "            JOIN MauSac ms ON spct.id_mauSac = ms.id\n"
                    + "            JOIN KichThuoc kt ON spct.id_kichThuoc = kt.id\n"
                    + "            WHERE spct.trangThai = 1";
            pr = conn.prepareStatement(query);
            rs = pr.executeQuery();
            while (rs.next()) {
                Model_SPChiTiet sp = new Model_SPChiTiet();
                sp.setId(rs.getInt("id"));
                sp.setMaSPChiTiet(rs.getString("maSPChiTiet"));
                sp.setTenSP(rs.getString("tenSP"));
                sp.setTenLoaiSP(rs.getString("tenLoaiSanPham"));
                sp.setTenMau(rs.getString("tenMau"));
                sp.setTenKichThuoc(rs.getString("tenKichThuoc"));
                sp.setSoLuongTon(rs.getInt("soLuongTon"));
                sp.setGiaBan(rs.getDouble("giaBan"));
                sp.setTrangThai(rs.getBoolean("trangThai"));
                list.add(sp);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy dữ liệu : " + e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pr != null) {
                    pr.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public boolean save(Model_SPChiTiet sp) throws SQLException {
        query = "INSERT INTO SPChiTiet \n" +
        "(maSPChiTiet, id_sanPham, id_mauSac, id_kichThuoc, soLuongTon, giaBan, trangThai)\n" +
        "VALUES (?,\n" +
        "    (SELECT TOP 1 id FROM SanPham WHERE tenSP = ?),\n" +
        "    (SELECT TOP 1 id FROM MauSac WHERE tenMau = ?),\n" +
        "    (SELECT TOP 1 id FROM KichThuoc WHERE tenKichThuoc = ?),\n" +
        "    ?, ?, ?)";


        try {
            conn = DBConnect.DBConnect.getConnection();
            pr = conn.prepareStatement(query);
            pr.setString(1, sp.getMaSPChiTiet());
            pr.setString(2, sp.getTenSP());
            pr.setString(3, sp.getTenMau());
            pr.setString(4, sp.getTenKichThuoc());
            pr.setInt(5, sp.getSoLuongTon());
            pr.setDouble(6, sp.getGiaBan());
            pr.setBoolean(7, sp.isTrangThai());

            return pr.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm mới: " + ex.getMessage());
            return false;
        } finally {
            try {
                if (pr != null) {
                    pr.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean update(Model_SPChiTiet sp) {
    String productIdQuery = "SELECT TOP 1 sp.id " +
                            "FROM SanPham sp " +
                            "JOIN LoaiSanPham lsp ON sp.id_loaiSanPham = lsp.id " +
                            "WHERE sp.tenSP = ? AND lsp.tenLoaiSanPham = ?";
    
    String updateQuery = "UPDATE SPChiTiet SET " +
                         "    maSPChiTiet = ?, " +
                         "    id_sanPham = ?, " +
                         "    id_mauSac = (SELECT TOP 1 id FROM MauSac WHERE tenMau = ?), " +
                         "    id_kichThuoc = (SELECT TOP 1 id FROM KichThuoc WHERE tenKichThuoc = ?), " +
                         "    soLuongTon = ?, " +
                         "    giaBan = ?, " +
                         "    trangThai = ? " +
                         "WHERE maSPChiTiet = ?";

    Connection conn = null;
    PreparedStatement pr = null;
    ResultSet rs = null;
    int productId = -1;  // Default invalid value

    try {
        conn = DBConnect.DBConnect.getConnection();
        
        // Kiểm tra xem sản phẩm và loại sản phẩm có tồn tại không
        pr = conn.prepareStatement(productIdQuery);
        pr.setString(1, sp.getTenSP());
        pr.setString(2, sp.getTenLoaiSP());
        rs = pr.executeQuery();
        
        if (rs.next()) {
            // Nếu có, lấy ID sản phẩm
            productId = rs.getInt("id");
        }

        // Nếu không tìm thấy sản phẩm, ta có thể gán một giá trị mặc định hoặc xử lý lỗi
        if (productId == -1) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm hoặc loại sản phẩm!");
            return false;
        }

        // Cập nhật thông tin sản phẩm chi tiết
        pr = conn.prepareStatement(updateQuery);
        pr.setString(1, sp.getMaSPChiTiet());
        pr.setInt(2, productId);  // Gán ID sản phẩm tìm được
        pr.setString(3, sp.getTenMau());
        pr.setString(4, sp.getTenKichThuoc());
        pr.setInt(5, sp.getSoLuongTon());
        pr.setDouble(6, sp.getGiaBan());
        pr.setBoolean(7, sp.isTrangThai());
        pr.setString(8, sp.getMaSPChiTiet());

        return pr.executeUpdate() > 0;

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Lỗi cập nhật sản phẩm: " + ex.getMessage());
        return false;
    } finally {
        try {
            if (rs != null) rs.close();
            if (pr != null) pr.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




    public boolean delete(String maSPChiTiet) {
        query = "UPDATE SPChiTiet SET trangThai = 0 WHERE maSPChiTiet = ?";  // Đảm bảo `maSPChiTiet` là đúng tên trường trong DB
        try {
            conn = DBConnect.DBConnect.getConnection();  // Kiểm tra kết nối DB
            pr = conn.prepareStatement(query);
            pr.setString(1, maSPChiTiet);

            // Kiểm tra số dòng bị ảnh hưởng bởi câu lệnh UPDATE
            int affectedRows = pr.executeUpdate();
            return affectedRows > 0;  // Nếu có ít nhất 1 dòng bị ảnh hưởng, xóa thành công
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Xóa lỗi: " + e.getMessage());
            return false;
        } finally {
            try {
                if (pr != null) {
                    pr.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<Model_SPChiTiet> filterByLoaiSanPham(String tenLoaiSP) {
    ArrayList<Model_SPChiTiet> list = new ArrayList<>();
    query = "SELECT \n"
            + "    spct.id,\n"
            + "    spct.maSPChiTiet,\n"
            + "    sp.tenSP,\n"
            + "    lsp.tenLoaiSanPham,\n"
            + "    ms.tenMau,\n"
            + "    kt.tenKichThuoc,\n"
            + "    spct.soLuongTon,\n"
            + "    spct.giaBan,\n"
            + "    spct.trangThai\n"
            + "FROM SPChiTiet spct\n"
            + "JOIN SanPham sp ON spct.id_sanPham = sp.id\n"
            + "JOIN LoaiSanPham lsp ON sp.id_loaiSanPham = lsp.id\n"
            + "JOIN MauSac ms ON spct.id_mauSac = ms.id\n"
            + "JOIN KichThuoc kt ON spct.id_kichThuoc = kt.id\n"
            + "WHERE lsp.tenLoaiSanPham = ? AND spct.trangThai = 1";

    try {
        conn = DBConnect.DBConnect.getConnection();
        pr = conn.prepareStatement(query);
        pr.setString(1, tenLoaiSP);
        rs = pr.executeQuery();
        while (rs.next()) {
            Model_SPChiTiet sp = new Model_SPChiTiet();
            sp.setId(rs.getInt("id"));
            sp.setMaSPChiTiet(rs.getString("maSPChiTiet"));
            sp.setTenSP(rs.getString("tenSP"));
            sp.setTenLoaiSP(rs.getString("tenLoaiSanPham"));
            sp.setTenMau(rs.getString("tenMau"));
            sp.setTenKichThuoc(rs.getString("tenKichThuoc"));
            sp.setSoLuongTon(rs.getInt("soLuongTon"));
            sp.setGiaBan(rs.getDouble("giaBan"));
            sp.setTrangThai(rs.getBoolean("trangThai"));
            list.add(sp);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Lỗi lọc loại sản phẩm: " + e.getMessage());
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
    return list;
}


}
