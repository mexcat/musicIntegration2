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

                actividad.setFecha_registro(rs.getString("fecha_registro"));
              //  Log.e("actividad.getFecha_registro()",actividad.getFecha_registro());
                actividad.setFecha_actividad(rs.getString("fecha_actividad"));
               // Log.e("actividad.getFecha_actividad()",actividad.getFecha_actividad());
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

    public Actividad updateActividad(Actividad actividad) {
        java.util.Date date =  new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            Log.e("actividad.getFecha_actividad()",actividad.getFecha_actividad());
            String fechaFinal = "";
            if (actividad.getFecha_actividad().contains("-")){
                String[] tempString = actividad.getFecha_actividad().split("-");
                fechaFinal = tempString[2]+"/"+tempString[1]+"/"+tempString[0];
            }else{
                fechaFinal = actividad.getFecha_actividad();
            }
            try {
                st.executeUpdate("UPDATE actividades SET  asignatura_id ="+ actividad.getAsignatura_id_asignatura() +", sede_id =" + actividad.getSede_id_sede() + ", fecha_registro = Date_Format(now(),'%m/%d/%Y'), fecha_actividad = STR_TO_DATE('"+fechaFinal+"', '%e/%c/%Y'), horaini_actividad='"+ actividad.getHoraini_actividad() +"',horafin_actividad='" + actividad.getHorafin_actividad() + "' where id = '" + actividad.getId() + "'");
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
