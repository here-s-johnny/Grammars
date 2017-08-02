package gramatyka;

public abstract class Grammar {

	protected Symbols terminals;
	protected Symbols nonterminals;
	protected ProductionsArray productions;
	protected String type;
	protected String form;
	
	public boolean ifRegular() 
	// flag holds information about the type of production acceptable:
	// flag = 1 means that C -> cD was first found, hence C -> Dc is not acceptable
	// flag = -1 means the reverse situation
	// flag = 0 means that no choice has yet been made
	{
		int flag = 0;
		for (Production p : productions.getProductions())
		{
			String rhs = p.getRhs();
			if (flag == 0) 
			// we're checking if each production meets one of the three conditions;
			// if the first two are not met ( -> & or -> a) we're checking if a version
			// of the third is met, if so - we're setting the flag correctly
			{
				if (!(rhs.length() == 0 || 
				     (rhs.length() == 1 && Character.isLowerCase(rhs.charAt(0)))))
				{
					if (rhs.length() == 2 && 
					    Character.isLowerCase(rhs.charAt(0)) && Character.isUpperCase(rhs.charAt(1)))
					{
						flag = 1;
					} else if (rhs.length() == 2 && 
						    Character.isLowerCase(rhs.charAt(1)) && 
						    Character.isUpperCase(rhs.charAt(0)))
					{
						flag = -1;
					} else
					{
						return false;
					}
				}

			} else if (flag == 1)
			// we're checking the production for the three conditions given by the
			// definition of regular grammar and determined by the flag
			{
				if (!(rhs.length() == 0 || 
				     (rhs.length() == 1 && Character.isLowerCase(rhs.charAt(0)) ||
				     (rhs.length() == 2 && Character.isLowerCase(rhs.charAt(0)) && 
				      Character.isUpperCase(rhs.charAt(1))))))
				{
				 	return false;
			    }
			} else if (flag == -1)
			{
				if (!(rhs.length() == 0 || 
					 (rhs.length() == 1 && Character.isLowerCase(rhs.charAt(0)) ||
					 (rhs.length() == 2 && Character.isLowerCase(rhs.charAt(1)) && 
					  Character.isUpperCase(rhs.charAt(0))))))
				{
				 	return false;
			    }
			}
		}
		return true;
	}

	public boolean ifChomsky() {
		for (Production p : productions.getProductions())
		{
			String rhs = p.getRhs();
			if (!(rhs.length() == 1 && Character.isLowerCase(rhs.charAt(0)) ||
				 (rhs.length() == 2 && Character.isUpperCase(rhs.charAt(0)) &&
				  Character.isUpperCase(rhs.charAt(1)))))
			{
				return false;
			}
		}
		return true;
	}

	public boolean ifGreibach() {
		for (Production p : productions.getProductions())
		{
			String rhs = p.getRhs();
			if (rhs.length() == 0)
			{
				return false;
			}
			if (Character.isUpperCase(rhs.charAt(0)))
			{
				return false;
			} else
			{
				int i = 1;
				while (i < rhs.length()) 
				{
					if (Character.isLowerCase(rhs.charAt(i)))
					{
						return false;
					}
					++i;
				}
			}
		}
		return true;
	}
	
	public String toString()
	{
		String tmp = "Gramatyka: " + type + "/" + form + "\nTerminale: ";
		int i = 0;
		while (i < terminals.getSymbols().length)
		{
			tmp += terminals.getSymbols()[i];
			++i;
		}
		tmp += "\nNieterminale: ";
		i = 0;
		while (i < nonterminals.getSymbols().length)
		{
			tmp += nonterminals.getSymbols()[i];
			++i;
		}
		tmp += "\nProdukcje:\n";
		i = 0;
		while (i < productions.getProductions().length)
		{
			tmp += productions.getProductions()[i] + "\n";
			++i;
		}
		return tmp;
	}

}
