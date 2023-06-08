package com.example.thuchanh.model;

public class NhanVien {
    private int id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private double salary;
    private PhongBan department;

    public NhanVien() {
    }

    public NhanVien(int id, String name, String email, String address, String phoneNumber, double salary, PhongBan department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.department = department;
    }

    public NhanVien(String name, String email, String address, String phoneNumber, double salary, PhongBan department) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.department = department;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public PhongBan getDepartment() {
        return department;
    }

    public void setDepartment(PhongBan department) {
        this.department = department;
    }
}
