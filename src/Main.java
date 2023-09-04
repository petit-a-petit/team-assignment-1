

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException {

        Properties prop = new Properties();
        try{
            Connection connection = getConnection(prop);

            int pageId = 4;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PAGE WHERE ID = ?");
            preparedStatement.setInt(1, pageId);
            ResultSet resultSet = preparedStatement.executeQuery();


            if(resultSet.next()){

                Page page = new Page(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));

                List<Integer> subPages = new ArrayList<>();

                List<Integer> breadCrumbs = new ArrayList<>();

                Response response = new Response(200, page, subPages, breadCrumbs);

                printResult(response);

            }else{
                Response response = new Response(404);

                printResult(response);
            }

            resultSet.close();;
            preparedStatement.close();
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

    private static void printResult(Response response) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String responseJson = gson.toJson(response);
        System.out.println(responseJson);
    }
}