/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se_vision;
/**
 *
 * @author rock_
 */
import java.util.ArrayList;
import java.util.List;

public class SE_vision {

    /**
     * @param args the command line arguments
     */
    static List<String> agregados = new ArrayList<>();
    static String Problem[] = {"miopia", "hipermetropia", "astigmatismo", "presbicia"};
    static int contador[] = {0, 0, 0, 0};
    public static void main(String[] args) {
        
        //int contador[] = {0, 0, 0, 0};

        Consultas c = new Consultas();

        c.Query("problema_de(vision_borrosa, Y)").stream().forEach((x) -> {
            agregados.add(x);
           // System.out.println(x);
        });
        c.Query("problema_de(dolor_cabeza, Y)").stream().forEach((x) -> {
            agregados.add(x);
           // System.out.println(x);
        });
        c.Query("problema_de(fatiga_visual, Y)").stream().forEach((x) -> {
            agregados.add(x);
           // System.out.println(x);
        });

        c.Query("problema_de(d_conducir_noche, Y)").stream().forEach((x) -> {
            agregados.add(x);
           // System.out.println(x);
        });
        /* c.Query2("sintoma_de(vision_borrosa, Y)").stream().forEach((x) -> {
            System.out.println(x);
        });*/
        comparar();
        int mayor = 0;
        int index = 0;
        for(int i=0; i<contador.length;i++){
            if(contador[i] >= mayor){
                mayor = contador[i];
                index = i;
            }
            //System.out.println(contador[i]);
        }
        
        System.out.println("Su problema puede ser "+Problem[index]);
    }
    
    public static void comparar(){
        for(String x : agregados){
            switch(x){
                case "miopia": contador[0] +=1;break; 
                case "hipermetropia": contador[1] +=1;break;
                case "astigmatismo": contador[2] +=1;break;
                case "presbicia": contador[3] +=1;break;
            }
        }
    }
}
