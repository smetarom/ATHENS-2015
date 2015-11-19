package fr.athens;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Roman Smetana
 */
public class Algorithm {
    public static final int MAX_NODES = 10;
    public static final int MIN_NODES = 2;
    public static final int SUBGRAPHS = 20;

    public static List<AlgorithmResult> denseGraph(String filePath) throws IOException {
        Set<String> skipHashtags = new HashSet<>();
        List<AlgorithmResult> results = new ArrayList<>(SUBGRAPHS);
        for (int i = 0; i < SUBGRAPHS; i++) {
            AlgorithmResult result = denseGraph(filePath, skipHashtags);
            skipHashtags.addAll(result.getHashtags());
            results.add(result);
        }
        return results;
    }

    public static AlgorithmResult denseGraph(String filePath, Set<String> skipHashtags) throws IOException {
        Map<String, Node> map = new HashMap<>();
        Set<Node> nodes = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] rawInput = line.split("\\s+");
                if (skipHashtags.contains(rawInput[0]) || skipHashtags.contains(rawInput[0])) {
                    continue;
                }
                Node node1 = map.get(rawInput[0]);
                if (node1 == null) {
                    node1 = new Node(rawInput[0]);
                    map.put(rawInput[0], node1);
                    nodes.add(node1);
                }

                Node node2 = map.get(rawInput[1]);
                if (node2 == null) {
                    node2 = new Node(rawInput[1]);
                    map.put(rawInput[1], node2);
                    nodes.add(node2);
                }

                int weight = Integer.valueOf(rawInput[2]);
                node1.addNode(node2, weight);
                node2.addNode(node1, weight);
            }
        }

        double maxDensity = Double.MIN_VALUE;
        Set<Node> maxSubgraph = new HashSet<>();
        while (!nodes.isEmpty()) {
            Node minNode = findMinDegree(nodes);
            nodes.remove(minNode);
            removeNodeFromGraph(minNode);
            if (nodes.size() <= MAX_NODES && nodes.size() >= MIN_NODES) {
                double density = calcDensity(nodes);
                if (density > maxDensity) {
                    maxDensity = density;
                    maxSubgraph = new HashSet<>(nodes);
                    System.out.println(density);
                }
            }
            System.out.println(minNode.getName() + " " + nodes.size());
        }

        AlgorithmResult result = new AlgorithmResult();
        result.setDensity(maxDensity);
        for (Node node : maxSubgraph) {
            result.addHashtag(node.getName());
        }
        return result;
    }

    private static Node findMinDegree(Set<Node> nodes) {
        Node min = null;
        for (Node node : nodes) {
            if (min == null || node.getWeightDegree() < min.getWeightDegree()) {
                min = node;
                if (min.getWeightDegree() <= 1) {
                    break;
                }
            }
        }
        return min;
    }

    private static void removeNodeFromGraph(Node toRemove) {
        Set<Node> nodesToUpdate = toRemove.getNodes().keySet();
        for (Node node : nodesToUpdate) {
            node.removeNode(toRemove);
        }
    }

    private static double calcDensity(Set<Node> nodes) {
        double edges = 0;
        int numNodes = nodes.size();
        for (Node node : nodes) {
            edges += node.getWeightDegree();
        }
        return edges / numNodes;
    }
}
