package blob.enchantlib;

import java.util.List;
import java.util.Optional;

import blob.enchantlib.enchanttable.WeightEntry;
import net.minecraft.util.RandomSource;

public class WeightedRandom {
	
	public static double getTotalWeight(List<? extends WeightEntry> pool) {
	    long l = 0L;
	    for (WeightEntry weightedEntry : pool)
	      l += weightedEntry.getWeight().asDouble(); 
	    if (l > 2147483647L)
	      throw new IllegalArgumentException("Sum of weights must be <= 2147483647"); 
	    return l;
	  }
	  
	  public static <T extends WeightEntry> Optional<T> getRandomItem(RandomSource random, List<T> pool, double totalWeight) {
	    if (totalWeight < 0)
	      throw new IllegalArgumentException("Negative total weight in getRandomItem"); 
	    if (totalWeight == 0)
	      return Optional.empty(); 
	    double i = (random.j() * totalWeight);
	    return getWeightedItem(pool, i);
	  }
	  
	  public static <T extends WeightEntry> Optional<T> getWeightedItem(List<T> pool, double totalWeight) {
	    for (WeightEntry weightedEntry : pool) {
	      totalWeight -= weightedEntry.getWeight().asDouble(); 
	      if (totalWeight < 0)
	        return Optional.of((T)weightedEntry); 
	    } 
	    return Optional.empty();
	  }
	  
	  public static <T extends WeightEntry> Optional<T> getRandomItem(RandomSource random, List<T> pool) {
	    return getRandomItem(random, pool, getTotalWeight(pool));
	  }	
}
