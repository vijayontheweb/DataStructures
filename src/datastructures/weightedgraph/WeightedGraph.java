/**
 * 
 */
package datastructures.weightedgraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A weighted graph is a graph in which a number (the weight) is assigned to
 * each edge. In applications, the weight may be a measure of the length of a
 * route, the capacity of a line, the energy required to move between locations
 * along a route, etc. Such graphs arise in many contexts, for example in
 * shortest path problems such as the traveling salesman problem. Following
 * solutions are implemented
 * 
 * 1) Minimum Spanning Tree with Weighted Graph
 * 
 * 2) Dijkstra's Algorithm a.k.a Shortest Path Problem
 * 
 * 
 * @author vshanmughada
 *
 */
public class WeightedGraph {

	static Vertex[] vertexList;
	static int[][] adjacencyMatrix;
	static int vertexCount;
	static int edgeCount;
	protected static final int INFINITY = 999999;

	public WeightedGraph(int vertexSize) {
		vertexList = new Vertex[vertexSize];
		adjacencyMatrix = new int[vertexSize][vertexSize];
		vertexCount = 0;
		edgeCount = 0;
		initAdjMatrix();
	}

	void initAdjMatrix() {
		for (int row = 0; row < vertexList.length; row++) {
			for (int col = 0; col < vertexList.length; col++) {
				adjacencyMatrix[row][col] = INFINITY;
			}
		}
	}

	public static void main(String[] args) {
		char isContinue = 'N';
		do {
			System.out.println(
					"\nEnter the following choice - M(Minimum Spanning Tree with Weighted Graph)	D(Dijkstra a.k.a Shortest Path Problem)");
			char choice = getChar();
			switch (choice) {
			case 'M':
				WeightedGraph graph = new WeightedGraph(6);
				graph.initializeGraphForMSTW();
				graph.performMSTW();
				break;
			case 'D':
				graph = new WeightedGraph(5);
				graph.initializeGraphForDijkstra();
				graph.performDijkstra();
				break;
			default:
				System.out.println("Invalid Entry !!");
			}
			System.out.println("\nDo you want to continue? Y/N");
			isContinue = getChar();
		} while (isContinue == 'Y');

	}

	/**
	 * 
	 * The performDijkstra() method carries out the shortest-path algorithm. It
	 * uses the DistanceAndParent class and the Vertex class. The starting
	 * vertex is always at index 0 of the vertexList[] array. The first task in
	 * performDijkstra() is to put this vertex into the processedVertices.
	 * Second, performDijkstra() copies the distances from the appropriate row
	 * of the adjacency matrix to shortestPathArray[]. This is always row 0,
	 * because for simplicity we assume 0 is the index of the starting vertex.
	 * Initially, the parent field of all the shortestPathArray[] entries is A,
	 * the starting vertex.
	 * 
	 * We now enter the main while loop of the algorithm. This loop terminates
	 * after all the vertices have been placed in the processedVertices. There
	 * are basically three actions in this loop:
	 * 
	 * 1. Choose the shortestPathArray[] entry with the minimum distance.
	 * 
	 * 2. Put the corresponding vertex (the column head for this entry) in the
	 * processedVertices. This becomes the “current vertex,” currentVert.
	 * 
	 * 3. Update all the shortestPathArray[] entries to reflect distances from
	 * currentVert.
	 * 
	 */
	void performDijkstra() {
		// The key data structure in the shortest-path (Dijkstra) algorithm is
		// an "array" that keeps track of the minimum distances from the
		// starting vertex to the other vertices (destination vertices). During
		// the execution of the algorithm, these distances are changed, until at
		// the end they hold the actual shortest distances from the start.
		DistanceAndParent[] shortestPathArray = new DistanceAndParent[vertexCount];

		int currentVertex = 0;
		List<Integer> processedVertices = new ArrayList<Integer>();
		processedVertices.add(new Integer(currentVertex));

		for (int destIndex = 0; destIndex < vertexCount; destIndex++) {
			int weight = adjacencyMatrix[currentVertex][destIndex];
			shortestPathArray[destIndex] = new DistanceAndParent(weight, currentVertex);
		}

		while (processedVertices.size() < vertexCount) {
			int minVertex = getMinimumPathVertex(shortestPathArray, processedVertices);
			int minDistance = shortestPathArray[minVertex].getDistance();
			System.out.println(
					"\nMinium Vertex -> " + vertexList[minVertex].label + " Minimum Distance -> " + minDistance);
			if (minDistance == INFINITY) {
				break;
			} else {
				processedVertices.add(new Integer(minVertex));
				currentVertex = minVertex;
				adjustShortestPathArray(shortestPathArray, processedVertices, currentVertex);
				printPath(shortestPathArray);
			}
		}
	}

