package Model;

import Util.Conexion_BD;
import View.VentanaPrincipal;
import java.sql.*;
import java.util.ArrayList;

public class HorariosAlumnoModelo {
    
    private int idAlumno;
    private int idHorario;
    
    private String codigoEstudiante;
    private String codigoHorario;
    
    // Conexión a la base de datos
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    VentanaPrincipal objVentReg;
    
    public HorariosAlumnoModelo(VentanaPrincipal objventReg) {
        this.objVentReg = objVentReg;
    }
    
    public HorariosAlumnoModelo() {
        // Constructor vacío
    }
    
    // Getter y Setter
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

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getCodigoHorario() {
        return codigoHorario;
    }

    public void setCodigoHorario(String codigoHorario) {
        this.codigoHorario = codigoHorario;
    }
    
    
    
    // Método para insertar horarios de un alumno
    public int insertarHorarioAlumno(HorariosAlumnoModelo horariosAlumno) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (?, ?);");
            pt.setInt(1, horariosAlumno.getIdAlumno());
            pt.setInt(2, horariosAlumno.getIdHorario());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }
    
    // Método para eliminar un horario de un alumno
    public int eliminarHorarioAlumno(int idAlumno, int idHorario) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM horarios_alumno WHERE id_alumno = ? AND id_horario = ?;");
            pt.setInt(1, idAlumno);
            pt.setInt(2, idHorario);
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estado;
    }
  
    public ArrayList<HorariosAlumnoModelo> enlistarHorarioAlumno(){
        
        ArrayList<HorariosAlumnoModelo> listaHorarios = new ArrayList<>();
        
        try{
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM horarios_alumno;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                HorariosAlumnoModelo horarioAlumno = new HorariosAlumnoModelo();
                
                horarioAlumno.setIdAlumno(rs.getInt("id_alumno"));
                horarioAlumno.setIdHorario(rs.getInt("id_horario"));
                
                listaHorarios.add(horarioAlumno);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        }catch(Exception e){
            System.err.println("ERROR: " + e);
        }
        
        return listaHorarios;
    }
    
    // Método para obtener todos los horarios de un alumno
    public ArrayList<HorariosAlumnoModelo> obtenerHorariosPorAlumno(int idAlumno) {
        ArrayList<HorariosAlumnoModelo> listaHorarios = new ArrayList<>();
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM horarios_alumno WHERE id_alumno = ?;");
            pt.setInt(1, idAlumno);
            rs = pt.executeQuery();
            
            while (rs.next()) {
                HorariosAlumnoModelo horario = new HorariosAlumnoModelo();
                horario.setIdAlumno(rs.getInt("id_alumno"));
                horario.setIdHorario(rs.getInt("id_horario"));
                listaHorarios.add(horario);
            }
            
            cn.close();
            pt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaHorarios;
    }
    
     // Nuevo Método: Obtener Alumnos por Horario
    public ArrayList<AlumnoModelo> obtenerAlumnosPorHorario(int idHorario) {
        ArrayList<AlumnoModelo> listaAlumnos = new ArrayList<>();
        String sql = "SELECT a.id_alumno, a.codigo_alumno, a.nombres, a.apellidos " +
                     "FROM alumno a " +
                     "JOIN horarios_alumno ha ON a.id_alumno = ha.id_alumno " +
                     "WHERE ha.id_horario = ?;";
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement(sql);
            pt.setInt(1, idHorario);
            rs = pt.executeQuery();
            
            while (rs.next()) {
                AlumnoModelo alumno = new AlumnoModelo();
                alumno.setId_alumno(rs.getInt("id_alumno"));
                alumno.setCodigoAlumno(rs.getString("codigo_alumno"));
                alumno.setNombres(rs.getString("nombres"));
                alumno.setApellidos(rs.getString("apellidos"));
                listaAlumnos.add(alumno);
            }
            
            rs.close();
            pt.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAlumnos;
    }
    //=========================================================================

    
    public String obtenerCodigoHoraioPorId(int idHorario) {
        String codigoHorario = "Desconocida";
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT codigo_horario  FROM horario_laboratorio WHERE id_horario  = ?";
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

    public String obtenerCodigoEstudiantePorId(int idEstudiante) {
        String codigoEstudiante = "Desconocido";
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT codigo_alumno FROM alumno WHERE id_alumno = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idEstudiante);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                codigoEstudiante = rs.getString("codigo_alumno");
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codigoEstudiante;
    }
    
    public int obtenerIdEstudiantePorCodigoEstudiante(String codigoEstudiante) {
        int idEstudiante = -1;
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT id_alumno FROM alumno WHERE codigo_alumno  = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, codigoEstudiante);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                idEstudiante = rs.getInt("id_alumno");
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idEstudiante;
    }
    
    public int obtenerIdHorarioPorCodigoHorario(String codigoHorario) {
        int idHorario = -1;
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT id_horario FROM horario_laboratorio WHERE codigo_horario  = ?";
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
    
    public ArrayList<HorariosAlumnoModelo> buscarRegistroHorarioAlumno(String buscar) {
        ArrayList<HorariosAlumnoModelo> listaHorarios = new ArrayList<>();

        try {
            // Consulta con JOIN para mostrar los nombres en lugar de los IDs
            String sql = "SELECT ha.id_alumno, ha.id_horario, a.codigo_alumno, hl.codigo_horario " +
                         "FROM horarios_alumno ha " +
                         "JOIN alumno a ON ha.id_alumno = a.id_alumno " +
                         "JOIN horario_laboratorio hl ON ha.id_horario = hl.id_horario " +
                         "WHERE a.codigo_alumno LIKE ? OR hl.codigo_horario LIKE ?;";

            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement(sql);

            // Asignar el mismo valor de búsqueda a todos los parámetros
            pt.setString(1, "%" + buscar + "%"); // for codigo_alumno
            pt.setString(2, "%" + buscar + "%"); // for codigo_horario

            rs = pt.executeQuery();

            // Itera sobre los resultados y agrega los registros a la lista
            while (rs.next()) {
                HorariosAlumnoModelo horarioAlumno = new HorariosAlumnoModelo();
                horarioAlumno.setCodigoEstudiante(rs.getString("codigo_alumno"));
                horarioAlumno.setCodigoHorario(rs.getString("codigo_horario")); // Nombre del laboratorio

                listaHorarios.add(horarioAlumno);
            }

            rs.close();
            pt.close();
            cn.close();

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

        return listaHorarios;
    }

    


}
