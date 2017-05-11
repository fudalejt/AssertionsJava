package number.set;

import java.util.Random;

public class NumberSet {
	public static int MAX_SIZE = 10; 
	public static int MIN_VAL = 0, MAX_VAL = 10;
	public int []nSet = new int[MAX_SIZE]; //private
	public int	size; //private
	
	/**
     * Metoda dodaje liczbê do zbioru liczb 
     * (zezwalamy na dodanie liczby ju¿ istniej¹cej)
     * 
     * 		@param	i
     *            liczba któr¹ dodajemy
     * 		@throws	Exception		
     *             wystêpuje w przypadku przepe³nienia zbioru
     */	
	public void add(int i) throws Exception{
		System.out.println("Add [" + i + "]");
		
		assert(size >= 0 && size <= MAX_SIZE) : "Niedozwolony rozmiar zbioru danych. "; //1)
	
		if(i < MIN_VAL && i > MAX_VAL)
			throw new Exception("Dodawana wartoœæ [" + i + "] nie mieœci siê w zakresie zbioru."); //2)
		
		if(size < MAX_SIZE){
			nSet[size++] = i;
		}
		else if(size == MAX_SIZE)
			throw new Exception("Zbiór pe³ny."); //3)
		else {
			assert(size > MAX_SIZE) : "Zbiór przepe³niony."; //4)
		}
	}
	
	/**
    * Metoda usuwa liczbê ze zbioru (ka¿de wyst¹pienie)
    * 
    * @param i
    *            liczba do usuniêcia
    * @throws Exception
    *             wystêpuje jeœli zbiór nie posiada liczby któr¹ chcemy usun¹æ
    */	
	public void remove(int i) throws Exception {
		System.out.println("Remove [" + i + "]");
		
		assert(nSet != null) : "Nast¹pi³o niedozwolone usuniêcie zbioru danych."; //1)		
		
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
     * Metoda losuje jedn¹ liczbê ze zbioru oraz j¹ usuwa
     * 
     * @return wylosowana liczba  
     * * @throws Exception
     *             wystêpuje jeœli zbiór jest pusty
     */
	public int getRandomValue() throws Exception { 
		assert(nSet != null) : "Nast¹pi³o niedozwolone usuniêcie zbioru danych."; //1)	
		assert(size >= 0 && size <= MAX_SIZE) : "Niedozwolony rozmiar zbioru danych. "; //2)
		
		if(size == 0)
			throw new Exception("Zbiór jest pusty."); //3)
		
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
     * Metoda zwraca sumê elementów
     * 
     * @return Suma liczb.
     * @throws Exception
     *             wystêpuje jeœli zbiór jest pusty.
     */
	public int getSumOfElements() throws Exception { 
		assert(nSet != null) : "Nast¹pi³o niedozwolone usuniêcie zbioru danych."; //1)	
		assert(size >= 0 && size <= MAX_SIZE) : "Niedozwolony rozmiar zbioru danych. "; //2)
		if(size == 0)
			throw new Exception("Zbior jest pusty."); //3)
		
		int sum = 0;			
		for(int i = 0; i < size; i++)
			sum += nSet[i];	
				
		assert(sum >= MIN_VAL * size && sum <= MAX_VAL * size) : "Niedozwolona suma elementów."; //4)		
		return sum;
	} 

	/**
     * Metoda dzieli ka¿dy element zbioru przez podan¹ wartoœæ bez reszty
     * 
     * @param d
     *            liczba przez któr¹ dzielimy
     * @throws Exception
     *             wystêpuje w przypadku dzielenia przez 0 (mo¿na zast¹piæ asercj¹)
     */
	public void divideAllElementsBy(int	d) throws Exception { 
		//System.out.println("Dividing by: " + d);
		assert(d != 0) : "Wyst¹pi³o dzielenie przez zero."; //1)
		assert(nSet != null) : "Nast¹pi³o niedozwolone usuniêcie zbioru danych."; //2)	
		assert(size >= 0 && size <= MAX_SIZE) : "Niedozwolony rozmiar zbioru danych. "; //3)
		
		try{
			for(int i = 0; i < size; i++){
				nSet[i] = nSet[i] / d;
				assert(nSet[i] >= MIN_VAL && nSet[i] <= MAX_VAL) : 
					"Dzielenie spowodowa³o przekroczenie zakresu wartoœci w zbiorze."; //4)
					
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
     * @return true w przypadku odnalezienia wartoœci, 
     *     false w przeciwnym razie.
     */
	public boolean contains(int i) throws Exception { 	
		System.out.println("Element 3 znajduje siê w zbiorze?");
		
		if(i < MIN_VAL || i > MAX_VAL)
			throw new Exception ("Szukana wartoœæ [" + i + "] nie mieœci siê w zakresie zbioru: <" + MIN_VAL + ", " + MAX_VAL + ">." ); //1)
		assert(nSet != null) : "Nast¹pi³o niedozwolone usuniêcie zbioru danych."; //2)	
		assert(size >= 0 && size <= MAX_SIZE) : "Niedozwolony rozmiar zbioru danych. "; //3)
		
		for(int j = 0; j < size; j++){
			if(nSet[j] == i)
				return true;							
		}
		return false; 
    } 
	
	/**
     * Metoda zwraca rozmiar zbioru (liczbê elementów)
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
