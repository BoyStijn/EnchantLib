package blob.enchantlib.loot;


import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import blob.enchantlib.EnchantLib;

import java.util.List;
import java.util.Set;

import net.minecraft.util.ChatDeserializer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
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
	  
	  LootEnchant(LootItemCondition[] conditions, NumberProvider range, boolean treasureEnchantmentsAllowed) {
	    super(conditions);
	    this.a = range;
	    this.b = treasureEnchantmentsAllowed;
	  }
	  
	  @Override
	  public LootItemFunctionType a() {
	    return LootItemFunctions.c;
	  }
	  
	  @Override
	  public Set<LootContextParameter<?>> b() {
	    return this.a.b();
	  }
	  
	  @Override
		public ItemStack apply(ItemStack t, LootTableInfo u) {
		  RandomSource rand = u.a();
		  return EnchantLib.API.enchantItem(rand, t, this.a.a(u), this.b);
		}
	  
	  @Override
	  public ItemStack a(ItemStack stack, LootTableInfo context) {
	    return this.apply(stack, context);
	  }
	  
	  public static a a(NumberProvider range) {
	    return new a(range);
	  }
	  
	  public static class a extends LootItemFunctionConditional.a<a> {
	    private final NumberProvider a;
	    private boolean b;
	    private final List<LootItemCondition> c = Lists.newArrayList();
	    
	    public a(NumberProvider range) {
	      this.a = range;
	    }
	    
	    public a e() {
	      this.b = true;
	      return this;
	    }
	    
	    @Override
	    public LootItemFunction b() {
	      return new LootEnchant(g(), this.a, this.b);
	    }

		@Override
		public a b(LootItemCondition.a arg0) {
			this.c.add(arg0.build());
	        return d();
		}

		@Override
		public a d() {
			return this;
		}

		@Override
		protected a c() {
			return d();
		}
	  }
	  
	  public static class b extends LootItemFunctionConditional.c<LootEnchant> {
	    @Override
	    public void a(JsonObject json, LootEnchant object, JsonSerializationContext context) {
	      super.a(json, object, context);
	      json.add("levels", context.serialize(object.a));
	      json.addProperty("treasure", Boolean.valueOf(object.b));
	    }
	    
	    @Override
	    public final LootEnchant a(JsonObject var0, JsonDeserializationContext var1) {
	        LootItemCondition[] var2 = ChatDeserializer.a(var0, "conditions", new LootItemCondition[0], var1, LootItemCondition[].class);
	        return b(var0, var1, var2);
	    }
	    
	    @Override
	    public LootEnchant b(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] lootItemConditions) {
	      NumberProvider numberProvider = (NumberProvider)ChatDeserializer.a(jsonObject, "levels", jsonDeserializationContext, NumberProvider.class);
	      boolean bl = ChatDeserializer.a(jsonObject, "treasure", false);
	      return new LootEnchant(lootItemConditions, numberProvider, bl);
	    }
	  }
}

