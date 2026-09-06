package cel20.redProt.mixin;

import cel20.redProt.RedstoneProtect;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.List;

@Mixin(FlowingFluid.class)
public class FlowingFluidMixin {

    /**
     * @author cel20
     * @reason Fluid check can flow event
     */

    @Overwrite
    private static boolean canHoldAnyFluid(BlockState state) {

        Block block = state.getBlock();

        if (block instanceof LiquidBlockContainer) {
            return true;
        }else{

            if(RedstoneProtect.redstoneProtected) {
                //Modification
                return state.blocksMotion()
                        ? false
                        : !(block instanceof DoorBlock)
                        && !state.is(BlockTags.SIGNS)
                        && !state.is(Blocks.LADDER)
                        && !state.is(Blocks.SUGAR_CANE)
                        && !state.is(Blocks.BUBBLE_COLUMN)
                        && !state.is(Blocks.NETHER_PORTAL)
                        && !state.is(Blocks.END_PORTAL)
                        && !state.is(Blocks.END_GATEWAY)
                        && !state.is(Blocks.STRUCTURE_VOID)
                        //RedstoneBlocks
                        && !state.is(Blocks.REDSTONE_WIRE)
                        && !state.is(Blocks.REPEATER)
                        && !state.is(Blocks.COMPARATOR)
                        && !state.is(Blocks.REDSTONE_TORCH)
                        && !state.is(Blocks.LEVER)
                        && !state.is(Blocks.TRIPWIRE)
                        && !state.is(Blocks.TRIPWIRE_HOOK)
                        && !state.is(BlockTags.BUTTONS);
            }else{
                return state.blocksMotion()
                        ? false
                        : !(block instanceof DoorBlock)
                        && !state.is(BlockTags.SIGNS)
                        && !state.is(Blocks.LADDER)
                        && !state.is(Blocks.SUGAR_CANE)
                        && !state.is(Blocks.BUBBLE_COLUMN)
                        && !state.is(Blocks.NETHER_PORTAL)
                        && !state.is(Blocks.END_PORTAL)
                        && !state.is(Blocks.END_GATEWAY)
                        && !state.is(Blocks.STRUCTURE_VOID);
            }

        }
    }
}
