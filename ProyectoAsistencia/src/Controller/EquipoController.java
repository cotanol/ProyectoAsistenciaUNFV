package Controller;
import Model.EquipoModelo;
import View.VentanaPrincipal;
import java.util.ArrayList;

public class EquipoController {
    EquipoModelo objEquipoModelo;
    VentanaPrincipal objVentReg;
    
    public EquipoController(VentanaPrincipal objVentReg) {
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
    
    public ArrayList<EquipoModelo> enlistarEquipoPorEstadoController (String estado) {
        return objEquipoModelo.enlistarEquiposPorEstado(estado);
    }
    
    public ArrayList<EquipoModelo> buscarResgistroEquipos(String buscar) {
        return objEquipoModelo.buscarResgistroEquipos(buscar);
    }
    
    public void exportarEquipoAExcel(String buscar) {
        objEquipoModelo.cargarBD_Excel_Equipo(buscar);
    }
    
    public int ultimoIdController () {
        return objEquipoModelo.ultimoId();
    }
    
    public int obtenerIDLaboratorioPorNumeroController(String numeroLaboratorio){
        return objEquipoModelo.obtenerIDLaboratorioPorNumero(numeroLaboratorio);
    }    
    
    public String obtenerNumeroLabPorIdController(int idLaboratorio){
        return objEquipoModelo.obtenerNumeroLabPorId(idLaboratorio);
    }    
}