package Dijkstra;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@RequiredArgsConstructor
public class Node implements Comparable<Node>{

    private final String name;
    private Integer distance = Integer.MAX_VALUE;

    private List<Node> shortestPath = new LinkedList<>();
    private Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addAdjacentNode(Node node, int weight){
        adjacentNodes.put(node, weight);
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(distance, node.distance);
    }

    @Override
    public String toString()
    {
        String s =  name + " : " + distance + " / ";
        for(Node node:shortestPath)
        {
            s = s + "" + node.getName() + "->";
        }
        return s +  getName() + "\n";
    }
}
