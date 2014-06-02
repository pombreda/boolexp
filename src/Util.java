import java.util.Vector;
import java.util.Stack;
import java.util.Scanner;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
Utility functions to manipulate infix boolean expressions
*/
public class Util
{
	/** 
	Takes a int representing the boolean state and the symbol table of the boolean function
	
	@param code_bool Each bit represents a boolean operand
	@param table The symbols of the boolean function

	@return A state to the boolean function (symbol, boolean)
	*/
	public static LinkedHashMap<String, Boolean> decode(int code_bool, LinkedHashSet<String> table)
	{
		int size = table.size();
		LinkedHashMap<String, Boolean> state = new LinkedHashMap<String, Boolean> (size);
		Iterator<String> table_iterator = table.iterator();
		for(int i = size - 1; i >= 0; --i)
		{
			String id = table_iterator.next();
			// Shift each bit and applie logical AND to generate respective symbol boolean
			boolean value = ((code_bool >> i) & 0x1) == 1;
			state.put(id, value);
		}
		return state;
	}

	/**
	The precedence of the operator

	@return A integer. The bigger the precedence the bigger the value
	*/
	private static int precedence(String token)
	{
		if(token.equals(BooleanOperators.NOT))
			return 4;
		if(token.equals(BooleanOperators.AND))
			return 3;
		if(token.equals(BooleanOperators.OR))
			return 2;
		if(token.equals(BooleanOperators.IMPLIES))
			return 1;
		return 0;	
	}
	
	/**
	Converts a boolean expression from infix to postfix

	@param infix A non parsed string with the infix expression

	@return A vector of strings, ordered by postfix notation.
	*/
	public static Vector<String> InfixToPostfix(String infix)
	{
		Stack<String> operators = new Stack<String>();
		Vector<String> postfix = new Vector<String>();

		// Parse the string. While there's a token, process the token
		Matcher matcher = BooleanOperators.pattern.matcher(infix);
		while(matcher.find())
		{
			int start = matcher.start();
			int end = matcher.end();
			String token = infix.substring(start, end);
			processToken(token, operators, postfix);
		}

		// If there are no more tokens pop all the stack to the output
		while(!operators.empty()) postfix.add(operators.pop());

		return postfix;
	}

	/**
	This function helds the main portion of the algorithm InfixToPostfix.
	Process each token.
		If operand, put the token in the output vector.
 		If operator, pop until find a operator of lower precedence, then push the token.
 		If ")" pop until find a matching "("

	@token A token
	@operators The operators stack
	@postfix The vector of tokens
	*/
	private static void processToken(String token, Stack<String> operators, Vector<String> postfix)
	{		
		if(token.matches("\\p{Alpha}+")) // If is an ID puts it on the vector
		{
				postfix.add(token);
		}		
		else if(token.equals(")")) // If ")" pop until find matching "("
		{
			while(!operators.empty())
			{
				String top = operators.pop();
				if(top.equals("("))
					break;
				postfix.add(top);
			}
		}
		else if(token.equals("("))
		{
			operators.push(token);
		}
		else// if is an operator token pop until find operator of lower precedence, then push
		{
			while (!operators.empty() && !operators.peek().equals('(')) 
			{
				String top = operators.peek();
				if (precedence(top) < precedence(token))
					break;
				postfix.add(operators.pop());
			}
			operators.push(token);
		}
	}
}
