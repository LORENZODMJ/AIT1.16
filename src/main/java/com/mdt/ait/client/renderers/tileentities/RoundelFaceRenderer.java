package com.mdt.ait.client.renderers.tileentities;

import com.mdt.ait.AIT;
import com.mdt.ait.client.models.tileentities.RoundelFace;
import com.mdt.ait.client.models.tileentities.TSV;
import com.mdt.ait.client.renderers.AITRenderTypes;
import com.mdt.ait.common.blocks.RoundelFaceBlock;
import com.mdt.ait.common.blocks.TSVBlock;
import com.mdt.ait.common.tileentities.RoundelFaceTile;
import com.mdt.ait.core.init.enums.EnumRoundelFaceState;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;


public class RoundelFaceRenderer extends TileEntityRenderer<RoundelFaceTile> {

    public ResourceLocation roundelLocation;

    public static final ResourceLocation MINT_FULL_LOCATION = new ResourceLocation(AIT.MOD_ID, "textures/tileentities/mint_roundel_face.png");
    public static final ResourceLocation MINT_HALF_LOCATION = new ResourceLocation(AIT.MOD_ID, "textures/tileentities/mint_roundel_face1.png");
    public RoundelFace model;
    private final TileEntityRendererDispatcher rendererDispatcher;

    public RoundelFaceRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
        this.model = new RoundelFace();
        this.rendererDispatcher = rendererDispatcherIn;
    }

    @Override
    public void render(RoundelFaceTile tile, float PartialTicks, MatrixStack MatrixStackIn, IRenderTypeBuffer Buffer, int CombinedLight, int CombinedOverlay) {
        MatrixStackIn.pushPose();
        if(tile.roundelFaceState == EnumRoundelFaceState.FULL_MINT) {
            roundelLocation = MINT_FULL_LOCATION;
        }
        if(tile.roundelFaceState == EnumRoundelFaceState.HALF_MINT) {
            roundelLocation = MINT_HALF_LOCATION;
        }
        MatrixStackIn.translate(0.5, 0, 0.5);
        MatrixStackIn.scale(1f, 1f, 1f);
        MatrixStackIn.translate(0, 1.5f, 0);
        MatrixStackIn.mulPose(Vector3f.XN.rotationDegrees(180.0f));
        MatrixStackIn.mulPose(Vector3f.YP.rotationDegrees(tile.getBlockState().getValue(RoundelFaceBlock.FACING).toYRot()));
        MatrixStackIn.mulPose(Vector3f.YN.rotationDegrees(180.0f));
        model.render(tile, MatrixStackIn, Buffer.getBuffer(AITRenderTypes.TardisRenderOver(roundelLocation)), 15728880, CombinedOverlay, 1, 1, 1, 1);
        MatrixStackIn.popPose();
    }
}
