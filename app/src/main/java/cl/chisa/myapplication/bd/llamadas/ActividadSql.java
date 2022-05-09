package cl.chisa.myapplication.bd.llamadas;

import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import cl.chisa.myapplication.bd.DBConnection;
import cl.chisa.myapplication.bd.clases.Actividad;
import cl.chisa.myapplication.bd.clases.ActividadConDetalle;
import cl.chisa.myapplication.bd.clases.Estado;
import cl.chisa.myapplication.bd.clases.Rol;

public class ActividadSql extends DBConnection {
    public Vector<Actividad> getAllActividad() {
        Vector<Actividad> listaActividad = new Vector<>();
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from actividades");
            while (rs.next()) {
                Actividad actividad = new Actividad();

                actividad.setId(rs.getInt("id"));
                actividad.setPersona_rut_persona(rs.getString("persona_rut_persona"));
                actividad.setAsignatura_id_asignatura(rs.getInt("asignatura_id"));
                actividad.setSede_id_sede(rs.getInt("sede_id"));
                actividad.setFecha_actividad(rs.getString("fecha_actividad"));
                actividad.setHoraini_actividad(rs.getString("horaini_actividad"));
                actividad.setHorafin_actividad(rs.getString("horafin_actividad"));
                listaActividad.add(actividad);
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaActividad;
    }

    public Vector<ActividadConDetalle> getAllActividadConDetalle() {
        Vector<ActividadConDetalle> listaActividad = new Vector<>();
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from actividades ac Join persona per on ac.persona_rut_persona = per.rut_persona join asignatura asig on ac.asignatura_id = asig.id join sede on ac.sede_id = sede.id");
            while (rs.next()) {
                ActividadConDetalle actividad = new ActividadConDetalle();

                actividad.setId(rs.getInt("id"));
                actividad.setPersona_rut_persona(rs.getString("persona_rut_persona"));
                actividad.setPersona_nombre(rs.getString("desc_nombre"));
                actividad.setPersona_paterno(rs.getString("desc_paterno"));
                actividad.setPersona_materno(rs.getString("desc_materno"));

                actividad.setAsignatura_id_asignatura(rs.getInt("asignatura_id"));
                actividad.setDesc_asignatura(rs.getString("desc_asignatura"));

                actividad.setSede_id_sede(rs.getInt("sede_id"));
                actividad.setDesc_sede(rs.getString("desc_sede"));

                actividad.setFecha_actividad(rs.getString("fecha_actividad"));
                actividad.setHoraini_actividad(rs.getString("horaini_actividad"));
                actividad.setHorafin_actividad(rs.getString("horafin_actividad"));
                listaActividad.add(actividad);
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaActividad;
    }

    public Actividad addActividad(Actividad actividad) {
        java.util.Date date =  new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            try {
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
