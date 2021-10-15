package com.ferdev.db;

import java.sql.*;

public class TransationJDBC {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL= "jdbc:mysql://localhost/perpustakaan";
    private static final String USER = "root";
    private static final String PASSWORD = "Begunggang1@23";
    private static Connection connection;

    static void transactionOnJDBC(){
        try {
            // load driver msql
            Class.forName(JDBC_DRIVER);

            // bikin koneksi
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            connection.setAutoCommit(false); // untuk mengaktifkan fitur transaction

            PreparedStatement ps = connection.prepareStatement(
                    "insert into buku (nama_buku, nama_pengarang, penerbit)" +
                            "values (?, ?, ?)"
            );

            for (int i = 1; i <= 100; i++) {
                ps.setString(1, "oke" + i);
                ps.setString(2, "oke" + i);
                ps.setString(3, "oke" + i);
                ps.addBatch();
            }

            ps.executeBatch();
            connection.commit(); // untuk commit transaction
            connection.setAutoCommit(true); // untuk menghidupkan kembali fitur auto commit

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
            try { // bisa di handle disini atau throw ke call method
                connection.rollback(); // jika terjadi error, transcation akan rollback
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void generateIncrementKey(){
        try {
            // load driver msql
            Class.forName(JDBC_DRIVER);

            // bikin koneksi
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            connection.setAutoCommit(false); // untuk mengaktifkan fitur transaction

            PreparedStatement ps = connection.prepareStatement(
                    "insert into buku (nama_buku, nama_pengarang, penerbit)" +
                            "values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, "bla");
            ps.setString(2, "bla");
            ps.setString(3, "bla");

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            Long id = rs.getLong(1);
            System.out.println(id);

            ps = connection.prepareStatement(
                    "select count(id_buku) as total from buku"
            );

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("total"));
            }

//            ps.executeBatch();
            connection.commit(); // untuk commit transaction
            connection.setAutoCommit(true); // untuk menghidupkan kembali fitur auto commit

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // transactionOnJDBC();

        // generate increment keys
        generateIncrementKey();


    }
}
