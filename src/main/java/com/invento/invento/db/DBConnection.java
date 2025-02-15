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
                    "jdbc:mysql://68.183.184.162:9000/invento",
                    "oshan",
                    "85934089"
            );
        }
        public static DBConnection getInstance() throws SQLException {
            if (dbConnection==null){
                dbConnection=new DBConnection();
            }
            return dbConnection;
        }

}
//
//docker run --name mysql-container \
//        -e MYSQL_ROOT_PASSWORD=85934089 \
//        -e MYSQL_DATABASE=invento \
//        -e MYSQL_USER=oshan \
//        -e MYSQL_PASSWORD=85934089 \
//        -p 9000:3306 \
//        -d mysql:latest