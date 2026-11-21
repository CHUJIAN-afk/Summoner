package first.summoner.register;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import first.summoner.Summoner;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class SummonerCreativeTabRegister {

    private static final Registrate REGISTER = Summoner.REGISTRATE;

    private static final RegistryEntry<CreativeModeTab, CreativeModeTab> Tab = REGISTER.simple("tab", Registries.CREATIVE_MODE_TAB, () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("modid.summoner"))
                    .icon(() -> SummonerWeaponRegister.TerraPrism.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(SummonerWeaponRegister.GildedWand.get());
                        var enchantments = parameters.holders().lookupOrThrow(Registries.ENCHANTMENT);
                        for (var enchantment : List.of(
                                SummonerEnchantmentRegister.FoodChain,
                                SummonerEnchantmentRegister.JungleHunter,
                                SummonerEnchantmentRegister.DeathMagister,
                                SummonerEnchantmentRegister.SoulFlail,
                                SummonerEnchantmentRegister.Starfall,
                                SummonerEnchantmentRegister.Bramble,
                                SummonerEnchantmentRegister.Vein,
                                SummonerEnchantmentRegister.Spine,
                                SummonerEnchantmentRegister.Firecracker,
                                SummonerEnchantmentRegister.FrostSnow,
                                SummonerEnchantmentRegister.DivineJudgment,
                                SummonerEnchantmentRegister.RoughDisplayOfFlowers,
                                SummonerEnchantmentRegister.DominanceOmen,
                                SummonerEnchantmentRegister.DarkHarvest,
                                SummonerEnchantmentRegister.Constellation,
                                SummonerEnchantmentRegister.CurrentSurge,
                                SummonerEnchantmentRegister.MorningStar,
                                SummonerEnchantmentRegister.Kaleidoscope,
                                SummonerEnchantmentRegister.Requiem,
                                SummonerEnchantmentRegister.Galaxy,
                                SummonerEnchantmentRegister.SilentEnd,
                                SummonerEnchantmentRegister.MoonBite,
                                SummonerEnchantmentRegister.Angel,
                                SummonerEnchantmentRegister.GodSlayer)) {
                            ItemStack book = Items.ENCHANTED_BOOK.getDefaultInstance();
                            book.enchant(enchantments.getOrThrow(enchantment.getKey()), 1);
                            output.accept(book);
                        }
                        output.accept(SummonerWeaponRegister.HornetStaff.get());
                        output.accept(SummonerWeaponRegister.ImpStaff.get());
                        output.accept(SummonerWeaponRegister.SurveyDroneRemote.get());
                        output.accept(SummonerWeaponRegister.FairyBell.get());
                        output.accept(SummonerWeaponRegister.BladeStaff.get());
                        output.accept(SummonerWeaponRegister.RainCloudStaff.get());
                        output.accept(SummonerWeaponRegister.OpticStaff.get());
                        output.accept(SummonerWeaponRegister.DeadlySphereStaff.get());
                        output.accept(SummonerWeaponRegister.XenoStaff.get());
                        output.accept(SummonerWeaponRegister.PulseTurretRemote.get());
                        output.accept(SummonerWeaponRegister.TempestStaff.get());
                        output.accept(SummonerWeaponRegister.TerraPrism.get());
                        output.accept(SummonerWeaponRegister.BallistaRod.get());
                        output.accept(SummonerWeaponRegister.BallistaCane.get());
                        output.accept(SummonerWeaponRegister.BallistaStaff.get());
                        output.accept(SummonerWeaponRegister.LightningAuraRod.get());
                        output.accept(SummonerWeaponRegister.LightningAuraCane.get());
                        output.accept(SummonerWeaponRegister.LightningAuraStaff.get());
                        output.accept(SummonerWeaponRegister.EtherealStellarCoreStaff.get());
                        output.accept(SummonerWeaponRegister.StardustCellStaff.get());
                        output.accept(SummonerWeaponRegister.StardustDragonStaff.get());
                        output.accept(SummonerWeaponRegister.MiniEarthShaker.get());
                        output.accept(SummonerWeaponRegister.MoonPortalStaff.get());
                        output.accept(SummonerWeaponRegister.RainbowCrystalStaff.get());
                        output.accept(SummonerWeaponRegister.InfiniteScabbard.get());
                        output.accept(SummonerWeaponRegister.VoidEaterMarionette.get());
                        output.accept(SummonerWeaponRegister.ThundercloudBonsai.get());
                        output.accept(SummonerWeaponRegister.CornCannon.get());
                        output.accept(SummonerWeaponRegister.NecroSpiritStaff.get());
                        output.accept(SummonerWeaponRegister.LaserMinigunStaff.get());
                        output.accept(SummonerWeaponRegister.BarnacleStaff.get());
                        output.accept(SummonerWeaponRegister.EyeLaserTurretStaff.get());
                        output.accept(SummonerWeaponRegister.HydraStaff.get());
                        output.accept(SummonerWeaponRegister.InfernoRod.get());
                        output.accept(SummonerWeaponRegister.InfernoCane.get());
                        output.accept(SummonerWeaponRegister.InfernoStaff.get());
                        output.accept(SummonerWeaponRegister.SanguineStaff.get());
                        output.accept(SummonerWeaponRegister.RuinStaff.get());
                        output.accept(SummonerWeaponRegister.FinchStaff.get());
                        output.accept(SummonerWeaponRegister.IronGolemStaff.get());
                        output.accept(SummonerWeaponRegister.SlimeStaff.get());
                        output.accept(SummonerWeaponRegister.SnowFlinxStaff.get());
                        output.accept(SummonerWeaponRegister.VampireFrogStaff.get());
                        output.accept(SummonerWeaponRegister.SpiderStaff.get());
                        output.accept(SummonerArmorRegister.FlinxFurCoat.get());
                        output.accept(SummonerArmorRegister.BeeHeadgear.get());
                        output.accept(SummonerArmorRegister.BeeChestplate.get());
                        output.accept(SummonerArmorRegister.BeeLeggings.get());
                        output.accept(SummonerArmorRegister.BeeBoots.get());
                        output.accept(SummonerArmorRegister.ObsidianHelmet.get());
                        output.accept(SummonerArmorRegister.ObsidianChestplate.get());
                        output.accept(SummonerArmorRegister.ObsidianLeggings.get());
                        output.accept(SummonerArmorRegister.ObsidianBoots.get());
                        output.accept(SummonerArmorRegister.SpiderMask.get());
                        output.accept(SummonerArmorRegister.SpiderChestplate.get());
                        output.accept(SummonerArmorRegister.SpiderLeggings.get());
                        output.accept(SummonerArmorRegister.SpiderBoots.get());
                        output.accept(SummonerArmorRegister.ForbiddenMask.get());
                        output.accept(SummonerArmorRegister.ForbiddenRobe.get());
                        output.accept(SummonerArmorRegister.ForbiddenLeggings.get());
                        output.accept(SummonerArmorRegister.ForbiddenBoots.get());
                        output.accept(SummonerArmorRegister.HallowedHelmet.get());
                        output.accept(SummonerArmorRegister.HallowedChestplate.get());
                        output.accept(SummonerArmorRegister.HallowedLeggings.get());
                        output.accept(SummonerArmorRegister.HallowedBoots.get());
                        output.accept(SummonerArmorRegister.ChlorophyteHelmet.get());
                        output.accept(SummonerArmorRegister.ChlorophyteChestplate.get());
                        output.accept(SummonerArmorRegister.ChlorophyteLeggings.get());
                        output.accept(SummonerArmorRegister.ChlorophyteBoots.get());
                        output.accept(SummonerArmorRegister.ValhallaKnightHelmet.get());
                        output.accept(SummonerArmorRegister.ValhallaKnightChestplate.get());
                        output.accept(SummonerArmorRegister.ValhallaKnightLeggings.get());
                        output.accept(SummonerArmorRegister.ValhallaKnightBoots.get());
                        output.accept(SummonerArmorRegister.TikiHelmet.get());
                        output.accept(SummonerArmorRegister.TikiChestplate.get());
                        output.accept(SummonerArmorRegister.TikiLeggings.get());
                        output.accept(SummonerArmorRegister.TikiBoots.get());
                        output.accept(SummonerArmorRegister.SpookyHelmet.get());
                        output.accept(SummonerArmorRegister.SpookyChestplate.get());
                        output.accept(SummonerArmorRegister.SpookyLeggings.get());
                        output.accept(SummonerArmorRegister.SpookyBoots.get());
                        output.accept(SummonerArmorRegister.StardustHelmet.get());
                        output.accept(SummonerArmorRegister.StardustChestplate.get());
                        output.accept(SummonerArmorRegister.StardustLeggings.get());
                        output.accept(SummonerArmorRegister.StardustBoots.get());
                        output.accept(SummonerCurioRegister.HivePack.get());
                        output.accept(SummonerCurioRegister.PygmyNecklace.get());
                        output.accept(SummonerCurioRegister.HerculesBeetle.get());
                        output.accept(SummonerCurioRegister.ApprenticesScarf.get());
                        output.accept(SummonerCurioRegister.HuntressesBuckler.get());
                        output.accept(SummonerCurioRegister.MonksBelt.get());
                        output.accept(SummonerCurioRegister.SquiresShield.get());
                        output.accept(SummonerCurioRegister.SummonerEmblem.get());
                        output.accept(SummonerCurioRegister.PrimordialShadowflame.get());
                        output.accept(SummonerCurioRegister.NecromanticScroll.get());
                        output.accept(SummonerCurioRegister.ThreatAnalyzer.get());
                        output.accept(SummonerCurioRegister.PapyrusScarab.get());
                        output.accept(SummonerCurioRegister.SoulRelief.get());
                        output.accept(SummonerCurioRegister.HallowedRune.get());
                        output.accept(SummonerCurioRegister.PhantasmalRelic.get());
                        output.accept(SummonerCurioRegister.HuntSoulEmblem.get());
                        output.accept(SummonerCurioRegister.EclipseRing.get());
                        output.accept(SummonerCurioRegister.StardustFragment.get());
                    })
                    .build());

    public static void init() {
    }
}
