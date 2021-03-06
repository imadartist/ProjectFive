/*
 * Programmer: Madison Leyens
 * Class: Tree
 * Date: 12.3.2020
 * Description: Tree Class that creates prediction suffix trees that all nodes are added to after being trained on inputed data
 */

import java.util.ArrayList;

public class Tree<T> {
//holds the root node and interacts with the tree via the root node. The tree does not perform actions of adding nodes or traversing the tree beyond its calls to the root node.

	Node<T> root = new Node(new ArrayList<T>()); //the root of the tree
	int tree; //declare tree as a global variable
	float pMin; //user inputed pmin value to be compared to empirical probability of tokens
	int totalInputTokens; //the total number of input tokens
	
	Tree(int L, float E) {
		tree = L; // set tree equal to L, which is the maximum token sequence length (order length) the user will pass in
		pMin = E;
	}

	void train(ArrayList<T> input) {
		// creates the unique token sequences from the input & using the root, adds them
		// to the tree
		for (int i = 1; i <= tree; i++) // i is referring to the current order number
		{
			for (int j = 0; j < input.size() - (i - 1); j++) // index into the input
			{
				ArrayList<T> curSequence = new ArrayList(input.subList(j, i + j));

				Node<T> theNewNode = new Node(curSequence);
				root.addNode(theNewNode);
			}
		}
		totalInputTokens = input.size() + totalInputTokens;
		//sum the total tokens in the input here(totalInputTokens)--
		//this is most easily done similarly to how we summed our total tokens 
		//in the ProbabilityGenerator
		root.pMinElimination( totalInputTokens, pMin);
	}
	void print(String s) {
	//calls the root to print
	System.out.println("---------------");
	System.out.println(s);
	System.out.println("---------------");
	root.print();	
	}
	
}


