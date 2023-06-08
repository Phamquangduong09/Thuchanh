package com.example.thuchanh.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.thuchanh.DAO.connection.MyConnection;
import com.example.thuchanh.model.NhanVien;
import com.example.thuchanh.model.PhongBan;
import com.example.thuchanh.service.PhongBanService;

public class NhanVienDAO {

    private final Connection connection;
    private final PhongBanService phongBanService = PhongBanService.getInstance();

    private final String SELECT_ALL = "select * from nhan_vien;";
    private final String SELECT_BY_ID = "select * from nhan_vien where id = ?;";
    private final String INSERT_INTO = "insert into nhan_vien(name,email,address,phone_number,salary,id_department) value (?,?,?,?,?,?);";
    private final String UPDATE_BY_ID = "update nhan_vien set name = ? ,  email = ? ,  address = ?, phone_number ,  email = ?,  id_department = ? where id = ?;";
    private final String DELETE_BY_ID = "delete from nhan_vien where id = ?";


    public NhanVienDAO() {
        connection = MyConnection.getConnection();
    }

    public List<NhanVien> displayAll() {
        List<NhanVien> nhanVienList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                double salary = resultSet.getDouble("salary");
                int idDepartment = resultSet.getInt("id_department");
                PhongBan phongBan = phongBanService.getById(idDepartment);
                NhanVien nhanVien = new NhanVien(id, name, email, address, phoneNumber, salary, phongBan);
                nhanVienList.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVienList;
    }

    public void addNhanVien(NhanVien nhanVien) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1, nhanVien.getName());
            preparedStatement.setString(2, nhanVien.getEmail());
            preparedStatement.setString(3, nhanVien.getAddress());
            preparedStatement.setString(4, nhanVien.getPhoneNumber());
            preparedStatement.setDouble(5, nhanVien.getSalary());
            preparedStatement.setInt(6, nhanVien.getDepartment().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNhanVien(NhanVien nhanVien) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setString(1, nhanVien.getName());
            preparedStatement.setString(2, nhanVien.getEmail());
            preparedStatement.setString(3, nhanVien.getAddress());
            preparedStatement.setString(4, nhanVien.getPhoneNumber());
            preparedStatement.setDouble(5, nhanVien.getSalary());
            preparedStatement.setInt(6, nhanVien.getDepartment().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public NhanVien displayById(int id) {
        NhanVien nhanVien = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String numberPhone = resultSet.getString("number_phone");
                double salary = resultSet.getDouble("salary");
                int idDepartment = resultSet.getInt("id_department");
                PhongBan phongBan = phongBanService.getById(idDepartment);
                nhanVien = new NhanVien(id, name, email, address, numberPhone, salary, phongBan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVien;
    }
}
