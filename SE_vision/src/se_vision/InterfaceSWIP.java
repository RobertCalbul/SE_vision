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
    private static final Consultas Consulta = new Consultas();
    private static final Preguntas Preguntas = new Preguntas();
    private static final List<String> agregados = new ArrayList<>();
    private static final String Problemas[] = {"miopia", "hipermetropia", "astigmatismo", "presbicia", "estrabismo", "catarata", "glaucoma"};
    private static final int C_problemas[] = {0, 0, 0, 0, 0, 0, 0};
    private static final String[] keys = Preguntas.keys();
    private static final String[] A_preguntas = Preguntas.preguntas();
    private static final JPanel P_preguntas = new JPanel(new CardLayout());
    private final GridLayout grid = new GridLayout(3, 1);
    private static final ButtonGroup B_group = new ButtonGroup();

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
            B_group.add(s);
            B_group.add(n);

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

        for (String preguntasArray1 : A_preguntas) {
            InterfaceSWIP p = new InterfaceSWIP(preguntasArray1, true);
            P_preguntas.add(p, p.toString());
        }

        JPanel controles = new JPanel();
        controles.add(new JButton(new AbstractAction("Anterior") {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) P_preguntas.getLayout();
                System.out.println(index);
                if (index > 0) {
                    index -= 1;
                    cl.previous(P_preguntas);
                }
            }
        }));

        controles.add(new JButton(new AbstractAction("Siguiente") {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) P_preguntas.getLayout();
                String query = "";
                try {
                    if (A_preguntas.length > index) {

                        if (getSelectedButtonText(B_group).equals("SI")) {
                            query = String.format("problema_de(%s, Y)", keys[index]);
                            Consulta.Query(query).stream().forEach((x) -> {
                                agregados.add(x);
                            });

                        }
                        cl.next(P_preguntas);
                        System.err.println(String.format("[%d de %d] %s %s:%s ", index, A_preguntas.length - 1, A_preguntas[index], keys[index], query));
                        index += 1;

                    } else {
                        comparar(agregados);
                        String resp = "Su problema puede ser " + Problemas[buscarMayor(C_problemas)];
                        JOptionPane.showMessageDialog(null, resp, "Alerta", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una opción", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
            }
        }));

        f.add(P_preguntas, BorderLayout.CENTER);
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
                    C_problemas[0] = Collections.frequency(l, key);
                    break;
                case "hipermetropia":
                    C_problemas[1] = Collections.frequency(l, key);
                    break;
                case "astigmatismo":
                    C_problemas[2] = Collections.frequency(l, key);
                    break;
                case "presbicia":
                    C_problemas[3] = Collections.frequency(l, key);
                    break;
                case "estrabismo":
                    C_problemas[4] = Collections.frequency(l, key);
                    break;
                case "catarata":
                    C_problemas[5] = Collections.frequency(l, key);
                    break;
                case "glaucoma":
                    C_problemas[6] = Collections.frequency(l, key);
                    break;
            }
        }
    }
}
