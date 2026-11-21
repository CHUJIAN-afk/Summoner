package first.summoner.register;

import com.tterrag.registrate.util.entry.ItemEntry;
import first.lyra.api.LyraHelper;
import first.lyra.common.attachment.AttachmentEntityData;
import first.lyra.common.attachmentEntity.PathNode;
import first.lyra.common.builder.MinionWeaponItemBuilder;
import first.lyra.common.dataComponent.LyraRarity;
import first.lyra.common.item.SummonerWeaponItem;
import first.lyra.common.minion.MinionSlotType;
import first.lyra.register.LyraAttributeRegister;
import first.lyra.register.LyraDataComponentRegister;
import first.summoner.Summoner;
import first.summoner.common.dataComponent.ScabbardContainer;
import first.summoner.common.item.GildedWandItem;
import first.summoner.common.minion.minion.*;
import first.summoner.common.minion.sentry.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.phys.Vec3;

import java.util.List;

@SuppressWarnings("unused")
public class SummonerWeaponRegister {

    public static final ItemEntry<GildedWandItem> GildedWand =
            Summoner.REGISTRATE.item("gilded_wand", itemProperties -> new GildedWandItem(() -> new Item.Properties()
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)
                            .component(LyraDataComponentRegister.RARITY, new LyraRarity(0xA66800))
                            .durability(15 * 60 * 20)
                            .component(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY)
                            .component(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
                                    .add(LyraAttributeRegister.SummonDamage, new AttributeModifier(Summoner.rl("gilded_wand"), 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.HAND)
                                    .add(LyraAttributeRegister.HealthRegen, new AttributeModifier(Summoner.rl("gilded_wand"), 0.2, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HAND)
                                    .build())
                    ))
                    .tag(ItemTags.DURABILITY_ENCHANTABLE)
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .recipe((ctx, recipeOutput) -> ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SummonerWeaponRegister.GildedWand)
                            .pattern("  G")
                            .pattern(" S ")
                            .pattern("G  ")
                            .define('G', Items.GOLD_INGOT)
                            .define('S', Items.STICK)
                            .unlockedBy("has_gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                            .save(recipeOutput, Summoner.rl("crafting/gilded_wand")))
                    .register();
    /**
     * 黄蜂法杖
     */
    public static final ItemEntry<SummonerWeaponItem<Hornet>> HornetStaff =
            Summoner.REGISTRATE.item("hornet_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("hornet_staff"), SummonerAttachmentEntityRegister.HORNET)
                            .damage(0.7f)
                            .knockback(0.2f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xFFDA41)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 小鬼法杖
     */
    public static final ItemEntry<SummonerWeaponItem<Imp>> ImpStaff =
            Summoner.REGISTRATE.item("imp_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("imp_staff"), SummonerAttachmentEntityRegister.IMP)
                            .damage(1.7f)
                            .knockback(0.2f)
                            .sound(SummonerSoundRegister.UseImpStaff)
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.RARE).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xFE7902)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 无人机
     */
    public static final ItemEntry<SummonerWeaponItem<OreScout>> SurveyDroneRemote =
            Summoner.REGISTRATE.item("survey_drone_remote", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("survey_drone_remote"), SummonerAttachmentEntityRegister.ORE_SCOUT)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .summon((weapon, player, itemStack) -> {
                                OreScout minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                AttachmentEntityData entityData = summonerHelper.getEntityData();
                                if (entityData.get(weapon.getEntityType()).isEmpty() && summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(minion.getRenderNode(1.0f));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xAB5D66)))
                    .defaultModel()
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 妖精铃铛
     */
    public static final ItemEntry<SummonerWeaponItem<ScavengerFairy>> FairyBell =
            Summoner.REGISTRATE.item("fairy_bell", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("fairy_bell"), SummonerAttachmentEntityRegister.SCAVENGER_FAIRY)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .summon((weapon, player, itemStack) -> {
                                ScavengerFairy minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                AttachmentEntityData entityData = summonerHelper.getEntityData();
                                if (entityData.get(weapon.getEntityType()).isEmpty() && summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(minion.getRenderNode(1.0f));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x936F20)))
                    .defaultModel()
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 刃杖 - 召唤附魔飞刀群
     */
    public static final ItemEntry<SummonerWeaponItem<EnchantedThrowingKnives>> BladeStaff =
            Summoner.REGISTRATE.item("blade_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("blade_staff"), SummonerAttachmentEntityRegister.ENCHANTED_THROWING_KNIVES)
                            .damage(0.6f)
                            .armorPierce(7.5f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .summon((weapon, player, itemStack) -> {
                                EnchantedThrowingKnives minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    PathNode idle = minion.getInterpolatedIdleState(1.0f);
                                    Vec3 center = player.getBoundingBox().getCenter();
                                    minion.init(new PathNode(new Vec3(center.x(), idle.pos().y(), center.z()), idle.yaw(), idle.pitch(), idle.roll()));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x636BAF)))
                    .recipe((ctx, recipeOutput) -> ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SummonerWeaponRegister.BladeStaff)
                            .pattern("  I")
                            .pattern(" A ")
                            .pattern("S  ")
                            .define('I', Items.IRON_SWORD)
                            .define('A', Items.AMETHYST_SHARD)
                            .define('S', Items.STICK)
                            .unlockedBy("has_amethyst_shard", InventoryChangeTrigger.TriggerInstance.hasItems(Items.AMETHYST_SHARD))
                            .save(recipeOutput, Summoner.rl("crafting/blade_staff")))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 雨云法杖
     */
    public static final ItemEntry<SummonerWeaponItem<Cloud>> RainCloudStaff =
            Summoner.REGISTRATE.item("rain_cloud_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("rain_cloud_staff"), SummonerAttachmentEntityRegister.CLOUD)
                            .damage(3f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseCloudStaff)
                            .summon((weapon, player, itemStack) -> {
                                Cloud cloud = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, cloud.getSlotCost())) {
                                    cloud.setOwner(player);
                                    cloud.init(new PathNode(player.getBoundingBox().getCenter(), 0, 0, 0));
                                    cloud.setVelocity(player.getLookAngle().scale(0.5f));
                                    summonerHelper.add(cloud);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xA6670C)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 魔眼法杖 - 召唤双子魔眼
     */
    public static final ItemEntry<SummonerWeaponItem<Twins>> OpticStaff =
            Summoner.REGISTRATE.item("optic_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("optic_staff"), SummonerAttachmentEntityRegister.TWINS)
                            .damage(2.4f)
                            .knockback(0.2f)
                            .sound(SummonerSoundRegister.UseTerraprism)
                            .summon((weapon, player, itemStack) -> {
                                Twins laserEye = weapon.createMinion(player, itemStack);
                                laserEye.setLaserEye(true);
                                laserEye.setSlotCost(1);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, laserEye.getSlotCost())) {
                                    RandomSource random = player.getRandom();
                                    PathNode pathNode = new PathNode(player.getBoundingBox().getCenter().offsetRandom(random, 2), 0, 0, 0);
                                    laserEye.init(pathNode);
                                    laserEye.setOwner(player);
                                    summonerHelper.add(laserEye);

                                    Twins flameEye = weapon.createMinion(player, itemStack);
                                    flameEye.setFlameEye(true);
                                    flameEye.setSlotCost(0);
                                    flameEye.setOther(laserEye);
                                    flameEye.init(pathNode);
                                    flameEye.setOwner(player);
                                    summonerHelper.add(flameEye);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.RARE).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xFF4040)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 致命球法杖 - 召唤致命球仆从
     */
    public static final ItemEntry<SummonerWeaponItem<DeadlySphere>> DeadlySphereStaff =
            Summoner.REGISTRATE.item("deadly_sphere_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("deadly_sphere_staff"), SummonerAttachmentEntityRegister.DEADLY_SPHERE)
                            .damage(5.5f)
                            .knockback(0.2f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.RARE).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x04F6FF)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 外星法杖
     */
    public static final ItemEntry<SummonerWeaponItem<UFO>> XenoStaff =
            Summoner.REGISTRATE.item("xeno_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("xeno_staff"), SummonerAttachmentEntityRegister.UFO)
                            .damage(3.6f)
                            .knockback(0.2f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x1079FF)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 脉冲炮塔遥控装置
     */
    public static final ItemEntry<SummonerWeaponItem<PulseTurret>> PulseTurretRemote =
            Summoner.REGISTRATE.item("pulse_turret_remote", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("pulse_turret_remote"), SummonerAttachmentEntityRegister.PULSE_TURRET)
                            .damage(2.5f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseBallistaStaff)
                            .summon((weapon, player, itemStack) -> {
                                PulseTurret minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                AttachmentEntityData entityData = summonerHelper.getEntityData();
                                List<PulseTurret> pulseTurrets = entityData.get(weapon.getEntityType());
                                if (pulseTurrets.isEmpty() && summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 0, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xB16500)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 暴风雨法杖 - 召唤鲨鱼龙卷
     */
    public static final ItemEntry<SummonerWeaponItem<Sharknado>> TempestStaff =
            Summoner.REGISTRATE.item("tempest_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("tempest_staff"), SummonerAttachmentEntityRegister.SHARKNADO)
                            .damage(5f)
                            .knockback(0.2f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.RARE).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xDC30FF)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 泰拉棱镜 - 召唤泰拉棱镜仆从
     */
    public static final ItemEntry<SummonerWeaponItem<Terraprism>> TerraPrism =
            Summoner.REGISTRATE.item("terraprism", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("terraprism"), SummonerAttachmentEntityRegister.TERRA_PRISM)
                            .damage(9f)
                            .knockback(0.4f)
                            .sound(SummonerSoundRegister.UseTerraprism)
                            .summon((weapon, player, itemStack) -> {
                                Terraprism minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(minion.getInterpolatedIdleState(1));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x75A7D1)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 弩车魔杖
     */
    public static final ItemEntry<SummonerWeaponItem<Ballista>> BallistaRod =
            Summoner.REGISTRATE.item("ballista_rod", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("ballista_rod"), SummonerAttachmentEntityRegister.BALLISTA)
                            .damage(3f)
                            .knockback(0.47f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseBallistaStaff)
                            .summon((weapon, player, itemStack) -> {
                                Ballista minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.setSentryLevel(1);
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 1, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xD25259)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 弩车手杖
     */
    public static final ItemEntry<SummonerWeaponItem<Ballista>> BallistaCane =
            Summoner.REGISTRATE.item("ballista_cane", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("ballista_cane"), SummonerAttachmentEntityRegister.BALLISTA)
                            .damage(7.4f)
                            .knockback(0.47f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseBallistaStaff)
                            .summon((weapon, player, itemStack) -> {
                                Ballista minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.setSentryLevel(2);
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 0, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xED5C5C)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 弩车法杖
     */
    public static final ItemEntry<SummonerWeaponItem<Ballista>> BallistaStaff =
            Summoner.REGISTRATE.item("ballista_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("ballista_staff"), SummonerAttachmentEntityRegister.BALLISTA)
                            .damage(15.6f)
                            .knockback(0.47f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseBallistaStaff)
                            .summon((weapon, player, itemStack) -> {
                                Ballista minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.setSentryLevel(3);
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 1, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xF3485E)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 闪电光环魔杖
     */
    public static final ItemEntry<SummonerWeaponItem<LightningAura>> LightningAuraRod =
            Summoner.REGISTRATE.item("lightning_aura_rod", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("lightning_aura_rod"), SummonerAttachmentEntityRegister.LIGHTNING_AURA)
                            .damage(0.4f)
                            .knockback(0.25f)
                            .armorPierce(1000f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseBallistaStaff)
                            .summon((weapon, player, itemStack) -> {
                                LightningAura minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.setSentryLevel(1);
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 1, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xCC5649)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 闪电光环手杖
     */
    public static final ItemEntry<SummonerWeaponItem<LightningAura>> LightningAuraCane =
            Summoner.REGISTRATE.item("lightning_aura_cane", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("lightning_aura_cane"), SummonerAttachmentEntityRegister.LIGHTNING_AURA)
                            .damage(1.1f)
                            .knockback(0.25f)
                            .armorPierce(1000f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseBallistaStaff)
                            .summon((weapon, player, itemStack) -> {
                                LightningAura minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.setSentryLevel(2);
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 0, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xCF5549)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 闪电光环法杖
     */
    public static final ItemEntry<SummonerWeaponItem<LightningAura>> LightningAuraStaff =
            Summoner.REGISTRATE.item("lightning_aura_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("lightning_aura_staff"), SummonerAttachmentEntityRegister.LIGHTNING_AURA)
                            .damage(3.4f)
                            .knockback(0.25f)
                            .armorPierce(1000f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseBallistaStaff)
                            .summon((weapon, player, itemStack) -> {
                                LightningAura minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.setSentryLevel(3);
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 1, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xD75346)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 缥缈星核法杖 - 召唤缥缈星核仆从
     */
    public static final ItemEntry<SummonerWeaponItem<EtherealStellarCore>> EtherealStellarCoreStaff =
            Summoner.REGISTRATE.item("ethereal_stellar_core_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("ethereal_stellar_core_staff"), SummonerAttachmentEntityRegister.ETHEREAL_STELLAR_CORE)
                            .damage(5f)
                            .knockback(0.2f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .summon((weapon, player, itemStack) -> {
                                EtherealStellarCore minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                AttachmentEntityData entityData = summonerHelper.getEntityData();
                                List<EtherealStellarCore> etherealStellarCores = entityData.get(weapon.getEntityType());
                                if (etherealStellarCores.size() < 9 && summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(minion.getInterpolatedIdleState(1.0f));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xFCB200)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 星尘细胞杖 - 召唤星尘细胞仆从
     */
    public static final ItemEntry<SummonerWeaponItem<StardustCell>> StardustCellStaff =
            Summoner.REGISTRATE.item("stardust_cell_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("stardust_cell_staff"), SummonerAttachmentEntityRegister.STARDUST_CELL)
                            .damage(6f)
                            .knockback(0.2f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x077AFF)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 星尘龙杖 - 召唤星尘龙（多体节仆从）
     */
    public static final ItemEntry<SummonerWeaponItem<StardustDragon>> StardustDragonStaff =
            Summoner.REGISTRATE.item("stardust_dragon_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("stardust_dragon_staff"), SummonerAttachmentEntityRegister.STARDUST_DRAGON)
                            .damage(4f)
                            .knockback(0.2f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .summon((weapon, player, itemStack) -> {
                                LyraHelper helper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (helper.canSummon(slotType, 1)) {
                                    List<StardustDragon> existing = helper.getEntityData().get(weapon.getEntityType());
                                    if (existing.isEmpty()) {
                                        for (int i = 0; i < 3; i++) {
                                            StardustDragon minion = weapon.createMinion(player, itemStack);
                                            if (i < 2) {
                                                minion.setSlotCost(0);
                                            }
                                            minion.init(new PathNode(player.position().add(0, 3, -i * minion.getSegmentDistance()), 0, 0, 0));
                                            minion.setOwner(player);
                                            helper.add(minion);
                                        }
                                    } else {
                                        StardustDragon minion = weapon.createMinion(player, itemStack);
                                        StardustDragon last = existing.getLast();
                                        PathNode pathNode = last.getCurrentPathNode();
                                        Vec3 pos = pathNode.pos().add(last.getLookAngle().scale(-minion.getSegmentDistance()));
                                        minion.init(new PathNode(pos, pathNode.yaw(), pathNode.pitch(), pathNode.roll()));
                                        minion.setOwner(player);
                                        helper.add(minion);
                                    }
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xFFB400)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    public static final ItemEntry<SummonerWeaponItem<MiniDestroyer>> MiniEarthShaker =
            Summoner.REGISTRATE.item("mini_earth_shaker", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("mini_earth_shaker"), SummonerAttachmentEntityRegister.MINI_DESTROYER)
                            .damage(2f)
                            .knockback(0.2f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .summon((weapon, player, itemStack) -> {
                                LyraHelper helper = LyraHelper.get(player);
                                if (helper.canSummon(weapon.getSlotType(itemStack), 1)) {
                                    List<MiniDestroyer> segments = helper.getEntityData().get(weapon.getEntityType());
                                    MiniDestroyer tail = weapon.createMinion(player, itemStack);
                                    MiniProbe probe = new MiniProbe();
                                    probe.copyAttributes(tail);
                                    MiniDestroyer body;
                                    if (segments.isEmpty()) {
                                        MiniDestroyer head = weapon.createMinion(player, itemStack);
                                        body = weapon.createMinion(player, itemStack);
                                        head.setSlotCost(0);
                                        body.setSlotCost(0);
                                        head.setSameSize(3);
                                        head.init(new PathNode(player.position().add(0, 3, 0), 0, 0, 0));
                                        body.init(head.getCurrentPathNode().modifyPos(head.getPos().add(0, 0, -body.getSegmentDistance())));
                                        helper.add(head);
                                        helper.add(body);
                                    } else {
                                        body = segments.getLast();
                                    }
                                    int bodyIndex = Math.max(1, segments.size() - 1);
                                    body.setOrder(bodyIndex);
                                    body.setSameSize(bodyIndex + 2);
                                    tail.setOrder(bodyIndex + 1);
                                    tail.init(body.getCurrentPathNode().modifyPos(body.getPos().subtract(body.getLookAngle().scale(tail.getSegmentDistance()))));
                                    tail.setSameSize(body.getSameSize());
                                    probe.attachTo(body);
                                    helper.add(tail);
                                    helper.add(probe);
                                }
                            })
                            .build(itemProperties))
                    .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), prov.mcLoc("item/stick")))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();

    /**
     * 月亮传送门法杖
     */
    public static final ItemEntry<SummonerWeaponItem<MoonPortal>> MoonPortalStaff =
            Summoner.REGISTRATE.item("moon_portal_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("moon_portal_staff"), SummonerAttachmentEntityRegister.MOON_PORTAL)
                            .damage(10f)
                            .knockback(0.75f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseMoonPortalStaff)
                            .summon((weapon, player, itemStack) -> {
                                MoonPortal minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(new PathNode(player.position().add(0, 2, 0), 0, 0, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x22DD97)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 七彩水晶法杖
     */
    public static final ItemEntry<SummonerWeaponItem<RainbowCrystal>> RainbowCrystalStaff =
            Summoner.REGISTRATE.item("rainbow_crystal_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("rainbow_crystal_staff"), SummonerAttachmentEntityRegister.RAINBOW_CRYSTAL)
                            .damage(13f)
                            .knockback(0.75f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseMoonPortalStaff)
                            .summon((weapon, player, itemStack) -> {
                                RainbowCrystal minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(new PathNode(player.position().add(0, 2, 0), 0, 0, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x994DFF)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 无限剑鞘
     */
    public static final ItemEntry<SummonerWeaponItem<InfiniteShadow>> InfiniteScabbard =
            Summoner.REGISTRATE.item("infinite_scabbard", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("infinite_scabbard"), SummonerAttachmentEntityRegister.INFINITE_SHADOW)
                            .sound(SummonerSoundRegister.UseTerraprism)
                            .summon((weapon, player, itemStack) -> {
                                InfiniteShadow minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                ScabbardContainer container = player.getMainHandItem().getComponents().getOrDefault(SummonerDataComponentRegister.SCABBARD.get(), ScabbardContainer.EMPTY);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost()) && !container.isEmpty()) {
                                    minion.setItemStack(container.itemStack());
                                    minion.init(minion.getInterpolatedIdleState(1));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC)
                            .component(SummonerDataComponentRegister.SCABBARD, ScabbardContainer.EMPTY)
                            .component(LyraDataComponentRegister.RARITY, new LyraRarity(0xA66053)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 虚空吞噬者傀具
     */
    public static final ItemEntry<SummonerWeaponItem<VoidEater>> VoidEaterMarionette =
            Summoner.REGISTRATE.item("void_eater_marionette", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("void_eater_marionette"), SummonerAttachmentEntityRegister.VOID_EATER)
                            .damage(11f)
                            .knockback(0.5f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .summon((weapon, player, itemStack) -> {
                                LyraHelper helper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (helper.canSummon(slotType, 1)) {
                                    List<VoidEater> existing = helper.getEntityData().get(weapon.getEntityType());
                                    if (existing.isEmpty()) {
                                        for (int i = 0; i < 3; i++) {
                                            VoidEater minion = weapon.createMinion(player, itemStack);
                                            if (i < 2) {
                                                minion.setSlotCost(0);
                                            }
                                            minion.init(new PathNode(player.position().add(0, 3, -i * minion.getSegmentDistance()), 0, 0, 0));
                                            minion.setOwner(player);
                                            helper.add(minion);
                                        }
                                    } else {
                                        VoidEater minion = weapon.createMinion(player, itemStack);
                                        VoidEater last = existing.getLast();
                                        PathNode pathNode = last.getCurrentPathNode();
                                        Vec3 pos = pathNode.pos().add(last.getLookAngle().scale(-minion.getSegmentDistance()));
                                        minion.init(new PathNode(pos, pathNode.yaw(), pathNode.pitch(), pathNode.roll()));
                                        minion.setOwner(player);
                                        helper.add(minion);
                                    }
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x955CAA)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 雷云盆栽
     */
    public static final ItemEntry<SummonerWeaponItem<SuperPeashooter>> ThundercloudBonsai =
            Summoner.REGISTRATE.item("thundercloud_bonsai", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("thundercloud_bonsai"), SummonerAttachmentEntityRegister.SUPER_PEASHOOTER)
                            .damage(12f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseMoonPortalStaff)
                            .summon((weapon, player, itemStack) -> {
                                SuperPeashooter minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 1, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x2978D3)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 玉米加农炮
     */
    public static final ItemEntry<SummonerWeaponItem<Cannon>> CornCannon =
            Summoner.REGISTRATE.item("corn_cannon", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("corn_cannon"), SummonerAttachmentEntityRegister.CANNON)
                            .damage(6400f)
                            .armorPierce(3600f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseBallistaStaff)
                            .summon((weapon, player, itemStack) -> {
                                Cannon minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                minion.setSlotCost(2);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 0, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x07BA19)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 死魂灵巫术单元
     */
    public static final ItemEntry<SummonerWeaponItem<NecroSpirit>> NecroSpiritStaff =
            Summoner.REGISTRATE.item("necro_spirit_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("necro_spirit_staff"), SummonerAttachmentEntityRegister.NECRO_SPIRIT)
                            .damage(700f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .summon((weapon, player, itemStack) -> {
                                NecroSpirit minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                AttachmentEntityData entityData = summonerHelper.getEntityData();
                                if (entityData.get(weapon.getEntityType()).isEmpty() && summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(minion.getInterpolatedIdleState(1.0f));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xDA5152)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 激光机枪 - 召唤激光机枪仆从（最多1个）
     */
    public static final ItemEntry<SummonerWeaponItem<LaserMinigun>> LaserMinigunStaff =
            Summoner.REGISTRATE.item("laser_minigun", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("laser_minigun"), SummonerAttachmentEntityRegister.LASER_MINIGUN)
                            .damage(6f)
                            .knockback(0.2f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .summon((weapon, player, itemStack) -> {
                                LaserMinigun minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                AttachmentEntityData entityData = summonerHelper.getEntityData();
                                if (entityData.get(weapon.getEntityType()).isEmpty() && summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(minion.getInterpolatedIdleState(1.0f));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.RARE).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x0084E6)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 藤壶法杖
     */
    public static final ItemEntry<SummonerWeaponItem<Barnacle>> BarnacleStaff =
            Summoner.REGISTRATE.item("barnacle_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("barnacle_staff"), SummonerAttachmentEntityRegister.BARNACLE)
                            .damage(3.3f)
                            .knockback(0.75f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .summon((weapon, player, itemStack) -> {
                                Barnacle minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 0, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x367A9C)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 眼球激光塔
     */
    public static final ItemEntry<SummonerWeaponItem<EyeLaserTurret>> EyeLaserTurretStaff =
            Summoner.REGISTRATE.item("eye_laser_turret_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("eye_laser_turret_staff"), SummonerAttachmentEntityRegister.EYE_LASER_TURRET)
                            .damage(1.2f)
                            .knockback(0.25f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .summon((weapon, player, itemStack) -> {
                                EyeLaserTurret minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 0, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xA26632)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 寒霜九头蛇法杖
     */
    public static final ItemEntry<SummonerWeaponItem<Hydra>> HydraStaff =
            Summoner.REGISTRATE.item("hydra_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("hydra_staff"), SummonerAttachmentEntityRegister.HYDRA)
                            .damage(10f)
                            .knockback(0.75f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .summon((weapon, player, itemStack) -> {
                                Hydra minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 0, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.RARE).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x0F98EA)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 爆炸烈焰魔杖
     */
    public static final ItemEntry<SummonerWeaponItem<Inferno>> InfernoRod =
            Summoner.REGISTRATE.item("inferno_rod", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("inferno_rod"), SummonerAttachmentEntityRegister.INFERNO)
                            .damage(1.7f)
                            .knockback(0.3f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseBallistaStaff)
                            .summon((weapon, player, itemStack) -> {
                                Inferno minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.setSentryLevel(1);
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 1, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.RARE).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xB94BEB)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 爆炸烈焰手杖
     */
    public static final ItemEntry<SummonerWeaponItem<Inferno>> InfernoCane =
            Summoner.REGISTRATE.item("inferno_cane", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("inferno_cane"), SummonerAttachmentEntityRegister.INFERNO)
                            .damage(4.2f)
                            .knockback(0.3f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseBallistaStaff)
                            .summon((weapon, player, itemStack) -> {
                                Inferno minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.setSentryLevel(2);
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 0, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.RARE).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xB94BEB)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 爆炸烈焰法杖
     */
    public static final ItemEntry<SummonerWeaponItem<Inferno>> InfernoStaff =
            Summoner.REGISTRATE.item("inferno_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("inferno_staff"), SummonerAttachmentEntityRegister.INFERNO)
                            .damage(8.8f)
                            .knockback(0.3f)
                            .slotType(MinionSlotType.Sentry)
                            .sound(SummonerSoundRegister.UseBallistaStaff)
                            .summon((weapon, player, itemStack) -> {
                                Inferno minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.setSentryLevel(3);
                                    minion.init(new PathNode(player.position().add(0, 1, 0), 0, 1, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.RARE).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xFFA91A)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 血红法杖 - 召唤血蝙蝠仆从
     */
    public static final ItemEntry<SummonerWeaponItem<SanguineBat>> SanguineStaff =
            Summoner.REGISTRATE.item("sanguine_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("sanguine_staff"), SummonerAttachmentEntityRegister.SANGUINE_BAT)
                            .damage(3.5f)
                            .knockback(0.3f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .summon((weapon, player, itemStack) -> {
                                SanguineBat minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(new PathNode(player.getBoundingBox().getCenter().offsetRandom(player.getRandom(), 2), 0, 0, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xFF4242)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();
    /**
     * 毁灭法杖 - 召唤禁戒遗迹仆从
     */
    public static final ItemEntry<SummonerWeaponItem<RuinRelic>> RuinStaff =
            Summoner.REGISTRATE.item("ruin_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("ruin_staff"), SummonerAttachmentEntityRegister.RUIN_RELIC)
                            .damage(4f)
                            .knockback(0.4f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .summon((weapon, player, itemStack) -> {
                                RuinRelic minion = weapon.createMinion(player, itemStack);
                                LyraHelper summonerHelper = LyraHelper.get(player);
                                MinionSlotType slotType = weapon.getSlotType(itemStack);
                                if (summonerHelper.canSummon(slotType, minion.getSlotCost())) {
                                    minion.init(new PathNode(player.getBoundingBox().getCenter().offsetRandom(player.getRandom(), 2), 0, 0, 0));
                                    minion.setOwner(player);
                                    summonerHelper.add(minion);
                                }
                            })
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.EPIC).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x9D6A0F)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();

    // ===================== confluence 迁移新增法杖 =====================
    // 数值取自 confluence 的 SummonItems；注意 Summoner 现有法杖伤害量级约为 confluence 的 1/10，
    // 这六把的数值待确认是否需要同步缩放。

    /**
     * 小鸟法杖 - 召唤小鸟仆从
     */
    public static final ItemEntry<SummonerWeaponItem<Finch>> FinchStaff =
            Summoner.REGISTRATE.item("finch_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("finch_staff"), SummonerAttachmentEntityRegister.FINCH)
                            .damage(0.3f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x4A9EFF)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();

    /**
     * 铁傀儡法杖 - 召唤铁傀儡仆从
     */
    public static final ItemEntry<SummonerWeaponItem<IronGolem>> IronGolemStaff =
            Summoner.REGISTRATE.item("iron_golem_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("iron_golem_staff"), SummonerAttachmentEntityRegister.IRON_GOLEM)
                            .damage(1.2f)
                            .knockback(1.0f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON).component(LyraDataComponentRegister.RARITY, new LyraRarity(0x4A9EFF)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();

    /**
     * 史莱姆法杖 - 召唤史莱姆仆从
     */
    public static final ItemEntry<SummonerWeaponItem<Slime>> SlimeStaff =
            Summoner.REGISTRATE.item("slime_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("slime_staff"), SummonerAttachmentEntityRegister.SLIME)
                            .damage(0.5f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xFF7A7A)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();

    /**
     * 小雪怪法杖 - 召唤小雪怪仆从
     */
    public static final ItemEntry<SummonerWeaponItem<SnowFlinx>> SnowFlinxStaff =
            Summoner.REGISTRATE.item("snow_flinx_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("snow_flinx_staff"), SummonerAttachmentEntityRegister.SNOW_FLINX)
                            .damage(0.7f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.RARE).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xFE7902)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();

    /**
     * 吸血鬼青蛙法杖 - 召唤吸血鬼青蛙仆从
     */
    public static final ItemEntry<SummonerWeaponItem<VampireFrog>> VampireFrogStaff =
            Summoner.REGISTRATE.item("vampire_frog_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("vampire_frog_staff"), SummonerAttachmentEntityRegister.VAMPIRE_FROG)
                            .damage(1.1f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.RARE).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xFE7902)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();

    /**
     * 蜘蛛法杖 - 召唤蜘蛛仆从
     */
    public static final ItemEntry<SummonerWeaponItem<Spider>> SpiderStaff =
            Summoner.REGISTRATE.item("spider_staff", itemProperties -> new MinionWeaponItemBuilder<>(Summoner.rl("spider_staff"), SummonerAttachmentEntityRegister.SPIDER)
                            .damage(2.0f)
                            .knockback(1.0f)
                            .sound(SummonerSoundRegister.UseMinionWeapon)
                            .build(itemProperties))
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON).component(LyraDataComponentRegister.RARITY, new LyraRarity(0xFF7A7A)))
                    .model((ctx, prov) -> prov.handheld(ctx::getEntry))
                    .tag(SummonerItemTagsRegister.MinionWeapon)
                    .register();

    public static void init() {
    }
}
