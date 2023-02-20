package blob.enchantlib.villagers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import blob.enchantlib.EnchantLib;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.SystemUtils;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.server.level.WorldServer;
import net.minecraft.tags.TagKey;
import net.minecraft.util.MathHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.IMerchantRecipeOption;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.EnumColor;
import net.minecraft.world.item.IDyeable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDye;
import net.minecraft.world.item.ItemEnchantedBook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemSuspiciousStew;
import net.minecraft.world.item.ItemWorldMap;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewer;
import net.minecraft.world.item.alchemy.PotionRegistry;
import net.minecraft.world.item.alchemy.PotionUtil;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.WeightedRandomEnchant;
import net.minecraft.world.item.trading.MerchantRecipe;
import net.minecraft.world.level.IMaterial;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapIcon;
import net.minecraft.world.level.saveddata.maps.WorldMap;

public class TradesOverride {
  
  public static void overrideVillager() {
	  Map<VillagerProfession, Int2ObjectMap<IMerchantRecipeOption[]>> m = (Map<VillagerProfession, Int2ObjectMap<IMerchantRecipeOption[]>>)SystemUtils.a(new HashMap<VillagerProfession, Int2ObjectMap<IMerchantRecipeOption[]>>(), map -> {
		  map.put(VillagerProfession.h, a(ImmutableMap.of((1), new IMerchantRecipeOption[] { new b((IMaterial)Items.of, 20, 16, 2), new b((IMaterial)Items.nj, 10, 16, 2), new g((IMaterial)Items.pK, 6, Items.pO, 6, 16, 1), new h(Items.ps, 3, 1, 16, 1) }, (2), new IMerchantRecipeOption[] { new b((IMaterial)Items.pK, 15, 16, 10), new g((IMaterial)Items.pL, 6, Items.pP, 6, 16, 5), new h(Items.uS, 2, 1, 5) }, (3), new IMerchantRecipeOption[] { new b((IMaterial)Items.pL, 13, 16, 20), new e(Items.pG, 3, 3, 10, 0.2F) }, (4), new IMerchantRecipeOption[] { new b((IMaterial)Items.pM, 6, 12, 30) }, (5), new IMerchantRecipeOption[] { new b((IMaterial)Items.pN, 4, 12, 30), new c(1, 12, 30, new ImmutableMap.Builder<VillagerType, Item>().put(VillagerType.c, Items.mL).put(VillagerType.g, Items.mN).put(VillagerType.e, Items.mN).put(VillagerType.a, Items.mR).put(VillagerType.b, Items.mR).put(VillagerType.d, Items.mT).put(VillagerType.f, Items.mV).build()) })));
		  map.put(VillagerProfession.i, a(ImmutableMap.of((1), new IMerchantRecipeOption[] { new b((IMaterial)Items.oc, 32, 16, 2), new h(Items.ni, 1, 16, 1), new g((IMaterial)Blocks.I, 10, Items.oJ, 10, 12, 1) }, (2), new IMerchantRecipeOption[] { new b((IMaterial)Items.oJ, 26, 12, 10), new h(Items.nh, 2, 1, 5) }, (3), new IMerchantRecipeOption[] { new b((IMaterial)Items.of, 14, 16, 20), new h(Items.uu, 3, 1, 10) }, (4), new IMerchantRecipeOption[] { new b((IMaterial)Items.og, 24, 16, 30), new e(Items.nh, 2, 3, 15) }, (5), new IMerchantRecipeOption[] { new b((IMaterial)Items.ln, 8, 12, 30), new e(Items.uu, 3, 3, 15), new j(Items.ni, 5, Items.tS, 5, 2, 12, 30) })));
		  map.put(VillagerProfession.c, a(ImmutableMap.of((1), new IMerchantRecipeOption[] { new b((IMaterial)Items.nj, 15, 16, 2), new h(new ItemStack((IMaterial)Items.ov), 7, 1, 12, 1, 0.2F), new h(new ItemStack((IMaterial)Items.ow), 4, 1, 12, 1, 0.2F), new h(new ItemStack((IMaterial)Items.ot), 5, 1, 12, 1, 0.2F), new h(new ItemStack((IMaterial)Items.ou), 9, 1, 12, 1, 0.2F) }, (2), new IMerchantRecipeOption[] { new b((IMaterial)Items.nr, 4, 12, 10), new h(new ItemStack((IMaterial)Items.uN), 36, 1, 12, 5, 0.2F), new h(new ItemStack((IMaterial)Items.os), 1, 1, 12, 5, 0.2F), new h(new ItemStack((IMaterial)Items.or), 3, 1, 12, 5, 0.2F) }, (3), new IMerchantRecipeOption[] { new b((IMaterial)Items.pl, 1, 12, 20), new b((IMaterial)Items.nl, 1, 12, 20), new h(new ItemStack((IMaterial)Items.op), 1, 1, 12, 10, 0.2F), new h(new ItemStack((IMaterial)Items.oq), 4, 1, 12, 10, 0.2F), new h(new ItemStack((IMaterial)Items.tU), 5, 1, 12, 10, 0.2F) }, (4), new IMerchantRecipeOption[] { new e(Items.oz, 14, 3, 15, 0.2F), new e(Items.oA, 8, 3, 15, 0.2F) }, (5), new IMerchantRecipeOption[] { new e(Items.ox, 8, 3, 30, 0.2F), new e(Items.oy, 16, 3, 30, 0.2F) })));
		  map.put(VillagerProfession.p, a(ImmutableMap.of((1), new IMerchantRecipeOption[] { new b((IMaterial)Items.nj, 15, 16, 2), new h(new ItemStack((IMaterial)Items.nQ), 3, 1, 12, 1, 0.2F), new e(Items.nN, 2, 3, 1) }, (2), new IMerchantRecipeOption[] { new b((IMaterial)Items.nr, 4, 12, 10), new h(new ItemStack((IMaterial)Items.uN), 36, 1, 12, 5, 0.2F) }, (3), new IMerchantRecipeOption[] { new b((IMaterial)Items.oJ, 24, 12, 20) }, (4), new IMerchantRecipeOption[] { new b((IMaterial)Items.nl, 1, 12, 30), new e(Items.nV, 12, 3, 15, 0.2F) }, (5), new IMerchantRecipeOption[] { new e(Items.nS, 8, 3, 30, 0.2F) })));
		  map.put(VillagerProfession.o, a(ImmutableMap.of((1), new IMerchantRecipeOption[] { new b((IMaterial)Items.nj, 15, 16, 2), new h(new ItemStack((IMaterial)Items.nG), 1, 1, 12, 1, 0.2F), new h(new ItemStack((IMaterial)Items.nE), 1, 1, 12, 1, 0.2F), new h(new ItemStack((IMaterial)Items.nF), 1, 1, 12, 1, 0.2F), new h(new ItemStack((IMaterial)Items.nH), 1, 1, 12, 1, 0.2F) }, (2), new IMerchantRecipeOption[] { new b((IMaterial)Items.nr, 4, 12, 10), new h(new ItemStack((IMaterial)Items.uN), 36, 1, 12, 5, 0.2F) }, (3), new IMerchantRecipeOption[] { new b((IMaterial)Items.oJ, 30, 12, 20), new e(Items.nQ, 1, 3, 10, 0.2F), new e(Items.nO, 2, 3, 10, 0.2F), new e(Items.nP, 3, 3, 10, 0.2F), new h(new ItemStack((IMaterial)Items.nW), 4, 1, 3, 10, 0.2F) }, (4), new IMerchantRecipeOption[] { new b((IMaterial)Items.nl, 1, 12, 30), new e(Items.nV, 12, 3, 15, 0.2F), new e(Items.nT, 5, 3, 15, 0.2F) }, (5), new IMerchantRecipeOption[] { new e(Items.nU, 13, 3, 30, 0.2F) })));
		  
      });
	  for (Entry<VillagerProfession, Int2ObjectMap<IMerchantRecipeOption[]>> e : m.entrySet()) {
		  VillagerTrades.a.put(e.getKey(), e.getValue());
	  }
  }
  
