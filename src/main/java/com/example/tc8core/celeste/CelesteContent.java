package com.example.tc8core.celeste;

import com.example.tc8core.TC8Core;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public final class CelesteContent {
    private CelesteContent() {}

    public static final DeferredBlock<Block> CELESTIAL_CRYSTAL_BLOCK =
            registerBlock("celestial_crystal_block", MapColor.COLOR_CYAN, 3.0F, 6.0F, 8);
    public static final DeferredBlock<Block> DREAM_BLOCK =
            registerSpecial("dream_block", () -> new DreamBlock(properties(MapColor.COLOR_PURPLE, 2.0F, 6.0F).lightLevel(s -> 7)));
    public static final DeferredBlock<Block> FORSAKEN_TEMPLE_BRICKS =
            registerBlock("forsaken_temple_bricks", MapColor.COLOR_BROWN, 2.5F, 6.0F, 0);
    public static final DeferredBlock<Block> GOLDEN_RIDGE_STONE =
            registerBlock("golden_ridge_stone", MapColor.COLOR_ORANGE, 2.5F, 6.0F, 0);
    public static final DeferredBlock<Block> MIRROR_TEMPLE_TILES =
            registerBlock("mirror_temple_tiles", MapColor.COLOR_BLUE, 3.0F, 6.0F, 3);
    public static final DeferredBlock<Block> SUMMIT_MOSAIC =
            registerBlock("summit_mosaic", MapColor.COLOR_LIGHT_BLUE, 3.0F, 6.0F, 4);
    public static final DeferredBlock<Block> CASSETTE_BLOCK =
            registerBlock("cassette_block", MapColor.COLOR_MAGENTA, 2.0F, 4.0F, 2);

    public static final DeferredBlock<Block> DASH_REFILL_CRYSTAL =
            registerSpecial("dash_refill_crystal", () -> new DashRefillCrystalBlock(
                    properties(MapColor.COLOR_CYAN, 1.0F, 3.0F).lightLevel(s -> 12).noOcclusion()));
    public static final DeferredBlock<Block> TRAFFIC_BLOCK =
            registerSpecial("traffic_block", () -> new LaunchBlock(
                    properties(MapColor.COLOR_RED, 3.0F, 6.0F), 1.7D, 0.25D));
    public static final DeferredBlock<Block> SPRING_BLOCK =
            registerSpecial("spring_block", () -> new SpringBlock(
                    properties(MapColor.COLOR_RED, 1.5F, 3.0F).noOcclusion()));
    public static final DeferredBlock<Block> BOOSTER_BLOCK =
            registerSpecial("booster_block", () -> new BoosterBlock(
                    properties(MapColor.COLOR_GREEN, 1.5F, 3.0F).lightLevel(s -> 8).noOcclusion()));
    public static final DeferredBlock<Block> CRUMBLE_BLOCK =
            registerSpecial("crumble_block", () -> new CrumbleBlock(
                    properties(MapColor.COLOR_LIGHT_GRAY, 1.0F, 2.0F)));
    public static final DeferredBlock<Block> KEVIN_BLOCK =
            registerSpecial("kevin_block", () -> new LaunchBlock(
                    properties(MapColor.COLOR_PURPLE, 5.0F, 10.0F), 2.2D, 0.5D));

    public static final DeferredBlock<Block> THEO_CRYSTAL_STATUE =
            registerBlock("theo_crystal_statue", MapColor.COLOR_CYAN, 2.0F, 5.0F, 6);
    public static final DeferredBlock<Block> MINI_MOUNTAIN_MODEL =
            registerBlock("mini_mountain_model", MapColor.STONE, 2.0F, 5.0F, 0);
    public static final DeferredBlock<Block> CELESTE_SIGN =
            registerBlock("celeste_sign", MapColor.WOOD, 1.0F, 2.0F, 0);
    public static final DeferredBlock<Block> CELESTE_BANNER =
            registerBlock("celeste_banner", MapColor.COLOR_PURPLE, 1.0F, 2.0F, 0);

    public static final DeferredItem<Item> STRAWBERRY =
            TC8Core.ITEMS.register("strawberry", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_STRAWBERRY =
            TC8Core.ITEMS.register("golden_strawberry", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> CRYSTAL_HEART =
            TC8Core.ITEMS.register("crystal_heart", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> CASSETTE =
            TC8Core.ITEMS.register("cassette", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FEATHER =
            TC8Core.ITEMS.register("celeste_feather", () -> new Item(new Item.Properties()));

    public static void bootstrap() {
        // Forces static initialization before TC8Core's DeferredRegisters are attached to the event bus.
    }

    private static BlockBehaviour.Properties properties(MapColor color, float hardness, float resistance) {
        return BlockBehaviour.Properties.of()
                .mapColor(color)
                .requiresCorrectToolForDrops()
                .strength(hardness, resistance);
    }

    private static DeferredBlock<Block> registerBlock(
            String name, MapColor color, float hardness, float resistance, int light) {
        return registerSpecial(name, () -> new Block(
                properties(color, hardness, resistance).lightLevel(state -> light)));
    }

    private static <T extends Block> DeferredBlock<T> registerSpecial(String name, Supplier<T> factory) {
        DeferredBlock<T> block = TC8Core.BLOCKS.register(name, factory);
        TC8Core.ITEMS.registerSimpleBlockItem(block);
        return block;
    }

    public static final class ReestrogenCompat {
        private static boolean unavailable;

        private ReestrogenCompat() {}

        @SuppressWarnings({"unchecked", "rawtypes"})
        public static boolean refillDash(Entity entity) {
            if (unavailable) return false;

            try {
                Class<?> varsClass = Class.forName("reestrogen.network.ReestrogenModVariables");
                Field supplierField = varsClass.getField("PLAYER_VARIABLES");
                Object supplied = supplierField.get(null);

                if (!(supplied instanceof Supplier supplier)) return false;

                Object variables = entity.getData(supplier);
                Field cooldownField = variables.getClass().getField("dashcooldown");
                cooldownField.setDouble(variables, 0.0D);

                Method markDirty = variables.getClass().getMethod("markSyncDirty");
                markDirty.invoke(variables);
                return true;
            } catch (ReflectiveOperationException | RuntimeException ex) {
                unavailable = true;
                TC8Core.LOGGER.warn("ReEstrogen dash integration is unavailable", ex);
                return false;
            }
        }
    }

    public static class DreamBlock extends Block {
        public DreamBlock(Properties properties) {
            super(properties);
        }
    }

    public static class DashRefillCrystalBlock extends Block {
        public DashRefillCrystalBlock(Properties properties) {
            super(properties);
        }

        @Override
        protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            if (!level.isClientSide && entity instanceof LivingEntity && ReestrogenCompat.refillDash(entity)) {
                level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 0.8F, 1.7F);
            }
            super.entityInside(state, level, pos, entity);
        }
    }

    public static class SpringBlock extends Block {
        public SpringBlock(Properties properties) {
            super(properties);
        }

        @Override
        public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
            Vec3 velocity = entity.getDeltaMovement();
            entity.setDeltaMovement(velocity.x, Math.max(1.35D, velocity.y), velocity.z);
            entity.hurtMarked = true;
            if (!level.isClientSide) {
                level.playSound(null, pos, SoundEvents.SLIME_BLOCK_FALL, SoundSource.BLOCKS, 0.8F, 1.4F);
            }
            super.stepOn(level, pos, state, entity);
        }
    }

    public static class BoosterBlock extends Block {
        public BoosterBlock(Properties properties) {
            super(properties);
        }

        @Override
        protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            Vec3 look = entity.getLookAngle().normalize();
            entity.setDeltaMovement(look.x * 1.5D, Math.max(0.35D, look.y * 1.5D), look.z * 1.5D);
            entity.hurtMarked = true;
            super.entityInside(state, level, pos, entity);
        }
    }

    public static class LaunchBlock extends Block {
        private final double horizontalPower;
        private final double verticalPower;

        public LaunchBlock(Properties properties, double horizontalPower, double verticalPower) {
            super(properties);
            this.horizontalPower = horizontalPower;
            this.verticalPower = verticalPower;
        }

        @Override
        protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            Vec3 look = entity.getLookAngle();
            Vec3 horizontal = new Vec3(look.x, 0.0D, look.z);
            if (horizontal.lengthSqr() < 0.001D) horizontal = new Vec3(0.0D, 0.0D, 1.0D);
            horizontal = horizontal.normalize();
            entity.setDeltaMovement(
                    horizontal.x * horizontalPower,
                    Math.max(verticalPower, entity.getDeltaMovement().y),
                    horizontal.z * horizontalPower);
            entity.hurtMarked = true;
            if (!level.isClientSide) {
                level.playSound(null, pos, SoundEvents.PISTON_EXTEND, SoundSource.BLOCKS, 0.8F, 1.0F);
            }
            super.entityInside(state, level, pos, entity);
        }
    }

    public static class CrumbleBlock extends Block {
        public CrumbleBlock(Properties properties) {
            super(properties);
        }

        @Override
        public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
            if (!level.isClientSide) level.scheduleTick(pos, this, 16);
            super.stepOn(level, pos, state, entity);
        }

        @Override
        protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
            level.destroyBlock(pos, true);
        }
    }
}
