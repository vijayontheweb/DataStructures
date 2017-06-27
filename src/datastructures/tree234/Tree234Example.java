package datastructures.tree234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tree234Example {

	public static void main(String[] args) {
		Tree234 tree234 = new Tree234();
		tree234.insert(70);
		tree234.insert(50);
		tree234.insert(30);
		tree234.insert(40);
		tree234.insert(20);
		tree234.insert(80);
		tree234.insert(25);
		tree234.insert(90);
		tree234.insert(75);
		tree234.insert(10);
		tree234.displayTree();
		char isContinue = 'N';
		do {
			System.out.println("\nEnter the following choice - I(Insert) S(Show) F(Find)");
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

				String choiceStr = br.readLine();
				char choice = choiceStr.charAt(0);
				switch (choice) {
				case 'I':
					System.out.println("Enter the number to insert..");
					br = new BufferedReader(new InputStreamReader(System.in));
					String insertStr = br.readLine();
					int number = Integer.parseInt(insertStr);
					tree234.insert(number);
					System.out.println("Revised list of elements - After Inserting " + insertStr);
					tree234.displayTree();
					break;
				case 'S':
					tree234.displayTree();
					break;
				case 'F':
					System.out.println("Enter the number to find..");
					br = new BufferedReader(new InputStreamReader(System.in));
					String findStr = br.readLine();
					number = Integer.parseInt(findStr);
					tree234.find(number);
					break;
				default:
					System.out.println("Invalid Entry !!");
				}
				System.out.println("\nDo you want to continue? Y/N");
				br = new BufferedReader(new InputStreamReader(System.in));

				choiceStr = br.readLine();
				isContinue = choiceStr.charAt(0);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (isContinue == 'Y');
	}
}

class Tree234 {
	Node root = new Node();

	void displayTree() {
		recursiveDisplayTree(root, 0, 0);
	}

	void recursiveDisplayTree(Node node, int levelNum, int childNum) {
		System.out.println("LEVEL=" + levelNum + " CHILD=" + childNum + " **********************************");
		node.displayNode();
		for (int index = 0; index <= node.getNumItems(); index++) {
			if (node.getChild(index) != null) {
				recursiveDisplayTree(node.getChild(index), levelNum + 1, index);
			} else {
				return;
			}
		}
	}

	/**
	 * Searching for a data item with a specified key is carried out by the
	 * find() routine. It starts at the root and at each node calls that node’s
	 * findItem() routine to see whether the item is there. If so, it returns
	 * the index of the item within the node’s item array. If find() is at a
	 * leaf and can’t find the item, the search has failed, so it returns –1. If
	 * it can’t find the item in the current node, and the current node isn’t a
	 * leaf, find() calls the getNextChild() method, which figures out which of
	 * a node’s children the routine should go to next.
	 * 
	 * @param key
	 * @return
	 */
	int find(int key) {
		Node node = root;
		int itemIndex;
		int levelNum = 0, childNum=0;
		while (true) {
			// Step 1 : Find match within Node. If element found, return
			// index
			if ((itemIndex = node.findItem(key)) != -1) {
				System.out.println("Node found with Key="+key+" at LevelIndex="+levelNum+" ChildIndex="+childNum+" ItemIndex="+itemIndex);
				return itemIndex;
			}
			// Step 2 : If element not found and if element is leaf, return
			// -1
			else if (node.isLeaf()) {
				System.out.println("Reached the leaf node and no match found for key=" + key);
				return -1;
			}
			// Step 3 : If match not found and Node is not leaf, find next
			// logical child Node and check
			else {
				node = getNextChild(node, levelNum++, childNum, key);
			}
		}
	}

	/**
	 * Gets appropriate child of node during search for value
	 * 
	 * @param node
	 * @param key
	 * @return
	 */
	Node getNextChild(Node node, int key) {
		int numItems = node.getNumItems();
		for (int itemIndex = 0; itemIndex < numItems; itemIndex++) {
			if (key < node.getItem(itemIndex).getKey()) {
				return node.getChild(itemIndex);
			}
		}
		return node.getChild(numItems);
	}	
	
	
	/**
	 * Overloaded Method to propagate extra information - levelNum, childNum
	 * Gets appropriate child of node during search for value
	 * 
	 * @param node
	 * @param key
	 * @return
	 */
	Node getNextChild(Node node, int levelNum, int childNum, int key) {
		int numItems = node.getNumItems();
		for (int itemIndex = 0; itemIndex < numItems; itemIndex++) {
			childNum = itemIndex;
			if (key < node.getItem(itemIndex).getKey()) {
				return node.getChild(itemIndex);
			}
		}
		return node.getChild(numItems);
	}

	/**
	 * The insert() method starts with code similar to find(), except that it
	 * splits a full node if it finds one. Also, it assumes it can’t fail; it
	 * keeps looking, going to deeper and deeper levels, until it finds a leaf
	 * node. At this point the method inserts the new data item into the leaf.
	 * (There is always room in the leaf; otherwise, the leaf would have been
	 * split.)
	 * 
	 * @param key
	 */
	void insert(int key) {
		DataItem newItem = new DataItem(key);
		Node node = root;
		while (true) {
			// if node is full, split
			if (node.isFull()) {
				Node currentNode = split(node);
				currentNode = currentNode.getParent();
				node = getNextChild(currentNode, key);
			}
			// if node is leaf, insert the item
			else if (node.isLeaf()) {
				break;
			}
			// If node is not a leaf, get the next logical child
			else {
				node = getNextChild(node, key);
			}
		}
		node.insertItem(newItem);
	}

	/**
	 * The split() method is passed the node that will be split as an argument.
	 * First, the two rightmost data items are removed from the node and stored.
	 * Then the two rightmost children are disconnected; their references are
	 * also stored. A new node, called newRight, is created. It will be placed
	 * to the right of the node being split. If the node being split is the
	 * root, an additional new node is created: a new root. Next, appropriate
	 * connections are made to the parent of the node being split. It maybe a
	 * pre-existing parent, or if the root is being split, it will be the newly
	 * created root node. Assume the three data items in the node being split
	 * are called A, B, and C. Item B is inserted in this parent node. If
	 * necessary, the parent’s existing children are disconnected and
	 * reconnected one position to the right to make room for the new data item
	 * and new connections. The newRight node is connected to this parent.Now
	 * the focus shifts to the newRight node. Data Item C is inserted in it, and
	 * child 2 and child 3, which were previously disconnected from the node
	 * being split, are connected to it. The split is now complete, and the
	 * split() routine returns.
	 * 
	 * @param node
	 */
	Node split(Node currentNode) {
		DataItem[] items = currentNode.getItemArray();
		// First, the two rightmost data items are removed from the node and
		// stored.
		DataItem itemMiddle = items[1];
		DataItem itemRight = items[2];
		currentNode.removeItem(itemMiddle);
		currentNode.removeItem(itemRight);
		// Then the two rightmost children are disconnected. Their
		// references
		// are also stored
		Node thirdChild = currentNode.getChild(2);
		Node fourthChild = currentNode.getChild(3);
		currentNode.disconnectChild(2);
		currentNode.disconnectChild(3);
		// A new node, called newRight, is created. It will be placed to the
		// right of the node being split.
		Node newRightNode = new Node();
		newRightNode.insertItem(itemRight);
		newRightNode.connectChild(0, thirdChild);
		newRightNode.connectChild(1, fourthChild);

		// If the node being split is the root, an additional new node is
		// created: a new root
		if (currentNode == root) {
			Node newRoot = new Node();
			newRoot.insertItem(itemMiddle);

			newRoot.connectChild(0, currentNode);
			newRoot.connectChild(1, newRightNode);
			root = newRoot;
		} else {
			Node parentNode = currentNode.getParent();
			int itemIndex = parentNode.insertItem(itemMiddle);
			// appropriate connections are made to the parent of the node
			// being split. It maybe a pre-existing parent, or if the root
			// is
			// being split, it will be the newly created root node.
			for (int index = parentNode.getNumItems() - 1; index > itemIndex; index--) {
				Node temp = parentNode.disconnectChild(index);
				parentNode.connectChild(index + 1, temp);
			}
			parentNode.connectChild(itemIndex + 1, newRightNode);
		}
		return currentNode;
	}

}

class Node {
	Node[] childArray;
	DataItem[] itemArray;
	int numItems;
	Node parent;

	Node() {
		itemArray = new DataItem[3];
		childArray = new Node[4];
	}

	public void connectChild(int childNum, Node child) {
		childArray[childNum] = child;
		if (child != null)
			child.parent = this;
	}

	public Node disconnectChild(int childNum) {
		Node tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}

	public DataItem[] getItemArray() {
		return itemArray;
	}

	public Node getChild(int childNum) {
		return childArray[childNum];
	}

	public Node getParent() {
		return parent;
	}

	public boolean isLeaf() {
		return (childArray[0] == null) ? true : false;
	}

	public int getNumItems() {
		return numItems;
	}

	public DataItem getItem(int index) {
		return itemArray[index];
	}

	public boolean isFull() {
		return (numItems == 3) ? true : false;
	}

	int findItem(int key) {
		for (int itemIndex = 0; itemIndex < numItems; itemIndex++) {
			if (itemArray[itemIndex] != null && itemArray[itemIndex].getKey() == key) {
				System.out.println("DataItem with Key=" + key + " found at index=" + itemIndex);
				return itemIndex;
			}
		}
		return -1;
	}

	int insertItem(DataItem item) {
		int keyToInsert = item.getKey();
		numItems++;
		for (int itemIndex = 2; itemIndex >= 0; itemIndex--) {
			if (itemArray[itemIndex] == null) {
				continue;
			} else {
				if (itemArray[itemIndex].getKey() > keyToInsert) {
					itemArray[itemIndex + 1] = itemArray[itemIndex];// shift
																	// right
				} else {
					itemArray[itemIndex + 1] = item;
					return itemIndex + 1;
				}
			}
		}
		itemArray[0] = item;
		return 0;
	}

	DataItem removeItem(DataItem item) {
		DataItem temp = itemArray[numItems - 1];
		itemArray[numItems - 1] = null;
		numItems--;
		return temp;
	}

	void displayNode() {
		System.out.print("[");
		for (int itemIndex = 0; itemIndex < numItems; itemIndex++) {
			System.out.print(itemIndex > 0 ? "," : "");
			itemArray[itemIndex].displayDataItem();
		}
		System.out.print("]\n");
	}

}

class DataItem {
	int key;

	public DataItem(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	void displayDataItem() {
		System.out.print(key);
	}

}