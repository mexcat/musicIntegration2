package cl.chisa.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import cl.chisa.myapplication.bd.DBConnection;
import cl.chisa.myapplication.bd.clases.Actividad;

public class ActividadesActivity extends DBConnection {
    Button btn_nuevo, btn_exportar,btn_guardar;
    Spinner spinner;
    RecyclerView rv_info;
    EditText etv_nombre,etv_hora_inicio,etv_hora_termino, etv_sede, etv_materia, etv_fecha_inicio;
    TextView tv_fecha,tv_hora_inicio, tv_hora_termino;
    ConstraintLayout cl_principal,cl_add_actividad;

    boolean is24HView = true;
    private int lastSelectedHour = -1;
    private int lastSelectedMinute = -1;
    private int lastSelectedHour2 = -1;
    private int lastSelectedMinute2 = -1;
    private int minHour = -1;
    private int minMinute = -1;
    private int currentHour = -1;
    private int currentMinute = -1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividades_activity);

        btn_nuevo = findViewById(R.id.btn_nuevo);
        btn_exportar = findViewById(R.id.btn_exportar);
        btn_guardar = findViewById(R.id.btn_guardar);
        spinner = findViewById(R.id.spinner);
        rv_info = findViewById(R.id.rv_info);
        //tv_nombre = findViewById(R.id.etv_nombre);
      //  etv_hora_inicio = findViewById(R.id.etv_hora_inicio);
        //etv_fecha_inicio = findViewById(R.id.etv_fecha_inicio);
        tv_fecha = findViewById(R.id.tv_fecha);
        tv_hora_inicio = findViewById(R.id.tv_hora_inicio);
        tv_hora_termino = findViewById(R.id.tv_hora_termino);
      //  etv_hora_termino = findViewById(R.id.etv_hora_termino);
      //  etv_sede = findViewById(R.id.etv_sede);
       // etv_materia = findViewById(R.id.etv_materia);
        cl_principal = findViewById(R.id.cl_principal);
        cl_add_actividad = findViewById(R.id.cl_add_actividad);

        cl_principal.setVisibility(View.VISIBLE);
        cl_add_actividad.setVisibility(View.GONE);

        ActividadesActivity context = this;

       // etv_fecha_inicio.setText(new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()));
        tv_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                Calendar calMin = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_YEAR, 1);
                calMin.add(Calendar.DAY_OF_YEAR, 0);
                final DatePickerDialog dateDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(final DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
                        tv_fecha.setText(dayOfMonth +"/"+monthOfYear+"/"+year);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                dateDialog.getDatePicker().setMinDate(calMin.getTimeInMillis());
                //dateDialog.getDatePicker().setMaxDate(cal.YEAR);
                dateDialog.show();
            }
        });

        tv_hora_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tv_hora_inicio.setText(hourOfDay + ":" + minute );
                        lastSelectedHour = hourOfDay;
                        lastSelectedMinute = minute;
                        minMinute = minute;
                        minHour = hourOfDay;
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                        timeSetListener, lastSelectedHour, lastSelectedMinute, is24HView);
                timePickerDialog.show();
            }
        });

        tv_hora_termino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        lastSelectedHour2 = hourOfDay;
                        lastSelectedMinute2 = minute;
                        tv_hora_termino.setText(hourOfDay + ":" + minute );
                        /*view.setOnTimeChangedListener((view1, hourOfDay1, minute1) -> {

                            boolean validTime;
                            if(hourOfDay < minHour) {
                                validTime = false;
                            }
                            else if(hourOfDay == minHour) {
                                validTime = minute >= minMinute;
                            }
                            else {
                                validTime = true;
                            }

                            if(validTime) {
                                currentHour = hourOfDay;
                                currentMinute = minute;
                                tv_hora_termino.setText(hourOfDay + ":" + minute );
                            }
                            else {
                                //tv_hora_inicio.setText(hourOfDay + ":" + minute );
                            }
                        });*/
                    }
                };

                TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                        timeSetListener, lastSelectedHour2, lastSelectedMinute2, is24HView);
                timePickerDialog.show();
            }
        });





        btn_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cl_principal.setVisibility(View.GONE);
                cl_add_actividad.setVisibility(View.VISIBLE);
            }
        });

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actividad actividad = new Actividad();

                actividad.setHoraini_actividad(etv_hora_inicio.toString());
                etv_hora_inicio.getText();
                etv_hora_termino.getText();
                etv_sede.getText();
                etv_materia.getText();
            }
        });
    }
}
