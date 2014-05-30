import java.util.Vector;
import java.util.Stack;
import java.util.Scanner;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util
{
	public static LinkedHashMap<String, Boolean> decode(int code_bool, LinkedHashSet<String> table)
	{
		int size = table.size();
		LinkedHashMap<String, Boolean> state = new LinkedHashMap<String, Boolean> (size);
		Iterator<String> table_iterator = table.iterator();
		for(int i = size - 1; i >= 0; --i)
		{
			String id = table_iterator.next();
			boolean val = ((code_bool >> i) & 0x1) == 1;
			//vect_b.add(val);
			//vect_b.add(val);
			state.put(id, val);
		}
		return state;
	}
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
	

	// se operando - coloca na lista
	// se operador
	//    pop na pilha até achar operador de menor precedencia ou pilha vazia
	//    push operador
	// se nao houver mais entradas colocar o conteúdo da pilha na lista
	public static Vector<String> InfixToPostfix(String infix)
	{
		Stack<String> operators = new Stack<String>();
		Vector<String> postfix = new Vector<String>();

		//Pattern pattern = Pattern.compile(BooleanOperators.patternString);
		Matcher matcher = BooleanOperators.pattern.matcher(infix);
		while(matcher.find())
		{
			int start = matcher.start();
			int end = matcher.end();
			String token = infix.substring(start, end);
			processToken(token, operators, postfix);
		}
		while(!operators.empty())
			// System.out.print(operators.pop() + " ");
			postfix.add(operators.pop());
		// System.out.println();
		return postfix;
	}

	private static void processToken(String token, Stack<String> operators, Vector<String> postfix)
	{			
		if(token.matches("\\p{Alpha}+"))
		{
				postfix.add(token);
		}
		else if(token.equals(")"))
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
		else
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
