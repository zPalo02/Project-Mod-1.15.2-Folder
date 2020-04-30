package com.zpalo.statuesmod.init;

import com.zpalo.statuesmod.StatuesMod;
import com.zpalo.statuesmod.StatuesMod.StatuesItemGroup;
import com.zpalo.statuesmod.objects.blocks.StatuesWorkbench;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(StatuesMod.MOD_ID)
@Mod.EventBusSubscriber(modid = StatuesMod.MOD_ID, bus = Bus.MOD)
public class BlockInit {
	public static final Block statues_workbench = null;
	public static final Block marble = null;

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry()
				.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)
						.harvestLevel(2).harvestTool(ToolType.PICKAXE)
						.sound(SoundType.STONE)).setRegistryName("marble"));
		event.getRegistry()
				.register(new StatuesWorkbench(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5f)
						.harvestTool(ToolType.AXE).sound(SoundType.WOOD).speedFactor(0.7f)
						.slipperiness(1.2f)).setRegistryName("statues_workbench"));
		// event.getRegistry().register(new
		// BlockTest(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f,
		// 18.0f).sound(SoundType.WOOD).harvestLevel(1).harvestTool(ToolType.AXE)));
	}

	@SubscribeEvent
	public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry()
			.register(new BlockItem(marble, new Item.Properties().group(StatuesItemGroup.instance))
						.setRegistryName("marble"));
		event.getRegistry()
				.register(new BlockItem(statues_workbench, new Item.Properties().group(StatuesItemGroup.instance))
						.setRegistryName("statues_workbench"));
		// event.getRegistry().register(new BlockItem(test_block, new
		// Item.Properties().group(TutorialItemGroup.instance)).setRegistryName("test_block"));
	}
}
