package islandCreator;

import java.util.Iterator;

import edu.princeton.cs.algs4.Queue;

/**
 * Creates an instance of the IslandQueue class.
 * 
 * @author Nick Pantuso
 */
public class IslandQueue implements Iterable<IslandCreator>{
	
	private Queue<IslandCreator> q;
	
	/**
	 * Defines an IslandQueue via a Queue.
	 */
	public IslandQueue() {
		q = new Queue<IslandCreator>();
	}
	
	/**
	 * Adds an item to the end of the queue.
	 * 
	 * @param i
	 */
	public void enqueue(IslandCreator i) {
		q.enqueue(i);
	}

	/**
	 * Returns an iterator for the queue.
	 */
	@Override
	public Iterator<IslandCreator> iterator() {
		return q.iterator();
	}
	
	/**
	 * Returns the least recently enqueued item.
	 * 
	 * @return least recently enqueued item
	 */
	public IslandCreator peek() {
		return q.peek();
	}
	
	/**
	 * Returns the size of the queue.
	 * 
	 * @return size of the queue
	 */
	public int size() {
		return q.size();
	}
}
