import java.util.*;

//HELP FROM https://github.com/chadfraser
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        ArrayList<ArrayList<Character>> v = new ArrayList<>();
        for (int i = 0; i < m+2; i++) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < n+2; j++) {
                row.add('0');
            }
            v.add(row);
        }

        // Read in data
        for(int i = 1; i <= m; i++) {
            String line = sc.next();
            for(int j = 1; j <= n; j++) {
                v.get(i).set(j, line.charAt(j-1));
            }
        }

        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(0, 0));

        while(!q.isEmpty()) {
            int i = q.peek().getFirst();
            int j = q.peek().getSecond();
            q.remove();

            if(v.get(i).get(j) != '0') {
                continue;
            }

            v.get(i).set(j, 's');

            if(untouched(v, i+1, j, m, n)) {
                q.add(new Pair<>(i+1, j));
            }
            if(untouched(v, i-1, j, m, n)) {
                q.add(new Pair<>(i-1, j));
            }
            if(untouched(v, i, j+1, m, n)) {
                q.add(new Pair<>(i, j+1));
            }
            if(untouched(v, i, j-1, m, n)) {
                q.add(new Pair<>(i, j-1));
            }
        }

        // Count the edges
        int total = 0;
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(v.get(i).get(j) == '1') {
                    total += count(v, i, j);
                }
            }
        }
        System.out.println(total);
    }
    static int count(ArrayList<ArrayList<Character>> v, int i, int j) {
        int total = 0;
        if(v.get(i+1).get(j) == 's') {
            total++;
        }
        if(v.get(i-1).get(j) == 's') {
            total++;
        }
        if(v.get(i).get(j+1) == 's') {
            total++;
        }
        if(v.get(i).get(j-1) == 's') {
            total++;
        }
        return total;
    }

    static boolean untouched(ArrayList<ArrayList<Character>> v, int i, int j, int m, int n) {
        if(i < 0 || j < 0 || i >= m+2 || j >= n+2) {
            return false;
        }
        if(v.get(i).get(j) != '0') {
            return false;
        }
        return true;
    }

}
