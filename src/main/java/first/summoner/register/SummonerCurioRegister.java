package first.summoner.register;


import com.google.common.collect.ImmutableMultimap;
import com.tterrag.registrate.util.entry.ItemEntry;
import first.lyra.api.LyraHelper;
import first.lyra.common.attachmentEntity.PathNode;
import first.lyra.register.LyraAttributeRegister;
import first.summoner.Summoner;
import first.summoner.common.item.CurioItem;
import first.summoner.common.projectile.MiniStardustCell;
import first.summoner.utils.CuriosUtil;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class SummonerCurioRegister {

    /**
     * 蜂巢背包
     */
    public static final ItemEntry<CurioItem> HivePack = Summoner.REGISTRATE.item("hive_pack", itemProperties -> CurioItem.builder()
                    .properties(properties -> properties.rarity(Rarity.EPIC))
                    .build(Summoner.rl("hive_pack")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 矮人项链 - 仆从栏+1
     */
    public static final ItemEntry<CurioItem> PygmyNecklace = Summoner.REGISTRATE.item("pygmy_necklace", itemProperties -> CurioItem.builder()
                    .attributeModifiers((slotContext, id, stack) -> {
                        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
                        builder.put(LyraAttributeRegister.MinionMaxCount, new AttributeModifier(id, 1, AttributeModifier.Operation.ADD_VALUE));
                        return builder.build();
                    })
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                    .build(Summoner.rl("pygmy_necklace")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 大力士甲虫 - 仆从栏+1，召唤击退+50%
     */
    public static final ItemEntry<CurioItem> HerculesBeetle = Summoner.REGISTRATE.item("hercules_beetle", itemProperties -> CurioItem.builder()
                    
                    .attributeModifiers((slotContext, id, stack) -> {
                        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
                        builder.put(LyraAttributeRegister.MinionMaxCount, new AttributeModifier(id, 1, AttributeModifier.Operation.ADD_VALUE));
                        builder.put(LyraAttributeRegister.SummonKnockback, new AttributeModifier(id, 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                        return builder.build();
                    })
                    .properties(properties -> properties.rarity(Rarity.RARE))
                    .build(Summoner.rl("hercules_beetle")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 学徒围巾 - 仆从数量+1，召唤伤害+10%
     */
    public static final ItemEntry<CurioItem> ApprenticesScarf = Summoner.REGISTRATE.item("apprentices_scarf", itemProperties -> CurioItem.builder()
                    .attributeModifiers((slotContext, id, stack) -> {
                        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
                        builder.put(LyraAttributeRegister.SentryMaxCount, new AttributeModifier(id, 1, AttributeModifier.Operation.ADD_VALUE));
                        builder.put(LyraAttributeRegister.SummonDamage, new AttributeModifier(id, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                        return builder.build();
                    })
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                    .build(Summoner.rl("apprentices_scarf")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 女猎人圆盾 - 护甲+2，仆从数量+1
     */
    public static final ItemEntry<CurioItem> HuntressesBuckler = Summoner.REGISTRATE.item("huntresses_buckler", itemProperties -> CurioItem.builder()
                    .attributeModifiers((slotContext, id, stack) -> {
                        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
                        builder.put(Attributes.ARMOR, new AttributeModifier(id, 2, AttributeModifier.Operation.ADD_VALUE));
                        builder.put(LyraAttributeRegister.SentryMaxCount, new AttributeModifier(id, 1, AttributeModifier.Operation.ADD_VALUE));
                        return builder.build();
                    })
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                    .build(Summoner.rl("huntresses_buckler")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 武僧腰带 - 召唤伤害+10%，召唤击退+50%
     */
    public static final ItemEntry<CurioItem> MonksBelt = Summoner.REGISTRATE.item("monks_belt", itemProperties -> CurioItem.builder()
                    .attributeModifiers((slotContext, id, stack) -> {
                        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
                        builder.put(LyraAttributeRegister.SummonDamage, new AttributeModifier(id, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                        builder.put(LyraAttributeRegister.SummonKnockback, new AttributeModifier(id, 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                        return builder.build();
                    })
                    .build(Summoner.rl("monks_belt")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 侍卫护盾 - 护甲+2，召唤伤害+10%
     */
    public static final ItemEntry<CurioItem> SquiresShield = Summoner.REGISTRATE.item("squires_shield", itemProperties -> CurioItem.builder()
                    .attributeModifiers((slotContext, id, stack) -> {
                        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
                        builder.put(Attributes.ARMOR, new AttributeModifier(id, 2, AttributeModifier.Operation.ADD_VALUE));
                        builder.put(LyraAttributeRegister.SummonDamage, new AttributeModifier(id, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                        return builder.build();
                    })
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                    .build(Summoner.rl("squires_shield")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 召唤师徽章 - 召唤伤害+15%
     */
    public static final ItemEntry<CurioItem> SummonerEmblem = Summoner.REGISTRATE.item("summoner_emblem", itemProperties -> CurioItem.builder()
                    .attributeModifiers((slotContext, id, stack) -> {
                        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
                        builder.put(LyraAttributeRegister.SummonDamage, new AttributeModifier(id, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                        return builder.build();
                    })
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                    .build(Summoner.rl("summoner_emblem")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 始源暗影焰
     */
    public static final ItemEntry<CurioItem> PrimordialShadowflame = Summoner.REGISTRATE.item("primordial_shadowflame", itemProperties -> CurioItem.builder()
                    .attributeModifiers((slotContext, id, stack) -> {
                        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
                        builder.put(LyraAttributeRegister.MinionMaxCount, new AttributeModifier(id, 1, AttributeModifier.Operation.ADD_VALUE));
                        return builder.build();
                    })
                    .onPostDamage((minion, owner, target, damageSource) -> target.addEffect(new MobEffectInstance(SummonerMobEffectRegister.Shadowflame, 60, 0)))
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                    .build(Summoner.rl("primordial_shadowflame")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 死灵卷轴 - 仆从栏+1，召唤伤害+10%
     */
    public static final ItemEntry<CurioItem> NecromanticScroll = Summoner.REGISTRATE.item("necromantic_scroll", itemProperties -> CurioItem.builder()
                    .attributeModifiers((slotContext, id, stack) -> {
                        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
                        builder.put(LyraAttributeRegister.MinionMaxCount, new AttributeModifier(id, 1, AttributeModifier.Operation.ADD_VALUE));
                        builder.put(LyraAttributeRegister.SummonDamage, new AttributeModifier(id, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                        return builder.build();
                    })
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                    .build(Summoner.rl("necromantic_scroll")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 威胁分析仪 - 加大仆从的索敌半径
     */
    public static final ItemEntry<CurioItem> ThreatAnalyzer = Summoner.REGISTRATE.item("threat_analyzer", itemProperties -> CurioItem.builder()
                    .attributeModifiers((slotContext, id, stack) -> {
                        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
                        builder.put(LyraAttributeRegister.SummonSearchRange, new AttributeModifier(id, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                        return builder.build();
                    })
                    .properties(properties -> properties.rarity(Rarity.EPIC))
                    .build(Summoner.rl("threat_analyzer")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 甲虫莎草纸 - 仆从栏+2，召唤伤害+15%，召唤击退+50%
     */
    public static final ItemEntry<CurioItem> PapyrusScarab = Summoner.REGISTRATE.item("papyrus_scarab", itemProperties -> CurioItem.builder()
                    .attributeModifiers((slotContext, id, stack) -> {
                        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
                        builder.put(LyraAttributeRegister.MinionMaxCount, new AttributeModifier(id, 2, AttributeModifier.Operation.ADD_VALUE));
                        builder.put(LyraAttributeRegister.SummonDamage, new AttributeModifier(id, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                        builder.put(LyraAttributeRegister.SummonKnockback, new AttributeModifier(id, 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                        return builder.build();
                    })
                    .properties(properties -> properties.rarity(Rarity.EPIC))
                    .build(Summoner.rl("papyrus_scarab")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 灵魂浮雕 - 仆从攻击后随机获得灵魂增益（下位互斥）
     */
    public static final ItemEntry<CurioItem> SoulRelief = Summoner.REGISTRATE.item("soul_relief", itemProperties -> CurioItem.builder()
                    .onPostDamage((minion, owner, target, damageSource) -> {
                        if (!CuriosUtil.isEquipped(owner, SummonerCurioRegister.HallowedRune.get()) && !CuriosUtil.isEquipped(owner, SummonerCurioRegister.PhantasmalRelic.get())) {
                            List<Holder<MobEffect>> effects = new ArrayList<>();
                            effects.add(SummonerMobEffectRegister.SoulMight);
                            effects.add(SummonerMobEffectRegister.SoulDefense);
                            effects.add(SummonerMobEffectRegister.SoulRecovery);
                            RandomSource random = minion.getRandom();
                            owner.addEffect(new MobEffectInstance(effects.get(random.nextInt(effects.size())), 60));
                        }
                    })
                    .properties(properties -> properties.rarity(Rarity.UNCOMMON))
                    .build(Summoner.rl("soul_relief")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 神圣符文 - 仆从攻击后随机获得神圣增益（中位互斥）
     */
    public static final ItemEntry<CurioItem> HallowedRune = Summoner.REGISTRATE.item("hallowed_rune", itemProperties -> CurioItem.builder()
                    .onPostDamage((minion, owner, target, damageSource) -> {
                        if (!CuriosUtil.isEquipped(owner, SummonerCurioRegister.PhantasmalRelic.get())) {
                            List<Holder<MobEffect>> effects = new ArrayList<>();
                            effects.add(SummonerMobEffectRegister.HallowedMight);
                            effects.add(SummonerMobEffectRegister.HallowedGrace);
                            effects.add(SummonerMobEffectRegister.HallowedRadiance);
                            RandomSource random = minion.getRandom();
                            owner.addEffect(new MobEffectInstance(effects.get(random.nextInt(effects.size())), 60));
                        }
                    })
                    .properties(properties -> properties.rarity(Rarity.RARE))
                    .build(Summoner.rl("hallowed_rune")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 幻魂神物 - 仆从攻击后随机获得幻魂增益（上位）
     */
    public static final ItemEntry<CurioItem> PhantasmalRelic = Summoner.REGISTRATE.item("phantasmal_relic", itemProperties -> CurioItem.builder()
                    .onPostDamage((minion, owner, target, damageSource) -> {
                        List<Holder<MobEffect>> effects = new ArrayList<>();
                        effects.add(SummonerMobEffectRegister.PhantasmalMight);
                        effects.add(SummonerMobEffectRegister.PhantasmalBulwark);
                        effects.add(SummonerMobEffectRegister.PhantasmalRebirth);
                        RandomSource random = minion.getRandom();
                        owner.addEffect(new MobEffectInstance(effects.get(random.nextInt(effects.size())), 60));
                    })
                    .properties(properties -> properties.rarity(Rarity.EPIC))
                    .build(Summoner.rl("phantasmal_relic")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 猎魂徽记
     */
    public static final ItemEntry<CurioItem> HuntSoulEmblem = Summoner.REGISTRATE.item("hunt_soul_emblem", itemProperties -> CurioItem.builder()
                    .attributeModifiers((slotContext, id, stack) -> {
                        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
                        builder.put(LyraAttributeRegister.SummonDamage, new AttributeModifier(id, 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                        return builder.build();
                    })
                    .properties(properties -> properties.rarity(Rarity.EPIC))
                    .build(Summoner.rl("hunt_soul_emblem")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 蚀日尊戒
     */
    public static final ItemEntry<CurioItem> EclipseRing = Summoner.REGISTRATE.item("eclipse_ring", itemProperties -> CurioItem.builder()
                    .attributeModifiers((slotContext, id, stack) -> {
                        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
                        builder.put(LyraAttributeRegister.SummonDamage, new AttributeModifier(id, 0.12, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                        builder.put(LyraAttributeRegister.MinionMaxCount, new AttributeModifier(id, 2, AttributeModifier.Operation.ADD_VALUE));
                        builder.put(LyraAttributeRegister.SentryMaxCount, new AttributeModifier(id, 2, AttributeModifier.Operation.ADD_VALUE));
                        builder.put(LyraAttributeRegister.SummonKnockback, new AttributeModifier(id, 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                        builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(id, 1.0, AttributeModifier.Operation.ADD_VALUE));
                        builder.put(LyraAttributeRegister.HealthRegen, new AttributeModifier(id, 0.1, AttributeModifier.Operation.ADD_VALUE));
                        builder.put(Attributes.ARMOR, new AttributeModifier(id, 3, AttributeModifier.Operation.ADD_VALUE));
                        return builder.build();
                    })
                    .properties(properties -> properties.rarity(Rarity.EPIC))
                    .build(Summoner.rl("eclipse_ring")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();
    /**
     * 星尘碎片 - 仆从攻击时5%概率产生星尘细胞射弹
     */
    public static final ItemEntry<CurioItem> StardustFragment = Summoner.REGISTRATE.item("stardust_fragment", itemProperties -> CurioItem.builder()
                    .onPostDamage((minion, owner, target, damageSource) -> {
                        if (minion.getRandom().nextFloat() < 0.05f) {
                            Vec3 startPos = minion.getPos();
                            Vec3 direction = startPos.offsetRandom(minion.getRandom(), 1).subtract(startPos).normalize();
                            MiniStardustCell projectile = new MiniStardustCell();
                            projectile.setOwner(owner);
                            projectile.init(new PathNode(startPos, direction));
                            projectile.copyAttributes(minion);
                            projectile.setVelocity(direction.scale(0.25f));
                            projectile.setChaseTarget(target);
                            LyraHelper.get(owner).add(projectile);
                        }
                    })
                    .properties(properties -> properties.rarity(Rarity.EPIC))
                    .build(Summoner.rl("stardust_fragment")))
            .defaultModel()
            .tag(SummonerItemTagsRegister.Curio)
                    .register();

    public static void init() {
    }
}
