package com.ferdev.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class KoneksiDatabase {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/perpustakaan";
    private static final String USER = "root";
    private static final String PASSWORD = "Begunggang1@23";

    public static void konekDB(){
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try {
            // register driver database yang digunakan
            Class.forName(JDBC_DRIVER);

            // bikin koneksi
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // bikin objek statement untuk menjalankan query
            statement = connection.createStatement();

            // query
            String query = "select * from buku";

            // execute query dan simpan di result set
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("ID BUKU        : " + resultSet.getInt("id_buku"));
                System.out.println("NAMA BUKU      : " + resultSet.getString("nama_buku"));
                System.out.println("NAMA PENGARANG : " + resultSet.getString("nama_pengarang"));
                System.out.println("PENERBIT       : " + resultSet.getString("penerbit"));
                System.out.println("");
            }

            String insertBukuBaru = "insert into buku (nama_buku, nama_pengarang, penerbit) values ('begunggang', 'begenggong', 'gunggangs')";

            boolean succes = statement.execute(insertBukuBaru);

            if(succes) {
                System.out.println("Query berhasil dijalankan");
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    System.out.println("ID BUKU        : " + resultSet.getInt("id_buku"));
                    System.out.println("NAMA BUKU      : " + resultSet.getString("nama_buku"));
                    System.out.println("NAMA PENGARANG : " + resultSet.getString("nama_pengarang"));
                    System.out.println("PENERBIT       : " + resultSet.getString("penerbit"));
                    System.out.println("");
                }

            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        konekDB();
    }


}
