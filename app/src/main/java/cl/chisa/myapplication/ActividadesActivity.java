package cl.chisa.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import cl.chisa.myapplication.bd.DBConnection;
import cl.chisa.myapplication.bd.clases.Actividad;
import cl.chisa.myapplication.bd.clases.Asignatura;
import cl.chisa.myapplication.bd.clases.Persona;
import cl.chisa.myapplication.bd.clases.Rol;
import cl.chisa.myapplication.bd.clases.Sede;
import cl.chisa.myapplication.bd.llamadas.ActividadSql;
import cl.chisa.myapplication.bd.llamadas.AsignaturaSql;
import cl.chisa.myapplication.bd.llamadas.PersonaSql;
import cl.chisa.myapplication.bd.llamadas.RolSql;
import cl.chisa.myapplication.bd.llamadas.SedeSql;
import cl.chisa.myapplication.bd.utilidades.Extras;

public class ActividadesActivity extends DBConnection {
    Button btn_nuevo, btn_exportar,btn_guardar;
    Spinner spinner, sp_docentes, sp_sede, sp_materia;
    RecyclerView rv_info;
    EditText etv_nombre,etv_hora_inicio,etv_hora_termino, etv_sede, etv_materia, etv_fecha_inicio;
    TextView tv_fecha,tv_hora_inicio, tv_hora_termino;
    ConstraintLayout cl_principal,cl_add_actividad;
    private Actividad actividadAEnviar = new Actividad();

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

        btn_exportar = findViewById(R.id.btn_exportar_actividad);
        btn_guardar = findViewById(R.id.btn_guardar_actividad);
        spinner = findViewById(R.id.spinner_actividad);
        sp_docentes  = findViewById(R.id.sp_docentes_actividad);
        sp_sede  = findViewById(R.id.sp_sede_actividad);
        sp_materia = findViewById(R.id.sp_materia_actividad);

        rv_info = findViewById(R.id.rv_info_actividad);
        //tv_nombre = findViewById(R.id.etv_nombre);
      //  etv_hora_inicio = findViewById(R.id.etv_hora_inicio);
        //etv_fecha_inicio = findViewById(R.id.etv_fecha_inicio);
        tv_fecha = findViewById(R.id.tv_fecha_actividad);
        tv_hora_inicio = findViewById(R.id.tv_hora_inicio_actividad);
        tv_hora_termino = findViewById(R.id.tv_hora_termino_actividad);
      //  etv_hora_termino = findViewById(R.id.etv_hora_termino);
      //  etv_sede = findViewById(R.id.etv_sede);
       // etv_materia = findViewById(R.id.etv_materia);
        cl_principal = findViewById(R.id.cl_principal_actividad);
        cl_add_actividad = findViewById(R.id.cl_add_actividad);
        btn_nuevo = findViewById(R.id.btn_nuevo_actividad);
        cl_principal.setVisibility(View.VISIBLE);
        cl_add_actividad.setVisibility(View.GONE);
        ActividadesActivity context = this;


        PersonaSql consultaDocentes = new PersonaSql();
        Persona inicioPersona = new Persona();
        inicioPersona.setRut_persona("Seleccione profesor...");
        Vector<Persona> listDocente = new Vector();
        listDocente.add(inicioPersona);
        Vector<Persona> listDocenteB = consultaDocentes.callAllDocente();
        if(listDocenteB != null) {
            listDocenteB.forEach(persona -> listDocente.add(persona));

            ArrayAdapter<Persona> adapterDocente = new ArrayAdapter(context,
                    android.R.layout.simple_spinner_item, listDocente);
            sp_docentes.
                    setAdapter(adapterDocente);
        }

          /*  Persona docenteSelected = (Persona) ( (Spinner) sp_docentes ).getSelectedItem();
            if(!docenteSelected.getRut_persona().equals("Seleccione profesor...")) {
                actividadAEnviar.setPersona_rut_persona(docenteSelected.getRut_persona());
                Log.e("docenteSelected.getRut_persona()", String.valueOf(docenteSelected.getRut_persona()));
            }
*/

