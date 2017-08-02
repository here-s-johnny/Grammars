package gramatyka;

public class ProductionsArray {
	
	private Production[] productions;
	private Symbols terminals;
	private Symbols nonterminals;
	
	public ProductionsArray(Production[] productions, Symbols terminals, Symbols nonterminals)
	{
		this.productions = productions;
		this.terminals = terminals;
		this.nonterminals = nonterminals;
		
	}
	
	public Production[] getProductions()
	{
		return productions;
	}
	
	public boolean ifCorrect()
	// checks if each productions consists of given symbols
	{
		boolean tmp = false;
		for (Production p : productions)
		{
			for (int i = 0; i < p.getRhs().length(); ++i)
			{
				tmp = false;
				if (p.getRhs().charAt(i) >= 'a' && p.getRhs().charAt(i) <= 'z')
				{
					for (int j = 0; j < terminals.getSymbols().length && !tmp; ++j)
				    {
						if (p.getRhs().charAt(i) == terminals.getSymbols()[j])
						{
							tmp = true;
						}
				    }
				}else
				{
					for (int j = 0; j < nonterminals.getSymbols().length && !tmp; ++j)
				    {
						if (p.getRhs().charAt(i) == nonterminals.getSymbols()[j])
						{
							tmp = true;
						}
				    }	
				}
			}
			if (!tmp && p.getRhs().length() > 0)
			{
				return false;
			}
		}
		return true;
	}
	
}
