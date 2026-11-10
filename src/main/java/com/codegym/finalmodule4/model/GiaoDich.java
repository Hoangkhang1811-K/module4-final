package com.codegym.finalmodule4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "giao_dich")
public class GiaoDich {
    @Id
    @Column(name = "ma_giao_dich", length = 8)
    private String maGiaoDich;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_khach_hang", nullable = false)
    private KhachHang khachHang;

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_dich_vu", nullable = false, length = 20)
    private LoaiDichVu loaiDichVu;

    @Column(name = "ngay_giao_dich", nullable = false)
    private LocalDate ngayGiaoDich;

    @Column(name = "don_gia", nullable = false, precision = 19, scale = 2)
    private BigDecimal donGia;

    @Column(name = "dien_tich", nullable = false, precision = 12, scale = 2)
    private BigDecimal dienTich;

    protected GiaoDich() {
    }

    public GiaoDich(String maGiaoDich, KhachHang khachHang, LoaiDichVu loaiDichVu,
                    LocalDate ngayGiaoDich, BigDecimal donGia, BigDecimal dienTich) {
        this.maGiaoDich = maGiaoDich;
        this.khachHang = khachHang;
        this.loaiDichVu = loaiDichVu;
        this.ngayGiaoDich = ngayGiaoDich;
        this.donGia = donGia;
        this.dienTich = dienTich;
    }

    public String getMaGiaoDich() { return maGiaoDich; }
    public KhachHang getKhachHang() { return khachHang; }
    public LoaiDichVu getLoaiDichVu() { return loaiDichVu; }
    public LocalDate getNgayGiaoDich() { return ngayGiaoDich; }
    public BigDecimal getDonGia() { return donGia; }
    public BigDecimal getDienTich() { return dienTich; }
}
