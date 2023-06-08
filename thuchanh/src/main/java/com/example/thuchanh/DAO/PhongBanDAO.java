package com.example.thuchanh.DAO;

import com.example.thuchanh.DAO.connection.MyConnection;
import com.example.thuchanh.model.PhongBan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhongBanDAO {
    private final Connection connection;

    private final String SELECT_ALL = "select * from phong_ban;";
    private final String SELECT_BY_ID = "select * from  phong_ban where id = ?;";

    public PhongBanDAO() {
        connection = MyConnection.getConnection();
    }

    public List<PhongBan> displayAll() {
        List<PhongBan> phongBanList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                PhongBan phongBan = new PhongBan(id, name);
                phongBanList.add(phongBan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phongBanList;
    }

    public PhongBan displayById(int id) {
        PhongBan phongBan = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                phongBan = new PhongBan(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phongBan;
    }
}
