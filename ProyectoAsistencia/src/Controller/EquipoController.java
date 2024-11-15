package Controller;
import Model.EquipoModelo;
import View.Ventana01RegistrosDeUsuarios;
import java.util.ArrayList;

public class EquipoController {
    EquipoModelo objEquipoModelo;
    Ventana01RegistrosDeUsuarios objVentReg;
    
    public EquipoController(Ventana01RegistrosDeUsuarios objVentReg) {
        this.objVentReg = objVentReg;
        objEquipoModelo = new EquipoModelo(objVentReg);
    }
    
    public EquipoController() {
        objEquipoModelo = new EquipoModelo(objVentReg);
    }
    
    public int insertarEquipoController (EquipoModelo obj) {
        return objEquipoModelo.insertarEquipoModelo(obj);
    }
    
    public int modificarEquipoController (EquipoModelo obj) {
        return objEquipoModelo.modificarEquipoModelo(obj);
    }
    
    public int eliminarEquipoController (EquipoModelo obj) {
        return objEquipoModelo.eliminarEquipoModelo(obj);
    }
    
    public ArrayList<EquipoModelo> enlistarEquipoController () {
        return objEquipoModelo.enlistarEquipoModelo();
    }
            
    public int ultimoIdController () {
        return objEquipoModelo.ultimoId();
    }
}