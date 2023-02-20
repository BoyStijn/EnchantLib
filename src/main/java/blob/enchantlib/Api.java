package blob.enchantlib;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.chat.ChatComponentUtils;
import net.minecraft.network.chat.IChatMutableComponent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_19_R2.enchantments.CraftEnchantment;
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftItemStack;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.plugin.Plugin;
import net.minecraft.network.chat.ChatModifier;
import net.minecraft.EnumChatFormat;
import net.minecraft.advancements.CriterionTriggers;
import net.minecraft.core.Holder.c;
import net.minecraft.core.IRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.util.MathHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeBase;
import net.minecraft.world.entity.ai.attributes.AttributeMapBase;
import net.minecraft.world.entity.ai.attributes.AttributeModifiable;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemEnchantedBook;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentDurability;
import net.minecraft.world.item.enchantment.EnchantmentManager;
import net.minecraft.world.item.enchantment.Enchantments;

import com.google.common.collect.Lists;

import blob.enchantlib.NMS.ConvertUtils;
import blob.enchantlib.NMS.NMSMapping;
import blob.enchantlib.NMS.NMSMapping.LootTable_Fields;
import blob.enchantlib.NMS.NMSMapping.E_Insentient_Fields;
import blob.enchantlib.NMS.NMSMapping.GoalType;
import blob.enchantlib.NMS.NMSMapping.Holder_Methodes;

public class Api {

	private ArrayList<Enchantment> CustomEnch = new ArrayList<Enchantment>();
	private HashMap<Enchantment, String> pluginmap = new HashMap<Enchantment, String>();
	private HashMap<Enchantment, String> NameMap = new HashMap<Enchantment, String>();
	private HashMap<CustomEnchantment, EnchantWrapper> WrapMap = new HashMap<CustomEnchantment, EnchantWrapper>();
	private HashMap<ItemStack , Set<Entry<org.bukkit.enchantments.Enchantment, Integer>>> LoreCache = new HashMap<ItemStack , Set<Entry<org.bukkit.enchantments.Enchantment, Integer>>>();
	private HashMap<Integer, Method> LoreRemoveHandler = new HashMap<Integer, Method>();
	private HashMap<Integer, Method> LoreAdderHandler = new HashMap<Integer, Method>();
	
	public void registerEnchants(NamespacedKey key, Enchantment ench, String name) {
	    
		NMSMapping.getField(LootTable_Fields.BRegistryLock).setField(null, true);
		NMSMapping.getField(LootTable_Fields.RegistryLock).setField(BuiltInRegistries.g, false);
		//[Enchantment Registry] public static final IRegistry<Enchantment> W = a(i, registry -> Enchantments.x); | IRegistry - 195
          	
    	c<Enchantment> e = IRegistry.b(BuiltInRegistries.g, new MinecraftKey(key.toString()), ench); //Register to Vanilla
    	NMSMapping.getMethod(Holder_Methodes.Enchantment_Bind).execute(e, ench);
    	//[Enchantment Registry] public static final IRegistry<Enchantment> W = a(i, registry -> Enchantments.x); | IRegistry - 195
    	org.bukkit.enchantments.Enchantment.registerEnchantment((org.bukkit.enchantments.Enchantment)new CraftEnchantment(ench));  // Register to Bukkit
        NameMap.put(ench, name); //Enchantment Name
        pluginmap.put(ench, key.getNamespace()); //Enchantment NameSpace
        CustomEnch.add(ench); //Custom Enchant List
        
        EnchantLib.Instance.getLogger().log(Level.INFO, "Registered enchantment " + String.valueOf(e));
        EnchantLib.Instance.getLogger().log(Level.INFO, "Registered key " + key.toString());

	}
	
	public boolean instanceofNMS(ItemStack i, Class<?> c) {
		return c.isInstance(CraftItemStack.asNMSCopy(i).c());
	}
	
	public void registerEnchants(NamespacedKey key, CustomEnchantment enchant, String name) {
		Enchantment ench = new EnchantWrapper(enchant);
		WrapMap.put(enchant, (EnchantWrapper) ench);
	    registerEnchants(key, ench, name);
	}
	
