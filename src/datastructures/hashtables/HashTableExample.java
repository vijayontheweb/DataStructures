package datastructures.hashtables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * In open addressing, when a data item can’t be placed at the index calculated
 * by the hash function, another location in the array is sought. Various
 * methods are linear probing, quadratic probing, and double hashing. In linear
 * probing we search sequentially for vacant cells. Quadratic probing is an
 * attempt to keep clusters from forming. The idea is to probe more widely
 * separated cells, instead of those adjacent to the primary hash site. Double
 * hashing requires that the size of the hash table is a prime number.
 * 
 * @author vshanmughada
 *
 */
public class HashTableExample {
	static HashElement[] hashTable;
	static int arraySize = 0;
	static int numberOfItems = 0;
	static List<Integer> randomNumberList = new ArrayList<Integer>();

	public static void main(String[] args) {
		System.out.println("Enter the size of the array");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String arraySizeStr = br.readLine();
			arraySize = Integer.parseInt(arraySizeStr);
			hashTable = new HashElement[arraySize];

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter the initial number of items. Random numbers will be generated");
		br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String numberOfItemsStr = br.readLine();
			numberOfItems = Integer.parseInt(numberOfItemsStr);
			Random random = new Random();
			for (int itemCount = 0; itemCount < numberOfItems; itemCount++) {
				int randomNumber = random.nextInt(1000);
				while (randomNumberList.contains(new Integer(randomNumber))) {
					randomNumber = random.nextInt(1000);
				}
				randomNumberList.add(new Integer(randomNumber));
				insert(randomNumber);
			}
			System.out.println("Initial list of elements");
			show();
			char isContinue = 'N';
			do {
				System.out.println("\nEnter the following choice - I(Insert) D(Delete) S(Show) F(Find)");
				br = new BufferedReader(new InputStreamReader(System.in));
				try {
					String choiceStr = br.readLine();
					char choice = choiceStr.charAt(0);
					switch (choice) {
					case 'I':

						System.out.println("Enter the number to insert..");
						br = new BufferedReader(new InputStreamReader(System.in));
						try {
							String insertStr = br.readLine();
							int number = Integer.parseInt(insertStr);
							insert(number);
							System.out.println("Revised list of elements - After Inserting " + insertStr);
							show();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;

					case 'D':

						System.out.println("Enter the number to delete..");
						br = new BufferedReader(new InputStreamReader(System.in));
						try {
							String deleteStr = br.readLine();
							int number = Integer.parseInt(deleteStr);
							HashElement deletedElement = delete(number);
							if (deletedElement != null) {
								System.out.println(
										"Revised list of elements - After Deleting " + deletedElement.getHashValue());
								show();
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;

					case 'S':

						show();

					case 'F':

						System.out.println("Enter the number to find..");
						br = new BufferedReader(new InputStreamReader(System.in));
						try {
							String findStr = br.readLine();
							int number = Integer.parseInt(findStr);
							find(number);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;

					default:
						System.out.println("Invalid Entry !!");
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("\nDo you want to continue? Y/N");
				br = new BufferedReader(new InputStreamReader(System.in));
				try {
					String choiceStr = br.readLine();
					isContinue = choiceStr.charAt(0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} while (isContinue == 'Y');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static void show() {
		for (int indexCount = 0; indexCount < arraySize; indexCount++) {
			if (hashTable[indexCount] != null) {
				System.out.print(" " + hashTable[indexCount].getHashValue());
			} else {
				System.out.print(" " + "#");
			}
		}
	}

	static void insert(int number) {
		int arrayIndex = hashFunction(number);
		while (hashTable[arrayIndex] != null) {
			++arrayIndex;
			arrayIndex = arrayIndex % arraySize;
		}
		hashTable[arrayIndex] = new HashElement(number);
	}

	static HashElement delete(int number) {
		int arrayIndex = hashFunction(number);
		while (hashTable[arrayIndex] != null) {
			if (hashTable[arrayIndex].hashValue == number) {
				System.out.println("Number " + number + " at index " + arrayIndex + " is deleted");
				HashElement temp = hashTable[arrayIndex];
				hashTable[arrayIndex] = new HashElement(-1);
				return temp;
			} else {
				++arrayIndex;
				arrayIndex = arrayIndex % arraySize;
			}
		}
		System.out.println("Number not found for Deletion!!");
		return null;
	}

	static HashElement find(int number) {
		int arrayIndex = hashFunction(number);
		while (hashTable[arrayIndex] != null) {
			if (hashTable[arrayIndex].hashValue == number) {
				System.out.println("Found Number " + number + " at index " + arrayIndex);
				return hashTable[arrayIndex];
			} else {
				++arrayIndex;
				arrayIndex = arrayIndex % arraySize;
			}
		}
		System.out.println("Number not found!!");
		return null;
	}

	static int hashFunction(int randomNumber) {
		int arrayIndex = randomNumber % arraySize;
		return arrayIndex;
	}

}

class HashElement {
	int hashValue;

	HashElement(int hashValue) {
		this.hashValue = hashValue;
	}

	public int getHashValue() {
		return hashValue;
	}

	public void setHashValue(int hashValue) {
		this.hashValue = hashValue;
	}

}
