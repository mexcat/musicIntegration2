package cl.chisa.myapplication.bd.llamadas;

import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import cl.chisa.myapplication.bd.DBConnection;
import cl.chisa.myapplication.bd.clases.Actividad;
import cl.chisa.myapplication.bd.clases.Estado;
import cl.chisa.myapplication.bd.clases.Rol;

public class ActividadSql extends DBConnection {
    public Actividad getAllActividad() {
        Actividad actividad = new Actividad();
        Estado estado = new Estado();
        Rol rol = new Rol();
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from actividad");
            while (rs.next()) {
                actividad.setId(rs.getInt("id"));
                actividad.setPersona_rut_persona(rs.getString("persona_rut_persona"));
                actividad.setAsignatura_id_asignatura(rs.getInt("asignatura_id_asignatura"));
                actividad.setSede_id_sede(rs.getInt("sede_id_sede"));
                actividad.setFecha_actividad(rs.getString("fecha_actividad"));
                actividad.setHoraini_actividad(rs.getString("horaini_actividad"));
                actividad.setHorafin_actividad(rs.getString("horafin_actividad"));
                //estado actividad

                //actividad.setEstado(estado);
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return actividad;
    }

    public Actividad addActividad(Actividad actividad) {
        java.util.Date date =  new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            try {
                Log.e("actividad.getPersona_rut_persona()",actividad.getPersona_rut_persona());
                Log.e("sql","INSERT INTO actividades (persona_rut_persona, asignatura_id, sede_id, fecha_registro, fecha_actividad, horaini_actividad,horafin_actividad) VALUES ('" + actividad.getPersona_rut_persona() + "'," + actividad.getAsignatura_id_asignatura() + "," + actividad.getSede_id_sede() + ",  NOW()  , STR_TO_DATE('"+actividad.getFecha_actividad()+"', '%e/%c/%Y') ,'" + actividad.getHoraini_actividad() + "','" + actividad.getHorafin_actividad() + "')");
                st.executeUpdate("INSERT INTO actividades (persona_rut_persona, asignatura_id, sede_id, fecha_registro, fecha_actividad, horaini_actividad,horafin_actividad) VALUES ('" + actividad.getPersona_rut_persona() + "'," + actividad.getAsignatura_id_asignatura() + "," + actividad.getSede_id_sede() + ",  Date_Format(now(),'%m/%d/%Y')  , STR_TO_DATE('"+actividad.getFecha_actividad()+"', '%e/%c/%Y') ,'" + actividad.getHoraini_actividad() + "','" + actividad.getHorafin_actividad() + "')");
            }catch (SQLException e){
                Log.e("error", e.toString());
            }
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return actividad;
    }

}
