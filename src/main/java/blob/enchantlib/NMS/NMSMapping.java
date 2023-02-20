package blob.enchantlib.NMS;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Mob;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryMaterials;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.attributes.AttributeMapBase;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import net.minecraft.world.entity.monster.EntityCreeper;
import net.minecraft.world.level.storage.loot.LootTableRegistry;

public class NMSMapping {

	//UTILS
		public static NMSField getField(NMSFields field) {
			try {
			     Field NMSF = field.getFieldClass().getDeclaredField(field.getField());
			     NMSF.setAccessible(true);
			     return new NMSField(NMSF);
			 } catch (Exception exc) {exc.printStackTrace();}
			return null;
		}
		
		public static NMSMethod getMethod(NMSMethods method) {
			try {
			     Method NMSF = method.getMethodClass().getDeclaredMethod(method.getMethod(), method.getMethodParams());
			     NMSF.setAccessible(true);
			     return new NMSMethod(NMSF);
			 } catch (Exception exc) {exc.printStackTrace();}
			return null;
		}
		
		public static void addGoal(Mob mob, GoalType type, PathfinderGoal goal, int priority) {
			PathfinderGoalSelector goals = (PathfinderGoalSelector) getField(E_Insentient_Fields.GoalSelector).getField(ConvertUtils.toNMS(mob));
			PathfinderGoalSelector targets = (PathfinderGoalSelector) getField(E_Insentient_Fields.TargetSelector).getField(ConvertUtils.toNMS(mob));
			switch (type) {
				case Goals:
					goals.a(priority, goal); //[addGoal] public void a(int priority, PathfinderGoal goal) { | PathfinderGoalSelector - 43
					break;
				case Targets:
					targets.a(priority, goal); //[addGoal] public void a(int priority, PathfinderGoal goal) { | PathfinderGoalSelector - 43
					break;
				default: break;
			}
		}
		
		public static void SetGoals(Mob mob, GoalType type, Map<PathfinderGoal, Integer> goals) {
			switch (type) {
				case Goals:
						removeAllGoals(mob, type);
						for (Entry<PathfinderGoal, Integer> set : goals.entrySet()) {
							addGoal(mob, type, set.getKey(), set.getValue());
						}
					break;
				case Targets:
						removeAllGoals(mob, type);
						for (Entry<PathfinderGoal, Integer> set : goals.entrySet()) {
							addGoal(mob, type, set.getKey(), set.getValue());
						}
					break;
				default: break;
			}
		}
		
		public static void removeAllGoals(Mob mob, GoalType type) {
			PathfinderGoalSelector goals = (PathfinderGoalSelector) getField(E_Insentient_Fields.GoalSelector).getField(ConvertUtils.toNMS(mob));
			PathfinderGoalSelector targets = (PathfinderGoalSelector) getField(E_Insentient_Fields.TargetSelector).getField(ConvertUtils.toNMS(mob));
			switch (type) {
				case Goals:
					goals.a(); //[removeAllGoals]	public void a() { | PathfinderGoalSelector - 48
					break;
				case Targets:
					targets.a(); //[removeAllGoals]	public void a() { | PathfinderGoalSelector - 48
					break;
				default: break;
			}
		}
		
		//UTIL - CLASSES
		
		//Field Access Class
		public static class NMSField {

			private final Field Accessor;
			
			public NMSField(Field fieldAccessor) {
				this.Accessor = fieldAccessor;
			}
			
			public void setField(Object obj, Object value) {
				try {
					this.Accessor.set(obj, value);
				 } catch (Exception exc) {exc.printStackTrace();}
			}
			
			public Object getField(Object obj) {
				try {
					Object val = this.Accessor.get(obj);
					return val;
				} catch (Exception exc) {exc.printStackTrace();}
				return null;
			}
			
		}
		
		public static class NMSMethod {

			private final Method Accessor;
			
			public NMSMethod(Method fieldAccessor) {
				this.Accessor = fieldAccessor;
			}
			
			public void execute(Object target, Object... args) {
				try {
					this.Accessor.invoke(target, args);
				 } catch (Exception exc) {exc.printStackTrace();}
			}
		}
		
		public static enum GoalType {
			Goals,
			Targets
		}
		
		//Expandable Field Interface
		public static interface NMSFields {
			public String getField();
			public Class<?> getFieldClass();
		}
		
		public static interface NMSMethods {
			public String getMethod();
			public Class<?> getMethodClass();
			public Class<?>[] getMethodParams();
		}
		
		//Build In Fields
		public static enum E_Insentient_Fields implements NMSFields {
			Navigation("bR", EntityInsentient.class), //[Navigation] protected NavigationAbstract bR; | EntityInsentient - 111
			GoalSelector("bS", EntityInsentient.class), //[GoalSelector] public PathfinderGoalSelector bS; | EntityInsentient - 112
			TargetSelector("bT", EntityInsentient.class), //[TargetSelector] public PathfinderGoalSelector bT; | EntityInsentient - 114
			CreeperFuse("bY", EntityCreeper.class), //[Fuse] public int bY = 30; | EntityCreeper - 60
			AttributeList("bQ", EntityLiving.class), //[AttributeList] private final AttributeMapBase bQ; | EntityLiving - 182
			AttributeMap("b", AttributeMapBase.class); //[AttributeMap] private final Map<AttributeBase, AttributeModifiable> b = Maps.newHashMap(); | AttributeMapBase - 22

			private final String FieldName;
			private final Class<?> FieldClass;
			
			E_Insentient_Fields(String FieldName, Class<?> FieldClass) {
				this.FieldName = FieldName;
				this.FieldClass = FieldClass;
			}

			@Override
			public String getField() {
				return this.FieldName;
			}

			@Override
			public Class<?> getFieldClass() {
				return this.FieldClass;
			}
			
		}
		
		public static enum LootTable_Fields implements NMSFields {
			LootTables("c", LootTableRegistry.class), //[LootTables] private Map<MinecraftKey, LootTable> c = (Map<MinecraftKey, LootTable>)ImmutableMap.of(); | LootTableRegistry - 24
			RegistryLock("l", RegistryMaterials.class), //[locked] private boolean ca; | RegistryMaterials - 47
			BRegistryLock("acceptingNew", org.bukkit.enchantments.Enchantment.class); //[acceptingData] private static boolean acceptingNew = true; | Enchantment - 215

			private final String FieldName;
			private final Class<?> FieldClass;
			
			LootTable_Fields(String FieldName, Class<?> FieldClass) {
				this.FieldName = FieldName;
				this.FieldClass = FieldClass;
			}

			@Override
			public String getField() {
				return this.FieldName;
			}

			@Override
			public Class<?> getFieldClass() {
				return this.FieldClass;
			}
			
		}
		
		public static enum Holder_Methodes implements NMSMethods {
			Enchantment_Bind("b", Holder.c.class, Object.class); //[LootTables] private Map<MinecraftKey, LootTable> c = (Map<MinecraftKey, LootTable>)ImmutableMap.of(); | LootTableRegistry - 24

			private final String FieldName;
			private final Class<?> FieldClass;
			private final Class<?>[] FieldParams;
			
			Holder_Methodes(String FieldName, Class<?> FieldClass, Class<?>... FieldParams) {
				this.FieldName = FieldName;
				this.FieldClass = FieldClass;
				this.FieldParams = FieldParams;
			}

			@Override
			public String getMethod() {
				return this.FieldName;
			}

			@Override
			public Class<?> getMethodClass() {
				return this.FieldClass;
			}

			@Override
			public Class<?>[] getMethodParams() {
				return this.FieldParams;
			}
			
		}
	
}
