package ugr.victorperalta.sufarmacia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class RegistrarExito extends AppCompatActivity {
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
        setContentView(R.layout.activity_registrar_exito);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Registrarse");
        setSupportActionBar(myToolbar);


        final Button volver = findViewById(R.id.volver_conectar);
        volver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent volverIntent = new Intent(getApplicationContext(), Conectar.class);
                startActivity(volverIntent);
            }
        });
    }
}
