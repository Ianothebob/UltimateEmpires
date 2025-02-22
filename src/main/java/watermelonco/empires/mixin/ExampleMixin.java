package watermelonco.empires.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({LivingEntity.class})
public abstract class ExampleMixin {
	@Shadow public abstract ItemStack getMainHandStack();

	@ModifyReturnValue(at = @At("RETURN"), method = "computeFallDamage")
	public int computeFallDamage(int original) {
		if((Object)this instanceof PlayerEntity) {
			final Logger logger = LoggerFactory.getLogger("damagesource");
			if(this.getMainHandStack().getComponents().toString().contains("usingTrident:1b")) {
				logger.info("{}",original);
				logger.info("{}",original/6);
				return (int) (original / 6);
			}
			return original;

		}
        return original;
    }
}