package Controller;
import Model.LaboratorioModelo;
import View.Ventana01RegistrosDeUsuarios;
import java.util.ArrayList;

public class LaboratorioController {
    
    LaboratorioModelo objLaboratorioModelo;
    Ventana01RegistrosDeUsuarios objVentReg;
    
    public LaboratorioController(Ventana01RegistrosDeUsuarios objVentReg) {
        this.objVentReg = objVentReg;
        objLaboratorioModelo = new LaboratorioModelo(objVentReg);
    }
    
    public LaboratorioController() {
        objLaboratorioModelo = new LaboratorioModelo(objVentReg);
    }
    
    public int insertarLaboratorioController (LaboratorioModelo obj) {
        return objLaboratorioModelo.insertarLaboratorioModelo(obj);
    }
    
    public int modificarLaboratorioController (LaboratorioModelo obj) {
        return objLaboratorioModelo.modificarLaboratorioModelo(obj);
    }
    
    public int eliminarLaboratorioController (LaboratorioModelo obj) {
        return objLaboratorioModelo.eliminarLaboratorioModelo(obj);
    }
    
    public ArrayList<LaboratorioModelo> enlistarLaboratorioController () {
        return objLaboratorioModelo.enlistarLaboratorioModelo();
    }
            
    public int ultimoIdController () {
        return objLaboratorioModelo.ultimoId();
    }
}
