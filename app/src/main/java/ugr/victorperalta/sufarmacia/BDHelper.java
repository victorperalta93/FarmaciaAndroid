package ugr.victorperalta.sufarmacia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

// Clase obligatoria para el uso de SQLite
// Se encarga de generar la base de datos y de realizar cualquier conexión directa.
// esta clase es de tipo Singleton, ésto asegura que sólo se crea una instancia de la clase
// lo que proporciona más eficiencia cuando queramos acceder a ella
// ----------------------------------------------------------------------------------------
// me preocupaba el uso excesivo de getWritableDatabase() y getReadableDatabase() pero
// tras investigar sobre estas llamadas, parece que Android se ocupa de hacerlas
// eficientes guardando la base de datos en caché
public class BDHelper extends SQLiteOpenHelper{
    private static BDHelper instancia;
    private static final int DATABASE_VERSION = 7; // cada vez que se cambia este valor la bd se crea de nuevo
    private static final String DATABASE_NOMBRE = "BD_farmacia";

    // constructor privado para evitar instanciación
    private BDHelper(Context context){
        super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
    }

    // devuelve la instancia única de la clase
    public static synchronized BDHelper getInstancia(Context context){
        if(instancia == null){
            instancia = new BDHelper(context.getApplicationContext());
        }

        return instancia;
    }

