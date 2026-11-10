package com.codegym.finalmodule4.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class KhachHangForm {
    @NotBlank(message = "Vui lòng nhập mã khách hàng.")
    @Pattern(regexp = "KH-[A-Za-z0-9]{3,17}", message = "Mã khách hàng cần có dạng KH-001.")
    private String maKhachHang;

    @NotBlank(message = "Vui lòng nhập tên khách hàng.")
    @Size(max = 120, message = "Tên khách hàng không được vượt quá 120 ký tự.")
    private String tenKhachHang;

    @NotBlank(message = "Vui lòng nhập số điện thoại.")
    @Size(min = 8, max = 20, message = "Số điện thoại cần có từ 8 đến 20 ký tự.")
    @Pattern(regexp = "[0-9+() .-]+", message = "Số điện thoại chỉ được chứa chữ số và ký tự + ( ) - .")
    private String soDienThoai;

    @NotBlank(message = "Vui lòng nhập email.")
    @Email(message = "Email chưa đúng định dạng.")
    @Size(max = 120, message = "Email không được vượt quá 120 ký tự.")
    private String email;

    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String maKhachHang) { this.maKhachHang = maKhachHang; }
    public String getTenKhachHang() { return tenKhachHang; }
    public void setTenKhachHang(String tenKhachHang) { this.tenKhachHang = tenKhachHang; }
    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
