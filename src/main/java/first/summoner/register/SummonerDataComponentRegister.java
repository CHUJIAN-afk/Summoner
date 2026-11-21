package first.summoner.register;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import first.summoner.Summoner;
import first.summoner.common.dataComponent.ScabbardContainer;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;

public class SummonerDataComponentRegister {

    private static final Registrate REGISTER = Summoner.REGISTRATE;

    public static final RegistryEntry<DataComponentType<?>, DataComponentType<ScabbardContainer>> SCABBARD =
            REGISTER.simple("scabbard_container", Registries.DATA_COMPONENT_TYPE, () -> DataComponentType.<ScabbardContainer>builder()
                    .persistent(ScabbardContainer.CODEC)
                    .networkSynchronized(ScabbardContainer.STREAM_CODEC)
                    .build());

    public static void init() {
    }
}
