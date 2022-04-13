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
registers a custom enchantment \
returns void

#### `registerEnchants(NamespacedKey key, CustomEnchantment ench, String name)`
registers a custom enchantment \
returns void

#### `isCustomEnchant(Enchantment ench)`
checks if an enchantment is a custom enchantment \
returns boolean

#### `ApplyCustomLore(ItemMeta meta, Map<org.bukkit.enchantments.Enchantment, Integer> enchs)`
updates the lore on an item. Updates are already handled by EnchantLib so this shouldn't be called \
returns ItemMeta

#### `damageItem(int i, ItemStack item)`
damages an item by ammount i. This function will damage items just like vanilla minecraft would \
returns void

#### `getEnchants()`
returns a list of all custom enchants 

#### `getBukkitEnchant(Enchantment e)`
returns the bukkit version of the enchantment 

#### `getBukkitEnchant(CustomEnchantment e)`
returns the bukkit version of the enchantment 

#### `getNSMEntity(LivingEntity e)`
returns the NMS version of an entity

#### `overrideGoals(LivingEntity e, Map<PathfinderGoal, Integer> goals, Map<PathfinderGoal, Integer> targets)`
override the pathfinding goals of an entity \
returns void

#### `addAttributes(LivingEntity e, AttributeBase b, double value)`
Add / override attributes of mobs (like movement speed and attack damage) \
returns void

### Making a custom enchantment

```java
import org.bukkit.inventory.ItemStack;
import org.bukkit.enchantments.Enchantment;

import blob.enchantlib.CustomEnchantment;
import blob.enchantlib.EnchantRarity;
import blob.enchantlib.EnchantSlot;
import blob.enchantlib.EnchantTarget;

public class TestEnchant extends CustomEnchantment {

	public TestEnchant(EnchantRarity rarity, EnchantTarget target, EnchantSlot[] slots) {
		super(rarity, target, slots);
	}
	
	@Override
	public int MinLvl() {
	    return 1;
	}
	
	@Override
	public int MaxLvl() {
	    return 1;
	}
	
	@Override
	public int MinCost(int lvl) {
	    return 1 + lvl * 10;
	}
	
	@Override
	public int MaxCost(int lvl) {
	    return MinCost(lvl) + 5;
	}
	
	@Override
	public boolean canEnchant(ItemStack item) {
	    return true;
	}
	
	@Override
	public boolean isCompatible(Enchantment enchant) {
		return true;
	}
	
	@Override
	public boolean OnlyTreasure() {
	    return false;
	}
	  
	@Override
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
```




