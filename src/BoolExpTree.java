import java.util.*;

public class BoolExpTree
{
	private ExpNode root;
	private LinkedHashSet<String> table;

	public BoolExpTree(Vector<String> postfix)
	{
		table = new LinkedHashSet<String>();
		Stack<ExpNode> stack = new Stack<ExpNode>();
		for(String token : postfix)
		{
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

	public boolean eval(Map<String, Boolean> state)
	{
		return root.eval(state);
	}

	public String toString()
	{
		return root.toString();
	}

	public LinkedHashSet<String> getTable()
	{
		return new LinkedHashSet<String>(table);
	}
}