	void printPath(DistanceAndParent[] shortestPathArray) {
		for (int index = 0; index < shortestPathArray.length; index++) {
			DistanceAndParent dp = shortestPathArray[index];
			System.out.print(vertexList[index].label + " = " + dp.getDistance() + "("
					+ vertexList[dp.getParentIndex()].label + ")	");
		}
	}

	/**
	 * The adjustShortestPathArray() method is used to update the
	 * shortestPathArray[] entries to reflect new information obtained from the
	 * vertex just inserted in the tree. When this routine is called,
	 * currentVert has just been placed in the tree, and startToCurrentDistance
	 * is the current weight/distance in shortestPathArray[] for this vertex.
	 * The adjustShortestPathArray() method now examines each vertex entry in
	 * shortestPathArray[], using the loop counter column to point to each
	 * vertex in turn. For each shortestPathArray[] entry, provided the vertex
	 * is not in the tree, it does three things:
	 * 
	 * 1. It adds the distance to the current vertex (already calculated and now
	 * in startToCurrentDistance) to the edge distance from currentVert to the
	 * column vertex. We call the result startToFringeDistance.
	 * 
	 * 2. It compares startToFringeDistance with the current entry in
	 * shortestPathArray[].
	 * 
	 * 3. If startToFringeDistance is less, it replaces the entry in
	 * shortestPathArray[].
	 * 
	 * This is the heart of Dijkstra’s algorithm. It keeps shortestPathArray[]
	 * updated with the shortest distances to all the vertices that are
	 * currently known.
	 * 
	 * @param shortestPathArray
	 */
	void adjustShortestPathArray(DistanceAndParent[] shortestPathArray, List<Integer> processedVertices,
			int currentVertex) {
		DistanceAndParent dp = shortestPathArray[currentVertex];
		int startToCurrentDistance = dp.getDistance();
		for (int destIndex = 0; destIndex < vertexCount; destIndex++) {
			int currentToFringeDistance = adjacencyMatrix[currentVertex][destIndex];
			if (currentToFringeDistance < INFINITY && !processedVertices.contains(new Integer(destIndex))) {
				int startToFringeDistance = startToCurrentDistance + currentToFringeDistance;
				DistanceAndParent destDP = shortestPathArray[destIndex];
				if (startToFringeDistance < destDP.getDistance()) {
					destDP.setDistance(startToFringeDistance);
					destDP.setParentIndex(currentVertex);
				}
			}
		}
	}

	/**
	 * This method finds the shortestPathArray[] entry with the minimum
	 * distance. it steps across the shortestPathArray[] entries and returns
	 * with the column number (the array index) of the entry with the minimum
	 * distance.
	 * 
	 * @param shortestPathArray
	 * @param processedVertices
	 * @return
	 */
	int getMinimumPathVertex(DistanceAndParent[] shortestPathArray, List<Integer> processedVertices) {
		int minimumPath = INFINITY;
		int minVertex = 0;
		for (int arrayIndex = 0; arrayIndex < shortestPathArray.length; arrayIndex++) {
			DistanceAndParent dp = shortestPathArray[arrayIndex];
			if (dp.getDistance() < minimumPath && !processedVertices.contains(new Integer(arrayIndex))) {
				minimumPath = dp.getDistance();
				minVertex = arrayIndex;
			}
		}
		return minVertex;
	}

