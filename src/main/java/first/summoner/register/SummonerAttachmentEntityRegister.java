package first.summoner.register;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import first.lyra.common.attachmentEntity.AttachmentEntity;
import first.lyra.common.attachmentEntity.AttachmentEntityType;
import first.lyra.register.LyraRegistries;
import first.summoner.Summoner;
import first.summoner.common.minion.minion.*;
import first.summoner.common.minion.sentry.*;
import first.summoner.common.projectile.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

/**
 * 附件实体类型注册类。
 * <p>
 * 只注册实体类型本身；渲染器注册见 {@link SummonerAttachmentEntityRenderRegister}（仅客户端加载）。
 * 渲染器引用客户端类，绝不能在本类中直接引用，否则服务端加载本类时会被
 * RuntimeDistCleaner 拦截（Attempted to load class ... for invalid dist DEDICATED_SERVER）导致崩溃。
 */
public class SummonerAttachmentEntityRegister {

    private static final Registrate REGISTER = Summoner.REGISTRATE;

    /** Lyra 未公开该注册表的 ResourceKey，按注册表自身的 key 取回并恢复元素类型。 */
    @SuppressWarnings("unchecked")
    private static final ResourceKey<Registry<AttachmentEntityType<?>>> ATTACHMENT_ENTITY_TYPES_KEY =
            (ResourceKey<Registry<AttachmentEntityType<?>>>) (ResourceKey<?>) LyraRegistries.ATTACHMENT_ENTITY_TYPES.key();

