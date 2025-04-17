/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

/**
 *
 * @author macbook
 */
public class Model_NV {
    private String maNV;
    private String tenNV;
    private String sdt;
    private String TK;
    private String MK;
    private boolean TrangThai;
    private String hinhanh;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTK() {
        return TK;
    }

    public void setTK(String TK) {
        this.TK = TK;
    }

    public String getMK() {
        return MK;
    }

    public void setMK(String MK) {
        this.MK = MK;
    }

    public Model_NV(String maNV, String tenNV, String sdt, String TK, String MK, boolean TrangThai) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.sdt = sdt;
        this.TK = TK;
        this.MK = MK;
        this.TrangThai = TrangThai;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }



    public Model_NV() {
    }
    
    
  
    
}
