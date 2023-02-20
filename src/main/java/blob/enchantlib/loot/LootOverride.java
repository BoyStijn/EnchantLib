package blob.enchantlib.loot;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.resources.MinecraftKey;
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

	@SuppressWarnings("rawtypes")
	public static void override(BiConsumer<MinecraftKey, LootTable.a> biConsumer) {
		biConsumer.accept(LootTables.P, LootTable.b().a(LootSelector.a().a((NumberProvider)UniformGenerator.a(5.0F, 10.0F)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.oO).a(1).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 2.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.um).a(1)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.pD).a(2).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)ConstantValue.a(1.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.fy).a(2).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 2.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.tp).a(2)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nW).a(2).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)ConstantValue.a(1.0F))).a((LootItemFunction.a)LootItemFunctionSetDamage.a((NumberProvider)UniformGenerator.a(0.8F, 1.0F))).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(30.0F, 50.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.to).a(2).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)ConstantValue.a(1.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.tm).a(2).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)ConstantValue.a(1.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.mC).a(2).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)ConstantValue.a(1.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ua).a(2)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ub).a(2)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.oz).a(2).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(30.0F, 50.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.pA).a(3).a((LootItemFunction.a)(new LootItemFunctionEnchant.a()).a(Enchantments.m))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.fw).a(3).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(4.0F, 10.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.lm).a(3).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.vp).a(3).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 4.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.np).a(3).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 15.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.sC).a(3).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.uR).a(3).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 15.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ov).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.vP).a(4).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.up).a(4).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.qU).a(5).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F))).a((LootItemFunction.a)SetPotionFunction.a(Potions.I))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.pA).a(5).a((LootItemFunction.a)LootItemFunctionEnchant.d())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.pA).a(5).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(3.0F, 10.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.qk).a(5).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 15.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.eI).a(5).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 15.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nj).a(7).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(6.0F, 15.0F))))));
		biConsumer.accept(LootTables.c, LootTable.b().a(LootSelector.a().a((NumberProvider)UniformGenerator.a(2.0F, 6.0F)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nl).a(5).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(2.0F, 7.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nr).a(10).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(4.0F, 8.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nv).a(15).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(2.0F, 7.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nm).a(2).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(2.0F, 6.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.tN).a(5).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 10.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.mC).a(3)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.tk)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.tl)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.tm)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nS).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.oA).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.oy).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.oz).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ox).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nU).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nT).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nN).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ow).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ou).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ov).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ot).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nP).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nO).a(3).a((LootItemFunction.a)LootEnchant.a((NumberProvider)UniformGenerator.a(20.0F, 39.0F)).e()))));
		biConsumer.accept(LootTables.A, LootTable.b().a(LootSelector.a().a((NumberProvider)UniformGenerator.a(2.0F, 6.0F)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nl).a(3).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nr).a(10).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 5.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nv).a(15).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(2.0F, 7.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Blocks.mx).a(15).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nm).a(2).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.qk).a(20).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(4.0F, 6.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.qO).a(16).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(3.0F, 7.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.mC).a(3)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.tk)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.tl)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.tm)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.pA).a((LootItemFunction.a)LootEnchant.a((NumberProvider)ConstantValue.a(30.0F)).e()))));
		biConsumer.accept(LootTables.y, LootTable.b().a(LootSelector.a().a((NumberProvider)UniformGenerator.a(2.0F, 3.0F)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.qP).a(10)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nl).a(3).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nr).a(10).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 5.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nv).a(5).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.kU).a(5).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(4.0F, 9.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ok).a(15).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ng).a(15).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nP).a(5)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nN).a(5)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ou).a(5)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ot).a(5)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ov).a(5)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ow).a(5)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.oN)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.mC)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.tk)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.tl)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.tm)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.um)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.pA).a((LootItemFunction.a)LootEnchant.a((NumberProvider)ConstantValue.a(30.0F)).e()))));
		biConsumer.accept(LootTables.x, LootTable.b().a(LootSelector.a().a((NumberProvider)UniformGenerator.a(1.0F, 4.0F)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nr).a(10).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 5.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nv).a(5).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.kU).a(5).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(4.0F, 9.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nj).a(10).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(3.0F, 8.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ok).a(15).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.ng).a(15).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nP)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.pA).a((LootItemFunction.a)LootEnchant.a((NumberProvider)ConstantValue.a(30.0F)).e()))));
		biConsumer.accept(LootTables.w, LootTable.b().a(LootSelector.a().a((NumberProvider)UniformGenerator.a(2.0F, 10.0F)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.pA).a(20).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(1.0F, 3.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.pz).a(20).a((LootItemFunction.a)LootItemFunctionSetCount.a((NumberProvider)UniformGenerator.a(2.0F, 7.0F)))).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.sN)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.pD)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.pA).a(10).a((LootItemFunction.a)LootEnchant.a((NumberProvider)ConstantValue.a(30.0F)).e()))));
		biConsumer.accept(LootTables.ak, LootTable.b().a(LootSelector.a().a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.tp)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.mC)).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.nh).a((LootItemFunction.a)LootItemFunctionSetDamage.a((NumberProvider)UniformGenerator.a(0.0F, 0.25F))).a((LootItemFunction.a)LootEnchant.a((NumberProvider)ConstantValue.a(30.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.pG).a((LootItemFunction.a)LootItemFunctionSetDamage.a((NumberProvider)UniformGenerator.a(0.0F, 0.25F))).a((LootItemFunction.a)LootEnchant.a((NumberProvider)ConstantValue.a(30.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.pA).a((LootItemFunction.a)LootEnchant.a((NumberProvider)ConstantValue.a(30.0F)).e())).a((LootEntryAbstract.a)LootItem.a((IMaterial)Items.us))));
	}
	
	
}
