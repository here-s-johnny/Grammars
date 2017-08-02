package gramatyka;

public class Production {

	private char lhs;
	private String rhs;
	
	public Production(char lhs, String rhs)
	{
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	public char getLhs()
	{
		return lhs;
	}
	
	public String getRhs()
	{
		return rhs;
	}
	
	public String toString()
	{
		String tmp = lhs + " -> ";
		if (rhs == "")
		{
			tmp += "&";
		} else
		{
			tmp += rhs;
		}
		return tmp;
	}
	
}

