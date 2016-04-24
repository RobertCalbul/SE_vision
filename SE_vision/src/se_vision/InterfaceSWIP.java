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
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    static String[] keys = {
        "cansansio_trabajo",
        "dolor_cabeza",
        "dolor_ocular",
        "d_letra_chica",
        "d_objeto_cerca",
        "d_objeto_lejo",
        "d_conducir_noche",
        "entrecerrar_ojos",
        "fatiga_visual",
        "irritacion",
        "luz_deslumbrante",
        "mayor_distancia",
        "ojos_bizcos",
        "ojos_enrojecidos",
        "ojos_hinchados",
        "ojos_cansados",
        "tension_ojos",
        "vision_doble",
        "vision_nublada",
        "vision_borrosa",
        "vision_distorcionada",};

    static String[] preguntasArray = {
        "¿Siente cansansio ocular?",
        "¿Siente dolor de cabeza al leer?",
        "¿Siente dolor ocular?",
        "¿Le dificulta ver las letras chicas?",
        "¿Le dificulta ver objetos cercanos?",
        "¿Le dificulta ver objetos lejanos?",
        "¿Le dificulta conducir de noche?",
        "¿Entrecierra los ojos al leer?",
        "¿Tiene fatíga visual?",
        "¿Presenta irritación ocular?",
        "¿La luz la considera deslumbrante?",
        "¿Tiene que alejar los objetos para verlo?",
        "¿Tiene ojos bizcos?",
        "¿Tiene los ojos enrojecidos?",
        "¿Tiene los ojos hinchados?",
        "¿Tiene ojos cansados?",
        "¿Presenta tensión en los ojos?",
        "¿Presenta visión doble?",
        "¿Presenta visión nublada?",
        "¿Presenta visión borrosa?",
        "¿Presenta visión distorcionada?"};
    //private static final Random random = new Random();
    private static final JPanel panelPreg = new JPanel(new CardLayout());
    private final String name;
    static int index = 0;
    GridLayout grid = new GridLayout(3, 1);
    static ButtonGroup bgrupo = new ButtonGroup();

    public InterfaceSWIP(String name) {
        this.name = name;                               //Setea pregunta a una variable

        this.setLayout(grid);                           //Setea una grilla al panel principal       
        this.setPreferredSize(new Dimension(640, 240)); //Setea dimensiones de la pantalla
        //this.setBackground(new Color(new Random().nextInt()));
        JLabel pregunta = new JLabel(name);             //Agrega pregunta a un label
        pregunta.setHorizontalAlignment(JLabel.CENTER); //Se centra la label

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

        this.add(pregunta);                             //Se agrega label a panel principal
        this.add(opt);                                  //Se agregan el panel al panel principal

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
            InterfaceSWIP p = new InterfaceSWIP(preguntasArray1);
            panelPreg.add(p, p.toString());
        }
        JPanel controles = new JPanel();
        controles.add(new JButton(new AbstractAction("Anterior") {

            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panelPreg.getLayout();
                System.out.println(index);
                index -= 1;
                cl.previous(panelPreg);
            }
        }));
        controles.add(new JButton(new AbstractAction("Siguiente") {

            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panelPreg.getLayout();
                System.out.println(getSelectedButtonText(bgrupo));
                System.out.println(index +">>"+keys[index]);

                index += 1;

                cl.next(panelPreg);
            }
        }));
        f.add(panelPreg, BorderLayout.CENTER);
        f.add(controles, BorderLayout.SOUTH);
        f.setTitle("Sistema Experto Vision - Patricio Aros, Robert Calbul, Enrique Ketterer");
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            System.err.println(o.toString() + " >>" + value);
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                creaSlide();
            }
        });
    }
}
