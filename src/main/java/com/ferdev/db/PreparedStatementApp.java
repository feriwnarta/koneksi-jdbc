package com.ferdev.db;

import java.sql.*;

public class PreparedStatementApp {
    // contoh penggunaan prepared statement

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL= "jdbc:mysql://localhost/belajar_mysql";
    private static final String USER = "root";
    private static final String PASSWORD = "Begunggang1@23";

    static void preparedStatement(){
        Connection connection;

        try {
            // load driver msql
            Class.forName(JDBC_DRIVER);

            // bikin koneksi
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // tidak akan pakai statement tapi akan pakai PreparedStatement
            PreparedStatement ps = connection.prepareStatement(
                    "select * from produk where id = ? "
            );
            ps.setString(1, "P0003");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("id : " + rs.getString("id"));
                System.out.println("nama produk : " + rs.getString("nama"));
            }

            rs.close();
            connection.close();
            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        preparedStatement();


    }
}
