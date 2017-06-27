package datastructures.binarytree.compression;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/**
 * @author vijayanand
 *
 * A Huffman tree is a binary tree (but not a search tree) used in a data-compression algorithm called Huffman Coding.In the Huffman code the characters 
 * that appear most frequently are coded with the fewest bits, and those that appear rarely are coded with the most bits. The only rule to adhere is 
 * "No code can be the prefix of any other code."
 *
 * An example is sending data over the Internet, where, especially over a dial-up connection, transmission can take a long time.
 * 
 * Each character in a normal uncompressed text file is represented in the computer by one byte (for the venerable ASCII code) or by two bytes (for the newer 
 * Unicode, which is designed to work for all languages.) In these schemes, every character requires the same number of bits.
 * 
 * How would we transform it back into characters? We can use a kind of binary tree called a Huffman tree. The characters in the message appear in the tree as 
 * leaf nodes. The higher their frequency in the message, the higher up they appear in the tree. The number outside each circle is the frequency. The numbers 
 * outside non-leaf nodes are the sums of the frequencies of their children. 
 * 
 * How do we use this tree to decode the message? For each character you start at the root. If you see a 0 bit, you go left to the next node, and if you see a 
 * 1 bit, you go right.
 */

public class HuffmanCode {

	static final Map<String,Node> frequencyMap = new HashMap<String,Node>();
	static final Map<String,String> huffmanMap = new HashMap<String,String>();	
	
	public static void main(String args[]) {		
		System.out.println("Enter the message to be encoded under Huffman Algorithm");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputText=null;
		try{
			inputText = br.readLine();
		}catch(Exception e){
			
		}
		/*
		 * 1. Make a Node object (as seen in tree.java) for each character used in the message. 
		 *    Each node has two data items: the character and that character’s frequency in the message. 		
		 */
		for(char c:inputText.toCharArray()){
			String charStr = String.valueOf(c);
			if(frequencyMap.containsKey(charStr)){
				incrementFrequency(charStr);
			}else{
				Node node = new Node(1,charStr);
				frequencyMap.put(charStr, node);
			}
		}
		//Simply printing
		for(Node node:frequencyMap.values()){
					System.out.print("\nCharacter:"+node.getCharacter()+" Frequency:"+node.getCharacterFrequency());
		}
		/*
		 * 2. Make a tree object for each of these nodes. The node becomes the root of the tree.
		 * 
		 * 3. Insert these trees in a priority queue. They are ordered by frequency, with the 
		 *    smallest frequency having the highest priority. That is, when you remove a tree, 
		 *    it’s always the one with the least-used character.
		 */   
		PriorityQueue priorityQueue = new PriorityQueue(frequencyMap.size());		
		for(Node node:frequencyMap.values()){			
			Tree tree = new Tree();
			tree.insertNode(node);
			priorityQueue.insertTree(tree);
		}
		/*
		 * 4. Remove two trees from the priority queue, and make them into children of a new node. 
		 *    The new node has a frequency that is the sum of the children’s frequencies; its 
		 *    character field can be left blank.
		 *    
		 * 5. Insert this new three-node tree back into the priority queue.
		 * 
		 * 6. Keep repeating steps 4 and 5. The trees will get larger and larger, and there will be 
		 *    fewer and fewer of them. When there is only one tree left in the queue, it is the 
		 *    Huffman tree and you’re done. 
		 * */
		while(priorityQueue.getItemCount()>1){
			Tree tree1 = priorityQueue.remove();
			Tree tree2 = priorityQueue.remove();
			Tree tree = new Tree();
			Node node = new Node(tree1.getRoot().getCharacterFrequency()+tree2.getRoot().getCharacterFrequency(),null);
			node.setLeftNode(tree1.getRoot());
			node.setRightNode(tree2.getRoot());
			tree.insertNode(node);			
			priorityQueue.insertTree(tree);
		}
		Tree finalTree = priorityQueue.peek();		
		finalTree.show();
		buildHuffmanMapping();
		//Testing whether it really works. Yippee !!
		String encodedStr = encodeToHuffman(inputText);
		System.out.println("Huffman Encoded String: Length->"+encodedStr.length()+"\n"+encodedStr);
		String decodedStr = decodeFromHuffman(encodedStr);
		System.out.println("Huffman Decoded String:"+decodedStr);
	}	
	
	private static void buildHuffmanMapping(){		
		for(Node node:frequencyMap.values()){
			huffmanMap.put(node.getCharacter(), node.getHuffmanCode().toString());
		}
	}
	
	private static void incrementFrequency(String charStr){
		Node node = frequencyMap.get(charStr);
		node.incrementFrequency();
	}
	
