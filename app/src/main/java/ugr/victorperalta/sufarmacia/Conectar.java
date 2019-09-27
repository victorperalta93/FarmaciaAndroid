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

// Actividad inicial
// conexión a la aplicación mediante usuario y contraseña
public class Conectar extends AppCompatActivity {
    private BDHelper db; // objeto de la bd

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actividad_login_mas,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conectar);
        db = BDHelper.getInstancia(this);

        // añadir barra superior y modificar titulo
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Iniciar Sesión");
        setSupportActionBar(myToolbar);


        // guardar los TextView como variables
        final TextView correo = findViewById(R.id.conectarCorreo);
        final TextView password = findViewById(R.id.conectarContraseña);
        final TextView log = findViewById(R.id.logConectar);

        // EventListener para el boton 'Iniciar Sesión'
        final Button conectarse = findViewById(R.id.iniciarSesion);
        conectarse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // solo pasar a la siguiente actividad si la conexión ha sido correcta
                if(db.conectar(correo.getText().toString(),password.getText().toString())){
                    Intent irAListaProductos = new Intent(getApplicationContext(), ListaProductos.class);
                    startActivity(irAListaProductos);
                }
                else{
                    log.setText(R.string.error_conexion);
                }
            }
        });

        // EventListener para cambiar a registrar
        final TextView cambiarRegistrar = findViewById(R.id.cambiarRegistrar);
        cambiarRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crearUsuarioIntent = new Intent(getApplicationContext(), Registrar.class);
                startActivity(crearUsuarioIntent);
            }
        });
    }
}
