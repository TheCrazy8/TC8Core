package com.example.tc8core;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(TC8Core.MODID)
public class TC8Core {
    public static final String MODID = "tc8core";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredItem<Item> CHISELING_TEMPLATE =
            ITEMS.register("chiseling_template", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PRISMATIC_QUARTZ =
            ITEMS.register("prismatic_quartz", () -> new Item(new Item.Properties()));

    public static final DeferredBlock<Block> CHISELED_LAPIS_BLOCK =
            registerBlock("chiseled_lapis_block", MapColor.LAPIS);
    public static final DeferredBlock<Block> CHISELED_EMERALD_BLOCK =
            registerBlock("chiseled_emerald_block", MapColor.EMERALD);
    public static final DeferredBlock<Block> CHISELED_GOLD_BLOCK =
            registerBlock("chiseled_gold_block", MapColor.GOLD);
    public static final DeferredBlock<Block> CHISELED_COPPER_BLOCK =
            registerBlock("chiseled_copper_block", MapColor.COLOR_ORANGE);
    public static final DeferredBlock<Block> CHISELED_IRON_BLOCK =
            registerBlock("chiseled_iron_block", MapColor.METAL);
    public static final DeferredBlock<Block> CHISELED_NETHERITE_BLOCK =
            registerBlock("chiseled_netherite_block", MapColor.COLOR_BLACK);
    public static final DeferredBlock<Block> CHISELED_AMETHYST_BLOCK =
            registerBlock("chiseled_amethyst_block", MapColor.COLOR_PURPLE);
    public static final DeferredBlock<Block> CHISELED_DIAMOND_BLOCK =
            registerBlock("chiseled_diamond_block", MapColor.DIAMOND);
    public static final DeferredBlock<Block> CHISELED_ZINC_BLOCK =
            registerBlock("chiseled_zinc_block", MapColor.METAL);
    public static final DeferredBlock<Block> CHISELED_BRASS_BLOCK =
            registerBlock("chiseled_brass_block", MapColor.GOLD);
    public static final DeferredBlock<Block> CHISELED_ANDESITE_ALLOY_BLOCK =
            registerBlock("chiseled_andesite_alloy_block", MapColor.METAL);
    public static final DeferredBlock<Block> CHISELED_JADE_BLOCK =
            registerBlock("chiseled_jade_block", MapColor.EMERALD);
    public static final DeferredBlock<Block> ORE_MOSAIC_BLOCK =
            registerBlock("ore_mosaic_block", MapColor.METAL);
    public static final DeferredBlock<Block> CHISELED_PALLADIUM_BLOCK =
            registerBlock("chiseled_palladium_block", MapColor.METAL);
    public static final DeferredBlock<Block> CHISELED_LUMINERE_BLOCK =
            registerBlock("chiseled_luminere_block", MapColor.GOLD);

    public static final DeferredBlock<Block> PRISMATIC_QUARTZ_BRICKS =
            registerBlock("prismatic_quartz_bricks", MapColor.QUARTZ);
    public static final DeferredBlock<Block> CHISELED_PRISMATIC_QUARTZ =
            registerBlock("chiseled_prismatic_quartz", MapColor.QUARTZ);
    public static final DeferredBlock<RotatedPillarBlock> PRISMATIC_QUARTZ_PILLAR =
            registerPillarBlock("prismatic_quartz_pillar", MapColor.QUARTZ);

    public static final DeferredBlock<MagmaBlock> MAGMA_CREAM_BLOCK =
            BLOCKS.register("magma_cream_block", () ->
                    new MagmaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MAGMA_BLOCK)));
    public static final DeferredItem<BlockItem> MAGMA_CREAM_BLOCK_ITEM =
            ITEMS.registerSimpleBlockItem(MAGMA_CREAM_BLOCK);

    public static final DeferredBlock<Block> CHISELED_REDSTONE_BLOCK =
            BLOCKS.register("chiseled_redstone_block", () ->
                    new PoweredChiseledBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_RED)
                            .requiresCorrectToolForDrops()
                            .strength(5.0F, 6.0F)));
    public static final DeferredItem<BlockItem> CHISELED_REDSTONE_BLOCK_ITEM =
            ITEMS.registerSimpleBlockItem(CHISELED_REDSTONE_BLOCK);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TC8_CORE_TAB =
            CREATIVE_MODE_TABS.register("tc8core", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.tc8core"))
                    .icon(() -> CHISELING_TEMPLATE.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(CHISELING_TEMPLATE.get());
                        output.accept(PRISMATIC_QUARTZ.get());
                        output.accept(PRISMATIC_QUARTZ_BRICKS.get());
                        output.accept(CHISELED_PRISMATIC_QUARTZ.get());
                        output.accept(PRISMATIC_QUARTZ_PILLAR.get());
                        output.accept(MAGMA_CREAM_BLOCK.get());
                        output.accept(CHISELED_LAPIS_BLOCK.get());
                        output.accept(CHISELED_EMERALD_BLOCK.get());
                        output.accept(CHISELED_REDSTONE_BLOCK.get());
                        output.accept(CHISELED_GOLD_BLOCK.get());
                        output.accept(CHISELED_COPPER_BLOCK.get());
                        output.accept(CHISELED_IRON_BLOCK.get());
                        output.accept(CHISELED_NETHERITE_BLOCK.get());
                        output.accept(CHISELED_AMETHYST_BLOCK.get());
                        output.accept(CHISELED_DIAMOND_BLOCK.get());
                        output.accept(CHISELED_ZINC_BLOCK.get());
                        output.accept(CHISELED_BRASS_BLOCK.get());
                        output.accept(CHISELED_ANDESITE_ALLOY_BLOCK.get());
                        output.accept(CHISELED_JADE_BLOCK.get());
                        output.accept(CHISELED_PALLADIUM_BLOCK.get());
                        output.accept(CHISELED_LUMINERE_BLOCK.get());
                        output.accept(ORE_MOSAIC_BLOCK.get());
                    }).build());

    public TC8Core(IEventBus modEventBus, ModContainer modContainer) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }

    private static DeferredBlock<Block> registerBlock(String name, MapColor color) {
        DeferredBlock<Block> block = BLOCKS.register(name, () ->
                new Block(BlockBehaviour.Properties.of()
                        .mapColor(color)
                        .requiresCorrectToolForDrops()
                        .strength(5.0F, 6.0F)));
        ITEMS.registerSimpleBlockItem(block);
        return block;
    }

    private static DeferredBlock<RotatedPillarBlock> registerPillarBlock(String name, MapColor color) {
        DeferredBlock<RotatedPillarBlock> block = BLOCKS.register(name, () ->
                new RotatedPillarBlock(BlockBehaviour.Properties.of()
                        .mapColor(color)
                        .requiresCorrectToolForDrops()
                        .strength(5.0F, 6.0F)));
        ITEMS.registerSimpleBlockItem(block);
        return block;
    }

    public static class PoweredChiseledBlock extends Block {
        public PoweredChiseledBlock(Properties properties) {
            super(properties);
        }

        @Override
        public boolean isSignalSource(BlockState state) {
            return true;
        }

        @Override
        public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 15;
        }
    }
}
