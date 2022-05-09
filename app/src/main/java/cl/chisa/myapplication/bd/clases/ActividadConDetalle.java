package cl.chisa.myapplication.bd.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class ActividadConDetalle implements Parcelable {

    private Integer id;
    private String persona_rut_persona;
    private String persona_nombre;
    private String persona_paterno;
    private String persona_materno;

    private Integer asignatura_id_asignatura;
    private String desc_asignatura;
    private Integer sede_id_sede;
    private String desc_sede;
    private String fecha_registro;
    private String fecha_actividad;
    private String horaini_actividad;
    private String horafin_actividad;

    public ActividadConDetalle() {

    }

    protected ActividadConDetalle(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        persona_rut_persona = in.readString();
        persona_nombre = in.readString();
        persona_paterno = in.readString();
        persona_materno = in.readString();
        if (in.readByte() == 0) {
            asignatura_id_asignatura = null;
        } else {
            asignatura_id_asignatura = in.readInt();
        }
        desc_asignatura = in.readString();
        if (in.readByte() == 0) {
            sede_id_sede = null;
        } else {
            sede_id_sede = in.readInt();
        }
        desc_sede = in.readString();
        fecha_registro = in.readString();
        fecha_actividad = in.readString();
        horaini_actividad = in.readString();
        horafin_actividad = in.readString();
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
        dest.writeString(persona_nombre);
        dest.writeString(persona_paterno);
        dest.writeString(persona_materno);
        if (asignatura_id_asignatura == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(asignatura_id_asignatura);
        }
        dest.writeString(desc_asignatura);
        if (sede_id_sede == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sede_id_sede);
        }
        dest.writeString(desc_sede);
        dest.writeString(fecha_registro);
        dest.writeString(fecha_actividad);
        dest.writeString(horaini_actividad);
        dest.writeString(horafin_actividad);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ActividadConDetalle> CREATOR = new Creator<ActividadConDetalle>() {
        @Override
        public ActividadConDetalle createFromParcel(Parcel in) {
            return new ActividadConDetalle(in);
        }

        @Override
        public ActividadConDetalle[] newArray(int size) {
            return new ActividadConDetalle[size];
        }
    };

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

    public String getPersona_nombre() {
        return persona_nombre;
    }

    public void setPersona_nombre(String persona_nombre) {
        this.persona_nombre = persona_nombre;
    }

    public String getPersona_paterno() {
        return persona_paterno;
    }

    public void setPersona_paterno(String persona_paterno) {
        this.persona_paterno = persona_paterno;
    }

    public String getPersona_materno() {
        return persona_materno;
    }

    public void setPersona_materno(String persona_materno) {
        this.persona_materno = persona_materno;
    }

    public Integer getAsignatura_id_asignatura() {
        return asignatura_id_asignatura;
    }

    public void setAsignatura_id_asignatura(Integer asignatura_id_asignatura) {
        this.asignatura_id_asignatura = asignatura_id_asignatura;
    }

    public String getDesc_asignatura() {
        return desc_asignatura;
    }

    public void setDesc_asignatura(String desc_asignatura) {
        this.desc_asignatura = desc_asignatura;
    }

    public Integer getSede_id_sede() {
        return sede_id_sede;
    }

    public void setSede_id_sede(Integer sede_id_sede) {
        this.sede_id_sede = sede_id_sede;
    }

    public String getDesc_sede() {
        return desc_sede;
    }

    public void setDesc_sede(String desc_id) {
        this.desc_sede = desc_id;
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

    public static Creator<ActividadConDetalle> getCREATOR() {
        return CREATOR;
    }
}
