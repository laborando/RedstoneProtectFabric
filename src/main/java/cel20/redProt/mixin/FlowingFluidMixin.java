package cel20.redProt.mixin;

import cel20.redProt.RedstoneProtect;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

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

                boolean isRedstone =
                           state.is(Blocks.REDSTONE_WIRE)
                        || state.is(Blocks.REPEATER)
                        || state.is(Blocks.COMPARATOR)
                        || state.is(Blocks.REDSTONE_TORCH)
                        || state.is(Blocks.LEVER)
                        || state.is(Blocks.TRIPWIRE)
                        || state.is(Blocks.TRIPWIRE_HOOK)
                        || state.is(BlockTags.BUTTONS);

                return isRedstone ? false : state.is(BlockTags.WASHED_AWAY_BY_FLUIDS);

            }else{
                return state.is(BlockTags.WASHED_AWAY_BY_FLUIDS);
            }

        }
    }
}
