package cl.chisa.myapplication.bd.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Rol implements Parcelable {
    private Integer id;
    private String desc_rol;

    public Rol() {
        id = 0;
        desc_rol = "";
    }

    protected Rol(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        desc_rol = in.readString();
    }

    public static final Creator<Rol> CREATOR = new Creator<Rol>() {
        @Override
        public Rol createFromParcel(Parcel in) {
            return new Rol(in);
        }

        @Override
        public Rol[] newArray(int size) {
            return new Rol[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc_rol() {
        return desc_rol;
    }

    public void setDesc_rol(String desc_rol) {
        this.desc_rol = desc_rol;
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
        dest.writeString(desc_rol);
    }
}
