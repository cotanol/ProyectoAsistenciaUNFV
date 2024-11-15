package Controller;
import Model.AlumnoModelo;
import View.Ventana01RegistrosDeUsuarios;
import java.util.ArrayList;

public class AlumnoController {
    
    AlumnoModelo objAlumnoModelo;
    Ventana01RegistrosDeUsuarios objVentReg;
    
    public AlumnoController(Ventana01RegistrosDeUsuarios objVentReg) {
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

