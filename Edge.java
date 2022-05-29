public class Edge {
	//first, second an label in each edge
	Node first;
	Node second;
	String label;
	public Edge(Node newfirst, Node newsecond, String newlabel) {
		//Set the first node, second node and label
		first = newfirst;
		second = newsecond;
		label = newlabel;
	}
	//get the node that node n is connected to
	public Node getOtherEnd(Node n) {
		if(first.equals(n)) {
			return second;
		}
		else if(second.equals(n)) {
			return first;
		}
		else {
			return null;
		}
	}
	//get the first node
	public Node getFirst() {
		return first;
	}
	//set the first node
	public void setFirst(Node first) {
		this.first = first;
	}
	//get the second node
	public Node getSecond() {
		return second;
	}
	//set the second node
	public void setSecond(Node second) {
		this.second = second;
	}
	//get the label
	public String getLabel() {
		return label;
	}
	//set the label
	public void setLabel(String label) {
		this.label = label;
	}

}
