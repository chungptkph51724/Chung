/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

/**
 *
 * @author admin
 */
public class Model_SPChiTiet {

    private int id;
    private String maSPChiTiet;
    private String tenSP, tenLoaiSP, tenMau, tenKichThuoc;
    private int idSanPham, idMauSac, idKichThuoc;
    private int soLuongTon;
    private double giaBan;
    private boolean trangThai;

    public Model_SPChiTiet() {
    }
    

    public Model_SPChiTiet(int id, String maSPChiTiet, String tenSP, String tenMau, String tenKichThuoc, String tenLoaiSP, int soLuongTon, double giaBan, boolean trangThai) {
        this.id = id;
        this.maSPChiTiet = maSPChiTiet;
        this.tenSP = tenSP;
        this.tenMau = tenMau;
        this.tenKichThuoc = tenKichThuoc;
        this.tenLoaiSP = tenLoaiSP;
        this.soLuongTon = soLuongTon;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
    }

    public Model_SPChiTiet(String maSPChiTiet, String tenSP, String tenMau, String tenKichThuoc, String tenLoaiSP, int soLuongTon, double giaBan, boolean trangThai) {
        this.maSPChiTiet = maSPChiTiet;
        this.tenSP = tenSP;
        this.tenMau = tenMau;
        this.tenKichThuoc = tenKichThuoc;
        this.tenLoaiSP = tenLoaiSP;
        this.soLuongTon = soLuongTon;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public int getIdMauSac() {
        return idMauSac;
    }

    public int getIdKichThuoc() {
        return idKichThuoc;
    }
    

    public int getId() {
        return id;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public void setIdMauSac(int idMauSac) {
        this.idMauSac = idMauSac;
    }

    public void setIdKichThuoc(int idKichThuoc) {
        this.idKichThuoc = idKichThuoc;
    }

    public String getMaSPChiTiet() {
        return maSPChiTiet;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getTenMau() {
        return tenMau;
    }

    public String getTenKichThuoc() {
        return tenKichThuoc;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMaSPChiTiet(String maSPChiTiet) {
        this.maSPChiTiet = maSPChiTiet;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public void setTenKichThuoc(String tenKichThuoc) {
        this.tenKichThuoc = tenKichThuoc;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    public Object[] toDataRow() {
        return new Object[]{id,maSPChiTiet, tenSP, tenMau, tenKichThuoc,tenLoaiSP, soLuongTon, giaBan,trangThai ?"Còn hàng":"Hết hàng"};
    }

}
