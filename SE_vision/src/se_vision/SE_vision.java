/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se_vision;

import org.jpl7.Query;



/**
 *
 * @author rock_
 */
import java.util.HashMap;
public class SE_vision {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        String lista = "";
        String query = "consult('src/base_conocimiento/BC_vista.pl')";
        Query ql = new Query(query);
        System.out.print(query +" "+ (ql.hasSolution()?"Exito":"fallo"));
        
        String call = "problema_de(vision_borrosa, Y)";
        Query q2 = new Query(call);
        String accion = q2.toString();
        
        while(q2.hasMoreSolutions()){
            HashMap ht= (HashMap) q2.nextSolution();
            System.out.println(ht.get("Y"));
        }
    
    }
    
}
