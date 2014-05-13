public class Stack<T>
{
	private Node<T> head;
	private Node<T> z;

	public Stack()
	{
		head = new Node<T>();
		z = new Node<T>();
		head.next = z;
		z.next = z;
	}
	public void add(T value)
	{
		Node<T> node = new Node<T>();
		node.value = value;
		node.next = head.next;
		head.next = node;
	}
	public T pop()
	{
		Node<T> node = head.next;
		head.next = node.next;
		return node.value;
	}
	public void clean()
	{
		while(head.next != z)
			pop();
	}

	public boolean isEmpty()
	{
		return head.next == z;
	}

	public static void main(String args[])
	{
		Stack<Integer> test = new Stack<Integer>();
		for(int i = 0; i < 10; i++)
		{
			System.out.println("Adding: "+ i);
			test.add(i);
		}
		while(!test.isEmpty())
			System.out.println("Popping: "+ test.pop());
	}
}