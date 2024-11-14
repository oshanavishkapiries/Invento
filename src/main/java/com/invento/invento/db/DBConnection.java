package com.invento.invento.db;

import lombok.Getter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DBConnection {

        private static DBConnection dbConnection;

        private final Connection connection;
        private DBConnection() throws SQLException {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://sql12.freesqldatabase.com/sql12744480",
                    "sql12744480",
                    "zfzuqInHN6"
            );
        }
        public static DBConnection getInstance() throws SQLException {
            if (dbConnection==null){
                dbConnection=new DBConnection();
            }
            return dbConnection;
        }

}
