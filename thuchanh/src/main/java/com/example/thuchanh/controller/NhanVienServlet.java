package com.example.thuchanh.controller;

import com.example.thuchanh.service.NhanVienService;
import com.example.thuchanh.service.PhongBanService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "NhanVienServlet", value = "/nhanVien")
public class NhanVienServlet extends HttpServlet {

    private final NhanVienService nhanVienService = NhanVienService.getInstance();
    private final PhongBanService phongBanService = PhongBanService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createGet(request, response);
                break;
            case "update":
                updateGet(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            default:
                displayAll(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createPost(request, response);
                break;
            case "update":
                updatePost(request, response);
                break;
        }
    }

    private void displayAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("nhanVien",nhanVienService.displayNhanVien());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/home.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("phongBan", phongBanService.displayAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idDepartment = Integer.parseInt(request.getParameter("idDepartment"));
        if (phongBanService.checkById(idDepartment)) {
            nhanVienService.save(request);
            response.sendRedirect("/nhanVien");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }

    private void updateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (nhanVienService.checkById(id)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/update.jsp");
            request.setAttribute("nhanVien", nhanVienService.getById(id));
            request.setAttribute("department", phongBanService.displayAll());
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/404.jsp");
        }
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int brand = Integer.parseInt(request.getParameter("phongBan"));
        int id = Integer.parseInt(request.getParameter("id"));

        if (nhanVienService.checkById(id) && phongBanService.checkById(brand)) {
            nhanVienService.save(request);
            response.sendRedirect("/nhanVien");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        nhanVienService.deleteById(request);
        response.sendRedirect("/nhanVien");
    }
}
