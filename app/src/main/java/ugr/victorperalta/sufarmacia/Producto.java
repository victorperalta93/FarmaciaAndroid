package ugr.victorperalta.sufarmacia;


import android.content.res.Resources;
import android.media.Image;
import android.widget.ImageView;

// esta clase almacena información sobre productos farmacéuticos
// su funcionalidad principal es crear una capa de abstracción sobre la base de datos
// de esta forma, una vez obtenido los datos, no es necesario lidiar más con llamadas a la base de datos
public class Producto {
    public static final String NOMBRE_TABLA = "productos";
    public static final String COLUMNA_ID = "ID";
    public static final String COLUMNA_NOMBRE = "nombre";
    public static final String COLUMNA_LAB = "laboratorio";
    public static final String COLUMNA_PATOLOGIA = "patologia";
    public static final String COLUMNA_PRECIO = "precio";
    // esta variable la utiliza la clase BDHelper para crear la tabla de productos
    // TODO: quizas mover esto a la clase BDHelper
    public static final String CREATE_TABLE =
            "CREATE TABLE " + NOMBRE_TABLA + "("
                    + COLUMNA_ID + " INTEGER PRIMARY KEY,"
                    + COLUMNA_NOMBRE + " TEXT,"
                    + COLUMNA_LAB + " TEXT,"
                    + COLUMNA_PATOLOGIA + " TEXT,"
                    + COLUMNA_PRECIO + " INTEGER"
                    + ")";

    private int id;
    private int id_r;
    private String nombre;
    private String laboratorio;
    private String patologia;
    private String precio;

    Producto(int id, String nombre, String laboratorio, String patologia, String precio){
        this.id = id;
        this.nombre = nombre;
        this.laboratorio = laboratorio;
        this.patologia = patologia;
        this.precio = precio;
    }

    // métodos getters para la información almacenada sobre el producto
    public int getID(){ return id; }
    public String getNombre(){ return nombre; }
    public String getLaboratorio(){ return laboratorio; }
    public String getPatologia(){ return patologia; }
    public String getPrecio(){ return precio; }

    public void setResource(int r){
        this.id_r = r;
    }

    public int getResource(){
        return id_r;
    }
}