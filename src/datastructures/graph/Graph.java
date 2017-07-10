package datastructures.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Graphs are data structures rather like trees. In fact, in a mathematical
 * sense, a tree is a kind of graph. Graphs, often have a shape dictated by a
 * physical or abstract problem. For example, nodes in a graph may represent
 * cities, while edges may represent airline flight routes between the cities.
 * In the graph, circles represent freeway interchanges, and straight lines
 * connecting the circles represent freeway segments. The circles are vertices,
 * and the lines are edges. Two vertices are said to be adjacent to one another
 * if they are connected by a single edge. A path is a sequence of edges. A
 * graph is said to be connected if there is at least one path from every vertex
 * to every other vertex. A Non-directed graph means that the edges don’t have a
 * direction; you can go either way on them.
 * 
 * In a graph each vertex may be connected to an arbitrary number of other
 * vertices. To model this sort of free-form organization, a different approach
 * to representing edges is preferable to that used for trees. Two methods are
 * commonly used for graphs: the adjacency matrix and the adjacency list. An
 * adjacency matrix is a two-dimensional array in which the elements indicate
 * whether an edge is present between two vertices. If a graph has N vertices,
 * the adjacency matrix is an NxN array. adjacency list is an array of lists
 * Each individual list shows what vertices a given vertex is adjacent to.
 * 
 * Within the Graph class, vertices are identified by their index number in
 * vertexList.
 * 
 * @author vshanmughada
 *
 */
public class Graph {
	static Vertex[] vertexList;
	static int[][] adjacencyMatrix;
	static int vertexCount;

	Graph(int vertexSize) {
		vertexList = new Vertex[vertexSize];
		adjacencyMatrix = new int[vertexSize][vertexSize];
		vertexCount = 0;
		initAdjMatrix();
	}

	public static void main(String[] args) {
		char isContinue = 'N';
		do {
			System.out.println(
					"\nEnter the following choice - D(Depth First Search) B(Breadth First Search) M(Minimum Spanning Tree) T(Topological Sorting)");
			char choice = getChar();
			switch (choice) {
			case 'D':
				initializeGraphForDFS();
				performDFS();
				break;
			case 'B':
				initializeGraphForBFS();
				performBFS();
				break;
			case 'M':
				initializeGraphForMST();
				performMST();
				break;
			case 'T':
				initializeGraphForTopoSort();
				performTopoSort();
				break;
			default:
				System.out.println("Invalid Entry !!");
			}
			System.out.println("\nDo you want to continue? Y/N");
			isContinue = getChar();
		} while (isContinue == 'Y');
	}

	static void initializeGraphForDFS() {
		Graph graph = new Graph(9);
		graph.addVertex('A'); // 0 (start for dfs)
		graph.addVertex('B'); // 1
		graph.addVertex('C'); // 2
		graph.addVertex('D'); // 3
		graph.addVertex('E'); // 4
		graph.addVertex('F'); // 5
		graph.addVertex('G'); // 6
		graph.addVertex('H'); // 7
		graph.addVertex('I'); // 8
		graph.addEdge(0, 1); // AB
		graph.addEdge(1, 5); // BF
		graph.addEdge(5, 7); // FH
		graph.addEdge(0, 2); // AC
		graph.addEdge(0, 3); // AD
		graph.addEdge(3, 6); // DG
		graph.addEdge(6, 8); // GI
		graph.addEdge(0, 4); // AE

		resetVisitHistory();
	}

	static void initializeGraphForBFS() {
		Graph graph = new Graph(5);
		graph.addVertex('A'); // 0 (start for bfs)
		graph.addVertex('B'); // 1
		graph.addVertex('C'); // 2
		graph.addVertex('D'); // 3
		graph.addVertex('E'); // 4
		graph.addEdge(0, 1); // AB
		graph.addEdge(1, 2); // BC
		graph.addEdge(0, 3); // AD
		graph.addEdge(3, 4); // DE
		resetVisitHistory();
	}

	static void initializeGraphForMST() {
		Graph graph = new Graph(5);
		graph.addVertex('A'); // 0 (start for mst)
		graph.addVertex('B'); // 1
		graph.addVertex('C'); // 2
		graph.addVertex('D'); // 3
		graph.addVertex('E'); // 4
		graph.addEdge(0, 1); // AB
		graph.addEdge(0, 2); // AC
		graph.addEdge(0, 3); // AD
		graph.addEdge(0, 4); // AE
		graph.addEdge(1, 2); // BC
		graph.addEdge(1, 3); // BD
		graph.addEdge(1, 4); // BE
		graph.addEdge(2, 3); // CD
		graph.addEdge(2, 4); // CE
		graph.addEdge(3, 4); // DE

		resetVisitHistory();
	}