	/**
	 * 
	 * The algorithm is carried out in the while loop, which terminates when all
	 * vertices are in the tree. Within this loop the following activities take
	 * place:
	 * 
	 * 1. The current vertex is placed in the tree.
	 * 
	 * 2. The edges adjacent to this vertex are placed in the priority queue (if
	 * appropriate). i.e. the edges adjacent to this vertex are considered for
	 * insertion in the priority queue. The edges are examined by scanning
	 * across the row whose number is currentVert in the adjacency matrix. An
	 * edge is placed in the queue unless one of these conditions is true:
	 * 
	 * ....A)The source and destination vertices are the same.
	 * 
	 * ....B)The destination vertex is in the tree.
	 * 
	 * ....C)here is no edge to this destination.
	 * 
	 * 3. The edge with the minimum weight is removed from the priority queue.
	 * The destination vertex of this edge becomes the current vertex.
	 * 
	 */
	void performMSTW() {
		List<Edge> minSpanTreeList = new ArrayList<Edge>();
		PriorityQueue pQueue = new PriorityQueue(edgeCount, this);
		int currentVertex = 0;
		List<Integer> processedVertices = new ArrayList<Integer>();
		processedVertices.add(new Integer(currentVertex));
		printVertices(processedVertices);
		while (processedVertices.size() < vertexCount) {
			for (int endVertex = 0; endVertex < vertexCount; endVertex++) {
				if (currentVertex == endVertex) {
					continue;
				} else if (processedVertices.contains(new Integer(endVertex))) {
					continue;
				} else if (adjacencyMatrix[currentVertex][endVertex] == INFINITY) {
					continue;
				} else {
					pQueue.insert(new Edge(currentVertex, endVertex, adjacencyMatrix[currentVertex][endVertex]));
				}
			}
			Edge edge = pQueue.remove();
			System.out.print("\nEdge " + printEdge(edge) + " removed from Queue..");
			minSpanTreeList.add(edge);
			printMinSpanTree(minSpanTreeList);
			currentVertex = edge.getEndVertex();
			processedVertices.add(new Integer(currentVertex));
			printVertices(processedVertices);
		}
		System.out.println("\nAll Vertices Processed !!");
	}

	static char getChar() {
		String str = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			str = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.charAt(0);
	}

	/**
	 * To add a vertex to a graph, you make a new vertex object with new and
	 * insert it into your vertex array, vertexList.
	 * 
	 * @param label
	 */
	void addVertex(char label) {
		Vertex vertex = new Vertex(label);
		vertexList[vertexCount++] = vertex;
	}

	void addWeightedEdge(int vertexIndexA, int vertexIndexB, int weight) {
		if (vertexIndexA < vertexList.length && vertexIndexB < vertexList.length) {
			adjacencyMatrix[vertexIndexA][vertexIndexB] = weight;
			adjacencyMatrix[vertexIndexB][vertexIndexA] = weight;
			edgeCount += 2;
		} else {
			System.out.println("Adjacency Matrix index exceeds max size!!");
		}
	}

	void addDirectedWeightedEdge(int vertexIndexA, int vertexIndexB, int weight) {
		if (vertexIndexA < vertexList.length && vertexIndexB < vertexList.length) {
			adjacencyMatrix[vertexIndexA][vertexIndexB] = weight;
			edgeCount += 1;
		} else {
			System.out.println("Adjacency Matrix index exceeds max size!!");
		}
	}

	void printMinSpanTree(List<Edge> minSpanTreeList) {
		System.out.print("Weighted Minimum Spanning Tree list -> ");
		for (Edge edge : minSpanTreeList) {
			char beginLabel = vertexList[edge.getBeginVertex()].label;
			char endLabel = vertexList[edge.getEndVertex()].label;
			int weight = adjacencyMatrix[edge.getBeginVertex()][edge.getEndVertex()];
			System.out.print(beginLabel + "" + endLabel + "" + weight + ",");
		}
	}

	String printEdge(Edge edge) {
		char beginLabel = vertexList[edge.getBeginVertex()].label;
		char endLabel = vertexList[edge.getEndVertex()].label;
		int weight = adjacencyMatrix[edge.getBeginVertex()][edge.getEndVertex()];
		return (beginLabel + "" + endLabel + "" + weight);
	}

	void printVertices(List<Integer> processedVertices) {
		System.out.print("\nProcessed Vertices -> ");
		for (int index : processedVertices) {
			System.out.print(vertexList[index].label + ",");
		}
	}

