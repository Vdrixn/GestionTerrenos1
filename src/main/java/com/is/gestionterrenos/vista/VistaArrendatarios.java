package com.is.gestionterrenos.vista;

import javax.swing.*;

import com.is.gestionterrenos.controlador.ControladorArrendatarios;
import com.is.gestionterrenos.modelo.Arrendatario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;

public class VistaArrendatarios {
    private JPanel panel;
    private DefaultListModel<String> listModel;
    private JList<String> jList;

    public static String dniActual;
    public static String nombreActual;
    public static String edadActual;
    public static String sexoActual;


    public  VistaArrendatarios() {
        panel = new JPanel(new BorderLayout());

        listModel = new DefaultListModel<>();
        jList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(jList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel botonesPanel = new JPanel(new GridLayout(2, 1));
        JButton addButton = new JButton("Añadir");
        JButton actButton = new JButton("Actualizar");
        JButton buscarButton = new JButton("Buscar");
        JButton deleteButton = new JButton("Borrar");

         addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                abrirVentanaAñadir();
            }

            
         });

        botonesPanel.add(addButton);
        botonesPanel.add(actButton);
        botonesPanel.add(buscarButton);
        botonesPanel.add(deleteButton);
        panel.add(botonesPanel, BorderLayout.EAST);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void actualizar() {
        // Limpiar la lista actual
        listModel.clear();

        // SE LLAMA AL CONTROLADOR PARA OBTENER LOS ARRENDATARIOS
        ArrayList<Arrendatario> arrendatarios=ControladorArrendatarios.listar();
        //arrendatarios=...
        if (arrendatarios != null) {
            for (Arrendatario arrendatario : arrendatarios) {
                listModel.addElement(arrendatario.toString());
            }
        }
    }

    private void abrirVentanaAñadir() {
        final JFrame ventanaAñadir = new JFrame("Añadir Arrendatario");
        ventanaAñadir.setSize(300, 200);
        ventanaAñadir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JPanel panelAñadir = new JPanel(new GridLayout(8, 4));

        panelAñadir.add(new JLabel("DNI:"));
        final JTextField dniField = new JTextField();
        panelAñadir.add(dniField);

        panelAñadir.add(new JLabel("Nombre:"));
        final JTextField nombreField = new JTextField();
        panelAñadir.add(nombreField);

        panelAñadir.add(new JLabel("Edad:"));
        final JTextField edadField = new JTextField();
        panelAñadir.add(edadField);

        panelAñadir.add(new JLabel("Sexo:"));
        final JTextField sexoField = new JTextField();
        panelAñadir.add(sexoField);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes obtener los datos ingresados y realizar la lógica de guardado
                 dniActual = dniField.getText();
                nombreActual = nombreField.getText();
                edadActual = edadField.getText();
                sexoActual = sexoField.getText();

                ControladorArrendatarios.insertar();
                // Cerrar la ventana después de guardar
                ventanaAñadir.dispose();
                actualizar();
            }
        });

        panelAñadir.add(guardarButton);

        ventanaAñadir.getContentPane().add(panelAñadir);
        ventanaAñadir.setVisible(true);
    }

 
}
