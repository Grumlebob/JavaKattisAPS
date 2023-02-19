import java.util.*;

public class TornToPieces {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int piecesOfMaps = sc.nextInt();
        sc.nextLine();
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, String> parentNode = new HashMap<>();

        for (int i = 0; i < piecesOfMaps; i++) {
            String[] line = sc.nextLine().split(" ");
            String vertex = line[0];
            if (!graph.containsKey(vertex)) {
                graph.put(vertex, new ArrayList<>());
            }
            for (int j = 1; j < line.length; j++) {
                String adjecent = line[j];
                if (!graph.containsKey(adjecent)) {
                    graph.put(adjecent, new ArrayList<>());
                }
                graph.get(vertex).add(adjecent);
                graph.get(adjecent).add(vertex);
            }
        }
        String source = sc.next();
        String destination = sc.next();
        sc.close();

        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            System.out.println("no route found");
            return;
        }

        //BFS to find a path from source to destination.
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(source);
        visited.add(source);
        while (!queue.isEmpty()) {
            String currentStation = queue.poll();
            //Terminate and print route if we found destination.
            if (currentStation.equals(destination)) {
                // construct and print the path
                List<String> path = new ArrayList<>();
                path.add(currentStation);
                while (parentNode.containsKey(currentStation)) {
                    currentStation = parentNode.get(currentStation);
                    path.add(currentStation);
                }
                Collections.reverse(path);
                for (String city : path) {
                    System.out.print(city + " ");
                }
                System.out.println();
                return;
            }
            //BFS on every node.
            for (String neighbor : graph.get(currentStation)) {
                if (!visited.contains(neighbor)) {
                    //If we haven't visited this node before, add it to the queue.
                    visited.add(neighbor);
                    //Add the node we came from to the map.
                    parentNode.put(neighbor, currentStation);
                    //Insert the neighbour to the queue, to be explored.
                    queue.offer(neighbor);
                }
            }
        }
        //If bfs is done and we didn't find a path, print no route found.
        System.out.println("no route found");
    }
}