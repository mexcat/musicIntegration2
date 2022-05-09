package cl.chisa.myapplication.bd.clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Actividad implements Parcelable {

    private Integer id;
    private String persona_rut_persona;
    private Integer asignatura_id_asignatura;
    private Integer sede_id_sede;
    private String fecha_registro;
    private String fecha_actividad;
    private String horaini_actividad;
    private String horafin_actividad;

    public Actividad() {

    }

    protected Actividad(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        persona_rut_persona = in.readString();
        if (in.readByte() == 0) {
            asignatura_id_asignatura = null;
        } else {
            asignatura_id_asignatura = in.readInt();
        }
        if (in.readByte() == 0) {
            sede_id_sede = null;
        } else {
            sede_id_sede = in.readInt();
        }
        fecha_registro = in.readString();
        horaini_actividad = in.readString();
        horafin_actividad = in.readString();
    }

    public static final Creator<Actividad> CREATOR = new Creator<Actividad>() {
        @Override
        public Actividad createFromParcel(Parcel in) {
            return new Actividad(in);
        }

        @Override
        public Actividad[] newArray(int size) {
            return new Actividad[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(persona_rut_persona);
        if (asignatura_id_asignatura == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(asignatura_id_asignatura);
        }
        if (sede_id_sede == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sede_id_sede);
        }
        dest.writeString(fecha_registro);
        dest.writeString(horaini_actividad);
        dest.writeString(horafin_actividad);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersona_rut_persona() {
        return persona_rut_persona;
    }

    public void setPersona_rut_persona(String persona_rut_persona) {
        this.persona_rut_persona = persona_rut_persona;
    }

    public Integer getAsignatura_id_asignatura() {
        return asignatura_id_asignatura;
    }

    public void setAsignatura_id_asignatura(Integer asignatura_id_asignatura) {
        this.asignatura_id_asignatura = asignatura_id_asignatura;
    }

    public Integer getSede_id_sede() {
        return sede_id_sede;
    }

    public void setSede_id_sede(Integer sede_id_sede) {
        this.sede_id_sede = sede_id_sede;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getFecha_actividad() {
        return fecha_actividad;
    }

    public void setFecha_actividad(String fecha_actividad) {
        this.fecha_actividad = fecha_actividad;
    }

    public String getHoraini_actividad() {
        return horaini_actividad;
    }

    public void setHoraini_actividad(String horaini_actividad) {
        this.horaini_actividad = horaini_actividad;
    }

    public String getHorafin_actividad() {
        return horafin_actividad;
    }

    public void setHorafin_actividad(String horafin_actividad) {
        this.horafin_actividad = horafin_actividad;
    }

    public static Creator<Actividad> getCREATOR() {
        return CREATOR;
    }
}
