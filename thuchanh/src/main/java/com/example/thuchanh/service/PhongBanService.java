package com.example.thuchanh.service;

import com.example.thuchanh.DAO.PhongBanDAO;
import com.example.thuchanh.model.PhongBan;

import java.util.List;

public class PhongBanService {
    private final PhongBanDAO phongBanDAO;
    private static PhongBanService phongBanService;

    private PhongBanService() {
        phongBanDAO =new PhongBanDAO();
    }

    public static PhongBanService getInstance() {
        if (phongBanService == null) {
            phongBanService = new PhongBanService();
        }
        return phongBanService;
    }

    public List<PhongBan> displayAll() {
        return phongBanDAO.displayAll();
    }


    public PhongBan getById(int id) {
        return phongBanDAO.displayById(id);
    }

    public boolean checkById(int id) {
        PhongBan phongBan =phongBanService.getById(id);
        return phongBan != null;
    }

}
