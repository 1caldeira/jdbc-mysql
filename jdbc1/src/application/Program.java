package application;

import db.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn = DB.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from department");
            System.out.println();
            System.out.println("Departments:\n");
            while(rs.next()){
                System.out.println(rs.getInt("Id")+", "+ rs.getString("Name"));
            }
            rs = st.executeQuery("select * from seller");
            System.out.println();
            System.out.println("Sellers:\n");
            while(rs.next()){
                System.out.println(rs.getString("Name")+", "+ rs.getString("Email")+", ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
