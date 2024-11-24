package Controller;
import Model.UsuarioFuncionalidadModelo;
import View.VentanaPrincipal;
import java.util.ArrayList;

public class UsuarioFuncionalidadController {
    
    UsuarioFuncionalidadModelo objUsuarioFuncionalidadModelo;
    VentanaPrincipal objVentReg;
    
    public UsuarioFuncionalidadController(VentanaPrincipal objVentReg) {
        this.objVentReg = objVentReg;
        objUsuarioFuncionalidadModelo = new UsuarioFuncionalidadModelo(objVentReg);
    }
    
    public UsuarioFuncionalidadController() {
        objUsuarioFuncionalidadModelo = new UsuarioFuncionalidadModelo(objVentReg);
    }
    
    public int insertarUsuarioFuncionalidadController (UsuarioFuncionalidadModelo obj) {
        return objUsuarioFuncionalidadModelo.insertarUsuarioFuncionalidadModelo(obj);
    }
    
    public int modificarUsuarioFuncionalidadController (UsuarioFuncionalidadModelo obj) {
        return objUsuarioFuncionalidadModelo.modificarUsuarioFuncionalidadModelo(obj);
    }
    
    public int eliminarUsuarioFuncionalidadController (UsuarioFuncionalidadModelo obj) {
        return objUsuarioFuncionalidadModelo.eliminarUsuarioFuncionalidadModelo(obj);
    }
    
    public ArrayList<UsuarioFuncionalidadModelo> enlistarUsuarioFuncionalidadController () {
        return objUsuarioFuncionalidadModelo.enlistarUsuarioFuncionalidadModelo();
    }
            
    public int ultimoIdController () {
        return objUsuarioFuncionalidadModelo.ultimoId();
    }
    
}
