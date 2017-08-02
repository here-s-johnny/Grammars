package gramatyka;

public class ContextFreeGrammar extends Grammar {
	
	private static int calculateProductions(String[][] arg)
	{
		int counter = 0;
		for (int i = 0; i < arg.length; ++i)
		{
			counter += arg[i].length;
		}
		return counter;
	}
	
	public ContextFreeGrammar(String terminals, String nonterminals, String[][] productions)
	throws WrongParametersException
	{
		char[] tmp = new char[terminals.length()];
		char[] tmp2 = new char[nonterminals.length()];
		Production[] tmp3 = new Production[calculateProductions(productions)]; 

		// setting terminals and checking if they are defined correctly
		int i = 0;
		while (i < terminals.length())
		{
			if (terminals.charAt(i) >= 'a' && terminals.charAt(i) <= 'z')
			{
				tmp[i] = terminals.charAt(i);
			} else
			{
				throw new WrongParametersException();
			}
			++i;
		}
		this.terminals = new Symbols(tmp);
		if (!this.terminals.ifCorrect())
		{
			throw new WrongParametersException();
		}

		// setting nonterminals and checking if they are defined correctly
		i = 0;
		while (i < nonterminals.length())
		{
			if (nonterminals.charAt(i) >= 'A' && nonterminals.charAt(i) <= 'Z')
			{
				tmp2[i] = nonterminals.charAt(i);
			} else
			{
				throw new WrongParametersException();
			}
			++i;
		}
		this.nonterminals = new Symbols(tmp2);
		if (!this.nonterminals.ifCorrect())
		{
			throw new WrongParametersException();
		}

//		 setting productions and checking if they are defined correctly
		if (productions.length > nonterminals.length())
		{
			throw new WrongParametersException();
		}
		
		int k = 0;
        for (i = 0; i < productions.length; ++i)
        {
        	for (int j = 0; j < productions[i].length; ++j)
        	{      		
        		tmp3[k] = new Production(nonterminals.charAt(i), productions[i][j]);
        		++k;
        	}
        }
        this.productions = new ProductionsArray(tmp3, this.terminals, this.nonterminals);
        if (!this.productions.ifCorrect())
        {
        	throw new WrongParametersException();
        }    
        
        // setting type and form
        if (this.ifRegular())
        {
        	type = "regularna";
        } else
        {
        	type = "bezkontekstowa";
        }
        if (this.ifGreibach())
        {
        	form = "Greibach";
        } else if (this.ifChomsky())
        {
        	form = "Chomsky";
        } else 
        {
        	form = "";
        }
    
	}
}