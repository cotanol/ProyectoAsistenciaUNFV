package Model;
import java.sql.*;
import java.util.ArrayList;
import Util.Conexion_BD;
import View.VentanaPrincipal;

public class ReporteModelo{
    
    private int idReporte;
    private int ano;
    private int mes;
    private int asistenciaTotal;
    private int numeroLab;
    
    VentanaPrincipal objVentReg;
    
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    public ReporteModelo(VentanaPrincipal objventReg) {
        this.objVentReg = objVentReg;
    }
    public ReporteModelo(){
        
    }
    
    public int insertarReporteModelo(ReporteModelo reporteModelo) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO reporte (ano, mes, asistencia_total, numero_lab) VALUES (?,?,?,?);");
            pt.setInt(1, reporteModelo.getAno());
            pt.setInt(2, reporteModelo.getMes());
            pt.setInt(3, reporteModelo.getAsistenciaTotal());
            pt.setInt(4, reporteModelo.getNumeroLab());
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarReporteModelo(ReporteModelo reporteModelo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE reporte SET ano = ?, mes = ?, asistencia_total = ?, numero_lab = ? WHERE id_reporte = ?;");
            pt.setInt(1, reporteModelo.getAno());
            pt.setInt(2, reporteModelo.getMes());
            pt.setInt(3, reporteModelo.getAsistenciaTotal());
            pt.setInt(4, reporteModelo.getNumeroLab());
            pt.setInt(5, reporteModelo.getIdReporte());
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarReporteModelo(ReporteModelo reporteModelo) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM horario_laboratorio WHERE id_reporte = ?;");
            pt.setInt(1, reporteModelo.getIdReporte());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<ReporteModelo> enlistarReporteModelo () {
        
        ArrayList<ReporteModelo> listaReportes = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM reporte;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                ReporteModelo reporteModelo = new ReporteModelo();
                reporteModelo.setIdReporte(rs.getInt("id_reporte"));
                reporteModelo.setAno(rs.getInt("ano"));
                reporteModelo.setMes(rs.getInt("mes"));
                reporteModelo.setAsistenciaTotal(rs.getInt("asistencia_total"));
                reporteModelo.setNumeroLab(rs.getInt("numero_lab"));
                
            
                listaReportes.add(reporteModelo);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return listaReportes;
    }
    
    public int ultimoId() {
        int id = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT MAX(id_reporte) AS max_id FROM laboratorio;");
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
    
    public int getIdReporte() {
        return idReporte;
    }
    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }
    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAsistenciaTotal() {
        return asistenciaTotal;
    }

    public void setAsistenciaTotal(int asistenciaTotal) {
        this.asistenciaTotal = asistenciaTotal;
    }

    public int getNumeroLab() {
        return numeroLab;
    }

    public void setNumeroLab(int numeroLab) {
        this.numeroLab = numeroLab;
    }    
}
