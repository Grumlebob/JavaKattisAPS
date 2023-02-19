import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int numberOfCountries = Integer.parseInt(line[0]);
        int numberOfEdges = Integer.parseInt(line[1]);
        int vertexOfHomeCountry = Integer.parseInt(line[2]);
        int firstCountryToLeave = Integer.parseInt(line[3]);

        if (firstCountryToLeave == vertexOfHomeCountry) {
            System.out.println("leave");
            return;
        }
        var countriesToLeave = new HashSet<Integer>();
        var CountriesThatLeft = new HashSet<Integer>();
        countriesToLeave.add(firstCountryToLeave);

        Digraph digraph = new Digraph(numberOfCountries);
        for (int i = 0; i < numberOfEdges; i++) {
            line = br.readLine().split(" ");
            int v = Integer.parseInt(line[0]);
            int w = Integer.parseInt(line[1]);
            digraph.addEdge(v, w);
            digraph.addEdge(w, v);
        }

        var startingOutdegree = new HashMap<Integer, Integer>();
        for (int i = 1; i < numberOfCountries + 1; i++) {
            startingOutdegree.put(i, digraph.outdegree(i));
        }

        while (countriesToLeave.size() > 0) {
            int countryToLeave = countriesToLeave.iterator().next();
            if (CountriesThatLeft.contains(countryToLeave))
                continue;

            if (countryToLeave == vertexOfHomeCountry) {
                System.out.println("leave");
                return;
            }
            countriesToLeave.remove(countryToLeave);
            CountriesThatLeft.add(countryToLeave);

            var verticesThatShouldRemoveCountry = digraph.adj.get(countryToLeave);

            for (var vertice : verticesThatShouldRemoveCountry) {
                digraph.adj.get(vertice).remove(countryToLeave);
            }

            for (var vertice : verticesThatShouldRemoveCountry) {
                float orignalOutdegree = startingOutdegree.get(vertice);
                float newOutdegree = digraph.outdegree(vertice);
                if (newOutdegree / orignalOutdegree <= 0.5) {
                    countriesToLeave.add(vertice);
                }
            }
        }

        if (CountriesThatLeft.contains(vertexOfHomeCountry)) {
            System.out.println("leave");
        } else {
            System.out.println("stay");
        }

    }

    static class Digraph {
        private static final String NEWLINE = System.getProperty("line.separator");
        private final int V;           // number of vertices in this digraph
        private final int E;                 // number of edges in this digraph
        public HashMap<Integer, HashSet<Integer>> adj;

        public Digraph(int V) {
            if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
            this.V = V + 1;
            this.E = 0;
            adj = new HashMap<Integer, HashSet<Integer>>();
            for (int v = 0; v < V + 1; v++) {
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
}

