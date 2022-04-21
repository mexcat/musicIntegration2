package cl.chisa.myapplication.bd.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Estado implements Parcelable {

    private Integer id;
    private String desc_estado;

    public Estado() {
        id = 0;
        desc_estado = "";
    }

    protected Estado(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        desc_estado = in.readString();
    }

    public static final Creator<Estado> CREATOR = new Creator<Estado>() {
        @Override
        public Estado createFromParcel(Parcel in) {
            return new Estado(in);
        }

        @Override
        public Estado[] newArray(int size) {
            return new Estado[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc_estado() {
        return desc_estado;
    }

    public void setDesc_estado(String desc_estado) {
        this.desc_estado = desc_estado;
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
        dest.writeString(desc_estado);
    }
}
