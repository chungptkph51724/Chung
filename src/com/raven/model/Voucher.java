/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Voucher {
    private int id;
    private String maVoucher;
    private String mota;
    private float giamGia;
    private BigDecimal giamGiaToiDa;
    private int hinhThucGiamGia;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String dieuKienApDung;
    
    private int is_deleted;

    public Voucher() {
    }

    public Voucher( String maVoucher, String mota, float giamGia, BigDecimal giamGiaToiDa, int hinhThucGiamGia, Date ngayBatDau, Date ngayKetThuc, String dieuKienApDung) {
        this.maVoucher = maVoucher;
        this.mota = mota;
        this.giamGia = giamGia;
        this.giamGiaToiDa = giamGiaToiDa;
        this.hinhThucGiamGia = hinhThucGiamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.dieuKienApDung = dieuKienApDung;
    }

    public Voucher(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }
    
    

    // Constructor cho việc cập nhật (có ID)

    public Voucher(int id, String maVoucher, String mota, float giamGia, BigDecimal giamGiaToiDa,

                   int hinhThucGiamGia, Date ngayBatDau, Date ngayKetThuc, String dieuKienApDung) {

        this.id = id;

        this.maVoucher = maVoucher;

        this.mota = mota;

        this.giamGia = giamGia;

        this.giamGiaToiDa = giamGiaToiDa;

        this.hinhThucGiamGia = hinhThucGiamGia;

        this.ngayBatDau = ngayBatDau;

        this.ngayKetThuc = ngayKetThuc;

        this.dieuKienApDung = dieuKienApDung;

    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }

    public BigDecimal getGiamGiaToiDa() {
        return giamGiaToiDa;
    }

    public void setGiamGiaToiDa(BigDecimal giamGiaToiDa) {
        this.giamGiaToiDa = giamGiaToiDa;
    }

    public int getHinhThucGiamGia() {
        return hinhThucGiamGia;
    }

    public void setHinhThucGiamGia(int hinhThucGiamGia) {
        this.hinhThucGiamGia = hinhThucGiamGia;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getDieuKienApDung() {
        return dieuKienApDung;
    }

    public void setDieuKienApDung(String dieuKienApDung) {
        this.dieuKienApDung = dieuKienApDung;
    }
    
    
    
}
