import java.sql.*;
import io.github.cdimascio.dotenv.*;

import javax.xml.transform.Result;

public class DBConnection {
    private Connection con;

    public DBConnection(){
        Dotenv dotenv = Dotenv.configure().load();
        try {
            this.con = DriverManager.getConnection(dotenv.get("CON_STRING"), dotenv.get("USER"), dotenv.get("PASSWORD"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void query(String q){
        try{
            Statement s = this.con.createStatement();
            s.execute(q);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet queryResp(String q){
        try{
            Statement s = this.con.createStatement();
            ResultSet rs = s.executeQuery(q);
            return rs;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
