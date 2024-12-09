package Model;
import Util.Conexion_BD;
import java.sql.*;
import java.util.ArrayList;
import View.VentanaPrincipal;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UsuarioModelo {
    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String nroDocumento;
    private String numero;
    private String tipoUsuario;
    private String nombreUsuario;
    private String contrasena;
    private String email;
    
    VentanaPrincipal objVentReg;
    
    Connection cn = null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    public UsuarioModelo(VentanaPrincipal objventReg) {
        this.objVentReg = objVentReg;
    }
    
    public UsuarioModelo() {
        
    }
    
    public int insertarUsuarioModelo(UsuarioModelo usuarioModelo) {
        int estado = 0;
        try {
            
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("INSERT INTO usuario (nombres, apellidos, tipo_documento, nro_documento, numero, tipo_usuario, nombre_usuario, contrasena, email)  VALUES (?,?,?,?,?,?,?,?,?);");
            pt.setString(1, usuarioModelo.getNombres());
            pt.setString(2, usuarioModelo.getApellidos());
            pt.setString(3, usuarioModelo.getTipoDocumento());
            pt.setString(4, usuarioModelo.getNroDocumento());
            pt.setString(5, usuarioModelo.getNumero());
            pt.setString(6, usuarioModelo.getTipoUsuario());
            pt.setString(7, usuarioModelo.getNombreUsuario());
            pt.setString(8, usuarioModelo.getContrasena());
            pt.setString(9, usuarioModelo.getEmail());
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return estado;
    }

    public int modificarUsuarioModelo(UsuarioModelo usuarioModelo) {
        int estado = 0;
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("UPDATE usuario SET nombres = ?, apellidos = ?, tipo_documento = ?, nro_documento = ?, numero = ?, tipo_usuario = ?, nombre_usuario = ?, contrasena = ?, email = ? WHERE email = ?;");
            pt.setString(1, usuarioModelo.getNombres());
            pt.setString(2, usuarioModelo.getApellidos());
            pt.setString(3, usuarioModelo.getTipoDocumento());
            pt.setString(4, usuarioModelo.getNroDocumento());
            pt.setString(5, usuarioModelo.getNumero());
            pt.setString(6, usuarioModelo.getTipoUsuario());
            pt.setString(7, usuarioModelo.getNombreUsuario());
            pt.setString(8, usuarioModelo.getContrasena());
            pt.setString(9, usuarioModelo.getEmail());
            pt.setString(10, usuarioModelo.getEmail());
            
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch (Exception e) {
            
        }
        
        return estado;
    }
    
    public int eliminarUsuarioModelo(UsuarioModelo usuarioModelo) {
        int estado = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("DELETE FROM usuario WHERE email = ?;");
            pt.setString(1, usuarioModelo.getEmail());
            
            estado = pt.executeUpdate();
            
            cn.close();
            pt.close();
            rs.close();
            
            
        } catch(Exception e) {
            
        }
        
        return estado;
    }
    
    public ArrayList<UsuarioModelo> enlistarUsuarioModelo () {
        
        ArrayList<UsuarioModelo> listaUsuarios = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM usuario;");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                UsuarioModelo usuarioModelo = new UsuarioModelo();
                usuarioModelo.setIdUsuario(rs.getInt("id_usuario"));
                usuarioModelo.setNombres(rs.getString("nombres"));
                usuarioModelo.setApellidos(rs.getString("apellidos"));
                usuarioModelo.setTipoDocumento(rs.getString("tipo_documento"));
                usuarioModelo.setNroDocumento(rs.getString("nro_documento"));
                usuarioModelo.setNumero(rs.getString("numero"));
                usuarioModelo.setTipoUsuario(rs.getString("tipo_usuario"));
                usuarioModelo.setNombreUsuario(rs.getString("nombre_usuario"));
                usuarioModelo.setContrasena(rs.getString("contrasena"));
                usuarioModelo.setEmail(rs.getString("email"));
            
                listaUsuarios.add(usuarioModelo);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e){
            
        }
        
        return listaUsuarios;
    }
    
     //============================================================================
    //                     METODO DE USUARIO DAO PARA BUSCAR
    //============================================================================
