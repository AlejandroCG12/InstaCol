package bases;

/**
 *
 * @author Momo
 */
import Controller.Imagen;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class BaseDatos {

     

    Connection conexion;
    Statement st;

    public BaseDatos() {
        //conexion
    }

    public Connection getConexion() {
        return conexion;
    }

    /**
     * Método utilizado para establecer la conexión con la base de datos
     *
     * @return estado regresa el estado de la conexión, true si se estableció la
     * conexión, falso en caso contrario
     */
    public boolean crearConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");                                      //user  //pass
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/instacol", "InstaColAdmin", "instacoladmin");
            st = conexion.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     *
     * Método utilizado para realizar las instrucciones: INSERT, DELETE y UPDATE
     *
     * @param sql Cadena que contiene la instrucción SQL a ejecutar
     * @return estado regresa el estado de la ejecución, true(éxito) o
     * false(error)
     *     
*/
    public boolean ejecutarSQL(String sql) {
        try {
            Statement sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     *
     * Método utilizado para realizar la instrucción SELECT
     *
     * @param sql Cadena que contiene la instrucción SQL a ejecutar
     * @return resultado regresa los registros generados por la consulta
     *     
*/
    public String ejecutarSQLSelect(String sql) {
        ResultSet rs;
        int id;
        String nom = "";
        String tel = "";
        String dir = "";
        String concatenar = "";

        try {
            Statement sentencia = conexion.createStatement();
            rs = sentencia.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt(1);
                nom = rs.getNString("nombreusuario");
                tel = rs.getNString("cedulausuario");
                dir = rs.getNString("celusuario");

                concatenar = id + " " + nom + " " + tel + " " + dir;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return concatenar;
    }

    public boolean sqlInsert(String insert,String Nombre_usuario, String Apellido_usuario, String Correo, String Clave, String Fecha_nacimiento,String Id_usuario) {
        // String insert = "insert into Imagenes(imagen,nombre) values(?,?)";
        PreparedStatement ps = null;
        try {
            conexion.setAutoCommit(false);
            ps = conexion.prepareStatement(insert);
            ps.setString(1, Nombre_usuario);
            ps.setString(2, Apellido_usuario);
            ps.setString(3, Correo);
            ps.setString(4, Clave);
            ps.setString(5, Fecha_nacimiento);
            ps.setString(6, Id_usuario);
            //ps.setString(2, nombre);
            ps.executeUpdate();
            conexion.commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public boolean sqlInsertWithImage(String ruta, String insert) {
        // String insert = "insert into Imagenes(imagen,nombre) values(?,?)";
        FileInputStream fis = null;
        PreparedStatement ps = null;
        try {
            conexion.setAutoCommit(false);
            File file = new File(ruta);
            fis = new FileInputStream(file);
            ps = conexion.prepareStatement(insert);
            ps.setString(1, "1135");
            ps.setString(2, "David");
            ps.setString(3, "Benavides");
            ps.setString(4, "30156");
            ps.setBinaryStream(5, fis, (int) file.length());
            //ps.setString(2, nombre);
            ps.executeUpdate();
            conexion.commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                fis.close();
            } catch (Exception ex) {
                Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public ArrayList buscarCorreo(String buscar) throws IOException {
        ArrayList arrElementos = new ArrayList();
        String cadena = "";
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios WHERE correo='" + buscar + "'");
            while (rs.next()) {
                String nombreUsuario = rs.getObject("nombre_usuario").toString();
                String apellidoUsuario = rs.getObject("apellido_usuario").toString();
                String correoUsuario = rs.getObject("correo").toString();
                String claveUsuario = rs.getObject("clave").toString();
                String fechaNacimientoUsuario = rs.getObject("fecha_nacimiento").toString();
                String idUsuario = rs.getObject("id_usuario").toString();

                arrElementos.add(nombreUsuario);
                arrElementos.add(apellidoUsuario);
                arrElementos.add(correoUsuario);
                arrElementos.add(claveUsuario);
                arrElementos.add(fechaNacimientoUsuario);
                arrElementos.add(idUsuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrElementos;
    }
    
     public void sqlDelete(String eliminar) throws IOException{
        String sql = "DELETE FROM usuarios WHERE id_usuario=" + eliminar +"";
        PreparedStatement ps;
        try{
            ps=conexion.prepareStatement(sql);
            ps.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
