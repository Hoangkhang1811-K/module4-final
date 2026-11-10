package com.codegym.finalmodule4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @Column(name = "ma_khach_hang", length = 20)
    private String maKhachHang;

    @Column(name = "ten_khach_hang", nullable = false, length = 120)
    private String tenKhachHang;

    @Column(name = "so_dien_thoai", nullable = false, length = 20)
    private String soDienThoai;

    @Column(nullable = false, length = 120)
    private String email;

    protected KhachHang() {
    }

    public KhachHang(String maKhachHang, String tenKhachHang, String soDienThoai, String email) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public String getMaKhachHang() { return maKhachHang; }
    public String getTenKhachHang() { return tenKhachHang; }
    public String getSoDienThoai() { return soDienThoai; }
    public String getEmail() { return email; }
}
