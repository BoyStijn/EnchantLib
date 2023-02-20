package blob.enchantlib;

//JAVA
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

//BUKKIT
import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.GsonBuilder;

//EnchantLib
import blob.enchantlib.NMS.ConvertUtils;
import blob.enchantlib.NMS.NMSMapping;
import blob.enchantlib.NMS.NMSMapping.LootTable_Fields;
import blob.enchantlib.NMS.NMSMapping.NMSField;
import blob.enchantlib.loot.LootOverride;
import blob.enchantlib.villagers.TradesOverride;

//NMS
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
		MinecraftServer server = ConvertUtils.toNMS(this.getServer());
		LootTableRegistry ltr = server.aG(); //[getLootTable] public LootTableRegistry aH() { | MinecraftServer - 2189
		NMSField lootField = NMSMapping.getField(LootTable_Fields.LootTables);
		
		@SuppressWarnings("unchecked")
		Map<MinecraftKey, LootTable> oldmap = (Map<MinecraftKey, LootTable>) lootField.getField(ltr);
		HashMap<MinecraftKey, LootTable> lootmap = new HashMap<MinecraftKey, LootTable>();
		HashMap<LootTable, MinecraftKey> keymap = new HashMap<LootTable, MinecraftKey>();
		
		LootOverride.override((key, loot) -> {
			LootTable l = loot.b();
			lootmap.put(key, l);
			keymap.put(l, key);
		});
		
		for (Entry<MinecraftKey, LootTable> e : oldmap.entrySet()) {
			if (!lootmap.containsKey(e.getKey())) {
				lootmap.put(e.getKey(), e.getValue());
				keymap.put(e.getValue(), e.getKey());
			}
		}
		
		ltr.lootTableToKey = keymap;
		lootField.setField(ltr, lootmap);
	}
}
