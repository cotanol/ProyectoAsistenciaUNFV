package Model;
import java.sql.*;
import java.util.ArrayList;
import Util.Conexion_BD;
import Util.Enums.Dia;
import java.time.LocalTime;
import View.VentanaPrincipal;

public class HorarioLaboratorioModelo {
    
    private int idHorario;
    private int idLaboratorio;
    private int idAsignatura;
    private String dia;
    private LocalTime horarioInicio;
    private LocalTime horarioFin;
    private int idUsuario;
    private String codigoHorario;
    
    VentanaPrincipal objVentReg;
    
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    public HorarioLaboratorioModelo(VentanaPrincipal objventReg) {
        this.objVentReg = objVentReg;
    }
    public HorarioLaboratorioModelo(){
        
    }
    
    public int insertarHorarioLaboratorioModelo(HorarioLaboratorioModelo horarioLaboratorioModelo) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO horario_laboratorio (id_laboratorio, id_asignatura, dia, horario_inicio, horario_fin, id_usuario) VALUES (?,?,?,?,?,?);");
            pt.setInt(1, horarioLaboratorioModelo.getIdLaboratorio());
            pt.setInt(2, horarioLaboratorioModelo.getIdAsignatura());
            pt.setString(3, horarioLaboratorioModelo.getDia());
            pt.setTime(4, java.sql.Time.valueOf(horarioLaboratorioModelo.getHorarioInicio()));
            pt.setTime(5, java.sql.Time.valueOf(horarioLaboratorioModelo.getHorarioFin()));
            pt.setInt(2, horarioLaboratorioModelo.getIdUsuario());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarHorarioLaboratorioModelo(HorarioLaboratorioModelo horarioLaboratorioModelo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE horario_laboratorio SET id_laboratorio = ?, id_asignatura = ?, dia = ?, horario_inicio = ?, horario_fin = ?, id_usuario = ?, codigo_horario = ? WHERE codigo_horario = ?;");
            pt.setInt(1, horarioLaboratorioModelo.getIdLaboratorio());
            pt.setInt(2, horarioLaboratorioModelo.getIdAsignatura());
            pt.setString(3, horarioLaboratorioModelo.getDia());
            pt.setTime(4, java.sql.Time.valueOf(horarioLaboratorioModelo.getHorarioInicio()));
            pt.setTime(5, java.sql.Time.valueOf(horarioLaboratorioModelo.getHorarioFin()));
            pt.setInt(6, horarioLaboratorioModelo.getIdUsuario());
            pt.setString(7, horarioLaboratorioModelo.getCodigoHorario());
            pt.setString(8, horarioLaboratorioModelo.getCodigoHorario());
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarHorarioLaboratorioModelo(HorarioLaboratorioModelo horarioLaboratorioModelo) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM horario_laboratorio WHERE codigo_horario = ?;");
            pt.setString(1, horarioLaboratorioModelo.getCodigoHorario());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<HorarioLaboratorioModelo> enlistarHorarioLaboratorioModelo() {
        
        ArrayList<HorarioLaboratorioModelo> listaHorarioLaboratorios = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM horario_laboratorio;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                HorarioLaboratorioModelo horarioLaboratorioModelo = new HorarioLaboratorioModelo();
                horarioLaboratorioModelo.setIdHorario(rs.getInt("id_horario"));
                horarioLaboratorioModelo.setIdLaboratorio(rs.getInt("id_laboratorio"));
                horarioLaboratorioModelo.setIdAsignatura(rs.getInt("id_asignatura"));
                horarioLaboratorioModelo.setDia((rs.getString("dia")));
                horarioLaboratorioModelo.setHorarioInicio(rs.getTime("horario_inicio").toLocalTime());
                horarioLaboratorioModelo.setHorarioFin(rs.getTime("horario_fin").toLocalTime());
                horarioLaboratorioModelo.setIdUsuario(rs.getInt("id_usuario"));
                horarioLaboratorioModelo.setCodigoHorario(rs.getString("id_usuario"));
            
                listaHorarioLaboratorios.add(horarioLaboratorioModelo);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){  
        }
        return listaHorarioLaboratorios;
    }
    
    public int ultimoId() {
        int id = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT MAX(id_horario) AS max_id FROM horario_laboratorio;");
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
    
    public ArrayList<HorarioLaboratorioModelo> buscarHorarios(int numeroLab, int asignatura, String dia) {
        ArrayList<HorarioLaboratorioModelo> listaHorarios = new ArrayList<>();
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM horario_laboratorio WHERE id_laboratorio = ? AND id_asignatura = ? AND dia = ?");
            pt.setInt(1, numeroLab);
            pt.setInt(2, asignatura);
            pt.setString(3, dia);
            rs = pt.executeQuery();

            while (rs.next()) {
                HorarioLaboratorioModelo horario = new HorarioLaboratorioModelo();
                horario.setIdHorario(rs.getInt("id_horario"));
                horario.setIdLaboratorio(rs.getInt("id_laboratorio"));
                horario.setIdAsignatura(rs.getInt("id_asignatura"));
                horario.setDia((rs.getString("dia")));
                horario.setHorarioInicio(rs.getTime("horario_inicio").toLocalTime());
                horario.setHorarioFin(rs.getTime("horario_fin").toLocalTime());
                horario.setIdUsuario(rs.getInt("id_usuario"));
                horario.setCodigoHorario(rs.getString("codigo_horario"));
                listaHorarios.add(horario);
            }

            cn.close();
            pt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaHorarios;
    }
    
    public int obtenerIdAsignaturaPorNombre(String nombreAsignatura) {
        int idAsignatura = -1;
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT id_asignatura FROM asignatura WHERE nombre_asignatura = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, nombreAsignatura);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                idAsignatura = rs.getInt("id_asignatura");
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAsignatura;
    }

    public int obtenerIdUsuarioPorNombre(String nombreUsuario) {
        int idUsuario = -1;
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT id_usuario FROM usuario WHERE nombre_usuario = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, nombreUsuario);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                idUsuario = rs.getInt("id_usuario");
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idUsuario;
    }
    
    public String obtenerNombreAsignaturaPorId(int idAsignatura) {
        String nombreAsignatura = "Desconocida";
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT nombre_asignatura FROM asignatura WHERE id_asignatura = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idAsignatura);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nombreAsignatura = rs.getString("nombre_asignatura");
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

    
    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFin() {
        return horarioFin;
    }

    public void setHorarioFin(LocalTime horarioFin) {
        this.horarioFin = horarioFin;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodigoHorario() {
        return codigoHorario;
    }

    public void setCodigoHorario(String codigoHorario) {
        this.codigoHorario = codigoHorario;
    }
    
    
    
}
