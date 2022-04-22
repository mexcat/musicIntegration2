package cl.chisa.myapplication;

import android.os.Bundle;

import cl.chisa.myapplication.bd.DBConnection;

public class ActividadesActivity extends DBConnection {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividades_layout);
    }
}
