import java.util.Map;

/**
A node for constant booleans
*/
public class ConstantNode implements ExpNode 
{
	public final boolean value;
	
	/**
	Constructor from a boolean

	@param value A boolean constant
	*/	
	public ConstantNode(boolean value)
	{
		this.value = value;
	}

	/**
	Constructor from a string
	
	@param value A string representing a boolean. Case is ignored.
	*/	
	public ConstantNode(String value)
	{
		this.value = new Boolean(value);
	}
	
	public boolean eval(Map<String, Boolean> state)
	{
		return value;
	}

	public String toString()
	{
		return Boolean.toString(value);
	}
}