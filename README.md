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
import net.minecraft.world.item.enchantment.Enchantment; //this is the NSM version of Enchantment

public class example extends Enchantment {
    
    public example(Rarity rarity, EnchantmentSlotType type, EnumItemSlot... aenumitemslot) {
		super(rarity, type, aenumitemslot);
	}
	
	@Override
	public int a(int i) { //Min enchant cost per level (int i)
	    return i * 30;
	}
	
	@Override
	public int b(int i) { //Max enchant cost per level (int i)
	    return a(i) + 30;
	}
	
	@Override
	public boolean b() { //isTreasure (if true the enchantment wont be obtainable with an enchantment table)
		return true;
	}
	
	
	@Override
	public int a() { //Max level of the enchantment (usually 1, 3 or 5)
		return 5;
	}
	
	@Override
	public boolean a(Enchantment enchantment) { //isCompatible (if false the enchantment wont apply)
		return true;
	}
    
}
```




