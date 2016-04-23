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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
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
    
    static String[] preguntasArray={"¿Come pan con chancho?",
                                 "¿Sera posible que se coma un pan con chancho?",
                                 "¿We require more minerals?",
                                 "¿We require more vespen gas?",
                                 "¿Los supply deppot?, donde estan mis supplys",
                                 "¿Sera posible que se me ocurra algo más?",
                                 "¿Ahi nomas?"};
    private static final Random random = new Random();
    private static final JPanel panelPreg = new JPanel(new CardLayout());
    private final String name;
    
    static int index=0;
    
    static ButtonGroup bgrupo = new ButtonGroup();
    
    public InterfaceSWIP(String name){
        this.name=name;
        this.setPreferredSize(new Dimension(320, 240));
        //this.setBackground(new Color(random.nextInt()));
        this.add(new JLabel(name));
        
        //Agrupacion de Botones radio Buttons
        
        //RadioButtons
        JRadioButton s = new JRadioButton("SI");
        JRadioButton n = new JRadioButton("NO");
        JRadioButton tl = new JRadioButton("TAL VEZ");
        
        bgrupo.add(s);
        bgrupo.add(n);
        bgrupo.add(tl);
        JPanel opt = new JPanel(new GridLayout(3,1));
        opt.add(s);
        opt.add(n);
        opt.add(tl);
        
        this.add(opt);
//        this.add(s);
//        this.add(n);
//        this.add(tl);
        ButtonModel select = bgrupo.getSelection();
        
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
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                creaSlide();
            }
        });
    }
    
    public static void creaSlide(){
        //Obtenemos preguntas
        
        
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 0; i < 7; i++) {
            InterfaceSWIP p = new InterfaceSWIP(preguntasArray[i]);
            panelPreg.add(p, p.toString());
                       
            
        }
        JPanel controles = new JPanel();
        controles.add(new JButton(new AbstractAction("Corregir") {

            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panelPreg.getLayout();
                System.out.println(index);
                index-=1;
                cl.previous(panelPreg);
            }
        }));
        controles.add(new JButton(new AbstractAction("Siguiente") {

            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panelPreg.getLayout();
                System.out.println(getSelectedButtonText(bgrupo));
                System.out.println(index);
                index+=1;
                
                cl.next(panelPreg);
            }
        }));
        f.add(panelPreg, BorderLayout.CENTER);
        f.add(controles, BorderLayout.SOUTH);
        f.setTitle("SWI JAVA COMBI");
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
    


 
    
}
