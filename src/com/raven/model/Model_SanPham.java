/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

public class Model_SanPham {
    private int id;
    private String maSP;
    private String tenSP;
    private int soLuongTon;
    private double gia;
    private String mauSac;
    private String kichThuoc;
    private String loaiSanPham;
    private String moTa;
    private double thanhTien;
    private boolean trangThai;

    public int getId() {
        return id;
    }

    public Model_SanPham(String maSP, double gia, int soLuongTon, double thanhTien) {
        this.maSP = maSP;
        this.soLuongTon = soLuongTon;
        this.gia = gia;
        this.thanhTien = thanhTien;
    }
    

    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public double getGia() {
        return gia;
    }

    public String getMauSac() {
        return mauSac;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Model_SanPham() {
    }

    public Model_SanPham(int id, String maSP, String tenSP, String moTa, boolean trangThai) {
        this.id = id;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public Model_SanPham(int id, String maSP, String tenSP, int soLuongTon, double gia, String mauSac, String kichThuoc, String loaiSanPham, String moTa, boolean trangThai) {
        this.id = id;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuongTon = soLuongTon;
        this.gia = gia;
        this.mauSac = mauSac;
        this.kichThuoc = kichThuoc;
        this.loaiSanPham = loaiSanPham;
        this.trangThai = trangThai;
    }

    public Model_SanPham(int soLuongTon, String maSP, String tenSP, int aInt1, String mauSac, String kichThuoc, String loaiSanPham, double gia, boolean trangThai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuongTon = soLuongTon;
        this.gia = gia;
        this.mauSac = mauSac;
        this.kichThuoc = kichThuoc;
        this.loaiSanPham = loaiSanPham;
        this.trangThai = trangThai;
    }
    

    
    

    // Phương thức toString để hiển thị thông tin sản phẩm
    public Object toDataSP() {
        return new Object[]{id, maSP, tenSP,moTa};
    }

    public Object toData_CTSP() {
        return new Object[]{id,maSP,tenSP, soLuongTon, mauSac,kichThuoc,loaiSanPham,gia,trangThai};
    }
    public Object toData_CTSP1() {
        return new Object[]{maSP,tenSP, soLuongTon, gia,mauSac,kichThuoc,loaiSanPham};
    }
}
