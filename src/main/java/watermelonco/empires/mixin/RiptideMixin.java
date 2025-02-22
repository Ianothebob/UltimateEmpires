package watermelonco.empires.mixin;

import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class RiptideMixin {
	@Shadow public abstract void readCustomDataFromNbt(NbtCompound nbt);
	@Inject(at = @At("HEAD"), method = "useRiptide")
	public void useRiptide(int riptideTicks, float riptideAttackDamage, ItemStack stack, CallbackInfo ci) {
		final Logger logger = LoggerFactory.getLogger("Riptide");
		if(stack.getComponents().toString().contains("trident:1b")){
			ComponentMap comps = stack.getComponents();
			NbtCompound dataComp = comps.get(DataComponentTypes.CUSTOM_DATA).copyNbt();
			dataComp.putBoolean("usingTrident",true);
			NbtComponent v = NbtComponent.of(dataComp);
			stack.set(DataComponentTypes.CUSTOM_DATA, v);
			logger.info(comps.toString());
		}
	}
}