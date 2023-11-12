package dev.newty.projectileding.commonmixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class ProjectileDingMixin {
    @Shadow public abstract void setSound(SoundEvent sound);

    @Unique private ClientPlayerEntity clientPlayer;


    @Inject(method = "onEntityHit", at = @At("HEAD"))
    private void onEntityHitHead(EntityHitResult entityHitResult, CallbackInfo ci) {
        // if it has not already been cached, do that!
        if (this.clientPlayer == null) {
            this.clientPlayer = MinecraftClient.getInstance().player;
        }

        Entity target = entityHitResult.getEntity();

        // ensure that the target is alive
        if (target instanceof LivingEntity) {
            ProjectileEntity projectile = (ProjectileEntity) (Object) this;

            // ensure that the projectile has been fired by the client player
            if (this.clientPlayer.equals(projectile.getOwner())) {
                this.setSound(SoundEvents.ENTITY_ARROW_HIT_PLAYER);
            }
        }
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void onEntityHitTail(EntityHitResult entityHitResult, CallbackInfo ci) {
        // ensure that the sound has been set back to its default, even if it hasn't changed
        this.setSound(SoundEvents.ENTITY_ARROW_HIT);
    }
}