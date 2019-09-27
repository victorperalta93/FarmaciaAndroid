package ugr.victorperalta.sufarmacia;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

// Actividad para mostrar la lista de productos en un RecyclerView
public class ListaProductos extends AppCompatActivity {
    private RecyclerView mRecyclerView; // RecyclerView es una versión más avanzada y flexible que ListView
    private RecyclerView.Adapter mAdapter; // organiza cada objeto de ViewHolder
    private RecyclerView.LayoutManager mLayoutManager;     // el LayoutManager proporciona la vista, puede ser Linear o Grid (o custom).
    private BDHelper db;
    private ArrayList<Producto> productos;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actividad_productos_mas,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Lista de Productos");
        setSupportActionBar(myToolbar);

        mRecyclerView = findViewById(R.id.vistaListaProductos);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // guardar productos de la base de datos
        db = BDHelper.getInstancia(this);
        productos = db.getProductos();

        // guardar id de imagen en cada producto
        for (Producto producto: productos) {
            String nombre = "p" + producto.getID();

            producto.setResource(getResources().getIdentifier(nombre,"drawable", getPackageName()));
        }

        // linearLayout
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // especificar adapter y añadir clicklistener (para añadir a cesta)
        mAdapter = new ListaProductosAdapter(productos, new ClickListener() {
            @Override public void onPositionClicked(int position) {
                showActionsDialog(position);
            }

            @Override public void onLongClicked(int position) {
                // No lo necesito
            }
        });

        mRecyclerView.setAdapter(mAdapter);

        // añade una linea entre cada item de la lista para separarlos
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    // ventana emergente al seleccionar un producto
    private void showActionsDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Comprar producto").setMessage("¿Desea comprar este producto?");

        // botones de ventana emergente
        builder.setPositiveButton("Comprar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent irAMapa = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(irAMapa);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // ------------------
            }
        });

        builder.show();
    }
}
