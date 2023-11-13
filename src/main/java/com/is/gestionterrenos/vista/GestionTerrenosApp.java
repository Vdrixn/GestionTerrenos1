package com.is.gestionterrenos.vista;
import javax.swing.*;

import com.is.gestionterrenos.dao.ArrendatarioDAO;
import com.is.gestionterrenos.dao.ConexionDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

// Clase principal que contiene el JFrame principal
public class GestionTerrenosApp {
    public static ImageIcon icono;
    public static boolean vistaArrenActiva = false;
    public static boolean vistaTerrenActiva = false;
    public static boolean vistaParcelaActiva = false;
    public static boolean vistaReciboActiva = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                icono = new ImageIcon("src/main/resources/icono.png");
                //Esta línea inicializa la conexión a la base de datos y evita un retardo que se producía
                //una vez iniciada la aplicación, al seleccionar una de las bases de datos.
                ArrendatarioDAO.conn=ConexionDB.getConn();
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        final JFrame frame = new JFrame("Gestión de Terrenos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 300);

        final JPanel panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(panel);

        String[] opciones = {"Seleccione una opción","Arrendatarios", "Terrenos", "Parcelas", "Recibos"};

        final JComboBox<String> comboBox = new JComboBox<>(opciones);
        panel.add(comboBox, BorderLayout.NORTH);

        // Crear instancias de las vistas específicas
        final VistaArrendatarios vistaArrendatarios = new VistaArrendatarios();
        final VistaTerrenos vistaTerrenos = new VistaTerrenos();
        final VistaParcelas vistaParcelas = new VistaParcelas();
        final VistaRecibos vistaRecibos = new VistaRecibos();
        

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboBox.getSelectedItem();

                // Verificar la selección y actualizar la vista correspondiente
                if ("Arrendatarios".equals(seleccion)) {
                    // Mostrar la vista de arrendatarios
                    //Comprobamos que vista está activa para borrarla y actualizar la interfaz
                    if(vistaTerrenActiva){
                        panel.remove(vistaTerrenos.getPanel());
                        vistaTerrenActiva = false;
                    }
                    else if(vistaReciboActiva){
                        panel.remove(vistaRecibos.getPanel());
                        vistaReciboActiva = false;
                    }
                    else if(vistaParcelaActiva){
                        panel.remove(vistaParcelas.getPanel());
                        vistaParcelaActiva = false;
                    }

                    panel.add(vistaArrendatarios.getPanel(), BorderLayout.CENTER);
                    vistaArrendatarios.actualizar(null);
                    frame.revalidate();
                    vistaArrenActiva = true;
                } else if ("Terrenos".equals(seleccion)) {
                    // Mostrar la vista de terrenos

                    //Comprobamos que vista está activa para borrarla y actualizar la interfaz
                    if(vistaArrenActiva){
                        panel.remove(vistaArrendatarios.getPanel());
                        vistaArrenActiva = false;
                    }
                    else if(vistaReciboActiva){
                        panel.remove(vistaRecibos.getPanel());
                        vistaReciboActiva = false;
                    }
                    else if(vistaParcelaActiva){
                        panel.remove(vistaParcelas.getPanel());
                        vistaParcelaActiva = false;
                    }
                    panel.add(vistaTerrenos.getPanel(), BorderLayout.CENTER);
                    vistaTerrenos.actualizar(null);
                    frame.revalidate();
                    vistaTerrenActiva = true;
                } else if("Parcelas".equals(seleccion)){
                    //mostrar vista parcelas 
                     if(vistaArrenActiva){
                        panel.remove(vistaArrendatarios.getPanel());
                        vistaArrenActiva = false;
                    }
                    else if(vistaTerrenActiva){
                        panel.remove(vistaTerrenos.getPanel());
                        vistaTerrenActiva = false;
                    }
                    else if(vistaReciboActiva){
                        panel.remove(vistaRecibos.getPanel());
                        vistaReciboActiva = false;
                    }
                    panel.add(vistaParcelas.getPanel(), BorderLayout.CENTER);
                    vistaParcelas.actualizar(null);
                    frame.revalidate();
                    vistaParcelaActiva= true;

                }else if("Recibos".equals(seleccion)){
                    //mostrar vista parcelas 
                     if(vistaArrenActiva){
                        panel.remove(vistaArrendatarios.getPanel());
                        vistaArrenActiva = false;
                    }
                    else if(vistaTerrenActiva){
                        panel.remove(vistaTerrenos.getPanel());
                        vistaTerrenActiva = false;
                    }
                    else if(vistaParcelaActiva){
                        panel.remove(vistaParcelas.getPanel());
                        vistaParcelaActiva = false;
                    }
                    panel.add(vistaRecibos.getPanel(), BorderLayout.CENTER);
                    vistaRecibos.actualizar(null);
                    frame.revalidate();
                    vistaReciboActiva= true;
                }
                else {
                    // Limpiar la vista si no es la opción de arrendatarios o terrenos
                    // vistaArrendatarios.limpiar();
                    panel.remove(vistaArrendatarios.getPanel());
                    panel.remove(vistaTerrenos.getPanel());
                    panel.remove(vistaParcelas.getPanel());
                    panel.remove(vistaRecibos.getPanel());
                    
                }

            }
        });

        frame.setLocationRelativeTo(null);
        frame.setIconImage(icono.getImage());
        frame.setVisible(true);
    }
}

// Clase VistaArrendatarios que contiene la lógica y la interfaz específica para los arrendatarios
