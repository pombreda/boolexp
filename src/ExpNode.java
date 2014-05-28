import java.util.Map;

interface ExpNode
{
	boolean eval(Map<String, Boolean> state);
	String toString();
}