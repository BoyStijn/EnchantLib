package blob.enchantlib;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R2.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import blob.enchantlib.loot.LootOverride;
import blob.enchantlib.villagers.TradesOverride;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTableRegistry;


public class EnchantLib extends JavaPlugin {

	public static JavaPlugin Instance;
	public static Api API;
	
	@Override
	public void onEnable() {
		Instance = this;
		API = new Api();
		this.getServer().getPluginManager().registerEvents(new MainListener(), this);
		TradesOverride.overrideVillager();
		rebuildLootTables();
	}
	
	public void rebuildLootTables() {
		try {
			MinecraftServer serv = ((CraftServer)Bukkit.getServer()).getServer();
			LootTableRegistry ltr = serv.aF();
			Field cField = LootTableRegistry.class.getDeclaredField("c");
			cField.setAccessible(true);
			
			Map<MinecraftKey, LootTable> oldmap = (Map<MinecraftKey, LootTable>) cField.get(ltr);
			HashMap<MinecraftKey, LootTable> lootmap = new HashMap<MinecraftKey, LootTable>();
			HashMap<LootTable, MinecraftKey> keymap = new HashMap<LootTable, MinecraftKey>();
			
			for (Entry<MinecraftKey, LootTable> e : oldmap.entrySet()) {
				lootmap.put(e.getKey(), e.getValue());
				keymap.put(e.getValue(), e.getKey());
			}
			
			LootOverride.override((key, loot) -> {
				LootTable l = loot.b();
				lootmap.put(key, l);
				keymap.put(l, key);
			});
			
			ltr.lootTableToKey = keymap;
			cField.set(ltr, lootmap);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
