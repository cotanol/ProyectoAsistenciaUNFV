package Model;

import Util.Conexion_BD;
import View.VentanaPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AsignaturaModelo {
    
    private int idAsignatura;
    private String nombre;
    private String codigo;

    VentanaPrincipal objVentReg;
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null; 
    
    public AsignaturaModelo(VentanaPrincipal objventReg) {
        this.objVentReg = objVentReg;
    }
    public AsignaturaModelo(){
        
    }
        public int insertarAsignaturaModelo(AsignaturaModelo asignaturaModelo) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO asignatura (nombre, codigo) VALUES (?,?);");
            pt.setString(1, asignaturaModelo.getNombre());
            pt.setString(2, asignaturaModelo.getCodigo());
            estado = pt.executeUpdate();
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarAsignaturaModelo(AsignaturaModelo asignaturaModelo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE asignatura SET nombre = ?, codigo = ? WHERE codigo = ?;");
            pt.setString(1, asignaturaModelo.getNombre());
            pt.setString(2, asignaturaModelo.getCodigo());
            estado = pt.executeUpdate();
            cn.close();
            pt.close();
            rs.close();
     
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarAsignaturaModelo(AsignaturaModelo asignaturaModelo) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM asignatura WHERE codigo = ?;");
            pt.setString(1, asignaturaModelo.getCodigo());
            estado = pt.executeUpdate();
            cn.close();
            pt.close();
            rs.close();
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
        public ArrayList<AsignaturaModelo> enlistarAsignaturaModelo() {
        
        ArrayList<AsignaturaModelo> listaAsignaturas = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM asignatura;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                AsignaturaModelo asignaturaModelo = new AsignaturaModelo();
                asignaturaModelo.setIdAsignatura(rs.getInt("id_asignatura"));
                asignaturaModelo.setNombre((rs.getString("nombre")));
                asignaturaModelo.setCodigo(rs.getString("codigo"));
                listaAsignaturas.add(asignaturaModelo);
            }         
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){  
        }
        return listaAsignaturas;
    }
    
    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }  
}