	public NBTTagList getLore(ItemStack  i) {
		net.minecraft.world.item.ItemStack ni = getNMSItem(i);
		if (ni == null) return null;
		NBTTagCompound tag = ni.u(); //[getNBTData] public NBTTagCompound u() { | ItemStack - 769
		if (tag == null) return null;
		if (tag.e("display")) { //[HasTag] public boolean e(String key) { | NBTTagCompound - 266
			NBTTagCompound display = tag.p("display"); //[getCompoundTag] public NBTTagCompound p(String key) { | NBTTagCompound - 394
			if (display.e("Lore")) { //[HasTag] public boolean e(String key) { | NBTTagCompound - 266
				 return display.c("Lore", 8); //[getList] public NBTTagList c(String key, int type) { | NBTTagCompound - 406
			}
		}
		return null;
	}
	
	public ItemStack applyLore(ItemStack  i, NBTTagList lore) {
		net.minecraft.world.item.ItemStack ni = getNMSItem(i);
		if (ni == null) return null;
		NBTTagCompound tag = ni.u(); //[getNBTData] public NBTTagCompound u() { | ItemStack - 769
		if (tag == null) return null;
		NBTTagCompound display = tag.p("display"); //[getCompoundTag] public NBTTagCompound p(String key) { | NBTTagCompound - 394
		display.a("Lore", lore); //[setTag] public NBTBase a(String key, NBTBase element) { | NBTTagCompound - 163
		tag.a("display", display); //[setTag] public NBTBase a(String key, NBTBase element) { | NBTTagCompound - 163
		ni.c(tag); //[setNBT] public void c(@Nullable NBTTagCompound nbt) { | ItemStack - 841
		return CraftItemStack.asCraftMirror(ni);
	}
	
	public Boolean isCustomEnchant(Enchantment ench) {
		if (CustomEnch.contains(ench)) return true;
		return false;
	}
	
	private boolean hasLoreHandler(Enchantment ce) {
		Plugin pl = Bukkit.getPluginManager().getPlugin(pluginmap.get(ce));
		if (pl == null) return false;
		Method rem = getLoreRemover(pl);
		Method add = getLoreAdder(pl);
		if (rem == null) return false;
		if (add == null) return false;
		return true;
	}
	
	private Method getLoreRemover(Plugin pl) {
		return LoreRemoveHandler.computeIfAbsent(pl.hashCode(), (code) -> {
			Method[] meth = pl.getClass().getDeclaredMethods();
			for (int i = 0; i < meth.length; i++) {
				if (meth[i].getReturnType() != boolean.class) continue;
				if (meth[i].getParameterCount() != 2) continue;
				if (!meth[i].getParameterTypes()[0].equals(Enchantment.class)) continue;
				if (!meth[i].getParameterTypes()[1].equals(IChatMutableComponent.class)) continue;
				if (meth[i].getName() != "RemoveEnchantmentLore") continue;
				return meth[i];
			}
			return null;
		});
	}
	
	private Method getLoreAdder(Plugin pl) {
		return LoreAdderHandler.computeIfAbsent(pl.hashCode(), (code) -> {
			Method[] meth = pl.getClass().getDeclaredMethods();
			for (int i = 0; i < meth.length; i++) {
				if (meth[i].getReturnType() != IChatMutableComponent.class) continue;
				if (meth[i].getParameterCount() != 2) continue;
				if (!meth[i].getParameterTypes()[0].equals(Enchantment.class)) continue;
				if (!meth[i].getParameterTypes()[1].equals(int.class)) continue;
				if (meth[i].getName() != "AddEnchantmentLore") continue;
				return meth[i];
			}
			return null;
		});
	}
	
	private boolean removeLore(Enchantment ce, IChatBaseComponent s) {
		try {
			Plugin pl = Bukkit.getPluginManager().getPlugin(pluginmap.get(ce));
			if (pl == null) return false;
			return (boolean) getLoreRemover(pl).invoke(pl, ce, s);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			EnchantLib.Instance.getLogger().log(Level.WARNING, pluginmap.get(ce) + " has invalid lore removal methode");
			return false;
		}
	}
	
