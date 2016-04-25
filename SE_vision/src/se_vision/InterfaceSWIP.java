/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Proyecto
package se_vision;
////
//Cosas a usar

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Nexus
 */
public class InterfaceSWIP extends JPanel {

    /**
     * @param args the command line arguments
     */
    private final String name;
    private static int index = 0;
    private static final Consultas c = new Consultas();
    private static final Preguntas p = new Preguntas();
    private static final List<String> agregados = new ArrayList<>();
    private static final String Problem[] = {"miopia", "hipermetropia", "astigmatismo", "presbicia"};
    private static final int contador[] = {0, 0, 0, 0};
    private static final String[] keys = p.keys();
    private static final String[] preguntasArray = p.preguntas();
    private static final JPanel panelPreg = new JPanel(new CardLayout());
    private final GridLayout grid = new GridLayout(3, 1);
    private static final ButtonGroup bgrupo = new ButtonGroup();

    public InterfaceSWIP(String name, Boolean flag) {
        this.name = name;                               //Setea pregunta a una variable

        this.setLayout(grid);                           //Setea una grilla al panel principal       
        this.setPreferredSize(new Dimension(640, 240)); //Setea dimensiones de la pantalla
        //this.setBackground(new Color(new Random().nextInt()));
        JLabel pregunta = new JLabel(name);             //Agrega pregunta a un label
        pregunta.setHorizontalAlignment(JLabel.CENTER); //Se centra la label
        this.add(pregunta);                             //Se agrega label a panel principal
        if (flag) {
            //RadioButtons
            JRadioButton s = new JRadioButton("SI");
            s.setName(name);
            JRadioButton n = new JRadioButton("NO");
            n.setName(name);

            //Agrupacion de Botones radio Buttons
            bgrupo.add(s);
            bgrupo.add(n);

            //Crea panela contencion ButtonGroup
            JPanel opt = new JPanel(new GridLayout(1, 1));  //Se crea una grilla
            opt.setLayout(new FlowLayout());                //Se sentre contenido
            opt.add(s);                                     //Se agrega el primer RadioButton
            opt.add(n);                                     //Se agrega el segundo RadioButton
            this.add(opt);                                  //Se agregan el panel al panel principal
        }
        // ButtonModel select = bgrupo.getSelection();
    }

    public static String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void creaSlide() {
        //Obtenemos preguntas
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (String preguntasArray1 : preguntasArray) {
            InterfaceSWIP p = new InterfaceSWIP(preguntasArray1, true);
            panelPreg.add(p, p.toString());
        }

        JPanel controles = new JPanel();
        controles.add(new JButton(new AbstractAction("Anterior") {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panelPreg.getLayout();
                System.out.println(index);
                if (index > 0) {
                    index -= 1;
                    cl.previous(panelPreg);
                }
            }
        }));

        controles.add(new JButton(new AbstractAction("Siguiente") {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panelPreg.getLayout();
                System.out.println(getSelectedButtonText(bgrupo));
                System.out.println(index + ">>" + keys[index]);
                if (index < preguntasArray.length - 1) {
                    if (getSelectedButtonText(bgrupo).equals("SI")) {

                        String query = String.format("problema_de(%s, Y)", keys[index]);
                        c.Query(query).stream().forEach((x) -> {
                            agregados.add(x);
                            // System.out.println(x);
                        });
                        System.err.println(query + ">>" + index + ">>" + preguntasArray.length);

                    }
                    index += 1;
                    cl.next(panelPreg);
                } else {
                    comparar(agregados);
                    String resp = "Su problema puede ser " + Problem[buscarMayor(contador)];
                    InterfaceSWIP p = new InterfaceSWIP(resp, false);
                    panelPreg.add(p, p.toString());
                    cl.next(panelPreg);
                    //System.out.println(resp);
                    //JOptionPane.showMessageDialog(null, resp, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }));

        f.add(panelPreg, BorderLayout.CENTER);
        f.add(controles, BorderLayout.SOUTH);
        f.setTitle("Sistema Experto Vision - Patricio Aros, Robert Calbul, Enrique Ketterer");
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            creaSlide();
        });
    }

    public static int buscarMayor(int[] l) {
        /**
         * *
         * Verifica cual es la enfermedad que mas se repitio y retorna el index
         * de la enfermedad probable
         */
        int mayor = 0;
        int index_c = 0;
        for (int i = 0; i < l.length; i++) {
            if (l[i] >= mayor) {
                mayor = l[i];
                index_c = i;
            }
            //System.out.println(contador[i]);
        }
        return index_c;
    }

    public static void comparar(List<String> l) {
        /**
         * *
         * Cuenta la cantidad de veces que se repitio una enfermedad
         */
        Set<String> lista = new HashSet<>(l);
        for (String key : lista) {
            System.out.println(key + " " + Collections.frequency(l, key));
            switch (key) {
                case "miopia":
                    contador[0] = Collections.frequency(l, key);
                    break;
                case "hipermetropia":
                    contador[1] = Collections.frequency(l, key);
                    break;
                case "astigmatismo":
                    contador[2] = Collections.frequency(l, key);
                    break;
                case "presbicia":
                    contador[3] = Collections.frequency(l, key);
                    break;
            }
        }
    }
}
