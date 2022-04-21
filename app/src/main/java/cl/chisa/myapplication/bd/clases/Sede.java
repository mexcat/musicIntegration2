package cl.chisa.myapplication.bd.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Sede implements Parcelable {
    private Integer id_sede;
    private String desc_sede;
    private Estado estado;

    public Sede() {
    }

    protected Sede(Parcel in) {
        if (in.readByte() == 0) {
            id_sede = null;
        } else {
            id_sede = in.readInt();
        }
        desc_sede = in.readString();
        estado = in.readParcelable(Estado.class.getClassLoader());
    }

    public static final Creator<Sede> CREATOR = new Creator<Sede>() {
        @Override
        public Sede createFromParcel(Parcel in) {
            return new Sede(in);
        }

        @Override
        public Sede[] newArray(int size) {
            return new Sede[size];
        }
    };

    public Integer getId_sede() {
        return id_sede;
    }

    public void setId_sede(Integer id_sede) {
        this.id_sede = id_sede;
    }

    public String getDesc_sede() {
        return desc_sede;
    }

    public void setDesc_sede(String desc_sede) {
        this.desc_sede = desc_sede;
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
        if (id_sede == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id_sede);
        }
        dest.writeString(desc_sede);
        dest.writeParcelable(estado, flags);
    }
}
