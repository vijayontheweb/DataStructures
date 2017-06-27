package datastructures.binarytree;


public class BinaryTree{
	public static void main(String[] args){
		Tree tree = new Tree();
		tree.insert(50, "emil");
		tree.insert(80, "ajith");
		tree.insert(10, "vijay");
		tree.insert(90, "anand");
		tree.insert(60, "kismath");
		tree.insert(70, "raja");
		tree.insert(20, "rajiv");
		System.out.println("-------------------------------------------");
		tree.show();
		System.out.println("-------------------------------------------");
		tree.find(70);
		System.out.println("-------------------------------------------");
		tree.find(170);
		System.out.println("-------------------------------------------");
		tree.findMinimum();
		System.out.println("-------------------------------------------");
		tree.findMaximum();
		System.out.println("-------------------------------------------");
		//tree.delete(90);	NO CHILDREN
		//tree.delete(60);  1 CHILD
		tree.delete(50);  //2 CHILREN NOT WORKING
		tree.show();
	}
	
}


class Tree{
	Node root;	
	public void insert(int key, String value){
		Node nodeToinsert = new Node(key,value);
		if(root == null){
			root = nodeToinsert;			
		}else{			
			Node parent = root;
			Node current = root;
			while(current!=null){
				if(key == current.getKey()){
					System.out.println("Node already found with same key = "+key+" and value = "+current.getValue());
					//push it right
					
				}else if(key < current.getKey()){
					parent = current;
					current = current.leftNode;
					if(current==null){
						parent.leftNode = nodeToinsert;
					}
				}else{
					parent = current;
					current = current.rightNode;
					if(current==null){
						parent.rightNode = nodeToinsert;
					}
				}
			}			
		}		
	}	
	public Node find(int key){
		Node current = root;
		while(current!=null){
			if(key == current.getKey()){
				System.out.println("Node found with key = "+key+" and value = "+current.getValue());
				return current;
			}else if(key < current.getKey()){
				current = current.leftNode;
			}else{
				current = current.rightNode;
			}
		}
		System.out.println("Node not found with key = "+key);
		return null;
	}
	
	public Node findMinimum(){
		Node current = root;
		while(current!=null){
			if(current.leftNode!=null){
				current = current.leftNode;
			}else{
				System.out.println("Node found with Minimum key = "+current.getKey()+" and value = "+current.getValue());
				break;
			}
		}
		return current;
	}
	
	public Node findMaximum(){
		Node current = root;
		while(current!=null){
			if(current.rightNode!=null){
				current = current.rightNode;
			}else{
				System.out.println("Node found with Minimum key = "+current.getKey()+" and value = "+current.getValue());
				break;
			}
		}
		return current;
	}
	
	public void show(){
		showTree(root);
		System.out.println();		
	}
	
	public void showTree(Node node){
			//Left-Display-Right=InOrder(Infix)
			//Display-Left-Right=PreOrder(Prefix)
			//Left-Right-Display=PostOrder(Postfix)			
			showLeft(node);				
			node.displayNode();
			showRight(node);			
			System.out.print("BACK-");
	}
	
	void showLeft(Node node){
		System.out.print("LEFT-");
		if(node.leftNode!=null){			
			showTree(node.leftNode);
		}else{
			System.out.print("BACK-");				
		}
	}
	
	void showRight(Node node){
		System.out.print("RIGHT-");
		if(node.rightNode!=null){			
			showTree(node.rightNode);			
		}else{
			System.out.print("BACK-");				
		}
	}
	
	public void delete(int key){
		//Most complicated. 3 scenarios
		//Node to be deleted has no children
		//Node to be deleted has 1 children
		//Node to be deleted has 2 children
		boolean found = false;
		Node current = root;
		Node parent = current;
		while(current!=null){
			if(key == current.getKey()){
				System.out.println("Node to be deleted found with key = "+key+" and value = "+current.getValue());
				found=true;
				break;
				
			}else if(key < current.getKey()){
				parent = current;
				current = current.leftNode;
			}else{
				parent = current;
				current = current.rightNode;
			}
		}
		if(found){
			
			//check if how many children does current have?
			if(current.hasLeftChild() && current.hasRightChild()){//2 Children
				Node successorToReplaceCurrent = getInorderSuccessor(current);
				if(current == root){
					root = successorToReplaceCurrent;					
				}else if(parent.getLeftNode()==current){
					parent.setLeftNode(successorToReplaceCurrent);
				}else if(parent.getRightNode()==current){
					parent.setRightNode(successorToReplaceCurrent);
				}
				successorToReplaceCurrent.setLeftNode(current.getLeftNode());
			}else if(current.hasLeftChild() || current.hasRightChild()){//1 Child
				if(current == root){
					root = current.hasLeftChild()?current.getLeftNode():current.getRightNode();					
				}else if(parent.getLeftNode()==current){
					parent.setLeftNode(current.hasLeftChild()?current.getLeftNode():current.getRightNode());
				}else if(parent.getRightNode()==current){
					parent.setRightNode(current.hasLeftChild()?current.getLeftNode():current.getRightNode());
				}
			}else{//No children
				if(current == root){
					root = null;					
				}else if(parent.getLeftNode()==current){//If current is parent's left child, null it
					parent.setLeftNode(null);
				}else if(parent.getRightNode()==current){//If current is parent's right child, null it
					parent.setRightNode(null);
				}
			}
		}else{
			System.out.println("Cannot find the Node to be deleted with Key = "+key);
		}		
	}
	
	public Node getInorderSuccessor(Node delNode){		 
		Node successor = delNode;
		Node successorParent = successor;
		Node current = delNode.rightNode;		
		while(current!=null){
			successorParent = successor;
			successor = current;
			current = current.leftNode;
		}
		if(successor != delNode.rightNode){
			System.out.println("Inorder Successor Node found with Minimum key = "+successor.getKey()+" and value = "+successor.getValue());
			successorParent.setLeftNode(successor.getRightNode());
			successor.rightNode = delNode.rightNode;
		}
		return successor;
	}
}

class Node{
	int key;
	String value;
	Node leftNode;
	Node rightNode;
	
	Node(int key, String value){
		this.key = key;
		this.value = value;
	}
	
	public void displayNode(){
		System.out.println("Key="+key+" value="+value);
	}
	protected int getKey() {
		return key;
	}

	protected void setKey(int key) {
		this.key = key;
	}

	protected String getValue() {
		return value;
	}

	protected void setValue(String value) {
		this.value = value;
	}

	protected Node getLeftNode() {
		return leftNode;
	}

	protected void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}

	protected Node getRightNode() {
		return rightNode;
	}

	protected void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}
	
	protected boolean hasLeftChild(){
		return (this.leftNode!=null);
	}
	
	protected boolean hasRightChild(){
		return (this.rightNode!=null);
	}
}