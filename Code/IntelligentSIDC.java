
package A3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * This class provides all the necessary methods for IntelligentSIDC system.
 * @author Raj(40085012)
 *
 */
public class IntelligentSIDC {
	

	private final int MIN_VALUE = 10000000; // minimum student id
	private final int MAX_VALUE = 100000000;  // maximum student id
	private int count = 0;  // keeps count of number of entries

	private BinaryTree bt;  // Binary tree reference
	
	/**
	 * 
	 */
	public IntelligentSIDC() {
		bt = new BinaryTree();
	}
	
	/**
	 * This function defines the size of array to use to store student records.
	 * @param size
	 */
	void SetSIDCThreshold(int size) {
		
	}
	
	/**
	 * Generates random number in the range of [MIN_VALUE (inclusive), MAX_VALUE(exclusive)]
	 * 
	 * @return
	 */
	public int generate() {
		Random random = new Random();
		int id = random.ints(MIN_VALUE, MAX_VALUE)
	      .findFirst()
	      .getAsInt();
		return id;
	}
	
	/**
	 * Adds student record to the system.
	 * @param key
	 * @param value
	 */
	public void add(int key,String value) {
		bt.insertRecord(key, value);
		++count;
	}
	
	/**
	 * Prints student IDs in sorted sequence.
	 */
	public void allKeys(boolean flag) {
		Queue<Integer> ans = bt.inorder();  // inorder traversal gives the sorted sequence in BST
		System.out.println("All the Students IDs (in the sorted sequence) are: ");
		if(flag) {
			try {
				PrintWriter pw = new PrintWriter(new FileOutputStream("Output2.txt"));
				while(ans.size() > 0) {
					pw.println(ans.remove());
				}
				pw.close();
			}
			catch(IOException e) {
				System.out.println("Error occured.");
			}
		}
		else {	
			while(ans.size() > 0) {
				
				System.out.print(ans.remove()+" ");
			}
			System.out.println();
		}
	}

	/**
	 * Removes a record with given student id.
	 * @param key
	 * @return int student_ID
	 */
	public int remove(int key) {
		 return bt.remove(key);
	}
	
	/**
	 * Gives value associated with the student ID.
	 * @param key
	 * @return String
	 */
	public String getValues(int key) {
		return bt.findNode(key);
	}
	
	/**
	 * Gives the next student record.
	 * @param key
	 * @return int
	 */
	public int nextKey(int key) {
		return bt.nextKey(key);
	}
	
	/**
	 * Gives the previous student record.
	 * @param key
	 * @return int
	 */
	public int prevKey(int key) {
		return bt.previousKey(key);
	}
	
	/**
	 * Gives the list of student IDs in the provided range.
	 * @param key1
	 * @param key2
	 * @return q
	 */
	public Queue<Integer> rangeKey(int key1,int key2) {
		Queue<Integer> q = bt.getKeysInRange(key1, key2, count);
		return q;
	}
	
}
