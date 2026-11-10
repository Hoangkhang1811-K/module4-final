package com.codegym.finalmodule4.web;

import com.codegym.finalmodule4.model.LoaiDichVu;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class GiaoDichForm {
    @NotBlank(message = "Vui lòng nhập mã giao dịch.")
    @Pattern(regexp = "MGD-[0-9]{4}", message = "Mã giao dịch phải có dạng MGD-XXXX (X là chữ số).")
    private String maGiaoDich;

    @NotBlank(message = "Vui lòng chọn khách hàng.")
    private String maKhachHang;

    @NotNull(message = "Vui lòng chọn loại dịch vụ.")
    private LoaiDichVu loaiDichVu;

    @NotNull(message = "Vui lòng chọn ngày giao dịch.")
    @Future(message = "Ngày giao dịch phải sau ngày hiện tại.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate ngayGiaoDich;

    @NotNull(message = "Vui lòng nhập đơn giá.")
    @Digits(integer = 17, fraction = 2, message = "Đơn giá phải là số hợp lệ.")
    @DecimalMin(value = "500000.00", inclusive = false, message = "Đơn giá phải lớn hơn 500.000 VND/m².")
    private BigDecimal donGia;

    @NotNull(message = "Vui lòng nhập diện tích.")
    @Digits(integer = 10, fraction = 2, message = "Diện tích phải là số hợp lệ.")
    @DecimalMin(value = "20.00", inclusive = false, message = "Diện tích phải lớn hơn 20 m².")
    private BigDecimal dienTich;

    public String getMaGiaoDich() { return maGiaoDich; }
    public void setMaGiaoDich(String maGiaoDich) { this.maGiaoDich = maGiaoDich; }
    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String maKhachHang) { this.maKhachHang = maKhachHang; }
    public LoaiDichVu getLoaiDichVu() { return loaiDichVu; }
    public void setLoaiDichVu(LoaiDichVu loaiDichVu) { this.loaiDichVu = loaiDichVu; }
    public LocalDate getNgayGiaoDich() { return ngayGiaoDich; }
    public void setNgayGiaoDich(LocalDate ngayGiaoDich) { this.ngayGiaoDich = ngayGiaoDich; }
    public BigDecimal getDonGia() { return donGia; }
    public void setDonGia(BigDecimal donGia) { this.donGia = donGia; }
    public BigDecimal getDienTich() { return dienTich; }
    public void setDienTich(BigDecimal dienTich) { this.dienTich = dienTich; }
}
