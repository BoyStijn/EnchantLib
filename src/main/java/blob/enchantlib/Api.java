package blob.enchantlib;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.Random;
import java.util.logging.Level;

import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_18_R2.enchantments.CraftEnchantment;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TranslatableComponent;
import net.minecraft.core.IRegistry;
import net.minecraft.core.RegistryMaterials;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.util.MathHelper;
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.attributes.AttributeBase;
import net.minecraft.world.entity.ai.attributes.AttributeMapBase;
import net.minecraft.world.entity.ai.attributes.AttributeModifiable;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemEnchantedBook;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentDurability;
import net.minecraft.world.item.enchantment.EnchantmentManager;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Api {

	private ArrayList<Enchantment> CustomEnch = new ArrayList<Enchantment>();
	private HashMap<Enchantment, String> NameMap = new HashMap<Enchantment, String>();
	private HashMap<CustomEnchantment, EnchantWrapper> WrapMap = new HashMap<CustomEnchantment, EnchantWrapper>();
	
	public void registerEnchants(NamespacedKey key, Enchantment ench, String name) {
	    try{
	        try {
	            Field f = org.bukkit.enchantments.Enchantment.class.getDeclaredField("acceptingNew");
	            f.setAccessible(true);
	            f.set(null, true);
	            Field bLField = RegistryMaterials.class.getDeclaredField("bL");
	        	bLField.setAccessible(true);
	        	bLField.set(IRegistry.V, false);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        try {	        	
	        	IRegistry.a(IRegistry.V, key.toString(), ench);
	        	org.bukkit.enchantments.Enchantment.registerEnchantment((org.bukkit.enchantments.Enchantment)new CraftEnchantment(ench)); 
	            NameMap.put(ench, name);
	            CustomEnch.add(ench);
	            
	            EnchantLib.Instance.getLogger().log(Level.INFO, "Registered enchantment " + ench.toString());
	            EnchantLib.Instance.getLogger().log(Level.INFO, "Registered key " + key.toString());
	        } catch (IllegalArgumentException e){
	        	e.printStackTrace();
	        }
	    }catch(Exception e){
	        e.printStackTrace();
	    }

	}
	
	public boolean instanceofNMS(ItemStack i, Class<?> c) {
		return c.isInstance(CraftItemStack.asNMSCopy(i).c());
	}
	
	public void registerEnchants(NamespacedKey key, CustomEnchantment enchant, String name) {
		Enchantment ench = new EnchantWrapper(enchant);
		WrapMap.put(enchant, (EnchantWrapper) ench);
	    try{
	        try {
	            Field f = org.bukkit.enchantments.Enchantment.class.getDeclaredField("acceptingNew");
	            f.setAccessible(true);
	            f.set(null, true);
	            Field bLField = RegistryMaterials.class.getDeclaredField("bL");
	        	bLField.setAccessible(true);
	        	bLField.set(IRegistry.V, false);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        try {
	        	IRegistry.a(IRegistry.V, key.toString(), ench);
	        	org.bukkit.enchantments.Enchantment.registerEnchantment((org.bukkit.enchantments.Enchantment)new CraftEnchantment(ench)); 
	            NameMap.put(ench, name);
	            CustomEnch.add(ench);
	            
	            EnchantLib.Instance.getLogger().log(Level.INFO, "Registered enchantment " + ench.toString());
	            EnchantLib.Instance.getLogger().log(Level.INFO, "Registered key " + key.toString());
	        } catch (IllegalArgumentException e){
	        	e.printStackTrace();
	        }
	    }catch(Exception e){
	        e.printStackTrace();
	    }

	}
	
	
	public Boolean isCustomEnchant(Enchantment ench) {
		if (CustomEnch.contains(ench)) return true;
		return false;
	}
	
	public ItemMeta ApplyCustomLore(ItemMeta meta, Map<org.bukkit.enchantments.Enchantment, Integer> enchs) {
		if (meta == null) return null;
		ArrayList<String> lore = new ArrayList<String>();
		for (Enchantment ce : CustomEnch) {
			org.bukkit.enchantments.Enchantment e = getBukkitEnchant(ce);
			if (enchs.containsKey(e)) {
				ChatColor c = null;
				if (ce.c()) c = ChatColor.RED;
				if (!ce.c()) c = ChatColor.GRAY;
				if (ce.a() == 1) {
					lore.add(c + NameMap.get(ce));
				} else {
					lore.add(c + NameMap.get(ce) + " " + new TranslatableComponent("enchantment.level." + enchs.get(e)).toPlainText());
				}
			}
		}
		if (meta.hasLore()) {
			List<String> tosort = meta.getLore();
			ArrayList<String> toRemove = new ArrayList<String>();
			for (String s : tosort) {
				for (Enchantment ce : CustomEnch) {
					if (s.contains(NameMap.get(ce))) {
						toRemove.add(s);
					}
				}
			}
			tosort.removeAll(toRemove);
			lore.addAll(tosort);
		}
		meta.setLore(lore);
		return meta;
	}
	
	public void damageItem(int i, ItemStack item) {
		int j;

        if (i > 0) {
            j = item.getEnchantmentLevel(org.bukkit.enchantments.Enchantment.DURABILITY);
            int k = 0;

            for (int l = 0; j > 0 && l < i; ++l) {
                if (EnchantmentDurability.a(CraftItemStack.asNMSCopy(item), j, new Random())) {
                    ++k;
                }
            }

            i -= k;
            if (i <= 0) {
                return;
            }
        }
        
        Damageable meta = ((Damageable)item.getItemMeta());

        j = meta.getDamage() + i;
        meta.setDamage(j);
        item.setItemMeta(meta);
	}
	
	public ArrayList<Enchantment> getEnchants() {
		return this.CustomEnch;
	}
	
	public org.bukkit.enchantments.Enchantment getBukkitEnchant(Enchantment e) {
		return new CraftEnchantment(e);
	}
	
	public org.bukkit.enchantments.Enchantment getBukkitEnchant(CustomEnchantment e) {
		if (WrapMap.containsKey(e)) return new CraftEnchantment(WrapMap.get(e));
		return null;
	}
	
	public EntityCreature getNSMEntity(LivingEntity e) {
		return (EntityCreature) ((EntityInsentient)((CraftEntity)e).getHandle());
	}
	
	public void overrideGoals(LivingEntity e, Map<PathfinderGoal, Integer> goals, Map<PathfinderGoal, Integer> targets) {
		EntityCreature c = (EntityCreature) ((EntityInsentient)((CraftEntity)e).getHandle());
		try {
		     Field dField = PathfinderGoalSelector.class.getDeclaredField("d");
		     dField.setAccessible(true);
		     dField.set(c.bQ, Sets.newLinkedHashSet());
		     dField.set(c.bR, Sets.newLinkedHashSet());
		 } catch (Exception exc) {exc.printStackTrace();}
		 for (Entry<PathfinderGoal, Integer> eset : goals.entrySet()) {
			 c.bQ.a(eset.getValue(), eset.getKey());
		 }
		 for (Entry<PathfinderGoal, Integer> eset : targets.entrySet()) {
			 c.bR.a(eset.getValue(), eset.getKey());
		 }
	}
	
	public void addAttributes(LivingEntity e, AttributeBase b, double value) {
		EntityCreature c = (EntityCreature) ((EntityInsentient)((CraftEntity)e).getHandle());
		try {
			 Field bQField = EntityLiving.class.getDeclaredField("bQ");
	    	 bQField.setAccessible(true);
	    	 AttributeMapBase base = (AttributeMapBase) bQField.get(c);
		     Field bField = AttributeMapBase.class.getDeclaredField("b");
		     bField.setAccessible(true);
	    	 @SuppressWarnings("unchecked")
			 Map<AttributeBase, AttributeModifiable> bmap = (Map<AttributeBase, AttributeModifiable>) bField.get(base);
	    	 if (bmap.containsKey(b)) {
	    		 bmap.get(b).a(value);
	    	 } else {
		    	 AttributeModifiable m = new AttributeModifiable(b, mod -> {});
		    	 m.a(value);
			     bmap.put(b, m);
	    	 }
		 } catch (Exception exc) {exc.printStackTrace();}
	}
	
	public net.minecraft.world.item.ItemStack enchantItem(Random var0, net.minecraft.world.item.ItemStack var1, int var2, boolean var3) {
		List<WeightedEnchant> var4 = selectEnchantment(var0, var1, var2, var3);
		boolean var5 = var1.a(Items.om);
		if (var5) var1 = new net.minecraft.world.item.ItemStack(Items.rB); 
		for (WeightedEnchant var7 : var4) {
			if (var5) {
				enchantbook(var1, var7);
				continue;
	      	} 
	      	var1.a(var7.enchantment, var7.level);
	    } 
	    return var1;
	}
	
	public void filterCompatibleEnchantments(List<WeightedEnchant> var0, WeightedEnchant var1) {
    	Iterator<WeightedEnchant> var2 = var0.iterator();
    	while (var2.hasNext()) {
    		if (!var1.enchantment.b(((WeightedEnchant)var2.next()).enchantment)) var2.remove(); 
        } 
    }
    
	public void enchantbook(net.minecraft.world.item.ItemStack var0, WeightedEnchant var1) {
		NBTTagList var2 = ItemEnchantedBook.d(var0);
		boolean var3 = true;
		MinecraftKey var4 = EnchantmentManager.a(var1.enchantment);
		for (int var5 = 0; var5 < var2.size(); var5++) {
			NBTTagCompound var6 = var2.a(var5);
			MinecraftKey var7 = EnchantmentManager.b(var6);
			if (var7 != null && var7.equals(var4)) {
				if (EnchantmentManager.a(var6) < var1.level) EnchantmentManager.a(var6, var1.level); 
				var3 = false;
				break;
			} 
		} 
		if (var3) var2.add(EnchantmentManager.a(var4, var1.level)); 
		var0.u().a("StoredEnchantments", (NBTBase)var2);
	}
	
	public List<WeightedEnchant> selectEnchantment(Random var0, net.minecraft.world.item.ItemStack var1, int var2, boolean var3) {
        List<WeightedEnchant> var4 = Lists.newArrayList();
        Item var5 = var1.c();
        int var6 = var5.c();
        if (var6 <= 0)
          return var4; 
        var2 += 1 + var0.nextInt(var6 / 4 + 1) + var0.nextInt(var6 / 4 + 1);
        float var7 = (var0.nextFloat() + var0.nextFloat() - 1.0F) * 0.15F;
        var2 = MathHelper.a(Math.round(var2 + var2 * var7), 1, 2147483647);
        List<WeightedEnchant> var8 = getAvailableEnchantmentResults(var2, var1, var3);
        if (!var8.isEmpty()) {
          Objects.requireNonNull(var4);
          WeightedRandom.getRandomItem(var0, var8).ifPresent(var4::add);
          while (var0.nextInt(50) <= var2) {
            if (!var4.isEmpty())
            	filterCompatibleEnchantments(var8, var4.get(var4.size()-1)); 
            if (var8.isEmpty())
              break; 
            Objects.requireNonNull(var4);
            WeightedRandom.getRandomItem(var0, var8).ifPresent(var4::add);
            var2 /= 2;
          } 
        } 
        return var4;
      }
	
    public List<WeightedEnchant> getAvailableEnchantmentResults(int var0, net.minecraft.world.item.ItemStack var1, boolean var2) {
    	List<WeightedEnchant> var3 = Lists.newArrayList();
    	//Item var4 = var1.c();
    	boolean var5 = var1.a(Items.om);
    	for (Enchantment var7 : IRegistry.V) {	
    		if (var7.b() && !var2) continue; 
    		if (!var7.i()) continue; 
    		if (!var7.a(var1) && !var5) continue; 
    		for (int var8 = var7.a(); var8 > var7.e() - 1; var8--) {
    			if (var0 >= var7.a(var8) && var0 <= var7.b(var8)) {
    				var3.add(new WeightedEnchant(var7, var8));
    				break;
    			} 
    		} 
    	} 
    	return var3;
    }
}