	static String encodeToHuffman(String inputText){
		StringBuffer encodedTxt = new StringBuffer();
		for(char c:inputText.toCharArray()){
			String charStr = String.valueOf(c);
			if(huffmanMap.containsKey(charStr)){				
				encodedTxt.append(huffmanMap.get(charStr));				
			}
		}
		return encodedTxt.toString();
	}
	
	static String decodeFromHuffman(String inputText){
		StringBuffer decodedTxt = new StringBuffer();
		StringBuffer huffmancode = new StringBuffer();
		for(char c:inputText.toCharArray()){
			huffmancode.append(c);
			while(huffmanMap.containsValue(huffmancode.toString())){
				for(String key:huffmanMap.keySet()){
					if(huffmancode.toString().equals(huffmanMap.get(key))){
						decodedTxt.append(key);
						//clear huffmancode
						huffmancode = new StringBuffer();
					}
				}								
			}
		}
		return decodedTxt.toString();
	}
	
}



class PriorityQueue {
	
    Tree[] array = null;
    int queueSize;
    int noItems;    

    PriorityQueue(int size){
        array = new Tree[size];
        queueSize = size;
        noItems = 0;
    }
    
    protected int getItemCount(){
    	return noItems;
    }
    

    protected void insertTree(Tree tree){
    	Node item = tree.getRoot();
        if(noItems>=queueSize){
            System.out.println("Q Full. Cant insert");
        }
        if(noItems==0){
            array[noItems++]=tree;
        }else{
            for(int index=noItems-1; index>=0; index--){
                if(item.getCharacterFrequency()>array[index].getRoot().getCharacterFrequency()){
                    array[index+1]= array[index];
                    array[index]=tree;
                }else{
                    array[index+1]=tree;
                    break;
                }
            }
            noItems++;
        }
        System.out.print("\nAfter Insert. Queue -> ");
        for(int i=noItems-1;i>=0;i--){        	
        		System.out.print(array[i].getRoot().getCharacter()+":"+array[i].getRoot().getCharacterFrequency()+" ");
        }        
    }

    protected Tree remove(){//Remove at Minimum item
        if(noItems<=0){
            System.out.println("Q Empty. Cant remove");
            return null;
        }else{
           return array[--noItems];
        }
    }

    protected Tree peek(){
         return array[noItems-1];
    }    
}



class Tree{
	Node root;
	
	protected Node getRoot(){
		return root;
	}
	
	public void insertNode(Node nodeToinsert){
		root = nodeToinsert;
	}	
	
	public void show(){
		System.out.println();
		showTree(root);
				
	}
	
	public void showTree(Node node){
			showLeft(node);
			node.displayNode();
			showRight(node);			
			System.out.print("BACK-");
	}
	
	void showLeft(Node node){
		System.out.print("LEFT-");
		if(node.leftNode!=null){
			node.leftNode.setHuffmanCode(new StringBuffer(node.getHuffmanCode()).append('0'));
			
			showTree(node.leftNode);
		}else{
			System.out.print("BACK-");				
		}
	}
	
	void showRight(Node node){
		System.out.print("RIGHT-");
		if(node.rightNode!=null){			
			node.rightNode.setHuffmanCode(new StringBuffer(node.getHuffmanCode()).append('1'));			
			showTree(node.rightNode);			
		}else{
			System.out.print("BACK-");				
		}
	}	
}


/*
 * Make a Node object for each character used in the message. Each node has two data items: the character and that character’s frequency in the message
 */
class Node{
	int characterFrequency;
	String character;
	StringBuffer huffmanCode;
	Node leftNode;
	Node rightNode;
	
	Node(int characterFrequency, String character){
		this.characterFrequency = characterFrequency;
		this.character = character;
		this.huffmanCode = new StringBuffer();
	}
	
	protected void incrementFrequency(){
		this.characterFrequency++;
	}
	
	protected int getCharacterFrequency() {
		return characterFrequency;
	}

	protected void setCharacterFrequency(int characterFrequency) {
		this.characterFrequency = characterFrequency;
	}

	protected String getCharacter() {
		return character;
	}

	protected void setCharacter(String character) {
		this.character = character;
	}

	protected StringBuffer getHuffmanCode() {
		return huffmanCode;
	}

	protected void setHuffmanCode(StringBuffer huffmanCode) {
		this.huffmanCode = huffmanCode;
	}

	public void displayNode(){
		if(character!=null)
		System.out.println("character:"+character+" characterFrequency:"+characterFrequency+ " huffmanCode:"+huffmanCode.toString());
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