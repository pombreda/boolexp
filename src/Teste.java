import java.util.Scanner;
import java.util.Vector;
import java.util.EmptyStackException;

class Stack<T>
{
	private Node head;
	private Node z;

	private class Node
	{
		public Node next;
		public T value;
	}

	public Stack()
	{
		head = new Node();
		z = new Node();
		head.next = z;
		z.next = z;
	}
	public void push(T value)
	{
		Node node = new Node();
		node.value = value;
		node.next = head.next;
		head.next = node;
	}
	public T pop()
	{
		if(head.next == z)
			throw new EmptyStackException();

		Node node = head.next;
		head.next = node.next;
		return node.value;
	}
	public void clean()
	{
		while(head.next != z)
			pop();
	}

	public boolean empty()
	{
		return head.next == z;
	}

	// public static void main(String args[])
	// {
	// 	Stack<Integer> test = new Stack<Integer>();
	// 	for(int i = 0; i < 10; i++)
	// 	{
	// 		System.out.println("Adding: "+ i);
	// 		test.add(i);
	// 	}
	// 	try{
	// 		while(true)
	// 			System.out.println("Popping: "+ test.pop());
	// 	}catch(EmptyStackException e) { }
	// }
}
public class Teste
{
	private static final String TOKEN_NOT = "NOT";
	private static final String TOKEN_AND = "AND";
	private static final String TOKEN_IMPLIES = "IMPLIES";

	public static void main(String args[])
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter expression: ");
		
		Scanner infix = new Scanner(input.nextLine());
		
		Stack<String> stack = new Stack<String>();
		Vector<String> postfix = new Vector<String>();
		
		while(infix.hasNext())
		{
			if(infix.hasNext(TOKEN_NOT))
				System.out.println(infix.next(TOKEN_NOT));
			else if(infix.hasNext(TOKEN_AND))
				System.out.println(infix.next(TOKEN_AND));
			else if(infix.hasNext(TOKEN_IMPLIES))
				System.out.println(infix.next(TOKEN_IMPLIES));
			else System.out.println(infix.next());
		}

		
		String result = "";
		for(int i = 0; i < postfix.size(); i++)
			result += postfix.get(i);
		System.out.println("Postfix: "+ result);
	}
}