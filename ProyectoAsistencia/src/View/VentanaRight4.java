package View;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import Util.ComponentFactory;
import Util.Constantes;
import Util.EstiloHover;
import Model.HorarioLaboratorioModelo;
import Controller.HorarioLaboratorioController;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import Util.Enums.Dia;

public class VentanaRight4 extends JPanel {
    // Controlador
    private HorarioLaboratorioController horarioControlador;

    // Componentes principales
    private JLabel lbRegistrosHorarios;
    private JLabel nombreSubPanel1_1, nombreSubPanel1_2;
    private JPanel subPanel1;
    private JLabel lbBuscarLaboratorio;
    private JTextField txtBuscarLaboratorio;
    private JButton btnBuscar, btnAgregar, btnModificar, btnEliminar;
    private JTable tablaHorarios;
    private DefaultTableModel modeloHorarios;

    // Datos
    private ArrayList<HorarioLaboratorioModelo> listaHorarios;

    public VentanaRight4(HorarioLaboratorioController horarioControlador) {
        this.horarioControlador = horarioControlador;

        setLayout(null);
        setBackground(Constantes.COLOR_FONDO_PANEL);

        inicializarComponentes();
        agregarEventos();
        listarHorarios();
    }

    private void inicializarComponentes() {
        // Título
        lbRegistrosHorarios = ComponentFactory.crearEtiqueta("HORARIOS DE LABORATORIO", 50, 30, 630, 52, Constantes.FUENTE_TITULO, Constantes.COLOR_TEXTO_NEGRO);
        add(lbRegistrosHorarios);

        // Subtítulo
        nombreSubPanel1_1 = ComponentFactory.crearEtiqueta("REGISTRO DE HORARIOS", 100, 110, 500, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        nombreSubPanel1_1.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        nombreSubPanel1_1.setOpaque(true);
        nombreSubPanel1_1.setHorizontalAlignment(SwingConstants.CENTER);
        add(nombreSubPanel1_1);

        // Subpanel para datos del registro
        subPanel1 = new JPanel(null);
        subPanel1.setBounds(100, 180, 1090, 270);
        subPanel1.setBackground(Color.WHITE);
        subPanel1.setBorder(Constantes.BORDER_NEGRO);
        add(subPanel1);

        // Campos de búsqueda
        lbBuscarLaboratorio = ComponentFactory.crearEtiqueta("Nro. de Laboratorio", 30, 20, 300, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
        subPanel1.add(lbBuscarLaboratorio);
        txtBuscarLaboratorio = ComponentFactory.crearCampoTexto(30, 50, 300, 30, Constantes.BORDER_HOVER);
        subPanel1.add(txtBuscarLaboratorio);

        // Botones
        btnBuscar = ComponentFactory.crearBotonAccion("BUSCAR", 350, 50, 150, 30);
        subPanel1.add(btnBuscar);

        btnAgregar = ComponentFactory.crearBotonAccion("AGREGAR", 700, 110, 150, 50);
        btnModificar = ComponentFactory.crearBotonAccion("MODIFICAR", 870, 110, 150, 50);
        btnEliminar = ComponentFactory.crearBotonAccion("ELIMINAR", 1040, 110, 150, 50);

        add(btnAgregar);
        add(btnModificar);
        add(btnEliminar);

        // Subtítulo para la tabla
        nombreSubPanel1_2 = ComponentFactory.crearEtiqueta("LISTA DE HORARIOS", 100, 490, 570, 50, Constantes.FUENTE_SUBTITULO, Constantes.COLOR_TEXTO_BLANCO);
        nombreSubPanel1_2.setBackground(Constantes.COLOR_HOVER_SELECCIONADO1);
        nombreSubPanel1_2.setOpaque(true);
        nombreSubPanel1_2.setHorizontalAlignment(SwingConstants.CENTER);
        add(nombreSubPanel1_2);

        // Tabla de horarios
        String[] columnasHorarios = {"Asignatura", "Laboratorio", "Día", "Hora Inicio", "Hora Fin", "Docente"};
        modeloHorarios = new DefaultTableModel(columnasHorarios, 0);
        tablaHorarios = ComponentFactory.crearTabla(columnasHorarios);
        tablaHorarios.setModel(modeloHorarios);

        JScrollPane scrollTabla = ComponentFactory.crearScrollTabla(tablaHorarios, 100, 560, 1090, 270);
        add(scrollTabla);
    }

    private void agregarEventos() {
        // Eventos para botones
        btnAgregar.addActionListener(e -> manejarAgregarHorario());
        btnModificar.addActionListener(e -> manejarModificarHorario());
        btnEliminar.addActionListener(e -> manejarEliminarHorario());
        btnBuscar.addActionListener(e -> buscarHorarios());

        // Eventos de hover en botones
        btnAgregar.addMouseListener(new EstiloHover.HoverAccionBoton(btnAgregar));
        btnModificar.addMouseListener(new EstiloHover.HoverAccionBoton(btnModificar));
        btnEliminar.addMouseListener(new EstiloHover.HoverAccionBoton(btnEliminar));
        btnBuscar.addMouseListener(new EstiloHover.HoverAccionBoton(btnBuscar));

        // Evento de selección en la tabla
        tablaHorarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                llenarCamposDesdeTabla();
            }
        });
    }

    private void manejarAgregarHorario() {
        DialogoHorario dialogo = new DialogoHorario(this);
        dialogo.setVisible(true);

        if(dialogo.isGuardadoExitoso()) {
            HorarioLaboratorioModelo horario = new HorarioLaboratorioModelo();
            String[] datos = dialogo.getDatosHorario();

            // Convertir nombres a IDs
            int idAsignatura = horarioControlador.obtenerIdAsignaturaPorNombreController(datos[0]);
            int idLaboratorio = Integer.parseInt(datos[1]);
            int idUsuario = horarioControlador.obtenerIdUsuarioPorNombreController(datos[5]);

            // Mapear los datos al modelo
            horario.setIdAsignatura(idAsignatura);
            horario.setIdLaboratorio(idLaboratorio);
            horario.setDia(datos[2]);
            horario.setHorarioInicio(datos[3]);
            horario.setHorarioFin(datos[4]);
            horario.setIdUsuario(idUsuario);

            int estado = horarioControlador.insertarHorarioLaboratorioController(horario);
            mostrarMensaje(estado, "Horario Agregado 🐧!!", "Horario no Agregado 🐧!!");

            listarHorarios();
            limpiarCampos();
        }
    }

    private void manejarModificarHorario() {
        int filaSeleccionada = tablaHorarios.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String[] datosExistentes = new String[6];
            for (int i = 0; i < 6; i++) {
                datosExistentes[i] = tablaHorarios.getValueAt(filaSeleccionada, i).toString();
            }

            DialogoHorario dialogo = new DialogoHorario(this, datosExistentes);
            dialogo.setVisible(true);

            if (dialogo.isGuardadoExitoso()) {
                HorarioLaboratorioModelo horario = new HorarioLaboratorioModelo();
                String[] datos = dialogo.getDatosHorario();

                // Mapear los datos del diálogo al modelo
                horario.setIdAsignatura(Integer.parseInt(datos[0]));
                horario.setIdLaboratorio(Integer.parseInt(datos[1]));
                horario.setDia(datos[2]);
                horario.setHorarioInicio(datos[3]);
                horario.setHorarioFin(datos[4]);
                horario.setIdUsuario(Integer.parseInt(datos[5]));

                int estado = horarioControlador.modificarHorarioLaboratorioController(horario);
                mostrarMensaje(estado, "Horario Modificado 🐧!!", "Horario no Modificado 🐧!!");

                listarHorarios();
                limpiarCampos();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un horario para modificar 🐧!!");
        }
    }

    private void manejarEliminarHorario() {
        int filaSeleccionada = tablaHorarios.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String asignatura = tablaHorarios.getValueAt(filaSeleccionada, 0).toString();
            HorarioLaboratorioModelo horario = new HorarioLaboratorioModelo();
            horario.setIdAsignatura(Integer.parseInt(asignatura));

            int estado = horarioControlador.eliminarHorarioLaboratorioController(horario);
            mostrarMensaje(estado, "Horario Eliminado 🐧!!", "Horario no Eliminado 🐧!!");

            listarHorarios();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un horario para eliminar 🐧!!");
        }
    }

    private void mostrarMensaje(int estado, String mensajeExito, String mensajeError) {
        if (estado == 1) {
            JOptionPane.showMessageDialog(null, mensajeExito);
        } else {
            JOptionPane.showMessageDialog(null, mensajeError);
        }
    }

