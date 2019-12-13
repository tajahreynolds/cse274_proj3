/**
 * A circular doubly linked-node implementation of the Set ADT in which elements of the set
 * are always sorted (in this case, lexicographically, which is a fancy
 * way of saying "alphabetically").
 * 
 * @author TaJah
 */
public class SortedSet implements SetInterface<String> {
	
	private Node head;
	private int size;
	
	public SortedSet() {
		size = 0;
		head = null;
	}
	
	@Override
	public int getCurrentSize() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean add(String newEntry) {
		Node newNode = new Node(newEntry);
		
		if (this.contains(newEntry))
			return false;						// No duplicates allowed
		
		if (head == null) { 					// List is empty
			return addToHead(newNode);
		}
		
		Node curr = head;
		while (curr.data.compareTo(newNode.data) < 0) {	// Curr comes before new
			if (curr.next.data.equals(head.data)) { // Next node is head
				return sort(newNode, curr);
			} else {
				curr = curr.next;
			}
		}
		
		// Curr comes after new
		if (curr.data.equals(head.data)) {	// New node is first node
			return sortFirst(newNode, curr);
		} else {							// New node is not first node
			return sortAfter(newNode, curr);
		}
	}
	
	private boolean sort(Node newNode, Node curr) {
		head.prev = newNode;
		newNode.next = head;
		newNode.prev = curr;
		curr.next = newNode;
		size++;
		return true;
	}
	
	private boolean sortFirst(Node newNode, Node curr) {
		head = newNode;
		newNode.prev = curr.prev;
		newNode.next = curr;
		curr.prev = newNode;
//		if (size == 1) {
			curr.next = newNode;
//		}
		size++;
		return true;
	}
	
	private boolean sortAfter(Node newNode, Node curr) {
		curr.prev.next = newNode;
		newNode.prev = curr.prev;
		curr.prev = newNode;
		newNode.next = curr;
		size++;
		return true;
	}
	
	private boolean addToHead(Node newNode) {
		head = newNode;
		head.next = head;
		head.prev = head;
		size++;
		return true;
	}

	@Override
	public boolean remove(String anEntry) {
		if (this.contains(anEntry)) {
			Node curr = head;
			while (!curr.data.equals(anEntry)) {
				curr = curr.next;
			}
			if (curr.data.equals(head.data))
				head = curr.next;
			curr.next.prev = curr.prev;
			curr.prev.next = curr.next;
			curr = null;
			size--;
			return true;
		}
		return false;
		
	}

	@Override
	public String remove() {
		if (this.isEmpty())
			return null;
		
		String ret = head.data;
		if (size > 1) {
			head = head.next;
			head.prev = head.prev.prev;
			head.prev = null;
		} else {
			head = null;
		}
		size--;
		return ret;
	}
	
	@Override
	public String removeLast() {
		if (this.isEmpty())
			return null;
		String ret = head.prev.data;
		if (size > 1) {
			head.prev = head.prev.prev;
			head.prev.next = head;
		} else {
			head = null;
		}
		size--;
		return ret;
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public boolean contains(String anEntry) {
		if (this.isEmpty())
			return false;
		
		Node curr = head;
		while (!curr.data.equals(anEntry)) {
			if (curr.next.data.equals(head.data))
				return false;
			curr = curr.next;
		}
		return true;
	}
	
	/*
	 * returns a string representation of the items in the set,
	 * specifically a space separated list of the strings, enclosed
	 * in square brackets [].  For example, if the set contained
	 * cat, dog, then this should return "[cat dog]".  If the
	 * set were empty, then this should return "[]".
	 */
	@Override
	public String toString() {
		String str = "[";
		Node curr = head;
		for (int i = 0; i < size; i++) {
			str += curr.data;
			if (i != size-1)
				str += " ";
			curr = curr.next;
		}
//		while (!curr.next.data.equals(head.data)) {
//			str += " " + curr.data;
//		}
//		str += curr.data + "]";
		return str + "]";
		
	}
	
	@Override
	public String[] toArray() {
		String[] str = new String[size];
		Node curr = head;
		for (int i = 0; i < size; i++) {
			str[i] = curr.data;
			curr = curr.next;
		}
		return str;
	}
	
	private class Node {
		private String data;
		private Node next;
		private Node prev;
		
		public Node(String data) {
			this.data = data;
		}
		
		public String getData() {
			return this.data;
		}
		
		public void setData(String data) {
			this.data = data;
		}
		
		public Node getNext() {
			return this.next;
		}
		
		public void setNext(Node next) {
			this.next = next;
		}
		
		public Node getPrev() {
			return this.prev;
		}
		
		public void setPrev(Node prev) {
			this.prev = prev;
		}
		
	}
	
}
