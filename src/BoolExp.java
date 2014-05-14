import java.util.LinkedHashSet;
import java.util.Hashtable;
import java.lang.Iterable;
import java.util.Iterator;
import java.util.Vector;

public class BoolExp
{
	private static final String TOKEN_NOT = "NOT";
	private static final String TOKEN_AND = "AND";
	private static final String TOKEN_IMPLIES = "IMPLIES";

	public Node root;

	public BoolExp()
	{
		
	}

	public void traverse()
	{
		root.traverse();
	}

	public boolean eval()
	{
		return root.eval();
	}

	private String postfix(String infix)
	{
		Stack<String> stack = new Stack<String>();
		Vector<String> postfix = new Vector<String>();
		
		while(!infix.equals(""))
		{
			if(infix.startsWith(TOKEN_AND))
			{
				infix = infix.replaceFirst(TOKEN_AND, "");
				postfix.add(stack.pop());
				postfix.add(stack.pop());
				stack.push(TOKEN_AND);
			}
			else if(infix.startsWith(TOKEN_IMPLIES))
			{
				infix = infix.replaceFirst(TOKEN_IMPLIES, "");
				postfix.add(stack.pop());
				postfix.add(stack.pop());
				stack.push(TOKEN_IMPLIES);
			}
			else if(infix.startsWith(TOKEN_NOT))
			{
				infix = infix.replaceFirst(TOKEN_AND, "");
				postfix.add(stack.pop());
				stack.push(TOKEN_NOT);
			}
			else
				stack.push();
			infix = infix.trim();
		}
		return postfix;
	}

	private class Node {
		public Node l;
		public Node r;
		public String token;

		public void traverse()
		{
			if(l != null) l.traverse();
			System.out.print(token);
			if(r != null) r.traverse();
		}

		public boolean eval( ) // Result r )
		{
			boolean result;
			if(token.equals(TOKEN_AND))		result =  l.eval() && r.eval();	else
			if(token.equals(TOKEN_IMPLIES))	result = !l.eval() || r.eval();	else
			if(token.equals(TOKEN_NOT))		result = !l.eval();				else
				result = false;
				// result = r[token];

			return result;

		}
	}

	private class Operands implements Iterable<Hashtable<String, Boolean>>
	{
		private LinkedHashSet<String> operands;
		
		public Operands()
		{
			operands = new LinkedHashSet<String>();
		}

		public void add(String operand)
		{
			operands.add(operand);
		}

		public Iterator<Hashtable<String, Boolean>> iterator()
		{
			return new MyIterator();
		}

		private class MyIterator implements Iterator<Hashtable<String, Boolean>>
		{
			int current_solution;

			public MyIterator()
			{
				current_solution = -1;
			}

			@Override
			public boolean hasNext()
			{
				return current_solution < operands.size();
			}

			@Override
			public Hashtable<String, Boolean> next()
			{
				Hashtable<String, Boolean> solution = new Hashtable<String, Boolean>(operands.size());

				return solution;
			}
			@Override
			public void remove() {};
		}

	}
}