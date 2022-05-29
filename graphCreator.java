/*
* Jason Zhang
* Periodd 5
* Graph Creator
* */



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.*;

public class graphCreator implements ActionListener, MouseListener {
    //create the frame
    JFrame frame = new JFrame();
    //create the panel
    graphPanel panel = new graphPanel();
    //create the buttons
    JButton nodeB = new JButton("Node");
    JButton edgeB = new JButton("Edge");
    JButton connectedB = new JButton("Test Connected");
    JButton salesmanB = new JButton("Shortest Path");
    //create the text fields
    JTextField labelsTF = new JTextField("A");
    JTextField firstNode = new JTextField("First");
    JTextField secondNode = new JTextField("Second");
    JTextField salesmanStartTF = new JTextField("A");

    //create the containers west, east and south
    Container west = new Container();
    Container east = new Container();
    Container south = new Container();
    //the states the program can be in
    final int NODE_CREATE = 0;
    final int EDGE_FIRST = 1;
    final int EDGE_SECOND = 2;
    int state = NODE_CREATE;
    Node first = null;
    //array list of paths
    ArrayList<ArrayList<Node>> completed = new ArrayList<>();
    //list of edges
    ArrayList<Edge> edgeList;
    //number of nodes
    int numberOfNodes = 0;
    //list of coasts
    ArrayList<Integer> totalList = new ArrayList<>();
    public graphCreator() {
        //set the frame's size to 800  by 600
        frame.setSize(800, 600);
        //set the layout to a borderLayout
        frame.setLayout(new BorderLayout());
        //Add of of the buttons and textFields and action listeners to the buttons
        frame.add(panel, BorderLayout.CENTER);
        west.setLayout(new GridLayout(3, 1));
        west.add(nodeB);
        nodeB.addActionListener(this);
        nodeB.setBackground(Color.GREEN);
        west.add(edgeB);
        edgeB.addActionListener(this);
        edgeB.setBackground(Color.LIGHT_GRAY);
        west.add(labelsTF);
        frame.add(west, BorderLayout.WEST);
        east.setLayout(new GridLayout(3, 1));
        east.add(firstNode);
        east.add(secondNode);
        east.add(connectedB);
        connectedB.addActionListener(this);
        frame.add(east, BorderLayout.EAST);
        panel.addMouseListener(this);
        south.setLayout(new GridLayout(1, 2));
        south.add(salesmanStartTF);
        south.add(salesmanB);
        salesmanB.addActionListener(this);
        frame.add(south, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set the frames visibility to true
        frame.setVisible(true);

    }

    public static void main(String args[]) {
        //create graph
        new graphCreator();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //when mouse is released
        //if the state is in node create
        if (state == NODE_CREATE) {
            //create a new node at the mouse's position, and give it the label in the text field
            panel.addNode(e.getX(), e.getY(), labelsTF.getText());
            //increment number of nods by 1
            numberOfNodes++;
        }
        //if the state is first edge
        else if (state == EDGE_FIRST) {
            //create a reference of the node clicked on
            Node n = panel.getNode(e.getX(), e.getY());
            //make sure a node was clicked on
            if (n != null) {
                //set first to n
                first = n;
                //set state to edge_second
                state = EDGE_SECOND;
                //highlight the node
                n.setHighlighted(true);
            }
        }
        //if state is edge_second
        else if (state == EDGE_SECOND) {
            Node n = panel.getNode(e.getX(), e.getY());
            //check if a node was clicked on, and that it was not the first node
            if (n != null && !first.equals(n)) {
                //get the label from the text  box
                String s = labelsTF.getText();
                boolean valid = true;
                //make sure the label only has numbers
                for (int a = 0; a < s.length(); a++) {
                    if (Character.isDigit(s.charAt(a)) == false) {
                        valid = false;
                    }
                }
                //if the label only has numbers
                if (valid == true) {
                    //un highlight the first node
                    first.setHighlighted(false);
                    //create a new edge between the two nodes
                    panel.addEdge(first, n, labelsTF.getText());
                    //reset first
                    first = null;
                    //and set the state back to edge_first
                    state = EDGE_FIRST;
                } else {
                    //Create a new pane with a message saying that only numbers are accepted in the label
                    JOptionPane.showMessageDialog(frame, "Con only have digits in label.");
                }
            }

        }
        //repaint
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if the node button was pressed
        if (e.getSource().equals(nodeB)) {
            //set the node button's color to green
            nodeB.setBackground(Color.GREEN);
            //set the edge button's background back to gray
            edgeB.setBackground(Color.LIGHT_GRAY);
            //set the state to noe create
            state = NODE_CREATE;
        }
        //if the edge button was pressed
        if (e.getSource().equals(edgeB)) {
            //set the node button's color to gray
            nodeB.setBackground(Color.LIGHT_GRAY);
            //set the edge button's color to green
            edgeB.setBackground(Color.GREEN);
            //set the state to edge first
            state = EDGE_FIRST;
            //stop all highlighting
            panel.stopHighlighting();
            //repaint the frame
            frame.repaint();
        }
        //if the connect button was pressed
        if (e.getSource().equals(connectedB)) {
            //make sure both nodes exist
            if (panel.nodeExists(firstNode.getText()) == false) {
                JOptionPane.showMessageDialog(frame, "First Node is not in your graph.");
            } else if (panel.nodeExists(secondNode.getText()) == false) {
                JOptionPane.showMessageDialog(frame, "Second Node is not in your graph.");
            } else {
                //find all the connected nodes of the first node, and check if the second node is one of them
                Queue queue = new Queue();
                ArrayList<String> connectedList = new ArrayList<String>();
                connectedList.add(panel.getNode(firstNode.getText()).getLabel());
                ArrayList<String> edges = panel.getConnectedLabels(firstNode.getText());
                for (int a = 0; a < edges.size(); a++) {
                    queue.enqeue(edges.get(a));
                }
                while (queue.isEmpty() == false) {
                    String currentNode = queue.dequeue();
                    if (connectedList.contains(currentNode) == false) {
                        connectedList.add(currentNode);
                    }
                    edges = panel.getConnectedLabels(currentNode);
                    for (int a = 0; a < edges.size(); a++) {
                        if (connectedList.contains(edges.get(a)) == false) {
                            queue.enqeue(edges.get(a));
                        }
                    }
                }
                //print connected or not connected
                if (connectedList.contains(secondNode.getText())) {
                    JOptionPane.showMessageDialog(frame, "Connected!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Not Connected!");
                }
            }

        }
        //the salesman
        if (e.getSource().equals(salesmanB)) {
            //if there is text
            if (panel.getNode(salesmanStartTF.getText()) != null) {
                //get the edgeList
                edgeList = panel.edgeList;
                //create arraylist path with the value in the text box in it
                ArrayList<Node> path =  new ArrayList<Node>();
                path.add(panel.getNode(salesmanStartTF.getText()));
                //call traveling
                travelling(panel.getNode(salesmanStartTF.getText()),path, 0);
                //make sure completed has a path
                if(completed.size()>0) {
                    //find the shortest path. print out its value and the path
                    String output = "";
                    int smallestVal = Collections.min(totalList);
                    for (Node n : completed.get(totalList.indexOf(smallestVal))) {
                        output += n.getLabel();
                    }
                    JOptionPane.showMessageDialog(frame, "The shortest path is: " + output + " with a length of " + smallestVal);
                }
                else{
                    JOptionPane.showMessageDialog(frame, "There is no path." );
                }
                //reset variables
                completed.clear();
                totalList.clear();
            }
            else{
                JOptionPane.showMessageDialog(frame, "Not a valid starting node!");
            }
        }
    }

    public void travelling(Node n, ArrayList<Node> path, int total) {
        //if the number of nodes in the path is equal to the number of nodes,
        //  add this path to the completed list
        //	remove the last thing in the path
        //  return
    	if(path.size() == numberOfNodes) {
    		completed.add((ArrayList<Node>) path.clone());
    		path.remove(path.size()-1);
    		totalList.add(total);
    		return;
    	}
        //else
        //  for each edge
        //      see if they are connected to the current node
        //      if they are not already in the path
        //      add node to path
        //      travelling(connected node, path, total + edge cost);
    	for(int a = 0; a<edgeList.size();a++) {
    		Edge e = edgeList.get(a);
    		if(e.getOtherEnd(n)!= null) {
    			if(path.contains(e.getOtherEnd(n)) == false) {
    				String val = e.getOtherEnd(n).getLabel();
    				path.add(e.getOtherEnd(n));
    				travelling(e.getOtherEnd(n), path, total+Integer.parseInt(e.getLabel()));
    			}
    		}
    	}
        //remove the last thing in the path
        path.remove(path.size()-1);

    }
    /*
     *Adjacency Matrix
     *
     *		A	B	C
     * A 	1	1	1
     * B	1	1	0
     * C	1	0	1
     *
     *
     * */

}
