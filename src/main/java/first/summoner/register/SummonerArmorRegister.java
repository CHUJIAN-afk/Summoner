package first.summoner.register;

import com.tterrag.registrate.util.entry.ItemEntry;
import first.lyra.common.builder.AttributeArmorItemBuilder;
import first.lyra.register.LyraAttributeRegister;
import first.summoner.Summoner;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.common.Tags;

public class SummonerArmorRegister {

    /**
     * 小雪怪外套
     */
    public static final ItemEntry<ArmorItem> FlinxFurCoat =
            Summoner.REGISTRATE.item("flinx_fur_coat", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.Flinx, ArmorItem.Type.CHESTPLATE)
                            .modifier(Attributes.ARMOR, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .build())
                    .defaultModel()
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.CHEST_ARMOR, ItemTags.CHEST_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 蜜蜂头饰 - +4% 召唤伤害，+1 仆从栏
     */
    public static final ItemEntry<ArmorItem> BeeHeadgear =
            Summoner.REGISTRATE.item("bee_headgear", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.BeeArmorMaterial, ArmorItem.Type.HELMET)
                            .modifier(Attributes.ARMOR, 2, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.04, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.HEAD_ARMOR, ItemTags.HEAD_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 蜜蜂胸甲 - +4% 召唤伤害，+1 仆从栏
     */
    public static final ItemEntry<ArmorItem> BeeChestplate =
            Summoner.REGISTRATE.item("bee_chestplate", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.BeeArmorMaterial, ArmorItem.Type.CHESTPLATE)
                            .modifier(Attributes.ARMOR, 2, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.04, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.CHEST_ARMOR, ItemTags.CHEST_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 蜜蜂护胫 - +5% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> BeeLeggings =
            Summoner.REGISTRATE.item("bee_leggings", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.BeeArmorMaterial, ArmorItem.Type.LEGGINGS)
                            .modifier(Attributes.ARMOR, 2, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.LEG_ARMOR, ItemTags.LEG_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 蜜蜂靴
     */
    public static final ItemEntry<ArmorItem> BeeBoots =
            Summoner.REGISTRATE.item("bee_boots", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.BeeArmorMaterial, ArmorItem.Type.BOOTS)
                            .modifier(Attributes.ARMOR, 1, AttributeModifier.Operation.ADD_VALUE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.FOOT_ARMOR, ItemTags.FOOT_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 黑曜石头盔 - +8% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> ObsidianHelmet =
            Summoner.REGISTRATE.item("obsidian_helmet", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ObsidianArmorMaterial, ArmorItem.Type.HELMET)
                            .modifier(Attributes.ARMOR, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.08, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.HEAD_ARMOR, ItemTags.HEAD_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 黑曜石胸甲 - +1 仆从栏
     */
    public static final ItemEntry<ArmorItem> ObsidianChestplate =
            Summoner.REGISTRATE.item("obsidian_chestplate", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ObsidianArmorMaterial, ArmorItem.Type.CHESTPLATE)
                            .modifier(Attributes.ARMOR, 2, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.CHEST_ARMOR, ItemTags.CHEST_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 黑曜石护腿 - +8% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> ObsidianLeggings =
            Summoner.REGISTRATE.item("obsidian_leggings", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ObsidianArmorMaterial, ArmorItem.Type.LEGGINGS)
                            .modifier(Attributes.ARMOR, 2, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.08, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.LEG_ARMOR, ItemTags.LEG_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 黑曜石靴子 - +8% 移动速度
     */
    public static final ItemEntry<ArmorItem> ObsidianBoots =
            Summoner.REGISTRATE.item("obsidian_boots", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ObsidianArmorMaterial, ArmorItem.Type.BOOTS)
                            .modifier(Attributes.ARMOR, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(Attributes.MOVEMENT_SPEED, 0.08, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.FOOT_ARMOR, ItemTags.FOOT_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 蜘蛛面具 - +5% 召唤伤害，+1 仆从栏
     */
    public static final ItemEntry<ArmorItem> SpiderMask =
            Summoner.REGISTRATE.item("spider_mask", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.SpiderArmorMaterial, ArmorItem.Type.HELMET)
                            .modifier(Attributes.ARMOR, 2, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.HEAD_ARMOR, ItemTags.HEAD_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 蜘蛛胸甲 - +5% 召唤伤害，+1 仆从栏
     */
    public static final ItemEntry<ArmorItem> SpiderChestplate =
            Summoner.REGISTRATE.item("spider_chestplate", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.SpiderArmorMaterial, ArmorItem.Type.CHESTPLATE)
                            .modifier(Attributes.ARMOR, 3, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.CHEST_ARMOR, ItemTags.CHEST_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 蜘蛛护胫 - +6% 召唤伤害，+1 仆从栏
     */
    public static final ItemEntry<ArmorItem> SpiderLeggings =
            Summoner.REGISTRATE.item("spider_leggings", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.SpiderArmorMaterial, ArmorItem.Type.LEGGINGS)
                            .modifier(Attributes.ARMOR, 3, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.LEG_ARMOR, ItemTags.LEG_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 蜘蛛靴
     */
    public static final ItemEntry<ArmorItem> SpiderBoots =
            Summoner.REGISTRATE.item("spider_boots", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.SpiderArmorMaterial, ArmorItem.Type.BOOTS)
                            .modifier(Attributes.ARMOR, 2, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.FOOT_ARMOR, ItemTags.FOOT_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 禁戒面具 - +15% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> ForbiddenMask =
            Summoner.REGISTRATE.item("forbidden_mask", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ForbiddenArmorMaterial, ArmorItem.Type.HELMET)
                            .modifier(Attributes.ARMOR, 2, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.HEAD_ARMOR, ItemTags.HEAD_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 禁戒长袍 - +10%召唤伤害，+1 仆从栏
     */
    public static final ItemEntry<ArmorItem> ForbiddenRobe =
            Summoner.REGISTRATE.item("forbidden_robe", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ForbiddenArmorMaterial, ArmorItem.Type.CHESTPLATE)
                            .modifier(Attributes.ARMOR, 4, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.CHEST_ARMOR, ItemTags.CHEST_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 禁戒裤 - +1 仆从栏
     */
    public static final ItemEntry<ArmorItem> ForbiddenLeggings =
            Summoner.REGISTRATE.item("forbidden_leggings", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ForbiddenArmorMaterial, ArmorItem.Type.LEGGINGS)
                            .modifier(Attributes.ARMOR, 3, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.LEG_ARMOR, ItemTags.LEG_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 禁戒靴
     */
    public static final ItemEntry<ArmorItem> ForbiddenBoots =
            Summoner.REGISTRATE.item("forbidden_boots", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ForbiddenArmorMaterial, ArmorItem.Type.BOOTS)
                            .modifier(Attributes.ARMOR, 2, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.FOOT_ARMOR, ItemTags.FOOT_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 神圣头盔
     */
    public static final ItemEntry<ArmorItem> HallowedHelmet =
            Summoner.REGISTRATE.item("hallowed_helmet", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.HallowedArmorMaterial, ArmorItem.Type.HELMET)
                            .modifier(Attributes.ARMOR, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.HEAD_ARMOR, ItemTags.HEAD_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 神圣胸甲 - +14% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> HallowedChestplate =
            Summoner.REGISTRATE.item("hallowed_chestplate", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.HallowedArmorMaterial, ArmorItem.Type.CHESTPLATE)
                            .modifier(Attributes.ARMOR, 5, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.14, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.CHEST_ARMOR, ItemTags.CHEST_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 神圣护腿 - +7% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> HallowedLeggings =
            Summoner.REGISTRATE.item("hallowed_leggings", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.HallowedArmorMaterial, ArmorItem.Type.LEGGINGS)
                            .modifier(Attributes.ARMOR, 4, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.07, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.LEG_ARMOR, ItemTags.LEG_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 神圣靴子 - +8% 移动速度
     */
    public static final ItemEntry<ArmorItem> HallowedBoots =
            Summoner.REGISTRATE.item("hallowed_boots", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.HallowedArmorMaterial, ArmorItem.Type.BOOTS)
                            .modifier(Attributes.ARMOR, 3, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(Attributes.MOVEMENT_SPEED, 0.08, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.FOOT_ARMOR, ItemTags.FOOT_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 叶绿面具 - +1 召唤栏，+10% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> ChlorophyteHelmet =
            Summoner.REGISTRATE.item("chlorophyte_helmet", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ChlorophyteArmorMaterial, ArmorItem.Type.HELMET)
                            .modifier(Attributes.ARMOR, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.16, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.HEAD_ARMOR, ItemTags.HEAD_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 叶绿板甲 - +6% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> ChlorophyteChestplate =
            Summoner.REGISTRATE.item("chlorophyte_chestplate", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ChlorophyteArmorMaterial, ArmorItem.Type.CHESTPLATE)
                            .modifier(Attributes.ARMOR, 6, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.19, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.CHEST_ARMOR, ItemTags.CHEST_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 叶绿护胫 - +4% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> ChlorophyteLeggings =
            Summoner.REGISTRATE.item("chlorophyte_leggings", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ChlorophyteArmorMaterial, ArmorItem.Type.LEGGINGS)
                            .modifier(Attributes.ARMOR, 5, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.16, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.LEG_ARMOR, ItemTags.LEG_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 叶绿战靴 - +2% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> ChlorophyteBoots =
            Summoner.REGISTRATE.item("chlorophyte_boots", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ChlorophyteArmorMaterial, ArmorItem.Type.BOOTS)
                            .modifier(Attributes.ARMOR, 3, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(Attributes.MOVEMENT_SPEED, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.UNCOMMON))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.FOOT_ARMOR, ItemTags.FOOT_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 英灵殿骑士头盔 - +2 哨兵栏，+10% 召唤伤害，+10% 原版伤害
     */
    public static final ItemEntry<ArmorItem> ValhallaKnightHelmet =
            Summoner.REGISTRATE.item("valhalla_knight_helmet", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ValhallaKnightArmorMaterial, ArmorItem.Type.HELMET)
                            .modifier(Attributes.ARMOR, 7, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SentryMaxCount, 2, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .modifier(Attributes.ATTACK_DAMAGE, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.HEAD_ARMOR, ItemTags.HEAD_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 英灵殿骑士胸甲 - +30% 召唤伤害，+0.4 生命再生
     */
    public static final ItemEntry<ArmorItem> ValhallaKnightChestplate =
            Summoner.REGISTRATE.item("valhalla_knight_chestplate", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ValhallaKnightArmorMaterial, ArmorItem.Type.CHESTPLATE)
                            .modifier(Attributes.ARMOR, 8, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.30, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .modifier(LyraAttributeRegister.HealthRegen, 0.4, AttributeModifier.Operation.ADD_VALUE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.CHEST_ARMOR, ItemTags.CHEST_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 英灵殿骑士护腿 - +20% 召唤伤害，+20% 原版伤害
     */
    public static final ItemEntry<ArmorItem> ValhallaKnightLeggings =
            Summoner.REGISTRATE.item("valhalla_knight_leggings", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ValhallaKnightArmorMaterial, ArmorItem.Type.LEGGINGS)
                            .modifier(Attributes.ARMOR, 6, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .modifier(Attributes.ATTACK_DAMAGE, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.LEG_ARMOR, ItemTags.LEG_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 英灵殿骑士战靴 - +20% 移动速度
     */
    public static final ItemEntry<ArmorItem> ValhallaKnightBoots =
            Summoner.REGISTRATE.item("valhalla_knight_boots", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.ValhallaKnightArmorMaterial, ArmorItem.Type.BOOTS)
                            .modifier(Attributes.ARMOR, 4, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(Attributes.MOVEMENT_SPEED, 0.20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.FOOT_ARMOR, ItemTags.FOOT_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 提基面具 - +1 召唤栏，+10% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> TikiHelmet =
            Summoner.REGISTRATE.item("tiki_helmet", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.TikiArmorMaterial, ArmorItem.Type.HELMET)
                            .modifier(Attributes.ARMOR, 2, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.HEAD_ARMOR, ItemTags.HEAD_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 提基胸甲 - +10% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> TikiChestplate =
            Summoner.REGISTRATE.item("tiki_chestplate", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.TikiArmorMaterial, ArmorItem.Type.CHESTPLATE)
                            .modifier(Attributes.ARMOR, 6, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.CHEST_ARMOR, ItemTags.CHEST_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 提基护腿 - +7% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> TikiLeggings =
            Summoner.REGISTRATE.item("tiki_leggings", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.TikiArmorMaterial, ArmorItem.Type.LEGGINGS)
                            .modifier(Attributes.ARMOR, 4, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.LEG_ARMOR, ItemTags.LEG_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 提基战靴 - +3% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> TikiBoots =
            Summoner.REGISTRATE.item("tiki_boots", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.TikiArmorMaterial, ArmorItem.Type.BOOTS)
                            .modifier(Attributes.ARMOR, 3, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.FOOT_ARMOR, ItemTags.FOOT_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 阴森头盔 - +11% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> SpookyHelmet =
            Summoner.REGISTRATE.item("spooky_helmet", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.SpookyArmorMaterial, ArmorItem.Type.HELMET)
                            .modifier(Attributes.ARMOR, 3, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.11, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.HEAD_ARMOR, ItemTags.HEAD_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 阴森胸甲 - +11% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> SpookyChestplate =
            Summoner.REGISTRATE.item("spooky_chestplate", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.SpookyArmorMaterial, ArmorItem.Type.CHESTPLATE)
                            .modifier(Attributes.ARMOR, 4, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.11, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.CHEST_ARMOR, ItemTags.CHEST_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 阴森护腿 - +8% 召唤伤害，+1 召唤栏
     */
    public static final ItemEntry<ArmorItem> SpookyLeggings =
            Summoner.REGISTRATE.item("spooky_leggings", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.SpookyArmorMaterial, ArmorItem.Type.LEGGINGS)
                            .modifier(Attributes.ARMOR, 4, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.11, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.LEG_ARMOR, ItemTags.LEG_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 阴森战靴 - +4% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> SpookyBoots =
            Summoner.REGISTRATE.item("spooky_boots", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.SpookyArmorMaterial, ArmorItem.Type.BOOTS)
                            .modifier(Attributes.ARMOR, 2, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(Attributes.MOVEMENT_SPEED, 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.RARE))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.FOOT_ARMOR, ItemTags.FOOT_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 星尘头盔 - +1 召唤栏，+16% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> StardustHelmet =
            Summoner.REGISTRATE.item("stardust_helmet", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.StardustArmorMaterial, ArmorItem.Type.HELMET)
                            .modifier(Attributes.ARMOR, 4, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SentryMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.22, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .modifier(LyraAttributeRegister.SummonSearchRange, 0.08, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.EPIC))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.HEAD_ARMOR, ItemTags.HEAD_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 星尘板甲 - +22% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> StardustChestplate =
            Summoner.REGISTRATE.item("stardust_chestplate", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.StardustArmorMaterial, ArmorItem.Type.CHESTPLATE)
                            .modifier(Attributes.ARMOR, 6, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 2, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.22, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .modifier(LyraAttributeRegister.SummonSearchRange, 0.08, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.EPIC))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.CHEST_ARMOR, ItemTags.CHEST_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 星尘护腿 - +15% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> StardustLeggings =
            Summoner.REGISTRATE.item("stardust_leggings", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.StardustArmorMaterial, ArmorItem.Type.LEGGINGS)
                            .modifier(Attributes.ARMOR, 4, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonDamage, 0.22, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .modifier(LyraAttributeRegister.SummonSearchRange, 0.08, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.EPIC))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.LEG_ARMOR, ItemTags.LEG_ARMOR_ENCHANTABLE)
                    .register();
    /**
     * 星尘战靴 - +7% 召唤伤害
     */
    public static final ItemEntry<ArmorItem> StardustBoots =
            Summoner.REGISTRATE.item("stardust_boots", itemProperties -> AttributeArmorItemBuilder.builder(SummonerArmorMaterialRegister.StardustArmorMaterial, ArmorItem.Type.BOOTS)
                            .modifier(Attributes.ARMOR, 3, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.MinionMaxCount, 1, AttributeModifier.Operation.ADD_VALUE)
                            .modifier(LyraAttributeRegister.SummonSearchRange, 0.08, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .properties(p -> p.rarity(Rarity.EPIC))
                            .build())
                    .tag(Tags.Items.ENCHANTABLES)
                    .tag(Tags.Items.ARMORS)
                    .tag(ItemTags.ARMOR_ENCHANTABLE)
                    .defaultModel()
                    .tag(ItemTags.FOOT_ARMOR, ItemTags.FOOT_ARMOR_ENCHANTABLE)
                    .register();

    public static void init() {
    }
}
