package com.programmeracid.oldpiglinbarter.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.Identifier;


import java.util.List;

public class ModLootTableModifiers {
    private static final Identifier PIGLIN_BARTER_ID = Identifier.of("minecraft", "gameplay/piglin_barter");



    public static void modifyLootTables() {
//        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
//            // key.getValue() matches how the example you pasted checks IDs
//            if (LootTables.PIGLIN_BARTERING_GAMEPLAY.equals(key)) {
//                // Example: add a pearl pool (2-4 pearls) with a configurable chance
//                LootPool.Builder pearlPool = LootPool.builder()
//                        .rolls(ConstantLootNumberProvider.create(1))
//                        .conditionally(RandomChanceLootCondition.builder(0.0473f)) // 5% chance per ingot (adjust this)
//                        .with(ItemEntry.builder(Items.ENDER_PEARL)
//                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 6.0f)))
//                        );
//
//                // Example: add obsidian (1 per successful roll) with vanilla-ish chance
//                LootPool.Builder obsidianPool = LootPool.builder()
//                        .rolls(ConstantLootNumberProvider.create(1))
//                        .conditionally(RandomChanceLootCondition.builder(0.0946f)) // ~8.71% chance
//                        .with(ItemEntry.builder(Items.OBSIDIAN)
//                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
//                        );
//
//                tableBuilder.pool(pearlPool.build());
//                tableBuilder.pool(obsidianPool.build());
//            }
//        });

        LootTableEvents.REPLACE.register(((registryKey, lootTable, lootTableSource, wrapperLookup) -> {


            if (LootTables.PIGLIN_BARTERING_GAMEPLAY.equals(registryKey)){

                RegistryWrapper.WrapperLookup registries = wrapperLookup;
                RegistryEntry<Enchantment> soulSpeed = registries.getOrThrow(RegistryKeys.ENCHANTMENT)
                        .getOrThrow(Enchantments.SOUL_SPEED);

                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))

                        // Enchanted Book with Soul Speed (5/423)
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK)
                                .weight(5)
                                .apply(new EnchantWithLevelsLootFunction.Builder(
                                        UniformLootNumberProvider.create(1, 3))
                                        .options(RegistryEntryList.of(soulSpeed))
                                ))

                        // Iron Boots with Soul Speed (8/423)
                        .with(ItemEntry.builder(Items.IRON_BOOTS)
                                .weight(5)
                                .apply(new EnchantWithLevelsLootFunction.Builder(
                                        UniformLootNumberProvider.create(1, 3))
                                        .options(RegistryEntryList.of(soulSpeed))
                                )
                        )

                        // Iron Nugget (9–36) (10/423)
                        .with(ItemEntry.builder(Items.IRON_NUGGET)
                                .weight(10)
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(9, 36)
                                )))

                        // Splash Potion of Fire Resistance (42/423)
                        .with(ItemEntry.builder(Items.SPLASH_POTION)
                                .weight(10)
                                .apply(SetPotionLootFunction.builder(Potions.FIRE_RESISTANCE)))

// Potion of Fire Resistance (42/423)
                        .with(ItemEntry.builder(Items.POTION)
                                .weight(10)
                                .apply(SetPotionLootFunction.builder(Potions.FIRE_RESISTANCE)))

                        // Nether Quartz (8–16) (20/423)
                        .with(ItemEntry.builder(Items.QUARTZ)
                                .weight(20)
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(8, 16)
                                )))

                        // Glowstone Dust (5–12) (?? weight ~20/423 → adjust to your table)
                        .with(ItemEntry.builder(Items.GLOWSTONE_DUST)
                                .weight(20)
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(5, 12)
                                )))

                        // Magma Cream (2–6) (~20/423 weight → use 10)
                        .with(ItemEntry.builder(Items.MAGMA_CREAM)
                                .weight(20)
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(2, 6)
                                )))

                        // Ender Pearls (4–8) (~20/423 weight → use 20)
                        .with(ItemEntry.builder(Items.ENDER_PEARL)
                                .weight(20)
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(4, 8)
                                )))

                        // String (8–24) (~10/423 weight → use 10)
                        .with(ItemEntry.builder(Items.STRING)
                                .weight(20)
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(8, 24)
                                )))

                        // Fire Charge (1–5) (40/423)
                        .with(ItemEntry.builder(Items.FIRE_CHARGE)
                                .weight(40)
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(1, 5)
                                )))

                        // Gravel (8–16) (~10/423 weight → use 10)
                        .with(ItemEntry.builder(Items.GRAVEL)
                                .weight(40)
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(8, 16)
                                )))

                        // Leather (4–10) (~10/423 weight → use 10)
                        .with(ItemEntry.builder(Items.LEATHER)
                                .weight(40)
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(4, 10)
                                )))

                        // Nether Brick (4–16) (~10/423 weight → use 10)
                        .with(ItemEntry.builder(Items.NETHER_BRICK)
                                .weight(40)
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(4, 16)
                                )))

                        // Obsidian (1) (~10/423 weight → use 10)
                        .with(ItemEntry.builder(Items.OBSIDIAN)
                                .weight(40))

                        // Crying Obsidian (1–3) (~10/423 weight → use 10)
                        .with(ItemEntry.builder(Items.CRYING_OBSIDIAN)
                                .weight(40)
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(1, 3)
                                )))

                        // Soul Sand (4–16) (~10/423 weight → use 10)
                        .with(ItemEntry.builder(Items.SOUL_SAND)
                                .weight(40)
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(4, 16)
                                )));

                return LootTable.builder().pool(pool).build();
            }

            return lootTable;
        }));
    }
}
