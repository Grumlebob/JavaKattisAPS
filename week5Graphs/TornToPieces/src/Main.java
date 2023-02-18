import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        var graph = new Digraph(n);
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            var line = scanner.nextLine();
            var split = line.split(" ");
            if (split.length == 1)
                continue;
            for (int j = 1; j < split.length; j++) {
                graph.addStringEdge(split[0], split[j]);
            }
        }

        var source = scanner.next();
        var destination = scanner.next();

        var bfs = new BreadthFirstDirectedPaths(graph, graph.stringToInt.get(source));

        var intPathList = bfs.pathTo(graph.stringToInt.get(destination));

        //Print graph adj with k value
        //System.out.println("graph.adj");
        for (var entry : graph.adj.entrySet()) {
            //System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }
        //Print graph intToString with k value
        //System.out.println("graph.intToString");
        for (var entry : graph.intToString.entrySet()) {
            //System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }
        //Print graph stringToInt with k value
        //System.out.println("graph.stringToInt");
        for (var entry : graph.stringToInt.entrySet()) {
            //System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }

        if (intPathList == null) {
            System.out.println("no route found");
            return;
        }

        var resultPrint = new ArrayList<String>();
        for (int i : intPathList) {
            resultPrint.add(graph.intToString.get(i));
        }
        System.out.println(String.join(" ", resultPrint));
    }
}