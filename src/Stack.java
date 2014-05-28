import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class Stack<T> implements Iterable<T>
{
	private Node<T> head;
	private int N;

	public Stack()
	{
		head = null;
		N = 0;
	}
	public void push(T value)
	{
		Node<T> node = new Node<T>(value);
		node.next = head;
		head = node;
		++N;
	}
	public T pop()
	{
		if(empty())
			throw new EmptyStackException();
		--N;
		T value = head.value;
		head = head.next;
		return value;
	}
	public void clean()
	{
		head = null;
		N = 0;
	}

	public boolean empty()
	{
		return head == null;
	}

	public int size()
	{
		return N;
	}
	
	public T top()
	{
		if(head == null)
			throw new EmptyStackException();
		return head.value;
	}

    public Iterator<T> iterator()  {
        return new ListIterator<T>(head);
    }

    private class ListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public ListIterator(Node<T> head) {
            current = head;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public T next()
        {
            if (!hasNext()) throw new NoSuchElementException();
            T value = current.value;
            current = current.next; 
            return value;
        }
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
