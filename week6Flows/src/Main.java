import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int players = sc.nextInt();
        int amountWhoCanSeeEachOther = sc.nextInt();
        int s = 0;
        int t = (players*2)+1;
        if (players > amountWhoCanSeeEachOther) {
            System.out.println("Impossible");
            return;
        }

        //s = 0.  Student 1,2,3, Target 4,5,6 and t = 7.
        FlowNetwork G = new FlowNetwork((players*2)+2);
        for (int i = 1; i < amountWhoCanSeeEachOther+1; i++) {
            if (i <= players) {
                //Add edges from s to all players
                G.addEdge(new FlowEdge(s, i, 1));
                //Add edges from all players to t
                G.addEdge(new FlowEdge(i+players, t, 1));
            }
            //Add edges from all players to t
            //Add edges from player to Target, both ways.
            int a = sc.nextInt();
            int b = sc.nextInt();
            var edgeOneWay = new FlowEdge(a,(b+players),999999);
            var edgeOtherWay = new FlowEdge(b,(a+players),99999);
            G.addEdge(edgeOneWay);
            G.addEdge(edgeOtherWay);
        }

        // compute maximum flow and minimum cut
        //var resultList = new ArrayList<Integer>();
        var resultMap = new HashMap<Integer, Integer>();
        FordFulkerson maxflow = new FordFulkerson(G, s, t);
        for (int v = 0; v < G.V(); v++) {
            for (FlowEdge e : G.adj(v)) {
                if ((v == e.from()) && e.flow() > 0)
                    //StdOut.println("   " + e);
                    if (e.to() > players && e.to() != t && e.from() != s) {
                        //System.out.println(e.from()+"<-Shooter - Killed->:" + (e.to() - players));
                        //resultList.add(e.to() - players);
                        resultMap.put(e.from(), e.to() - players);
                    }
            }
        }

        // print min-cut
        //StdOut.print("Min cut: ");
        for (int v = 0; v < G.V(); v++) {
            //if (maxflow.inCut(v)) StdOut.print(v + " ");
        }

        //print resultmap
        //System.out.println(resultMap);
        //Print values of map


        //StdOut.println("Max flow value = " +  maxflow.value());
        if (maxflow.value() == players) {
            //Print resultMap values in order of keys
            for (int i = 1; i < players+1; i++) {
                System.out.println(resultMap.get(i));
            }
        } else {
            System.out.println("Impossible");
        }

    }
}