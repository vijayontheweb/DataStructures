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
		System.out.print("Visits : " + vertexList[0].label);				
		while (stack.isNotEmpty()){
			int adjVertex = getAdjacentUnvisitedVertex(stack.peek());
			if(adjVertex == -1){
				stack.pop();
			}
			else {
				stack.push(adjVertex);
				vertexList[adjVertex].wasVisited = true;
				System.out.print(vertexList[adjVertex].label);				
			}			 			
		}
	}
	
	void performBFS() {
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

	int getAdjacentUnvisitedVertex(int currentVertex) {
		for (int col = 0; col < vertexCount; col++) {
			if (adjacencyMatrix[currentVertex][col] == 1 && !vertexList[col].wasVisited) {
				return col;
			}
		}
		return -1;
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

	private int frontPeek() {
		return array[front];
	}

	private int rearPeek() {
		return array[rear];
	}
}
