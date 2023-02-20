package blob.enchantlib;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftItemStack;

import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantWrapper extends Enchantment {

	private final CustomEnchantment enchant;
	
	protected EnchantWrapper(CustomEnchantment ench) {
		super(ench.getRarity().getValue(), ench.getTarget().getValue(), toNSM(ench.getSlots()));
		this.enchant = ench;
	}

	private static EnumItemSlot[] toNSM(EnchantSlot[] a) {
		EnchantLib.Instance.getLogger().log(Level.INFO, a.toString());
		List<EnumItemSlot> e = new ArrayList<EnumItemSlot>();
		for (EnchantSlot s : a) {
			e.add(s.getValue());
		}
		return e.toArray(new EnumItemSlot[0]);
	}
	
	public double getWeightValue() {
		return this.enchant.getWeighting();
	}
	
	@Override
	public int e() {
	    return this.enchant.MinLvl();
	}
	
	@Override
	public int a() {
	    return this.enchant.MaxLvl();
	}
	
	@Override
	public int a(int lvl) {
		return this.enchant.MinCost(lvl);
	}
	
	@Override
	public int b(int lvl) {
		return this.enchant.MaxCost(lvl);
	}
	
	@Override
	public boolean a(Enchantment e) {
		return (super.a(e) && this.enchant.isCompatible(EnchantLib.API.getBukkitEnchant(e)));
	}
	
	@Override
	public boolean a(net.minecraft.world.item.ItemStack i) {
		return (super.a(i) && this.enchant.canEnchant(CraftItemStack.asBukkitCopy(i)));
	}
	
	@Override
	public boolean b() {
		return this.enchant.OnlyTreasure();
	}
	
	@Override
	public boolean c() {
		return this.enchant.isCursed();
	}
	
	@Override
	public boolean h() {
	    return  this.enchant.canTrade();
	}
	
	@Override
	public boolean i() {
	    return  this.enchant.canFound();
	}
	
}
