package watermelonco.empires.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.BannedPlayerEntry;
import net.minecraft.server.BannedPlayerList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ServerPlayerEntity.class})
public abstract class DeathMixin {
	/*public DeathMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
		super(world, pos, yaw, gameProfile);
	}*/


	@Shadow public abstract void readCustomDataFromNbt(NbtCompound nbt);

	@Shadow @Final public MinecraftServer server;

	//@Shadow public abstract ItemStack getMainHandStack();
	@Inject(at=@At("RETURN"),method="onDeath")
	public void onDeath(DamageSource damageSource, CallbackInfo ci) {
		final Logger logger = LoggerFactory.getLogger("death");
		NbtCompound attr = this.server.getDataCommandStorage().get(Identifier.of(((Entity) (Object) this).getUuidAsString()));
		if(attr.contains("lives")&&attr.getInt("lives")>1) {
			if(attr.getInt("lives")>2) {
				attr.putInt("lives",(attr.getInt("lives")-1));
				this.server.getDataCommandStorage().set(Identifier.of(((Entity) (Object) this).getUuidAsString()), attr);
			} else {
				attr.putInt("lives",(attr.getInt("lives")-2));
				this.server.getDataCommandStorage().set(Identifier.of(((Entity) (Object) this).getUuidAsString()), attr);
				GameProfile gameProfile = ((PlayerEntity) (Object) this).getGameProfile();
				BannedPlayerList bannedPlayerList = this.server.getPlayerManager().getUserBanList();
				BannedPlayerEntry bannedPlayerEntry = new BannedPlayerEntry(gameProfile, null, "server", null, "lives ran out");
				bannedPlayerList.add(bannedPlayerEntry);
				ServerPlayerEntity serverPlayerEntity = this.server.getPlayerManager().getPlayer(gameProfile.getId());
				if (serverPlayerEntity != null) {
					serverPlayerEntity.networkHandler.disconnect(Text.translatable("multiplayer.disconnect.banned"));
				}
			}
		} else {
			attr.putInt("lives",5);
			this.server.getDataCommandStorage().set(Identifier.of(((Entity) (Object) this).getUuidAsString()), attr);
		}
    }
}