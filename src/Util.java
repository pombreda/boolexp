import java.util.Vector;

public class Util
{
	static Vector<Boolean> decode(int code_bool, int size)
	{
		Vector<Boolean> vect_b = new Vector<Boolean>(size);
		for(int i = size - 1; i >= 0; --i)
		{
			boolean val = ((code_bool >> i) & 0x1) == 1;
			//vect_b.add(val);
			vect_b.add(val);
		}
		return vect_b;
	}
	
	public static void main(String args[])
	{
		int valor = 0b11110;
		Vector<Boolean> teste = decode(valor, 5);
		
		System.out.println(teste);
	}
}
