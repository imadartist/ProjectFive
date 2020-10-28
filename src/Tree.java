import java.util.ArrayList;

public class Tree<T> {
//holds the root node and interacts with the tree via the root node. The tree does not perform actions of adding nodes or traversing the tree beyond its calls to the root node.

	Node<T> root; //the root of the tree
	int tree; //declare tree as a global variable
	
	Tree(int L) {
		tree = L; // set tree equal to L, which is the maximum token sequence length (order length) the user will pass in
	}

	void train(ArrayList<T> input) {
		// creates the unique token sequences from the input & using the root, adds them
		// to the tree
		for (int i = 1; i <= tree; i++) // i is referring to the current order number
		{
			for (int j = 0; j < input.size() - (i - 1); j++) // index into the input
			{
				ArrayList<T> curSequence = new ArrayList(input.subList(j, i + j));
				System.out.println(curSequence);

				Node<T> theNewNode = new Node(curSequence);
				root.addNode(theNewNode);
			}
		}
	}
	void print() {
	//calls the root to print
	root.print();	
	}
	
}


