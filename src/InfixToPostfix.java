import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfixToPostfix {
	private Queue<String> postfix;
	private Stack<String> operators;
	
	private final String NOT = "!";
	private final String AND = "&";
	private final String OR = "|";
	private final String IMPLIES = "->";
	private final String patternString = "\\!|\\&|\\-\\>|[\\(\\)]|\\p{Alpha}+";
	
	public InfixToPostfix() 
	{
		operators = new Stack<String>();
		postfix = new Queue<String>();
	}

	private int precedence(String token)
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
	

	// se operand - print
	// se operator
	//    pop ateh achar menor precedencia ou vazio
	//    push operator
	// se nao houver mais entrada, pop ate o fim
	private Queue<String> process(String input)
	{
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(input);
		while(matcher.find())
		{
			int start = matcher.start();
			int end = matcher.end();
			String token = input.substring(start, end);
			processToken(token);
		}
		while(!operators.empty())
			// System.out.print(operators.pop() + " ");
			postfix.enqueue(operators.pop());
		// System.out.println();
		return postfix;
	}

	private void processToken(String token)
	{			
		if(token.matches("\\p{Alpha}+"))
		{
				// System.out.print(token + " ");
				postfix.enqueue(token);
		}
		else if(token.equals(")"))
		{
			while(!operators.empty())
			{
				String top = operators.pop();
				if(top.equals("("))
					break;
				// System.out.print(top + " ");
				postfix.enqueue(top);
			}
		}
		else if(token.equals("("))
		{
			operators.push(token);
		}
		else
		{
			while (!operators.empty() && !operators.top().equals('('))
			{
				String top = operators.top();
				if (precedence(top) < precedence(token))
					break;
				// System.out.print(operators.pop() + " ");
				postfix.enqueue(operators.pop());
			}
			operators.push(token);
		}
	}

	public static void main(String args[])
	{		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter boolean expression: ");
		String input = in.nextLine();

		InfixToPostfix o = new InfixToPostfix();
		System.out.println(o.process(input));
	}
}
