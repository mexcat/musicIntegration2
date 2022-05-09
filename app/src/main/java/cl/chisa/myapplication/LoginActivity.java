package cl.chisa.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cl.chisa.myapplication.bd.DBConnection;
import cl.chisa.myapplication.bd.clases.Persona;
import cl.chisa.myapplication.bd.llamadas.ConsultasSql;

public class LoginActivity extends DBConnection {
    Button b1, b2;
    EditText ed1, ed2;
    TextView tx1;
    Bundle extras;
    Persona personaData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        b1 = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);

        DoOnThread();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                ConsultasSql consulta = new ConsultasSql();
                                Persona mResult = consulta.login(ed1.getText().toString(), ed2.getText().toString());
                                if (!mResult.getRut_persona().isEmpty()) {
                                    Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                                    i.putExtra("persona", mResult);
;                                   startActivity(i);
                                }else{
                                        Toast.makeText(LoginActivity.this, "Error usuario/contraseña. Intente nuevamente",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                };
                thread.start();
            }
        });
    }

    public void DoOnThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                String mResult = Query_Version();
                Log.e("MYSQL Version", mResult);
            }
        };
        thread.start();
    }
}
