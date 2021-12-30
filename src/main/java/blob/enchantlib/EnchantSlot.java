package blob.enchantlib;

import net.minecraft.world.entity.EnumItemSlot;

public enum EnchantSlot {
	MAIN_HAND(EnumItemSlot.a),
	OFF_HAND(EnumItemSlot.b),
	HEAD(EnumItemSlot.f),
	CHEST(EnumItemSlot.e),
	LEGS(EnumItemSlot.d),
	FEET(EnumItemSlot.c);

	private final EnumItemSlot s;
	
	EnchantSlot(EnumItemSlot s) {
		this.s = s;
	}
	
	public EnumItemSlot getValue() {
		return this.s;
	}
}
