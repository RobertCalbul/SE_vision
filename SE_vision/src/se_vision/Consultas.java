package se_vision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jpl7.Query;

/**
 *
 * @author Robert Calbul, Patricio Aros, Enrique Ketterer
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

    public List<String> EsSintoma() {

        List<String> lista = new ArrayList<>();
        int[] cantidades = null;
        Query q = new Query("sintoma_de(X,Y)");

        while (q.hasMoreSolutions()) {
            HashMap ht = (HashMap) q.nextSolution();
            lista.add(ht.get("Y").toString());
        }
        return lista;
    }

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
