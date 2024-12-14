package Model;
import Util.Conexion_BD;
import java.sql.*;
import java.util.ArrayList;
import Util.Enums.EstadoAsistencia;
import java.time.LocalDate;
import java.time.LocalTime;
import View.VentanaPrincipal;

public class AsistenciaModelo {
    
    private int idAsistencia;
    private LocalDate fecha;
    private String estado;
    private int idAlumno;
    private int idHorario;
    
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    VentanaPrincipal objVentReg;
    
    public AsistenciaModelo(){
        
    }
    
    public AsistenciaModelo(VentanaPrincipal objventReg) {
        this.objVentReg = objVentReg;
    }
    
    public int insertarAsistenciaModelo(AsistenciaModelo asistenciaModelo) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO asistencia (fecha, estado, id_alumno, id_horario) VALUES (?,?,?,?);");
            pt.setDate(1, java.sql.Date.valueOf(asistenciaModelo.getFecha()));
            pt.setString(2, asistenciaModelo.getEstado());
            pt.setInt(3, asistenciaModelo.getIdAlumno());
            pt.setInt(4, asistenciaModelo.getIdHorario());
            
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
            pt = cn.prepareStatement("UPDATE asistencia SET fecha = ?, estado = ?, id_alumno = ?, id_horario = ? WHERE id_alumno = ? AND id_horario = ? AND fecha = ?;");
            pt.setDate(1, java.sql.Date.valueOf(asistenciaModelo.getFecha()));
            pt.setString(2, asistenciaModelo.getEstado());
            pt.setInt(3, asistenciaModelo.getIdAlumno());
            pt.setInt(4, asistenciaModelo.getIdHorario());
            pt.setInt(5, asistenciaModelo.getIdAlumno());
            pt.setInt(6, asistenciaModelo.getIdHorario());
            pt.setDate(7, java.sql.Date.valueOf(asistenciaModelo.getFecha()));
            
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
            pt = cn.prepareStatement("DELETE FROM asistencia WHERE id_alumno = ? AND id_horario = ? AND fecha = ?;");
            pt.setInt(1, asistenciaModelo.getIdAlumno());
            pt.setInt(2, asistenciaModelo.getIdHorario());
            pt.setDate(3, java.sql.Date.valueOf(asistenciaModelo.getFecha()));
            
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
                asistenciaModelo.setEstado((rs.getString("estado")));
                asistenciaModelo.setIdAlumno(rs.getInt("id_alumno"));
                asistenciaModelo.setIdHorario(rs.getInt("id_horario"));
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
        int id = 0; 
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT MAX(id_asistencia) AS max_id FROM asistencia;");
            rs = pt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("max_id"); 
            }

            // Cerrar recursos
            rs.close();
            pt.close();
            cn.close();

        } catch (Exception e) {  
        }

        return id; 
    }    
    
    public ArrayList<AlumnoModelo> obtenerAlumnosPorLaboratorioYHorario(int numeroLab, int asignatura, String horarioInicio) {
        ArrayList<AlumnoModelo> listaAlumnos = new ArrayList<>();
        try (Connection cn = Conexion_BD.getConexionBD();
             PreparedStatement pt = cn.prepareStatement(
                 "SELECT a.codigo_alumno, a.nombres, a.apellidos " +
                 "FROM alumno a " +
                 "JOIN asistencia ass ON a.codigo_alumno = ass.codigo_alumno " +
                 "JOIN horario_laboratorio hl ON ass.numero_lab = hl.numero_lab " +
                 "WHERE hl.numero_lab = ? AND hl.asignatura = ? AND hl.horario_inicio = ?")) {

            pt.setInt(1, numeroLab);
            pt.setInt(2, asignatura);
            pt.setString(3, horarioInicio);
            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                AlumnoModelo alumno = new AlumnoModelo();
                alumno.setCodigoAlumno(rs.getString("codigo_alumno"));
                alumno.setNombres(rs.getString("nombres"));
                alumno.setApellidos(rs.getString("apellidos"));
                listaAlumnos.add(alumno);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaAlumnos;
    }

    public int obtenerIdAlumnoPorCodigo(String codigoAlumno) {
        int idAlumno = -1;
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT id_alumno FROM alumno WHERE codigo_alumno = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, codigoAlumno);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                idAlumno = rs.getInt("id_alumno");
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAlumno;
    }
      
    public String obtenerCodigoAlumnoPorId(int idAlumno) {
        String codigoAlumno = "Desconocido";
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT codigo_alumno FROM alumno WHERE id_alumno = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idAlumno);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                codigoAlumno = rs.getString("codigo_alumno");
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codigoAlumno;
    }     
    
    public int obtenerIdHorarioPorCodigo(String codigoHorario) {
        int idHorario = -1;
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT id_horario FROM horario_laboratorio WHERE codigo_horario = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, codigoHorario);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                idHorario = rs.getInt("id_horario");
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idHorario;
    }
      
    public String obtenerCodigoHorarioPorId(int idHorario) {
        String codigoHorario = "Desconocido";
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT codigo_horario FROM horario_laboratorio WHERE id_horario = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idHorario);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                codigoHorario = rs.getString("codigo_horario");
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codigoHorario;
    }   
    
    public ArrayList<AlumnoModelo> obtenerAlumnosPorHorario(int idHorario) {
        ArrayList<AlumnoModelo> listaAlumnos = new ArrayList<>();
        String sql = "SELECT DISTINCT a.codigo_alumno, a.nombres, a.apellidos " +
                     "FROM alumno a " +
                     "JOIN asistencia ass ON a.id_alumno = ass.id_alumno " +
                     "WHERE ass.id_horario = ?";
        try (Connection cn = Conexion_BD.getConexionBD();
             PreparedStatement pt = cn.prepareStatement(sql)) {
            pt.setInt(1, idHorario);
            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                AlumnoModelo alumno = new AlumnoModelo();
                alumno.setCodigoAlumno(rs.getString("codigo_alumno"));
                alumno.setNombres(rs.getString("nombres"));
                alumno.setApellidos(rs.getString("apellidos"));
                listaAlumnos.add(alumno);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaAlumnos;
    }
    
    public ArrayList<AsistenciaModelo> obtenerAsistenciasPorHorario(int idHorario) {
        ArrayList<AsistenciaModelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM asistencia WHERE id_horario = ?";
        try (Connection cn = Conexion_BD.getConexionBD();
             PreparedStatement pt = cn.prepareStatement(sql)) {
            pt.setInt(1, idHorario);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                AsistenciaModelo a = new AsistenciaModelo();
                a.setIdAsistencia(rs.getInt("id_asistencia"));
                a.setFecha(rs.getDate("fecha").toLocalDate());
                a.setEstado(rs.getString("estado"));
                a.setIdAlumno(rs.getInt("id_alumno"));
                a.setIdHorario(rs.getInt("id_horario"));
                lista.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public boolean existeAsistencia(int idAlumno, int idHorario, LocalDate fecha) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) as cnt FROM asistencia WHERE id_alumno = ? AND id_horario = ? AND fecha = ?";
        try (Connection cn = Conexion_BD.getConexionBD();
             PreparedStatement pt = cn.prepareStatement(sql)) {
            pt.setInt(1, idAlumno);
            pt.setInt(2, idHorario);
            pt.setDate(3, java.sql.Date.valueOf(fecha));
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt("cnt") > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    

    
}