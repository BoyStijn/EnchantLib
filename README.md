# EnchantLib

EnchantLib is a spigot library for registering custom enchantments.

## Features

- Custom Enchants work just like vanilla enchants
- Automatically works with loot generation
- works with anvils, enchantment tables, grindstones and smithing tables just like you would expect
- No special commands needed for giving custom enchants to the player
- Allows Custom Enchantment plugins to work together

## Usage

### Getting the api

```java
if (Bukkit.getServer().getPluginManager().isPluginEnabled("EnchantLib")) {  
    EnchantLib.API; //static Api access
    ...
```
**Dont forget to add EnchantLib as a dependency in your plugin.yml!** 

### Api Functions

#### `registerEnchants(NamespacedKey key, Enchantment ench, String name)`
registers a custom enchantment
returns void

#### `isCustomEnchant(Enchantment ench)`
checks if an enchantment is a custom enchantment
returns boolean

#### `ApplyCustomLore(ItemMeta meta, Map<org.bukkit.enchantments.Enchantment, Integer> enchs)`
updates the lore on an item. Updates are already handled by EnchantLib so this shouldn't be called
returns ItemMeta

#### `damageItem(int i, ItemStack item)`
damages an item by ammount i. This function will damage items just like vanilla minecraft would
returns void

#### `getEnchants()`
returns a list of all custom enchants

#### `getBukkitEnchant(Enchantment e)`
returns the bukkit version of the enchantment

### Making a custom enchantment

```java
import org.bukkit.inventory.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class TestEnchant extends CustomEnchantment {

	protected TestEnchant(EnchantRarity rarity, EnchantTarget target, EnchantSlot[] slots) {
		super(rarity, target, slots);
	}

	@Override
	public int getMinCost(int level) { // the xp cost of the enchant
		return 0;
	}

	@Override
	public int getMaxCost(int level) {
		return 0;
	}

	@Override
	public boolean isTreasure() { // if true the enchantment cant be obtained in an enchantment table
		return false;
	}

	@Override
	public int getMaxLevel() { // max level enchantment obtainable (Usually 1,3 or 5)
		return 1;
	}

	@Override
	public boolean isCompatible(Enchantment e) { // if this enchant can be applied to an item with the other enchant
		return true;
	}

	@Override
	public boolean canEnchant(ItemStack i) { // if this enchant can be applied to a certain item
		return true;
	}

	@Override
	public boolean isCursed() { // if the enchant is cursed
		return false;
	}

	@Override
	public boolean isFindable() { // if false the item wont so in loot generation, villagers and enchantment tables. it can still be in loot if specified, but wont be in random enchantment pool
		return true;
	}
	
}
```




