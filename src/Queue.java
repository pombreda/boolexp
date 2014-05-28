import java.util.NoSuchElementException;
import java.util.Iterator;

public class Queue<T> implements Iterable<T>
{
	private Node<T> first;
	private Node<T> last;
	private int N;

	public Queue()
	{
		first = null;
		last = null;
		N = 0;
	}

	public void enqueue(T value)
	{
		Node<T> node = new Node<T>(value);
		if(empty())
		{
			first = last = node;
		}
		else
		{
			last.next = node;
			last = node;
		}
		++N;
	}

	public T dequeue()
	{
		if(empty())	throw new NoSuchElementException();
		T value = first.value;
		first = first.next;
		--N;
		if(empty())	last = null;
		return value;
	}

	public boolean empty()
	{
		return first == null;
	}

	public int size()
	{
		return N;
	}
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        for (T value : this)
            s.append(value + " ");
        return s.toString();
    } 

    public Iterator<T> iterator()  {
        return new ListIterator<T>(first);
    }

    private class ListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public ListIterator(Node<T> first) {
            current = first;
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
}
