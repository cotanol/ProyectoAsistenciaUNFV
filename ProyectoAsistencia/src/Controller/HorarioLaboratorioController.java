package Controller;
import Model.HorarioLaboratorioModelo;
import View.Ventana01RegistrosDeUsuarios;
import java.util.ArrayList;

public class HorarioLaboratorioController {
    
    HorarioLaboratorioModelo objHorarioLaboratorioModelo;
    Ventana01RegistrosDeUsuarios objVentReg;
    
    public HorarioLaboratorioController(Ventana01RegistrosDeUsuarios objVentReg) {
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
    
    public ArrayList<HorarioLaboratorioModelo> buscarHorarios(int numeroLab, String asignatura, String dia) {
        return objHorarioLaboratorioModelo.buscarHorarios(numeroLab, asignatura, dia);
    }
}    
