package com.paiique.template.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.entity.ExplosionConfig;
import com.hypixel.hytale.server.core.entity.ExplosionUtils;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ExplodeCommand extends AbstractCommand {

    public ExplodeCommand(String name, String description) {
        super(name, description);
    }

    @Nullable
    @Override
    protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
        if (!(context.sender() instanceof Player player)) return CompletableFuture.completedFuture(null);
        World world = player.getWorld();
        if (world == null) {
            player.sendMessage(Message.raw("You are not in a world!"));
            return CompletableFuture.completedFuture(null);
        }
        Store<ChunkStore> chunkStore = world.getChunkStore().getStore();

        Ref<EntityStore> ref = player.getReference();
        if (ref == null) {
            player.sendMessage(Message.raw("You have no reference!"));
            return CompletableFuture.completedFuture(null);
        }
        Store<EntityStore> entityStore = world.getEntityStore().getStore();

        Damage damage = new Damage(Damage.NULL_SOURCE, 50, 1);
        final class ExplosionCustom extends ExplosionConfig {
            public ExplosionCustom() {
                super();
                this.blockDamageRadius = 20;
                this.entityDamageRadius = 20;
                this.entityDamage = 50;
                this.blockDamageFalloff = 1.0f;
                this.blockDropChance = 1.0f;
            }
        }

        world.execute(() -> {
            entityStore.forEachChunk((chunk, commandBuffer) -> {
                ExplosionConfig explosionConfig = new ExplosionCustom();
                Vector3d playerLocation = player.getPlayerRef().getTransform().getPosition();
                ExplosionUtils.performExplosion(
                        damage.getSource(),
                        playerLocation,
                        explosionConfig,
                        player.getReference(),
                        commandBuffer,
                        chunkStore);
                return false;
            });
        });
        context.sendMessage(Message.raw("Kaboom!"));
        return CompletableFuture.completedFuture(null);
    }

}
