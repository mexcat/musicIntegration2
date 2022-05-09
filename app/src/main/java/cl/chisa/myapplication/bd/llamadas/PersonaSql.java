package cl.chisa.myapplication.bd.llamadas;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import cl.chisa.myapplication.bd.DBConnection;
import cl.chisa.myapplication.bd.clases.Estado;
import cl.chisa.myapplication.bd.clases.Persona;
import cl.chisa.myapplication.bd.clases.Rol;

public class PersonaSql extends DBConnection {
    public Persona callPersona(String rut) {
        Persona persona = new Persona();
        Estado estado = new Estado();
        Rol rol = new Rol();
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from persona where rut_persona = '" + rut + "'");
            //persona.desc_nombre,persona.desc_paterno,persona.desc_materno,persona.rut_persona,persona.desc_email,rol.id as id_rol, rol.desc_rol, estado.id as id_estado, estado.desc_estado
            while (rs.next()) {
                persona.setDesc_nombre(rs.getString("desc_nombre"));
                persona.setDesc_paterno(rs.getString("desc_paterno"));
                persona.setDesc_materno(rs.getString("desc_materno"));
                persona.setRut_persona(rs.getString("rut_persona"));
                persona.setDesc_email(rs.getString("desc_email"));
                persona.setPassword("");
                persona.setRol_id(rs.getInt("rol_id"));
                persona.setEstado_id(rs.getInt("estado_id"));
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return persona;
    }

    public List<Persona> callAllPersona() {
        List<Persona> lista = null;
        Persona persona = new Persona();
        Estado estado = new Estado();
        Rol rol = new Rol();
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from persona");
            while (rs.next()) {
                persona.setDesc_nombre(rs.getString("desc_nombre"));
                persona.setDesc_paterno(rs.getString("desc_paterno"));
                persona.setDesc_materno(rs.getString("desc_materno"));
                persona.setRut_persona(rs.getString("rut_persona"));
                persona.setDesc_email(rs.getString("desc_email"));
                persona.setPassword("");
                persona.setRol_id(rs.getInt("rol_id"));
                persona.setEstado_id(rs.getInt("estado_id"));
                lista.add(persona);
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Vector<Persona> callAllDocente() {
        Vector<Persona> lista = new Vector<Persona>() {
        } ;
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from persona where rol_id = 4 and estado_id =1");

            while (rs.next()) {
                Persona persona = new Persona();

                Log.e("Rut", rs.getString("rut_persona"));
                persona.setDesc_nombre(rs.getString("desc_nombre"));
                persona.setDesc_paterno(rs.getString("desc_paterno"));
                persona.setDesc_materno(rs.getString("desc_materno"));
                persona.setRut_persona(rs.getString("rut_persona"));
                persona.setDesc_email(rs.getString("desc_email"));
                persona.setPassword("");
                persona.setEstado_id(1);
                persona.setRol_id(4);
                if(persona != null){
                    lista.add(persona);
                }

            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("lista", String.valueOf(lista.size()));
        return lista;
    }

    public Persona updatePersona(Persona persona) {
        Estado estado = new Estado();
        Rol rol = new Rol();
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            ResultSet rs = st.executeQuery("Update persona set desc_nombre = '" + persona.getDesc_nombre() + "',desc_paterno = '" + persona.getDesc_paterno() + "',desc_materno = '" + persona.getDesc_materno() + "', desc_email = '" + persona.getDesc_email() + "', id_rol = '" + persona.getRol_id() + "', id_estado = '" + persona.getEstado_id() + "' where rut_persona = '" + persona.getRut_persona() + "'");
            while (rs.next()) {
                Log.e("ok", "ok");
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return persona;
    }

    public List<Persona> callPersonaXitem(String[] filtros, String[] valores) {
        List<Persona> lista = null;
        Persona persona = new Persona();
        Estado estado = new Estado();
        Rol rol = new Rol();
        StringBuilder sql = new StringBuilder("SELECT * from persona where ");
        if (filtros.length > 1) {
            for (int i = 0; i < filtros.length; i++) {
                switch (filtros[i]) {
                    case "rut": {
                        sql.append("rut_persona = '").append(valores[i]).append("'");
                    }
                    case "nombre": {
                        sql.append("des_nombre LIKE '%").append(valores[i]).append("%'");
                    }
                    case "paterno": {
                        sql.append("desc_paterno LIKE '%").append(valores[i]).append("%'");
                    }
                    case "materno": {
                        sql.append("desc_materno LIKE '%").append(valores[i]).append("%'");
                    }
                    case "email": {
                        sql.append("desc_email LIKE '%").append(valores[i]).append("%'");
                    }
                    case "estado": {
                        sql.append("estado_id = '").append(valores[i]).append("'");
                    }
                    case "rol": {
                        sql.append("rol_id = '").append(valores[i]).append("'");
                    }
                }
                if (i > 1 && i < filtros.length) {
                    sql.append(", ");
                }
            }
            Log.e("call", sql.toString());
        }
        try {
            Connection ConnexionMySQL = CONN();
            Statement st = ConnexionMySQL.createStatement();
            ResultSet rs = st.executeQuery(sql.toString());
            //persona.desc_nombre,persona.desc_paterno,persona.desc_materno,persona.rut_persona,persona.desc_email,rol.id as id_rol, rol.desc_rol, estado.id as id_estado, estado.desc_estado
            while (rs.next()) {
                persona.setDesc_nombre(rs.getString("desc_nombre"));
                persona.setDesc_paterno(rs.getString("desc_paterno"));
                persona.setDesc_materno(rs.getString("desc_materno"));
                persona.setRut_persona(rs.getString("rut_persona"));
                persona.setDesc_email(rs.getString("desc_email"));
                persona.setPassword("");
                persona.setRol_id(rs.getInt("rol_id"));
                persona.setEstado_id(rs.getInt("estado_id"));
                lista.add(persona);
            }
            rs.close();
            ConnexionMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }


}