  private static Int2ObjectMap<IMerchantRecipeOption[]> a(ImmutableMap<Integer, IMerchantRecipeOption[]> map) {
    return new Int2ObjectOpenHashMap<IMerchantRecipeOption[]>(map);
  }
  
  static class a implements IMerchantRecipeOption {
	    private final Item a;
	    
	    private final int b;
	    
	    private final int c;
	    
	    private final int d;
	    
	    public a(Item item, int price) {
	      this(item, price, 12, 1);
	    }
	    
	    public a(Item item, int price, int maxUses, int experience) {
	      this.a = item;
	      this.b = price;
	      this.c = maxUses;
	      this.d = experience;
	    }
	    
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      ItemStack itemStack = new ItemStack((IMaterial)Items.nm, this.b);
	      ItemStack itemStack2 = new ItemStack((IMaterial)this.a);
	      if (this.a instanceof net.minecraft.world.item.ItemArmorColorable) {
	        List<ItemDye> list = Lists.newArrayList();
	        list.add(a(random));
	        if (random.i() > 0.7F)
	          list.add(a(random)); 
	        if (random.i() > 0.8F)
	          list.add(a(random)); 
	        itemStack2 = IDyeable.a(itemStack2, list);
	      } 
	      return new MerchantRecipe(itemStack, itemStack2, this.c, this.d, 0.2F);
	    }
	    
