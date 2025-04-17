/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

/**
 *
 * @author Admin
 */
public class Model_HDCT {
    private String maHDCT;
    private String maHD, maSP;
    private int solg;
    private double dongia;

    public Model_HDCT(String maHD, String maSP, int solg, double dongia) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.solg = solg;
        this.dongia = dongia;
    }
    

    public Model_HDCT(String maHDCT, String maHD, String maSP, int solg, double dongia) {
        this.maHDCT = maHDCT;
        this.maHD = maHD;
        this.maSP = maSP;
        this.solg = solg;
        this.dongia = dongia;
    }
    

    public Model_HDCT() {
    }

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    

    public int getSolg() {
        return solg;
    }

    public void setSolg(int solg) {
        this.solg = solg;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }
    public Object[] ToDataRow(){
        return new Object[] {this.getMaHDCT(), this.getMaHD(), this.getMaSP(), this.getDongia(), this.getSolg()};
    }
    
    public double getThanhTien() {
    return dongia * solg;
}
}
