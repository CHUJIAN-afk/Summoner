package first.summoner.register;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import first.summoner.Summoner;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

public class SummonerSoundRegister {

    private static final Registrate REGISTER = Summoner.REGISTRATE;

    public static final RegistryEntry<SoundEvent, SoundEvent> UseTerraprism = create("use_terraprism");
    public static final RegistryEntry<SoundEvent, SoundEvent> UseMinionWeapon = create("use_minion_weapon");
    public static final RegistryEntry<SoundEvent, SoundEvent> UseMoonPortalStaff = create("use_moon_portal_staff");
    public static final RegistryEntry<SoundEvent, SoundEvent> UseBallistaStaff = create("use_ballista_staff");
    public static final RegistryEntry<SoundEvent, SoundEvent> UseCloudStaff = create("use_cloud_staff");
    public static final RegistryEntry<SoundEvent, SoundEvent> UseImpStaff = create("use_imp_staff");

    public static final RegistryEntry<SoundEvent, SoundEvent> BallistaShot = create("ballista_shot");
    public static final RegistryEntry<SoundEvent, SoundEvent> Laser = create("laser");
    public static final RegistryEntry<SoundEvent, SoundEvent> ImpFire = create("imp_fire");

    private static RegistryEntry<SoundEvent, SoundEvent> create(String name) {
        return REGISTER.simple(name, Registries.SOUND_EVENT, () -> SoundEvent.createVariableRangeEvent(Summoner.rl(name)));
    }

    public static void init() {
    }

}
