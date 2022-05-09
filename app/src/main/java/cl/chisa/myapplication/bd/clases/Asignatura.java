package cl.chisa.myapplication.bd.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Asignatura implements Parcelable {

    private Integer id;
    private String desc_asignatura;
    private Integer estado_id;

    public Asignatura(){
        id= 0;
        desc_asignatura = "";
        estado_id= 0;

    }

    @Override public String toString() { return this.desc_asignatura; }

    protected Asignatura(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        desc_asignatura = in.readString();
        if (in.readByte() == 0) {
            estado_id = null;
        } else {
            estado_id = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(desc_asignatura);
        if (estado_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(estado_id);
        }
    }

    @Override
    public int describeContents() {
        return 0;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc_asignatura() {
        return desc_asignatura;
    }

    public void setDesc_asignatura(String desc_asignatura) {
        this.desc_asignatura = desc_asignatura;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }

    public static Creator<Asignatura> getCREATOR() {
        return CREATOR;
    }
}
