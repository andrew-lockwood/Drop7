

class LinkedList
{
	private Node head; 
	private int size; 

	public LinkedList() {
		head = new Node();	
		size = 0;
	}

	public void add(int v) {
		Node temp = new Node();
		temp.setValue(v);
		Node curr = head; 

		if ( curr != null ) {
			while ( curr.getNext() != null )
				curr = curr.getNext();

			curr.setNext(temp);
		}
		incrementCounter();
	}


	public String toString() {
		String output = "Size " + getCounter() + ": ";

		if ( head.getNext() != null ) {
			Node curr = head.getNext();
			while ( curr != null ) {
				output += " " + curr.getValue();
				curr = curr.getNext();
			}
		}

		return output;
	}


	public int getValueAt(int index) {
		// Check if there are any items in the list and if 
		// there is a valid index
		if ( index < 0 || index > size || head.getNext() == null )
			return -1;

		Node curr = head.getNext();

		for ( int i = 1; i < index; i++ ) 
			curr = curr.getNext();

		return curr.getValue();

	}


	public void setValueAt(int index, int value) {
		// Check if there are any items in the list and if 
		// there is a valid index
		if ( index < 0 || index > size || head.getNext() == null )
			return;

		Node curr = head.getNext();

		for ( int i = 1; i < index; i++ ) 
			curr = curr.getNext();

		curr.setValue(value);

	}


	public boolean removeValueAt(int index) {
		// Check if there are any items in the list and if 
		// there is a valid index
		if ( index < 0 || index > size || head.getNext() == null )
			return false;

		Node curr = head.getNext();

		for ( int i = 1; i < index - 1; i++ ) 
			curr = curr.getNext();
		
		// Check if the value to be removed is at the end of the list 
		if ( curr.getNext().getNext() != null )
			curr.setNext(curr.getNext().getNext());
		else 
			curr.setNext(null);

		decrementCounter();
		
		return true;
	}

	public boolean removeValue(int value) {
		// Check if there are any items in the list 
		if ( head.getNext() == null )
			return false;

		Node curr = head;

		while ( curr.getNext() != null ) 
		{
			if ( curr.getNext().getValue() == value ) {
				decrementCounter();
				if ( curr.getNext().getNext() != null )
					curr.setNext(curr.getNext().getNext());
				else 
					curr.setNext(null);
			}
			else 
				curr = curr.getNext();
		}

		return true;

	}

	public int size() {
		return getCounter();
	}


	private int getCounter() {
		return size;
	}

	private void incrementCounter() {
		size++;
	}

	private void decrementCounter() {
		size--;
	}


}


class Node 
{

	Node next;
	int value;

	public Node() {
		next = null;
	}

	public void setNext(Node n) {
		next = n;
	}

	public void setValue(int v) {
		value = v;
	}

	public int getValue() {
		return value;
	}

	public Node getNext() {
		return next;
	}

}
