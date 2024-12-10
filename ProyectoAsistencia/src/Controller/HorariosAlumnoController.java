package Controller;

import Model.AlumnoModelo;
import Model.HorariosAlumnoModelo;
import View.VentanaPrincipal;
import java.util.ArrayList;

public class HorariosAlumnoController {
    
    private HorariosAlumnoModelo objHorariosAlumnoModelo;
    private VentanaPrincipal objVent;
    
    public HorariosAlumnoController(VentanaPrincipal objVent) {
        objHorariosAlumnoModelo = new HorariosAlumnoModelo(objVent);
        this.objVent = objVent;
    }
    
    public HorariosAlumnoController() {
        objHorariosAlumnoModelo = new HorariosAlumnoModelo();
    }
    
    // Método para insertar un horario para un alumno
    public int insertarHorarioAlumnoController(HorariosAlumnoModelo horariosAlumno) {
        return objHorariosAlumnoModelo.insertarHorarioAlumno(horariosAlumno);
    }
    
    // Método para eliminar un horario de un alumno
    public int eliminarHorarioAlumnoController(int idAlumno, int idHorario) {
        return objHorariosAlumnoModelo.eliminarHorarioAlumno(idAlumno, idHorario);
    }
    
    // Método para obtener los horarios de un alumno
    public ArrayList<HorariosAlumnoModelo> obtenerHorariosPorAlumnoController(int idAlumno) {
        return objHorariosAlumnoModelo.obtenerHorariosPorAlumno(idAlumno);
    }
    
    // **Nuevo Método:** Obtener Alumnos por Horario
    public ArrayList<AlumnoModelo> obtenerAlumnosPorHorarioController(int idHorario) {
        return objHorariosAlumnoModelo.obtenerAlumnosPorHorario(idHorario);
    }
}
