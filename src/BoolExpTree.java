import java.util.*;

/**
Boolean Expression Tree
Build expression tree from a vector of string in the postfix notation
*/
public class BoolExpTree
{
	private ExpNode root; // Root node of the tree
	private LinkedHashSet<String> table; // symbol table for the expression

	/**
	Construct a expression tree from a vector of string in the postfix notation.
	Algorithm:
	For each token:
		if is an ID push a new IdentifierNode to the stack;
		if is an operator pop the required number of operands and push a new CompoundNode with the operands as its children

	@param postfix a vector of strings in the postfix notation

	TODO: move some of the parsing to the factory method of CompoundNode
	*/
	public BoolExpTree(Vector<String> postfix)
	{
		table = new LinkedHashSet<String>();
		Stack<ExpNode> stack = new Stack<ExpNode>();
		for(String token : postfix)
		{
			// if is an operator pop the required number of operands and push a new CompoundNode with the operands as its children
			if(token.equals(BooleanOperators.NOT))
			{
				ExpNode node = CompoundNode.make(token, stack.pop(), null);
				stack.push(node);
			}
			else if(token.equals(BooleanOperators.AND) || 
				    token.equals(BooleanOperators.OR) ||
			    	token.equals(BooleanOperators.IMPLIES))
			{
				ExpNode right = stack.pop();
				ExpNode left = stack.pop();
				ExpNode node = CompoundNode.make(token,left, right);
				stack.push(node);
			}
			// if is an ID push a new Node to the stack;
			else
			{
				String boolToken = token.toLowerCase();
				if (boolToken.equals("true") || boolToken.equals("false"))
					stack.push(new ConstantNode(token));
				else
				{
					stack.push(new IdentifierNode(token));
					table.add(token);
				}
			}
		}
		root = stack.pop();
	}

	/**
	Eval the expression given a state

	@param state The state of each boolean variable

	@return The result of the evaluation
	*/
	public boolean eval(Map<String, Boolean> state)
	{
		return root.eval(state);
	}

	/**
	The representation of the expression tree in infix notation
	*/
	public String toString()
	{
		return root.toString();
	}

	/**
	@return The symbol table of the expression
	*/
	public LinkedHashSet<String> getTable()
	{
		return new LinkedHashSet<String>(table);
	}
}