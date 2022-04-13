package blob.enchantlib;

import org.bukkit.plugin.java.JavaPlugin;
public class EnchantLib extends JavaPlugin {

	public static JavaPlugin Instance;
	public static Api API;
	
	@Override
	public void onEnable() {
		Instance = this;
		API = new Api();
		this.getServer().getPluginManager().registerEvents(new MainListener(), this);
	}
	
}
