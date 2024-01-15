//package projectCPS2232;
//
//public class ZooMap {
//    private int[][] adjacencyMatrix;
//    private int numVertices;
//
//    public ZooMap(int numVertices) {
//        this.numVertices = numVertices;
//        this.adjacencyMatrix = new int[numVertices][numVertices];
//        // Initializing adjacency matrix with default values (in this case, assuming an unweighted graph)
//        for (int i = 0; i < numVertices; i++) {
//            for (int j = 0; j < numVertices; j++) {
//                adjacencyMatrix[i][j] = 0; // Default value indicating no direct connection
//            }
//        }
//    }
//
//    // Method to add an edge between two vertices
//    public void addEdge(int source, int destination, int weight) {
//        adjacencyMatrix[source][destination] = weight;
//        // For undirected graph, also set the reverse edge
//        adjacencyMatrix[destination][source] = weight;
//    }
//
//    // Method to find the shortest path using Dijkstra's algorithm
//    public void shortestPath(int sourceVertex, int destinationVertex) {
//        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(numVertices);
//        dijkstra.dijkstraAlgorithm(adjacencyMatrix, sourceVertex);
//        System.out.println("Shortest path from " + sourceVertex + " to " + destinationVertex + ": ");
//        dijkstra.printSolution(destinationVertex);
//    }
//}
//
//class DijkstraAlgorithm {
//    private boolean visited[];
//    private int distance[];
//    private int numVertices;
//
//    public DijkstraAlgorithm(int numVertices) {
//        this.numVertices = numVertices;
//        visited = new boolean[numVertices];
//        distance = new int[numVertices];
//    }
//
//    public void dijkstraAlgorithm(int[][] adjacencyMatrix, int source) {
//        for (int i = 0; i < numVertices; i++) {
//            distance[i] = Integer.MAX_VALUE;
//            visited[i] = false;
//        }
//
//        distance[source] = 0;
//
//        for (int i = 0; i < numVertices - 1; i++) {
//            int minVertex = findMinVertex();
//            visited[minVertex] = true;
//
//            for (int j = 0; j < numVertices; j++) {
//                if (!visited[j] && adjacencyMatrix[minVertex][j] != 0 && distance[minVertex] != Integer.MAX_VALUE &&
//                        distance[minVertex] + adjacencyMatrix[minVertex][j] < distance[j]) {
//                    distance[j] = distance[minVertex] + adjacencyMatrix[minVertex][j];
//                }
//            }
//        }
//    }
//
//    private int findMinVertex() {
//        int minVertex = -1;
//        for (int i = 0; i < numVertices; i++) {
//            if (!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])) {
//                minVertex = i;
//            }
//        }
//        return minVertex;
//    }
//
//    public void printSolution(int destination) {
//        System.out.print(destination + " <- ");
//        int prev = destination;
//        int prevDistance = distance[destination];
//
//        for (int i = 0; i < numVertices; i++) {
//            if (adjacencyMatrix[i][destination] != 0 && distance[i] == prevDistance - adjacencyMatrix[i][destination]) {
//                System.out.print(i + " <- ");
//                prev = i;
//                prevDistance = distance[i];
//            }
//        }
//
//        if (prev != 0) {
//            printSolution(prev);
//        }
//    }
//}
