import java.sql.*;
import io.github.cdimascio.dotenv.*;

public class DBConnection {
    private Connection con;

    public DBConnection(){
        Dotenv dotenv = Dotenv.configure().load();
        try {
            this.con = DriverManager.getConnection(dotenv.get("CON_STRING"), dotenv.get("USER"), dotenv.get("PASSWORD"));
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
