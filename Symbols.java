package gramatyka;

public class Symbols {
	
	private char[] symbols;
	
	public Symbols(char[] arg)
	{
		symbols = arg;
	}
	
	public char[] getSymbols()
	{
    	return symbols;
    }
	
	public boolean ifCorrect()
	//checks if there are any symbols at all and if there are no repetitions
	{
		if (symbols.length == 0)
		{
			return false;
		}
		for (int i = 1; i < symbols.length; ++i)
		{
			for (int j = 0; j < i; ++j)
			{
				if (symbols[i] == symbols[j])
				{
					return false;
				}
			}
		}
		return true;
	}
	 
}
