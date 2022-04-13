package blob.enchantlib;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.world.inventory.InventoryCraftResult;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftResultInventory;

public class MainListener implements Listener {
	
	@EventHandler
	public void onInv(InventoryEvent event) {
		Inventory inv = event.getInventory();
		if (!(inv instanceof CraftResultInventory)) return;
		InventoryCraftResult result = (InventoryCraftResult) ((CraftResultInventory) inv).getResultInventory();
		ItemStack i = CraftItemStack.asBukkitCopy(result.a(0));
		
		if (i != null) {
			if (i.getType() == Material.ENCHANTED_BOOK) {
				i.setItemMeta(EnchantLib.API.ApplyCustomLore(i.getItemMeta(), ((EnchantmentStorageMeta)i.getItemMeta()).getStoredEnchants()));
			} else {
				i.setItemMeta(EnchantLib.API.ApplyCustomLore(i.getItemMeta(), i.getEnchantments()));
			}
			result.a(0, CraftItemStack.asNMSCopy(i));
			result.e();
		}
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
