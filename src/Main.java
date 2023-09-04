

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Stack;

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

                List<Integer> subPages = getSubPages(connection, pageId, preparedStatement);

                List<Integer> breadCrumbs = getBreadCrumbs(connection, pageId, resultSet);

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

    private static List<Integer> getSubPages(Connection connection, int pageId, PreparedStatement preparedStatement) throws SQLException {
        List<Integer> subPages = new ArrayList<>();

        PreparedStatement stmt1 = connection.prepareStatement("SELECT * FROM page WHERE parent_page_id = ?");
        stmt1.setInt(1, pageId);
        ResultSet rs1 = stmt1.executeQuery();

        while(rs1.next()){
            subPages.add(rs1.getInt(1));
        }

        stmt1.close();
        rs1.close();

        return subPages;
    }

    private static List<Integer> getBreadCrumbs(Connection connection, int pageId, ResultSet resultSet) throws SQLException {
        //3. bread crumbs 확인
        int parentId = resultSet.getInt(4);
        List<Integer> breadCrumbs = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.add(pageId);
        PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM PAGE WHERE ID = ?");
        while(true){
            stmt2.setInt(1, parentId);
            ResultSet rs2 = stmt2.executeQuery();
            if(rs2.next()){
                stack.push(rs2.getInt(1));
                parentId = rs2.getInt(4);
            }else{
                while(!stack.isEmpty()){
                    breadCrumbs.add(stack.pop());
                }
                break;
            }
            rs2.close();
        }
        stmt2.close();
        return breadCrumbs;
    }

    private static void printResult(Response response) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String responseJson = gson.toJson(response);
        System.out.println(responseJson);
    }
}