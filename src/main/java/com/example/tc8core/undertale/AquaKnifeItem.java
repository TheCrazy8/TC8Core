package com.example.tc8core.undertale;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.Set;

/**
 * A fast knife weapon that exposes Farmer's Delight-compatible knife abilities
 * without requiring Farmer's Delight on TC8Core's compile classpath.
 */
public class AquaKnifeItem extends SwordItem {
    private static final ItemAbility KNIFE_DIG = ItemAbility.get("knife_dig");
    private static final ItemAbility KNIFE_HARVEST = ItemAbility.get("knife_harvest");

    private static final Set<ItemAbility> KNIFE_ACTIONS = Set.of(
            ItemAbilities.SHEARS_CARVE,
            ItemAbilities.SWORD_DIG,
            KNIFE_DIG,
            KNIFE_HARVEST
    );

    public AquaKnifeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility ability) {
        return KNIFE_ACTIONS.contains(ability) || super.canPerformAction(stack, ability);
    }
}
