package Model;
import java.sql.*;
import java.util.ArrayList;
import Util.Conexion_BD;
import Util.Enums.Dia;
import java.time.LocalTime;
import View.Ventana01RegistrosDeUsuarios;

public class HorarioLaboratorioModelo {
    
    private int idHorario;
    private int numeroLab;
    private String asignatura;
    private Dia dia;
    private LocalTime horarioInicio;
    private LocalTime horarioFin;
    private String docente;
    
    Ventana01RegistrosDeUsuarios objVentReg;
    
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    public HorarioLaboratorioModelo(Ventana01RegistrosDeUsuarios objventReg) {
        this.objVentReg = objVentReg;
    }
    public HorarioLaboratorioModelo(){
        
    }
    
    public int insertarHorarioLaboratorioModelo(HorarioLaboratorioModelo horarioLaboratorioModelo) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO horario_laboratorio (numero_lab, asignatura, dia, horario_inicio, horario_fin, docente) VALUES (?,?,?,?,?,?);");
            pt.setInt(1, horarioLaboratorioModelo.getNumeroLab());
            pt.setString(2, horarioLaboratorioModelo.getAsignatura());
            pt.setString(3, horarioLaboratorioModelo.getDia().name());
            pt.setTime(4, java.sql.Time.valueOf(horarioLaboratorioModelo.getHorarioInicio()));
            pt.setTime(5, java.sql.Time.valueOf(horarioLaboratorioModelo.getHorarioFin()));
            pt.setString(2, horarioLaboratorioModelo.getDocente());
            
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
            pt = cn.prepareStatement("UPDATE horario_laboratorio SET numero_lab = ?, asignatura = ?, dia = ?, horario_inicio = ?, horario_fin = ?, docente = ? WHERE id_horario = ?;");
            pt.setInt(1, horarioLaboratorioModelo.getNumeroLab());
            pt.setString(2, horarioLaboratorioModelo.getAsignatura());
            pt.setString(3, horarioLaboratorioModelo.getDia().name());
            pt.setTime(4, java.sql.Time.valueOf(horarioLaboratorioModelo.getHorarioInicio()));
            pt.setTime(5, java.sql.Time.valueOf(horarioLaboratorioModelo.getHorarioFin()));
            pt.setString(6, horarioLaboratorioModelo.getDocente());
            pt.setInt(7, horarioLaboratorioModelo.getIdHorario());
            
            
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
            pt = cn.prepareStatement("DELETE FROM horario_laboratorio WHERE id_horario = ?;");
            pt.setInt(1, horarioLaboratorioModelo.getIdHorario());
            
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
                horarioLaboratorioModelo.setNumeroLab(rs.getInt("numero_lab"));
                horarioLaboratorioModelo.setAsignatura(rs.getString("asignatura"));
                horarioLaboratorioModelo.setDia(Dia.valueOf(rs.getString("dia")));
                horarioLaboratorioModelo.setHorarioInicio(rs.getTime("horario_inicio").toLocalTime());
                horarioLaboratorioModelo.setHorarioInicio(rs.getTime("horario_fin").toLocalTime());
                horarioLaboratorioModelo.setDocente(rs.getString("docente"));
            
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
    
    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getNumeroLab() {
        return numeroLab;
    }

    public void setNumeroLab(int numeroLab) {
        this.numeroLab = numeroLab;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
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

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }
    
}
