import java.util.Map;

public class ConstantNode implements ExpNode 
{
	public final boolean value;
	public ConstantNode(boolean value)
	{
		this.value = value;
	}

	public boolean eval(Map<String, Boolean> table)
	{
		return value;
	}

	public String toString()
	{
		return Boolean.toString(value);
	}
}