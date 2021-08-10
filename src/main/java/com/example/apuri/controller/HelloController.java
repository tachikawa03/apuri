package com.example.apuri.controller;

import org.springframework.stereotype.Controller;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;

@Controller
@EnableAutoConfiguration
public class HelloController
{
    private static String DBNAME = "sample.db";
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Spring Boot Sample Application!";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String all() {
        String dbname = DBNAME; // 利用するデータベースファイル
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);
            System.out.println("接続成功");

            stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE products (pid INTEGER, name VARCHAR(20), price INTEGER, PRIMARY KEY (pid))");
            System.out.println("テーブル作成");

            stmt.executeUpdate("INSERT INTO products VALUES(1, 'AAA', 100)");
            stmt.executeUpdate("INSERT INTO products VALUES(2, 'BBB', 80)");
            stmt.executeUpdate("INSERT INTO products VALUES(3, 'CCC', 220)");
            System.out.println("データ挿入");

            ResultSet rs = stmt
                    .executeQuery("SELECT * FROM products WHERE price >= 100");
            System.out.println("選択");
            while (rs.next()) {
                int pid = rs.getInt("pid");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                System.out.println(pid + "\t" + name + "\t" + price);
            }
            rs.close();

            stmt.executeUpdate("DROP TABLE products");
            System.out.println("テーブル削除");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "Spring Boot Sample Application!";
    }

}
