package Model;
import java.sql.*;
import java.util.ArrayList;
import Util.Conexion_BD;
import View.VentanaPrincipal;

public class UsuarioFuncionalidadModelo {
    
    private int idUsuarioFuncion;
    private int idUsuario;
    private int numeroLab;
    private String funcionalidad;
    
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    VentanaPrincipal objVentReg;
    
    public UsuarioFuncionalidadModelo(VentanaPrincipal objventReg) {
        this.objVentReg = objVentReg;
    }
    public UsuarioFuncionalidadModelo(){
        
    }
    
    public int insertarUsuarioFuncionalidadModelo(UsuarioFuncionalidadModelo usuarioFuncionalidadModelo) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO usuario_funcionalidad (id_usuario, numero_lab, funcionalidad)  VALUES (?,?,?);");
            pt.setInt(1, usuarioFuncionalidadModelo.getIdUsuario());
            pt.setInt(2, usuarioFuncionalidadModelo.getNumeroLab());
            pt.setString(3, usuarioFuncionalidadModelo.getFuncionalidad());
            
            

            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarUsuarioFuncionalidadModelo(UsuarioFuncionalidadModelo usuarioFuncionalidadModelo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE usuario_funcionalidad SET id_usuario = ?, numero_lab = ?, funcionalidad = ? WHERE id_usuario_funcion = ?;");
            pt.setInt(1, usuarioFuncionalidadModelo.getIdUsuario());
            pt.setInt(2, usuarioFuncionalidadModelo.getNumeroLab());
            pt.setString(3, usuarioFuncionalidadModelo.getFuncionalidad());
            pt.setInt(4, usuarioFuncionalidadModelo.getIdUsuarioFuncion());
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarUsuarioFuncionalidadModelo(UsuarioFuncionalidadModelo usuarioFuncionalidadModelo) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM usuario_funcionalidad WHERE id_usuario_funcion = ?;");
            pt.setInt(1, usuarioFuncionalidadModelo.getIdUsuarioFuncion());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<UsuarioFuncionalidadModelo> enlistarUsuarioFuncionalidadModelo() {
        
        ArrayList<UsuarioFuncionalidadModelo> listaUsuarioFuncionalidades = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM usuario_funcionalidad;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                UsuarioFuncionalidadModelo usuarioFuncionalidadModelo = new UsuarioFuncionalidadModelo();
                usuarioFuncionalidadModelo.setIdUsuarioFuncion(rs.getInt("id_usuario_funcion"));
                usuarioFuncionalidadModelo.setIdUsuario(rs.getInt("id_usuario"));
                usuarioFuncionalidadModelo.setNumeroLab(rs.getInt("numero_lab"));
                usuarioFuncionalidadModelo.setFuncionalidad(rs.getString("funcionalidad"));
                
            
                listaUsuarioFuncionalidades.add(usuarioFuncionalidadModelo);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return listaUsuarioFuncionalidades;
    }
    
    public int ultimoId() {
        int id = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT MAX(id_usuario_funcion) AS max_id FROM usuario;");
            rs = pt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("max_id");
            }

            rs.close();
            pt.close();
            cn.close();

        } catch (Exception e) {
            
        }

        return id; 
    }
    
    public int getIdUsuarioFuncion() {
        return idUsuarioFuncion;
    }

    public void setIdUsuarioFuncion(int idUsuarioFuncion) {
        this.idUsuarioFuncion = idUsuarioFuncion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getNumeroLab() {
        return numeroLab;
    }

    public void setNumeroLab(int numeroLab) {
        this.numeroLab = numeroLab;
    }

    public String getFuncionalidad() {
        return funcionalidad;
    }

    public void setFuncionalidad(String funcionalidad) {
        this.funcionalidad = funcionalidad;
    }
}
