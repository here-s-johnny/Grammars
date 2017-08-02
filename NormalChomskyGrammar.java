package gramatyka;

import java.util.*;

public class NormalChomskyGrammar extends Grammar {
	
	public NormalChomskyGrammar(ContextFreeGrammar grammar) throws WrongParametersException
	{
		if (!grammar.ifChomsky())
		{
			throw new WrongParametersException();
		} else
		{
			this.terminals = grammar.terminals;
			this.nonterminals = grammar.nonterminals;
			this.productions = grammar.productions;
			this.type = grammar.type;
			this.form = "Chomsky";
		}
	}
	
	@Override
	public boolean ifChomsky() 
	{
		return true;
	}
	
	public NormalGreibachGrammar toGreibach() throws WrongParametersException
	// this method is based on the algorithm given in moodle discussion
	
	// this method consists of three parts - preparation, first step, second step;
	// most of each part is outsourced to separate methods for readability 
	{
		// preparation
		int n = this.nonterminals.getSymbols().length;
		String tmpNonterminals = "";
		tmpNonterminals = prepToGreibach(tmpNonterminals,n);
		String[][] tmpProductions = new String[2*n][];
		
		// first step
		tmpProductions = stepOne(tmpProductions,tmpNonterminals,n);

		//second step
		tmpProductions = stepTwo(tmpProductions, tmpNonterminals,n);
		
		String tmpTerminals = "";
		for (int i = 0; i < this.terminals.getSymbols().length; ++i)
		{
			tmpTerminals += this.terminals.getSymbols()[i];
		}
		
		ContextFreeGrammar g = new ContextFreeGrammar(tmpTerminals, tmpNonterminals, tmpProductions);
		return new NormalGreibachGrammar(g);	
	}

	
	private String prepToGreibach(String tmp, int n)
	{
		for (int i = 0; i < n; ++i)
		{
			tmp += this.nonterminals.getSymbols()[i];
		}
		for (int i = 0; i < n; ++i)
		{
			char c = 'A';
			boolean readyToAdd = false;
			while (!readyToAdd) 
			{
				int j = 0;
				while (j < tmp.length() && c != tmp.charAt(j)) 
				{
						++j;
				}
				if (j == tmp.length()) 
				{
					readyToAdd = true;
				} else
				{
					++c;
				}
			}
			tmp = c + tmp;
		}
		return tmp;
	}
	
	private String[][] stepOne(String[][] tmpProductions, String tmpNonterminals, int n)
	{
		int start = 0;
		for (int i = n; i < 2*n; ++i) 
		{
			List<String> XProductions = new ArrayList<String>();
			List<String> PProductions = new ArrayList<String>();
			boolean OK = false;
			
			int count = 0;
			for (int j = 0; j < this.productions.getProductions().length; ++j)
			{
				if (this.productions.getProductions()[j].getLhs() == this.nonterminals.getSymbols()[i-n])
				{
					++count;
				}
			}
			String[] tmpArray = new String[count];
			for (int j = 0; j < count; ++j)
			{
				tmpArray[j] = this.productions.getProductions()[start +j].getRhs();
			}
			start += count - 1;
			tmpProductions[i] = tmpArray;
			
			while (!OK) 
			{
				for (int j = 0; j < tmpProductions[i].length; ++j) 
				{
					String tmp = tmpProductions[i][j];
					if (tmp.length() == 1) 
					{
						XProductions.add(tmp);
					} else 
					{  
						int k = position(tmp.charAt(0), tmpNonterminals);
						if (k < i) 
						{
							for (int l = 0; l < tmpProductions[k].length; ++l) 
							{
								XProductions.add(tmpProductions[k][l] + tmp.substring(1));
							}
						} else 
						{
							XProductions.add(tmp);
						}
					}	
				}
				
				String[] XProductionsArray = new String[XProductions.size()];
				for (int j = 0; j < XProductionsArray.length; ++j) 
				{
					XProductionsArray[j] = XProductions.get(j);
				}
				tmpProductions[i] = XProductionsArray;
				OK = true;
				for (int j = 0; j < XProductionsArray.length && OK; ++j) 
				{
					if (!(XProductionsArray[j].charAt(0) <= 'z' && XProductionsArray[j].charAt(0) >= 'a') &&
							position(XProductionsArray[j].charAt(0), tmpNonterminals) < i) 
					{
						OK = false;
					}
				}
				if (!OK) 
				{
					XProductions.clear();
				}
			}
			String[] X1 = new String[XProductions.size()];
			for (int j = 0; j < X1.length; ++j) 
			{
				X1[j] = XProductions.get(j);
			}
			for (int j = 0; j < X1.length; ++j) 
			{
				String current = X1[j];
				String p = "" + tmpNonterminals.charAt(2*n-i-1);
				if (current.charAt(0) == tmpNonterminals.charAt(i))
				{
					PProductions.add(current.substring(1));
					PProductions.add(current.substring(1) + p);
					XProductions.remove(current);
				} else 
				{
					XProductions.add(current + p);
				}
			}
			String[] XProductionsArray = new String[XProductions.size()];
			for (int j = 0; j < XProductions.size(); ++j)
			{
				XProductionsArray[j] = XProductions.get(j);
			}
			String[] PProductionsArray = new String[PProductions.size()];
			for (int j = 0; j < PProductions.size(); ++j)
			{
				PProductionsArray[j] = PProductions.get(j);
			}
			tmpProductions[i] = XProductionsArray;
			tmpProductions[2*n-i-1] = PProductionsArray;
		}
		return tmpProductions;
	}
	
	private String[][] stepTwo(String[][] tmpProductions, String tmpNonterminals, int n)
	{
		for (int i = 2*n - 1; i >= 0; --i)
		{
			List<String> XProductions = new ArrayList<String> ();
			for (int j = 0; j < tmpProductions[i].length; ++j) 
			{
				String current = tmpProductions[i][j];
				if (current.charAt(0) <= 'z' && current.charAt(0) >= 'a') 
				{
					XProductions.add(current);
				} else 
				{
					int k = position(current.charAt(0), tmpNonterminals);
					for (int m = 0; m < tmpProductions[k].length; ++m) 
					{
						XProductions.add(tmpProductions[k][m] + current.substring(1));
					}
				}
			}
			String[] XProductionsArray = new String[XProductions.size()];
			for (int j = 0; j < XProductions.size(); ++j) 
			{
				XProductionsArray[j] = XProductions.get(j);
			}
			tmpProductions[i] = XProductionsArray;
		}
		return tmpProductions;
	}
	
	private static int position(char c, String s) {
		int i = 0;
		while (i < s.length() && c != s.charAt(i))
			++i;
		if (i < s.length())
			return i;
		else
			return -1;
	}

}
