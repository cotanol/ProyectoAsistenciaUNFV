package Model;
import java.sql.*;
import java.util.ArrayList;
import Util.Conexion_BD;
import Util.Enums.Dia;
import java.time.LocalTime;
import View.VentanaPrincipal;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HorarioLaboratorioModelo {
    
    private int idHorario;
    private int idLaboratorio;
    private int idAsignatura;
    private String dia;
    private String horarioInicio;
    private String horarioFin;
    private int idUsuario;
    private String codigoHorario;
    
    private String numeroLaboratorio;
    private String asignaturaNombre;
    private String nombreUsuario;
    
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
            pt = cn.prepareStatement("INSERT INTO horario_laboratorio (id_laboratorio, id_asignatura, dia, horario_inicio, horario_fin, id_usuario, codigo_laboratorio) VALUES (?,?,?,?,?,?,?);");
            pt.setInt(1, horarioLaboratorioModelo.getIdLaboratorio());
            pt.setInt(2, horarioLaboratorioModelo.getIdAsignatura());
            pt.setString(3, horarioLaboratorioModelo.getDia());
            pt.setString(4, horarioLaboratorioModelo.getHorarioInicio());
            pt.setString(5, horarioLaboratorioModelo.getHorarioFin());
            pt.setInt(6, horarioLaboratorioModelo.getIdUsuario());
            pt.setString(7, horarioLaboratorioModelo.getCodigoHorario());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            
        } catch (Exception e){
            System.err.println("ERROR: " + e);
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
            pt.setString(4, horarioLaboratorioModelo.getHorarioInicio());
            pt.setString(5, horarioLaboratorioModelo.getHorarioInicio());
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

                // Manejar valores NULL
                if (rs.getObject("id_asignatura") != null) {
                    horarioLaboratorioModelo.setIdAsignatura(rs.getInt("id_asignatura"));
                }
                if (rs.getObject("dia") != null) {
                    horarioLaboratorioModelo.setDia(rs.getString("dia"));
                }
                if (rs.getObject("horario_inicio") != null) {
                    horarioLaboratorioModelo.setHorarioInicio(rs.getString("horario_inicio"));
                }
                if (rs.getObject("horario_fin") != null) {
                    horarioLaboratorioModelo.setHorarioFin(rs.getString("horario_fin"));
                }
                if (rs.getObject("id_usuario") != null) {
                    horarioLaboratorioModelo.setIdUsuario(rs.getInt("id_usuario"));
                }
                if (rs.getObject("codigo_horario") != null) {
                    horarioLaboratorioModelo.setCodigoHorario(rs.getString("codigo_horario"));
                }

                listaHorarioLaboratorios.add(horarioLaboratorioModelo);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){  
        }
        return listaHorarioLaboratorios;
    }
    
    //============================================================================
    //              CARGAR DE TABLA (BASE DE DATOS) A EXCEL
    //============================================================================

    public static void cargarBD_Excel() {
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet("ReporteUsuarios");

        Conexion_BD cn = new Conexion_BD();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String[] cabeceras = new String[]{"Nro Laboratorio", "Asignatura", "Docente", "Dia", "Hora Inicio", "HoraFin", "Codgio de Horario"};

        Row filaCabeceras = hoja.createRow(0); // Fila Cabeceras de las columnas
        for (int i = 0; i < cabeceras.length; i++) {
            Cell celda = filaCabeceras.createCell(i);
            celda.setCellValue(cabeceras[i]);
            
        }

        int numFila = 1;

        try {
            Connection conexion = cn.getConexionBD();

            ps = conexion.prepareStatement(
                "SELECT " +
                "l.numero_lab AS laboratorio_nombre, " +  // Número del laboratorio
                "a.nombre AS asignatura_nombre, " +       // Nombre de la asignatura
                "u.nombre_usuario AS usuario_nombre, " +  // Nombre de usuario
                "hl.dia, " +                              // Día
                "hl.horario_inicio, " +                   // Hora de inicio
                "hl.horario_fin, " +                      // Hora de fin
                "hl.codigo_horario " +                    // Código del horario
                "FROM horario_laboratorio hl " +
                "JOIN laboratorio l ON hl.id_laboratorio = l.id_laboratorio " + // Join con laboratorio
                "JOIN asignatura a ON hl.id_asignatura = a.id_asignatura " +    // Join con asignatura
                "JOIN usuario u ON hl.id_usuario = u.id_usuario"               // Join con usuario
            );



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
                if (i == cabeceras.length - 1) {
                    hoja.setColumnWidth(i, 90 * 256); // Última columna con 90 caracteres de ancho
                }else if(i == cabeceras.length - 2 || i == cabeceras.length - 3){
                    hoja.setColumnWidth(i, 15 * 256); // Las demás columnas con 30 caracteres de ancho
                }else if(i == cabeceras.length - 4){
                    hoja.setColumnWidth(i, 20 * 256); // Las demás columnas con 30 caracteres de ancho
                }else if(i == cabeceras.length - 6){
                    hoja.setColumnWidth(i, 45 * 256); // Las demás columnas con 30 caracteres de ancho
                }else if(i == cabeceras.length - 7){
                    hoja.setColumnWidth(i, 25 * 256); // Las demás columnas con 30 caracteres de ancho
                }else {
                    hoja.setColumnWidth(i, 30 * 256); // Las demás columnas con 30 caracteres de ancho
                }
            }
            // Guarda el archivo Excel
            String filePath = "ReporteClasesDeLaboratorio.xlsx";
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
    
            //============================================================================
       //                     METODO DE USUARIO DAO PARA BUSCAR
       //============================================================================
public ArrayList<HorarioLaboratorioModelo> buscarResgistroHorarioLaboratorio(String buscar) {
    ArrayList<HorarioLaboratorioModelo> listaHorarioLaboratorio = new ArrayList<>();

    try {
        // Consulta con JOIN para mostrar los nombres en lugar de los IDs
        String sql = "SELECT " +
                     "hl.id_horario, " +
                     "l.numero_lab AS laboratorio_nombre, " +
                     "a.nombre AS asignatura_nombre, " +
                     "u.nombre_usuario AS usuario_nombre, " +
                     "hl.dia, " +
                     "hl.horario_inicio, " +
                     "hl.horario_fin, " +
                     "hl.codigo_horario " +
                     "FROM horario_laboratorio hl " +
                     "JOIN laboratorio l ON hl.id_laboratorio = l.id_laboratorio " +
                     "JOIN asignatura a ON hl.id_asignatura = a.id_asignatura " +
                     "JOIN usuario u ON hl.id_usuario = u.id_usuario " +
                     "WHERE " +
                     "l.numero_lab LIKE ? OR " +
                     "a.nombre LIKE ? OR " +
                     "u.nombre_usuario LIKE ? OR " +
                     "hl.dia LIKE ? OR " +
                     "hl.horario_inicio LIKE ? OR " +
                     "hl.horario_fin LIKE ? OR " +
                     "hl.codigo_horario LIKE ?;";

        cn = Conexion_BD.getConexionBD();
        pt = cn.prepareStatement(sql);

        // Asignar el mismo valor de búsqueda a todos los parámetros
        for (int i = 1; i <= 7; i++) {
            pt.setString(i, "%" + buscar + "%");
        }

        rs = pt.executeQuery();

        // Itera sobre los resultados y agrega los registros a la lista
        while (rs.next()) {
            HorarioLaboratorioModelo horarioLabModelo = new HorarioLaboratorioModelo();
            horarioLabModelo.setIdHorario(rs.getInt("id_horario"));
            horarioLabModelo.setNumeroLaboratorio(rs.getString("laboratorio_nombre")); // Nombre del laboratorio
            horarioLabModelo.setAsignaturaNombre(rs.getString("asignatura_nombre"));  // Nombre de la asignatura
            horarioLabModelo.setNombreUsuario(rs.getString("usuario_nombre"));        // Nombre del usuario
            horarioLabModelo.setDia(rs.getString("dia"));
            horarioLabModelo.setHorarioInicio(rs.getString("horario_inicio"));
            horarioLabModelo.setHorarioFin(rs.getString("horario_fin"));
            horarioLabModelo.setCodigoHorario(rs.getString("codigo_horario"));

            listaHorarioLaboratorio.add(horarioLabModelo);
        }

        rs.close();
        pt.close();
        cn.close();

    } catch (Exception e) {
        System.err.println("Error: " + e);
    }

    return listaHorarioLaboratorio;
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
    
    
    public int obtenerIdAsignaturaPorNombre(String nombreAsignatura) {
        int idAsignatura = -1;
        try {
            Connection cn = Conexion_BD.getConexionBD();
            String sql = "SELECT id_asignatura FROM asignatura WHERE nombre = ?";
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

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFin() {
        return horarioFin;
    }

    public void setHorarioFin(String horarioFin) {
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
    
    public String getNumeroLaboratorio() {
        return numeroLaboratorio;
    }

    public void setNumeroLaboratorio(String numeroLaboratorio) {
        this.numeroLaboratorio = numeroLaboratorio;
    }

    public String getAsignaturaNombre() {
        return asignaturaNombre;
    }

    public void setAsignaturaNombre(String asignaturaNombre) {
        this.asignaturaNombre = asignaturaNombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
}
