package data;


import java.sql.*;

public class Conexion {
    private static final String SQL_CONEXION = "jdbc:mysql://localhost:3306/inegi?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String SQL_USER = "PROFESORA";
    private static final String SQL_PASSWORD = "";
    
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Connection conection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conection = DriverManager.getConnection(SQL_CONEXION, SQL_USER, SQL_PASSWORD);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return conection;
    }
    
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public static void close(Statement stmt) throws SQLException{
        stmt.close();
    }
    
    public static void close(PreparedStatement stmt) throws SQLException{
        stmt.close();
    }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }    
    
}
