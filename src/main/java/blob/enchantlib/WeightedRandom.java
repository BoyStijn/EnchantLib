package blob.enchantlib;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import blob.enchantlib.enchanttable.WeightEntry;

public class WeightedRandom {

	public static double getTotalWeight(List<? extends WeightEntry> $$0) {
		double total = 0;
	    for (WeightEntry $$2 : $$0)
	      total += $$2.getWeight().asDouble(); 
	    if (total > 2147483647L)
	      throw new IllegalArgumentException("Sum of weights must be <= 2147483647"); 
	    return total;
	  }
	  
	  public static <T extends WeightEntry> Optional<T> getRandomItem(Random $$0, List<T> $$1, double $$2) {
	    if ($$2 < 0)
	      throw new IllegalArgumentException("Negative total weight in getRandomItem"); 
	    if ($$2 == 0)
	      return Optional.empty(); 
	    double $$3 = $$0.nextDouble($$2);
	    return getWeightedItem($$1, $$3);
	  }
	  
	  public static <T extends WeightEntry> Optional<T> getWeightedItem(List<T> $$0, double $$1) {
	    for (WeightEntry weightedEntry : $$0) {
	      $$1 -= weightedEntry.getWeight().asInt();
	      if ($$1 < 0)
	        return Optional.of((T)weightedEntry); 
	    } 
	    return Optional.empty();
	  }
	  
	  public static <T extends WeightEntry> Optional<T> getRandomItem(Random $$0, List<T> $$1) {
	    return getRandomItem($$0, $$1, getTotalWeight($$1));
	  }
	
}
