package Model;
import java.util.ArrayList;
import java.sql.*;
import Util.Conexion_BD;
import View.VentanaPrincipal;

public class LaboratorioModelo {
    
    private int idLaboratorio;
    private String numeroLab;
    private int capacidad;
    
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    VentanaPrincipal objVentReg;
    
    public LaboratorioModelo(VentanaPrincipal objventReg) {
        this.objVentReg = objVentReg;
    }
    
    public LaboratorioModelo(){
        
    }
    
    public int insertarLaboratorioModelo(LaboratorioModelo laboratorioModelo) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO laboratorio (numero_lab ,capacidad) VALUES (?,?);");
            pt.setString(1, laboratorioModelo.getNumeroLab());
            pt.setInt(2, laboratorioModelo.getCapacidad());
            
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
            pt = cn.prepareStatement("UPDATE laboratorio SET numero_lab = ?, capacidad = ? WHERE numero_lab = ?;");
            pt.setString(1, laboratorioModelo.getNumeroLab());
            pt.setInt(2, laboratorioModelo.getCapacidad());
            pt.setString(3, laboratorioModelo.getNumeroLab());
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int modificarLaboratorioModelo(String nuevo, int capacidad, String antiguo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE laboratorio SET numero_lab = ?, capacidad = ? WHERE numero_lab = ?;");
            pt.setString(1, nuevo);
            pt.setInt(2, capacidad);
            pt.setString(3, antiguo);
            
            
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
            pt.setString(1, laboratorioModelo.getNumeroLab());
            
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
                
                laboratorioModelo.setIdLaboratorio(rs.getInt("id_laboratorio"));
                laboratorioModelo.setNumeroLab(rs.getString("numero_lab"));
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
    
    public ArrayList<LaboratorioModelo> buscarResgistroLaboratorios(String buscar) {
        ArrayList<LaboratorioModelo> listaLaboratorios = new ArrayList<>();

        try {
    
            String sql = "SELECT * FROM laboratorio WHERE "
                       + "id_laboratorio LIKE ? OR "
                       + "numero_lab LIKE ? OR "
                       + "capacidad LIKE ?;";
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement(sql);

            for (int i = 1; i <= 3; i++) {
                pt.setString(i, "%" + buscar + "%");
            }

            rs = pt.executeQuery();

            while (rs.next()) {
                LaboratorioModelo labModelo= new LaboratorioModelo();
                labModelo.setIdLaboratorio(rs.getInt("id_laboratorio"));
                labModelo.setNumeroLab(rs.getString("numero_lab"));
                labModelo.setCapacidad(rs.getInt("capacidad"));


                listaLaboratorios.add(labModelo);
            }

            rs.close();
            pt.close();
            cn.close();

        } catch (Exception e) {
            System.err.println("Error: " + e);
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

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getNumeroLab() {
        return numeroLab;
    }

    public void setNumeroLab(String numeroLab) {
        this.numeroLab = numeroLab;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    
}
