package cl.chisa.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import cl.chisa.myapplication.adapter.ActividadAdapter;
import cl.chisa.myapplication.bd.DBConnection;
import cl.chisa.myapplication.bd.clases.Actividad;
import cl.chisa.myapplication.bd.clases.ActividadConDetalle;
import cl.chisa.myapplication.bd.clases.Asignatura;
import cl.chisa.myapplication.bd.clases.Persona;
import cl.chisa.myapplication.bd.clases.Sede;
import cl.chisa.myapplication.bd.llamadas.ActividadSql;
import cl.chisa.myapplication.bd.llamadas.AsignaturaSql;
import cl.chisa.myapplication.bd.llamadas.PersonaSql;
import cl.chisa.myapplication.bd.llamadas.SedeSql;
import cl.chisa.myapplication.bd.utilidades.Extras;

public class ActividadesActivity extends DBConnection {
    Button btn_nuevo, btn_exportar,btn_guardar;
    Spinner spinner, sp_docentes, sp_sede, sp_materia;
    RecyclerView rv_info;
    EditText etv_nombre,etv_hora_inicio,etv_hora_termino, etv_sede, etv_materia, etv_fecha_inicio, etv_search_actividad;
    TextView tv_fecha,tv_hora_inicio, tv_hora_termino, tituloActividad;
    ConstraintLayout cl_principal,cl_add_actividad;
    List<ActividadConDetalle> rvLista;

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
        //spinner = findViewById(R.id.spinner_actividad);
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
        etv_search_actividad = findViewById(R.id.etv_search_actividad);
        tituloActividad = findViewById(R.id.textView5);

        cl_principal.setVisibility(View.VISIBLE);
        cl_add_actividad.setVisibility(View.GONE);
        ActividadesActivity context = this;
        tituloActividad.setText("AGREGAR ACTIVIDAD");
        ActividadSql consultaActividades = new ActividadSql();
        Vector<ActividadConDetalle> actividadesConDetalles = consultaActividades.getAllActividadConDetalle();
        rvLista = new Vector<ActividadConDetalle>();
        actividadesConDetalles.forEach(actividadConDetalle -> rvLista.add(actividadConDetalle));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_info_actividad);
        ActividadAdapter adapter = new ActividadAdapter(actividadesConDetalles, context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        etv_search_actividad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("str",String.valueOf(s));
                adapter.getFilter().filter(String.valueOf(s));
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

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
                        actividadAEnviar.setFecha_actividad(dayOfMonth +"/"+(monthOfYear +1)+"/"+year);
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
//                    limpiar();

                    Intent i = new Intent(context, ActividadesActivity.class);
                    startActivity(i);

                }else{
                    Toast.makeText(context,"Revise los datos ingresados", Toast.LENGTH_LONG).show();
                }
            }
        });

        if(getIntent().getParcelableExtra("actividad") != null){

            Actividad aCambiar = getIntent().getParcelableExtra("actividad");
            actividadAEnviar = aCambiar;

            tituloActividad.setText("EDITAR ACTIVIDAD");
            cl_principal.setVisibility(View.GONE);
            cl_add_actividad.setVisibility(View.VISIBLE);
            final Integer[] cDocente = {0};
            final Integer[] pDocente = {0};
            listDocente.forEach(persona ->{
                if(persona.getRut_persona().equals(aCambiar.getPersona_rut_persona())){
                    pDocente[0] = cDocente[0];
                }
                cDocente[0] = cDocente[0] +1;
                });
            sp_docentes.setSelection(pDocente[0]);
            sp_docentes.setEnabled(false);

            final Integer[] cSede = {0};
            final Integer[] pSede = {0};
            listSede.forEach(sede ->{
                Integer x =0;
                if(sede.getId()== null){
                    x= -1;
                }else{
                    x = sede.getId();
                }
                if(x.equals(aCambiar.getSede_id_sede())){
                    pSede[0] = cSede[0];
                }
                cSede[0] = cSede[0] +1;
            });
            sp_sede.setSelection(pSede[0]);
            final Integer[] cMateria = {0};
            final Integer[] pMateria = {0};
            listAsignatura.forEach(materia ->{
                if(materia.getId().equals(aCambiar.getAsignatura_id_asignatura())){
                    pMateria[0] = cMateria[0];
                }
                cMateria[0] = cMateria[0] +1;
            });
            sp_materia.setSelection(pMateria[0]);
            String[] split = String.valueOf(aCambiar.getFecha_actividad()).split("-");
            String fecha = split[2]+"/"+split[1]+"/"+split[0];

            tv_fecha.setText(fecha);
            tv_hora_inicio.setText(aCambiar.getHoraini_actividad());
            tv_hora_termino.setText(aCambiar.getHorafin_actividad());

            btn_guardar.setText("ACTUALIZAR");
            btn_guardar.setOnClickListener(v -> {
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
                    actividad.updateActividad(actividadAEnviar);
//                    limpiar();
                    Toast.makeText(context,"Datos actualizados", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, ActividadesActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(context,"Revise los datos ingresados", Toast.LENGTH_LONG).show();
                }

            });
        }
    }
    public void limpiar(){
        tv_fecha.setText("Fehca actividad");
        tv_hora_inicio.setText("Hora inicio");
        tv_hora_termino.setText("Hora termino");
        sp_docentes.setSelection(0);
        sp_sede.setSelection(0);
        sp_materia.setSelection(0);
    }
}
