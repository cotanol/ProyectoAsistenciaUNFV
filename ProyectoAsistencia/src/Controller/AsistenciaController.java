package Controller;
import Model.AsistenciaModelo;
import Model.AlumnoModelo;
import View.VentanaPrincipal;
import java.util.ArrayList;
import java.time.LocalTime;

public class AsistenciaController {
    AsistenciaModelo objAsistenciaModelo;
    VentanaPrincipal objVentReg;
    
    public AsistenciaController(VentanaPrincipal objVentReg) {
        this.objVentReg = objVentReg;
        objAsistenciaModelo = new AsistenciaModelo(objVentReg);
    }
    
    public AsistenciaController() {
        objAsistenciaModelo = new AsistenciaModelo(objVentReg);
    }
    
    public int insertarAsistenciaController (AsistenciaModelo obj) {
        return objAsistenciaModelo.insertarAsistenciaModelo(obj);
    }
    
    public int modificarAsistenciaController (AsistenciaModelo obj) {
        return objAsistenciaModelo.modificarAsistenciaModelo(obj);
    }
    
    public int eliminarAsistenciaController (AsistenciaModelo obj) {
        return objAsistenciaModelo.eliminarAsistenciaModelo(obj);
    }
    
    public ArrayList<AsistenciaModelo> enlistarAsistenciaController () {
        return objAsistenciaModelo.enlistarAsistenciaModelo();
    }
            
    public int ultimoIdController () {
        return objAsistenciaModelo.ultimoId();
    }    
    
    public ArrayList<AlumnoModelo> obtenerAlumnosPorLaboratorioYHorario(int numeroLab, int asignatura, LocalTime horarioInicio) {
        return objAsistenciaModelo.obtenerAlumnosPorLaboratorioYHorario(numeroLab, asignatura, horarioInicio);
    }
}