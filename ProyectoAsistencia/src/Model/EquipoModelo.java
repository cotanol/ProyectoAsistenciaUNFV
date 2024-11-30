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

            // Establecer los valores correspondientes
            pt.setString(1, equipoModelo.getCodPatrimonial()); // Código Patrimonial
            pt.setInt(2, equipoModelo.getIdLaboratorio());         // Número de laboratorio
            pt.setString(3, equipoModelo.getTipoEquipo());     // Tipo de equipo
            pt.setString(4, equipoModelo.getNumeroSerie());    // Número de serie
            pt.setString(5, equipoModelo.getEstado());         // Estado

            estado = pt.executeUpdate(); // Ejecutar la consulta

            // Cerrar recursos
            cn.close();
            pt.close();

        } catch (Exception e) {
            e.printStackTrace(); // Imprimir el error en caso de problemas
        }

        return estado;
    }
    
    public int modificarEquipoModelo(EquipoModelo equipoModelo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE equipo SET cod_patrimonial = ?, id_laboratorio = ?, tipo_equipo = ?, numero_serie = ?, estado = ? WHERE cod_patrimonial = ?;");
            pt.setString(1, equipoModelo.getCodPatrimonial());
            pt.setInt(2, equipoModelo.getIdLaboratorio());
            pt.setString(3, equipoModelo.getTipoEquipo());
            pt.setString(4, equipoModelo.getNumeroSerie());
            pt.setString(5, equipoModelo.getEstado());
            pt.setString(6, equipoModelo.getCodPatrimonial());
            
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

    public static void cargarBD_Excel() {
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet("ReporteEquipos");

        Conexion_BD cn = new Conexion_BD();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String[] cabeceras = new String[]{"Nro Laboratorio", "Tipo Equipo", "Código Patrimonial", "Nro Serie", "Estado"};

        Row filaCabeceras = hoja.createRow(0); // Fila Cabeceras de las columnas
        for (int i = 0; i < cabeceras.length; i++) {
            Cell celda = filaCabeceras.createCell(i);
            celda.setCellValue(cabeceras[i]);
            
        }

        int numFila = 1;

        try {
            Connection conexion = cn.getConexionBD();
      
            ps = conexion.prepareStatement("SELECT numero_lab, tipo_equipo, cod_patrimonial, numero_serie, estado FROM equipo WHERE estado = 'Operativo';");


            rs = ps.executeQuery();

            int numCol = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Row filaDatos = hoja.createRow(numFila);

                for (int i = 0; i < numCol; i++) {
                    Cell celda = filaDatos.createCell(i);
                    celda.setCellValue(rs.getString(i + 1));
                }

                numFila++;
            }

            rs.close();
            ps.close();
            conexion.close();
            
            for (int i = 0; i < cabeceras.length; i++) {
            hoja.setColumnWidth(i, 30 * 256); // Forzamos ancho de 30 caracteres
        }
            // Guarda el archivo Excel
            String filePath = "ReporteEquiposLaboratorio.xlsx";
            FileOutputStream archivo = new FileOutputStream(filePath);
            libro.write(archivo);
            archivo.close();

            // Abre el archivo Excel automáticamente
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
    
    //======================================================================
    
         //============================================================================
    //                     METODO DE USUARIO DAO PARA BUSCAR
    //============================================================================
    public ArrayList<EquipoModelo> buscarResgistroEquipos(String buscar) {
    ArrayList<EquipoModelo> listaEquipos = new ArrayList<>();

    try {
        // Consulta segura con parámetros preparados
        String sql = "SELECT * FROM equipo WHERE "
                   + "cod_patrimonial LIKE ? OR "
                   + "CAST(id_laboratorio AS CHAR) LIKE ? OR "
                   + "tipo_equipo LIKE ? OR "
                   + "numero_serie LIKE ? OR "
                   + "estado LIKE ?"; // Corregido: Se añadió "LIKE" faltante en "numero_serie"

        cn = Conexion_BD.getConexionBD();
        pt = cn.prepareStatement(sql);

        for (int i = 1; i <= 5; i++) {
            pt.setString(i, "%" + buscar + "%");
        }

        rs = pt.executeQuery();

        // Itera sobre los resultados y agrega los equipos a la lista
        while (rs.next()) {
            EquipoModelo equipoModelo = new EquipoModelo();
            equipoModelo.setCodPatrimonial(rs.getString("cod_patrimonial"));
            equipoModelo.setIdLaboratorio(rs.getInt("id_laboratorio"));
            equipoModelo.setTipoEquipo(rs.getString("tipo_equipo"));
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

    

    
}