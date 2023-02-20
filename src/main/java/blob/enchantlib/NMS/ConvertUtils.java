package blob.enchantlib.NMS;

import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_19_R2.CraftServer;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftCreeper;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftMob;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftZombie;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Zombie;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.monster.EntityCreeper;
import net.minecraft.world.entity.monster.EntityZombie;

public class ConvertUtils {
	
	public static MinecraftServer toNMS(Server server) {
		CraftServer cs = (CraftServer) server;
		return cs.getServer();
	}

	public static EntityInsentient toNMS(Mob mob) {
		CraftMob cm = (CraftMob) mob;
		return cm.getHandle();
	}
	
	public static EntityLiving toNMS(LivingEntity mob) {
		CraftLivingEntity cm = (CraftLivingEntity) mob;
		return cm.getHandle();
	}
	
	public static EntityZombie toNMS(Zombie mob) {
		CraftZombie cm = (CraftZombie) mob;
		return cm.getHandle();
	}
	
	public static EntityCreeper toNMS(Creeper mob) {
		CraftCreeper cm = (CraftCreeper) mob;
		return cm.getHandle();
	}
	
}