    @Override
    // crea las tablas de productos y usuarios
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Usuario.CREATE_TABLE);
        db.execSQL(Producto.CREATE_TABLE);
    }

    @Override
    // TODO: Provisional
    // CUIDADO : ESTE MÉTODO ELIMINA TODAS LAS TABLAS DE LA BASE DE DATOS
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // eliminar tabla si existe
        db.execSQL("DROP TABLE IF EXISTS " + Usuario.NOMBRE_TABLA);
        db.execSQL("DROP TABLE IF EXISTS " + Producto.NOMBRE_TABLA);

        // crear la tabla de nuevo
        onCreate(db);
    }

    // DEBUG : añade productos a la base de datos, SOLO EJECUTAR UNA VEZ
    // TODO: Eliminar esta función cuando no haga falta
    private void addProductos(){
        SQLiteDatabase db = this.getWritableDatabase();

        String count = "SELECT count(*) FROM " + Producto.NOMBRE_TABLA;


        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);

        mcursor.close();

        if(icount==0){
            ContentValues p1 = new ContentValues();
            ContentValues p2 = new ContentValues();
            ContentValues p3 = new ContentValues();
            ContentValues p4 = new ContentValues();
            ContentValues p5 = new ContentValues();
            ContentValues p6 = new ContentValues();
            ContentValues p7 = new ContentValues();
            ContentValues p8 = new ContentValues();
            ContentValues p9 = new ContentValues();
            ContentValues p10 = new ContentValues();

            p1.put(Producto.COLUMNA_ID,1);
            p1.put(Producto.COLUMNA_NOMBRE,"Multicentrum Mujer (30 comprimidos)");
            p1.put(Producto.COLUMNA_LAB,"Multicentrum");
            p1.put(Producto.COLUMNA_PATOLOGIA,"Multivitamínico especialmente formulado para mujeres.");
            p1.put(Producto.COLUMNA_PRECIO,"12,00€");

            p2.put(Producto.COLUMNA_ID,2);
            p2.put(Producto.COLUMNA_NOMBRE,"Fisiogen Ferro Forte (30 cápsulas)");
            p2.put(Producto.COLUMNA_LAB,"Zambon");
            p2.put(Producto.COLUMNA_PATOLOGIA,"Una solución para mejorar los niveles de hierro en estados carenciales, y con una composición que minimiza los efectos secundarios.");
            p2.put(Producto.COLUMNA_PRECIO,"22,00€");

            p3.put(Producto.COLUMNA_ID,3);
            p3.put(Producto.COLUMNA_NOMBRE,"Solución Fisiológica Cinfa PH Neutro (30 unidosis)");
            p3.put(Producto.COLUMNA_LAB,"Cinfa");
            p3.put(Producto.COLUMNA_PATOLOGIA,"Para la higiene nasal y oftálmica. Solución tampón, no inyectable.");
            p3.put(Producto.COLUMNA_PRECIO,"4,00€");

            p4.put(Producto.COLUMNA_ID,4);
            p4.put(Producto.COLUMNA_NOMBRE,"Calmatopic Roll-on");
            p4.put(Producto.COLUMNA_LAB,"Calmatopic");
            p4.put(Producto.COLUMNA_PATOLOGIA,"Esta crema en roll-on está especialmente pensada para calmar, aliviar y refrescar la piel irritada o sensible.");
            p4.put(Producto.COLUMNA_PRECIO,"7€");
            p5.put(Producto.COLUMNA_ID,5);

            p5.put(Producto.COLUMNA_NOMBRE,"Arkorelax Sueño (30 comprimidos)");
            p5.put(Producto.COLUMNA_LAB,"Arkopharma");
            p5.put(Producto.COLUMNA_PATOLOGIA,"Complemento alimenticio que, gracias a su composición natural, ayuda a conciliar un sueño totalmente reparador");
            p5.put(Producto.COLUMNA_PRECIO,"10€");

            p6.put(Producto.COLUMNA_ID,6);
            p6.put(Producto.COLUMNA_NOMBRE,"Aquilea Artinova Colágeno + Magnesio Sabor Limón (375g)");
            p6.put(Producto.COLUMNA_LAB,"Aquilea");
            p6.put(Producto.COLUMNA_PATOLOGIA,"Complemento alimenticio a base de colágeno y magnesio que favorece el buen estado de huesos y articulaciones");
            p6.put(Producto.COLUMNA_PRECIO,"20€");

            p7.put(Producto.COLUMNA_ID,7);
            p7.put(Producto.COLUMNA_NOMBRE,"Utipro Plus (15 cápsulas)");
            p7.put(Producto.COLUMNA_LAB,"Ferrer");
            p7.put(Producto.COLUMNA_PATOLOGIA,"Cápsulas para el control y prevención de infecciones urinarias.");
            p7.put(Producto.COLUMNA_PRECIO,"18€");

            p8.put(Producto.COLUMNA_ID,8);
            p8.put(Producto.COLUMNA_NOMBRE,"Redoxon Go Vitamina C (30 comprimidos)");
            p8.put(Producto.COLUMNA_LAB,"Redoxon");
            p8.put(Producto.COLUMNA_PATOLOGIA,"Complemento alimenticio sin necesidad de agua que refuerza el sistema inmunitario.");
            p8.put(Producto.COLUMNA_PRECIO,"15€");

            p9.put(Producto.COLUMNA_ID,9);
            p9.put(Producto.COLUMNA_NOMBRE,"Eladiet Fitotablet Guaraná (60 comprimidos)");
            p9.put(Producto.COLUMNA_LAB,"Eladiet");
            p9.put(Producto.COLUMNA_PATOLOGIA,"Ayuda a sentirte con más energía y a reducir la fatiga mental.");
            p9.put(Producto.COLUMNA_PRECIO,"8€");

            p10.put(Producto.COLUMNA_ID,10);
            p10.put(Producto.COLUMNA_NOMBRE,"XLS Medical Mantenimiento (180 comprimidos)");
            p10.put(Producto.COLUMNA_LAB,"XLS Medical");
            p10.put(Producto.COLUMNA_PATOLOGIA,"Complemento alimenticio que ayuda a mantener un estilo de vida saludable.");
            p10.put(Producto.COLUMNA_PRECIO,"45€");

            db.insert(Producto.NOMBRE_TABLA,null,p1);
            db.insert(Producto.NOMBRE_TABLA,null,p2);
            db.insert(Producto.NOMBRE_TABLA,null,p3);
            db.insert(Producto.NOMBRE_TABLA,null,p4);
            db.insert(Producto.NOMBRE_TABLA,null,p5);
            db.insert(Producto.NOMBRE_TABLA,null,p6);
            db.insert(Producto.NOMBRE_TABLA,null,p7);
            db.insert(Producto.NOMBRE_TABLA,null,p8);
            db.insert(Producto.NOMBRE_TABLA,null,p9);
            db.insert(Producto.NOMBRE_TABLA,null,p10);
        }

        db.close();
    }

    // añade un usuario a la base de datos
    public boolean addUsuario(String correo, String password){
        if(!existeUsuario(correo)){
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Usuario.COLUMNA_CORREO,correo);
            values.put(Usuario.COLUMNA_PASSWORD,password);

            db.insert(Usuario.NOMBRE_TABLA,null,values);
            db.close();

            return true;
        }
        else{
            return false;
        }
    }

    // devuelve una lista con todos los productos
    public ArrayList<Producto> getProductos(){
        addProductos();


        ArrayList<Producto> productos = new ArrayList<>();



        String selectQuery = "SELECT * FROM " + Producto.NOMBRE_TABLA + " ORDER BY " +
                Producto.COLUMNA_ID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor != null && cursor.moveToFirst()){
            do{
                Producto producto = new Producto(
                        cursor.getInt(cursor.getColumnIndex(Producto.COLUMNA_ID)),
                        cursor.getString(cursor.getColumnIndex(Producto.COLUMNA_NOMBRE)),
                        cursor.getString(cursor.getColumnIndex(Producto.COLUMNA_LAB)),
                        cursor.getString(cursor.getColumnIndex(Producto.COLUMNA_PATOLOGIA)),
                        cursor.getString(cursor.getColumnIndex(Producto.COLUMNA_PRECIO)));

                productos.add(producto);
            }while(cursor.moveToNext());

            cursor.close();
        }

        db.close();

        return productos;
    }

    // devuelve un objeto de la Clase usuario con la contraseña
    // del correo indicado
    public Usuario getUsuario(String correo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Usuario.NOMBRE_TABLA,
                new String[]{Usuario.COLUMNA_CORREO, Usuario.COLUMNA_PASSWORD},
                Usuario.COLUMNA_CORREO + "=?", new String[]{correo},
                null,null,null,null);


        Usuario usuario = null;
        if (cursor != null && cursor.moveToFirst()){
            usuario = new Usuario(
                cursor.getString(cursor.getColumnIndex(Usuario.COLUMNA_CORREO)),
                cursor.getString(cursor.getColumnIndex(Usuario.COLUMNA_PASSWORD)));

            cursor.close();
        }

        return usuario;
    }

    // comprueba si un usuario existe un la base de datos
    // devuelve false si no existe.
    private boolean existeUsuario(String correo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Usuario.NOMBRE_TABLA,
                new String[]{Usuario.COLUMNA_CORREO, Usuario.COLUMNA_PASSWORD},
                Usuario.COLUMNA_CORREO + "=?", new String[]{correo},
                null,null,null,null);

        if (cursor != null && cursor.getCount() >= 1){
            cursor.moveToFirst();

            String correodb = cursor.getString(cursor.getColumnIndex(Usuario.COLUMNA_CORREO));

            cursor.close();
            return correo.equals(correodb);
        }
        else
            return false;
    }

    // compara la contraseña dada en la aplicación con la contraseña
    // del usuario en la base de datos
    public boolean conectar(String correo,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Usuario.NOMBRE_TABLA,
                new String[]{Usuario.COLUMNA_CORREO, Usuario.COLUMNA_PASSWORD},
                Usuario.COLUMNA_CORREO + "=?", new String[]{correo},
                null,null,null,null);

        if (cursor != null && cursor.getCount() >= 1){
            cursor.moveToFirst();

            String passwordbd = cursor.getString(cursor.getColumnIndex(Usuario.COLUMNA_PASSWORD));

            cursor.close();
            return password.equals(passwordbd);
        }
        else
            return false;
    }
}