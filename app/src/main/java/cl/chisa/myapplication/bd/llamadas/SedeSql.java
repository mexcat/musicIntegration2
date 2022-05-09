package cl.chisa.myapplication.bd.llamadas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import cl.chisa.myapplication.bd.DBConnection;
import cl.chisa.myapplication.bd.clases.Rol;
import cl.chisa.myapplication.bd.clases.Sede;

public class SedeSql extends DBConnection {

    public Vector<Sede> getActiveSede() {
        Vector<Sede> listSede = new Vector<Sede>();
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from sede where estado_id = 1");
            while (rs.next()) {
                Sede sede = new Sede();

                sede.setId(rs.getInt("id"));
                sede.setDesc_sede(rs.getString("desc_sede"));
                sede.setEstado_id(1);
                listSede.add(sede);
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSede;
    }
}
