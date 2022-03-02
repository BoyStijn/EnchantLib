package blob.enchantlib;

import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public abstract class CustomEnchantment extends Enchantment {

	protected CustomEnchantment(EnchantRarity rarity, EnchantTarget target, EnchantSlot[] slots) {
		super(rarity.getValue(), target.getValue(), toNSM(slots));
		// TODO Auto-generated constructor stub
	}

	private static EnumItemSlot[] toNSM(EnchantSlot[] a) {
		EnumItemSlot[] e = {};
		for (EnchantSlot s : a) {
			e[e.length] = s.getValue();
		}
		return e;
	}
	
	@Override
	public int a(int i) {
	    return this.getMinCost(i);
	}
	
	@Override
	public int b(int i) {
	    return this.getMaxCost(i);
	}
	
	@Override
	public boolean b() {
		return this.isTreasure();
	}
	
	@Override
	public int a() {
		return this.getMaxLevel();
	}
	
	@Override
	public boolean a(Enchantment e) {
		return isCompatible(e);
	}
	
	@Override
	public boolean a(net.minecraft.world.item.ItemStack i) {
		return (super.a(i) && this.canEnchant(CraftItemStack.asBukkitCopy(i)));
	}
	
	@Override
	public boolean c() {
	    return this.isCursed();
	}
	
	@Override
	public boolean i() {
		return this.isFindable();
	}
	
	public abstract int getMinCost(int level);
	public abstract int getMaxCost(int level);
	public abstract boolean isTreasure();
	public abstract boolean isCursed();
	public abstract boolean isFindable(); 
	public abstract int getMaxLevel();
	public abstract boolean isCompatible(Enchantment e);
	public abstract boolean canEnchant(ItemStack i);
	
}
