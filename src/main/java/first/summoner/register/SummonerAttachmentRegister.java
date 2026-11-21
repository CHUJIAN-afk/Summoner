package first.summoner.register;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import first.summoner.Summoner;
import first.summoner.common.attachment.CurioData;
import first.summoner.common.attachment.GildedWandData;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.HashSet;

public class SummonerAttachmentRegister {

    private static final Registrate REGISTER = Summoner.REGISTRATE;

    public static final RegistryEntry<AttachmentType<?>, AttachmentType<CurioData>> CurioData =
            REGISTER.simple("curio_data", NeoForgeRegistries.Keys.ATTACHMENT_TYPES, () -> AttachmentType.builder(() -> new CurioData(new HashSet<>()))
                    .build());

    public static final RegistryEntry<AttachmentType<?>, AttachmentType<GildedWandData>> GildedWandData =
            REGISTER.simple("gilded_wand_data", NeoForgeRegistries.Keys.ATTACHMENT_TYPES, () -> AttachmentType.builder(GildedWandData::new)
                    .build());

    public static void init() {
    }
}
