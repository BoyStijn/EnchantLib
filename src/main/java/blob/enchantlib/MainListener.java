package blob.enchantlib;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.scheduler.BukkitRunnable;

import blob.enchantlib.enchanttable.EnchantMenu;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.world.INamableTileEntity;
import net.minecraft.world.TileInventory;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.inventory.Container;
import net.minecraft.world.inventory.ContainerAccess;
import net.minecraft.world.inventory.InventoryCraftResult;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.entity.TileEntity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftInventoryEnchanting;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftInventoryView;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftResultInventory;

public class MainListener implements Listener {
	
	@EventHandler
	public void onInv(InventoryDragEvent event) {
		event.setCursor(doLoreCalc(event.getCursor()));
	}
	
	@EventHandler
	public void onInv(PrepareAnvilEvent event) {
		event.setResult(doLoreCalc(event.getResult()));
	}
	
	@EventHandler
	public void onInv(EnchantItemEvent event) {
		doLoreCalc(event.getItem());
	}
	
	@EventHandler
	public void onInv(PrepareSmithingEvent event) {
		event.setResult(doLoreCalc(event.getResult()));
	}
	
	@EventHandler
	public void onInv(InventoryCloseEvent event) {
		Inventory inv = event.getInventory();
		doLoreCalc(inv);
	}
	
	@EventHandler
	public void onInv(InventoryOpenEvent event) {
		Inventory inv = event.getInventory();
		
		if (inv instanceof CraftInventoryEnchanting) {
			InventoryView iv = event.getView();
			Container cv = ((CraftInventoryView)iv).getHandle();
			if (!(cv instanceof EnchantMenu)) {
				event.setCancelled(true);
				EntityHuman p = ((CraftPlayer)event.getPlayer()).getHandle();
				World w = ((CraftWorld)event.getPlayer().getWorld()).getHandle();
				Location loc = inv.getLocation();
				BlockPosition pos = new BlockPosition(loc.getX(), loc.getY(), loc.getZ());
				TileEntity data = w.c_(pos);
				IChatBaseComponent var4 = ((INamableTileEntity)data).C_();
				p.a(new TileInventory((numb, inv2, ent) -> new EnchantMenu(numb, inv2, ContainerAccess.a(w, pos)), var4));
			}
		}
		
		doLoreCalc(inv);
	}
	
	@EventHandler
	public void onInv(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		doLoreCalc(inv);
	}
	
	@EventHandler
	public void onPick(EntityPickupItemEvent event) {
		doLoreCalc(event.getItem().getItemStack());
	}
	
	public ItemStack doLoreCalc(Inventory inv) {
		if (!(inv instanceof CraftResultInventory)) return null;
		InventoryCraftResult result = (InventoryCraftResult) ((CraftResultInventory) inv).getResultInventory();
		ItemStack i = CraftItemStack.asBukkitCopy(result.a(0));
		
		if (i != null) {
			if (i.getType() == Material.ENCHANTED_BOOK) {
				i.setItemMeta(EnchantLib.API.ApplyCustomLore(i.getItemMeta(), ((EnchantmentStorageMeta)i.getItemMeta()).getStoredEnchants()));
			} else {
				i.setItemMeta(EnchantLib.API.ApplyCustomLore(i.getItemMeta(), i.getEnchantments()));
			}
			return i;
		}
		return i;
	}
	
	public ItemStack doLoreCalc(ItemStack i) {
		if (i != null) {
			if (i.getType() == Material.ENCHANTED_BOOK) {
				i.setItemMeta(EnchantLib.API.ApplyCustomLore(i.getItemMeta(), ((EnchantmentStorageMeta)i.getItemMeta()).getStoredEnchants()));
			} else {
				i.setItemMeta(EnchantLib.API.ApplyCustomLore(i.getItemMeta(), i.getEnchantments()));
			}
			return i;
		}
		return i;
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		BukkitRunnable br = new BukkitRunnable() {

			@Override
			public void run() {
				for (ItemStack i : event.getPlayer().getInventory().getContents()) {
					if (i == null) continue;
					if (i.getType() == Material.ENCHANTED_BOOK) {
						i.setItemMeta(EnchantLib.API.ApplyCustomLore(i.getItemMeta(), ((EnchantmentStorageMeta)i.getItemMeta()).getStoredEnchants()));
					} else {
						i.setItemMeta(EnchantLib.API.ApplyCustomLore(i.getItemMeta(), i.getEnchantments()));
					}
				}
			}
        	
        };
        br.runTaskLater(EnchantLib.Instance, 1);
	}
}
