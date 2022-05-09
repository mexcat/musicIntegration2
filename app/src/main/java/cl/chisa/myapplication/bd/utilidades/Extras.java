package cl.chisa.myapplication.bd.utilidades;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Extras {
    public Date convertirStringADate(String texto) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Log.e("texto", texto);
        Date fecha = formato.parse(texto);
        return fecha;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String dateNow(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    return dtf.format(LocalDateTime.now());
    }
}
