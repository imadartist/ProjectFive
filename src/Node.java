/*
 * Programmer: Madison Leyens
 * Class: Node
 * Date: 12.3.2020
 * Description: Node Class that adds nodes to the prediction suffix tree, checks for suffixes, prints, and does p-min elimination
 * of child nodes based on empirical probability
 */

import java.util.ArrayList;

public class Node<T> {
	// one node in the prediction suffix tree
	ArrayList<Node> children = new ArrayList();
	ArrayList<T> tokenSequence = new ArrayList(); // tokenSequence – the sequence at this node
	int count = 1; // the number of times the node appears in the input

	Node(ArrayList<T> newSequence) {
		tokenSequence = newSequence;// set current sequence to the token sequence using a set function in the
									// constructor as a parameter
	}

	boolean addNode(Node node) { // Adds a child node. Will only add a child node if the input node contains this
									// node as a suffix.
		boolean found = false; // whether the node has been added or not yet
		if (tokenSequence.equals(node.getTokenSequence())) { // if the tokenSequence of this node is the same as the
			// token sequence of the added node use .equals to check this
			found = true; // do NOTHING else. You will do things here in the future. But not for now;
			count++;
		} else if (amIaSuffix(node) || (tokenSequence.size() == 0)) // note that the empty sequence is always a suffix!
		{
			int i = 0;

			while (i < children.size() && !found) { // call.use a loop to go through children.
				found = children.get(i).addNode(node); // 1. try to add the node to all the children nodes. the pseudo
														// recursive
				i++;

			}
			if (!found) { // If NOT found and the length of node’s tokenSequence is one less than this
							// tokenSequence
				children.add(node); // Add the node to our children array. Thus- found=true.
				found = true;
			}

		}
		return found; // boolean requires a return
	}

	void print() {
		System.out.println(tokenSequence); // prints the tokenSequence, then prints its children
		for (int i = 0; i < children.size(); i++) // for each node in the children
		{
			children.get(i).print(1);// see the next function
		}
	}

	void print(int numSpacesBefore) {

		for (int i = 1; i < numSpacesBefore; i++) { // for 1 to numSpacesBefore
			System.out.print(" ");
		}
		System.out.print("-->");// print an arrow (-->)
		System.out.println(tokenSequence); // print the token
		for (int i = 0; i < children.size(); i++) // for each node in the children
		{
			children.get(i).print(numSpacesBefore + 1); // each time you call this from the next child the number of
														// spaces will increase by 1
		}

	}

	ArrayList<T> getTokenSequence() {
		return tokenSequence;
	}

	boolean amIaSuffix(Node node) {
		boolean amI = false;
//  is the token sequence the suffix at the incoming node at node.getSequence which is the same size or greater than the tokenSequence
//	is the given node’s tokenSequence a suffix of the input sequence?
//	determines whether the tokenSequence of this node is a suffix of the tokenSequence of the input node

		amI = node.getTokenSequence()
				.subList(node.getTokenSequence().size() - tokenSequence.size(), node.getTokenSequence().size())
				.equals(tokenSequence);

		if (tokenSequence.size() == 0) {
			// do something to increase tokenSequence.size()? add a token?
			amI = false;
		}
		if (node.getTokenSequence().size() - tokenSequence.size() == 0) { // add an if statement for when
																			// node.getTokenSequence().size() -
																			// tokenSequence.size() is 1-1
			amI = false;
		}

		return amI;
	}

	boolean pMinElimination(int totalTokens, float pMin) {// performs elimination based on an empirical probability
															// threshold PMin.
		boolean shouldRemove = false;
		float emProb = 0;
		// 1. find the number of times that the sequence could have occurred ( dependent
		// on tokenSequence.size() )
		emProb = (float) count / (float) (totalTokens - (tokenSequence.size() - 1)); // cast as float
		//System.out.println(tokenSequence + ":" + emProb);
		// 2. shouldRemove = empirical probability of the token sequence < pMin (note:
		// handle the empty sequence/ root) AND is not the root node
		shouldRemove = emProb < pMin && tokenSequence.size() != 0; 
		// 3. if we should NOT remove this node{
		if (!shouldRemove) {
			for (int i = children.size() - 1; i >= 0; i--) {// for each node (start from the end & go to the front of
															// each array):

				boolean remove = children.get(i).pMinElimination(totalTokens, pMin); // call pMinElimination on all the
																						// children nodes if they return
																						// true (ie, we should remove
																						// the node)
				if (remove) {
					children.remove(children.get(i)); // then remove the entire node (which incl. its children) you may
														// use the ArrayList method.remove()
				}
			}
		}
		return shouldRemove; // Returns whether to delete this node or not. The parent node performs the
								// deletion.
	}
}
