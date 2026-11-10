package com.codegym.finalmodule4.repository;

import com.codegym.finalmodule4.model.GiaoDich;
import com.codegym.finalmodule4.model.LoaiDichVu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

public interface GiaoDichRepository extends JpaRepository<GiaoDich, String> {
    boolean existsByKhachHang_MaKhachHang(String maKhachHang);

    @Override
    @EntityGraph(attributePaths = "khachHang")
    List<GiaoDich> findAll();

    @Override
    @EntityGraph(attributePaths = "khachHang")
    java.util.Optional<GiaoDich> findById(String id);

    @EntityGraph(attributePaths = "khachHang")
    List<GiaoDich> findByKhachHang_TenKhachHangContainingIgnoreCase(String tenKhachHang);

    @EntityGraph(attributePaths = "khachHang")
    List<GiaoDich> findByLoaiDichVu(LoaiDichVu loaiDichVu);

    @EntityGraph(attributePaths = "khachHang")
    List<GiaoDich> findByKhachHang_TenKhachHangContainingIgnoreCaseAndLoaiDichVu(
            String tenKhachHang, LoaiDichVu loaiDichVu);
}
