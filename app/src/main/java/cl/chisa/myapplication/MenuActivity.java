package cl.chisa.myapplication;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cl.chisa.myapplication.bd.DBConnection;
import cl.chisa.myapplication.bd.clases.Estado;
import cl.chisa.myapplication.bd.clases.Persona;
import cl.chisa.myapplication.bd.clases.Rol;
import cl.chisa.myapplication.bd.utilidades.ToExcel;

public class MenuActivity extends DBConnection {
    Persona persona;
    Rol rol;
    Estado estado;
    Application app;
    Button btn_actividades, btn_sedes, btn_asignaturas, btn_personas, btn_mis_datos, btn_salir;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        btn_actividades = findViewById(R.id.btn_actividades);
        btn_sedes = findViewById(R.id.btn_sedes);
        btn_asignaturas = findViewById(R.id.btn_asignaturas);
        btn_personas = findViewById(R.id.btn_personas);
        btn_mis_datos = findViewById(R.id.btn_mis_datos);
        btn_salir = findViewById(R.id.btn_salir);

        app = (Application) getApplicationContext();
        persona = getIntent().getParcelableExtra("persona");
        rol = getIntent().getParcelableExtra("rol");
        estado = getIntent().getParcelableExtra("estado");

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

        //Log.e("rut", persona.getRut_persona());
       // Rol rol = persona.getRol();
        Log.e("rol??", rol.getId().toString());
        switch (rol.getId()) {
            case 0:
                btn_actividades.setVisibility(View.VISIBLE);
                btn_sedes.setVisibility(View.VISIBLE);
                btn_asignaturas.setVisibility(View.VISIBLE);
                btn_personas.setVisibility(View.VISIBLE);
                btn_mis_datos.setVisibility(View.VISIBLE);
                break;
            case 1:
                btn_actividades.setVisibility(View.GONE);
                btn_sedes.setVisibility(View.VISIBLE);
                btn_asignaturas.setVisibility(View.VISIBLE);
                btn_personas.setVisibility(View.GONE);
                btn_mis_datos.setVisibility(View.VISIBLE);
                break;
            case 2:
                btn_actividades.setVisibility(View.GONE);
                btn_sedes.setVisibility(View.GONE);
                btn_asignaturas.setVisibility(View.GONE);
                btn_personas.setVisibility(View.GONE);
                btn_mis_datos.setVisibility(View.GONE);
                break;

        }
    }
}
