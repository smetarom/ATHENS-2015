package fr.athens;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Roman Smetana
 */
public class Node implements Comparable<Node> {
    private String name;
    private Map<Node, Integer> nodes = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public void addNode(Node node, int weight) {
        nodes.put(node, weight);
    }

    public void removeNode(Node node) {
        nodes.remove(node);
    }

    public String getName() {
        return name;
    }

    public Map<Node, Integer> getNodes() {
        return nodes;
    }

    public int getWeightDegree() {
        int degree = 0;
        for (Integer v : nodes.values()) {
            degree += v;
        }
        return degree;
    }

    @Override
    public String toString() {
        String stringNodes = "[";
        for (Map.Entry<Node, Integer> entry : nodes.entrySet()) {
            stringNodes += entry.getKey().getName() + "=" + entry.getValue()+",";
        }
        stringNodes += "]";
        return "Node{" +
                "name='" + name + '\'' +
                ", nodes=" + stringNodes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return getName().equals(node.getName());

    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public int compareTo(Node o) {
        return name.compareTo(o.toString());
    }
}
