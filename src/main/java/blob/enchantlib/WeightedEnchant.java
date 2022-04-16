package blob.enchantlib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
		try {
			Method[] meth = ench.getClass().getDeclaredMethods();
			for (int i = 0; i < meth.length; i++) {
				if (meth[i].getReturnType() != double.class) continue;
				if (meth[i].getParameterCount() > 0) continue;
				if (meth[i].getName() != "getWeightValue") continue;
				return (double)meth[i].invoke(ench);
			}
			return ench.d().a();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
