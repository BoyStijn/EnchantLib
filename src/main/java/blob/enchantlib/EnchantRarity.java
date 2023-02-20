package blob.enchantlib;

import net.minecraft.world.item.enchantment.Enchantment.Rarity;

public enum EnchantRarity {
	COMMON(Rarity.a),
	RARE(Rarity.b),
	EPIC(Rarity.c),
	LEGENDARY(Rarity.d),;

	private final Rarity r;
	
	EnchantRarity(Rarity r) {
		this.r = r;
	}
	
	public Rarity getValue() {
		return r;
	}
}
