package datastructures.graph;

/**
 * DFS algorithm provides a systematic way to start at a specified vertex and
 * then move along edges to other vertices in such a way that, when it’s done,
 * you are guaranteed that it has visited every vertex that’s connected to the
 * starting vertex. The depth-first search is implemented with a stack and uses
 * it to remember where it should go when it reaches a dead end.
 * 
 * To carry out the depth-first search, you pick a starting point and loop until
 * the stack is empty. Within the loop, it does four things:
 * 
 * 1. It examines the vertex at the top of the stack, using peek().
 * 
 * 2. It tries to find an unvisited neighbor of this vertex.
 * 
 * 3. If it doesn’t find one, it pops the stack.
 * 
 * 4. If it finds such a vertex, it visits that vertex and pushes it onto the
 * stack.
 * 
 * @author vshanmughada
 *
 */
public class DepthFirstSearch {
	
	public static void main(String[] args) {
		Graph graph = new Graph();
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
		
		graph.performDFS();
	}
}

