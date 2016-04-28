package se_vision;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
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
    private static final Consultas CONSULTA = new Consultas();
    private static final Preguntas PREGUNTAS = new Preguntas();
    private static final List<String> AGREGADOS = new ArrayList<>();
    private static final String[] PROBLEMAS = {"miopia", "hipermetropia", "astigmatismo", "presbicia", "estrabismo", "catarata", "glaucoma"};
    private static final int[] C_PROBLEMAS = {0, 0, 0, 0, 0, 0, 0};
    private static final String[] KEYS = PREGUNTAS.keys();
    private static final String[] A_PREGUNTAS = PREGUNTAS.preguntas();
    private static final JPanel P_PREGUNTAS = new JPanel(new CardLayout());
    private final GridLayout grid = new GridLayout(3, 1);
    private static final ButtonGroup B_GROUP = new ButtonGroup();
    private Image imagen;



    public InterfaceSWIP(String name, Boolean flag) {
        this.name = name;                               //Setea pregunta a una variable

        this.setLayout(grid);                           //Setea una grilla al panel principal       
        this.setPreferredSize(new Dimension(640, 150)); //Setea dimensiones de la pantalla
        //this.setBackground(new Color(new Random().nextInt()));
        JLabel pregunta = new JLabel(name);             //Agrega pregunta a un label
        pregunta.setHorizontalAlignment(JLabel.CENTER); //Se centra la label

        //setBackground("back.png");
        this.add(pregunta);                             //Se agrega label a panel principal
       this.setOpaque(false);
        if (flag) {
            //RadioButtons
            JRadioButton s = new JRadioButton("SI");
            s.setName(name);
            s.setOpaque(false);
            JRadioButton n = new JRadioButton("NO");
            n.setName(name);
            n.setOpaque(false);
            //Agrupacion de Botones radio Buttons
            B_GROUP.add(s);
            B_GROUP.add(n);

            //Crea panela contencion ButtonGroup
            JPanel opt = new JPanel(new GridLayout(1, 1));  //Se crea una grilla
            opt.setOpaque(false);
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
        
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Panel2 principal = new Panel2();
        principal.setBackground("back.png");
        GridLayout gprin = new GridLayout(2,1);        
        P_PREGUNTAS.setOpaque(false);        
        principal.setLayout(gprin);
        
        for (String preguntasArray1 : A_PREGUNTAS) {
            InterfaceSWIP p = new InterfaceSWIP(preguntasArray1, true);
            P_PREGUNTAS.add(p, p.toString());
        }

        JPanel controles = new JPanel();
        controles.setOpaque(false);
        controles.add(new JButton(new AbstractAction("Anterior") {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) P_PREGUNTAS.getLayout();
                System.out.println(index);
                if (index > 0) {
                    index -= 1;
                    cl.previous(P_PREGUNTAS);
                }
            }
        }));

        controles.add(new JButton(new AbstractAction("Siguiente") {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) P_PREGUNTAS.getLayout();
                String query = "";
                try {
                    if (A_PREGUNTAS.length > index) {

                        if (getSelectedButtonText(B_GROUP).equals("SI")) {
                            query = String.format("problema_de(%s, Y)", KEYS[index]);
                            CONSULTA.Query(query).stream().forEach((x) -> {
                                AGREGADOS.add(x);
                            });

                        }
                        cl.next(P_PREGUNTAS);
                        System.err.println(String.format("[%d de %d] %s %s:%s ", index, A_PREGUNTAS.length - 1, A_PREGUNTAS[index], KEYS[index], query));
                        index += 1;

                    } else {
                        comparar(AGREGADOS);
                        String resp = "Su problema puede ser " + PROBLEMAS[buscarMayor(C_PROBLEMAS)];
                        JOptionPane.showMessageDialog(null, resp, "Alerta", JOptionPane.ERROR_MESSAGE);
                    }
                    B_GROUP.clearSelection();
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una opción", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
            }
        }));
        principal.add(P_PREGUNTAS, BorderLayout.CENTER);
        principal.add(controles, BorderLayout.SOUTH);
        //f.add(P_PREGUNTAS, BorderLayout.CENTER);
        //f.add(controles, BorderLayout.SOUTH);
        f.add(principal);
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
                    C_PROBLEMAS[0] = Collections.frequency(l, key);
                    break;
                case "hipermetropia":
                    C_PROBLEMAS[1] = Collections.frequency(l, key);
                    break;
                case "astigmatismo":
                    C_PROBLEMAS[2] = Collections.frequency(l, key);
                    break;
                case "presbicia":
                    C_PROBLEMAS[3] = Collections.frequency(l, key);
                    break;
                case "estrabismo":
                    C_PROBLEMAS[4] = Collections.frequency(l, key);
                    break;
                case "catarata":
                    C_PROBLEMAS[5] = Collections.frequency(l, key);
                    break;
                case "glaucoma":
                    C_PROBLEMAS[6] = Collections.frequency(l, key);
                    break;
            }
        }
    }
}
