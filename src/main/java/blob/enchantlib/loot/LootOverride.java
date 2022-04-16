package blob.enchantlib.loot;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.network.chat.ChatMessage;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.tags.ConfiguredStructureTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.IMaterial;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.saveddata.maps.MapIcon;
import net.minecraft.world.level.storage.loot.LootSelector;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.entries.LootEntryAbstract;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootSelectorEmpty;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionEnchant;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionExplorationMap;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionSetCount;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionSetDamage;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionSetName;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionSetStewEffect;
import net.minecraft.world.level.storage.loot.functions.SetPotionFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class LootOverride {

	public static void override(BiConsumer<MinecraftKey, LootTable.a> biConsumer) {
		biConsumer.accept(LootTables.c, 
		        LootTable.b()
		        .a(LootSelector.a()
		          .a((NumberProvider)UniformGenerator.a(2.0F, 6.0F))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.mk).a(5).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(2.0F, 7.0F))))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.mq).a(10).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(4.0F, 8.0F))))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.mu).a(15).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(2.0F, 7.0F))))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ml).a(2).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(2.0F, 6.0F))))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.so).a(5).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 10.0F))))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.lL).a(3))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.rL))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.rM))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.rN))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.mR).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nz).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nx).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ny).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nw).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.mT).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.mS).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.mM).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nv).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nt).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nu).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ns).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.mO).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))
		          .a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.mN).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))));
	}
	
	
}
