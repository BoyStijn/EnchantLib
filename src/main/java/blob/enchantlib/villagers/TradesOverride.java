package blob.enchantlib.villagers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.SystemUtils;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.IRegistry;
import net.minecraft.network.chat.ChatMessage;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.server.level.WorldServer;
import net.minecraft.tags.ConfiguredStructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.MathHelper;
import net.minecraft.world.effect.MobEffectList;
import net.minecraft.world.effect.MobEffects;
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
import net.minecraft.world.item.enchantment.EnchantmentManager;
import net.minecraft.world.item.enchantment.WeightedRandomEnchant;
import net.minecraft.world.item.trading.MerchantRecipe;
import net.minecraft.world.level.IMaterial;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.saveddata.maps.MapIcon;
import net.minecraft.world.level.saveddata.maps.WorldMap;

public class TradesOverride {
  
  public static void overrideVillager() {
	  Map<VillagerProfession, Int2ObjectMap<IMerchantRecipeOption[]>> map = (Map<VillagerProfession, Int2ObjectMap<IMerchantRecipeOption[]>>)SystemUtils.a(new HashMap<VillagerProfession, Int2ObjectMap<IMerchantRecipeOption[]>>(), var0 -> {
          var0.put(VillagerProfession.g, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b((IMaterial)Items.ne, 20, 16, 2), new b((IMaterial)Items.mi, 10, 16, 2), new g((IMaterial)Items.ov, 6, Items.oz, 6, 16, 1), new h(Items.of, 3, 1, 16, 1) }, Integer.valueOf(2), new IMerchantRecipeOption[] { new b((IMaterial)Items.ov, 15, 16, 10), new g((IMaterial)Items.ow, 6, Items.oA, 6, 16, 5), new h(Items.tq, 2, 1, 5) }, Integer.valueOf(3), new IMerchantRecipeOption[] { new b((IMaterial)Items.ow, 13, 16, 20), new e(Items.or, 3, 3, 10, 0.2F) }, Integer.valueOf(4), new IMerchantRecipeOption[] { new b((IMaterial)Items.ox, 6, 12, 30) }, Integer.valueOf(5), new IMerchantRecipeOption[] { new b((IMaterial)Items.oy, 4, 12, 30), new c(1, 12, 30, new ImmutableMap.Builder<VillagerType, Item>().put(VillagerType.c, Items.lU).put(VillagerType.g, Items.lV).put(VillagerType.e, Items.lV).put(VillagerType.a, Items.lX).put(VillagerType.b, Items.lX).put(VillagerType.d, Items.lY).put(VillagerType.f, Items.lZ).build()) })));
          var0.put(VillagerProfession.h, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b((IMaterial)Items.nb, 32, 16, 2), new h(Items.mh, 1, 16, 1), new g((IMaterial)Blocks.E, 10, Items.nI, 10, 12, 1) }, Integer.valueOf(2), new IMerchantRecipeOption[] { new b((IMaterial)Items.nI, 26, 12, 10), new h(Items.mg, 2, 1, 5) }, Integer.valueOf(3), new IMerchantRecipeOption[] { new b((IMaterial)Items.ne, 14, 16, 20), new h(Items.sT, 3, 1, 10) }, Integer.valueOf(4), new IMerchantRecipeOption[] { new b((IMaterial)Items.nf, 24, 16, 30), new e(Items.mg, 2, 3, 15) }, Integer.valueOf(5), new IMerchantRecipeOption[] { new b((IMaterial)Items.kG, 8, 12, 30), new e(Items.sT, 3, 3, 15), new j(Items.mh, 5, Items.st, 5, 2, 12, 30) })));
          var0.put(VillagerProfession.b, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b((IMaterial)Items.mi, 15, 16, 2), new h(new ItemStack((IMaterial)Items.nu), 7, 1, 12, 1, 0.2F), new h(new ItemStack((IMaterial)Items.nv), 4, 1, 12, 1, 0.2F), new h(new ItemStack((IMaterial)Items.ns), 5, 1, 12, 1, 0.2F), new h(new ItemStack((IMaterial)Items.nt), 9, 1, 12, 1, 0.2F) }, Integer.valueOf(2), new IMerchantRecipeOption[] { new b((IMaterial)Items.mq, 4, 12, 10), new h(new ItemStack((IMaterial)Items.tl), 36, 1, 12, 5, 0.2F), new h(new ItemStack((IMaterial)Items.nr), 1, 1, 12, 5, 0.2F), new h(new ItemStack((IMaterial)Items.nq), 3, 1, 12, 5, 0.2F) }, Integer.valueOf(3), new IMerchantRecipeOption[] { new b((IMaterial)Items.nY, 1, 12, 20), new b((IMaterial)Items.mk, 1, 12, 20), new h(new ItemStack((IMaterial)Items.no), 1, 1, 12, 10, 0.2F), new h(new ItemStack((IMaterial)Items.np), 4, 1, 12, 10, 0.2F), new h(new ItemStack((IMaterial)Items.sv), 5, 1, 12, 10, 0.2F) }, Integer.valueOf(4), new IMerchantRecipeOption[] { new e(Items.ny, 14, 3, 15, 0.2F), new e(Items.nz, 8, 3, 15, 0.2F) }, Integer.valueOf(5), new IMerchantRecipeOption[] { new e(Items.nw, 8, 3, 30, 0.2F), new e(Items.nx, 16, 3, 30, 0.2F) })));
          var0.put(VillagerProfession.o, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b((IMaterial)Items.mi, 15, 16, 2), new h(new ItemStack((IMaterial)Items.mP), 3, 1, 12, 1, 0.2F), new e(Items.mM, 2, 3, 1) }, Integer.valueOf(2), new IMerchantRecipeOption[] { new b((IMaterial)Items.mq, 4, 12, 10), new h(new ItemStack((IMaterial)Items.tl), 36, 1, 12, 5, 0.2F) }, Integer.valueOf(3), new IMerchantRecipeOption[] { new b((IMaterial)Items.nI, 24, 12, 20) }, Integer.valueOf(4), new IMerchantRecipeOption[] { new b((IMaterial)Items.mk, 1, 12, 30), new e(Items.mU, 12, 3, 15, 0.2F) }, Integer.valueOf(5), new IMerchantRecipeOption[] { new e(Items.mR, 8, 3, 30, 0.2F) })));
          var0.put(VillagerProfession.n, a(ImmutableMap.of(Integer.valueOf(1), new IMerchantRecipeOption[] { new b((IMaterial)Items.mi, 15, 16, 2), new h(new ItemStack((IMaterial)Items.mF), 1, 1, 12, 1, 0.2F), new h(new ItemStack((IMaterial)Items.mD), 1, 1, 12, 1, 0.2F), new h(new ItemStack((IMaterial)Items.mE), 1, 1, 12, 1, 0.2F), new h(new ItemStack((IMaterial)Items.mG), 1, 1, 12, 1, 0.2F) }, Integer.valueOf(2), new IMerchantRecipeOption[] { new b((IMaterial)Items.mq, 4, 12, 10), new h(new ItemStack((IMaterial)Items.tl), 36, 1, 12, 5, 0.2F) }, Integer.valueOf(3), new IMerchantRecipeOption[] { new b((IMaterial)Items.nI, 30, 12, 20), new e(Items.mP, 1, 3, 10, 0.2F), new e(Items.mN, 2, 3, 10, 0.2F), new e(Items.mO, 3, 3, 10, 0.2F), new h(new ItemStack((IMaterial)Items.mV), 4, 1, 3, 10, 0.2F) }, Integer.valueOf(4), new IMerchantRecipeOption[] { new b((IMaterial)Items.mk, 1, 12, 30), new e(Items.mU, 12, 3, 15, 0.2F), new e(Items.mS, 5, 3, 15, 0.2F) }, Integer.valueOf(5), new IMerchantRecipeOption[] { new e(Items.mT, 13, 3, 30, 0.2F) })));
      });
	  for (Entry<VillagerProfession, Int2ObjectMap<IMerchantRecipeOption[]>> e : map.entrySet()) {
		  VillagerTrades.a.put(e.getKey(), e.getValue());
	  }
  }
  
  private static Int2ObjectMap<IMerchantRecipeOption[]> a(ImmutableMap<Integer, IMerchantRecipeOption[]> var0) {
    return (Int2ObjectMap<IMerchantRecipeOption[]>)new Int2ObjectOpenHashMap((Map)var0);
  }
  
  private static class b implements IMerchantRecipeOption {
    private final Item a;
    
    private final int b;
    
    private final int c;
    
    private final int d;
    
    private final float e;
    
    public b(IMaterial var0, int var1, int var2, int var3) {
      this.a = var0.l();
      this.b = var1;
      this.c = var2;
      this.d = var3;
      this.e = 0.05F;
    }
    
    public MerchantRecipe a(Entity var0, Random var1) {
      ItemStack var2 = new ItemStack((IMaterial)this.a, this.b);
      return new MerchantRecipe(var2, new ItemStack((IMaterial)Items.ml), this.c, this.d, this.e);
    }
  }
  
  private static class c implements IMerchantRecipeOption {
    private final Map<VillagerType, Item> a;
    
    private final int b;
    
    private final int c;
    
    private final int d;
    
    public c(int var0, int var1, int var2, Map<VillagerType, Item> var3) {
      IRegistry.am.q().filter(var12 -> !var3.containsKey(var12)).findAny().ifPresent(var01 -> {
            throw new IllegalStateException("Missing trade for villager type: " + IRegistry.am.b(var01));
          });
      this.a = var3;
      this.b = var0;
      this.c = var1;
      this.d = var2;
    }
    
    @Nullable
    public MerchantRecipe a(Entity var0, Random var1) {
      if (var0 instanceof VillagerDataHolder) {
        ItemStack var2 = new ItemStack((IMaterial)this.a.get(((VillagerDataHolder)var0).fK().a()), this.b);
        return new MerchantRecipe(var2, new ItemStack((IMaterial)Items.ml), this.c, this.d, 0.05F);
      } 
      return null;
    }
  }
  
  private static class h implements IMerchantRecipeOption {
    private final ItemStack a;
    
    private final int b;
    
    private final int c;
    
    private final int d;
    
    private final int e;
    
    private final float f;
    
    public h(Block var0, int var1, int var2, int var3, int var4) {
      this(new ItemStack((IMaterial)var0), var1, var2, var3, var4);
    }
    
    public h(Item var0, int var1, int var2, int var3) {
      this(new ItemStack((IMaterial)var0), var1, var2, 12, var3);
    }
    
    public h(Item var0, int var1, int var2, int var3, int var4) {
      this(new ItemStack((IMaterial)var0), var1, var2, var3, var4);
    }
    
    public h(ItemStack var0, int var1, int var2, int var3, int var4) {
      this(var0, var1, var2, var3, var4, 0.05F);
    }
    
    public h(ItemStack var0, int var1, int var2, int var3, int var4, float var5) {
      this.a = var0;
      this.b = var1;
      this.c = var2;
      this.d = var3;
      this.e = var4;
      this.f = var5;
    }
    
    public MerchantRecipe a(Entity var0, Random var1) {
      return new MerchantRecipe(new ItemStack((IMaterial)Items.ml, this.b), new ItemStack((IMaterial)this.a.c(), this.c), this.d, this.e, this.f);
    }
  }
  
  private static class i implements IMerchantRecipeOption {
    final MobEffectList a;
    
    final int b;
    
    final int c;
    
    private final float d;
    
    public i(MobEffectList var0, int var1, int var2) {
      this.a = var0;
      this.b = var1;
      this.c = var2;
      this.d = 0.05F;
    }
    
    @Nullable
    public MerchantRecipe a(Entity var0, Random var1) {
      ItemStack var2 = new ItemStack((IMaterial)Items.sU, 1);
      ItemSuspiciousStew.a(var2, this.a, this.b);
      return new MerchantRecipe(new ItemStack((IMaterial)Items.ml, 1), var2, 12, this.c, this.d);
    }
  }
  
  private static class e implements IMerchantRecipeOption {
    private final ItemStack a;
    
    private final int b;
    
    private final int c;
    
    private final int d;
    
    private final float e;
    
    public e(Item var0, int var1, int var2, int var3) {
      this(var0, var1, var2, var3, 0.05F);
    }
    
    public e(Item var0, int var1, int var2, int var3, float var4) {
      this.a = new ItemStack((IMaterial)var0);
      this.b = var1;
      this.c = var2;
      this.d = var3;
      this.e = var4;
    }
    
    public MerchantRecipe a(Entity var0, Random var1) {
      int var2 = 5 + var1.nextInt(15);
      ItemStack var3 = EnchantmentManager.a(var1, new ItemStack((IMaterial)this.a.c()), var2, false);
      int var4 = Math.min(this.b + var2, 64);
      ItemStack var5 = new ItemStack((IMaterial)Items.ml, var4);
      return new MerchantRecipe(var5, var3, this.c, this.d, this.e);
    }
  }
  
  private static class j implements IMerchantRecipeOption {
    private final ItemStack a;
    
    private final int b;
    
    private final int c;
    
    private final int d;
    
    private final int e;
    
    private final Item f;
    
    private final int g;
    
    private final float h;
    
    public j(Item var0, int var1, Item var2, int var3, int var4, int var5, int var6) {
      this.a = new ItemStack((IMaterial)var2);
      this.c = var4;
      this.d = var5;
      this.e = var6;
      this.f = var0;
      this.g = var1;
      this.b = var3;
      this.h = 0.05F;
    }
    
    public MerchantRecipe a(Entity var0, Random var1) {
      ItemStack var2 = new ItemStack((IMaterial)Items.ml, this.c);
      List<PotionRegistry> var3 = (List<PotionRegistry>)IRegistry.Y.q().filter(var10 -> (!var10.a().isEmpty() && PotionBrewer.a(var10))).collect(Collectors.toList());
      PotionRegistry var4 = var3.get(var1.nextInt(var3.size()));
      ItemStack var5 = PotionUtil.a(new ItemStack((IMaterial)this.a.c(), this.b), var4);
      return new MerchantRecipe(var2, new ItemStack((IMaterial)this.f, this.g), var5, this.d, this.e, this.h);
    }
  }
  
  private static class a implements IMerchantRecipeOption {
    private final Item a;
    
    private final int b;
    
    private final int c;
    
    private final int d;
    
    public a(Item var0, int var1) {
      this(var0, var1, 12, 1);
    }
    
    public a(Item var0, int var1, int var2, int var3) {
      this.a = var0;
      this.b = var1;
      this.c = var2;
      this.d = var3;
    }
    
    public MerchantRecipe a(Entity var0, Random var1) {
      ItemStack var2 = new ItemStack((IMaterial)Items.ml, this.b);
      ItemStack var3 = new ItemStack((IMaterial)this.a);
      if (this.a instanceof net.minecraft.world.item.ItemArmorColorable) {
        List<ItemDye> var4 = Lists.newArrayList();
        var4.add(a(var1));
        if (var1.nextFloat() > 0.7F)
          var4.add(a(var1)); 
        if (var1.nextFloat() > 0.8F)
          var4.add(a(var1)); 
        var3 = IDyeable.a(var3, var4);
      } 
      return new MerchantRecipe(var2, var3, this.c, this.d, 0.2F);
    }
    
    private static ItemDye a(Random var0) {
      return ItemDye.a(EnumColor.a(var0.nextInt(16)));
    }
  }
  
  private static class d implements IMerchantRecipeOption {
    private final int a;
    
    public d(int var0) {
      this.a = var0;
    }
    
    public MerchantRecipe a(Entity var0, Random var1) {
      List<Enchantment> var2 = (List<Enchantment>)IRegistry.V.q().filter(Enchantment::h).collect(Collectors.toList());
      Enchantment var3 = var2.get(var1.nextInt(var2.size()));
      int var4 = MathHelper.a(var1, var3.e(), var3.a());
      ItemStack var5 = ItemEnchantedBook.a(new WeightedRandomEnchant(var3, var4));
      int var6 = 2 + var1.nextInt(5 + var4 * 10) + 3 * var4;
      if (var3.b())
        var6 *= 2; 
      if (var6 > 64)
        var6 = 64; 
      return new MerchantRecipe(new ItemStack((IMaterial)Items.ml, var6), new ItemStack((IMaterial)Items.om), var5, 12, this.a, 0.2F);
    }
  }
  
  private static class k implements IMerchantRecipeOption {
    private final int a;
    
    private final TagKey<StructureFeature<?, ?>> b;
    
    private final String c;
    
    private final MapIcon.Type d;
    
    private final int e;
    
    private final int f;
    
    public k(int var0, TagKey<StructureFeature<?, ?>> var1, String var2, MapIcon.Type var3, int var4, int var5) {
      this.a = var0;
      this.b = var1;
      this.c = var2;
      this.d = var3;
      this.e = var4;
      this.f = var5;
    }
    
    @Nullable
    public MerchantRecipe a(Entity var0, Random var1) {
      if (!(var0.s instanceof WorldServer))
        return null; 
      WorldServer var2 = (WorldServer)var0.s;
      BlockPosition var3 = var2.a(this.b, var0.cW(), 100, true);
      if (var3 != null) {
        ItemStack var4 = ItemWorldMap.a((World)var2, var3.u(), var3.w(), (byte)2, true, true);
        ItemWorldMap.a(var2, var4);
        WorldMap.a(var4, var3, "+", this.d);
        var4.a((IChatBaseComponent)new ChatMessage(this.c));
        return new MerchantRecipe(new ItemStack((IMaterial)Items.ml, this.a), new ItemStack((IMaterial)Items.op), var4, this.e, this.f, 0.2F);
      } 
      return null;
    }
  }
  
  private static class g implements IMerchantRecipeOption {
    private final ItemStack a;
    
    private final int b;
    
    private final int c;
    
    private final ItemStack d;
    
    private final int e;
    
    private final int f;
    
    private final int g;
    
    private final float h;
    
    public g(IMaterial var0, int var1, Item var2, int var3, int var4, int var5) {
      this(var0, var1, 1, var2, var3, var4, var5);
    }
    
    public g(IMaterial var0, int var1, int var2, Item var3, int var4, int var5, int var6) {
      this.a = new ItemStack(var0);
      this.b = var1;
      this.c = var2;
      this.d = new ItemStack((IMaterial)var3);
      this.e = var4;
      this.f = var5;
      this.g = var6;
      this.h = 0.05F;
    }
    
    @Nullable
    public MerchantRecipe a(Entity var0, Random var1) {
      return new MerchantRecipe(new ItemStack((IMaterial)Items.ml, this.c), new ItemStack((IMaterial)this.a.c(), this.b), new ItemStack((IMaterial)this.d.c(), this.e), this.f, this.g, this.h);
    }
  }
}
