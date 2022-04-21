package cl.chisa.myapplication.bd.clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

public class Actividad implements Parcelable {

    private Integer id;
    private Persona persona;
    private Asignatura asignatura;
    private Sede sede;
    private String fecha_registro;
    private Date fecha_actividad;
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
        persona = in.readParcelable(Persona.class.getClassLoader());
        sede = in.readParcelable(Sede.class.getClassLoader());
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Date getFecha_actividad() {
        return fecha_actividad;
    }

    public void setFecha_actividad(Date fecha_actividad) {
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

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
        dest.writeParcelable(persona, flags);
        dest.writeParcelable(sede, flags);
        dest.writeString(fecha_registro);
        dest.writeString(horaini_actividad);
        dest.writeString(horafin_actividad);
    }
}
