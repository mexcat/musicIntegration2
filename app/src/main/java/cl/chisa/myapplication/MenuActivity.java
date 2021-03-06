package cl.chisa.myapplication;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import java.util.Vector;

import cl.chisa.myapplication.bd.DBConnection;
import cl.chisa.myapplication.bd.clases.Actividad;
import cl.chisa.myapplication.bd.clases.Excel;
import cl.chisa.myapplication.bd.clases.Persona;
import cl.chisa.myapplication.bd.llamadas.ConsultasSql;
import cl.chisa.myapplication.bd.utilidades.ToExcel;

public class MenuActivity extends DBConnection {
    Persona persona;
    Application app;
    Button btn_actividades, btn_sedes, btn_asignaturas, btn_personas, btn_mis_datos, btn_salir, btn_exportar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        btn_actividades = findViewById(R.id.btn_actividades);
        btn_sedes = findViewById(R.id.btn_sedes);
        btn_asignaturas = findViewById(R.id.btn_asignaturas);
        btn_personas = findViewById(R.id.btn_personas);
        btn_mis_datos = findViewById(R.id.btn_mis_datos);
        btn_salir = findViewById(R.id.btn_salir);
        btn_exportar = findViewById(R.id.btn_exportar);

        app = (Application) getApplicationContext();
        persona = getIntent().getParcelableExtra("persona");

        String[] strings = new String[2];
        strings[0] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        strings[1] = Manifest.permission.READ_EXTERNAL_STORAGE;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, strings, 1000
            );
        }



        btn_actividades.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), ActividadesActivity.class);
                i.putExtra("persona", persona);
                startActivity(i);
            }
        });
        btn_mis_datos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              /*  try {
                    ToExcel.writeExcel(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
        });

        btn_exportar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    Vector<Excel> info = new Vector();
                    ConsultasSql consulta = new ConsultasSql();

                    switch (persona.getRol_id()) {
                        case 1:
                        case 2:
                            info = consulta.exportAllToExcel();
                            break;
                        case 4:
                            info = consulta.exportDocenteToExcel(String.valueOf(persona.getRut_persona()));
                    }
                        String[] headers = new String[]{
                            "Fecha", "Rut", "Docente", "Sede", "Asignatura", "Inicio", "Termino", "Horas"
                    };
                    ToExcel.writeExcel(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString(), headers, info, MenuActivity.this );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn_salir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i2 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i2);
            }
        });


        switch (persona.getRol_id()) {
            case 1:
                btn_actividades.setVisibility(View.VISIBLE);
                btn_sedes.setVisibility(View.VISIBLE);
                btn_asignaturas.setVisibility(View.VISIBLE);
                btn_personas.setVisibility(View.VISIBLE);
                btn_mis_datos.setVisibility(View.VISIBLE);
                btn_exportar.setVisibility(View.VISIBLE);
                break;
            case 2:
                btn_actividades.setVisibility(View.VISIBLE);
                btn_sedes.setVisibility(View.GONE);
                btn_asignaturas.setVisibility(View.GONE);
                btn_personas.setVisibility(View.GONE);
                btn_mis_datos.setVisibility(View.GONE);
                btn_exportar.setVisibility(View.VISIBLE);
                break;
            case 3:
                btn_actividades.setVisibility(View.GONE);
                btn_sedes.setVisibility(View.GONE);
                btn_asignaturas.setVisibility(View.GONE);
                btn_personas.setVisibility(View.GONE);
                btn_mis_datos.setVisibility(View.GONE);
                btn_exportar.setVisibility(View.VISIBLE);
                break;
            case 4:
                btn_actividades.setVisibility(View.VISIBLE);
                btn_sedes.setVisibility(View.GONE);
                btn_asignaturas.setVisibility(View.GONE);
                btn_personas.setVisibility(View.GONE);
                btn_mis_datos.setVisibility(View.GONE);
                btn_exportar.setVisibility(View.VISIBLE);
                break;

        }
    }

    public void editar(Actividad actividad, Context contexto){
        Intent i = new Intent(contexto, ActividadesActivity.class);
        i.putExtra("actividad", actividad);
        contexto.startActivity(i);
    }
}
