package Controller;
import Model.AsignaturaModelo;
import View.VentanaPrincipal;
import java.util.ArrayList;

public class AsignaturaController {
    
    AsignaturaModelo objAsignaturaModelo;
    VentanaPrincipal objVentReg;
    
    public AsignaturaController(VentanaPrincipal objVentReg) {
        this.objVentReg = objVentReg;
        objAsignaturaModelo = new AsignaturaModelo(objVentReg);
    }
    
    public AsignaturaController() {
        objAsignaturaModelo = new AsignaturaModelo(objVentReg);
    }
    
    public int insertarAsignaturaController (AsignaturaModelo obj) {
        return objAsignaturaModelo.insertarAsignaturaModelo(obj);
    }
    
    public int modificarAsignaturaController (AsignaturaModelo obj) {
        return objAsignaturaModelo.modificarAsignaturaModelo(obj);
    }
    
    public int eliminarAsignaturaController (AsignaturaModelo obj) {
        return objAsignaturaModelo.eliminarAsignaturaModelo(obj);
    }
    
    public ArrayList<AsignaturaModelo> enlistarAsignaturaController () {
        return objAsignaturaModelo.enlistarAsignaturaModelo();
    }
    
}    
