/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

public class Model_SanPham {
    private int id;
    private String maSP;
    private String tenSP;
    private String loaiSanPham;
    private String moTa;
    private boolean trangThai;

    public Model_SanPham() {
    }

    public Model_SanPham(String maSP, String tenSP, String loaiSanPham, String moTa, boolean trangThai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSanPham = loaiSanPham;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public Model_SanPham(int id, String maSP, String tenSP, String loaiSanPham, String moTa, boolean trangThai) {
        this.id = id;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSanPham = loaiSanPham;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public String getMoTa() {
        return moTa;
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

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Object toDataSP() {
        return new Object[]{id, maSP, tenSP,loaiSanPham,moTa,trangThai?"Còn hàng":"Hết hàng"};
    }

    
}
