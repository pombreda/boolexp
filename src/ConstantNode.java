import java.util.Map;

public class ConstantNode implements ExpNode 
{
	public final boolean value;
	
	public ConstantNode(boolean value)
	{
		this.value = value;
	}

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