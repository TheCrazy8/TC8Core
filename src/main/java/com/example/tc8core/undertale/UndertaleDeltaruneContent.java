package com.example.tc8core.undertale;

import com.example.tc8core.TC8Core;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.function.Supplier;

public final class UndertaleDeltaruneContent {
    private UndertaleDeltaruneContent() {}

    public static final DeferredBlock<Block> RUINS_BRICKS =
            registerBlock("ruins_bricks", MapColor.COLOR_PURPLE, 2.0F, 6.0F, 0);
    public static final DeferredBlock<Block> CORE_TILES =
            registerBlock("core_tiles", MapColor.COLOR_RED, 3.0F, 8.0F, 5);
    public static final DeferredBlock<Block> SAVE_POINT_BLOCK =
            registerBlock("save_point_block", MapColor.GOLD, 1.0F, 3.0F, 13);
    public static final DeferredBlock<Block> CASTLE_TOWN_BRICKS =
            registerBlock("castle_town_bricks", MapColor.COLOR_PURPLE, 2.5F, 6.0F, 0);
    public static final DeferredBlock<Block> CARD_CASTLE_BRICKS =
            registerBlock("card_castle_bricks", MapColor.COLOR_BLACK, 2.5F, 6.0F, 0);
    public static final DeferredBlock<Block> CYBER_CITY_TILES =
            registerBlock("cyber_city_tiles", MapColor.COLOR_CYAN, 3.0F, 6.0F, 8);
    public static final DeferredBlock<Block> DARK_FOUNTAIN_BLOCK =
            registerBlock("dark_fountain_block", MapColor.COLOR_BLACK, 4.0F, 12.0F, 10);

    public static final DeferredItem<KnifeItem> AQUA_KNIFE =
            TC8Core.ITEMS.register("aqua_knife", () -> new KnifeItem(
                    Tiers.DIAMOND,
                    new Item.Properties().attributes(
                            KnifeItem.createAttributes(Tiers.DIAMOND, 2.0F, -1.8F))));

    public static final DeferredItem<Item> HUMAN_SOUL = registerItem("human_soul", true);
    public static final DeferredItem<Item> MONSTER_SOUL = registerItem("monster_soul", false);
    public static final DeferredItem<Item> DETERMINATION = registerItem("determination", true);
    public static final DeferredItem<Item> SHADOW_CRYSTAL = registerItem("shadow_crystal", true);
    public static final DeferredItem<Item> PURE_CRYSTAL = registerItem("pure_crystal", true);
    public static final DeferredItem<Item> DARK_DOLLAR = registerItem("dark_dollar", false);
    public static final DeferredItem<Item> EGG = registerItem("mysterious_egg", false);

    public static void bootstrap() {}

    private static DeferredItem<Item> registerItem(String name, boolean fireResistant) {
        return TC8Core.ITEMS.register(name, () -> {
            Item.Properties properties = new Item.Properties();
            if (fireResistant) properties = properties.fireResistant();
            return new Item(properties);
        });
    }

    private static DeferredBlock<Block> registerBlock(
            String name, MapColor color, float hardness, float resistance, int lightLevel) {
        return registerSpecial(name, () -> new Block(BlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(hardness, resistance)
                .sound(SoundType.STONE)
                .lightLevel(state -> lightLevel)));
    }

    private static <T extends Block> DeferredBlock<T> registerSpecial(String name, Supplier<T> factory) {
        DeferredBlock<T> block = TC8Core.BLOCKS.register(name, factory);
        TC8Core.ITEMS.registerSimpleBlockItem(block);
        return block;
    }
}
