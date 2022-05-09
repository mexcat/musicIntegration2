package cl.chisa.myapplication.bd.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Excel  implements Parcelable {
    private String Fecha;
    private String Rut;
    private String Docente;
    private String Sede;
    private String Asignatura;
    private String Inicio;
    private String Termino;
    private String Horas;


    public Excel() {}
    public Excel(Parcel in) {
        Fecha = in.readString();
        Rut = in.readString();
        Docente = in.readString();
        Sede = in.readString();
        Asignatura = in.readString();
        Inicio = in.readString();
        Termino = in.readString();
        Horas = in.readString();
    }

    public static final Creator<Excel> CREATOR = new Creator<Excel>() {
        @Override
        public Excel createFromParcel(Parcel in) {
            return new Excel(in);
        }

        @Override
        public Excel[] newArray(int size) {
            return new Excel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Fecha);
        dest.writeString(Rut);
        dest.writeString(Docente);
        dest.writeString(Sede);
        dest.writeString(Asignatura);
        dest.writeString(Inicio);
        dest.writeString(Termino);
        dest.writeString(Horas);
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        this.Fecha = fecha;
    }

    public String getRut() {
        return Rut;
    }

    public void setRut(String rut) {
        Rut = rut;
    }

    public String getDocente() {
        return Docente;
    }

    public void setDocente(String docente) {
        Docente = docente;
    }

    public String getSede() {
        return Sede;
    }

    public void setSede(String sede) {
        Sede = sede;
    }

    public String getAsignatura() {
        return Asignatura;
    }

    public void setAsignatura(String asignatura) {
        Asignatura = asignatura;
    }

    public String getInicio() {
        return Inicio;
    }

    public void setInicio(String inicio) {
        Inicio = inicio;
    }

    public String getTermino() {
        return Termino;
    }

    public void setTermino(String termino) {
        Termino = termino;
    }

    public String getHoras() {
        return Horas;
    }

    public void setHoras(String horas) {
        Horas = horas;
    }

    public static Creator<Excel> getCREATOR() {
        return CREATOR;
    }
}
