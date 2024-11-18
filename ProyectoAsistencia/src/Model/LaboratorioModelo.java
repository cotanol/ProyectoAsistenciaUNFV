package Model;
import java.util.ArrayList;
import java.sql.*;
import Util.Conexion_BD;
import View.Ventana01RegistrosDeUsuarios;

public class LaboratorioModelo {
    
    private int numeroLab;
    private int capacidad;
    
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    Ventana01RegistrosDeUsuarios objVentReg;
    
    public LaboratorioModelo(Ventana01RegistrosDeUsuarios objventReg) {
        this.objVentReg = objVentReg;
    }
    
    public LaboratorioModelo(){
        
    }
    
    public int insertarLaboratorioModelo(LaboratorioModelo laboratorioModelo) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO laboratorio (capacidad) VALUES (?);");
            pt.setInt(1, laboratorioModelo.getCapacidad());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarLaboratorioModelo(LaboratorioModelo laboratorioModelo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE laboratorio SET capacidad = ? WHERE numero_lab = ?;");
            pt.setInt(1, laboratorioModelo.getCapacidad());
            pt.setInt(2, laboratorioModelo.getNumeroLab());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarLaboratorioModelo(LaboratorioModelo laboratorioModelo) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM laboratorio WHERE numero_lab = ?;");
            pt.setInt(1, laboratorioModelo.getNumeroLab());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<LaboratorioModelo> enlistarLaboratorioModelo () {
        
        ArrayList<LaboratorioModelo> listaLaboratorios = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM laboratorio;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                LaboratorioModelo laboratorioModelo = new LaboratorioModelo();
                laboratorioModelo.setNumeroLab(rs.getInt("numero_lab"));
                laboratorioModelo.setCapacidad(rs.getInt("capacidad"));
            
                listaLaboratorios.add(laboratorioModelo);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return listaLaboratorios;
    }
    
    public int ultimoId() {
        int id = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT MAX(numero_lab) AS max_id FROM laboratorio;");
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
    
    public int getNumeroLab() {
        return numeroLab;
    }

    public void setNumeroLab(int numeroLab) {
        this.numeroLab = numeroLab;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
