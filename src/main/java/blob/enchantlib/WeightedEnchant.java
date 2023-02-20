package blob.enchantlib;

import java.util.logging.Level;

import blob.enchantlib.enchanttable.WeightEntry;
import net.minecraft.world.item.enchantment.Enchantment;

public class WeightedEnchant extends WeightEntry {
	
	public final Enchantment enchantment;  
	public final int level;
	  
	public WeightedEnchant(Enchantment ench, int lvl) {
		super(WeightHelper(ench));
    	this.enchantment = ench;
    	this.level = lvl;
	}
	
	private static double WeightHelper(Enchantment ench) {
		if (ench instanceof EnchantWrapper) {
			EnchantLib.Instance.getLogger().log(Level.INFO, "using custom rarity methode for enchantment: " + ench.toString());
			return ((EnchantWrapper)ench).getWeightValue();
		}
		return ench.d().a();
	}
	
}
