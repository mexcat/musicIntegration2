package cl.chisa.myapplication.bd.llamadas;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import cl.chisa.myapplication.bd.DBConnection;
import cl.chisa.myapplication.bd.clases.Estado;
import cl.chisa.myapplication.bd.clases.Persona;
import cl.chisa.myapplication.bd.clases.Rol;

public class ConsultasSql extends DBConnection {

    public Persona login(String usuario, String pass) {
        Persona persona = new Persona();
        Estado estado = new Estado();
        Rol rol = new Rol();
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            ResultSet rs = st.executeQuery("SELECT persona.desc_nombre,persona.desc_paterno,persona.desc_materno,persona.rut_persona,persona.desc_email,rol.id as id_rol, rol.desc_rol, estado.id as id_estado, estado.desc_estado from persona join estado on persona.estado_id = estado.id join rol on persona.rol_id = rol.id where persona.desc_email = '" + usuario + "' and persona.password = '" + pass + "' and persona.estado_id =1");
            while (rs.next()) {
                persona.setDesc_nombre(rs.getString("desc_nombre"));
                persona.setDesc_paterno(rs.getString("desc_paterno"));
                persona.setDesc_materno(rs.getString("desc_materno"));
                persona.setRut_persona(rs.getString("rut_persona"));
                persona.setDesc_email(rs.getString("desc_email"));
                persona.setPassword("");

                rol.setId(rs.getInt("id_rol"));
                Log.e("id_rol_out", String.valueOf(rs.getInt("id_rol")));
                Log.e("id_rol_in", String.valueOf(rol.getId()));
                rol.setDesc_rol(rs.getString("desc_rol"));
                persona.setRol(rol);

                estado.setId(rs.getInt("id_estado"));
                estado.setDesc_estado("desc_estado");
                persona.setEstado(estado);
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return persona;
    }
}
