import java.util.ArrayList;

public class Node<T> {
	// one node in the prediction suffix tree
	ArrayList<Node> children = new ArrayList();
	ArrayList<T> tokenSequence = new ArrayList(); // tokenSequence – the sequence at this node
	Node (ArrayList<T>newSequence) {
	tokenSequence = newSequence;//set current sequence to the token sequence using a set function in the constructor as a parameter
	}
	boolean addNode(Node node) {
//	//Adds a child node. Will only add a child node if the input node contains this node as a suffix.
		boolean found = false; // whether the node has been added or not yet
//		if (tokenSequence = node.tokenSequence){}// ifthe tokenSequence of this node is the same as the token sequence of the added node
//		use .equals to check this
//		{
//		found = true
//		//do NOTHING else. You will do things here in the future. But not for now;
//		}
//		else if( amIASuffix(node) || (tokenSequence.size()==0)) //note that the empty sequence is always a suffix!
//		{
//		1. try to add the node to all the children nodes. the pseudo recursive call. use a loop to go through children. 
//		2. Did one your child nodes add the node? **keep track of this via the found variable**
//		if found set found to true found = true;
//		If NOT found and the length of node’s tokenSequence is one less than this tokenSequence
//		Add the node to our children array. Thus- found=true.
//		children.add(node);
//		}
//
		return found; 	
	}

	void print() {
		System.out.println(tokenSequence); //prints the tokenSequence, then prints its children
//		print the token sequence
//		for each node in the children
//		{
//		node.print(1) //see the next function
//		}
	}
	
	void print(int numSpacesBefore) {
		for (int i = 1; i < numSpacesBefore; i++) { //for 1 to numSpacesBefore
//		System.out.print(“ “); //note: don’t use println because you don’t want a line
		System.out.println("-->");// print an arrow (-->) if you like, it will be clearer
//		print the token
		}
//		for each node in the children
//		{
//		//each time you call this from the next child the number of spaces will increase by 1
//		node.print(numSpacesBefore + 1);
//		}
	}
	
	ArrayList <T> getTokenSequence () {
		return tokenSequence;
	}
	
	boolean amIaSuffix(Node node) {
	boolean amI = false;
//  is the token sequence the suffix at the incoming node at node.getSequence which is the same size or greater than the tokenSequence
//	is the given node’s tokenSequence a suffix of the input sequence?
//	determines whether the tokenSequence of this node is a suffix of the tokenSequence of the input node
//	Hint #1: using the sublist() method makes this much easier.
//	Hint #2: note the difference between .equals() and ==.
//	Hint #3: You MUST test this separately to make sure it works. That means calling it temporarily from the main
//	class to make sure it works.
//	Nothing in your Node adding will work if this is incorrect and you cannot simply
//	assume it is correct if you haven’t tested it
	return amI;
	}
}
