package watermelonco.empires

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object UltimateEmpires : ModInitializer {
    private val logger = LoggerFactory.getLogger("ultimate-empires")
	const val MOD_ID: String = "ultimate-empires"

	override fun onInitialize() {
		UltimateItems.initialize()

		//ChunkPos.toLong()
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hello Fabric world!")
	}
}