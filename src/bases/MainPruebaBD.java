/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

/**
 *
 * @author Alejo
 */
public class MainPruebaBD {

    //Comentario 1
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        BaseDatos objBases = new BaseDatos();
        boolean conexion;
        
        
        String sql = "", ruta = "";
        conexion = objBases.crearConexion();
        if (conexion) {
            //sql="insert into profesores (identificacion, nombre, apellido, telefono) values('4567','Pedro','Benavides', '12121')";
            //sql="insert into cursos (nombrecurso,codigocurso,idprofesorcurso) values('Programación Orientada a Objetos','3456','1130')";
            sql = "insert into profesores (idprofesores,nombreprofesor,apellidoprofesor,telefonoprofesor,imagenp) values(?,?,?,?,?)";
            ruta = "C:\\Users\\Alejo\\Pictures\\david.JPG";
            boolean ejecutar = objBases.sqlInsertWithImage(ruta, sql);
            //objBases.ejecutarSQL(sql);
            if (ejecutar) {
                System.out.println("Se inserto de manera exitosa");
            } else {
                System.out.println("no se pudo realizar la conexión");
            }
        } else {
            System.out.println("no se pudo realizar la conexión");
        }
    }

}
