package cl.chisa.myapplication.bd.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Asignatura implements Parcelable {

    private Integer id_asignatura;
    private String desc_asignatura;
    private Estado estado;

    public Asignatura() {
        id_asignatura = 0;
        desc_asignatura = "";
    }

    protected Asignatura(Parcel in) {
        if (in.readByte() == 0) {
            id_asignatura = null;
        } else {
            id_asignatura = in.readInt();
        }
        desc_asignatura = in.readString();
        estado = in.readParcelable(Estado.class.getClassLoader());
    }

    public static final Creator<Asignatura> CREATOR = new Creator<Asignatura>() {
        @Override
        public Asignatura createFromParcel(Parcel in) {
            return new Asignatura(in);
        }

        @Override
        public Asignatura[] newArray(int size) {
            return new Asignatura[size];
        }
    };

    public Integer getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(Integer id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public String getDesc_asignatura() {
        return desc_asignatura;
    }

    public void setDesc_asignatura(String desc_asignatura) {
        this.desc_asignatura = desc_asignatura;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id_asignatura == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id_asignatura);
        }
        dest.writeString(desc_asignatura);
        dest.writeParcelable(estado, flags);
    }
}