	void initializeGraphForMSTW() {
		addVertex('A'); // 0 (start for mst)
		addVertex('B'); // 1
		addVertex('C'); // 2
		addVertex('D'); // 3
		addVertex('E'); // 4
		addVertex('F'); // 5

		addWeightedEdge(0, 1, 6); // AB 6
		addWeightedEdge(0, 3, 4); // AD 4
		addWeightedEdge(1, 2, 10); // BC 10
		addWeightedEdge(1, 3, 7); // BD 7
		addWeightedEdge(1, 4, 7); // BE 7
		addWeightedEdge(2, 3, 8); // CD 8
		addWeightedEdge(2, 4, 5); // CE 5
		addWeightedEdge(2, 5, 6); // CF 6
		addWeightedEdge(3, 4, 12); // DE 12
		addWeightedEdge(4, 5, 7); // EF 7
	}

	void initializeGraphForDijkstra() {
		addVertex('A'); // 0 (start)
		addVertex('B'); // 1
		addVertex('C'); // 2
		addVertex('D'); // 3
		addVertex('E'); // 4
		addDirectedWeightedEdge(0, 1, 50); // AB 50
		addDirectedWeightedEdge(0, 3, 80); // AD 80
		addDirectedWeightedEdge(1, 2, 60); // BC 60
		addDirectedWeightedEdge(1, 3, 90); // BD 90
		addDirectedWeightedEdge(2, 4, 40); // CE 40
		addDirectedWeightedEdge(3, 2, 20); // DC 20
		addDirectedWeightedEdge(3, 4, 70); // DE 70
		addDirectedWeightedEdge(4, 1, 50); // EB 50
	}

}

class Vertex {
	char label;
	boolean isFoundInMinSpanningTree;

	Vertex(char label) {
		this.label = label;
		this.isFoundInMinSpanningTree = false;
	}
}

class Edge {
	int beginVertex;
	int endVertex;
	int weight;

	Edge(int beginVertex, int endVertex, int weight) {
		this.beginVertex = beginVertex;
		this.endVertex = endVertex;
		this.weight = weight;
	}

	public int getBeginVertex() {
		return beginVertex;
	}

	public void setBeginVertex(int beginVertex) {
		this.beginVertex = beginVertex;
	}

	public int getEndVertex() {
		return endVertex;
	}

	public void setEndVertex(int endVertex) {
		this.endVertex = endVertex;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}

class PriorityQueue {
	WeightedGraph weightedGraph = null;
	Edge[] edgeArray = null;
	int queueSize;
	int noItems;

	PriorityQueue(int size, WeightedGraph wGraph) {
		weightedGraph = wGraph;
		edgeArray = new Edge[size];
		queueSize = size;
		noItems = 0;
	}

	protected void insert(Edge edge) {
		if (noItems >= queueSize) {
			System.out.println("Q Full. Cant insert");
		}
		if (noItems == 0) {
			edgeArray[noItems++] = edge;
		} else {
			for (int index = noItems - 1; index >= 0; index--) {
				if (edge.getWeight() > edgeArray[index].getWeight()) {
					edgeArray[index + 1] = edgeArray[index];
					edgeArray[index] = edge;
				} else {
					edgeArray[index + 1] = edge;
					break;
				}
			}
			noItems++;
		}
		System.out.print("\nEdge " + weightedGraph.printEdge(edge) + " inserted into Priority Queue -> ");
		for (int i = noItems - 1; i >= 0; i--) {
			System.out.print(weightedGraph.printEdge(edgeArray[i]) + ",");
		}

	}

	protected Edge remove() {// Remove at Minimum item
		if (noItems <= 0) {
			System.out.println("Q Empty. Cant remove");
			return null;
		} else {
			return edgeArray[--noItems];
		}
	}
}

/**
 * This class holds the minimum distance from the starting vertex to each
 * destination vertex, and also the path taken. However, the entire path is not
 * explicitly stored. Only the parent of the destination vertex is stored. The
 * parent is the vertex reached just before the destination.
 * 
 * @author vshanmughada
 *
 */
class DistanceAndParent {
	int distance;
	int parentIndex;

	public DistanceAndParent(int distance, int parentIndex) {
		this.distance = distance;
		this.parentIndex = parentIndex;
	}

	public int getDistance() {
		return distance;
	}

	public int getParentIndex() {
		return parentIndex;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}
}