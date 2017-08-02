package gramatyka;

public class Main {
	
	// please note that the WrongParametersException is not handled on purpose -
	// - we wouldn't want to be able to call methods from object that was not created

	public static void main(String[] args) throws WrongParametersException
	{
		String inp1 = "a";
		String inp2 = "BC";
		String[][] arr3 = {{"a", "BB"},{"a","CB"}};
		
		ContextFreeGrammar g = new ContextFreeGrammar(inp1,inp2,arr3);
		System.out.println(g);
		
		System.out.println(g.ifRegular());
		System.out.println(g.ifChomsky());
		System.out.println(g.ifGreibach());
		
		NormalChomskyGrammar r = new NormalChomskyGrammar(g);
		System.out.println(r);
		
		System.out.println(r.ifRegular());
		System.out.println(r.ifChomsky());
		System.out.println(r.ifGreibach());
		
		NormalGreibachGrammar s = r.toGreibach();
		System.out.println(s);
		
		System.out.println(s.ifRegular());
		System.out.println(s.ifChomsky());
		System.out.println(s.ifGreibach());
		
		
		
		String inp4 = "poextra";
		String inp5 = "POEXTRA";
		String[][] arr6 = {{""},{""},{"POpoEXexTRAtra","","XXXX","extra"},{"t"},{"rat"},{"RRRaAA",""},{"PoppoP"}};
		
		ContextFreeGrammar h = new ContextFreeGrammar(inp4,inp5,arr6);
		System.out.println(h);
		
		System.out.println(h.ifRegular());
		System.out.println(h.ifChomsky());
		System.out.println(h.ifGreibach());
		
		
		
		String inp7 = "gram";
		String inp8 = "GRAM";
		String[][] arr9 = {{""},{"g"},{"rR","gG","aA","mM"},{"g","r","a","m"}};
		// for the data in the next line isGreibach = true;
//		String[][] arr9 = {{"a"},{"g"},{"rR","gG","aA","mM"},{"g","r","a","m"}};
		
		
		ContextFreeGrammar i = new ContextFreeGrammar(inp7,inp8,arr9);
		System.out.println(i);
		
		RegularGrammar t = new RegularGrammar(i);
		System.out.println(t);
		
		System.out.println(t.ifRegular());
		System.out.println(t.ifChomsky());
		System.out.println(t.ifGreibach());
		
		
		
		// for the following attempts the WrongParametersException is thrown
		
		// ContextFreeGrammar is just not in the type/form of the others
		
//		NormalChomskyGrammar u = new NormalChomskyGrammar(h);
//		NormalGreibachGrammar v = new NormalGreibachGrammar(h);
//		RegularGrammar w = new RegularGrammar(h);
		
		// terminals contain uppercase letter
		
//		String inp10 = "grAm";
//		String inp11 = "GRAM";
//		String[][] arr12 = {{""},{"g"},{"rR","gG","aA","mM"},{"g","r","a","m"}};
//		
//		ContextFreeGrammar z = new ContextFreeGrammar(inp10,inp11,arr12);
		
		// nonterminals contain lowercase letter
		
//		String inp10 = "gram";
//		String inp11 = "GRAMs";
//		String[][] arr12 = {{""},{"g"},{"rR","gG","aA","mM"},{"g","r","a","m"}};
//		
//		ContextFreeGrammar z = new ContextFreeGrammar(inp10,inp11,arr12);
		
		// there are more production tables than nonterminals
		
//		String inp10 = "gram";
//		String inp11 = "GRAM";
//		String[][] arr12 = {{""},{"g"},{"rR","gG","aA","mM"},{"g","r","a","m"},{"}};
//		
//		ContextFreeGrammar z = new ContextFreeGrammar(inp10,inp11,arr12);
		

	}

}