public ArrayList<UsuarioModelo> buscarResgistroUsuarios(String buscar) {
    ArrayList<UsuarioModelo> listaUsuarios = new ArrayList<>();

    try {
        // Consulta segura con parámetros preparados
        String sql = "SELECT * FROM usuario WHERE "
                   + "nombres LIKE ? OR "
                   + "apellidos LIKE ? OR "
                   + "tipo_documento LIKE ? OR "
                   + "nro_documento LIKE ? OR "
                   + "numero LIKE ? OR "
                   + "tipo_usuario LIKE ? OR "
                   + "nombre_usuario LIKE ? OR "
                   + "contrasena LIKE ? OR "
                   + "email LIKE ?;";
        cn = Conexion_BD.getConexionBD();
        pt = cn.prepareStatement(sql);

        for (int i = 1; i <= 9; i++) {
            pt.setString(i, "%" + buscar + "%");
        }

        rs = pt.executeQuery();

        // Itera sobre los resultados y agrega los usuarios a la lista
        while (rs.next()) {
            UsuarioModelo usuarioModelo = new UsuarioModelo();
            usuarioModelo.setIdUsuario(rs.getInt("id_usuario"));
            usuarioModelo.setNombres(rs.getString("nombres"));
            usuarioModelo.setApellidos(rs.getString("apellidos"));
            usuarioModelo.setTipoDocumento(rs.getString("tipo_documento"));
            usuarioModelo.setNroDocumento(rs.getString("nro_documento"));
            usuarioModelo.setNumero(rs.getString("numero"));
            usuarioModelo.setTipoUsuario(rs.getString("tipo_usuario"));
            usuarioModelo.setNombreUsuario(rs.getString("nombre_usuario"));
            usuarioModelo.setContrasena(rs.getString("contrasena"));
            usuarioModelo.setEmail(rs.getString("email"));

            listaUsuarios.add(usuarioModelo);
        }

        rs.close();
        pt.close();
        cn.close();

    } catch (Exception e) {
        System.err.println("Error: " + e);
    }

    return listaUsuarios;
}

    //============================================================================
    //              CARGAR DE TABLA (BASE DE DATOS) A EXCEL
    //============================================================================

    public static void cargarBD_Excel(String buscar) {
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet("ReporteUsuarios");

        Conexion_BD cn = new Conexion_BD();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String[] cabeceras = new String[]{"Nombres", "Apellidos", "Tipo Documento", "Nro Documento", "Nro Celular", "Tipo Usuario", "Nombre Usuario", "Contraseña", "Correo"};

        // Crear fila de cabeceras
        Row filaCabeceras = hoja.createRow(0);
        for (int i = 0; i < cabeceras.length; i++) {
            Cell celda = filaCabeceras.createCell(i);
            celda.setCellValue(cabeceras[i]);
        }

        int numFila = 1;

        try {
            Connection conexion = cn.getConexionBD();

            // Consulta con filtro dinámico
            String sql = "SELECT nombres, apellidos, tipo_documento, nro_documento, numero, tipo_usuario, nombre_usuario, contrasena, email " +
                         "FROM usuario WHERE " +
                         "nombres LIKE ? OR " +
                         "apellidos LIKE ? OR " +
                         "tipo_documento LIKE ? OR " +
                         "nro_documento LIKE ? OR " +
                         "numero LIKE ? OR " +
                         "tipo_usuario LIKE ? OR " +
                         "nombre_usuario LIKE ? OR " +
                         "contrasena LIKE ? OR " +
                         "email LIKE ?";

            ps = conexion.prepareStatement(sql);

            // Configurar los parámetros del filtro
            for (int i = 1; i <= 9; i++) {
                ps.setString(i, "%" + buscar + "%");
            }

            rs = ps.executeQuery();
            int numCol = rs.getMetaData().getColumnCount();

            // Llenar filas con los datos filtrados
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

            // Ajustar ancho de columnas
            for (int i = 0; i < cabeceras.length; i++) {
                hoja.setColumnWidth(i, 30 * 256);
            }

            // Guardar archivo Excel
            String filePath = "ReporteRegistrosUsuarios.xlsx";
            FileOutputStream archivo = new FileOutputStream(filePath);
            libro.write(archivo);
            archivo.close();

            // Abrir automáticamente el archivo Excel
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
    
    
    public ArrayList<UsuarioModelo> enlistarDocentes () {
        ArrayList<UsuarioModelo> listaDocentes = new ArrayList<>();
        
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT * FROM usuario WHERE tipo_usuario = 'DOCENTE';");
            rs = pt.executeQuery();
            
            while (rs.next()) {
                UsuarioModelo docente = new UsuarioModelo();
                docente.setIdUsuario(rs.getInt("id_usuario"));
                docente.setNombres(rs.getString("nombres"));
                docente.setApellidos(rs.getString("apellidos"));
                docente.setTipoDocumento(rs.getString("tipo_documento"));
                docente.setNroDocumento(rs.getString("nro_documento"));
                docente.setNumero(rs.getString("numero"));
                docente.setTipoUsuario(rs.getString("tipo_usuario"));
                docente.setNombreUsuario(rs.getString("nombre_usuario"));
                docente.setContrasena(rs.getString("contrasena"));
                docente.setEmail(rs.getString("email"));
                
                listaDocentes.add(docente);
            }
            
            cn.close();
            pt.close();
            rs.close();
            
        } catch (Exception e) {
            
            
        }
        
        
        return listaDocentes;
    }
    
    public int ultimoId() {
        int id = 0;
        try {
            cn = Conexion_BD.getConexionBD();
            pt = cn.prepareStatement("SELECT MAX(id_usuario) AS max_id FROM usuario;");
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
  
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
