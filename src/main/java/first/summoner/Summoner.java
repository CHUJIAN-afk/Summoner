package first.summoner;

import com.tterrag.registrate.Registrate;
import first.summoner.dataGenerator.SummonerLootProvider;
import first.summoner.register.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.fml.common.Mod;

@Mod(Summoner.MODID)
public class Summoner {

    public static final String MODID = "summoner";
    public static final Registrate REGISTRATE = Registrate.create(MODID).defaultCreativeTab((ResourceKey<CreativeModeTab>) null);

    public Summoner() {
        // 触发注册类的类加载，保证所有条目在 RegisterEvent 之前完成登记
        SummonerRegistries.init();
        SummonerDataComponentRegister.init();
        SummonerAttachmentRegister.init();
        SummonerAttachmentEntityRegister.init();
        SummonerArmorMaterialRegister.init();
        SummonerEnchantmentRegister.init();
        SummonerMobEffectRegister.init();
        SummonerPotionRegister.init();
        SummonerSoundRegister.init();
        SummonerSummonMarkRegister.init();
        SummonerWeaponRegister.init();
        SummonerArmorRegister.init();
        SummonerCurioRegister.init();
        SummonerCreativeTabRegister.init();
        new SummonerLanguageProvider(MODID).init();
        new SummonerLootProvider(MODID).init();
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path.toLowerCase());
    }
}
