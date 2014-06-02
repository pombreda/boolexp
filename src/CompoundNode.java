import java.util.Map;

/**
Compound Nodes represents operands and always contain children nodes.

TODO: Use arbitrary number of children
*/
public abstract class CompoundNode implements ExpNode
{
	/**
	Factory method for creating Nodes for each operator token

	@param operator Token from the expression
	@param left Left child node
	@param right Right child node

	@return A subclass of ExpNode
	*/
	public static ExpNode make(String operator, ExpNode left, ExpNode right)
	{
		if(operator.equals(BooleanOperators.NOT))
			return new NotNode(left);
		else if(operator.equals(BooleanOperators.OR))
			return new OrNode(left, right);
		else if(operator.equals(BooleanOperators.AND))
			return new AndNode(left, right);
		else if(operator.equals(BooleanOperators.IMPLIES))
			return new ImpliesNode(left, right);
		throw new IllegalArgumentException("Unkown operator '" + operator + "'");

	}
}

class NotNode extends CompoundNode
{
	private ExpNode node;
	public NotNode(ExpNode node)
	{
		this.node = node;
	}

	public boolean eval(Map<String, Boolean> state)
	{
		return !node.eval(state);
	}

	public String toString()
	{
		return "(" + BooleanOperators.NOT + node.toString() + ")";
	}
}

class AndNode extends CompoundNode
{
	private ExpNode left;
	private ExpNode right;

	public AndNode(ExpNode left, ExpNode right)
	{
		this.left = left;
		this.right = right;
	}

	public boolean eval(Map<String, Boolean> state)
	{
		return left.eval(state) && right.eval(state);
	}

	public String toString()
	{
		return "(" + left.toString() + " "+ BooleanOperators.AND + " "+ right.toString() + ")";
	}
}

class OrNode extends CompoundNode
{
	private ExpNode left;
	private ExpNode right;

	public OrNode(ExpNode left, ExpNode right)
	{
		this.left = left;
		this.right = right;
	}
	
	public boolean eval(Map<String, Boolean> state)
	{
		return left.eval(state) || right.eval(state);
	}

	public String toString()
	{
		return "(" + left.toString() + " "+ BooleanOperators.OR + " "+ right.toString() + ")";
	}
}

class ImpliesNode extends CompoundNode
{
	private ExpNode left;
	private ExpNode right;

	public ImpliesNode(ExpNode left, ExpNode right)
	{
		this.left = left;
		this.right = right;
	}
	
	public boolean eval(Map<String, Boolean> state)
	{
		return !left.eval(state) || right.eval(state);
	}

	public String toString()
	{
		return "(" + left.toString() + " "+ BooleanOperators.IMPLIES + " "+ right.toString() + ")";
	}
}