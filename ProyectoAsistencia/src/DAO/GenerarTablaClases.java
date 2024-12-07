package DAO;

import Util.Conexion_BD;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GenerarTablaClases {

    public static void crearTablaConFechas(Connection conn, String nombreTabla, String fechaInicio, int numeroClases, int intervaloDias, String[] clasesConFechas) throws Exception {
        // Borrar la tabla si ya existe
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS " + nombreTabla + "");
        }

        // Crear la tabla con las columnas básicas
        StringBuilder createTableQuery = new StringBuilder("CREATE TABLE " + nombreTabla + " (");
        createTableQuery.append("codigo VARCHAR(50) PRIMARY KEY, ");
        createTableQuery.append("apellidos VARCHAR(255), ");
        createTableQuery.append("nombres VARCHAR(255), ");

        // Modificar el formato de fecha en las columnas
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");  // Formato cambiado a dd_MM_yyyy
        for (int i = 0; i < numeroClases; i++) {
            // Cambiar el formato de fecha a "dd_MM_yyyy"
            String fechaClase = clasesConFechas[i];

            // Ajustar el formato para que utilice guion bajo en lugar de guion
            String formattedFecha = fechaClase.replace("-", "_");

            // Agregar la columna con el formato adecuado
            createTableQuery.append("").append(formattedFecha).append(" VARCHAR(50)");

            if (i < numeroClases - 1) {
                createTableQuery.append(", ");
            }
        }

        createTableQuery.append(");");

        // Ejecutar la creación de la tabla
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableQuery.toString());
            System.out.println("Tabla creada con el nombre: " + nombreTabla);
        }
    }


    public static void insertarAlumno(Connection conn, String codigo, String apellidos, String nombres, String[] clases) throws SQLException {
        // Preparar la consulta de inserción
        StringBuilder insertQuery = new StringBuilder("INSERT INTO clases_programadas (codigo, apellidos, nombres");

        // Agregar las fechas como columnas
        for (int i = 0; i < clases.length; i++) {
            insertQuery.append(", ").append(clases[i]).append("");
        }
        insertQuery.append(") VALUES (?, ?, ?");

        // Agregar los valores de fechas
        for (int i = 0; i < clases.length; i++) {
            insertQuery.append(", ?");
        }
        insertQuery.append(")");

        // Ejecutar la inserción
        try (PreparedStatement stmt = conn.prepareStatement(insertQuery.toString())) {
            stmt.setString(1, codigo);
            stmt.setString(2, apellidos);
            stmt.setString(3, nombres);

            // Insertar valores de las fechas de clase
            for (int i = 0; i < clases.length; i++) {
                stmt.setString(i + 4, "Asistido"); // Puedes agregar un valor por defecto como "Asistido" o "Pendiente"
            }

            stmt.executeUpdate();
            System.out.println("Alumno " + codigo + " insertado exitosamente.");
        }
    }
    
    public static void insertarHorarioLaboratorio(Connection conn, int idLaboratorio, int idAsignatura, String dia, String horaInicio, String horaFin, int idUsuario, String codigoHorario) throws SQLException {
    String insertQuery = "INSERT INTO horario_laboratorio (id_laboratorio, id_asignatura, dia, horario_inicio, horario_fin, id_usuario, codigo_horario) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
        stmt.setInt(1, idLaboratorio);
        stmt.setInt(2, idAsignatura);
        stmt.setString(3, dia);
        stmt.setTime(4, Time.valueOf(horaInicio + ":00")); // Formato "HH:mm:ss"
        stmt.setTime(5, Time.valueOf(horaFin + ":00"));   // Formato "HH:mm:ss"
        stmt.setInt(6, idUsuario);
        stmt.setString(7, codigoHorario);

        stmt.executeUpdate();
        System.out.println("Horario insertado exitosamente en la tabla 'horario_laboratorio'.");
    }
}

}