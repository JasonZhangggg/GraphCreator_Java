
public class Node {
	//x, y, and label of the Node
	int x;
	int y;
	String label;
	boolean highlighted = false;
	public Node(int newx, int newy, String newlabel) {
		//set the x, y and label of the node
		x=newx;
		y=newy;
		label=newlabel;
		highlighted = false;

	}
	//get the x position of the node
	public int getX() {
		return x;
	}
	//set the x position of the node
	public void setX(int x) {
		this.x = x;
	}
	//get the Y position of the noe
	public int getY() {
		return y;
	}
	//set the y position of the node
	public void setY(int y) {
		this.y = y;
	}
	//get the label
	public String getLabel() {
		return label;
	}
	//set the label
	public void setLabel(String label) {
		this.label = label;
	}
	//check if the node is highlighted
	public boolean getHighlighted(){
		return highlighted;
	}
	//set the node to highlighted
	public void setHighlighted(boolean highlighted){
		this.highlighted = highlighted;
	}


}
