/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behind;

import bases.BaseDatos;
import java.util.ArrayList;

/**
 *
 * @author Momo
 */
public class usuario {
    
    private String nombre_usuario;
    private String apellido_usuario;
    private String correo;
    private String clave;
    private String fecha_nacimiento;
    private String id_usuario;

    public usuario() {
    }

    public usuario(String nombre_usuario, String apellido_usuario, String correo, String clave, String fecha_nacimiento, String id_usuario) {
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.correo = correo;
        this.clave = clave;
        this.fecha_nacimiento = fecha_nacimiento;
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "usuario{" + "nombre_usuario=" + nombre_usuario + ", apellido_usuario=" + apellido_usuario + ", correo=" + correo + ", clave=" + clave + ", fecha_nacimiento=" + fecha_nacimiento + ", id_usuario=" + id_usuario + '}';
    }
    
    public boolean insertarUsuario(ArrayList<usuario> arrUsu){
        String sql="";
        BaseDatos objBases=new BaseDatos();
        boolean conexion=false;
        boolean insertar=false;
        
        sql="insert into usuarios (nombre_usuario,apellido_usuario,correo,clave,fecha_nacimiento,id_usuario) values(?,?,?,?,?,?)";
        
        for (usuario arrUsu1 : arrUsu){
            conexion=objBases.crearConexion();
            if (conexion) {
                insertar = objBases.sqlInsert(sql, 
                        arrUsu1.getNombre_usuario(), arrUsu1.getApellido_usuario(),arrUsu1.getCorreo(), 
                        arrUsu1.getClave(), arrUsu1.getFecha_nacimiento(),arrUsu1.getId_usuario());
            }
        }
        return insertar;
    }
}
