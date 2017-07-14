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
 * @author vshanmughada
 *
 */
public class WeightedGraph {

	static Vertex[] vertexList;
	static int[][] adjacencyMatrix;
	static int vertexCount;
	static int edgeCount;

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
				adjacencyMatrix[row][col] = 0;
			}
		}
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char isContinue = 'N';
		do {
			System.out.println("\nEnter the following choice - M(Minimum Spanning Tree with Weighted Graph)");
			char choice = getChar();
			switch (choice) {
			case 'M':
				WeightedGraph graph = new WeightedGraph(6);
				graph.initializeGraphForMSTW();
				graph.performMSTW();
				break;
			default:
				System.out.println("Invalid Entry !!");
			}
			System.out.println("\nDo you want to continue? Y/N");
			isContinue = getChar();
		} while (isContinue == 'Y');

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
				} else if (adjacencyMatrix[currentVertex][endVertex] == 0) {
					continue;
				} else {
					pQueue.insert(new Edge(currentVertex, endVertex, adjacencyMatrix[currentVertex][endVertex]));
				}
			}
			Edge edge = pQueue.remove();			
			System.out.print("\nEdge "+printEdge(edge)+" removed from Queue..");
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
		System.out.print("\nEdge "+weightedGraph.printEdge(edge)+" inserted into Priority Queue -> ");
		for (int i = noItems - 1; i >= 0; i--) {			
			System.out.print(weightedGraph.printEdge(edgeArray[i])+",");
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
