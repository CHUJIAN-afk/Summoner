package first.summoner.register;

import first.summoner.Summoner;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.CuriosConstants;

public class SummonerItemTagsRegister {

    public static final TagKey<Item> Curio = register(ResourceLocation.fromNamespaceAndPath(CuriosConstants.MOD_ID, "curio"));
    public static final TagKey<Item> MinionWeapon = register("minion_weapon");

    private static TagKey<Item> register(ResourceLocation location) {
        return TagKey.create(Registries.ITEM, location);
    }

    private static TagKey<Item> register(String path) {
        return TagKey.create(Registries.ITEM, Summoner.rl(path));
    }
}
