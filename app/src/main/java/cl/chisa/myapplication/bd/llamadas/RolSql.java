package cl.chisa.myapplication.bd.llamadas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import cl.chisa.myapplication.bd.DBConnection;
import cl.chisa.myapplication.bd.clases.Estado;
import cl.chisa.myapplication.bd.clases.Persona;
import cl.chisa.myapplication.bd.clases.Rol;

public class RolSql extends DBConnection {

    public List<Rol> getAllRol() {
        Rol rol = new Rol();
        List<Rol> listRol = null;
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from rol");
            while (rs.next()) {
                rol.setId(rs.getInt("id"));
                rol.setDesc_rol(rs.getString("desc_rol"));
                listRol.add(rol);
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRol;
    }
}
