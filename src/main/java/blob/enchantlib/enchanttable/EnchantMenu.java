package blob.enchantlib.enchanttable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import net.minecraft.advancements.CriterionTriggers;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.sounds.SoundCategory;
import net.minecraft.sounds.SoundEffects;
import net.minecraft.stats.StatisticList;
import net.minecraft.world.IInventory;
import net.minecraft.world.InventorySubcontainer;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.entity.player.PlayerInventory;
import net.minecraft.world.inventory.Container;
import net.minecraft.world.inventory.ContainerAccess;
import net.minecraft.world.inventory.ContainerProperty;
import net.minecraft.world.inventory.Containers;
import net.minecraft.world.inventory.Slot;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemEnchantedBook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentManager;
import net.minecraft.world.item.enchantment.WeightedRandomEnchant;
import net.minecraft.world.level.block.BlockEnchantmentTable;
import net.minecraft.world.level.block.Blocks;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftInventoryEnchanting;
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftInventoryView;
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_19_R2.util.CraftNamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

import blob.enchantlib.EnchantLib;
import blob.enchantlib.WeightedEnchant;


public class EnchantMenu extends Container {
	private final IInventory n;
	  
	  private final ContainerAccess o;
	  
	  private final RandomSource p;
	  
	  private final ContainerProperty q;
	  
	  public final int[] k;
	  
	  public final int[] l;
	  
	  public final int[] m;
	  
	  private CraftInventoryView bukkitEntity = null;
	  
	  private Player player;
	  
	  public EnchantMenu(int syncId, PlayerInventory playerInventory) {
	    this(syncId, playerInventory, ContainerAccess.a);
	  }
	  
	  public EnchantMenu(int syncId, PlayerInventory playerInventory, final ContainerAccess context) {
	    super(Containers.m, syncId);
	    this.n = (IInventory)new InventorySubcontainer(2) {
	        public void e() {
	          super.e();
	          EnchantMenu.this.a((IInventory)this);
	        }
	        
	        public Location getLocation() {
	          return context.getLocation();
	        }
	      };
	    this.p = RandomSource.a();
	    this.q = ContainerProperty.a();
	    this.k = new int[3];
	    this.l = new int[] { -1, -1, -1 };
	    this.m = new int[] { -1, -1, -1 };
	    this.o = context;
	    a(new Slot(this.n, 0, 15, 47) {
	          public boolean a(ItemStack stack) {
	            return true;
	          }
	          
	          public int a() {
	            return 1;
	          }
	        });
	    a(new Slot(this.n, 1, 35, 47) {
	          public boolean a(ItemStack stack) {
	            return stack.a(Items.nn);
	          }
	        });
	    int j;
	    for (j = 0; j < 3; j++) {
	      for (int k = 0; k < 9; k++)
	        a(new Slot((IInventory)playerInventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18)); 
	    } 
	    for (j = 0; j < 9; j++)
	      a(new Slot((IInventory)playerInventory, j, 8 + j * 18, 142)); 
	    a(ContainerProperty.a(this.k, 0));
	    a(ContainerProperty.a(this.k, 1));
	    a(ContainerProperty.a(this.k, 2));
	    a(this.q).a(playerInventory.l.fM());
	    a(ContainerProperty.a(this.l, 0));
	    a(ContainerProperty.a(this.l, 1));
	    a(ContainerProperty.a(this.l, 2));
	    a(ContainerProperty.a(this.m, 0));
	    a(ContainerProperty.a(this.m, 1));
	    a(ContainerProperty.a(this.m, 2));
	    this.player = (Player)playerInventory.l.getBukkitEntity();
	  }
	  
