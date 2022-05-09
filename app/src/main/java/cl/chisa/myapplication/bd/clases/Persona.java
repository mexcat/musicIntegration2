package cl.chisa.myapplication.bd.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Persona implements Parcelable {
    private String rut_persona;
    private String desc_nombre;
    private String desc_paterno;
    private String desc_materno;
    private String desc_email;
    private String password;
    private Integer estado_id;
    private Integer rol_id;

    public Persona() {
        rut_persona = "";
        desc_nombre = "";
        desc_paterno = "";
        desc_materno = "";
        desc_email = "";
        password = "";
        estado_id = 0;
        rol_id = 1;
    }
    @Override public String toString() { return this.rut_persona; }

    protected Persona(Parcel in) {
        rut_persona = in.readString();
        desc_nombre = in.readString();
        desc_paterno = in.readString();
        desc_materno = in.readString();
        desc_email = in.readString();
        password = in.readString();
        if (in.readByte() == 0) {
            estado_id = null;
        } else {
            estado_id = in.readInt();
        }
        if (in.readByte() == 0) {
            rol_id = null;
        } else {
            rol_id = in.readInt();
        }
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rut_persona);
        dest.writeString(desc_nombre);
        dest.writeString(desc_paterno);
        dest.writeString(desc_materno);
        dest.writeString(desc_email);
        dest.writeString(password);
        if (estado_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(estado_id);
        }
        if (rol_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(rol_id);
        }
    }

    public String getRut_persona() {
        return rut_persona;
    }

    public void setRut_persona(String rut_persona) {
        this.rut_persona = rut_persona;
    }

    public String getDesc_nombre() {
        return desc_nombre;
    }

    public void setDesc_nombre(String desc_nombre) {
        this.desc_nombre = desc_nombre;
    }

    public String getDesc_paterno() {
        return desc_paterno;
    }

    public void setDesc_paterno(String desc_paterno) {
        this.desc_paterno = desc_paterno;
    }

    public String getDesc_materno() {
        return desc_materno;
    }

    public void setDesc_materno(String desc_materno) {
        this.desc_materno = desc_materno;
    }

    public String getDesc_email() {
        return desc_email;
    }

    public void setDesc_email(String desc_email) {
        this.desc_email = desc_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }

    public Integer getRol_id() {
        return rol_id;
    }

    public void setRol_id(Integer rol_id) {
        this.rol_id = rol_id;
    }

    public static Creator<Persona> getCREATOR() {
        return CREATOR;
    }
}
