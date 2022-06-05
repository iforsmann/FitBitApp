import java.sql.*;

public class DBConnection {
    private Connection con;

    public DBConnection(){
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/iforsman", "iforsman", "25759532");
            Statement s = this.con.createStatement();
            ResultSet rs = s.executeQuery("Select * from Customer limit 5;");
            while(rs.next()){
                System.out.println(rs.getString("name") + ", " + rs.getString("ssn"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
