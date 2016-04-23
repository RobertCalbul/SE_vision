/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.HashMap;
import org.jpl7.Query;


/**
 *
 * @author rock_
 */
public class Consultas_prolog {

    public String consulta() {

        String lista = "";
        String query = "consult('BC_vista.pl')";
        Query ql = new Query(query);
        System.out.print(query + " " + (ql.hasSolution() ? "Exito" : "fallo"));

        String call = "problema_de(vision_borrosa, Y)";
        Query q2 = new Query(call);
        String accion = q2.toString();

        while (q2.hasMoreSolutions()) {
            HashMap ht = (HashMap) q2.nextSolution();
            System.out.println(ht.get("Y"));
            lista += ht.get("Y")+"\n";
        }
        
        return lista;
    }
    public void a() {
        
        String lista = "";
        String query = "consult('BC_vista.pl')";
        Query ql = new Query(query);
        System.out.print(query + " " + (ql.hasSolution() ? "Exito" : "fallo"));

        String call = "problema_de(vision_borrosa, Y)";
        Query q2 = new Query(call);
        String accion = q2.toString();

        while (q2.hasMoreSolutions()) {
            HashMap ht = (HashMap) q2.nextSolution();
            System.out.println(ht.get("Y"));
            lista += ht.get("Y")+"\n";
        }
        

    }
}
