package ugr.victorperalta.sufarmacia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Registrar extends AppCompatActivity {
    private BDHelper db;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actividad_registrar_mas,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        db = BDHelper.getInstancia(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Registrarse");
        setSupportActionBar(myToolbar);


        final TextView correo = findViewById(R.id.registrarCorreo);
        final TextView password = findViewById(R.id.registrarContraseña);
        final TextView log = findViewById(R.id.logCrearUsuario);

        final Button registrarse = findViewById(R.id.registrar);
        registrarse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(db.addUsuario(correo.getText().toString(),password.getText().toString())){
                    Intent exitoIntent = new Intent(getApplicationContext(), RegistrarExito.class);
                    startActivity(exitoIntent);
                }
                else {
                    log.setText(R.string.error_usuario_existe);
                }
            }
        });

        final TextView cambiarConectar = findViewById(R.id.cambiarConectar);
        cambiarConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cambiarConectarIntent = new Intent(getApplicationContext(), Conectar.class);
                startActivity(cambiarConectarIntent);
            }
        });
    }
}
