package com.codegym.finalmodule4.web;

import com.codegym.finalmodule4.model.LoaiDichVu;
import com.codegym.finalmodule4.repository.KhachHangRepository;
import com.codegym.finalmodule4.service.GiaoDichService;
import com.codegym.finalmodule4.service.MaGiaoDichDaTonTaiException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GiaoDichController {
    private final GiaoDichService giaoDichService;
    private final KhachHangRepository khachHangRepository;

    public GiaoDichController(GiaoDichService giaoDichService, KhachHangRepository khachHangRepository) {
        this.giaoDichService = giaoDichService;
        this.khachHangRepository = khachHangRepository;
    }

    @ModelAttribute("loaiDichVuOptions")
    public LoaiDichVu[] loaiDichVuOptions() { return LoaiDichVu.values(); }

    @GetMapping("/")
    public String danhSach(@RequestParam(required = false) String tenKhachHang,
                           @RequestParam(required = false) LoaiDichVu loaiDichVu,
                           @RequestParam(required = false) String ketQua,
                           Model model) {
        model.addAttribute("giaoDichList", giaoDichService.timKiem(tenKhachHang, loaiDichVu));
        model.addAttribute("tenKhachHang", tenKhachHang == null ? "" : tenKhachHang);
        model.addAttribute("loaiDichVu", loaiDichVu);
        model.addAttribute("tongSoGiaoDich", giaoDichService.demTatCa());
        if ("them-moi".equals(ketQua)) {
            model.addAttribute("thongBao", "Đã thêm giao dịch thành công.");
        } else if ("xoa".equals(ketQua)) {
            model.addAttribute("thongBao", "Giao dịch đã được xóa.");
        }
        return "giao-dich/danh-sach";
    }

    @GetMapping("/giao-dich/them-moi")
    public String hienThiForm(Model model) {
        model.addAttribute("giaoDichForm", new GiaoDichForm());
        model.addAttribute("khachHangList", khachHangRepository.findAll());
        return "giao-dich/them-moi";
    }

    @PostMapping("/giao-dich/them-moi")
    public String taoMoi(@Valid @ModelAttribute GiaoDichForm giaoDichForm,
                         BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("maGiaoDich") && giaoDichService.daTonTai(giaoDichForm.getMaGiaoDich())) {
            bindingResult.rejectValue("maGiaoDich", "duplicate", "Mã giao dịch này đã tồn tại.");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("khachHangList", khachHangRepository.findAll());
            return "giao-dich/them-moi";
        }
        try {
            giaoDichService.taoMoi(giaoDichForm);
        } catch (MaGiaoDichDaTonTaiException e) {
            bindingResult.rejectValue("maGiaoDich", "duplicate", "Mã giao dịch này đã tồn tại.");
            model.addAttribute("khachHangList", khachHangRepository.findAll());
            return "giao-dich/them-moi";
        }
        redirectAttributes.addAttribute("ketQua", "them-moi");
        return "redirect:/";
    }

    @GetMapping("/giao-dich/{maGiaoDich}")
    public String chiTiet(@PathVariable String maGiaoDich, Model model) {
        model.addAttribute("giaoDich", giaoDichService.timTheoMa(maGiaoDich));
        return "giao-dich/chi-tiet";
    }

    @PostMapping("/giao-dich/{maGiaoDich}/xoa")
    public String xoa(@PathVariable String maGiaoDich, RedirectAttributes redirectAttributes) {
        giaoDichService.xoa(maGiaoDich);
        redirectAttributes.addAttribute("ketQua", "xoa");
        return "redirect:/";
    }
}
