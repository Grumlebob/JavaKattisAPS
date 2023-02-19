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

        PriorityQueue<Integer> countriesToLeave = new PriorityQueue<>(Comparator.comparingInt(i -> digraph.outdegree(i)));
        Set<Integer> countriesThatLeft = new HashSet<>();

        countriesToLeave.add(firstCountryToLeave);

        while (!countriesToLeave.isEmpty()) {
            int countryToLeave = countriesToLeave.poll();
            if (countryToLeave == vertexOfHomeCountry) {
                System.out.println("leave");
                return;
            }
            if (countriesThatLeft.contains(countryToLeave))
                continue;

            countriesThatLeft.add(countryToLeave);

            var verticesThatShouldRemoveCountry = digraph.adj.get(countryToLeave);

            for (var vertice : verticesThatShouldRemoveCountry) {
                digraph.adj.get(vertice).remove(countryToLeave);
            }

            for (var vertice : verticesThatShouldRemoveCountry) {
                int originalOutdegree = startingOutdegree.get(vertice);
                int newOutdegree = digraph.outdegree(vertice);
                if (newOutdegree * 2 <= originalOutdegree) {
                    countriesToLeave.add(vertice);
                }
            }
        }

        if (countriesThatLeft.contains(vertexOfHomeCountry)) {
            System.out.println("leave");
        } else {
            System.out.println("stay");
        }
    }

    static class Digraph {
        public HashMap<Integer, HashSet<Integer>> adj;

        public Digraph(int V) {

            adj = new HashMap<>();
            for (int v = 0; v < V + 1; v++) {
                adj.put(v, new HashSet<>());
            }
        }

        public void addEdge(int v, int w) {
            adj.get(v).add(w);
        }

        public int outdegree(int v) {
            return adj.get(v).size();
        }
    }
}
