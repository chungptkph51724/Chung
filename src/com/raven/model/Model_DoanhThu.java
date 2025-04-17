/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

public class Model_DoanhThu {
    private String thang;
    private int soLuong;
      private String maSanPham;
    private String tenSanPham;

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Model_DoanhThu(int soLuong, String maSanPham, String tenSanPham) {
        this.soLuong = soLuong;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
    }
    public Model_DoanhThu(String thang, int soLuong) {
        this.thang = thang;
        this.soLuong = soLuong;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public Object  todata_SanPham_DT(){
        return new Object[]{maSanPham,tenSanPham,soLuong};
    }
    
}
