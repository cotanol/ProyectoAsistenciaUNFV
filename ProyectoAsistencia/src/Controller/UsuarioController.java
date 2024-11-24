package Controller;
import Model.UsuarioModelo;
import View.VentanaPrincipal;
import java.util.ArrayList;

public class UsuarioController {
    
    UsuarioModelo objUsuarioModelo;
    VentanaPrincipal objVentReg;
    
    public UsuarioController(VentanaPrincipal objVentReg) {
        this.objVentReg = objVentReg;
        objUsuarioModelo = new UsuarioModelo(objVentReg);
    }
    
    public UsuarioController() {
        objUsuarioModelo = new UsuarioModelo(objVentReg);
    }
    
    public int insertarUsuarioController (UsuarioModelo obj) {
        return objUsuarioModelo.insertarUsuarioModelo(obj);
    }
    
    public int modificarUsuarioController (UsuarioModelo obj) {
        return objUsuarioModelo.modificarUsuarioModelo(obj);
    }
    
    public int eliminarUsuarioController (UsuarioModelo obj) {
        return objUsuarioModelo.eliminarUsuarioModelo(obj);
    }
    
    public ArrayList<UsuarioModelo> enlistarUsuarioController () {
        return objUsuarioModelo.enlistarUsuarioModelo();
    }
            
    public int ultimoIdController () {
        return objUsuarioModelo.ultimoId();
    }
}
