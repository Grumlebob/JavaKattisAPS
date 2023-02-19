import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int nodesInGraph = Integer.parseInt(input[0]);
        int numberOfEdges = Integer.parseInt(input[1]);
        int source = Integer.parseInt(input[2]);
        int destination = Integer.parseInt(input[3]);

        var flowNetwork = new FlowNetwork(nodesInGraph);

        for (int i = 0; i < numberOfEdges; i++) {
            String[] input2 = br.readLine().split(" ");
            int from = Integer.parseInt(input2[0]);
            int to = Integer.parseInt(input2[1]);
            int capacity = Integer.parseInt(input2[2]);
            flowNetwork.addEdge(new FlowEdge(from, to, capacity));
        }

        FordFulkerson ff = new FordFulkerson(flowNetwork, source, destination);

        //Get mincut
        var resultList = new ArrayList<Integer>();
        for (int v = 0; v < nodesInGraph; v++) {
            if (ff.inCut(v))
                resultList.add(v);
        }

        //print resultlist
        System.out.println(resultList.size());
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(resultList.get(i));
        }

    }
}