import java.util.EmptyStackException;

public class Stack<T>
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