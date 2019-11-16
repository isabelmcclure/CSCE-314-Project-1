import java.util.ArrayList; 
import java.math.BigInteger;

/*
 *  Desc: This class generates primes, twin primes, and hexagon crosses using BigInteger data types.
 */
public class PrimeOperations {
	
	//create array of primes of type BigInteger
	ArrayList<BigInteger> primes = new ArrayList<BigInteger>();
	ArrayList<Pair> twinPrimePairs = new ArrayList<Pair>();
	ArrayList<Pair> hexPairs = new ArrayList<Pair>();
	
	// Pair class implementation.
	private class Pair<T> {
		private T first;
		private T second;
		
		// These are for reference when outputting hex crosses. keeping track of the twin pairs within
		// the twin pair arraylist.
		private int indexOfFirstTwin;
		private int indexOfSecondTwin;
		
		public void setFirst(T newFirst) {this.first = newFirst;}
		public T getFirst() {return first;}
		
		public void setSecond(T newSecond) {this.second = newSecond;}
		public T getSecond() {return second;}
		
		public void setIndexOfFirst(int newIndex) {this.indexOfFirstTwin = newIndex;}
		public int getIndexOfFirst() {return indexOfFirstTwin;}
		
		public void setIndexOfSecond(int newIndex) {this.indexOfSecondTwin = newIndex;}
		public int getIndexOfSecond() {return indexOfSecondTwin;}
		
	}
	
	// Member variables for containing out lists of integers, twin primes, hexagon crosses, and the pairs of twin primes that make up the hex crosses.
	
	// Add a prime to the prime list if and only iff it is not already in the list. (ignore duplicates)
	public void addPrime(BigInteger x)
	{
		primes.add(x);
	}
	
	// Output the prime list. Each prime should be on a separate line and the total number of primes should be on the following line.
	public void printPrimes()
	{
		for (int i = 0; i < primes.size(); i++) {
			System.out.println(primes.get(i).toString());
		}
		System.out.println("Total primes:" + primes.size());
	}
		
	// Output the twin prime list. Each twin prime should be on a separate line with a comma separating them, and the total number of twin primes should be on the following line.
	public void printTwins()
	{
		for (int i = 0; i < twinPrimePairs.size(); i++) {
			System.out.print(twinPrimePairs.get(i).getFirst().toString());
			System.out.print(",");
			System.out.println(twinPrimePairs.get(i).getSecond().toString());
		}
		System.out.println("Total twins:" + twinPrimePairs.size());
	}
		
	// Output the hexagon cross list. Each should be on a separate line listing the two twin primes and the corresponding hexagon cross, with the total number on the following line.
	public void printHexes()
	{
		for(int i = 0; i < hexPairs.size(); i++) {
			System.out.print("Prime Pairs:" + twinPrimePairs.get(hexPairs.get(i).getIndexOfFirst()).getFirst().toString());
			System.out.print("," + twinPrimePairs.get(hexPairs.get(i).getIndexOfFirst()).getSecond().toString());
			System.out.print(" and " + twinPrimePairs.get(hexPairs.get(i).getIndexOfSecond()).getFirst().toString());
			System.out.print(","+ twinPrimePairs.get(hexPairs.get(i).getIndexOfSecond()).getSecond().toString());
			System.out.println(" separated by " + hexPairs.get(i).getFirst().toString() + "," + hexPairs.get(i).getSecond().toString());
		}
		System.out.println("Total hexes:" + hexPairs.size());
	}
		
	// Generate and store a list of primes.
	public void generatePrimes(int count)
	{
		int i = 0;
		int numPrimes = 0;
		while (numPrimes < count) {
			i++;
			int primeIndicator = 0;
			for (int j = i; j >= 1; j--) {
				if (i % j == 0) {
					primeIndicator = primeIndicator + 1;
				}
			}
			if (primeIndicator == 2) {//only divisible by itself and 1
				addPrime(BigInteger.valueOf(i));
				numPrimes = numPrimes + 1;
			}
		}
			
	}
	
	// Generate and store a list of twin primes.
	public void generateTwinPrimes()
	{
		for (int i = 0; i < (primes.size()-1); i++) {
			if((primes.get(i + 1).subtract(primes.get(i))).intValue() == 2) {
				Pair<BigInteger> pair = new Pair<BigInteger>();
				pair.setFirst(primes.get(i));
				pair.setSecond(primes.get(i+1));
				twinPrimePairs.add(pair);
			}
			
		}
	}
	
	// Generate and store the hexagon crosses, along with the two twin primes that generate the hexagon cross.
	public void generateHexPrimes()
	{
		for (int i = 0; i < (twinPrimePairs.size()-1); i++) {
			// PotentialHex1 is just a variable that represents the first value in the potential hexagon cross pair
			BigInteger potentialHex1 = (BigInteger) twinPrimePairs.get(i).getFirst();
			potentialHex1 = potentialHex1.add(BigInteger.valueOf(1));
			for (int j = (i + 1); j < (twinPrimePairs.size()); j++) {
				// Same as potentialHex1, but its the second value of the pair
				BigInteger potentialHex2 = (BigInteger) twinPrimePairs.get(j).getFirst();
				potentialHex2 = potentialHex2.add(BigInteger.valueOf(1));
				boolean hex = potentialHex2.equals(potentialHex1.multiply(BigInteger.valueOf(2)));//this means its a hex cross
				if (hex) {
					Pair<BigInteger> pair = new Pair<BigInteger>();
					pair.setFirst(potentialHex1);
					pair.setSecond(potentialHex2);
					pair.setIndexOfFirst(i);
					pair.setIndexOfSecond(j);
					hexPairs.add(pair);
				}
			}
		}
	}
}
