package com.raven.model;

public class Model_KhachHang {
    private int id;
    private String maKhachHang;
    private String ten;
    private String soDienThoai;
    private String email;
    private String diaChi;
    private boolean gioiTinh;
    private boolean trangThai;

    public Model_KhachHang() {
    }

    
    

    public Model_KhachHang(int id, String maKhachHang, String ten, String soDienThoai, String email, String diaChi, boolean gioiTinh, boolean trangThai) {
        this.id = id;
        this.maKhachHang = maKhachHang;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public Model_KhachHang(String maKhachHang, String ten, String soDienThoai, String email, String diaChi, boolean gioiTinh) {
        this.maKhachHang = maKhachHang;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }
    

    public Model_KhachHang(String maKhachHang, String ten, String soDienThoai, String email, String diaChi, boolean gioiTinh, boolean trangThai) {
        this.maKhachHang = maKhachHang;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    // Dùng cho table hiển thị
    public Object[] toDataRow() {
        return new Object[]{
            maKhachHang, ten, soDienThoai, email, diaChi, gioiTinh ? "Nam" : "Nữ", trangThai ? "Hoạt động" : "Không hoạt động"
        };
    }
}
