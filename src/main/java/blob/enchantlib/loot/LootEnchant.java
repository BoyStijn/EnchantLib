package blob.enchantlib.loot;


import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import blob.enchantlib.EnchantLib;

import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.util.ChatDeserializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentManager;
import net.minecraft.world.level.storage.loot.LootTableInfo;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionConditional;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraft.world.level.storage.loot.parameters.LootContextParameter;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

public class LootEnchant extends LootItemFunctionConditional {
	final NumberProvider a;
	  
	  final boolean b;
	  
	  LootEnchant(LootItemCondition[] var0, NumberProvider var1, boolean var2) {
	    super(var0);
	    this.a = var1;
	    this.b = var2;
	  }
	  
	  public LootItemFunctionType a() {
	    return LootItemFunctions.c;
	  }
	  
	  public Set<LootContextParameter<?>> b() {
	    return this.a.b();
	  }
	  
	  public ItemStack a(ItemStack var0, LootTableInfo var1) {
	    Random var2 = var1.a();
	    return EnchantLib.API.enchantItem(var2, var0, this.a.a(var1), this.b); //override
	  }
	  
	  public static class a extends LootItemFunctionConditional.a<a> {
	    private final NumberProvider a;
	    private final List<LootItemCondition> c = Lists.newArrayList();
	    private boolean b;
	    
	    public a b(LootItemCondition.a var0) {
	    	this.c.add(var0.build());
	        return d();
	      }
	    
	    public a(NumberProvider var0) {
	      this.a = var0;
	    }
	    
	    protected a d() {
	      return this;
	    }
	    
	    public a e() {
	      this.b = true;
	      return this;
	    }
	    
	    public LootItemFunction b() {
	      return new LootEnchant(g(), this.a, this.b);
	    }

		@Override
		public a c() {
			return d();
		}
	  }
	  
	  public static a a(NumberProvider var0) {
	    return new a(var0);
	  }
	  
	  public static class b extends LootItemFunctionConditional.c<LootEnchant> {
	    public void a(JsonObject var0, LootEnchant var1, JsonSerializationContext var2) {
	      super.a(var0, var1, var2);
	      var0.add("levels", var2.serialize(var1.a));
	      var0.addProperty("treasure", Boolean.valueOf(var1.b));
	    }
	    
	    @Override
	    public LootEnchant b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
	      NumberProvider var3 = (NumberProvider)ChatDeserializer.a(var0, "levels", var1, NumberProvider.class);
	      boolean var4 = ChatDeserializer.a(var0, "treasure", false);
	      return new LootEnchant(var2, var3, var4);
	    }

	    public final LootEnchant a(JsonObject var0, JsonDeserializationContext var1) {
	        LootItemCondition[] var2 = (LootItemCondition[])ChatDeserializer.a(var0, "conditions", new LootItemCondition[0], var1, LootItemCondition[].class);
	        return b(var0, var1, var2);
	      }
	    
	  }

	@Override
	public ItemStack apply(ItemStack t, LootTableInfo u) {
		Random var2 = u.a();
	    return EnchantmentManager.a(var2, t, this.a.a(u), this.b);
	}
}