private void buscarHorarios() {
    String busqueda = txtBuscarLaboratorio.getText().trim().toLowerCase();
    if (!busqueda.isEmpty()) {
        modeloHorarios.setRowCount(0);

        try {
            int numeroLab = Integer.parseInt(busqueda);
            
            // Modify the search to use -1 for unspecified asignatura and empty string for unspecified dia
            listaHorarios = horarioControlador.buscarHorarios(numeroLab, -1, "");

            if (listaHorarios.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron horarios para el laboratorio " + numeroLab);
            } else {
                for (HorarioLaboratorioModelo horario : listaHorarios) {
                    modeloHorarios.addRow(new Object[]{
                        horarioControlador.obtenerNombreAsignaturaPorIdController(horario.getIdAsignatura()),
                        horario.getIdLaboratorio(),
                        horario.getDia(),
                        horario.getHorarioInicio(),
                        horario.getHorarioFin(),
                        horarioControlador.obtenerNombreUsuarioPorIdController(horario.getIdUsuario())
                    });
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un número de laboratorio válido");
        }
    } else {
        listarHorarios();
    }
}

    public void listarHorarios() {
        modeloHorarios.setRowCount(0);
        listaHorarios = horarioControlador.enlistarHorarioLaboratorioController();

        for (HorarioLaboratorioModelo horario : listaHorarios) {
            modeloHorarios.addRow(new Object[]{
                horarioControlador.obtenerNombreAsignaturaPorIdController(horario.getIdAsignatura()),
                horarioControlador.obtenerNumeroLabPorIdController(horario.getIdLaboratorio()),
                horario.getDia(),
                horario.getHorarioInicio(),
                horario.getHorarioFin(),
                horarioControlador.obtenerNombreUsuarioPorIdController(horario.getIdUsuario())
            });
        }
    }

    private void limpiarCampos() {
        txtBuscarLaboratorio.setText("");
    }

    private void llenarCamposDesdeTabla() {
        int filaSeleccionada = tablaHorarios.getSelectedRow();
        if (filaSeleccionada != -1) {
            txtBuscarLaboratorio.setText(tablaHorarios.getValueAt(filaSeleccionada, 1).toString());
        }
    }

    private class DialogoHorario extends JDialog {
        private JTextField txtCurso, txtNumeroLab, txtDia, txtHoraInicio, txtHoraFin, txtDocente;
        private JButton btnGuardar, btnCancelar;
        private boolean guardadoExitoso = false;
        private String[] datosHorario;
        public DialogoHorario(Component parent) {
            super((Frame)SwingUtilities.getAncestorOfClass(Frame.class, parent), "Gestionar Horario", true);
            // Resto del constructor igual
        }
                public DialogoHorario(Component parent, String[] datosExistentes) {
            super((Frame)SwingUtilities.getAncestorOfClass(Frame.class, parent), "Gestionar Horario", true);
            // Resto del constructor igual que en la versión anterior
        }
        public DialogoHorario(Frame parent, String[] datosExistentes) {
            super(parent, "Gestionar Horario", true);
            setSize(600, 500);
            setLocationRelativeTo(parent);
            setLayout(null);
            setBackground(Constantes.COLOR_FONDO_PANEL);
            
            String[] labels = {
                "Nombre del Curso:", 
                "Número de Laboratorio:", 
                "Día (LUNES, MARTES, etc):", 
                "Hora de Inicio (HH:MM):", 
                "Hora de Fin (HH:MM):", 
                "Docente:"
            };
            
            JTextField[] txtFields = new JTextField[6];
            
            for (int i = 0; i < labels.length; i++) {
                JLabel lbl = ComponentFactory.crearEtiqueta(labels[i], 20, 20 + (i * 60), 300, 30, Constantes.FUENTE_LABEL, Constantes.COLOR_HOVER_SELECCIONADO1);
                add(lbl);
                
                txtFields[i] = ComponentFactory.crearCampoTexto(20, 50 + (i * 60), 540, 30, Constantes.BORDER_HOVER);
                add(txtFields[i]);
            }
            
            txtCurso = txtFields[0];
            txtNumeroLab = txtFields[1];
            txtDia = txtFields[2];
            txtHoraInicio = txtFields[3];
            txtHoraFin = txtFields[4];
            txtDocente = txtFields[5];
            
            // Prellenar campos si existen datos existentes
            if (datosExistentes != null) {
                txtCurso.setText(datosExistentes[0]);
                txtNumeroLab.setText(datosExistentes[1]);
                txtDia.setText(datosExistentes[2]);
                txtHoraInicio.setText(datosExistentes[3]);
                txtHoraFin.setText(datosExistentes[4]);
                txtDocente.setText(datosExistentes[5]);
            }
            
            btnGuardar = ComponentFactory.crearBotonAccion("Guardar", 140, 420, 150, 30);
            btnCancelar = ComponentFactory.crearBotonAccion("Cancelar", 300, 420, 150, 30);
            
            add(btnGuardar);
            add(btnCancelar);
            
            btnGuardar.addMouseListener(new EstiloHover.HoverAccionBoton(btnGuardar));
            btnCancelar.addMouseListener(new EstiloHover.HoverAccionBoton(btnCancelar));
            
            btnGuardar.addActionListener(e -> guardarHorario());
            btnCancelar.addActionListener(e -> dispose());
        }
        
        private void guardarHorario() {
            // Validaciones
            if(txtCurso.getText().trim().isEmpty() || 
               txtNumeroLab.getText().trim().isEmpty() || 
               txtDia.getText().trim().isEmpty() || 
               txtHoraInicio.getText().trim().isEmpty() || 
               txtHoraFin.getText().trim().isEmpty() || 
               txtDocente.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor complete todos los campos", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                // Validar formato de hora
                LocalTime.parse(txtHoraInicio.getText());
                LocalTime.parse(txtHoraFin.getText());
                
                // Validar día
                Dia.valueOf(txtDia.getText().toUpperCase());
                
                // Validar número de laboratorio
                Integer.parseInt(txtNumeroLab.getText());
                
                datosHorario = new String[6];
                datosHorario[0] = txtCurso.getText().trim();
                datosHorario[1] = txtNumeroLab.getText().trim();
                datosHorario[2] = txtDia.getText().trim();
                datosHorario[3] = txtHoraInicio.getText().trim();
                datosHorario[4] = txtHoraFin.getText().trim();
                datosHorario[5] = txtDocente.getText().trim();
                
                guardadoExitoso = true;
                dispose();
            } catch (DateTimeParseException | IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, 
                    "Formato de hora, día o número de laboratorio inválido. " +
                    "Use HH:MM para horas, días en mayúsculas, y un número para el laboratorio.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        
        public boolean isGuardadoExitoso() {
            return guardadoExitoso;
        }
        
        public String[] getDatosHorario() {
            return datosHorario;
        }
    }
}
