package Controller;
import Model.AlumnoModelo;
import View.VentanaPrincipal;
import java.util.ArrayList;

public class AlumnoController {
    
    AlumnoModelo objAlumnoModelo;
    VentanaPrincipal objVentReg;
    
    public AlumnoController(VentanaPrincipal objVentReg) {
        this.objVentReg = objVentReg;
        objAlumnoModelo = new AlumnoModelo(objVentReg);
    }
    
    public AlumnoController() {
        objAlumnoModelo = new AlumnoModelo(objVentReg);
    }
    
    public int insertarAlumnoController (AlumnoModelo obj) {
        return objAlumnoModelo.insertarAlumnoModelo(obj);
    }
    
    public int modificarAlumnoController (String nuevo, String nombre, String apellido, String antiguo) {
        return objAlumnoModelo.modificarAlumnoModelo(nuevo, nombre, apellido, antiguo);
    }
    
    public int eliminarAlumnoController (AlumnoModelo obj) {
        return objAlumnoModelo.eliminarAlumnoModelo(obj);
    }
    
    public ArrayList<AlumnoModelo> enlistarAlumnoController () {
        return objAlumnoModelo.enlistarAlumnoModelo();
    }
    
    public ArrayList<AlumnoModelo> buscarResgistroAlumnoController(String buscar){
        return objAlumnoModelo.buscarResgistroAlumno(buscar);
    }
    
    public int ultimoIdController () {
        return objAlumnoModelo.ultimoId();
    }
}

