package gramatyka;

public class NormalGreibachGrammar extends Grammar {

	public NormalGreibachGrammar(ContextFreeGrammar grammar) throws WrongParametersException
	{
		if (!grammar.ifGreibach())
		{
			throw new WrongParametersException();
		} else
		{
			this.terminals = grammar.terminals;
			this.nonterminals = grammar.nonterminals;
			this.productions = grammar.productions;
			this.type = grammar.type;
			this.form = "Greibach";
		}
	}
	
	@Override
	public boolean ifGreibach() 
	{
		return true;
	}
}
