
import java.util.*;
public class Queue {
	public Queue() {
	}
	//arraylist of strings
	ArrayList<String> queue = new ArrayList<>();
	//add something to the queue
	public void enqeue(String s) {
		queue.add(s);
	}
	//remove something from the queue
	public String dequeue() {
		String s = queue.get(0);
		queue.remove(0);
		return s;
	}
	//check if the queue is empty
	public boolean isEmpty() {
		return queue.isEmpty();
	}


}
