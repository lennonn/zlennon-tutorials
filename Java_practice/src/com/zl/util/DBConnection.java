package com.zl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection conn = null;

    /*
     * һ��ȡ�����ӵľ�̬����
     */
    public static Connection getConnection() {
        try {
            // ��������
             Class.forName("oracle.jdbc.driver.OracleDriver");
            // ��������
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@127.0.0.1:1521:orcl", "SCOTT", "tiger");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void main(String[] args) {
		System.out.println(DBConnection.getConnection().getClass());
	}
}
