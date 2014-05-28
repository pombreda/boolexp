public class Node<T>
{
	public Node<T> next;
	public T value;
	public Node() { next = null; }
	public Node(T value) { this.value = value; next = null; }
}