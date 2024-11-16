package Model;
import java.sql.*;
import java.util.ArrayList;
import Util.Conexion_BD;
import View.Ventana01RegistrosDeUsuarios;

public class EquipoModelo {
    private String codPatrimonial;
    private int numeroLaboratorio;
    private String tipoEquipo;
    private String numeroSerie;
    private String estado;
    
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    Ventana01RegistrosDeUsuarios objVentReg;
    
    public EquipoModelo(Ventana01RegistrosDeUsuarios objventReg) {
        this.objVentReg = objVentReg;
    }
    
    public EquipoModelo(){
        
    }
    
    public int insertarEquipoModelo(EquipoModelo equipoModelo) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO equipo (numero_lab, tipo_equipo, numero_serie, estado) VALUES (?,?,?,?);");
            pt.setInt(1, equipoModelo.getNumeroLab());
            pt.setString(2, equipoModelo.getTipoEquipo());
            pt.setString(3, equipoModelo.getNumeroSerie());
            pt.setString(4, equipoModelo.getEstado());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }
    
    public int modificarEquipoModelo(EquipoModelo equipoModelo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE equipo SET numero_lab = ?, tipo_equipo = ?, numero_serie = ?, estado = ? WHERE cod_patrimonial = ?;");
            pt.setInt(1, equipoModelo.getNumeroLab());
            pt.setString(2, equipoModelo.getTipoEquipo());
            pt.setString(3, equipoModelo.getNumeroSerie());
            pt.setString(4, equipoModelo.getEstado());
            pt.setString(5, equipoModelo.getCodPatrimonial());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarEquipoModelo(EquipoModelo equipoModelo) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM equipo WHERE cod_patrimonial = ?;");
            pt.setString(1, equipoModelo.getCodPatrimonial());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<EquipoModelo> enlistarEquipoModelo () {
        
        ArrayList<EquipoModelo> listaEquipos = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM equipo;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                EquipoModelo equipoModelo = new EquipoModelo();
                equipoModelo.setCodPatrimonial(rs.getString("cod_patrimonial"));
                equipoModelo.setNumeroLab(rs.getInt("numero_lab"));
                equipoModelo.setTipoEquipo(rs.getString("tipo_equipo"));
                equipoModelo.setNumeroSerie(rs.getString("numero_serie"));
                equipoModelo.setEstado(rs.getString("estado"));
                
                listaEquipos.add(equipoModelo);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return listaEquipos;
    }
    
    public ArrayList<EquipoModelo> enlistarEquiposPorEstado(String estado) {
        ArrayList<EquipoModelo> listaEquipos = new ArrayList<>();
        try {
            cn = Conexion_BD.getConexionBD();
            String query = "SELECT * FROM equipo WHERE estado = ?";
            pt = cn.prepareStatement(query);
            pt.setString(1, estado);
            rs = pt.executeQuery();

            while (rs.next()) {
                EquipoModelo equipo = new EquipoModelo();
                equipo.setCodPatrimonial(rs.getString("cod_patrimonial"));
                equipo.setNumeroLab(rs.getInt("numero_lab"));
                equipo.setTipoEquipo(rs.getString("tipo_equipo"));
                equipo.setNumeroSerie(rs.getString("numero_serie"));
                equipo.setEstado(rs.getString("estado"));
                listaEquipos.add(equipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pt != null) pt.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listaEquipos;
    }
    
    public int ultimoId() {
        int id = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT MAX(cod_patrimonial) AS max_id FROM equipo;");
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

    public String getCodPatrimonial() {
        return codPatrimonial;
    }

    public void setCodPatrimonial(String codPatrimonial) {
        this.codPatrimonial = codPatrimonial;
    }
    

    public int getNumeroLab() {
        return numeroLaboratorio;
    }

    public void setNumeroLab(int numeroLaboratorio) {
        this.numeroLaboratorio = numeroLaboratorio;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
