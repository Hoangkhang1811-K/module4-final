package com.codegym.finalmodule4.web;

import com.codegym.finalmodule4.service.KhachHangService;
import com.codegym.finalmodule4.service.KetQuaXoaKhachHang;
import com.codegym.finalmodule4.service.MaKhachHangDaTonTaiException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class KhachHangController {
    private final KhachHangService khachHangService;

    public KhachHangController(KhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

    @GetMapping("/khach-hang")
    public String danhSach(@RequestParam(required = false) String ketQua, Model model) {
        model.addAttribute("khachHangList", khachHangService.danhSach());
        if ("them-moi".equals(ketQua)) {
            model.addAttribute("thongBao", "Đã tạo khách hàng mới thành công.");
        } else if ("da-xoa".equals(ketQua)) {
            model.addAttribute("thongBao", "Đã xóa khách hàng thành công.");
        } else if ("dang-su-dung".equals(ketQua)) {
            model.addAttribute("loiThongBao", "Không thể xóa khách hàng đang có giao dịch. Hãy xóa các giao dịch liên quan trước.");
        } else if ("khong-ton-tai".equals(ketQua)) {
            model.addAttribute("loiThongBao", "Khách hàng không tồn tại hoặc đã được xóa.");
        }
        return "khach-hang/danh-sach";
    }

    @GetMapping("/khach-hang/them-moi")
    public String hienThiForm(Model model) {
        model.addAttribute("khachHangForm", new KhachHangForm());
        return "khach-hang/them-moi";
    }

    @PostMapping("/khach-hang/them-moi")
    public String taoMoi(@Valid @ModelAttribute KhachHangForm khachHangForm,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("maKhachHang")
                && khachHangService.daTonTai(khachHangForm.getMaKhachHang())) {
            bindingResult.rejectValue("maKhachHang", "duplicate", "Mã khách hàng này đã tồn tại.");
        }
        if (bindingResult.hasErrors()) {
            return "khach-hang/them-moi";
        }

        try {
            khachHangService.taoMoi(khachHangForm);
        } catch (MaKhachHangDaTonTaiException e) {
            bindingResult.rejectValue("maKhachHang", "duplicate", "Mã khách hàng này đã tồn tại.");
            return "khach-hang/them-moi";
        }
        redirectAttributes.addAttribute("ketQua", "them-moi");
        return "redirect:/khach-hang";
    }

    @PostMapping("/khach-hang/{maKhachHang}/xoa")
    public String xoa(@PathVariable String maKhachHang, RedirectAttributes redirectAttributes) {
        KetQuaXoaKhachHang ketQua = khachHangService.xoa(maKhachHang);
        redirectAttributes.addAttribute("ketQua", switch (ketQua) {
            case DA_XOA -> "da-xoa";
            case DANG_DUOC_SU_DUNG -> "dang-su-dung";
            case KHONG_TON_TAI -> "khong-ton-tai";
        });
        return "redirect:/khach-hang";
    }
}