	  public void a(IInventory inventory) {
	    if (inventory == this.n) {
	      ItemStack itemstack = inventory.a(0);
	      if (!itemstack.b()) {
	        this.o.a((world, blockposition) -> {
	              int i = 0;
	              Iterator<BlockPosition> iterator = BlockEnchantmentTable.b.iterator();
	              while (iterator.hasNext()) {
	                BlockPosition blockposition1 = iterator.next();
	                if (BlockEnchantmentTable.a(world, blockposition, blockposition1))
	                  i++; 
	              } 
	              this.p.b((long)this.q.b());
	              int j;
	              for (j = 0; j < 3; j++) {
	                this.k[j] = EnchantmentManager.a(this.p, j, i, itemstack);
	                this.l[j] = -1;
	                this.m[j] = -1;
	                if (this.k[j] < j + 1)
	                  this.k[j] = 0; 
	              } 
	              for (j = 0; j < 3; j++) {
	                if (this.k[j] > 0) {
	                	List<WeightedEnchant> list = a(itemstack, j, this.k[j]);
	                  if (list != null && !list.isEmpty()) {
	                	  WeightedEnchant weightedrandomenchant = list.get(this.p.a(list.size()));
	                    this.l[j] = BuiltInRegistries.g.a(weightedrandomenchant.enchantment);
	                    this.m[j] = weightedrandomenchant.level;
	                  } 
	                } 
	              } 
	              CraftItemStack item = CraftItemStack.asCraftMirror(itemstack);
	              EnchantmentOffer[] offers = new EnchantmentOffer[3];
	              for (j = 0; j < 3; j++) {
	                Enchantment enchantment = (this.l[j] >= 0) ? Enchantment.getByKey(CraftNamespacedKey.fromMinecraft(BuiltInRegistries.g.b(BuiltInRegistries.g.a(this.l[j])))) : null;
	                offers[j] = (enchantment != null) ? new EnchantmentOffer(enchantment, this.m[j], this.k[j]) : null;
	              } 
	              PrepareItemEnchantEvent event = new PrepareItemEnchantEvent(this.player, getBukkitView(), this.o.getLocation().getBlock(), item, offers, i);
	              event.setCancelled(!itemstack.C());
	              world.getCraftServer().getPluginManager().callEvent(event);
	              if (event.isCancelled()) {
	                for (j = 0; j < 3; j++) {
	                  this.k[j] = 0;
	                  this.l[j] = -1;
	                  this.m[j] = -1;
	                } 
	                return;
	              } 
	              for (j = 0; j < 3; j++) {
	                EnchantmentOffer offer = event.getOffers()[j];
	                if (offer != null) {
	                  this.k[j] = offer.getCost();
	                  this.l[j] = BuiltInRegistries.g.a(BuiltInRegistries.g.a(CraftNamespacedKey.toMinecraft(offer.getEnchantment().getKey())));
	                  this.m[j] = offer.getEnchantmentLevel();
	                } else {
	                  this.k[j] = 0;
	                  this.l[j] = -1;
	                  this.m[j] = -1;
	                } 
	              } 
	              d();
	            });
	      } else {
	        for (int i = 0; i < 3; i++) {
	          this.k[i] = 0;
	          this.l[i] = -1;
	          this.m[i] = -1;
	        } 
	      } 
	    } 
	  }
	  
	  public boolean b(EntityHuman player, int id) {
	    if (id >= 0 && id < this.k.length) {
	      ItemStack itemstack = this.n.a(0);
	      ItemStack itemstack1 = this.n.a(1);
	      int j = id + 1;
	      if ((itemstack1.b() || itemstack1.K() < j) && !(player.fF()).d)
	        return false; 
	      if (this.k[id] > 0 && !itemstack.b() && ((player.ch >= j && player.ch >= this.k[id]) || (player.fF()).d)) {
	        this.o.a((world, blockposition) -> {
	              ItemStack itemstack2 = itemstack;
	              List<WeightedEnchant> list = a(itemstack, id, this.k[id]);
	              boolean flag = itemstack.a(Items.pA);
	              Map<Enchantment, Integer> enchants = new HashMap<>();
	              for (WeightedEnchant obj : list) {
	            	  WeightedEnchant instance = obj;
	                enchants.put(Enchantment.getByKey(CraftNamespacedKey.fromMinecraft(BuiltInRegistries.g.b(instance.enchantment))), Integer.valueOf(instance.level));
	              } 
	              CraftItemStack item = CraftItemStack.asCraftMirror(itemstack2);
	              EnchantItemEvent event = new EnchantItemEvent((Player)player.getBukkitEntity(), getBukkitView(), this.o.getLocation().getBlock(), item, this.k[id], enchants, id);
	              world.getCraftServer().getPluginManager().callEvent(event);
	              int level = event.getExpLevelCost();
	              if (event.isCancelled() || (level > player.ch && !(player.fF()).d) || event.getEnchantsToAdd().isEmpty())
	                return; 
	              if (flag) {
	                itemstack2 = new ItemStack(Items.ta);
	                NBTTagCompound nbttagcompound = itemstack.u();
	                if (nbttagcompound != null)
	                  itemstack2.c(nbttagcompound.h()); 
	                this.n.a(0, itemstack2);
	              } 
	              for (Map.Entry<Enchantment, Integer> entry : (Iterable<Map.Entry<Enchantment, Integer>>)event.getEnchantsToAdd().entrySet()) {
	                try {
	                  if (flag) {
	                    NamespacedKey enchantId = ((Enchantment)entry.getKey()).getKey();
	                    net.minecraft.world.item.enchantment.Enchantment nms = BuiltInRegistries.g.a(CraftNamespacedKey.toMinecraft(enchantId));
	                    if (nms == null)
	                      continue; 
	                    WeightedRandomEnchant weightedrandomenchant = new WeightedRandomEnchant(nms, ((Integer)entry.getValue()).intValue());
	                    ItemEnchantedBook.a(itemstack2, weightedrandomenchant);
	                    continue;
	                  } 
	                  item.addUnsafeEnchantment(entry.getKey(), ((Integer)entry.getValue()).intValue());
	                } catch (IllegalArgumentException illegalArgumentException) {}
	              } 
	              player.a(itemstack, j);
	              if (!(player.fF()).d) {
	                itemstack1.h(j);
	                if (itemstack1.b())
	                  this.n.a(1, ItemStack.b); 
	              } 
	              player.a(StatisticList.ak);
	              if (player instanceof EntityPlayer)
	                CriterionTriggers.i.a((EntityPlayer)player, itemstack2, j); 
	              this.n.e();
	              this.q.a(player.fM());
	              a(this.n);
	              world.a((EntityHuman)null, blockposition, SoundEffects.ga, SoundCategory.e, 1.0F, world.w.i() * 0.1F + 0.9F);
	            });
	        return true;
	      } 
	      return false;
	    } 
	    return false;
	  }
	  
