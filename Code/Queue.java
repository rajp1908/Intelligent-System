/**
 * 
 */
package A3;

/**
 * @author Raj
 *
 */
public class Queue<T> {
	
	private static int count;
	public LinkedList<T> ll;
	/**
	 * 
	 */
	public Queue() {
		ll = new LinkedList<T>();
	}
	
	public void add(T i) {
		ll.addLast(i);
		count++;
	}
	
	public T remove() {
		T val = ll.removeFirst();
		if(val != null) {
			count = count-1;
		}
		return val;
	}
	
	public T peek() {
		return ll.getFirst();
	}
	
	public int size() {
		return count;
	}
}
