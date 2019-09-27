package ugr.victorperalta.sufarmacia;

// clase encargada de almacenar la información sobre usuarios
// TODO: eliminar esta clase y toda la funcionalidad interna de usuarios
// TODO: una vez utilice el servidor para la autenticación de los mismos
public class Usuario {
    public static final String NOMBRE_TABLA = "usuarios";
    public static final String COLUMNA_CORREO = "correo";
    public static final String COLUMNA_PASSWORD = "contraseña";
    public static final String CREATE_TABLE =     // Query de la tabla SQL
            "CREATE TABLE " + NOMBRE_TABLA + "("
                    + COLUMNA_CORREO + " TEXT PRIMARY KEY,"
                    + COLUMNA_PASSWORD + " TEXT"
                    + ")";

    private String correo;
    private String password;

    public Usuario(String correo, String password){
        this.correo = correo;
        this.password = password;
    }

    public String getCorreo(){ return correo; }
    public String getPassword(){ return password; }
}