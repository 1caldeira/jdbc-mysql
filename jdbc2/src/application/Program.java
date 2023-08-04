package application;
import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO seller"
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES "
                    + "(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "Carl Purple");
            st.setString(2, "carlpurple@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("14/09/1985").getTime()));
            st.setDouble(4, 3000);
            st.setInt(5,2);
            st = conn.prepareStatement("insert into department (Name) values ('D1'), ('D2')", Statement.RETURN_GENERATED_KEYS);
            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                rs = st.getGeneratedKeys();
                while(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = "+id);
                }
            }else{
                System.out.println("No rows affected!");
            }

            rs = st.executeQuery("select * from seller");
            System.out.println();
            System.out.println("Sellers:\n");
            while(rs.next()){
                System.out.println(rs.getString("Name")+", "+ rs.getString("Email")+", ");
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        } finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