	private IChatBaseComponent addLore(Enchantment ce, int lvl) {
		try {
			Plugin pl = Bukkit.getPluginManager().getPlugin(pluginmap.get(ce));
			if (pl == null) return IChatBaseComponent.b("");
			return (IChatBaseComponent) getLoreAdder(pl).invoke(pl, ce, lvl);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			EnchantLib.Instance.getLogger().log(Level.WARNING, pluginmap.get(ce) + " has invalid lore adder methode");
			return IChatBaseComponent.b("");
		}
	}
	
	private boolean compareEnchant(Set<Entry<org.bukkit.enchantments.Enchantment, Integer>> set1, Set<Entry<org.bukkit.enchantments.Enchantment, Integer>> set2) {
		if (set1.size() != set2.size()) return false;
		List<Entry<org.bukkit.enchantments.Enchantment, Integer>> arr1 = new ArrayList<>(set1);
		List<Entry<org.bukkit.enchantments.Enchantment, Integer>> arr2 = new ArrayList<>(set2);
		for (int i = 0; i < set1.size(); i++) {
			if (!arr1.get(i).getKey().equals(arr2.get(i).getKey())) return false;
			if (!arr1.get(i).getValue().equals(arr2.get(i).getValue())) return false;
		}
		return true;
	}
	
