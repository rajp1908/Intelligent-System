
package A3;

/**
 * This class provides functionality of Doubly LinkedList.
 * @author Raj (40085012)
 *
 */
public class LinkedList<T> {
	
	/**
	 * This class represents a Node in LinkedList
	 * @author Raj
	 *
	 * @param <T>
	 */
	private class Node<T>
	{
		private T id;  // Student id
		private Node<T> next;  // A link to the next node,
		private Node<T> previous;  // A link to previous node
		
		// Default constructors 
		public Node() {
			id = null;
			next = previous = null;
		}
		
		// Parameterized constructor 
		public Node(T i, Node<T> nxt, Node<T> pre)
		{
			id = i;
			next = nxt;
			previous = pre;
		}

	} // end of inner class Node
	
	private Node<T> head;  // head pointer
	private Node<T> tail;  // tail pointer
	
	public LinkedList()
	{
		head = null;
		tail = null;
	}
	
	/**
	 * Adds element at the start of the linkedlist.
	 * @param i
	 */
	public void addFirst(T i) {
		Node<T> temp = new Node<T>(i, head, null);
		if(head == null) {
			head = tail = temp;
		}
		else 
			head = temp;
	}
	
	/**
	 * Adds element at the end of the linkedlist. 
	 * @param i
	 */
	public void addLast(T i) {
		
		Node<T> temp = new Node<T>(i, null, null);
		
		temp.previous = tail;
		if(head == null) {
			head = temp;
		}
		else {
			tail.next = temp;
		}
		tail = temp;
		
	}
	
	/**
	 * Removes element at the start of the linkedlist.
	 * @return T null if element is not present
	 */
	public T removeFirst() {
		Node<T> temp = head;
		
		if(head != null) {
			if(head.next == null) {
				head = null;
			}
			else {
				head = head.next;
				head.previous = null;
			}
			
			return temp.id;
		}
		return null;
	}
	
	/**
	 * Removes element at the end of the linkedlist.
	 * @return T null iof element is not present.
	 */
	public T removeLast() {
		Node<T> temp = tail;
		if(tail != null) {
			tail = tail.previous;
			tail.next = null;
			return temp.id;
		}
		return null;
	}
	
	/**
	 * Gets the first element of the LinkedList without removing.
	 * @return T
	 */
	public T getFirst() {
		return (head != null) ? head.id : null;
	}
	
	/**
	 * Gets the last element of the LinkedList without removing.
	 * @return T
	 */
	public T getLast() {
		return (tail != null) ? tail.id : null;
	}
	
	/**
	 * Displays the elements of the LinkedList 
	 */
	public void display() {
		Node<T> temp = head;
		while(temp != null) {
			System.out.print(temp.id + " ");
			temp = temp.next;
		}
		System.out.println();
	}

}
