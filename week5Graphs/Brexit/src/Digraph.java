import java.util.HashMap;
import java.util.HashSet;



public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;           // number of vertices in this digraph
    private final int E;                 // number of edges in this digraph
    public HashMap<Integer, HashSet<Integer>> adj;

    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        this.V = V+1;
        this.E = 0;
        adj = new HashMap<Integer, HashSet<Integer>>();
        for (int v = 0; v < V+1; v++) {
            adj.put(v, new HashSet<Integer>());
        }
    }
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
    }

    public int outdegree(int v) {
        return adj.get(v).size();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj.get(v)) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


}
