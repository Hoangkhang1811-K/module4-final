package com.codegym.finalmodule4.config;

import com.codegym.finalmodule4.model.GiaoDich;
import com.codegym.finalmodule4.model.KhachHang;
import com.codegym.finalmodule4.model.LoaiDichVu;
import com.codegym.finalmodule4.repository.GiaoDichRepository;
import com.codegym.finalmodule4.repository.KhachHangRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DuLieuMauConfig {
    @Bean
    CommandLineRunner taoDuLieuMau(KhachHangRepository khachHangRepository, GiaoDichRepository giaoDichRepository) {
        return args -> {
            if (khachHangRepository.count() == 0) {
                khachHangRepository.saveAll(List.of(
                        new KhachHang("KH-001", "Nguyễn Minh Anh", "0901234567", "minhanh@example.com"),
                        new KhachHang("KH-002", "Trần Quốc Bảo", "0912345678", "quocbao@example.com"),
                        new KhachHang("KH-003", "Lê Thu Hà", "0987654321", "thuha@example.com"),
                        new KhachHang("KH-004", "Phạm Gia Huy", "0934567890", "giahuy@example.com"),
                        new KhachHang("KH-005", "Võ Ngọc Lan", "0978123456", "ngoclan@example.com")
                ));
            }
            if (giaoDichRepository.count() == 0) {
                List<KhachHang> khachHangs = khachHangRepository.findAll();
                giaoDichRepository.saveAll(List.of(
                        new GiaoDich("MGD-0001", khachHangs.get(0), LoaiDichVu.NHA_VA_DAT,
                                LocalDate.now().plusDays(3), new BigDecimal("24500000"), new BigDecimal("96")),
                        new GiaoDich("MGD-0002", khachHangs.get(1), LoaiDichVu.DAT,
                                LocalDate.now().plusDays(6), new BigDecimal("12800000"), new BigDecimal("120")),
                        new GiaoDich("MGD-0003", khachHangs.get(2), LoaiDichVu.NHA_VA_DAT,
                                LocalDate.now().plusDays(10), new BigDecimal("31500000"), new BigDecimal("82.5")),
                        new GiaoDich("MGD-0004", khachHangs.get(3), LoaiDichVu.DAT,
                                LocalDate.now().plusDays(14), new BigDecimal("8900000"), new BigDecimal("150"))
                ));
            }
        };
    }
}
