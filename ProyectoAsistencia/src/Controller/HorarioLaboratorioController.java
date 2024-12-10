package Controller;
import Model.HorarioLaboratorioModelo;
import View.VentanaPrincipal;
import java.util.ArrayList;

public class HorarioLaboratorioController {
    
    HorarioLaboratorioModelo objHorarioLaboratorioModelo;
    VentanaPrincipal objVentReg;
    
    public HorarioLaboratorioController(VentanaPrincipal objVentReg) {
        this.objVentReg = objVentReg;
        objHorarioLaboratorioModelo = new HorarioLaboratorioModelo(objVentReg);
    }
    
    public HorarioLaboratorioController() {
        objHorarioLaboratorioModelo = new HorarioLaboratorioModelo(objVentReg);
    }
    
    public int insertarHorarioLaboratorioController (HorarioLaboratorioModelo obj) {
        return objHorarioLaboratorioModelo.insertarHorarioLaboratorioModelo(obj);
    }
    
    public int modificarHorarioLaboratorioController (HorarioLaboratorioModelo obj) {
        return objHorarioLaboratorioModelo.modificarHorarioLaboratorioModelo(obj);
    }
    
    public int eliminarHorarioLaboratorioController (HorarioLaboratorioModelo obj) {
        return objHorarioLaboratorioModelo.eliminarHorarioLaboratorioModelo(obj);
    }
    
    public ArrayList<HorarioLaboratorioModelo> enlistarHorarioLaboratorioController () {
        return objHorarioLaboratorioModelo.enlistarHorarioLaboratorioModelo();
    }
            
    public int ultimoIdController () {
        return objHorarioLaboratorioModelo.ultimoId();
    }
    
    public void exportarUsuariosAExcel(String buscar) {
        objHorarioLaboratorioModelo.cargarBD_Excel_HorarioLaboratorio(buscar);
    }
    
    public ArrayList<HorarioLaboratorioModelo> buscarResgistroHorarioLaboratorioController(String buscar){
        return objHorarioLaboratorioModelo.buscarResgistroHorarioLaboratorio(buscar);
    }
    
    public int obtenerIdAsignaturaPorNombreController(String nombreAsignatura) {
        return objHorarioLaboratorioModelo.obtenerIdAsignaturaPorNombre(nombreAsignatura);
    }
    
    public int obtenerIdUsuarioPorNombreController(String nombreUsuario) {
        return objHorarioLaboratorioModelo.obtenerIdUsuarioPorNombre(nombreUsuario);
    }
    
    public int obtenerIDLaboratorioPorNumeroController(String numeroLaboratorio){
        return objHorarioLaboratorioModelo.obtenerIDLaboratorioPorNumero(numeroLaboratorio);
    }
    
    public String obtenerNombreAsignaturaPorIdController(int idAsignatura) {
        return objHorarioLaboratorioModelo.obtenerNombreAsignaturaPorId(idAsignatura);
    }
            
    public String obtenerNombreUsuarioPorIdController(int idUsuario) {
        return objHorarioLaboratorioModelo.obtenerNombreUsuarioPorId(idUsuario);
    }
    
    public String obtenerNumeroLabPorIdController(int idLaboratorio){
        return objHorarioLaboratorioModelo.obtenerNumeroLabPorId(idLaboratorio);
    }    
    
}