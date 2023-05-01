package Dijkstra;

import java.util.*;
import java.util.stream.Stream;

public class DijkstraAlgorithm {

    public static void main(String[] args)
    {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addAdjacentNode(nodeB, 2);
        nodeA.addAdjacentNode(nodeC, 4);

        nodeB.addAdjacentNode(nodeC, 3);
        nodeB.addAdjacentNode(nodeD, 1);
        nodeB.addAdjacentNode(nodeE, 5);

        nodeC.addAdjacentNode(nodeD,2);

        nodeD.addAdjacentNode(nodeE, 1);
        nodeD.addAdjacentNode(nodeF, 4);

        nodeE.addAdjacentNode(nodeF, 2);

        searchShortestPathFrom(nodeA);
        List.of(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF).forEach(System.out::println);

    }

    public static void searchShortestPathFrom(Node beginingNode) {
        beginingNode.setDistance(0);
        Set<Node> visitedNodes = new HashSet<>();
        Queue<Node> unvisitedNodes = new PriorityQueue<>(Collections.singleton(beginingNode));
        while(!unvisitedNodes.isEmpty())
        {
            Node startingNode = unvisitedNodes.poll();
            startingNode.getAdjacentNodes().entrySet().stream()
                    .filter(entry -> !visitedNodes.contains(entry.getKey()))
                    .forEach(entry -> {
                        calculateUpdatedPath(entry.getKey(), entry.getValue(), startingNode);
                        unvisitedNodes.add(entry.getKey());
                    });
            visitedNodes.add(startingNode);
        }
    }

    private static void calculateUpdatedPath(Node visitedNode, Integer weight, Node startingNode) {
        int newDistance = startingNode.getDistance() + weight;
        if(newDistance < visitedNode.getDistance())
        {
            visitedNode.setDistance(newDistance);
            visitedNode.setShortestPath(Stream.concat(startingNode.getShortestPath().stream(), Stream.of(startingNode)).toList());
        }
    }
}