	static void initializeGraphForTopoSort() {
		Graph graph = new Graph(8);
		graph.addVertex('A'); // 0 (start for topo sort)
		graph.addVertex('B'); // 1
		graph.addVertex('C'); // 2
		graph.addVertex('D'); // 3
		graph.addVertex('E'); // 4
		graph.addVertex('F'); // 5
		graph.addVertex('G'); // 6
		graph.addVertex('H'); // 7
		graph.addDirectedEdge(0, 3); // AD
		graph.addDirectedEdge(0, 4); // AE
		graph.addDirectedEdge(1, 4); // BE
		graph.addDirectedEdge(2, 5); // CF
		graph.addDirectedEdge(3, 6); // DG
		graph.addDirectedEdge(4, 6); // EG
		graph.addDirectedEdge(5, 7); // FH
		graph.addDirectedEdge(6, 7); // GH

		resetVisitHistory();
	}

	static void resetVisitHistory() {
		for (int index = 0; index < vertexList.length; index++) {
			vertexList[index].wasVisited = false;
		}
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

	public static int getVertexCount() {
		return vertexCount;
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

	void addEdge(int vertexIndexA, int vertexIndexB) {
		if (vertexIndexA < vertexList.length && vertexIndexB < vertexList.length) {
			adjacencyMatrix[vertexIndexA][vertexIndexB] = 1;
			adjacencyMatrix[vertexIndexB][vertexIndexA] = 1;
		} else {
			System.out.println("Adjacency Matrix index exceeds max size");
		}
	}

	/**
	 * In a directed graph you can proceed only one way along an edge. An edge
	 * in a directed graph has only one entry in the adjacency matrix
	 * 
	 * @param vertexIndexA
	 * @param vertexIndexB
	 */
	void addDirectedEdge(int row, int col) {
		if (row < vertexList.length && col < vertexList.length) {
			adjacencyMatrix[row][col] = 1;
		} else {
			System.out.println("Adjacency Matrix index exceeds max size");
		}
	}

	static void displayVertex(int vertexIndex) {
		System.out.println(vertexList[vertexIndex].label);
	}

	/**
	 * There are two common approaches to searching a graph: depth-first search
	 * (DFS) and breadth-first search (BFS). Both will eventually reach all
	 * connected vertices.
	 * 
	 * DFS algorithm provides a systematic way to start at a specified vertex
	 * and then move along edges to other vertices in such a way that, when it’s
	 * done, you are guaranteed that it has visited every vertex that’s
	 * connected to the starting vertex. The depth-first search is implemented
	 * with a stack and uses it to remember where it should go when it reaches a
	 * dead end.
	 * 
	 * To carry out the depth-first search, you pick a starting point and loop
	 * until the stack is empty. Within the loop, it does four things:
	 * 
	 * 1. It examines the vertex at the top of the stack, using peek().
	 * 
	 * 2. It tries to find an unvisited neighbor of this vertex.
	 * 
	 * 3. If it doesn’t find one, it pops the stack.
	 * 
	 * 4. If it finds such a vertex, it visits that vertex and pushes it onto
	 * the stack.
	 * 
	 */
	static void performDFS() {
		Stack stack = new Stack(getVertexCount());
		stack.push(0);// Pushed A into Stack
		vertexList[0].wasVisited = true;
		System.out.print("Visits : " + vertexList[0].label);
		while (stack.isNotEmpty()) {
			int adjVertex = getAdjacentUnvisitedVertex(stack.peek());
			if (adjVertex == -1) {
				stack.pop();
			} else {
				stack.push(adjVertex);
				vertexList[adjVertex].wasVisited = true;
				System.out.print(vertexList[adjVertex].label);
			}
		}
	}

	static int getAdjacentUnvisitedVertex(int currentVertex) {
		for (int col = 0; col < vertexCount; col++) {
			if (adjacencyMatrix[currentVertex][col] == 1 && !vertexList[col].wasVisited) {
				return col;
			}
		}
		return -1;
	}

	/**
	 * In the breadth-first search, the algorithm likes to stay as close as
	 * possible to the starting point. It visits all the vertices adjacent to
	 * the starting vertex, and only then goes further afield. This kind of
	 * search is implemented using a queue instead of a stack. This is useful if
	 * you’re trying to find the shortest path from the starting vertex to a
	 * given vertex. It uses a queue instead of a stack and features nested
	 * loops instead of a single loop.
	 * 
	 * 
	 */
	static void performBFS() {
		Queue queue = new Queue(getVertexCount());
		queue.insert(0);// Pushed A into Queue
		vertexList[0].wasVisited = true;
		System.out.print("Visits : " + vertexList[0].label);
		int currentVertex = queue.remove();
		while (currentVertex != -1) {
			int adjVertex = getAdjacentUnvisitedVertex(currentVertex);
			if (adjVertex != -1) {
				queue.insert(adjVertex);
				vertexList[adjVertex].wasVisited = true;
				System.out.print(vertexList[adjVertex].label);
			} else {
				currentVertex = queue.remove();
			}
		}
	}

	/**
	 * A minimum spanning tree (MST) is a graph with the minimum number of edges
	 * necessary to connect the vertices. By executing the depth-first search
	 * and recording the edges you’ve traveled to make the search, you
	 * automatically create a minimum spanning tree.
	 * 
	 */
	static void performMST() {
		Stack stack = new Stack(getVertexCount());
		stack.push(0);// Pushed A into Stack
		vertexList[0].wasVisited = true;
		System.out.print("Minimum Spanning Tree : ");
		while (stack.isNotEmpty()) {
			int currentVertex = stack.peek();
			int adjVertex = getAdjacentUnvisitedVertex(currentVertex);
			if (adjVertex == -1) {
				stack.pop();
			} else {
				stack.push(adjVertex);
				vertexList[adjVertex].wasVisited = true;
				// Minimum Spanning Tree
				System.out.print(vertexList[currentVertex].label);
				System.out.print(vertexList[adjVertex].label + "	");
			}
		}
	}

	static void performTopoSort() {
		char[] sortArray = new char[vertexCount];
		while (vertexCount > 0) {
			int currentVertex = noSuccessors();
			if (currentVertex == -1) {
				System.out.println("Current Vertex has cycles. Program Aborted..");
				return;
			} else {
				sortArray[vertexCount - 1] = vertexList[currentVertex].label;
				deleteVertex(currentVertex);
			}
		}
		System.out.println("The Topological Sorted Order is : ");
		for (int index = 0; index < sortArray.length; index++) {
			System.out.print(sortArray[index]);
		}
	}

	static int noSuccessors() {
		boolean hasSuccessor;
		for (int row = 0; row < vertexCount; row++) {
			hasSuccessor = false;
			for (int col = 0; col < vertexCount; col++) {
				if (adjacencyMatrix[row][col] == 1) {
					hasSuccessor = true;
					break;
				}
			}
			if (!hasSuccessor) {
				return row;
			}
		}
		return -1;
	}

	static void deleteVertex(int vertexToDelete) {
		if (vertexToDelete != vertexCount - 1) {
			for (int index = vertexToDelete; index < vertexCount - 1; index++) {
				vertexList[index] = vertexList[index + 1];
			}
			// Move Row one level up
			for (int row = vertexToDelete; row < vertexCount - 1; row++) {
				for (int col = 0; col < vertexCount; col++) {
					adjacencyMatrix[row][col] = adjacencyMatrix[row + 1][col];
				}

			}
			// move Column one level left
			for (int col = vertexToDelete; col < vertexCount - 1; col++) {
				for (int row = 0; row < vertexCount; row++) {
					adjacencyMatrix[row][col] = adjacencyMatrix[row][col + 1];
				}
			}
		}
		vertexCount--;
	}

}

/**
 * A Vertex represents some real-world object, and the object must be described
 * using data items. If a vertex represents a city in an airline route
 * simulation, for example, it may need to store the name of the city, its
 * altitude, its location, and other such information. Thus, it’s usually
 * convenient to represent a vertex by an object of a vertex class.
 * 
 * @author vshanmughada
 *
 */
class Vertex {
	char label;
	boolean wasVisited;

	Vertex(char label) {
		this.label = label;
		this.wasVisited = false;
	}
}

class Stack {
	int[] array = null;
	int stackSize = 0;
	int pointer = -1;

	public Stack(int size) {
		array = new int[size];
		stackSize = size;
	}

	protected void push(int number) {
		if (pointer == stackSize - 1) {
			System.out.println("Stack Overflow. Can't Push.");
		} else {
			array[++pointer] = number;
		}
	}

	protected int pop() {
		if (pointer < 0) {
			System.out.println("\nStack Empty. Can't Pop.");
			return -1;
		}
		return array[pointer--];
	}

	protected int peek() {
		return array[pointer];
	}

	protected boolean isNotEmpty() {
		return pointer > -1;
	}
}

class Queue {

	int[] array = null;
	int queueSize = 0;
	int front, rear;
	int numberOfItems = 0;

	Queue(int size) {
		array = new int[size];
		queueSize = size;
		front = 0;
		rear = -1;
	}

	void insert(int index) {
		if (numberOfItems >= queueSize) {
			System.out.println("Queue Full. Cannot Insert");
			return;
		}
		if ((rear + 1) >= queueSize) {
			rear = 0;
		}
		array[++rear] = index;
		numberOfItems++;
	}

	int remove() {
		if (numberOfItems <= 0) {
			System.out.println("\nQueue Empty. Cannot Remove");
			return -1;
		}
		if ((front + 1) >= queueSize) {
			front = 0;
		}
		numberOfItems--;
		return array[front++];
	}
}
