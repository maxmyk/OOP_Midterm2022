//package com.example.demo.Magic;
//
//import com.example.demo.Parsers.CompanyInfo;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class DBHandler {
//    private Connection connect() {
//        String url = "jdbc:sqlite:identifier.sqlite";
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return conn;
//    }
//
//
//    public void insert(CompanyInfo info) {
//        String sql = "INSERT INTO companies(url, twitter, facebook, logo, icon, employees, address) VALUES(?,?,?,?,?,?,?)";
//
//        try{
//            Connection conn = this.connect();
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//
////            pstmt.setString(1, name);
////            pstmt.setDouble(2, capacity);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public static void main(String[] args) {
//
//        DBHandler app = new DBHandler();
//        // insert three new rows
//        CompanyInfo info = new CompanyInfo();
//        app.insert(info);
//    }
//}
