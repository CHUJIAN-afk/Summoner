package first.summoner.dataGenerator;

import first.lyra.provider.LyraAbstractLootProvider;
import first.summoner.register.SummonerCurioRegister;
import first.summoner.register.SummonerWeaponRegister;
import net.minecraft.Util;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.WeatherCheck;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class SummonerLootProvider extends LyraAbstractLootProvider {

    public SummonerLootProvider(String modid) {
        super(modid);
    }

    @Override
    public void loot() {
        addEntityLoot(EntityType.BEE, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.HornetStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))));
        addEntityLoot(EntityType.MAGMA_CUBE, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.ImpStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.01f))
                        .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().nbt(new NbtPredicate(Util.make(new CompoundTag(), tag -> tag.putInt("Size", 3))))))));
        addEntityLoot(EntityType.PHANTOM, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.RainCloudStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))
                        .when(WeatherCheck.weather().setRaining(true))));
        addEntityLoot(EntityType.GHAST, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.OpticStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.02f))));
        addEntityLoot(EntityType.ALLAY, LootPool.lootPool()
                .add(LootItem.lootTableItem(SummonerWeaponRegister.TerraPrism.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.01f))));
        addEntityLoot(EntityType.ENDER_DRAGON, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.MoonPortalStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.1f))));
        addEntityLoot(EntityType.ENDER_DRAGON, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.RainbowCrystalStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.1f))));
        addEntityLoot(EntityType.CREEPER, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.ThundercloudBonsai.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.01f))
                        .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().nbt(new NbtPredicate(Util.make(new CompoundTag(), tag -> tag.putBoolean("powered", true))))))));
        addEntityLoot(EntityType.WARDEN, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.LaserMinigunStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.01f))));
        addEntityLoot(EntityType.DROWNED, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.BarnacleStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.01f))));
        addEntityLoot(EntityType.ELDER_GUARDIAN, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.EyeLaserTurretStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.02f))));
        addEntityLoot(EntityType.STRAY, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.HydraStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.01f))));
        addEntityLoot(EntityType.BAT, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.SanguineStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.01f))));
        addEntityLoot(EntityType.EVOKER, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerCurioRegister.SummonerEmblem.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.1f))));
        addChestLoot(BuiltInLootTables.SHIPWRECK_TREASURE, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.ImpStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))));
        addChestLoot(BuiltInLootTables.SHIPWRECK_TREASURE, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.FairyBell.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))));
        addChestLoot(BuiltInLootTables.JUNGLE_TEMPLE, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.DeadlySphereStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))));
        addChestLoot(BuiltInLootTables.END_CITY_TREASURE, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.XenoStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))));
        addChestLoot(BuiltInLootTables.END_CITY_TREASURE, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.EtherealStellarCoreStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.02f))));
        addChestLoot(BuiltInLootTables.END_CITY_TREASURE, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.StardustCellStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.02f))));
        addChestLoot(BuiltInLootTables.END_CITY_TREASURE, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.StardustDragonStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.02f))));
        addChestLoot(BuiltInLootTables.END_CITY_TREASURE, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.VoidEaterMarionette.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.005f))));
        addChestLoot(BuiltInLootTables.ABANDONED_MINESHAFT, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.PulseTurretRemote.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))));
        addChestLoot(BuiltInLootTables.PILLAGER_OUTPOST, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.BallistaRod.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))));
        addChestLoot(BuiltInLootTables.PILLAGER_OUTPOST, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.BallistaCane.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))));
        addChestLoot(BuiltInLootTables.PILLAGER_OUTPOST, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.BallistaStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))));
        addChestLoot(BuiltInLootTables.VILLAGE_WEAPONSMITH, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.LightningAuraRod.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))));
        addChestLoot(BuiltInLootTables.VILLAGE_WEAPONSMITH, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.LightningAuraCane.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))));
        addChestLoot(BuiltInLootTables.VILLAGE_WEAPONSMITH, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.LightningAuraStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))));
        addChestLoot(BuiltInLootTables.ANCIENT_CITY, LootPool.lootPool()
                .add(LootItem.lootTableItem(SummonerWeaponRegister.InfiniteScabbard.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))));
        addChestLoot(BuiltInLootTables.BASTION_TREASURE, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.InfernoRod.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.02f))));
        addChestLoot(BuiltInLootTables.BASTION_TREASURE, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.InfernoCane.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.02f))));
        addChestLoot(BuiltInLootTables.BASTION_TREASURE, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.InfernoStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.02f))));
        addChestLoot(BuiltInLootTables.DESERT_PYRAMID, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1f))
                .add(LootItem.lootTableItem(SummonerWeaponRegister.RuinStaff.get())
                        .when(LootItemRandomChanceCondition.randomChance(0.05f))));
    }
}
