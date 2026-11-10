package com.codegym.finalmodule4.service;

import com.codegym.finalmodule4.model.GiaoDich;
import com.codegym.finalmodule4.model.KhachHang;
import com.codegym.finalmodule4.model.LoaiDichVu;
import com.codegym.finalmodule4.repository.GiaoDichRepository;
import com.codegym.finalmodule4.repository.KhachHangRepository;
import com.codegym.finalmodule4.web.GiaoDichForm;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GiaoDichService {
    private final GiaoDichRepository giaoDichRepository;
    private final KhachHangRepository khachHangRepository;

    public GiaoDichService(GiaoDichRepository giaoDichRepository, KhachHangRepository khachHangRepository) {
        this.giaoDichRepository = giaoDichRepository;
        this.khachHangRepository = khachHangRepository;
    }

    public List<GiaoDich> timKiem(String tenKhachHang, LoaiDichVu loaiDichVu) {
        boolean coTen = tenKhachHang != null && !tenKhachHang.isBlank();
        if (coTen && loaiDichVu != null) {
            return giaoDichRepository.findByKhachHang_TenKhachHangContainingIgnoreCaseAndLoaiDichVu(tenKhachHang.trim(), loaiDichVu);
        }
        if (coTen) return giaoDichRepository.findByKhachHang_TenKhachHangContainingIgnoreCase(tenKhachHang.trim());
        if (loaiDichVu != null) return giaoDichRepository.findByLoaiDichVu(loaiDichVu);
        return giaoDichRepository.findAll();
    }

    public long demTatCa() {
        return giaoDichRepository.count();
    }

    public boolean daTonTai(String maGiaoDich) {
        return maGiaoDich != null && giaoDichRepository.existsById(maGiaoDich.trim());
    }

    @Transactional
    public void taoMoi(GiaoDichForm form) {
        if (giaoDichRepository.existsById(form.getMaGiaoDich())) {
            throw new MaGiaoDichDaTonTaiException();
        }
        KhachHang khachHang = khachHangRepository.findById(form.getMaKhachHang())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khách hàng đã chọn."));
        giaoDichRepository.save(new GiaoDich(form.getMaGiaoDich().trim(), khachHang,
                form.getLoaiDichVu(), form.getNgayGiaoDich(), form.getDonGia(), form.getDienTich()));
    }

    public GiaoDich timTheoMa(String maGiaoDich) {
        return giaoDichRepository.findById(maGiaoDich)
                .orElseThrow(() -> new GiaoDichKhongTonTaiException(maGiaoDich));
    }

    @Transactional
    public void xoa(String maGiaoDich) {
        if (!giaoDichRepository.existsById(maGiaoDich)) throw new GiaoDichKhongTonTaiException(maGiaoDich);
        giaoDichRepository.deleteById(maGiaoDich);
    }
}
