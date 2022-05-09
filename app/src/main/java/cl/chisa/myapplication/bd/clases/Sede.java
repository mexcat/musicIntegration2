package cl.chisa.myapplication.bd.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Sede implements Parcelable {
    private Integer id;
    private String desc_sede;
    private Integer estado_id;

    public Sede() {
    }

    @Override public String toString() { return this.desc_sede; }

    protected Sede(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        desc_sede = in.readString();
        if (in.readByte() == 0) {
            estado_id = null;
        } else {
            estado_id = in.readInt();
        }
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
        dest.writeString(desc_sede);
        if (estado_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(estado_id);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc_sede() {
        return desc_sede;
    }

    public void setDesc_sede(String desc_sede) {
        this.desc_sede = desc_sede;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }

    public static Creator<Sede> getCREATOR() {
        return CREATOR;
    }
}
