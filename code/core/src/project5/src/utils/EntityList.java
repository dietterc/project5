/**
 * Linked list for storing entities 
 */
package project5.src.utils;

import project5.src.*;

public class EntityList {
	
	private Node top;
	private Node iterator;
	
	public EntityList() {
		top = null;
		iterator = top;
	}
	
	public void insert(Entity e) {
		top = new Node(e,top);
	}
	
	public Entity getNext() {
		Entity retVal = null;
		iterator = iterator.next;
		if (iterator != null)
			retVal = iterator.item;
			return retVal;
	}
	
	public void startIter() {
		iterator = top;
	}
	
	class Node {
		Entity item;
		Node next;
		public Node(Entity e, Node n) {
			item = e;
			next = n;
		}
		
		
	}

	public Entity getTopItem() {
		return top.item;
	}

}
