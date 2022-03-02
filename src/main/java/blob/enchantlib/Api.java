package blob.enchantlib;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.attributes.AttributeBase;
import net.minecraft.world.entity.ai.attributes.AttributeMapBase;
import net.minecraft.world.entity.ai.attributes.AttributeModifiable;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentDurability;
import com.google.common.collect.Sets;

public class Api {

	private ArrayList<Enchantment> CustomEnch = new ArrayList<Enchantment>();
	private HashMap<Enchantment, String> NameMap = new HashMap<Enchantment, String>();
	
	public void registerEnchants(NamespacedKey key, Enchantment ench, String name) {
	    try{
	        try {
	            Field f = org.bukkit.enchantments.Enchantment.class.getDeclaredField("acceptingNew");
	            f.setAccessible(true);
	            f.set(null, true);
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
		ArrayList<String> lore = new ArrayList<String>();
		for (Enchantment ce : CustomEnch) {
			org.bukkit.enchantments.Enchantment e = getBukkitEnchant(ce);
			if (enchs.containsKey(e)) {
				ChatColor c = ChatColor.GRAY;
				if (ce.c()) c = ChatColor.RED;
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
}