	public net.minecraft.world.item.ItemStack getNMSItem(ItemStack i) {
		try {
			Field handleField = CraftItemStack.class.getDeclaredField("handle");
			handleField.setAccessible(true);
			return (net.minecraft.world.item.ItemStack) handleField.get((CraftItemStack)i);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void ApplyCustomLore(ItemStack  item, Map<org.bukkit.enchantments.Enchantment, Integer> enchs) {
		if (item == null) return;
		if (this.LoreCache.containsKey(item) && compareEnchant(this.LoreCache.get(item), enchs.entrySet())) return;
		
		List<IChatBaseComponent> oldLore = new ArrayList<IChatBaseComponent>();
		List<IChatBaseComponent> newLore = new ArrayList<IChatBaseComponent>();
		
		NBTTagList nbttaglist = getLore(item);
		if (nbttaglist != null)
        for (int j = 0; j < nbttaglist.size(); j++) {
        	String s = nbttaglist.j(j); //[getString] public String j(int index) { | NBTTagList - 257
        	try {
        		IChatMutableComponent comp1 = IChatBaseComponent.ChatSerializer.a(s); //[fromJson] public static IChatMutableComponent a(String json) { | ChatSerializer - 514 
        		if (comp1 != null) oldLore.add(ChatComponentUtils.a(comp1, ChatModifier.a.a(EnumChatFormat.f).b(true))); 
        		//[mergeStyle] public static IChatMutableComponent a(IChatMutableComponent text, ChatModifier style) { | ChatComponentUtils - 24
        		//[withColor] public ChatModifier a(@Nullable EnumChatFormat color) { | ChatModifier - 134
        		//[italic] public ChatModifier b(@Nullable Boolean italic) { | ChatModifier - 146
          } catch (Exception exception) {} 
        }
		
		if (!oldLore.isEmpty()) {
			ArrayList<IChatBaseComponent> toRemove = new ArrayList<IChatBaseComponent>();
			for (IChatBaseComponent s : oldLore) {
				for (Enchantment ce : CustomEnch) {
					if (hasLoreHandler(ce)) {
						if(removeLore(ce, s)) toRemove.add(s);
						continue;
					}
					if (s.b().toString().contains(NameMap.get(ce))) {
						toRemove.add(s);
					}
				}
			}
			oldLore.removeAll(toRemove);
			newLore.addAll(oldLore);
		}
		
		
		for (Enchantment ce : CustomEnch) {
			org.bukkit.enchantments.Enchantment e = getBukkitEnchant(ce);
			if (enchs.containsKey(e)) {
				if (hasLoreHandler(ce)) {
					newLore.add(addLore(ce, enchs.get(e)));
					continue;
				}
				EnumChatFormat form;
				if (ce.c()) { form = EnumChatFormat.m; } else { form = EnumChatFormat.h; } 
				IChatMutableComponent chat = IChatBaseComponent.b(NameMap.get(ce));
				chat.a(form);
				if (enchs.get(e) != 1 || ce.a() != 1) {
					IChatMutableComponent m = IChatBaseComponent.c("enchantment.level." + enchs.get(e));
					m.a(form);
					chat.f(" ").b(m); 
				}
				newLore.add(chat);
			}
		}
		
		NBTTagList newTag = new NBTTagList();
		for (IChatBaseComponent b : newLore) {
			String s = IChatBaseComponent.ChatSerializer.a(b);
			//[toJson] public static IChatMutableComponent a(IChatBaseComponent json) { | ChatSerializer - 501 
			EnchantLib.Instance.getLogger().log(Level.INFO, s);
			newTag.add(NBTTagString.a(s));
		}
		
		this.LoreCache.put(item, enchs.entrySet());
		applyLore(item, newTag);
	}
	
	public void damageItem(int amount, ItemStack item, LivingEntity ent) {
		net.minecraft.world.item.ItemStack NItem = getNMSItem(item);
		EntityLiving Nent = ConvertUtils.toNMS(ent);
		RandomSource random = Nent.s.s_();
		
		if (!NItem.h())
		      return; 
		    if (amount > 0) {
		      int i = EnchantmentManager.a(Enchantments.w, NItem);
		      int k = 0;
		      for (int l = 0; i > 0 && l < amount; l++) {
		        if (EnchantmentDurability.a(NItem, i, random))
		          k++; 
		      } 
		      amount -= k;
		      if (Nent instanceof EntityPlayer) {
		        EntityPlayer serverPlayer = (EntityPlayer)Nent;
		        PlayerItemDamageEvent event = new PlayerItemDamageEvent((Player)serverPlayer.getBukkitEntity(), CraftItemStack.asCraftMirror(NItem), amount);
		        event.getPlayer().getServer().getPluginManager().callEvent(event);
		        if (amount != event.getDamage() || event.isCancelled())
		          event.getPlayer().updateInventory(); 
		        if (event.isCancelled())
		          return; 
		        amount = event.getDamage();
		      }
		      if (amount <= 0)
		        return; 
		    } 
		    if (Nent instanceof EntityPlayer) {
		      EntityPlayer serverPlayer = (EntityPlayer)Nent;
		      if (amount != 0)
		        CriterionTriggers.t.a(serverPlayer, NItem, NItem.j() + amount); 
		    } 
		    int j = NItem.j() + amount;
		    Damageable meta = ((Damageable)item.getItemMeta());
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
	
	public EntityLiving getNSMEntity(LivingEntity e) {
		return ConvertUtils.toNMS(e);
	}

	public EntityCreature getNSMEntity(Mob e) {
		return (EntityCreature) ConvertUtils.toNMS(e);
	}
	
	public void overrideGoals(Mob e, Map<PathfinderGoal, Integer> goals, Map<PathfinderGoal, Integer> targets) {
		NMSMapping.SetGoals(e, GoalType.Goals, goals);
		NMSMapping.SetGoals(e, GoalType.Targets, targets);
	}
	
	@SuppressWarnings("unchecked")
	public void addAttributes(Mob e, AttributeBase b, double value) {
		EntityInsentient c = ConvertUtils.toNMS(e);
		
	    AttributeMapBase base = (AttributeMapBase) NMSMapping.getField(E_Insentient_Fields.AttributeList).getField(c);
		Map<AttributeBase, AttributeModifiable> bmap = (Map<AttributeBase, AttributeModifiable>) NMSMapping.getField(E_Insentient_Fields.AttributeMap).getField(base);
    	 if (bmap.containsKey(b)) {
    		 bmap.get(b).a(value);
    	 } else {
	    	 AttributeModifiable m = new AttributeModifiable(b, mod -> {});
	    	 m.a(value);
		     bmap.put(b, m);
    	 }
	}
	
	public net.minecraft.world.item.ItemStack enchantItem(RandomSource var0, net.minecraft.world.item.ItemStack target, int var2, boolean var3) {
		List<WeightedEnchant> list = selectEnchantment(var0, target, var2, var3);
	    boolean bl = target.a(Items.pA);
	    if (bl)
	      target = new net.minecraft.world.item.ItemStack(Items.ta); 
	    for (WeightedEnchant enchantmentInstance : list) {
	      if (bl) {
	    	  enchantbook(target, enchantmentInstance);
	        continue;
	      } 
	      target.a(enchantmentInstance.enchantment, enchantmentInstance.level);
	    } 
	    return target;
	}
	
	public void filterCompatibleEnchantments(List<WeightedEnchant> possibleEntries, WeightedEnchant pickedEntry) {
	    Iterator<WeightedEnchant> iterator = possibleEntries.iterator();
	    while (iterator.hasNext()) {
	      if (!pickedEntry.enchantment.b((iterator.next()).enchantment))
	        iterator.remove(); 
	    } 
    }
    
	public void enchantbook(net.minecraft.world.item.ItemStack stack, WeightedEnchant entry) {
		NBTTagList listTag = ItemEnchantedBook.d(stack);
	    boolean bl = true;
	    MinecraftKey resourceLocation = EnchantmentManager.a(entry.enchantment);
	    for (int i = 0; i < listTag.size(); i++) {
	      NBTTagCompound compoundTag = listTag.a(i);
	      MinecraftKey resourceLocation2 = EnchantmentManager.b(compoundTag);
	      if (resourceLocation2 != null && resourceLocation2.equals(resourceLocation)) {
	        if (EnchantmentManager.a(compoundTag) < entry.level)
	          EnchantmentManager.a(compoundTag, entry.level); 
	        bl = false;
	        break;
	      } 
	    } 
	    if (bl)
	      listTag.add(EnchantmentManager.a(resourceLocation, entry.level)); 
	    stack.v().a("StoredEnchantments", listTag);
	}
	
	public List<WeightedEnchant> selectEnchantment(RandomSource random, net.minecraft.world.item.ItemStack stack, int level, boolean treasureAllowed) {
		
		List<WeightedEnchant> list = Lists.newArrayList();
        Item item = stack.c();
        int i = item.c();
        if (i <= 0) {
            return list;
        } else {
            level += 1 + random.a(i / 4 + 1) + random.a(i / 4 + 1);
            float f = (random.i() + random.i() - 1.0F) * 0.15F;
            level = MathHelper.a(Math.round((float)level + (float)level * f), 1, Integer.MAX_VALUE);
            List<WeightedEnchant> list2 = getAvailableEnchantmentResults(level, stack, treasureAllowed);
            if (!list2.isEmpty()) {
                WeightedRandom.getRandomItem(random, list2).ifPresent(list::add);

                while(random.a(50) <= level) {
                    if (!list.isEmpty()) {
                        filterCompatibleEnchantments(list2, list.get(list.size()-1));
                    }

                    if (list2.isEmpty()) {
                        break;
                    }

                    WeightedRandom.getRandomItem(random, list2).ifPresent(list::add);
                    level /= 2;
                }
            }

            return list;
        }
	}
	
    public List<WeightedEnchant> getAvailableEnchantmentResults(int power, net.minecraft.world.item.ItemStack stack, boolean treasureAllowed) {
    	List<WeightedEnchant> list = Lists.newArrayList();
        Item item = stack.c();
        boolean bl = stack.a(Items.pA);
        for (Enchantment enchantment : BuiltInRegistries.g) {
        boolean isEnchantable = (enchantment.e.a(item) && enchantment.a(stack));
          if ((enchantment.b() && !treasureAllowed) || !enchantment.i() || (!isEnchantable && !bl))
            continue; 
          for (int i = enchantment.a(); i > enchantment.e() - 1; i--) {
            if (power >= enchantment.a(i) && power <= enchantment.b(i)) {
              list.add(new WeightedEnchant(enchantment, i));
              break;
            } 
          } 
        } 
        return list;
    }
}
