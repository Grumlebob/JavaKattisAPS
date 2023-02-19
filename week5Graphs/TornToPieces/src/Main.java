import java.util.*;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        var graph = new Digraph(n);
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            var line = scanner.nextLine();
            var split = line.split(" ");
            if (split.length <= 1)
                continue;
            for (int j = 1; j < split.length; j++) {
                graph.addStringEdge(split[0], split[j]);
            }
        }
        graph.V = graph.vertexCounter;

        var source = scanner.next();
        var destination = scanner.next();

        var bfs = new BreadthFirstDirectedPaths(graph, graph.stringToInt.get(source));

        if (!bfs.hasPathTo(graph.stringToInt.get(destination))) {
            System.out.println("no route found");
            return;
        }

        var intPathList = bfs.pathTo(graph.stringToInt.get(destination));
        var resultPrint = new ArrayList<String>();
        for (int i : intPathList) {
            resultPrint.add(graph.intToString.get(i));
        }
        System.out.println(String.join(" ", resultPrint));


    }

}