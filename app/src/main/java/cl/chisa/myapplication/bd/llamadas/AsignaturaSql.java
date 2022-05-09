package cl.chisa.myapplication.bd.llamadas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import cl.chisa.myapplication.bd.DBConnection;
import cl.chisa.myapplication.bd.clases.Asignatura;
import cl.chisa.myapplication.bd.clases.Rol;

public class AsignaturaSql extends DBConnection {

    public Vector<Asignatura> getAllAsignatura() {
        Asignatura asignatura = new Asignatura();
        Vector<Asignatura> listaAsignatura = new Vector<Asignatura>();
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from asignatura where estado_id = 1");
            while (rs.next()) {
                asignatura.setId(rs.getInt("id"));
                asignatura.setDesc_asignatura(rs.getString("desc_asignatura"));
                asignatura.setEstado_id(1);
                listaAsignatura.add(asignatura);
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaAsignatura;
    }

}
