package cl.chisa.myapplication.bd.llamadas;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import cl.chisa.myapplication.bd.DBConnection;
import cl.chisa.myapplication.bd.clases.ActividadConDetalle;
import cl.chisa.myapplication.bd.clases.Estado;
import cl.chisa.myapplication.bd.clases.Excel;
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

                //persona.setRol_id(rs.getInt("rol_id"));
                Log.e("id_rol_out", String.valueOf(rs.getInt("id_rol")));
                Log.e("id_rol_in", String.valueOf(rol.getId()));

                persona.setEstado_id(rs.getInt("estado_id"));

            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return persona;
    }

    public Vector<Excel> exportAllToExcel() {
        Vector<Excel> listaExcel = new Vector<>();
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            ResultSet rs = st.executeQuery("select a.fecha_actividad as Fecha, p.rut_persona as Rut, CONCAT_WS(' ',p.desc_nombre,p.desc_paterno,p.desc_materno) as Docente, s.desc_sede as Sede, asi.desc_asignatura as Asignatura, a.horaini_actividad as Inicio, a.horafin_actividad as Termino, a.horafin_actividad - a.horaini_actividad as Horas from appfund.actividades a join appfund.persona p on p.rut_persona = a.persona_rut_persona join appfund.sede s on s.id = a.sede_id join appfund.asignatura asi on asi.id = a.asignatura_id");
            while (rs.next()) {
                Excel actividad = new Excel();

                actividad.setFecha(rs.getString("Fecha"));
                actividad.setRut(rs.getString("Rut"));
                actividad.setDocente(rs.getString("Docente"));
                actividad.setSede(rs.getString("Sede"));
                actividad.setAsignatura(rs.getString("Asignatura"));
                actividad.setInicio(rs.getString("Inicio"));
                actividad.setTermino(rs.getString("Termino"));
                actividad.setHoras(rs.getString("Horas"));

                listaExcel.add(actividad);
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaExcel;
    }
    public Vector<Excel> exportDocenteToExcel(String rut) {
        Vector<Excel> listaExcel = new Vector<>();
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            ResultSet rs = st.executeQuery("select a.fecha_actividad as Fecha, p.rut_persona as Rut, CONCAT_WS(' ',p.desc_nombre,p.desc_paterno,p.desc_materno) as Docente, s.desc_sede as Sede, asi.desc_asignatura as Asignatura, a.horaini_actividad as Inicio, a.horafin_actividad as Termino, a.horafin_actividad - a.horaini_actividad as Horas from appfund.actividades a join appfund.persona p on p.rut_persona = a.persona_rut_persona join appfund.sede s on s.id = a.sede_id join appfund.asignatura asi on asi.id = a.asignatura_id where p.rut_persona='"+String.valueOf(rut)+"'");
            while (rs.next()) {
                Excel actividad = new Excel();

                actividad.setFecha(rs.getString("Fecha"));
                actividad.setRut(rs.getString("Rut"));
                actividad.setDocente(rs.getString("Docente"));
                actividad.setSede(rs.getString("Sede"));
                actividad.setAsignatura(rs.getString("Asignatura"));
                actividad.setInicio(rs.getString("Inicio"));
                actividad.setTermino(rs.getString("Termino"));
                actividad.setHoras(rs.getString("Horas"));

                listaExcel.add(actividad);
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaExcel;
    }


}