    // ===================== 仆从类型 =====================

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<InfiniteShadow>> INFINITE_SHADOW = register("infinite_shadow", InfiniteShadow::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Terraprism>> TERRA_PRISM = register("terraprism", Terraprism::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<StardustCell>> STARDUST_CELL = register("stardust_cell", StardustCell::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<EnchantedThrowingKnives>> ENCHANTED_THROWING_KNIVES = register("enchanted_throwing_knives", EnchantedThrowingKnives::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<StardustDragon>> STARDUST_DRAGON = register("stardust_dragon", StardustDragon::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<MiniDestroyer>> MINI_DESTROYER = register("mini_destroyer", MiniDestroyer::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<MiniProbe>> MINI_PROBE = register("mini_probe", MiniProbe::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Twins>> TWINS = register("twins", Twins::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Sharknado>> SHARKNADO = register("sharknado", Sharknado::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<DeadlySphere>> DEADLY_SPHERE = register("deadly_sphere", DeadlySphere::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<EtherealStellarCore>> ETHEREAL_STELLAR_CORE = register("ethereal_stellar_core", EtherealStellarCore::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<OreScout>> ORE_SCOUT = register("survey_drone", OreScout::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<ScavengerFairy>> SCAVENGER_FAIRY = register("scavenger_fairy", ScavengerFairy::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<ChlorophyteCrystal>> CHLOROPHYTE_CRYSTAL = register("chlorophyte_crystal", ChlorophyteCrystal::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<MoonPortal>> MOON_PORTAL = register("moon_portal", MoonPortal::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<RainbowCrystal>> RAINBOW_CRYSTAL = register("rainbow_crystal", RainbowCrystal::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Ballista>> BALLISTA = register("ballista", Ballista::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<SuperPeashooter>> SUPER_PEASHOOTER = register("super_peashooter", SuperPeashooter::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<VoidEater>> VOID_EATER = register("void_eater", VoidEater::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Cloud>> CLOUD = register("cloud", Cloud::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<PulseTurret>> PULSE_TURRET = register("pulse_turret", PulseTurret::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Hornet>> HORNET = register("hornet", Hornet::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Imp>> IMP = register("imp", Imp::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<UFO>> UFO = register("ufo", UFO::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Cannon>> CANNON = register("cannon", Cannon::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<NecroSpirit>> NECRO_SPIRIT = register("necro_spirit", NecroSpirit::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<LaserMinigun>> LASER_MINIGUN = register("laser_minigun", LaserMinigun::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<LightningAura>> LIGHTNING_AURA = register("lightning_aura", LightningAura::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Barnacle>> BARNACLE = register("barnacle", Barnacle::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<EyeLaserTurret>> EYE_LASER_TURRET = register("eye_laser_turret", EyeLaserTurret::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Hydra>> HYDRA = register("hydra", Hydra::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Inferno>> INFERNO = register("inferno", Inferno::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<SanguineBat>> SANGUINE_BAT = register("sanguine_bat", SanguineBat::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Finch>> FINCH = register("finch", Finch::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<IronGolem>> IRON_GOLEM = register("iron_golem", IronGolem::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Slime>> SLIME = register("slime", Slime::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<SnowFlinx>> SNOW_FLINX = register("snow_flinx", SnowFlinx::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Spider>> SPIDER = register("spider", Spider::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<VampireFrog>> VAMPIRE_FROG = register("vampire_frog", VampireFrog::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<RuinRelic>> RUIN_RELIC = register("ruin_relic", RuinRelic::new);

    // ===================== 射弹类型 =====================

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<DestructionOmen>> DESTRUCTION_OMEN = register("destruction_omen", DestructionOmen::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<MiniStardustCell>> MINI_STARDUST_CELL = register("mini_stardust_cell", MiniStardustCell::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Laser>> LASER = register("laser", Laser::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<ProbeLaser>> PROBE_LASER = register("probe_laser", ProbeLaser::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<DemonFlame>> DEMON_FLAME = register("demon_flame", DemonFlame::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<SharkDragon>> SHARK_DRAGON = register("shark_dragon", SharkDragon::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<ShatteredStellarCore>> SHATTERED_STELLAR_CORE = register("shattered_stellar_core", ShatteredStellarCore::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<MiniChlorophyteCrystal>> MINI_CHLOROPHYTE_CRYSTAL = register("mini_chlorophyte_crystal", MiniChlorophyteCrystal::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<MoonPortalLaser>> MOON_PORTAL_LASER = register("moon_portal_laser", MoonPortalLaser::new);


    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<VoidEaterLaser>> VOID_EATER_LASER = register("void_eater_laser", VoidEaterLaser::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<MiniRainbowCrystal>> MINI_RAINBOW_CRYSTAL = register("mini_rainbow_crystal", MiniRainbowCrystal::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<CrossbowBolt>> CROSSBOW_BOLT = register("crossbow_bolt", CrossbowBolt::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<GodFlame>> GOD_FLAME = register("god_flame", GodFlame::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<BlitzBall>> BLITZ_BALL = register("blitz_ball", BlitzBall::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Rain>> RAIN = register("rain", Rain::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<DestructionBullet>> DESTRUCTION_BULLET = register("destruction_bullet", DestructionBullet::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<HornetStinger>> HORNET_STINGER = register("hornet_stinger", HornetStinger::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<ImpFireball>> IMP_FIREBALL = register("imp_fireball", ImpFireball::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<EyeFireball>> EYE_FIREBALL = register("eye_fireball", EyeFireball::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Corn>> CORN = register("corn", Corn::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<MiniNecroSpirit>> MINI_NECRO_SPIRIT = register("mini_necro_spirit", MiniNecroSpirit::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<ElectricLaser>> ELECTRIC_LASER = register("electric_laser", ElectricLaser::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Meteor>> METEOR = register("meteor", Meteor::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<Petal>> PETAL = register("petal", Petal::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<PoisonSaliva>> POISON_SALIVA = register("poison_saliva", PoisonSaliva::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<FrostBolt>> FROST_BOLT = register("frost_bolt", FrostBolt::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<ExplosionFlameBall>> EXPLOSION_FLAME_BALL = register("explosion_flame_ball", ExplosionFlameBall::new);

    public static final RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<ForbiddenOrb>> FORBIDDEN_ORB = register("forbidden_orb", ForbiddenOrb::new);

    private static <T extends AttachmentEntity> RegistryEntry<AttachmentEntityType<?>, AttachmentEntityType<T>> register(String name, Supplier<T> supplier) {
        return REGISTER.simple(name, ATTACHMENT_ENTITY_TYPES_KEY, () -> new AttachmentEntityType<>(Summoner.rl(name), supplier));
    }

    public static void init() {
    }
}
