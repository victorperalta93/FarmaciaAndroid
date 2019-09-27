package ugr.victorperalta.sufarmacia;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,OnRequestPermissionsResultCallback,GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private boolean mLocationPermissionsGranted;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mLocationPermissionsGranted = false;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getDeviceLocation();

        mMap.setOnInfoWindowClickListener(this);
    }

    /**
     * Obtiene la localización actual del gps y opera con el mapa moviendo la cámara a dicha posición y creando los marcadores para las farmacias
     *
     * Los marcadores son creados en posiciones cercanas a la posición generada, no representan farmacias reales.
     */
    private void getDeviceLocation(){
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        getLocationPermissions();

        try{
            if (mLocationPermissionsGranted){ // si obtuvo permisos de localizacion...

                Task<Location> location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener<Location>() {
                    // toda la lógica del mapa va dentro de este método ya que se lanza una hebra para dicho método
                    // y no se comienza la recepción de información hasta que la task finalize
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if(task.isSuccessful()){
                            // guardar localización actual
                            Location currentLocation = task.getResult();
                            LatLng actual = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());

                            // generar localizaciones de farmacias con respecto a localización actual
                            LatLng farm1 = new LatLng(currentLocation.getLatitude()+.004,currentLocation.getLongitude()+.004);
                            LatLng farm2 = new LatLng(currentLocation.getLatitude()-.002,currentLocation.getLongitude()-.002);
                            LatLng farm3 = new LatLng(currentLocation.getLatitude()-.002,currentLocation.getLongitude()+.002);
                            LatLng farm4 = new LatLng(currentLocation.getLatitude()+.002,currentLocation.getLongitude()-.002);
                            LatLng farm5 = new LatLng(currentLocation.getLatitude()+.003,currentLocation.getLongitude()+.003);

                            // añadir marcadores en las localizaciones de las farmacias
                            mMap.addMarker(new MarkerOptions().position(farm1).title("Farmacia 1").snippet("Click aquí para reservar compra"));
                            mMap.addMarker(new MarkerOptions().position(farm2).title("Farmacia 2").snippet("Click aquí para reservar compra"));
                            mMap.addMarker(new MarkerOptions().position(farm3).title("Farmacia 3").snippet("Click aquí para reservar compra"));
                            mMap.addMarker(new MarkerOptions().position(farm4).title("Farmacia 4").snippet("Click aquí para reservar compra"));
                            mMap.addMarker(new MarkerOptions().position(farm5).title("Farmacia 5").snippet("Click aquí para reservar compra"));

                            // mover camara a la localización actual
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(actual));
                            mMap.setMyLocationEnabled(true);
                            //mMap.addMarker(new MarkerOptions().position(actual).title("Posicion actual"));

                            // ajustes de cámara
                            CameraPosition campos = new CameraPosition.Builder()
                                    .target(actual)
                                    .zoom(16)
                                    .bearing(45)
                                    .build();
                            CameraUpdate camUp13 = CameraUpdateFactory.newCameraPosition(campos);
                            mMap.animateCamera(camUp13);
                        }
                    }
                });
            }
        }catch (SecurityException e){
            // ignorar
        }
    }

    /**
     * trata de obtener permisos para leer la información de localización del gps del dispositivo
     */
    private void getLocationPermissions(){
        String [] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
            }
            else{
                ActivityCompat.requestPermissions(this,permissions,LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
        else{
            ActivityCompat.requestPermissions(this,permissions,LOCATION_PERMISSION_REQUEST_CODE);
        }
    }


    // este método se ejecuta cuando el usuario ha dado los permisos de acceso a localización (si se ha pedido)
    // en tal caso llama a getDeviceLocation para actualizar la posición
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        getDeviceLocation();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if(marker.getTitle().contains("Farmacia")){
            showActionsDialog();
        }
    }

    private void showActionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Finalizar Compra").setMessage("¿Desea finalizar la reserva en la farmacia seleccionada?");

        // Add the buttons
        builder.setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // TODO: logic
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });


        builder.show();
    }
}