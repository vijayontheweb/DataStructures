package datastructures.graph;

public class Graph {
	static final int VERTEX_MAX_SIZE = 10;
	Vertex[] vertexList;
	int[][] adjacencyMatrix;
	int vertexCount;

	Graph() {
		vertexList = new Vertex[VERTEX_MAX_SIZE];
		adjacencyMatrix = new int[VERTEX_MAX_SIZE][VERTEX_MAX_SIZE];
		vertexCount = 0;
		initAdjMatrix();
	}

	public int getVertexCount() {
		return vertexCount;
	}

	void initAdjMatrix() {
		for (int row = 0; row < VERTEX_MAX_SIZE; row++) {
			for (int col = 0; col < VERTEX_MAX_SIZE; col++) {
				adjacencyMatrix[row][col] = 0;
			}
		}
	}

	void addVertex(char label) {
		Vertex vertex = new Vertex(label);
		vertexList[vertexCount++] = vertex;
	}

	void addEdge(int vertexIndexA, int vertexIndexB) {
		if (vertexIndexA < VERTEX_MAX_SIZE && vertexIndexB < VERTEX_MAX_SIZE) {
			adjacencyMatrix[vertexIndexA][vertexIndexB] = 1;
			adjacencyMatrix[vertexIndexB][vertexIndexA] = 1;
		} else {
			System.out.println("Adjacency Matrix index exceeds max size");
		}
	}

	void displayVertex(int vertexIndex) {
		System.out.println(vertexList[vertexIndex].label);
	}

	void performDFS() {
		Stack stack = new Stack(getVertexCount());
		stack.push(0);// Pushed A into Stack
		vertexList[0].wasVisited = true;
		System.out.print(vertexList[0].label);
		getAdjacentUnvisitedNode(stack);
	}

	void getAdjacentUnvisitedNode(Stack stack) {
		int currentIndex = stack.peek();
		for (int col = 0; col < vertexCount; col++) {
			if (adjacencyMatrix[currentIndex][col] == 1 && !vertexList[col].wasVisited) {
				stack.push(col);
				vertexList[col].wasVisited = true;
				System.out.print(vertexList[col].label);
				getAdjacentUnvisitedNode(stack);
			}
		}
		if (stack.pop() != -1) {
			return;
		}
	}

}

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
			System.out.println("Stack Empty. Can't Pop.");
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
