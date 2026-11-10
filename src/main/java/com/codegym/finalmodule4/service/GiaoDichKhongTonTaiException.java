package com.codegym.finalmodule4.service;

public class GiaoDichKhongTonTaiException extends RuntimeException {
    public GiaoDichKhongTonTaiException(String maGiaoDich) {
        super("Không tìm thấy giao dịch " + maGiaoDich);
    }
}
