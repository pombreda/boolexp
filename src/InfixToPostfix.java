import java.util.LinkedList;
import java.util.Stack;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfixToPostfix {
	
	public static final String NOT = "!";
	public static final String AND = "&";
	public static final String OR = "|";
	public static final String IMPLIES = "->";
	public static final String patternString = "\\!|\\&|\\-\\>|[\\(\\)]|\\p{Alpha}+";
	
	private static int precedence(String token)
	{
		if(token.equals(NOT))
			return 5;
		if(token.equals(AND))
			return 4;
		if(token.equals(OR))
			return 3;
		if(token.equals(IMPLIES))
			return 2;
		return 0;	
	}
	

	// se operando - coloca na lista
	// se operador
	//    pop na pilha até achar operador de menor precedencia ou pilha vazia
	//    push operador
	// se nao houver mais entradas colocar o conteúdo da pilha na lista
	public static LinkedList<String> process(String input)
	{
		Stack<String> operators = new Stack<String>();
		LinkedList<String> postfix = new LinkedList<String>();

		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(input);
		while(matcher.find())
		{
			int start = matcher.start();
			int end = matcher.end();
			String token = input.substring(start, end);
			processToken(token, operators, postfix);
		}
		while(!operators.empty())
			// System.out.print(operators.pop() + " ");
			postfix.add(operators.pop());
		// System.out.println();
		return postfix;
	}

	private static void processToken(String token, Stack<String> operators, LinkedList<String> postfix)
	{			
		if(token.matches("\\p{Alpha}+"))
		{
				// System.out.print(token + " ");
				postfix.add(token);
		}
		else if(token.equals(")"))
		{
			while(!operators.empty())
			{
				String top = operators.pop();
				if(top.equals("("))
					break;
				// System.out.print(top + " ");
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
				// System.out.print(operators.pop() + " ");
				postfix.add(operators.pop());
			}
			operators.push(token);
		}
	}
}
