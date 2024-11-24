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
    
    public int modificarAlumnoController (AlumnoModelo obj) {
        return objAlumnoModelo.modificarAlumnoModelo(obj);
    }
    
    public int eliminarAlumnoController (AlumnoModelo obj) {
        return objAlumnoModelo.eliminarAlumnoModelo(obj);
    }
    
    public ArrayList<AlumnoModelo> enlistarAlumnoController () {
        return objAlumnoModelo.enlistarAlumnoModelo();
    }
            
    public int ultimoIdController () {
        return objAlumnoModelo.ultimoId();
    }
}

