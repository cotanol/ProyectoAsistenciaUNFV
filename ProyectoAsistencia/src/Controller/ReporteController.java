package Controller;
import Model.ReporteModelo;
import View.VentanaPrincipal;
import java.util.ArrayList;

public class ReporteController {
    ReporteModelo objReporteModelo;
    VentanaPrincipal objVentReg;
    
    public ReporteController(VentanaPrincipal objVentReg) {
        this.objVentReg = objVentReg;
        objReporteModelo = new ReporteModelo(objVentReg);
    }
    
    public ReporteController() {
        objReporteModelo = new ReporteModelo(objVentReg);
    }
    
    public int insertarReporteController (ReporteModelo obj) {
        return objReporteModelo.insertarReporteModelo(obj);
    }
    
    public int modificarReporteController (ReporteModelo obj) {
        return objReporteModelo.modificarReporteModelo(obj);
    }
    
    public int eliminarReporteController (ReporteModelo obj) {
        return objReporteModelo.eliminarReporteModelo(obj);
    }
    
    public ArrayList<ReporteModelo> enlistarReporteController () {
        return objReporteModelo.enlistarReporteModelo();
    }
            
    public int ultimoIdController () {
        return objReporteModelo.ultimoId();
    }
}