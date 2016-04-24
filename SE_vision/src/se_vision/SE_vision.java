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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SE_vision {

    /**
     * @param args the command line arguments
     */
    static List<String> agregados = new ArrayList<>();
    static String Problem[] = {"miopia", "hipermetropia", "astigmatismo", "presbicia"};
    static int contador[] = {0, 0, 0, 0};

    public static void main(String[] args) {

        
        agrega_sintomas();
        comparar();
        System.out.println("Su problema puede ser " + Problem[buscarMayor()]);
    }
    
    public static void agrega_sintomas(){
    //int contador[] = {0, 0, 0, 0};
        Consultas c = new Consultas();

        c.Query("problema_de(vision_borrosa, Y)").stream().forEach((x) -> {
            agregados.add(x);
            // System.out.println(x);
        });
        c.Query("problema_de(vision_borrosa, Y)").stream().forEach((x) -> {
            agregados.add(x);
            // System.out.println(x);
        });
        c.Query("problema_de(vision_borrosa, Y)").stream().forEach((x) -> {
            agregados.add(x);
            // System.out.println(x);
        });
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
        c.Query("sintoma_de(vision_borrosa, Y)").stream().forEach((x) -> {
            agregados.add(x);
        });
    }

    public static int buscarMayor() {
        /***
         * Verifica cual es la enfermedad que mas se repitio
         * y retorna el index de la enfermedad probable
         */
        int mayor = 0;
        int index = 0;
        for (int i = 0; i < contador.length; i++) {
            if (contador[i] >= mayor) {
                mayor = contador[i];
                index = i;
            }
            //System.out.println(contador[i]);
        }
        return index;
    }

    public static void comparar() {
        /***
         * Cuenta la cantidad de veces que se repitio una enfermedad
         */
        Set<String> lista = new HashSet<>(agregados);
        for (String key : lista) {
            System.out.println(key + " " + Collections.frequency(agregados, key));
            switch (key) {
                case "miopia":
                    contador[0] = Collections.frequency(agregados, key);
                    break;
                case "hipermetropia":
                    contador[1] = Collections.frequency(agregados, key);
                    break;
                case "astigmatismo":
                    contador[2] = Collections.frequency(agregados, key);
                    break;
                case "presbicia":
                    contador[3] = Collections.frequency(agregados, key);
                    break;
            }
        }
    }
}
