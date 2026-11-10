package com.codegym.finalmodule4.service;

import com.codegym.finalmodule4.model.KhachHang;
import com.codegym.finalmodule4.repository.GiaoDichRepository;
import com.codegym.finalmodule4.repository.KhachHangRepository;
import com.codegym.finalmodule4.web.KhachHangForm;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class KhachHangService {
    private final KhachHangRepository khachHangRepository;
    private final GiaoDichRepository giaoDichRepository;

    public KhachHangService(KhachHangRepository khachHangRepository, GiaoDichRepository giaoDichRepository) {
        this.khachHangRepository = khachHangRepository;
        this.giaoDichRepository = giaoDichRepository;
    }

    public List<KhachHang> danhSach() {
        return khachHangRepository.findAll(Sort.by(Sort.Direction.ASC, "tenKhachHang"));
    }

    public boolean daTonTai(String maKhachHang) {
        return maKhachHang != null && khachHangRepository.existsById(maKhachHang.trim());
    }

    @Transactional
    public void taoMoi(KhachHangForm form) {
        if (daTonTai(form.getMaKhachHang())) {
            throw new MaKhachHangDaTonTaiException();
        }
        khachHangRepository.save(new KhachHang(
                form.getMaKhachHang().trim(),
                form.getTenKhachHang().trim(),
                form.getSoDienThoai().trim(),
                form.getEmail().trim()));
    }

    @Transactional
    public KetQuaXoaKhachHang xoa(String maKhachHang) {
        if (!khachHangRepository.existsById(maKhachHang)) {
            return KetQuaXoaKhachHang.KHONG_TON_TAI;
        }
        if (giaoDichRepository.existsByKhachHang_MaKhachHang(maKhachHang)) {
            return KetQuaXoaKhachHang.DANG_DUOC_SU_DUNG;
        }
        khachHangRepository.deleteById(maKhachHang);
        return KetQuaXoaKhachHang.DA_XOA;
    }
}