	    private static ItemDye a(RandomSource random) {
	      return ItemDye.a(EnumColor.a(random.a(16)));
	    }
	  }
	  
	  static class b implements IMerchantRecipeOption {
	    private final Item a;
	    
	    private final int b;
	    
	    private final int c;
	    
	    private final int d;
	    
	    private final float e;
	    
	    public b(IMaterial item, int price, int maxUses, int experience) {
	      this.a = item.l();
	      this.b = price;
	      this.c = maxUses;
	      this.d = experience;
	      this.e = 0.05F;
	    }
	    
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      ItemStack itemStack = new ItemStack((IMaterial)this.a, this.b);
	      return new MerchantRecipe(itemStack, new ItemStack((IMaterial)Items.nm), this.c, this.d, this.e);
	    }
	  }
	  
	  static class c implements IMerchantRecipeOption {
	    private final Map<VillagerType, Item> a;
	    
	    private final int b;
	    
	    private final int c;
	    
	    private final int d;
	    
	    public c(int count, int maxUses, int experience, Map<VillagerType, Item> map) {
	      BuiltInRegistries.y.s().filter(villagerType -> !map.containsKey(villagerType))
	        
	        .findAny().ifPresent(villagerType -> {
	            throw new IllegalStateException("Missing trade for villager type: " + BuiltInRegistries.y.b(villagerType));
	          });
	      this.a = map;
	      this.b = count;
	      this.c = maxUses;
	      this.d = experience;
	    }
	    
	    @Nullable
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      if (entity instanceof VillagerDataHolder) {
	        ItemStack itemStack = new ItemStack((IMaterial)this.a.get(((VillagerDataHolder)entity).fX().a()), this.b);
	        return new MerchantRecipe(itemStack, new ItemStack((IMaterial)Items.nm), this.c, this.d, 0.05F);
	      } 
	      return null;
	    }
	  }
	  
	  static class d implements IMerchantRecipeOption {
	    private final int a;
	    
	    public d(int experience) {
	      this.a = experience;
	    }
	    
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      List<Enchantment> list = (List<Enchantment>)BuiltInRegistries.g.s().filter(Enchantment::h).collect(Collectors.toList());
	      Enchantment enchantment = list.get(random.a(list.size()));
	      int i = MathHelper.a(random, enchantment.e(), enchantment.a());
	      ItemStack itemStack = ItemEnchantedBook.a(new WeightedRandomEnchant(enchantment, i));
	      int j = 2 + random.a(5 + i * 10) + 3 * i;
	      if (enchantment.b())
	        j *= 2; 
	      if (j > 64)
	        j = 64; 
	      return new MerchantRecipe(new ItemStack((IMaterial)Items.nm, j), new ItemStack((IMaterial)Items.pA), itemStack, 12, this.a, 0.2F);
	    }
	  }
	  
	  static class e implements IMerchantRecipeOption {
	    private final ItemStack a;
	    
	    private final int b;
	    
	    private final int c;
	    
	    private final int d;
	    
	    private final float e;
	    
	    public e(Item item, int basePrice, int maxUses, int experience) {
	      this(item, basePrice, maxUses, experience, 0.05F);
	    }
	    
	    public e(Item item, int basePrice, int maxUses, int experience, float multiplier) {
	      this.a = new ItemStack((IMaterial)item);
	      this.b = basePrice;
	      this.c = maxUses;
	      this.d = experience;
	      this.e = multiplier;
	    }
	    
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      int i = 5 + random.a(15);
	      ItemStack itemStack = EnchantLib.API.enchantItem(random, new ItemStack((IMaterial)this.a.c()), i, false);
	      int j = Math.min(this.b + i, 64);
	      ItemStack itemStack2 = new ItemStack((IMaterial)Items.nm, j);
	      return new MerchantRecipe(itemStack2, itemStack, this.c, this.d, this.e);
	    }
	  }
	  
	  static class g implements IMerchantRecipeOption {
	    private final ItemStack a;
	    
	    private final int b;
	    
	    private final int c;
	    
	    private final ItemStack d;
	    
	    private final int e;
	    
	    private final int f;
	    
	    private final int g;
	    
	    private final float h;
	    
	    public g(IMaterial item, int secondCount, Item sellItem, int sellCount, int maxUses, int experience) {
	      this(item, secondCount, 1, sellItem, sellCount, maxUses, experience);
	    }
	    
	    public g(IMaterial item, int secondCount, int price, Item sellItem, int sellCount, int maxUses, int experience) {
	      this.a = new ItemStack(item);
	      this.b = secondCount;
	      this.c = price;
	      this.d = new ItemStack((IMaterial)sellItem);
	      this.e = sellCount;
	      this.f = maxUses;
	      this.g = experience;
	      this.h = 0.05F;
	    }
	    
	    @Nullable
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      return new MerchantRecipe(new ItemStack((IMaterial)Items.nm, this.c), new ItemStack((IMaterial)this.a.c(), this.b), new ItemStack((IMaterial)this.d.c(), this.e), this.f, this.g, this.h);
	    }
	  }
	  
	  static class h implements IMerchantRecipeOption {
	    private final ItemStack a;
	    
	    private final int b;
	    
	    private final int c;
	    
	    private final int d;
	    
	    private final int e;
	    
	    private final float f;
	    
	    public h(Block block, int price, int count, int maxUses, int experience) {
	      this(new ItemStack((IMaterial)block), price, count, maxUses, experience);
	    }
	    
	    public h(Item item, int price, int count, int experience) {
	      this(new ItemStack((IMaterial)item), price, count, 12, experience);
	    }
	    
	    public h(Item item, int price, int count, int maxUses, int experience) {
	      this(new ItemStack((IMaterial)item), price, count, maxUses, experience);
	    }
	    
	    public h(ItemStack stack, int price, int count, int maxUses, int experience) {
	      this(stack, price, count, maxUses, experience, 0.05F);
	    }
	    
	    public h(ItemStack stack, int price, int count, int maxUses, int experience, float multiplier) {
	      this.a = stack;
	      this.b = price;
	      this.c = count;
	      this.d = maxUses;
	      this.e = experience;
	      this.f = multiplier;
	    }
	    
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      return new MerchantRecipe(new ItemStack((IMaterial)Items.nm, this.b), new ItemStack((IMaterial)this.a.c(), this.c), this.d, this.e, this.f);
	    }
	  }
	  
	  static class i implements IMerchantRecipeOption {
	    final MobEffectList a;
	    
	    final int b;
	    
	    final int c;
	    
	    private final float d;
	    
	    public i(MobEffectList effect, int duration, int experience) {
	      this.a = effect;
	      this.b = duration;
	      this.c = experience;
	      this.d = 0.05F;
	    }
	    
	    @Nullable
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      ItemStack itemStack = new ItemStack((IMaterial)Items.uv, 1);
	      ItemSuspiciousStew.a(itemStack, this.a, this.b);
	      return new MerchantRecipe(new ItemStack((IMaterial)Items.nm, 1), itemStack, 12, this.c, this.d);
	    }
	  }
	  
	  static class j implements IMerchantRecipeOption {
	    private final ItemStack a;
	    
	    private final int b;
	    
	    private final int c;
	    
	    private final int d;
	    
	    private final int e;
	    
	    private final Item f;
	    
	    private final int g;
	    
	    private final float h;
	    
	    public j(Item arrow, int secondCount, Item tippedArrow, int sellCount, int price, int maxUses, int experience) {
	      this.a = new ItemStack((IMaterial)tippedArrow);
	      this.c = price;
	      this.d = maxUses;
	      this.e = experience;
	      this.f = arrow;
	      this.g = secondCount;
	      this.b = sellCount;
	      this.h = 0.05F;
	    }
	    
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      ItemStack itemStack = new ItemStack((IMaterial)Items.nm, this.c);
	      List<PotionRegistry> list = (List<PotionRegistry>)BuiltInRegistries.j.s().filter(potionx -> (!potionx.a().isEmpty() && PotionBrewer.a(potionx))).collect(Collectors.toList());
	      PotionRegistry potion = list.get(random.a(list.size()));
	      ItemStack itemStack2 = PotionUtil.a(new ItemStack((IMaterial)this.a.c(), this.b), potion);
	      return new MerchantRecipe(itemStack, new ItemStack((IMaterial)this.f, this.g), itemStack2, this.d, this.e, this.h);
	    }
	  }
	  
	  static class k implements IMerchantRecipeOption {
	    private final int a;
	    
	    private final TagKey<Structure> b;
	    
	    private final String c;
	    
	    private final MapIcon.Type d;
	    
	    private final int e;
	    
	    private final int f;
	    
	    public k(int price, TagKey<Structure> structure, String nameKey, MapIcon.Type iconType, int maxUses, int experience) {
	      this.a = price;
	      this.b = structure;
	      this.c = nameKey;
	      this.d = iconType;
	      this.e = maxUses;
	      this.f = experience;
	    }
	    
	    @Nullable
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      if (!(entity.s instanceof WorldServer))
	        return null; 
	      WorldServer serverLevel = (WorldServer)entity.s;
	      BlockPosition blockPos = serverLevel.a(this.b, entity.df(), 100, true);
	      if (blockPos != null) {
	        ItemStack itemStack = ItemWorldMap.a((World)serverLevel, blockPos.u(), blockPos.w(), (byte)2, true, true);
	        ItemWorldMap.a(serverLevel, itemStack);
	        WorldMap.a(itemStack, blockPos, "+", this.d);
	        itemStack.a((IChatBaseComponent)IChatBaseComponent.c(this.c));
	        return new MerchantRecipe(new ItemStack((IMaterial)Items.nm, this.a), new ItemStack((IMaterial)Items.pD), itemStack, this.e, this.f, 0.2F);
	      } 
	      return null;
	    }
	  }
  
	  /*
  static class a implements IMerchantRecipeOption {
	    private final Item a;
	    
	    private final int b;
	    
	    private final int c;
	    
	    private final int d;
	    
	    public a(Item item, int price) {
	      this(item, price, 12, 1);
	    }
	    
	    public a(Item item, int price, int maxUses, int experience) {
	      this.a = item;
	      this.b = price;
	      this.c = maxUses;
	      this.d = experience;
	    }
	    
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      ItemStack itemStack = new ItemStack(Items.mV, this.b);
	      ItemStack itemStack2 = new ItemStack(this.a);
	      if (this.a instanceof net.minecraft.world.item.ItemArmorColorable) {
	        List<ItemDye> list = Lists.newArrayList();
	        list.add(a(random));
	        if (random.i() > 0.7F)
	          list.add(a(random)); 
	        if (random.i() > 0.8F)
	          list.add(a(random)); 
	        itemStack2 = IDyeable.a(itemStack2, list);
	      } 
	      return new MerchantRecipe(itemStack, itemStack2, this.c, this.d, 0.2F);
	    }
	    
	    private static ItemDye a(RandomSource random) {
	      return ItemDye.a(EnumColor.a(random.a(16)));
	    }
	  }
	  
	  static class b implements IMerchantRecipeOption {
	    private final Item a;
	    
	    private final int b;
	    
	    private final int c;
	    
	    private final int d;
	    
	    private final float e;
	    
	    public b(IMaterial item, int price, int maxUses, int experience) {
	      this.a = item.l();
	      this.b = price;
	      this.c = maxUses;
	      this.d = experience;
	      this.e = 0.05F;
	    }
	    
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      ItemStack itemStack = new ItemStack(this.a, this.b);
	      return new MerchantRecipe(itemStack, new ItemStack(Items.mV), this.c, this.d, this.e);
	    }
	  }
	  
	  static class c implements IMerchantRecipeOption {
	    private final Map<VillagerType, Item> a;
	    
	    private final int b;
	    
	    private final int c;
	    
	    private final int d;
	    
	    public c(int count, int maxUses, int experience, Map<VillagerType, Item> map) {
	      IRegistry.ao.r().filter(villagerType -> !map.containsKey(villagerType))
	        
	        .findAny().ifPresent(villagerType -> {
	            throw new IllegalStateException("Missing trade for villager type: " + IRegistry.ao.b(villagerType));
	          });
	      this.a = map;
	      this.b = count;
	      this.c = maxUses;
	      this.d = experience;
	    }
	    
	    @Nullable
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      if (entity instanceof VillagerDataHolder) {
	        ItemStack itemStack = new ItemStack(this.a.get(((VillagerDataHolder)entity).fU().a()), this.b);
	        return new MerchantRecipe(itemStack, new ItemStack(Items.mV), this.c, this.d, 0.05F);
	      } 
	      return null;
	    }
	  }
	  
	  static class d implements IMerchantRecipeOption {
	    private final int a;
	    
	    public d(int experience) {
	      this.a = experience;
	    }
	    
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      List<Enchantment> list = (List<Enchantment>)IRegistry.W.r().filter(Enchantment::h).collect(Collectors.toList());
	      Enchantment enchantment = list.get(random.a(list.size()));
	      int i = MathHelper.a(random, enchantment.e(), enchantment.a());
	      ItemStack itemStack = ItemEnchantedBook.a(new WeightedRandomEnchant(enchantment, i));
	      int j = 2 + random.a(5 + i * 10) + 3 * i;
	      if (enchantment.b())
	        j *= 2; 
	      if (j > 64)
	        j = 64; 
	      return new MerchantRecipe(new ItemStack(Items.mV, j), new ItemStack(Items.oY), itemStack, 12, this.a, 0.2F);
	    }
	  }
	  
	  static class e implements IMerchantRecipeOption {
	    private final ItemStack a;
	    
	    private final int b;
	    
	    private final int c;
	    
	    private final int d;
	    
	    private final float e;
	    
	    public e(Item item, int basePrice, int maxUses, int experience) {
	      this(item, basePrice, maxUses, experience, 0.05F);
	    }
	    
	    public e(Item item, int basePrice, int maxUses, int experience, float multiplier) {
	      this.a = new ItemStack(item);
	      this.b = basePrice;
	      this.c = maxUses;
	      this.d = experience;
	      this.e = multiplier;
	    }
	    
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      int i = 5 + random.a(15);
	      ItemStack itemStack = EnchantLib.API.enchantItem(random, new ItemStack(this.a.c()), i, false);
	      int j = Math.min(this.b + i, 64);
	      ItemStack itemStack2 = new ItemStack(Items.mV, j);
	      return new MerchantRecipe(itemStack2, itemStack, this.c, this.d, this.e);
	    }
	  }
	  
	  static class g implements IMerchantRecipeOption {
	    private final ItemStack a;
	    
	    private final int b;
	    
	    private final int c;
	    
	    private final ItemStack d;
	    
	    private final int e;
	    
	    private final int f;
	    
	    private final int g;
	    
	    private final float h;
	    
	    public g(IMaterial item, int secondCount, Item sellItem, int sellCount, int maxUses, int experience) {
	      this(item, secondCount, 1, sellItem, sellCount, maxUses, experience);
	    }
	    
	    public g(IMaterial item, int secondCount, int price, Item sellItem, int sellCount, int maxUses, int experience) {
	      this.a = new ItemStack(item);
	      this.b = secondCount;
	      this.c = price;
	      this.d = new ItemStack(sellItem);
	      this.e = sellCount;
	      this.f = maxUses;
	      this.g = experience;
	      this.h = 0.05F;
	    }
	    
	    @Nullable
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      return new MerchantRecipe(new ItemStack(Items.mV, this.c), new ItemStack(this.a.c(), this.b), new ItemStack(this.d.c(), this.e), this.f, this.g, this.h);
	    }
	  }
	  
	  static class h implements IMerchantRecipeOption {
	    private final ItemStack a;
	    
	    private final int b;
	    
	    private final int c;
	    
	    private final int d;
	    
	    private final int e;
	    
	    private final float f;
	    
	    public h(Block block, int price, int count, int maxUses, int experience) {
	      this(new ItemStack(block), price, count, maxUses, experience);
	    }
	    
	    public h(Item item, int price, int count, int experience) {
	      this(new ItemStack(item), price, count, 12, experience);
	    }
	    
	    public h(Item item, int price, int count, int maxUses, int experience) {
	      this(new ItemStack(item), price, count, maxUses, experience);
	    }
	    
	    public h(ItemStack stack, int price, int count, int maxUses, int experience) {
	      this(stack, price, count, maxUses, experience, 0.05F);
	    }
	    
	    public h(ItemStack stack, int price, int count, int maxUses, int experience, float multiplier) {
	      this.a = stack;
	      this.b = price;
	      this.c = count;
	      this.d = maxUses;
	      this.e = experience;
	      this.f = multiplier;
	    }
	    
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      return new MerchantRecipe(new ItemStack(Items.mV, this.b), new ItemStack(this.a.c(), this.c), this.d, this.e, this.f);
	    }
	  }
	  
	  static class i implements IMerchantRecipeOption {
	    final MobEffectList a;
	    
	    final int b;
	    
	    final int c;
	    
	    private final float d;
	    
	    public i(MobEffectList effect, int duration, int experience) {
	      this.a = effect;
	      this.b = duration;
	      this.c = experience;
	      this.d = 0.05F;
	    }
	    
	    @Nullable
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      ItemStack itemStack = new ItemStack(Items.tN, 1);
	      ItemSuspiciousStew.a(itemStack, this.a, this.b);
	      return new MerchantRecipe(new ItemStack(Items.mV, 1), itemStack, 12, this.c, this.d);
	    }
	  }
	  
	  static class j implements IMerchantRecipeOption {
	    private final ItemStack a;
	    
	    private final int b;
	    
	    private final int c;
	    
	    private final int d;
	    
	    private final int e;
	    
	    private final Item f;
	    
	    private final int g;
	    
	    private final float h;
	    
	    public j(Item arrow, int secondCount, Item tippedArrow, int sellCount, int price, int maxUses, int experience) {
	      this.a = new ItemStack(tippedArrow);
	      this.c = price;
	      this.d = maxUses;
	      this.e = experience;
	      this.f = arrow;
	      this.g = secondCount;
	      this.b = sellCount;
	      this.h = 0.05F;
	    }
	    
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      ItemStack itemStack = new ItemStack(Items.mV, this.c);
	      List<PotionRegistry> list = (List<PotionRegistry>)IRegistry.Z.r().filter(potionx -> (!potionx.a().isEmpty() && PotionBrewer.a(potionx))).collect(Collectors.toList());
	      PotionRegistry potion = list.get(random.a(list.size()));
	      ItemStack itemStack2 = PotionUtil.a(new ItemStack(this.a.c(), this.b), potion);
	      return new MerchantRecipe(itemStack, new ItemStack(this.f, this.g), itemStack2, this.d, this.e, this.h);
	    }
	  }
	  
	  static class k implements IMerchantRecipeOption {
	    private final int a;
	    
	    private final TagKey<Structure> b;
	    
	    private final String c;
	    
	    private final MapIcon.Type d;
	    
	    private final int e;
	    
	    private final int f;
	    
	    public k(int price, TagKey<Structure> structure, String nameKey, MapIcon.Type iconType, int maxUses, int experience) {
	      this.a = price;
	      this.b = structure;
	      this.c = nameKey;
	      this.d = iconType;
	      this.e = maxUses;
	      this.f = experience;
	    }
	    
	    @Nullable
	    public MerchantRecipe a(Entity entity, RandomSource random) {
	      if (!(entity.s instanceof WorldServer))
	        return null; 
	      WorldServer serverLevel = (WorldServer)entity.s;
	      BlockPosition blockPos = serverLevel.a(this.b, entity.da(), 100, true);
	      if (blockPos != null) {
	        ItemStack itemStack = ItemWorldMap.a((World)serverLevel, blockPos.u(), blockPos.w(), (byte)2, true, true);
	        ItemWorldMap.a(serverLevel, itemStack);
	        WorldMap.a(itemStack, blockPos, "+", this.d);
	        itemStack.a((IChatBaseComponent)IChatBaseComponent.c(this.c));
	        return new MerchantRecipe(new ItemStack(Items.mV, this.a), new ItemStack(Items.pb), itemStack, this.e, this.f, 0.2F);
	      } 
	      return null;
	    }
	  }*/
}