	//{MOD-START - EnchantLib}
	  private List<WeightedEnchant> a(ItemStack stack, int slot, int lvl) {
		  //Bukkit.getLogger().info("before random");
		  this.p.b((long)(this.q.b() + slot));
		    List<WeightedEnchant> list = EnchantLib.API.selectEnchantment(this.p, stack, lvl, false);
		    if (stack.a(Items.pA) && list.size() > 1) list.remove(this.p.a(list.size())); 
		    return list;
	    }
	  //{MOD-END}
	  
	  public int l() {
	    ItemStack itemstack = this.n.a(1);
	    return itemstack.b() ? 0 : itemstack.K();
	  }
	  
	  public int m() {
	    return this.q.b();
	  }
	  
	  public void b(EntityHuman player) {
	    super.b(player);
	    this.o.a((BiConsumer<net.minecraft.world.level.World, BlockPosition>)((world, blockposition) -> a(player, this.n)));
	  }
	  
	  public boolean a(EntityHuman player) {
	    if (!this.checkReachable)
	      return true; 
	    return a(this.o, player, Blocks.fb);
	  }
	  
	  public ItemStack a(EntityHuman player, int slot) {
	    ItemStack itemstack = ItemStack.b;
	    Slot slot1 = (Slot)this.i.get(slot);
	    if (slot1 != null && slot1.f()) {
	      ItemStack itemstack1 = slot1.e();
	      itemstack = itemstack1.o();
	      if (slot == 0) {
	        if (!a(itemstack1, 2, 38, true))
	          return ItemStack.b; 
	      } else if (slot == 1) {
	        if (!a(itemstack1, 2, 38, true))
	          return ItemStack.b; 
	      } else if (itemstack1.a(Items.nn)) {
	        if (!a(itemstack1, 1, 2, true))
	          return ItemStack.b; 
	      } else {
	        if (((Slot)this.i.get(0)).f() || !((Slot)this.i.get(0)).a(itemstack1))
	          return ItemStack.b; 
	        ItemStack itemstack2 = itemstack1.o();
	        itemstack2.f(1);
	        itemstack1.h(1);
	        ((Slot)this.i.get(0)).d(itemstack2);
	      } 
	      if (itemstack1.b()) {
	        slot1.d(ItemStack.b);
	      } else {
	        slot1.d();
	      } 
	      if (itemstack1.K() == itemstack.K())
	        return ItemStack.b; 
	      slot1.a(player, itemstack1);
	    } 
	    return itemstack;
	  }
	  
	  public CraftInventoryView getBukkitView() {
	    if (this.bukkitEntity != null)
	      return this.bukkitEntity; 
	    CraftInventoryEnchanting inventory = new CraftInventoryEnchanting(this.n);
	    this.bukkitEntity = new CraftInventoryView(this.player, inventory, this);
	    return this.bukkitEntity;
	  }
	
}