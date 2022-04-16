package blob.enchantlib.enchanttable;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import net.minecraft.advancements.CriterionTriggers;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.IRegistry;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemEnchantedBook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentManager;
import net.minecraft.world.item.enchantment.WeightedRandomEnchant;
import net.minecraft.world.level.block.BlockEnchantmentTable;
import net.minecraft.world.level.block.Blocks;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftInventoryEnchanting;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftInventoryView;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_18_R2.util.CraftNamespacedKey;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

import com.google.common.collect.Lists;

import blob.enchantlib.EnchantLib;
import blob.enchantlib.WeightedEnchant;


public class EnchantMenu extends Container {

    private final IInventory enchantSlots;
    private final ContainerAccess access;
    private final Random random;
    private final ContainerProperty enchantmentSeed;
    public final int[] costs;
    public final int[] enchantClue;
    public final int[] levelClue;
    // CraftBukkit start
    private CraftInventoryView bukkitEntity = null;
    private Player player;
    // CraftBukkit end

    public EnchantMenu(int i, PlayerInventory playerinventory) {
        this(i, playerinventory, ContainerAccess.a);
    }

    public EnchantMenu(int i, PlayerInventory playerinventory, ContainerAccess containeraccess) {
        super(Containers.m, i);
        this.enchantSlots = new InventorySubcontainer(2) {
            @Override
            public void e() {
                super.e();
                EnchantMenu.this.a(this);
            }

            // CraftBukkit start
            @Override
            public Location getLocation() {
                return containeraccess.getLocation();
            }
            // CraftBukkit end
        };
        this.random = new Random();
        this.enchantmentSeed = ContainerProperty.a();
        this.costs = new int[3];
        this.enchantClue = new int[]{-1, -1, -1};
        this.levelClue = new int[]{-1, -1, -1};
        this.access = containeraccess;
        this.a(new Slot(this.enchantSlots, 0, 15, 47) {
            @Override
            public boolean a(ItemStack itemstack) {
                return true;
            }

            @Override
            public int a() {
                return 1;
            }
        });
        this.a(new Slot(this.enchantSlots, 1, 35, 47) {
            @Override
            public boolean a(ItemStack itemstack) {
                return itemstack.a(Items.mm);
            }
        });

        int j;

        for (j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.a(new Slot(playerinventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
            }
        }

        for (j = 0; j < 9; ++j) {
            this.a(new Slot(playerinventory, j, 8 + j * 18, 142));
        }

        this.a(ContainerProperty.a(this.costs, 0));
        this.a(ContainerProperty.a(this.costs, 1));
        this.a(ContainerProperty.a(this.costs, 2));
        this.a(this.enchantmentSeed).a(playerinventory.l.fy());
        this.a(ContainerProperty.a(this.enchantClue, 0));
        this.a(ContainerProperty.a(this.enchantClue, 1));
        this.a(ContainerProperty.a(this.enchantClue, 2));
        this.a(ContainerProperty.a(this.levelClue, 0));
        this.a(ContainerProperty.a(this.levelClue, 1));
        this.a(ContainerProperty.a(this.levelClue, 2));
        // CraftBukkit start
        player = (Player) playerinventory.l.getBukkitEntity();
        // CraftBukkit end
    }

    @Override
    public void a(IInventory iinventory) {
        if (iinventory == this.enchantSlots) {
            ItemStack itemstack = iinventory.a(0);

            if (!itemstack.b()) { // CraftBukkit - relax condition
                this.access.a((world, blockposition) -> {
                    int i = 0;
                    int j = 0;
                    
                    Iterator<BlockPosition> iterator = BlockEnchantmentTable.b.iterator();
                    while (iterator.hasNext()) {
                      BlockPosition blockposition1 = iterator.next();
                      if (BlockEnchantmentTable.a(world, blockposition, blockposition1))
                        i++; 
                    } 

                    this.random.setSeed((long) this.enchantmentSeed.b());

                    for (j = 0; j < 3; ++j) {
                        this.costs[j] = EnchantmentManager.a(this.random, j, i, itemstack);
                        this.enchantClue[j] = -1;
                        this.levelClue[j] = -1;
                        if (this.costs[j] < j + 1) {
                            this.costs[j] = 0;
                        }
                    }

                    for (j = 0; j < 3; ++j) {
                        if (this.costs[j] > 0) {
                            List<WeightedEnchant> list = this.a(itemstack, j, this.costs[j]);

                            if (list != null && !list.isEmpty()) {
                            	WeightedEnchant weightedrandomenchant = list.get(this.random.nextInt(list.size()));

                                this.enchantClue[j] = IRegistry.V.a(weightedrandomenchant.enchantment);
                                this.levelClue[j] = weightedrandomenchant.level;
                            }
                        }
                    }

                    // CraftBukkit start
                    CraftItemStack item = CraftItemStack.asCraftMirror(itemstack);
                    org.bukkit.enchantments.EnchantmentOffer[] offers = new EnchantmentOffer[3];
                    for (j = 0; j < 3; ++j) {
                        org.bukkit.enchantments.Enchantment enchantment = (this.enchantClue[j] >= 0) ? org.bukkit.enchantments.Enchantment.getByKey(CraftNamespacedKey.fromMinecraft(IRegistry.V.b(IRegistry.V.a(this.enchantClue[j])))) : null;
                        offers[j] = (enchantment != null) ? new EnchantmentOffer(enchantment, this.levelClue[j], this.costs[j]) : null;
                    }

                    PrepareItemEnchantEvent event = new PrepareItemEnchantEvent(player, this.getBukkitView(), access.getLocation().getBlock(), item, offers, i);
                    event.setCancelled(!itemstack.B());
                    world.getCraftServer().getPluginManager().callEvent(event);

                    if (event.isCancelled()) {
                        for (j = 0; j < 3; ++j) {
                            this.costs[j] = 0;
                            this.enchantClue[j] = -1;
                            this.levelClue[j] = -1;
                        }
                        return;
                    }

                    for (j = 0; j < 3; j++) {
                        EnchantmentOffer offer = event.getOffers()[j];
                        if (offer != null) {
                            this.costs[j] = offer.getCost();
                            this.enchantClue[j] = IRegistry.V.a(IRegistry.V.a(CraftNamespacedKey.toMinecraft(offer.getEnchantment().getKey())));
                            this.levelClue[j] = offer.getEnchantmentLevel();
                        } else {
                            this.costs[j] = 0;
                            this.enchantClue[j] = -1;
                            this.levelClue[j] = -1;
                        }
                    }
                    // CraftBukkit end

                    this.d();
                });
            } else {
                for (int i = 0; i < 3; ++i) {
                    this.costs[i] = 0;
                    this.enchantClue[i] = -1;
                    this.levelClue[i] = -1;
                }
            }
        }

    }

    @Override
    public boolean a(EntityHuman entityhuman, int i) {
        ItemStack itemstack = this.enchantSlots.a(0);
        ItemStack itemstack1 = this.enchantSlots.a(1);
        int j = i + 1;

        if ((itemstack1.b() || itemstack1.J() < j) && !entityhuman.fs().d) {
            return false;
        } else if (this.costs[i] > 0 && !itemstack.b() && (entityhuman.ci >= j && entityhuman.ci >= this.costs[i] || entityhuman.fs().d)) {
            this.access.a((world, blockposition) -> {
                ItemStack itemstack2 = itemstack;
                List<WeightedEnchant> list = this.a(itemstack, i, this.costs[i]);

                // CraftBukkit start
               // if (true || !list.isEmpty()) {
                    // entityhuman.onEnchantmentPerformed(itemstack, j); // Moved down
                    boolean flag = itemstack.a(Items.om);
                    Map<org.bukkit.enchantments.Enchantment, Integer> enchants = new java.util.HashMap<org.bukkit.enchantments.Enchantment, Integer>();
                    for (WeightedEnchant instance : list) {
                        enchants.put(org.bukkit.enchantments.Enchantment.getByKey(CraftNamespacedKey.fromMinecraft(IRegistry.V.b(instance.enchantment))), instance.level);
                    }
                    CraftItemStack item = CraftItemStack.asCraftMirror(itemstack2);

                    EnchantItemEvent event = new EnchantItemEvent((Player) entityhuman.getBukkitEntity(), this.getBukkitView(), access.getLocation().getBlock(), item, this.costs[i], enchants, i);
                    world.getCraftServer().getPluginManager().callEvent(event);

                    int level = event.getExpLevelCost();
                    if (event.isCancelled() || (level > entityhuman.ci && !entityhuman.fs().d) || event.getEnchantsToAdd().isEmpty()) {
                        return;
                    }

                    if (flag) {
                        itemstack2 = new ItemStack(Items.rB);
                        NBTTagCompound nbttagcompound = itemstack.t();

                        if (nbttagcompound != null) {
                            itemstack2.c(nbttagcompound.g());
                        }

                        this.enchantSlots.a(0, itemstack2);
                    }

                    for (Map.Entry<org.bukkit.enchantments.Enchantment, Integer> entry : event.getEnchantsToAdd().entrySet()) {
                        try {
                            if (flag) {
                                NamespacedKey enchantId = entry.getKey().getKey();
                                Enchantment nms = IRegistry.V.a(CraftNamespacedKey.toMinecraft(enchantId));
                                if (nms == null) {
                                    continue;
                                }

                                WeightedRandomEnchant weightedrandomenchant = new WeightedRandomEnchant(nms, entry.getValue());
                                ItemEnchantedBook.a(itemstack2, weightedrandomenchant);
                            } else {
                                item.addUnsafeEnchantment(entry.getKey(), entry.getValue());
                            }
                        } catch (IllegalArgumentException e) {
                            /* Just swallow invalid enchantments */
                        }
                    }

                    entityhuman.a(itemstack, j);
                    // CraftBukkit end

                    // CraftBukkit - TODO: let plugins change this
                    if (!entityhuman.fs().d) {
                        itemstack1.g(j);
                        if (itemstack1.b()) {
                            this.enchantSlots.a(1, ItemStack.b);
                        }
                    }

                    entityhuman.a(StatisticList.ak);
                    if (entityhuman instanceof EntityPlayer) {
                        CriterionTriggers.i.a((EntityPlayer) entityhuman, itemstack2, j);
                    }

                    this.enchantSlots.e();
                    this.enchantmentSeed.a(entityhuman.fy());
                    this.a(this.enchantSlots);
                    world.a((EntityHuman) null, blockposition, SoundEffects.fi, SoundCategory.e, 1.0F, world.v.nextFloat() * 0.1F + 0.9F);
                }

            //}
        );
            return true;
        } else {
            return false;
        }
    }

    private List<WeightedEnchant> a(ItemStack itemstack, int i, int j) {
        this.random.setSeed((long) (this.enchantmentSeed.b() + i));
        List<WeightedEnchant> list = EnchantLib.API.selectEnchantment(this.random, itemstack, j, false);

        if (itemstack.a(Items.om) && list.size() > 1) {
            list.remove(this.random.nextInt(list.size()));
        }

        return list;
    }

    public int l() {
        ItemStack itemstack = this.enchantSlots.a(1);

        return itemstack.b() ? 0 : itemstack.J();
    }

    public int m() {
        return this.enchantmentSeed.b();
    }

    @Override
    public void b(EntityHuman entityhuman) {
        super.b(entityhuman);
        this.access.a((world, blockposition) -> {
            this.a(entityhuman, this.enchantSlots);
        });
    }

    @Override
    public boolean a(EntityHuman entityhuman) {
        if (!this.checkReachable) return true; // CraftBukkit
        return a(this.access, entityhuman, Blocks.ei);
    }

    @Override
    public ItemStack b(EntityHuman entityhuman, int i) {
        ItemStack itemstack = ItemStack.b;
        Slot slot = (Slot) this.i.get(i);

        if (slot != null && slot.f()) {
            ItemStack itemstack1 = slot.e();

            itemstack = itemstack1.n();
            if (i == 0) {
                if (!this.a(itemstack1, 2, 38, true)) {
                    return ItemStack.b;
                }
            } else if (i == 1) {
                if (!this.a(itemstack1, 2, 38, true)) {
                    return ItemStack.b;
                }
            } else if (itemstack1.a(Items.mm)) {
                if (!this.a(itemstack1, 1, 2, true)) {
                    return ItemStack.b;
                }
            } else {
                if (((Slot) this.i.get(0)).f() || !((Slot) this.i.get(0)).a(itemstack1)) {
                    return ItemStack.b;
                }

                ItemStack itemstack2 = itemstack1.n();

                itemstack2.e(1);
                itemstack1.g(1);
                ((Slot) this.i.get(0)).d(itemstack2);
            }

            if (itemstack1.b()) {
                slot.d(ItemStack.b);
            } else {
                slot.d();
            }

            if (itemstack1.J() == itemstack.J()) {
                return ItemStack.b;
            }

            slot.a(entityhuman, itemstack1);
        }

        return itemstack;
    }

    // CraftBukkit start
    @Override
    public CraftInventoryView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }

        CraftInventoryEnchanting inventory = new CraftInventoryEnchanting(this.enchantSlots);
        bukkitEntity = new CraftInventoryView(this.player, inventory, this);
        return bukkitEntity;
    }
    // CraftBukkit end
}