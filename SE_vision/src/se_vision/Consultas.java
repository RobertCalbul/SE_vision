/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se_vision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jpl7.Query;
import static se_vision.SE_vision.contador;

/**
 *
 * @author rock_
 */
public final class Consultas {

    private String ruta = "src/base_conocimiento/BC_vista.pl";

    public Consultas() {
        init();
    }

    public Consultas(String ruta) {
        this.ruta = ruta;
        init();
    }

    public List<String> Query(String query) {

        List<String> lista = new ArrayList<>();
        Query q = new Query(query);

        while (q.hasMoreSolutions()) {
            HashMap ht = (HashMap) q.nextSolution();
            lista.add((ht.get("X") != null ? ht.get("X") + "," : "") + (ht.get("Y") != null ? ht.get("Y") + "" : ""));
        }
        return lista;
    }

    /*public List<String> Query2(String query) {

        List<String> lista = new ArrayList<>();
        Query q = new Query(query);

        while (q.hasMoreSolutions()) {
            HashMap ht = (HashMap) q.nextSolution();
            lista.add(ht.get("X")+","+ht.get("Y"));
        }
        return lista;
    }*/
    public Boolean init() {
        Boolean bandera = false;
        try {
            String query = String.format("consult('%s')", this.ruta);
            Query ql = new Query(query);
            bandera = ql.hasSolution();
            System.out.println("Conexion con BD correcta");
        } catch (Exception ex) {
            System.err.println("Error Consultas.init() " + ex.getMessage());
        }
        return bandera;
    }
}
