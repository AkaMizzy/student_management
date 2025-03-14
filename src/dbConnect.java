import java.sql.*;

public class dbConnect {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String db="etudata", user = "root", pass = "";
        String url = "jdbc:mysql://localhost:3306/"+db;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url,user,pass);
        return conn;
    }
}
