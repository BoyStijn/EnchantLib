package blob.enchantlib;

import net.minecraft.world.item.enchantment.EnchantmentSlotType;

public enum EnchantTarget {
	ARMOR(EnchantmentSlotType.a),
	ARMOR_HEAD(EnchantmentSlotType.b),
	ARMOR_CHEST(EnchantmentSlotType.c),
	ARMOR_LEGS(EnchantmentSlotType.d),
	ARMOR_FEET(EnchantmentSlotType.e),
	SWORD(EnchantmentSlotType.f),
	TOOLS(EnchantmentSlotType.g),
	FISHING_ROD(EnchantmentSlotType.h),
	TRIDENT(EnchantmentSlotType.i),
	BREAKABLE(EnchantmentSlotType.j),
	BOW(EnchantmentSlotType.k),
	WEARABLE(EnchantmentSlotType.l),
	VANISABLE(EnchantmentSlotType.j),;

	private final EnchantmentSlotType type;
	
	EnchantTarget(EnchantmentSlotType type) {
		this.type = type;
	}
	
	public EnchantmentSlotType getValue() {
		return type;
	}
	
}


