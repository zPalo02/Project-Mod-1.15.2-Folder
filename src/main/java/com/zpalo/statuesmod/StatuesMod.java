package com.zpalo.statuesmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zpalo.statuesmod.init.BlockInit;
import com.zpalo.statuesmod.world.gen.MarbleGen;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("statuesmod")
@Mod.EventBusSubscriber(modid = StatuesMod.MOD_ID, bus = Bus.MOD)
public class StatuesMod {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "statuesmod";
	public static StatuesMod instance;
	// public static final WorldType EXAMPLE_WORLDTYPE = new ExampleWorldType();
	public static final ResourceLocation EXAMPLE_DIM_TYPE = new ResourceLocation(MOD_ID, "example");

	public StatuesMod() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::doClientStuff);
		
		instance = this;
		MinecraftForge.EVENT_BUS.register(this);
	}
	private void setup(final FMLCommonSetupEvent event) {

	}
	
	private void doClientStuff(final FMLClientSetupEvent event) {
		
	}

	@SubscribeEvent
	public static void onServerStarting(FMLServerStartingEvent event) {

	}
	
	@SubscribeEvent
	public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
		MarbleGen.generateOre();
	}
	
	public static class StatuesItemGroup extends ItemGroup {
		public static StatuesItemGroup instance = new StatuesItemGroup(ItemGroup.GROUPS.length,"statuestab");
		
		private StatuesItemGroup(int index, String label) {
			super(index, label);
		}
		
		public ItemStack createIcon() {
			return new ItemStack(BlockInit.statues_workbench);
		}
	}
}
