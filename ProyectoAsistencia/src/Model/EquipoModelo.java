package Model;
import java.sql.*;
import java.util.ArrayList;
import Util.Conexion_BD;
import View.VentanaPrincipal;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EquipoModelo {
    private int id_equipo;
    private String codPatrimonial;
    private int idLaboratorio;
    private String tipoEquipo;
    private String numeroSerie;
    private String estado;
    
    private String numero_lab;
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    VentanaPrincipal objVentReg;
    
    public EquipoModelo(VentanaPrincipal objventReg) {
        this.objVentReg = objVentReg;
    }
    
    public EquipoModelo(){
        
    }
    
    public int insertarEquipoModelo(EquipoModelo equipoModelo) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();

            
            pt = cn.prepareStatement("INSERT INTO equipo (cod_patrimonial, id_laboratorio, tipo_equipo, numero_serie, estado) VALUES (?,?,?,?,?);");

            pt.setString(1, equipoModelo.getCodPatrimonial()); // Código Patrimonial
            pt.setInt(2, equipoModelo.getIdLaboratorio());         // Número de laboratorio
            pt.setString(3, equipoModelo.getTipoEquipo());     // Tipo de equipo
            pt.setString(4, equipoModelo.getNumeroSerie());    // Número de serie
            pt.setString(5, equipoModelo.getEstado());         // Estado

            estado = pt.executeUpdate(); 

            cn.close();
            pt.close();

        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return estado;
    }
    
    public int modificarEquipoModelo(EquipoModelo equipoModelo, String codigo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE equipo SET cod_patrimonial = ?, id_laboratorio = ?, tipo_equipo = ?, numero_serie = ?, estado = ? WHERE cod_patrimonial = ?;");
            pt.setString(1, equipoModelo.getCodPatrimonial());
            pt.setInt(2, equipoModelo.getIdLaboratorio());
            pt.setString(3, equipoModelo.getTipoEquipo());
            pt.setString(4, equipoModelo.getNumeroSerie());
            pt.setString(5, equipoModelo.getEstado());
            pt.setString(6, codigo);
            
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
    
    //============================================================================
    //              CARGAR DE TABLA (BASE DE DATOS) A EXCEL
    //============================================================================

public static void cargarBD_Excel_Equipo(String buscar) {
    Workbook libro = new XSSFWorkbook();
    Sheet hoja = libro.createSheet("ReporteEquipos");

    Conexion_BD cn = new Conexion_BD();
    PreparedStatement ps = null;
    ResultSet rs = null;

    String[] cabeceras = new String[]{"Laboratorio", "Tipo Equipo", "Código Patrimonial", "Número de Serie", "Estado"};

    // Crear fila de cabeceras
    Row filaCabeceras = hoja.createRow(0);
    for (int i = 0; i < cabeceras.length; i++) {
        Cell celda = filaCabeceras.createCell(i);
        celda.setCellValue(cabeceras[i]);
    }

    int numFila = 1;

    try {
        Connection conexion = cn.getConexionBD();

        String sql = "SELECT l.id_laboratorio, e.tipo_equipo, e.cod_patrimonial, e.numero_serie, e.estado " +
                     "FROM equipo e " +
                     "INNER JOIN laboratorio l ON e.id_laboratorio = l.id_laboratorio " +
                     "WHERE (l.numero_lab LIKE ? OR e.tipo_equipo LIKE ? OR e.cod_patrimonial LIKE ? OR e.numero_serie LIKE ? OR e.estado LIKE ?)";

        ps = conexion.prepareStatement(sql);

        for (int i = 1; i <= 5; i++) {
            ps.setString(i, "%" + buscar + "%");
        }

        rs = ps.executeQuery();

        EquipoModelo equipoModelo = new EquipoModelo();

        while (rs.next()) {
            Row filaDatos = hoja.createRow(numFila);

            int idLaboratorio = rs.getInt("id_laboratorio");
            String numeroLab = equipoModelo.obtenerNumeroLabPorId(idLaboratorio);

            Object[] fila = {
                numeroLab,  
                rs.getString("tipo_equipo"),
                rs.getString("cod_patrimonial"),
                rs.getString("numero_serie"),
                rs.getString("estado")
            };

            for (int i = 0; i < fila.length; i++) {
                Cell celda = filaDatos.createCell(i);
                celda.setCellValue(fila[i].toString());
            }

            numFila++;
        }

        rs.close();
        ps.close();
        conexion.close();

        for (int i = 0; i < cabeceras.length; i++) {
            hoja.setColumnWidth(i, 30 * 256);
        }

        String filePath = "ReporteEquipos.xlsx";
        FileOutputStream archivo = new FileOutputStream(filePath);
        libro.write(archivo);
        archivo.close();

        File archivoExcel = new File(filePath);
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(archivoExcel);
        } else {
            System.out.println("No se pudo abrir automáticamente el archivo Excel. Verifica tu sistema.");
        }

    } catch (Exception ex) {
        System.err.println("Error: " + ex);
    }
}


    //=================================================================================
    
    public ArrayList<EquipoModelo> enlistarEquipoModelo () {
        
        ArrayList<EquipoModelo> listaEquipos = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM equipo;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                EquipoModelo equipoModelo = new EquipoModelo();
                equipoModelo.setCodPatrimonial(rs.getString("cod_patrimonial"));
                equipoModelo.setIdLaboratorio(rs.getInt("id_laboratorio"));
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
    
    
         //============================================================================
    //                     METODO DE USUARIO DAO PARA BUSCAR
    //============================================================================
    public ArrayList<EquipoModelo> buscarResgistroEquipos(String buscar) {
        ArrayList<EquipoModelo> listaEquipos = new ArrayList<>();

        try {
            String sql = "SELECT e.id_laboratorio, e.tipo_equipo, e.cod_patrimonial, e.numero_serie, e.estado " +
                 "FROM equipo e " +
                 "INNER JOIN laboratorio l ON e.id_laboratorio = l.id_laboratorio " +
                 "WHERE e.cod_patrimonial LIKE ? OR " +
                 "l.numero_lab LIKE ? OR " + 
                 "e.tipo_equipo LIKE ? OR " +
                 "e.numero_serie LIKE ? OR " +
                 "e.estado LIKE ?";

            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement(sql);

            for (int i = 1; i <= 5; i++) {
                pt.setString(i, "%" + buscar + "%");
            }

            rs = pt.executeQuery();

            while (rs.next()) {
                EquipoModelo equipoModelo = new EquipoModelo();
                equipoModelo.setIdLaboratorio(rs.getInt("id_laboratorio"));  
                equipoModelo.setTipoEquipo(rs.getString("tipo_equipo"));
                equipoModelo.setCodPatrimonial(rs.getString("cod_patrimonial"));
                equipoModelo.setNumeroSerie(rs.getString("numero_serie"));
                equipoModelo.setEstado(rs.getString("estado"));

                listaEquipos.add(equipoModelo);
            }

            rs.close();
            pt.close();
            cn.close();

        } catch (Exception e) {
            System.err.println("Error: " + e);
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
                equipo.setIdLaboratorio(rs.getInt("id_laboratorio"));
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
    
    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getCodPatrimonial() {
        return codPatrimonial;
    }

    public void setCodPatrimonial(String codPatrimonial) {
        this.codPatrimonial = codPatrimonial;
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
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

    public String getNumero_lab() {
        return numero_lab;
    }

    public void setNumero_lab(String numero_lab) {
        this.numero_lab = numero_lab;
    }
    
}