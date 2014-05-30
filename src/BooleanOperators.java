import java.util.regex.Pattern;
public class BooleanOperators
{
	public static final String NOT = "!";
	public static final String AND = "&";
	public static final String OR = "|";
	public static final String IMPLIES = "->";
	public static final String patternString;// = "\\!|\\&|\\-\\>|[\\(\\)]|\\p{Alpha}+";
	public static final Pattern pattern;
	
	static
	{
		patternString = 
			Pattern.quote(NOT)		+ "|" + 
			Pattern.quote(AND)		+ "|" + 
			Pattern.quote(OR)		+ "|" + 
			Pattern.quote(IMPLIES)	+ "|" + 
			"\\p{Alpha}+";
		pattern = Pattern.compile(patternString);
	}
}