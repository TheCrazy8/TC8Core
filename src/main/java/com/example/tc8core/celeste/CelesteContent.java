package com.example.tc8core.celeste;

import com.example.tc8core.TC8Core;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public final class CelesteContent {
    private CelesteContent() {}

    private static final VoxelShape DASH_REFILL_CRYSTAL_SHAPE = Shapes.or(
            Block.box(6, 0, 6, 10, 14, 10),
            Block.box(2, 0, 4, 6, 10, 8),
            Block.box(10, 0, 8, 14, 11, 12),
            Block.box(4, 0, 10, 8, 8, 14)
    );

    private static final VoxelShape SPRING_SHAPE = Shapes.or(
            Block.box(1, 0, 1, 15, 3, 15),
            Block.box(3, 3, 3, 13, 5, 13),
            Block.box(5, 5, 5, 11, 8, 11),
            Block.box(3, 8, 3, 13, 10, 13),
            Block.box(5, 10, 5, 11, 13, 11),
            Block.box(2, 13, 2, 14, 16, 14)
    );

    private static final VoxelShape BOOSTER_SHAPE = Shapes.or(
            Block.box(1, 1, 1, 15, 15, 15),
            Block.box(4, 4, 0, 12, 12, 2),
            Block.box(4, 4, 14, 12, 12, 16),
            Block.box(0, 4, 4, 2, 12, 12),
            Block.box(14, 4, 4, 16, 12, 12)
    );

    private static final VoxelShape THEO_CRYSTAL_STATUE_SHAPE = Shapes.or(
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(5, 2, 5, 11, 10, 11),
            Block.box(6, 10, 6, 10, 14, 10),
            Block.box(3, 6, 6, 5, 10, 10),
            Block.box(11, 6, 6, 13, 10, 10)
    );

    private static final VoxelShape MINI_MOUNTAIN_SHAPE = Shapes.or(
            Block.box(1, 0, 1, 15, 3, 15),
            Block.box(3, 3, 3, 13, 7, 13),
            Block.box(5, 7, 5, 11, 11, 11),
            Block.box(7, 11, 7, 10, 16, 10)
    );

    private static final VoxelShape CELESTE_SIGN_SHAPE = Shapes.or(
            Block.box(1, 5, 7, 15, 15, 9),
            Block.box(7, 0, 7, 9, 5, 9)
    );

    private static final VoxelShape CELESTE_BANNER_SHAPE = Shapes.or(
            Block.box(2, 2, 7, 14, 16, 9),
            Block.box(7, 0, 7, 9, 2, 9)
    );

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
                    properties(MapColor.COLOR_CYAN, 1.0F, 3.0F).lightLevel(s -> 12).noOcclusion(),
                    DASH_REFILL_CRYSTAL_SHAPE));
    public static final DeferredBlock<Block> TRAFFIC_BLOCK =
            registerSpecial("traffic_block", () -> new LaunchBlock(
                    properties(MapColor.COLOR_RED, 3.0F, 6.0F), 1.7D, 0.25D));
    public static final DeferredBlock<Block> SPRING_BLOCK =
            registerSpecial("spring_block", () -> new SpringBlock(
                    properties(MapColor.COLOR_RED, 1.5F, 3.0F).noOcclusion(), SPRING_SHAPE));
    public static final DeferredBlock<Block> BOOSTER_BLOCK =
            registerSpecial("booster_block", () -> new BoosterBlock(
                    properties(MapColor.COLOR_GREEN, 1.5F, 3.0F).lightLevel(s -> 8).noOcclusion(),
                    BOOSTER_SHAPE));
    public static final DeferredBlock<Block> CRUMBLE_BLOCK =
            registerSpecial("crumble_block", () -> new CrumbleBlock(
                    properties(MapColor.COLOR_LIGHT_GRAY, 1.0F, 2.0F)));
    public static final DeferredBlock<Block> KEVIN_BLOCK =
            registerSpecial("kevin_block", () -> new LaunchBlock(
                    properties(MapColor.COLOR_PURPLE, 5.0F, 10.0F), 2.2D, 0.5D));

    public static final DeferredBlock<Block> THEO_CRYSTAL_STATUE =
            registerShapedBlock("theo_crystal_statue", MapColor.COLOR_CYAN, 2.0F, 5.0F, 6,
                    THEO_CRYSTAL_STATUE_SHAPE);
    public static final DeferredBlock<Block> MINI_MOUNTAIN_MODEL =
            registerShapedBlock("mini_mountain_model", MapColor.STONE, 2.0F, 5.0F, 0,
                    MINI_MOUNTAIN_SHAPE);
    public static final DeferredBlock<Block> CELESTE_SIGN =
            registerShapedBlock("celeste_sign", MapColor.WOOD, 1.0F, 2.0F, 0,
                    CELESTE_SIGN_SHAPE);
    public static final DeferredBlock<Block> CELESTE_BANNER =
            registerShapedBlock("celeste_banner", MapColor.COLOR_PURPLE, 1.0F, 2.0F, 0,
                    CELESTE_BANNER_SHAPE);

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

    private static DeferredBlock<Block> registerShapedBlock(
            String name, MapColor color, float hardness, float resistance, int light, VoxelShape shape) {
        return registerSpecial(name, () -> new ShapedBlock(
                properties(color, hardness, resistance)
                        .lightLevel(state -> light)
                        .noOcclusion(),
                shape));
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

    public static class ShapedBlock extends Block {
        private final VoxelShape shape;

        public ShapedBlock(Properties properties, VoxelShape shape) {
            super(properties);
            this.shape = shape;
        }

        @Override
        protected VoxelShape getShape(
                BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
            return shape;
        }

        @Override
        protected VoxelShape getCollisionShape(
                BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
            return shape;
        }
    }

    public static class DreamBlock extends Block {
        public DreamBlock(Properties properties) {
            super(properties);
        }
    }

    public static class DashRefillCrystalBlock extends ShapedBlock {
        public DashRefillCrystalBlock(Properties properties, VoxelShape shape) {
            super(properties, shape);
        }

        @Override
        protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            if (!level.isClientSide && entity instanceof LivingEntity && ReestrogenCompat.refillDash(entity)) {
                level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 0.8F, 1.7F);
            }
            super.entityInside(state, level, pos, entity);
        }
    }

    public static class SpringBlock extends ShapedBlock {
        public SpringBlock(Properties properties, VoxelShape shape) {
            super(properties, shape);
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

    public static class BoosterBlock extends ShapedBlock {
        public BoosterBlock(Properties properties, VoxelShape shape) {
            super(properties, shape);
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
