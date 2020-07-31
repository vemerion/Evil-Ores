package mod.vemerion.evilores.mobs.models;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class EvilIronModel<T extends Entity> extends SegmentedModel<T> {
	private final ModelRenderer body;

	public EvilIronModel() {
	      this.body = new ModelRenderer(this, 0, 0);
	      this.body.addBox(-8.0F, 8, -8.0F, 16.0F, 16.0F, 16.0F);
	   }

	   /**
	    * Sets this entity's model rotation angles
	    */
	   public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	   }

	   public Iterable<ModelRenderer> getParts() {
	      return ImmutableList.of(this.body);
	   }

}
