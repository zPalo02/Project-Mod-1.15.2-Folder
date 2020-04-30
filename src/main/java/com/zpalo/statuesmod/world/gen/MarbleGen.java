package com.zpalo.statuesmod.world.gen;

import com.zpalo.statuesmod.init.BlockInit;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class MarbleGen {
	public static void generateOre() {
		for(Biome biome : ForgeRegistries.BIOMES) {
			if(biome == Biomes.MOUNTAIN_EDGE || biome ==Biomes.WOODED_MOUNTAINS) {
				ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(12, 45, 45, 95));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
								Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								BlockInit.marble.getDefaultState(), 45)).withPlacement(customConfig));
			}
		}
	}

}
