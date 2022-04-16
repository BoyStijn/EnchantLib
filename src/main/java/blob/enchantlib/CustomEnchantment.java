package blob.enchantlib;


import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;


public abstract class CustomEnchantment {
	
	protected EnchantRarity rarity = EnchantRarity.COMMON;
	private final EnchantTarget target;
	private final EnchantSlot[] slots;
	private final double Weighting;
	
	protected CustomEnchantment(double weight, EnchantTarget target, EnchantSlot[] slots) {
		this.Weighting = weight;
		this.target = target;
		this.slots = slots;
	}
	
	public double getWeighting() {
		return this.Weighting;
	}
	
	public EnchantRarity getRarity() {
		return this.rarity;
	}
	
	public EnchantTarget getTarget() {
		return this.target;
	}
	
	public EnchantSlot[] getSlots() {
		return this.slots;
	}
	
	public int MinLvl() {
	    return 1;
	}
	
	public int MaxLvl() {
	    return 1;
	}
	
	public int MinCost(int lvl) {
	    return 1 + lvl * 10;
	}
	  
	public int MaxCost(int lvl) {
	    return MinCost(lvl) + 5;
	}
	
	public boolean canEnchant(ItemStack item) {
	    return true;
	}
	
	public boolean isCompatible(Enchantment enchant) {
		return true;
	}
	
	public boolean OnlyTreasure() {
	    return false;
	}
	  
	public boolean isCursed() {
	    return false;
	}
	  
	public boolean canTrade() {
	    return true;
	}
	  
	public boolean canFound() {
	    return true;
	}
	
}
