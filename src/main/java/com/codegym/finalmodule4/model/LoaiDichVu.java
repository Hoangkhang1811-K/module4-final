package com.codegym.finalmodule4.model;

public enum LoaiDichVu {
    DAT("Đất"),
    NHA_VA_DAT("Nhà và đất");

    private final String tenHienThi;

    LoaiDichVu(String tenHienThi) {
        this.tenHienThi = tenHienThi;
    }

    public String getTenHienThi() {
        return tenHienThi;
    }
}
