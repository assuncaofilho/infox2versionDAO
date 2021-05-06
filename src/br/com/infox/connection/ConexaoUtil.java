package br.com.infox.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class ConexaoUtil { // abstract não permite instanciação da classe

    private static Connection conn; // static -> uma única versão será compartilhada com as demais entidades;

    public synchronized static Connection getConnection() { // evita que threads acessem ao mesmo tempo e instanciem mais
                                                            // mais de 1 objeto Connection;
        if (conn == null) {
            Properties props = loadProperties();
            String url = props.getProperty("dburl");
            String driver = props.getProperty("driver");
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, props);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
