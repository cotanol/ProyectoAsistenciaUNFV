/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.UsuarioModelo;
import View.Ventana01RegistrosDeUsuarios;
import java.util.ArrayList;

/**
 *
 * @author brigi
 */
public class UsuarioController {
    
    UsuarioModelo objUsuarioModelo;
    Ventana01RegistrosDeUsuarios objVentReg;
    
    
    
    public UsuarioController(Ventana01RegistrosDeUsuarios objVentReg) {
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
