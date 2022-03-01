package com.brewingcoder.brewtools.world;

import com.brewingcoder.brewtools.blocks.ModBlocks;
import com.brewingcoder.brewtools.tileentity.TileEntityMiningPortal;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class MiningWorldTeleporter implements ITeleporter {
    private final BlockPos pos;

    public MiningWorldTeleporter(BlockPos pos){
        this.pos = pos;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        Entity e = repositionEntity.apply(false);
        if(!(e instanceof ServerPlayerEntity)) return e;

        ServerPlayerEntity player = (ServerPlayerEntity) e;
        Chunk chunk = (Chunk) destWorld.getChunk(pos);

        BlockPos portalBlockPos = findPortalBlockInChunk(chunk);

        if (portalBlockPos == null) {
           portalBlockPos=placePortalBlock(destWorld,chunk);
        }
        if (portalBlockPos == null){
            return e;
        }

        player.giveExperienceLevels(0);
        player.teleportTo(portalBlockPos.getX() + 0.5D,portalBlockPos.getY()+1D, portalBlockPos.getZ() +0.5D);
        return e;
    }

    private BlockPos findPortalBlockInChunk(Chunk chunk){
        Stream<Map.Entry<BlockPos, TileEntity>> stream = chunk.getBlockEntities()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() instanceof TileEntityMiningPortal);
        BlockPos portalBlockPos = stream.sorted( (o1,o2) -> o2.getKey().getY() - o1.getKey().getY()).map(Map.Entry::getKey).findFirst().orElse(null);

        if (portalBlockPos != null){
            if(chunk.getBlockState(portalBlockPos.above()).is(Blocks.AIR)){
                return portalBlockPos;
            }
        }
        return null;
    }

    private BlockPos placePortalBlock(ServerWorld world, Chunk chunk){

        BlockPos.Mutable pos = new BlockPos.Mutable();
//        for(int y=world.getHeight()-1; y >= 1; y =y-1){
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for(int y=255; y >= 1; y--){
                        pos.set(x, y, z);
                        if(chunk.getBlockState(pos).is(Blocks.AIR)) {
                            continue;
                        }
                        if (chunk.getBlockState(pos.above(1)).is(Blocks.AIR) && chunk.getBlockState(pos.above(2)).is(Blocks.AIR)) {
                            BlockPos absolutePos = chunk.getPos().getWorldPosition().offset(pos.getX(), pos.getY(), pos.getZ());
                            world.setBlockAndUpdate(absolutePos, ModBlocks.MINING_PORTAL.get().defaultBlockState());
                            return absolutePos;
                        }
                    }
                }
            }
        return null;
    }
}
