package Model;

import Util.Conexion_BD;
import View.VentanaPrincipal;
import java.sql.*;
import java.util.ArrayList;

public class HorariosAlumnoModelo {
    
    private int idAlumno;
    private int idHorario;
    
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
}
