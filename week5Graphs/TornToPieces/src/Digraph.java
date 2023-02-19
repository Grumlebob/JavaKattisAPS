/******************************************************************************
 *  Compilation:  javac Digraph.java
 *  Execution:    java Digraph filename.txt
 *  Dependencies: Bag.java In.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/42digraph/tinyDG.txt
 *                https://algs4.cs.princeton.edu/42digraph/mediumDG.txt
 *                https://algs4.cs.princeton.edu/42digraph/largeDG.txt
 *
 *  A graph, implemented using an array of lists.
 *  Parallel edges and self-loops are permitted.
 *
 *  % java Digraph tinyDG.txt
 *  13 vertices, 22 edges
 *  0: 5 1
 *  1:
 *  2: 0 3
 *  3: 5 2
 *  4: 3 2
 *  5: 4
 *  6: 9 4 8 0
 *  7: 6 9
 *  8: 6
 *  9: 11 10
 *  10: 12
 *  11: 4 12
 *  12: 9
 *
 ******************************************************************************/

import java.util.*;


public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    public int V;           // number of vertices in this digraph
    private int E;                 // number of edges in this digraph

    public Map<Integer, Set<Integer>> adj = new HashMap<>();
    public Map<String, Integer> stringToInt = new HashMap<>();
    public Map<Integer, String> intToString = new HashMap<>();
    public int vertexCounter = 0;

    //indegree
    public int[] indegree;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        indegree = new int[999];
    }

    public int V() {
        return V;
    }
    public void addEdge(int v, int w) {
        if (adj.get(v) == null) {
            adj.put(v, new HashSet<Integer>());
        }
        adj.get(v).add(w);
        indegree[w]++;
        E++;

    }
    public void addStringEdge(String v, String w) {
        if (!stringToInt.containsKey(v)) {
            stringToInt.put(v, vertexCounter);
            intToString.put(vertexCounter, v);
            vertexCounter++;
        }
        if (!stringToInt.containsKey(w)) {
            stringToInt.put(w, vertexCounter);
            intToString.put(vertexCounter, w);
            vertexCounter++;
        }
        int vInt = stringToInt.get(v);
        int wInt = stringToInt.get(w);
        //System.out.println("adding edge: " + vInt + " " + wInt + " " + v + " " + w);
        addEdge(vInt, wInt);
        addEdge(wInt, vInt);
    }

    public Iterable<Integer> adj(int v) {
        return adj.get(v);
    }

    public int indegree(int v) {
        return indegree[v];
    }

    /**
     * Returns the reverse of the digraph.
     *
     * @return the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
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
