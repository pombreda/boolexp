import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;
/**
Evaluate the satisfiability of a boolean expression
*/
public class SAT
{
	/**
	Given a Expression Tree, evaluate all of its possible states and prints the truth table

	@param tree A Boolean Expression Tree
	*/
	public static void evalStates(BoolExpTree tree)
	{
		LinkedHashSet<String> table = tree.getTable();
		boolean sat = false; // satisfiability
		boolean fal = false; // falseability

		printTable(table);
		System.out.println();
		for(int i = 0; i < Math.pow(2, table.size()); i++)
		{
			HashMap<String, Boolean> state = Util.decode(i, table);
			boolean eval = tree.eval(state);

			printValues(state, eval);

			sat = sat || eval;
			fal = fal || ! eval;
			
			if(sat && fal)
			{
				break;
			}
		}
		System.out.println();
		if(sat)
		{
			System.out.println("Satisfeita");
			if(!fal) System.out.println("Tautologia");
		}
		if(fal)
		{
			System.out.println("Falsificável");
			if (!sat) System.out.println("Contradição");
		}
	}

	/**
	Print values of table

	@param table List of symbols
	*/
	public static void printTable(LinkedHashSet<String> table)
	{
		StringBuilder out = new StringBuilder();
		String delim = "";
		for(String symbol : table)
		{
			out.append(delim);
			out.append(symbol);

			delim = "|";
		}
		System.out.println(out);
	}

	/**
	Print states
	
	@param state Map of symbols and boolean values
	@param eval Result of evaluation for the state
	*/
	public static void printValues(HashMap<String, Boolean> state, boolean eval)
	{
		StringBuilder out = new StringBuilder();
		String delim = "";
		for(Entry<String, Boolean> entry : state.entrySet())
		{
			out.append(delim);
			int padding = entry.getKey().length() - 1;
			if(padding > 0)
				out.append(String.format("%" + padding +"s", " "));
			out.append(entry.getValue() ? 'T' : 'F');

			delim = "|";
		}
		out.append("\t");
		out.append(eval ? 'T' : 'F');
		System.out.println(out);
	}
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Entre com a expressão booleana: ");
		String input = in.nextLine();
		BoolExpTree tree = new BoolExpTree(Util.InfixToPostfix(input));
		System.out.println("\nTabela verdade:");
		evalStates(tree);
	}
}
