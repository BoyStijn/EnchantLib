package blob.enchantlib;

public class Weight {

	private final double weight;
	
	private Weight(double w) {
		this.weight = w;
	}

	public static Weight of(double d) {
		validateWeight(d);
		return new Weight(d);
	}
	
	private static void validateWeight(double $$0) {
	    if ($$0 < 0)
	      throw new IllegalArgumentException("Weight should be >= 0"); 
	}
	
	public String toString() {
		return Double.toString(this.weight);
	}
		  
	public int hashCode() {
		return Double.hashCode(this.weight);
	}
	
	public double asDouble() {
		return this.weight;
	}
	
	public int asInt() {
		return (int) Math.round(this.weight);
	}
		  
	public boolean equals(Object $$0) {
		if (this == $$0) return true; 
		return ($$0 instanceof Weight && this.weight == ((Weight)$$0).weight);
	}
	
}
