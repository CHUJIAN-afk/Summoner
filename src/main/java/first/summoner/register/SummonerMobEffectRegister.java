package first.summoner.register;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import first.lyra.register.LyraAttributeRegister;
import first.lyra.register.SimpleMobEffectBuilder;
import first.summoner.Summoner;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SummonerMobEffectRegister {

    private static final Registrate REGISTER = Summoner.REGISTRATE;

    /**
     * 着魔
     */
    public static final RegistryEntry<MobEffect, MobEffect> Obsession =
            REGISTER.simple("obsession", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.BENEFICIAL, 0xb565ff)
                    .addAttributeModifier(LyraAttributeRegister.MinionMaxCount, Summoner.rl("obsession"), 1, AttributeModifier.Operation.ADD_VALUE)
                    .build()
            );

    /**
     * 细胞寄生
     */
    public static final RegistryEntry<MobEffect, MobEffect> CellParasitism =
            REGISTER.simple("cell_parasitism", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.HARMFUL, 0x8AE0FF)
                    .addAttributeModifier(LyraAttributeRegister.HealthRegen, Summoner.rl("cell_parasitism"), amplifier -> -(amplifier + 1) * 2, AttributeModifier.Operation.ADD_VALUE)
                    .build()
            );

    /**
     * 诅咒焰
     */
    public static final RegistryEntry<MobEffect, MobEffect> CursedFlame =
            REGISTER.simple("cursed_flame", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.HARMFUL, 0x1AFF05)
                    .addAttributeModifier(LyraAttributeRegister.HealthRegen, Summoner.rl("cursed_flame"), amplifier -> -(amplifier + 1) * 2.4, AttributeModifier.Operation.ADD_VALUE)
                    .build()
            );

    /**
     * 暗影焰
     */
    public static final RegistryEntry<MobEffect, MobEffect> Shadowflame =
            REGISTER.simple("shadowflame", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.HARMFUL, 0x9933FF)
                    .addAttributeModifier(LyraAttributeRegister.HealthRegen, Summoner.rl("shadowflame"), amplifier -> -(amplifier + 1) * 1.5, AttributeModifier.Operation.ADD_VALUE)
                    .build()
            );

    /**
     * 酸性毒液 - 每秒损失 1 点生命（经 Lyra 生命恢复属性结算，与其它伤害性效果一致）
     */
    public static final RegistryEntry<MobEffect, MobEffect> AcidVenom =
            REGISTER.simple("acid_venom", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.HARMFUL, 0x228B22)
                    .addAttributeModifier(LyraAttributeRegister.HealthRegen, Summoner.rl("acid_venom"), amplifier -> -(amplifier + 1) * 1, AttributeModifier.Operation.ADD_VALUE)
                    .build()
            );

    /**
     * 月噬
     */
    public static final RegistryEntry<MobEffect, MobEffect> MoonBite =
            REGISTER.simple("moon_bite", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.HARMFUL, 0x00d4b0)
                    .addAttributeModifier(Attributes.ARMOR, Summoner.rl("moon_bite"), amplifier -> -(amplifier + 1) * 10, AttributeModifier.Operation.ADD_VALUE)
                    .build()
            );

    /**
     * 噬神怒焰
     */
    public static final RegistryEntry<MobEffect, MobEffect> GodSlayerInferno =
            REGISTER.simple("god_slayer_inferno", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.HARMFUL, 0x6f19d4)
                    .addAttributeModifier(LyraAttributeRegister.HealthRegen, Summoner.rl("god_slayer_inferno"), amplifier -> -(amplifier + 1) * 25, AttributeModifier.Operation.ADD_VALUE)
                    .build()
            );

    /**
     * 碎甲
     */
    public static final RegistryEntry<MobEffect, MobEffect> ArmorCrunch =
            REGISTER.simple("armor_crunch", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.HARMFUL, 0x303030)
                    .addAttributeModifier(Attributes.ARMOR, Summoner.rl("armor_crunch"), amplifier -> -(amplifier + 1) * 5, AttributeModifier.Operation.ADD_VALUE)
                    .build()
            );

    public static final RegistryEntry<MobEffect, MobEffect> BallistaPanicked =
            REGISTER.simple("ballista_panicked", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.BENEFICIAL, 0xffa219)
                    .build()
            );

    public static final RegistryEntry<MobEffect, MobEffect> SoulMight =
            REGISTER.simple("soul_might", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.BENEFICIAL, 0x9B6BFF)
                    .addAttributeModifier(LyraAttributeRegister.SummonDamage, Summoner.rl("soul_might"), 0.08, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .build()
            );

    public static final RegistryEntry<MobEffect, MobEffect> SoulDefense =
            REGISTER.simple("soul_defense", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.BENEFICIAL, 0x88A8FF)
                    .addAttributeModifier(Attributes.ARMOR, Summoner.rl("soul_defense"), 2, AttributeModifier.Operation.ADD_VALUE)
                    .build()
            );

    public static final RegistryEntry<MobEffect, MobEffect> SoulRecovery =
            REGISTER.simple("soul_recovery", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.BENEFICIAL, 0x83FFB1)
                    .addAttributeModifier(LyraAttributeRegister.HealthRegen, Summoner.rl("soul_recovery"), 0.05, AttributeModifier.Operation.ADD_VALUE)
                    .build());

    public static final RegistryEntry<MobEffect, MobEffect> HallowedMight =
            REGISTER.simple("hallowed_might", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.BENEFICIAL, 0xFFD875)
                    .addAttributeModifier(LyraAttributeRegister.SummonDamage, Summoner.rl("hallowed_might"), 0.16, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .build());

    public static final RegistryEntry<MobEffect, MobEffect> HallowedGrace =
            REGISTER.simple("hallowed_grace", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.BENEFICIAL, 0xFFF4B0)
                    .addAttributeModifier(Attributes.ARMOR, Summoner.rl("hallowed_grace"), 4, AttributeModifier.Operation.ADD_VALUE)
                    .build());

    public static final RegistryEntry<MobEffect, MobEffect> HallowedRadiance =
            REGISTER.simple("hallowed_radiance", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.BENEFICIAL, 0xFFFFAA)
                    .addAttributeModifier(LyraAttributeRegister.HealthRegen, Summoner.rl("hallowed_radiance"), 0.1, AttributeModifier.Operation.ADD_VALUE)
                    .build());

    public static final RegistryEntry<MobEffect, MobEffect> PhantasmalMight =
            REGISTER.simple("phantasmal_might", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.BENEFICIAL, 0xD48CFF)
                    .addAttributeModifier(LyraAttributeRegister.SummonDamage, Summoner.rl("phantasmal_might"), 0.32, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .build());

    public static final RegistryEntry<MobEffect, MobEffect> PhantasmalBulwark =
            REGISTER.simple("phantasmal_bulwark", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.BENEFICIAL, 0xB58CFF)
                    .addAttributeModifier(Attributes.ARMOR, Summoner.rl("phantasmal_bulwark"), 8, AttributeModifier.Operation.ADD_VALUE)
                    .build());

    public static final RegistryEntry<MobEffect, MobEffect> PhantasmalRebirth =
            REGISTER.simple("phantasmal_rebirth", Registries.MOB_EFFECT, () -> new SimpleMobEffectBuilder(MobEffectCategory.BENEFICIAL, 0xFF8CDE)
                    .addAttributeModifier(LyraAttributeRegister.HealthRegen, Summoner.rl("phantasmal_rebirth"), 0.2, AttributeModifier.Operation.ADD_VALUE)
                    .build());


    public static void init() {
    }
}
