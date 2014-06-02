import java.util.Map;

/**
A interface for Boolean Expression Nodes
*/
interface ExpNode
{
	/**
	Eval the node given a state

	@param state The state of each boolean variable

	@return The result of the evaluation
	*/
	boolean eval(Map<String, Boolean> state);

	/**
	The string representation of the expression, in infix notation.
	*/
	String toString();
}