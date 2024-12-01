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

    public static void cargarBD_Excel() {
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet("ReporteUsuarios");

        Conexion_BD cn = new Conexion_BD();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String[] cabeceras = new String[]{"Nombres", "Apellidos", "Tipo Documento", "Nro Documento", "Nro Celular", "Tipo Usuario", "Nombre Usuario", "Contraseña", "Correo"};

        Row filaCabeceras = hoja.createRow(0); // Fila Cabeceras de las columnas
        for (int i = 0; i < cabeceras.length; i++) {
            Cell celda = filaCabeceras.createCell(i);
            celda.setCellValue(cabeceras[i]);
            
        }

        int numFila = 1;

        try {
            Connection conexion = cn.getConexionBD();

            ps = conexion.prepareStatement("select nombres, apellidos, tipo_documento, nro_documento, numero, tipo_usuario, nombre_usuario, contrasena, email from usuario");
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
            String filePath = "ReporteRegistrosUsuarios.xlsx";
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
