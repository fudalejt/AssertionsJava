package number.set;

import java.util.Random;

public class NumberSet {
	public static int MAX_SIZE = 10; 
	public static int MIN_VAL = 0, MAX_VAL = 10;
	public int []nSet = new int[MAX_SIZE]; //private
	public int	size; //private
	
	/**
     * Metoda dodaje liczb� do zbioru liczb 
     * (zezwalamy na dodanie liczby ju� istniej�cej)
     * 
     * 		@param	i
     *            liczba kt�r� dodajemy
     * 		@throws	Exception		
     *             wyst�puje w przypadku przepe�nienia zbioru
     */	
	public void add(int i) throws Exception{
		System.out.println("Add [" + i + "]");
		
		assert(size >= 0 && size <= MAX_SIZE) : "Niedozwolony rozmiar zbioru danych. "; //1)
	
		if(i < MIN_VAL && i > MAX_VAL)
			throw new Exception("Dodawana warto�� [" + i + "] nie mie�ci si� w zakresie zbioru."); //2)
		
		if(size < MAX_SIZE){
			nSet[size++] = i;
		}
		else if(size == MAX_SIZE)
			throw new Exception("Zbi�r pe�ny."); //3)
		else {
			assert(size > MAX_SIZE) : "Zbi�r przepe�niony."; //4)
		}
	}
	
	/**
    * Metoda usuwa liczb� ze zbioru (ka�de wyst�pienie)
    * 
    * @param i
    *            liczba do usuni�cia
    * @throws Exception
    *             wyst�puje je�li zbi�r nie posiada liczby kt�r� chcemy usun��
    */	
	public void remove(int i) throws Exception {
		System.out.println("Remove [" + i + "]");
		
		assert(nSet != null) : "Nast�pi�o niedozwolone usuni�cie zbioru danych."; //1)		
		
		int removed = 0;
		for(int j = 0; j < size; j++){
			if(nSet[j] == i){
				nSet[j] = 0;
				removed++;				
			}
			else if(removed > 0){
				nSet[j-removed] = nSet[j];
			}			
		}
		size -= removed;		
		assert(size >= 0 &&  size <= MAX_SIZE); //2)
		
		if(removed == 0)
			throw new Exception("Brak liczby w zbiorze.");	//3)	
	}
	
	/**
     * Metoda losuje jedn� liczb� ze zbioru oraz j� usuwa
     * 
     * @return wylosowana liczba  
     * * @throws Exception
     *             wyst�puje je�li zbi�r jest pusty
     */
	public int getRandomValue() throws Exception { 
		assert(nSet != null) : "Nast�pi�o niedozwolone usuni�cie zbioru danych."; //1)	
		assert(size >= 0 && size <= MAX_SIZE) : "Niedozwolony rozmiar zbioru danych. "; //2)
		
		if(size == 0)
			throw new Exception("Zbi�r jest pusty."); //3)
		
		Random random = new Random();
		int genNum = random.nextInt(MAX_VAL) + MIN_VAL;  
		
		for(int i = 0; i < size; i++){
			if(nSet[i] == genNum){
				int val = nSet[i];
				remove(nSet[i]);
				return val;
			}			
		}	
		
		throw new Exception("Brak liczby [" + genNum + "] w zbiorze."); //4)
	} 	
	
	/**
     * Metoda zwraca sum� element�w
     * 
     * @return Suma liczb.
     * @throws Exception
     *             wyst�puje je�li zbi�r jest pusty.
     */
	public int getSumOfElements() throws Exception { 
		assert(nSet != null) : "Nast�pi�o niedozwolone usuni�cie zbioru danych."; //1)	
		assert(size >= 0 && size <= MAX_SIZE) : "Niedozwolony rozmiar zbioru danych. "; //2)
		if(size == 0)
			throw new Exception("Zbior jest pusty."); //3)
		
		int sum = 0;			
		for(int i = 0; i < size; i++)
			sum += nSet[i];	
				
		assert(sum >= MIN_VAL * size && sum <= MAX_VAL * size) : "Niedozwolona suma element�w."; //4)		
		return sum;
	} 

	/**
     * Metoda dzieli ka�dy element zbioru przez podan� warto�� bez reszty
     * 
     * @param d
     *            liczba przez kt�r� dzielimy
     * @throws Exception
     *             wyst�puje w przypadku dzielenia przez 0 (mo�na zast�pi� asercj�)
     */
	public void divideAllElementsBy(int	d) throws Exception { 
		//System.out.println("Dividing by: " + d);
		assert(d != 0) : "Wyst�pi�o dzielenie przez zero."; //1)
		assert(nSet != null) : "Nast�pi�o niedozwolone usuni�cie zbioru danych."; //2)	
		assert(size >= 0 && size <= MAX_SIZE) : "Niedozwolony rozmiar zbioru danych. "; //3)
		
		try{
			for(int i = 0; i < size; i++){
				nSet[i] = nSet[i] / d;
				assert(nSet[i] >= MIN_VAL && nSet[i] <= MAX_VAL) : 
					"Dzielenie spowodowa�o przekroczenie zakresu warto�ci w zbiorze."; //4)
					
			}
		}catch(ArithmeticException e){
			throw new Exception("Dzielenie przez zero."); //5)
		}		
	} 
	
	
	/**
     * Metoda sprawdza czy w zbiorze istnieje podany element
     * 
     * @param i
     *            element do sprawdzenia
     * @return true w przypadku odnalezienia warto�ci, 
     *     false w przeciwnym razie.
     */
	public boolean contains(int i) throws Exception { 	
		System.out.println("Element 3 znajduje si� w zbiorze?");
		
		if(i < MIN_VAL || i > MAX_VAL)
			throw new Exception ("Szukana warto�� [" + i + "] nie mie�ci si� w zakresie zbioru: <" + MIN_VAL + ", " + MAX_VAL + ">." ); //1)
		assert(nSet != null) : "Nast�pi�o niedozwolone usuni�cie zbioru danych."; //2)	
		assert(size >= 0 && size <= MAX_SIZE) : "Niedozwolony rozmiar zbioru danych. "; //3)
		
		for(int j = 0; j < size; j++){
			if(nSet[j] == i)
				return true;							
		}
		return false; 
    } 
	
	/**
     * Metoda zwraca rozmiar zbioru (liczb� element�w)
     * 
     * @return rozmiar zbioru
     */
	public int getSize() { 
		assert(size >= 0 && size <= MAX_SIZE) : "Niedozwolony rozmiar [" + size + "] zbioru danych. MAX_SIZE = " + MAX_SIZE; //1)
		return size; 
    } 
	
	
	@Override
	public String toString() {
		assert(size >= 0 && size <= MAX_SIZE) : "Niedozwolony rozmiar [" + size + "] zbioru danych. MAX_SIZE = " + MAX_SIZE; //1)
		String string = new String("Data Set = ");
		
		for(int i = 0; i < size; i++){
			string += nSet[i] + ", ";
		}
		
		return string;
	} 
}
