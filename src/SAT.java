import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.Scanner;

public class SAT
{
	public static void testeStates(BoolExpTree tree)
	{
		LinkedHashSet<String> table = tree.getTable();
		boolean tautology = true;
		boolean sat = false;
		for(int i = 0; i < Math.pow(2, table.size()); i++)
		{
			HashMap<String, Boolean> state = Util.decode(i, table);
			boolean eval = tree.eval(state);

			System.out.print("State: ");
			System.out.println(state);

			System.out.print("Eval: ");
			System.out.println(eval);
			sat = sat || eval;
			tautology = tautology && eval;
		}
		if(tautology)
		{
			System.out.println("Tautologia");
		}else if(sat)
		{
			System.out.println("Satisfeita");
		}
		else
		{
			System.out.println("Contradição");
		}
	}

	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter boolean expression: ");
		String input = in.nextLine();
		BoolExpTree tree = new BoolExpTree(Util.InfixToPostfix(input));
		testeStates(tree);
	}
}