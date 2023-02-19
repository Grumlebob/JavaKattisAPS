import java.util.*;

public class BreakingBad {

    private static List<List<Integer>> graph;
    private static int[] component;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<String, Integer> itemToIndex = new HashMap<>();
        Map<Integer, String> indexToItem = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String item = sc.next();
            itemToIndex.put(item, i);
            indexToItem.put(i, item);
        }

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            String item1 = sc.next();
            String item2 = sc.next();
            int index1 = itemToIndex.get(item1);
            int index2 = itemToIndex.get(item2);
            graph.get(index1).add(index2);
            graph.get(index2).add(index1);
        }

        component = new int[n];
        Arrays.fill(component, -1);

        List<Integer> component1 = new ArrayList<>();
        List<Integer> component2 = new ArrayList<>();
        boolean possible = true;

        for (int i = 0; i < n; i++) {
            if (component[i] == -1) {
                assignComponent(i, 1, component);
            }
        }

        for (int i = 0; i < n; i++) {
            if (component[i] == 1) {
                component1.add(i);
            } else {
                component2.add(i);
            }
        }

        String[] result1 = new String[component1.size()];
        for (int i = 0; i < component1.size(); i++) {
            int index = component1.get(i);
            result1[i] = indexToItem.get(index);
        }
        Arrays.sort(result1);

        String[] result2 = new String[component2.size()];
        for (int i = 0; i < component2.size(); i++) {
            int index = component2.get(i);
            result2[i] = indexToItem.get(index);
        }
        Arrays.sort(result2);

        for (int i = 0; i < component1.size(); i++) {
            int index1 = component1.get(i);
            for (int j = 0; j < graph.get(index1).size(); j++) {
                int index2 = graph.get(index1).get(j);
                if (component[index1] == component[index2]) {
                    possible = false;
                }
            }
        }

        if (!possible) {
            System.out.println("impossible");
        } else {
            for (int i = 0; i < result1.length; i++) {
                if (i == result1.length - 1) {
                    System.out.print(result1[i]);
                }
                else {
                    System.out.print(result1[i] + " ");
                }
            }
            System.out.println();
            for (int i = 0; i < result2.length; i++) {
                if (i == result2.length - 1) {
                    System.out.print(result2[i]);
                }
                else {
                    System.out.print(result2[i] + " ");
                }
            }
            System.out.println();
        }
    }

    private static void assignComponent(int v, int c, int[] component) {
        component[v] = c;
        for (int u : graph.get(v)) {
            if (component[u] == -1) {
                assignComponent(u, 3 - c, component);
            }
        }
    }
}
