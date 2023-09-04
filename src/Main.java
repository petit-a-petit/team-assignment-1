

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException {

        Properties prop = new Properties();
        try{
            Connection connection = getConnection(prop);
            connection.close();

        }catch (Exception e){
            System.out.print("DB 연결 실패 : " + e.getMessage());
        }
    }

    private static Connection getConnection(Properties prop) throws IOException, SQLException {
        prop.load(new FileInputStream("src/driver.properties"));

        return DriverManager.getConnection(prop.getProperty("url"),
                prop.getProperty("username"),
                prop.getProperty("password"));
    }
}