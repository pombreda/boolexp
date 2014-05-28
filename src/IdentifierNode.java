import java.util.Map;

public class IdentifierNode implements ExpNode
{
	public final String name;
	
	public IdentifierNode(String name)
	{
		this.name = name;
	}

	public boolean eval(Map<String, Boolean> state)
	{
		return state.get(name);
	}

	public String toString()
	{
		return this.name;
	}

}