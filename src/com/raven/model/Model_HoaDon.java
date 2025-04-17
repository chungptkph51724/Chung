package com.raven.model;

public class Model_HoaDon {

    private String maHoaDon;
    private String maKhachHang;
    private String tenKhachHang;
    private String soDienThoai;
    private String idNhanVien;
    private String ngayThanhToan;
    private String maVoucher;
    private double tongTienBanDau;
    private double tongKhuyenMai;
    private double tongTienSauKM;
    private boolean trangThai;

    // Dùng cho hiển thị index hoặc STT nếu cần
    private int stt;

    public Model_HoaDon() {
    }

    public Model_HoaDon(String maHoaDon, String ngayThanhToan, boolean trangThai) {
        this.maHoaDon = maHoaDon;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
    }
    

    public Model_HoaDon(String maHoaDon, String idNhanVien, String ngayThanhToan, boolean trangThai) {
        this.maHoaDon = maHoaDon;
        this.idNhanVien = idNhanVien;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
    }
    

    // Constructor đầy đủ
    public Model_HoaDon(String maHoaDon, String maKhachHang, String tenKhachHang, String soDienThoai,
            String idNhanVien, String ngayThanhToan, String maVoucher,
            double tongTienBanDau, double tongKhuyenMai, double tongTienSauKM, boolean trangThai) {
        this.maHoaDon = maHoaDon;
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.idNhanVien = idNhanVien;
        this.ngayThanhToan = ngayThanhToan;
        this.maVoucher = maVoucher;
        this.tongTienBanDau = tongTienBanDau;
        this.tongKhuyenMai = tongKhuyenMai;
        this.tongTienSauKM = tongTienSauKM;
        this.trangThai = trangThai;
    }

    // Constructor dùng khi chỉ cần: maHoaDon, idNV, ngayTT, trạng thái, stt
    public Model_HoaDon(String maHoaDon, String idNhanVien, String ngayThanhToan, boolean trangThai, int stt) {
        this.maHoaDon = maHoaDon;
        this.idNhanVien = idNhanVien;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
        this.stt = stt;
    }

    // Getters and Setters
    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public double getTongTienBanDau() {
        return tongTienBanDau;
    }

    public void setTongTienBanDau(double tongTienBanDau) {
        this.tongTienBanDau = tongTienBanDau;
    }

    public double getTongKhuyenMai() {
        return tongKhuyenMai;
    }

    public void setTongKhuyenMai(double tongKhuyenMai) {
        this.tongKhuyenMai = tongKhuyenMai;
    }

    public double getTongTienSauKM() {
        return tongTienSauKM;
    }

    public void setTongTienSauKM(double tongTienSauKM) {
        this.tongTienSauKM = tongTienSauKM;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public Object[] toDataRow() {
        return new Object[]{
            maHoaDon, maKhachHang, tenKhachHang, soDienThoai, ngayThanhToan, tongTienBanDau, tongKhuyenMai,
            maVoucher == null ? "" : maVoucher,
            tongTienSauKM,
            trangThai ? "Đã thanh toán" : "Chờ thanh toán"
        };
    }

    public Object[] toDataHoaDonCho() {
        return new Object[]{maHoaDon, ngayThanhToan, trangThai ? "Đã Thanh Toán" : "Chờ Thanh Toan"};
    }

    public double getPhanTramGiam() {
        if (tongTienBanDau == 0) {
            return 0;
        }
        return (tongKhuyenMai / tongTienBanDau) * 100;
    }
}
