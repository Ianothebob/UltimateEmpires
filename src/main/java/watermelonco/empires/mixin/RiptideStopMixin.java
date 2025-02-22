package watermelonco.empires.mixin;

import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class RiptideStopMixin {
	@Shadow protected ItemStack activeItemStack;

	@Inject(at = @At("RETURN"), method = "tickRiptide")
	public void tickRiptide(Box a, Box b, CallbackInfo ci) {
		final Logger logger = LoggerFactory.getLogger("StopRiptide");
		if(this.activeItemStack.getComponents().toString().contains("trident:1b")){
			ComponentMap comps = this.activeItemStack.getComponents();
			NbtCompound dataComp = comps.get(DataComponentTypes.CUSTOM_DATA).copyNbt();
			dataComp.putBoolean("usingTrident",false);
			NbtComponent v = NbtComponent.of(dataComp);
			this.activeItemStack.set(DataComponentTypes.CUSTOM_DATA, v);
			logger.info(comps.toString());
		}
	}
}