package Model;
import Util.Conexion_BD;
import java.sql.*;
import java.util.ArrayList;
import Util.Enums.EstadoAsistencia;
import java.time.LocalDate;
import View.Ventana01RegistrosDeUsuarios;

public class AsistenciaModelo {
    
    private int idAsistencia;
    private LocalDate fecha;
    private EstadoAsistencia estado;
    private int numeroLab;
    private int codigoAlumno;
    
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    Ventana01RegistrosDeUsuarios objVentReg;
    
    public AsistenciaModelo(){
        
    }
    
    public AsistenciaModelo(Ventana01RegistrosDeUsuarios objventReg) {
        this.objVentReg = objVentReg;
    }
    
    public int insertarAsistenciaModelo(AsistenciaModelo asistenciaModelo) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO asistencia (fecha, estado, numero_lab, codigo_alumno) VALUES (?,?,?,?);");
            pt.setDate(1, java.sql.Date.valueOf(asistenciaModelo.getFecha()));
            pt.setString(2, asistenciaModelo.getEstado().name());
            pt.setInt(3, asistenciaModelo.getNumeroLab());
            pt.setInt(4, asistenciaModelo.getCodigoAlumno());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarAsistenciaModelo(AsistenciaModelo asistenciaModelo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE asistencia SET fecha = ?, estado = ?, numero_lab = ?, codigo_alumno = ? WHERE id_asistencia = ?;");
            pt.setDate(1, java.sql.Date.valueOf(asistenciaModelo.getFecha()));
            pt.setString(2, asistenciaModelo.getEstado().name());
            pt.setInt(3, asistenciaModelo.getNumeroLab());
            pt.setInt(4, asistenciaModelo.getCodigoAlumno());
            pt.setInt(5, asistenciaModelo.getIdAsistencia());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarAsistenciaModelo(AsistenciaModelo asistenciaModelo) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM asistencia WHERE id_asistencia = ?;");
            pt.setInt(1, asistenciaModelo.getIdAsistencia());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<AsistenciaModelo> enlistarAsistenciaModelo () {
        
        ArrayList<AsistenciaModelo> listaAsistencias = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM asistencia;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                AsistenciaModelo asistenciaModelo = new AsistenciaModelo();
                asistenciaModelo.setIdAsistencia(rs.getInt("id_asistencia"));
                asistenciaModelo.setFecha(rs.getDate("fecha").toLocalDate());
                asistenciaModelo.setEstado(EstadoAsistencia.valueOf(rs.getString("estado")));
                asistenciaModelo.setNumeroLab(rs.getInt("numero_lab"));
                asistenciaModelo.setCodigoAlumno(rs.getInt("codigo_alumno"));
                listaAsistencias.add(asistenciaModelo);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return listaAsistencias;
    }
    public int ultimoId() {
        int id = 0; // Inicializa el ID con un valor predeterminado
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT MAX(id_asistencia) AS max_id FROM asistencia;");
            rs = pt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("max_id"); // Obtén el máximo ID de la columna 'max_id'
            }

            // Cerrar recursos
            rs.close();
            pt.close();
            cn.close();

        } catch (Exception e) {  
        }

        return id; 
    }    
    
    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EstadoAsistencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsistencia estado) {
        this.estado = estado;
    }

    public int getNumeroLab() {
        return numeroLab;
    }

    public void setNumeroLab(int numeroLab) {
        this.numeroLab = numeroLab;
    }

    public int getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(int codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }   
    
}