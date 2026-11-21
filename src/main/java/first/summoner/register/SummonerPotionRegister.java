package first.summoner.register;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import first.summoner.Summoner;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class SummonerPotionRegister {

    private static final Registrate REGISTER = Summoner.REGISTRATE;

    public static final RegistryEntry<Potion, Potion> Obsession = REGISTER.simple("obsession", Registries.POTION, () -> new Potion("obsession", new MobEffectInstance(SummonerMobEffectRegister.Obsession, 12000)));
    public static final RegistryEntry<Potion, Potion> LongObsession = REGISTER.simple("long_obsession", Registries.POTION, () -> new Potion("long_obsession", new MobEffectInstance(SummonerMobEffectRegister.Obsession, 24000)));
    public static final RegistryEntry<Potion, Potion> StrongObsession = REGISTER.simple("strong_obsession", Registries.POTION, () -> new Potion("strong_obsession", new MobEffectInstance(SummonerMobEffectRegister.Obsession, 6000, 1)));

    public static void init() {
    }

}
