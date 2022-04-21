package cl.chisa.myapplication.bd;

import android.app.Activity;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection extends Activity {
    String gURL = "jdbc:mysql://";
    String gIP = "makrohard.sytes.net";
    String gPORT = "3306";
    String gDATABASE = "appfund";
    String gUSR = "appfund";
    String gPSW = "IpChile2022";

    public Connection CONN() {
        final String class_jdbc = "com.mysql.jdbc.Driver";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        try {
            Class.forName(class_jdbc);
            conn = DriverManager.getConnection(gURL + gIP + ":" + gPORT + "/" + gDATABASE, gUSR, gPSW);
        } catch (SQLException se) {
            Log.e("ERROR1", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR2", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR3", e.getMessage());
        }
        return conn;
    }

    public String Query_Version() {
        String response = "";

        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            ResultSet rs = st.executeQuery("SELECT VERSION()");
            //ResultSet rs = st.executeQuery("SHOW VARIABLES LIKE \"%version%\"");

            while (rs.next()) {
                response += rs.getString(1) + "\r\n";
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return response;
    }

    public String Check_Login(String usuario, String pass) {
        String response = "";

        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();

            ResultSet rs = st.executeQuery("SELECT desc_rol from rol where id = 1");
            //ResultSet rs = st.executeQuery("SHOW VARIABLES LIKE \"%version%\"");

            while (rs.next()) {
                response += rs.getString(1) + "\r\n";
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return response;
    }


}

