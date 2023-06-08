package com.example.thuchanh.service;

import com.example.thuchanh.DAO.NhanVienDAO;
import com.example.thuchanh.model.NhanVien;
import com.example.thuchanh.model.PhongBan;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class NhanVienService {
    private static NhanVienService nhanVienService;
    private static PhongBanService phongBanService;
    private final NhanVienDAO nhanVienDAO;

    private NhanVienService() {
        nhanVienDAO = new NhanVienDAO();
        phongBanService = PhongBanService.getInstance();
    }

    public static NhanVienService getInstance() {
        if (nhanVienService == null) {
            nhanVienService = new NhanVienService();
        }
        return nhanVienService;
    }

    public List<NhanVien> displayNhanVien() {
        return nhanVienDAO.displayAll();
    }

    public NhanVien getById(int id) {
        return nhanVienDAO.displayById(id);
    }

    public void deleteById(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        nhanVienDAO.deleteById(id);
    }

    public boolean checkById(int id) {
        NhanVien nhanVien = nhanVienDAO.displayById(id);
        return nhanVien != null;
    }

    public void save(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int idDepartment = Integer.parseInt(request.getParameter("idDepartment"));
        PhongBan phongBan = phongBanService.getById(idDepartment);
        if (id != null) {
            int idUpdate = Integer.parseInt(id);
            nhanVienDAO.updateNhanVien(new NhanVien(idUpdate, name, email, address, phoneNumber, salary, phongBan));
        } else {
            nhanVienDAO.addNhanVien(new NhanVien(name, email, address, phoneNumber, salary, phongBan));
        }
    }
}
