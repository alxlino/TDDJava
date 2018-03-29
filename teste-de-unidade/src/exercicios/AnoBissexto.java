package exercicios;

public class AnoBissexto {
	
	
	public Boolean ehBissexto(int ano) {
		
		Boolean ehBissexto = false;
		
		if ((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0 ) {
			ehBissexto = true;
		}	
			
		//		if (ano % 4 == 0) {
		//			if (ano % 100 != 0) 
		//				ehBissexto = true;
		//		}
		
		//Calendar cal = Calendar.getInstance();
		
		//ano = cal.get(Calendar.YEAR);
		//System.out.println(ano);
		
		//cal.setTime(new Date());

		//int numOfDays = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
		//System.out.println(numOfDays);
		
		return ehBissexto;
		
	} 
	
	public boolean isAnoBissextoAlura(int ano) {
	    if (((ano % 4) == 0) && ((ano % 100) != 0)) return true; 
	        else if ((ano % 400) == 0) return true; 
	        else return false;                
	}

}
