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
    public int obtenerIDLaboratorioPorNumero(String numeroLaboratorio) {
        int idLaboratorio = -1;
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT id_laboratorio FROM laboratorio WHERE numero_lab = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, numeroLaboratorio);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                idLaboratorio = rs.getInt("id_laboratorio");
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idLaboratorio;
    }
    
    public String obtenerNombreAsignaturaPorId(int idAsignatura) {
        String nombreAsignatura = "Desconocida";
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT nombre FROM asignatura WHERE id_asignatura = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idAsignatura);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nombreAsignatura = rs.getString("nombre");
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreAsignatura;
    }

    public String obtenerNombreUsuarioPorId(int idUsuario) {
        String nombreUsuario = "Desconocido";
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT nombre_usuario FROM usuario WHERE id_usuario = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nombreUsuario = rs.getString("nombre_usuario");
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreUsuario;
    }

    

    public String obtenerNumeroLabPorId(int idLaboratorio) {
        String NumeroLaboratorio = "Desconocida";
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT numero_lab FROM laboratorio WHERE id_laboratorio = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idLaboratorio);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                NumeroLaboratorio = rs.getString("numero_lab");
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return NumeroLaboratorio;
    }
}
