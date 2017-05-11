package number.test;

import number.set.NumberSet;

public class NSTest {

	public static void main(String[] args) {
		NumberSet ns = new NumberSet();
		
		try {
			
			/** adding */ 
			System.out.println("1) adding");	
			ns.add(0); 
			ns.add(1); 
			ns.add(2); 
			ns.add(3);
			ns.add(4);	
			ns.add(5);
			System.out.println(ns + "\n");
			
			/** removing */
			System.out.println("2) removing");
			ns.remove(2);			
			System.out.println(ns + "\n");
			
			/** getting random val */
			System.out.println("3) getting random val");
			ns.getRandomValue();
			System.out.println(ns + "\n");
			
			/** sum */ 
			System.out.println("4) sum");
			System.out.println("Suma = " + ns.getSumOfElements() + "\n");
		
			/** dividing */	
			System.out.println("5) dividing");		
			System.out.println("Before: " + ns);
			ns.divideAllElementsBy(2);
			System.out.println("After: " + ns  + "\n");
			
			/** contains */	
			System.out.println("6) contains");				
			System.out.println(ns.contains(3)  + "\n");
			
			/** size */ 
			System.out.println("7) size");
			System.out.println("size = " + ns.getSize() + "\n");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
