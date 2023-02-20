package blob.enchantlib;

import net.minecraft.world.item.enchantment.EnchantmentSlotType;

public enum EnchantTarget {
	ARMOR(EnchantmentSlotType.a),
	ARMOR_HEAD(EnchantmentSlotType.e),
	ARMOR_CHEST(EnchantmentSlotType.d),
	ARMOR_LEGS(EnchantmentSlotType.c),
	ARMOR_FEET(EnchantmentSlotType.b),
	SWORD(EnchantmentSlotType.f),
	TOOLS(EnchantmentSlotType.g),
	FISHING_ROD(EnchantmentSlotType.h),
	TRIDENT(EnchantmentSlotType.i),
	BREAKABLE(EnchantmentSlotType.j),
	BOW(EnchantmentSlotType.k),
	WEARABLE(EnchantmentSlotType.l),
	CROSSBOW(EnchantmentSlotType.m),
	VANISABLE(EnchantmentSlotType.n);

	private final EnchantmentSlotType type;
	
	EnchantTarget(EnchantmentSlotType type) {
		this.type = type;
	}
	
	public EnchantmentSlotType getValue() {
		return type;
	}
	
}


