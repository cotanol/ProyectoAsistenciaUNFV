package Controller;
import Model.AsistenciaModelo;
import View.Ventana01RegistrosDeUsuarios;
import java.util.ArrayList;

public class AsistenciaController {
    AsistenciaModelo objAsistenciaModelo;
    Ventana01RegistrosDeUsuarios objVentReg;
    
    public AsistenciaController(Ventana01RegistrosDeUsuarios objVentReg) {
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
}