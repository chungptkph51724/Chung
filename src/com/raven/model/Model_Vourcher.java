package com.raven.model;

import java.math.BigDecimal;
import java.util.Date;

public class Model_Vourcher {
    private int id;
    private String maVoucher;
    private String mota;
    private float giamGia;
    private BigDecimal giamGiaToiDa;
    private int hinhThucGiamGia;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String dieuKienApDung;
    
    private boolean trangThai;

    public Model_Vourcher() {
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Model_Vourcher(String maVoucher, String mota, float giamGia, BigDecimal giamGiaToiDa, int hinhThucGiamGia, Date ngayBatDau, Date ngayKetThuc, String dieuKienApDung) {
        this.maVoucher = maVoucher;
        this.mota = mota;
        this.giamGia = giamGia;
        this.giamGiaToiDa = giamGiaToiDa;
        this.hinhThucGiamGia = hinhThucGiamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.dieuKienApDung = dieuKienApDung;
    }
    

    public Model_Vourcher(int id, boolean trangThai) {
        this.id = id;
        this.trangThai = trangThai;
    }

    public Model_Vourcher(int id, String maVoucher, String mota, float giamGia, BigDecimal giamGiaToiDa, int hinhThucGiamGia, Date ngayBatDau, Date ngayKetThuc, String dieuKienApDung, boolean trangThai) {
        this.id = id;
        this.maVoucher = maVoucher;
        this.mota = mota;
        this.giamGia = giamGia;
        this.giamGiaToiDa = giamGiaToiDa;
        this.hinhThucGiamGia = hinhThucGiamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.dieuKienApDung = dieuKienApDung;
        this.trangThai = trangThai;
    }

    public Model_Vourcher(int id, String maVoucher, String mota, float giamGia, BigDecimal giamGiaToiDa, int hinhThucGiamGia, Date ngayBatDau, Date ngayKetThuc, String dieuKienApDung) {
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
    

    public Model_Vourcher(String maVoucher, String mota, float giamGia, BigDecimal giamGiaToiDa, int hinhThucGiamGia, Date ngayBatDau, Date ngayKetThuc, String dieuKienApDung, boolean trangThai) {
        this.maVoucher = maVoucher;
        this.mota = mota;
        this.giamGia = giamGia;
        this.giamGiaToiDa = giamGiaToiDa;
        this.hinhThucGiamGia = hinhThucGiamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.dieuKienApDung = dieuKienApDung;
        this.trangThai = trangThai;
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
