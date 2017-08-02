package gramatyka;

public class RegularGrammar extends Grammar {

	public RegularGrammar(ContextFreeGrammar grammar) throws WrongParametersException
	{
		if (!grammar.ifRegular())
		{
			throw new WrongParametersException();
		} else
		{
			this.terminals = grammar.terminals;
			this.nonterminals = grammar.nonterminals;
			this.productions = grammar.productions;
			this.type = "regularna";
			this.form = grammar.form;
		}
	}
	
	@Override
	public boolean ifRegular() 
	{
		return true;
	}
	
}