        SedeSql consultaSede = new SedeSql();
        Sede inicioSede = new Sede();
        inicioSede.setDesc_sede("Seleccione sede...");
        Vector<Sede> listSede = new Vector();
        listSede.add(inicioSede);
        List<Sede> listSedeB = consultaSede.getActiveSede();
        if(listSedeB != null) {
            listSedeB.forEach(sede -> listSede.add(sede));
            ArrayAdapter<Sede> adapterSede = new ArrayAdapter<Sede>(context,
                    android.R.layout.simple_spinner_item, listSede);
            sp_sede.setAdapter(adapterSede);
        }

     /*   Sede sedeSelected = (Sede) ( (Spinner) sp_sede ).getSelectedItem();
        if(!sedeSelected.getDesc_sede().equals("Seleccione sede...")) {
            actividadAEnviar.setSede_id_sede(sedeSelected.getId());
            Log.e("sedeSelected.getId()", String.valueOf(sedeSelected.getId()));
        }
*/

        AsignaturaSql consultaAsignatura = new AsignaturaSql();
        Asignatura inicioAsignatura = new Asignatura();
        inicioAsignatura.setDesc_asignatura("Seleccione asignatura...");
        Vector<Asignatura> listAsignatura = new Vector();
        listAsignatura.add(inicioAsignatura);
        List<Asignatura> listAsignaturaB = consultaAsignatura.getAllAsignatura();
        if (listAsignaturaB != null) {
            listAsignaturaB.forEach(asignatura -> listAsignatura.add(asignatura));
            ArrayAdapter<Asignatura> adapterAsignatura = new ArrayAdapter<Asignatura>(context,
                    android.R.layout.simple_spinner_item, listAsignatura);
            sp_materia.setAdapter(adapterAsignatura);
        }

      /*  Asignatura asignaturaSelected = (Asignatura) ( (Spinner) sp_materia ).getSelectedItem();
        if(!asignaturaSelected.getDesc_asignatura().equals("Seleccione asignatura...")) {
            actividadAEnviar.setAsignatura_id_asignatura(asignaturaSelected.getId());
            Log.e("asignaturaSelected.getId()", String.valueOf(asignaturaSelected.getId()));
        }*/

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
                        tv_fecha.setText(dayOfMonth +"/"+(monthOfYear +1)+"/"+year);
                        actividadAEnviar.setFecha_actividad(dayOfMonth +"/"+monthOfYear+"/"+year);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                dateDialog.getDatePicker().setMinDate(calMin.getTimeInMillis());
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
                        actividadAEnviar.setHoraini_actividad(hourOfDay + ":" + minute);
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
                        actividadAEnviar.setHorafin_actividad(hourOfDay + ":" + minute );
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
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Extras extra = new Extras();

                Persona docenteSelected = (Persona) ( (Spinner) sp_docentes ).getSelectedItem();
                if(!docenteSelected.getRut_persona().equals("Seleccione profesor...")) {
                    actividadAEnviar.setPersona_rut_persona(docenteSelected.getRut_persona());
                }

                Asignatura asignaturaSelected = (Asignatura) ( (Spinner) sp_materia ).getSelectedItem();
                if(!asignaturaSelected.getDesc_asignatura().equals("Seleccione asignatura...")) {
                    actividadAEnviar.setAsignatura_id_asignatura(asignaturaSelected.getId());
                }
                Sede sedeSelected = (Sede) ( (Spinner) sp_sede ).getSelectedItem();
                if(!sedeSelected.getDesc_sede().equals("Seleccione sede...")) {
                    actividadAEnviar.setSede_id_sede(sedeSelected.getId());
                }

                if (actividadAEnviar.getAsignatura_id_asignatura() != null
                        && actividadAEnviar.getSede_id_sede() != null
                        && actividadAEnviar.getPersona_rut_persona() != null
                        && actividadAEnviar.getFecha_actividad() != null
                        && actividadAEnviar.getHoraini_actividad() != null
                        && actividadAEnviar.getHorafin_actividad() != null
                ){
                    ActividadSql actividad = new ActividadSql();
                    actividad.addActividad(actividadAEnviar);
                    Toast.makeText(context,"Datos guardados", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(context,"Revise los datos ingresados", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
