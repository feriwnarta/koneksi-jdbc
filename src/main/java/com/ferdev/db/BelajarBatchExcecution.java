package com.ferdev.db;

import javax.imageio.stream.ImageInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BelajarBatchExcecution {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL= "jdbc:mysql://localhost/perpustakaan";
    private static final String USER = "root";
    private static final String PASSWORD = "Begunggang1@23";

    static void batchExcutionTest(){
        Connection connection;

        try {
            // load driver msql
            Class.forName(JDBC_DRIVER);

            // bikin koneksi
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            PreparedStatement ps = connection.prepareStatement(
                "insert into buku (nama_buku, nama_pengarang, penerbit)" +
                        "values (?, ?, ?)"
            );

            for (int i = 1; i <= 100; i++) {
                ps.setString(1, "i");
                ps.setString(2, "i");
                ps.setString(3, "i");
                ps.addBatch();
            }

            ps.executeBatch();

            ResultSet rs = ps.executeQuery(
                    "select * from buku"
            );

            while (rs.next()) {
                System.out.println("nama buku : " + rs.getString("nama_buku"));
                System.out.println("nama pengarang : " + rs.getString("nama_pengarang"));
                System.out.println("nama penerbt : " + rs.getString("penerbit"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        batchExcutionTest();
    }

}
