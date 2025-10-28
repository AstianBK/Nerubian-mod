package com.tbk.nerubian.client.model;// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tbk.nerubian.NerubianMod;
import com.tbk.nerubian.client.anim.ScarabAnim;
import com.tbk.nerubian.server.cap.NerubianCap;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.joml.Quaternionf;

public class ScarabModel<T extends Player> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NerubianMod.MODID, "scarab"), "main");
	private final ModelPart truemain;
	private final ModelPart main;
	private final ModelPart Head;
	private final ModelPart Spike;
	private final ModelPart Pincers;
	private final ModelPart Right;
	private final ModelPart Left;
	private final ModelPart Torso;
	private final ModelPart UpperChest;
	private final ModelPart Chest;
	private final ModelPart RightArm;
	private final ModelPart RightUpper;
	private final ModelPart RightLower;
	private final ModelPart RightHand;
	private final ModelPart LeftArm;
	private final ModelPart LeftUpper;
	private final ModelPart LeftLower;
	private final ModelPart LeftHand;
	private final ModelPart RightBackLeg;
	private final ModelPart SectionBackRight;
	private final ModelPart LeftBackLeg;
	private final ModelPart SectionBackLeft;
	private final ModelPart LeftFrontLeg;
	private final ModelPart SectionFrontLeft;
	private final ModelPart RightFrontLeg;
	private final ModelPart SectionFrontRight;
	public float swimAmount;

	public ScarabModel(ModelPart root) {
		this.truemain = root.getChild("truemain");
		this.main = this.truemain.getChild("main");
		this.Head = this.main.getChild("Head");
		this.Spike = this.Head.getChild("Spike");
		this.Pincers = this.Head.getChild("Pincers");
		this.Right = this.Pincers.getChild("Right");
		this.Left = this.Pincers.getChild("Left");
		this.Torso = this.main.getChild("Torso");
		this.UpperChest = this.Torso.getChild("UpperChest");
		this.Chest = this.UpperChest.getChild("Chest");
		this.RightArm = this.UpperChest.getChild("RightArm");
		this.RightUpper = this.RightArm.getChild("RightUpper");
		this.RightLower = this.RightArm.getChild("RightLower");
		this.RightHand = this.RightLower.getChild("RightHand");
		this.LeftArm = this.UpperChest.getChild("LeftArm");
		this.LeftUpper = this.LeftArm.getChild("LeftUpper");
		this.LeftLower = this.LeftArm.getChild("LeftLower");
		this.LeftHand = this.LeftLower.getChild("LeftHand");
		this.RightBackLeg = this.main.getChild("RightBackLeg");
		this.SectionBackRight = this.RightBackLeg.getChild("SectionBackRight");
		this.LeftBackLeg = this.main.getChild("LeftBackLeg");
		this.SectionBackLeft = this.LeftBackLeg.getChild("SectionBackLeft");
		this.LeftFrontLeg = this.main.getChild("LeftFrontLeg");
		this.SectionFrontLeft = this.LeftFrontLeg.getChild("SectionFrontLeft");
		this.RightFrontLeg = this.main.getChild("RightFrontLeg");
		this.SectionFrontRight = this.RightFrontLeg.getChild("SectionFrontRight");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition truemain = partdefinition.addOrReplaceChild("truemain", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -2.7972F));

		PartDefinition main = truemain.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head = main.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(38, 72).addBox(-4.0F, -6.553F, -5.0405F, 8.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 66).addBox(-5.0F, -6.653F, -6.0405F, 10.0F, 7.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(31, 60).addBox(-4.0F, -2.6781F, -6.3539F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(31, 60).mirror().addBox(2.0F, -2.6781F, -6.3539F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -28.3219F, 3.2539F));

		PartDefinition Spike = Head.addOrReplaceChild("Spike", CubeListBuilder.create(), PartPose.offset(0.0F, -0.7031F, 8.2461F));

		PartDefinition cube_r1 = Spike.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(59, 32).addBox(0.1745F, -3.4139F, -17.6267F, 0.0F, 9.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, -4.0F, 2.0071F, -0.0436F, -3.1416F));

		PartDefinition Pincers = Head.addOrReplaceChild("Pincers", CubeListBuilder.create(), PartPose.offset(0.0F, -1.6174F, -4.7287F));

		PartDefinition Right = Pincers.addOrReplaceChild("Right", CubeListBuilder.create(), PartPose.offset(-5.0F, 0.0F, -0.5F));

		PartDefinition PRight_r1 = Right.addOrReplaceChild("PRight_r1", CubeListBuilder.create().texOffs(18, 53).addBox(-3.0F, -3.0F, 0.0F, 7.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0607F, -0.0251F, -1.5708F, 0.0F, 0.0F));

		PartDefinition Left = Pincers.addOrReplaceChild("Left", CubeListBuilder.create(), PartPose.offset(5.0F, 0.0F, -0.5F));

		PartDefinition PLeft_r1 = Left.addOrReplaceChild("PLeft_r1", CubeListBuilder.create().texOffs(18, 53).mirror().addBox(-4.0F, -3.0F, 0.0F, 7.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.0607F, -0.0251F, -1.5708F, 0.0F, 0.0F));

		PartDefinition Torso = main.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 0).addBox(-7.45F, -8.3351F, 2.7107F, 16.0F, 9.0F, 25.0F, new CubeDeformation(0.0F))
				.texOffs(57, 1).addBox(-4.45F, 0.6649F, 2.7107F, 10.0F, 5.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.55F, -10.7002F, 4.153F));

		PartDefinition TorsoLower_r1 = Torso.addOrReplaceChild("TorsoLower_r1", CubeListBuilder.create().texOffs(44, 34).addBox(-3.0F, -3.5F, -3.5F, 10.0F, 7.0F, 8.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-1.45F, 3.2002F, -0.653F, 0.2618F, 0.0F, 0.0F));

		PartDefinition UpperChest = Torso.addOrReplaceChild("UpperChest", CubeListBuilder.create().texOffs(74, 26).addBox(-4.45F, -7.2747F, -4.9396F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Chest = UpperChest.addOrReplaceChild("Chest", CubeListBuilder.create().texOffs(0, 34).addBox(-7.0F, -10.0F, -4.0F, 14.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.55F, -7.2747F, -0.9395F));

		PartDefinition RightArm = UpperChest.addOrReplaceChild("RightArm", CubeListBuilder.create(), PartPose.offset(-6.55F, -15.3703F, -1.0745F));

		PartDefinition RightUpper = RightArm.addOrReplaceChild("RightUpper", CubeListBuilder.create().texOffs(104, 36).addBox(-4.9F, -1.9044F, -2.8651F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightLower = RightArm.addOrReplaceChild("RightLower", CubeListBuilder.create(), PartPose.offset(-2.0F, 5.0F, 0.0F));

		PartDefinition RightForeArm_r1 = RightLower.addOrReplaceChild("RightForeArm_r1", CubeListBuilder.create().texOffs(7, 7).addBox(-2.0F, -2.5F, 0.25F, 4.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, -0.1544F, -0.3651F, -1.5708F, 0.0F, 0.0F));

		PartDefinition RightHand = RightLower.addOrReplaceChild("RightHand", CubeListBuilder.create(), PartPose.offset(0.1F, 2.2696F, -10.2566F));

		PartDefinition RightClaw_r1 = RightHand.addOrReplaceChild("RightClaw_r1", CubeListBuilder.create().texOffs(0, -10).addBox(-1.0F, -3.5F, 0.5F, 0.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, -10).addBox(1.0F, -3.5F, 0.5F, 0.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(96, 0).addBox(-2.0F, -2.5F, -2.5F, 4.0F, 5.0F, 5.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.0F, 1.1483F, -1.8177F, -2.7053F, 0.0F, 0.0F));

		PartDefinition LeftArm = UpperChest.addOrReplaceChild("LeftArm", CubeListBuilder.create(), PartPose.offset(7.65F, -15.3703F, -1.0745F));

		PartDefinition LeftUpper = LeftArm.addOrReplaceChild("LeftUpper", CubeListBuilder.create().texOffs(104, 36).mirror().addBox(-0.1F, -1.9044F, -2.8651F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftLower = LeftArm.addOrReplaceChild("LeftLower", CubeListBuilder.create(), PartPose.offset(2.0F, 5.0F, 0.0F));

		PartDefinition LeftForeArm_r1 = LeftLower.addOrReplaceChild("LeftForeArm_r1", CubeListBuilder.create().texOffs(7, 7).mirror().addBox(-2.0F, -2.5F, 0.25F, 4.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.1F, -0.1544F, -0.3651F, -1.5708F, 0.0F, 0.0F));

		PartDefinition LeftHand = LeftLower.addOrReplaceChild("LeftHand", CubeListBuilder.create(), PartPose.offset(-0.1F, 2.2696F, -10.2566F));

		PartDefinition LeftClaw1_r1 = LeftHand.addOrReplaceChild("LeftClaw1_r1", CubeListBuilder.create().texOffs(0, -10).mirror().addBox(1.0F, -3.5F, 0.5F, 0.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, -10).mirror().addBox(-1.0F, -3.5F, 0.5F, 0.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(96, 0).mirror().addBox(-2.0F, -2.5F, -2.5F, 4.0F, 5.0F, 5.0F, new CubeDeformation(-0.02F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.1483F, -1.8177F, -2.7053F, 0.0F, 0.0F));

		PartDefinition RightBackLeg = main.addOrReplaceChild("RightBackLeg", CubeListBuilder.create().texOffs(0, 52).addBox(-2.5239F, -3.7321F, -9.254F, 4.0F, 4.0F, 10.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-5.3093F, -5.5763F, 10.3697F, 0.0F, 2.5307F, 0.0F));

		PartDefinition rightbackleg_r1 = RightBackLeg.addOrReplaceChild("rightbackleg_r1", CubeListBuilder.create().texOffs(96, 10).addBox(-3.0F, -6.0F, -2.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4761F, 0.0F, -13.7181F, -0.5236F, 0.0F, 0.0F));

		PartDefinition SectionBackRight = RightBackLeg.addOrReplaceChild("SectionBackRight", CubeListBuilder.create().texOffs(64, 12).addBox(-1.9833F, 4.3274F, -6.1715F, 4.0F, 6.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(-0.4906F, -4.7511F, -13.2238F));

		PartDefinition rightbacklegsection_r1 = SectionBackRight.addOrReplaceChild("rightbacklegsection_r1", CubeListBuilder.create().texOffs(64, 4).addBox(-2.0F, -6.0F, -1.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0167F, 10.0267F, -5.1253F, -0.3054F, 0.0F, 0.0F));

		PartDefinition rightbacklegsection_r2 = SectionBackRight.addOrReplaceChild("rightbacklegsection_r2", CubeListBuilder.create().texOffs(39, 49).addBox(-4.0F, -10.0F, -2.0F, 5.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4667F, 4.8274F, -5.1715F, -0.4363F, 0.0F, 0.0F));

		PartDefinition LeftBackLeg = main.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create().texOffs(0, 52).mirror().addBox(-1.4761F, -3.7321F, -9.254F, 4.0F, 4.0F, 10.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(5.3093F, -5.5763F, 10.3697F, 0.0F, -2.5307F, 0.0F));

		PartDefinition leftbackleg_r1 = LeftBackLeg.addOrReplaceChild("leftbackleg_r1", CubeListBuilder.create().texOffs(96, 10).mirror().addBox(-1.0F, -6.0F, -2.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.4761F, 0.0F, -13.7181F, -0.5236F, 0.0F, 0.0F));

		PartDefinition SectionBackLeft = LeftBackLeg.addOrReplaceChild("SectionBackLeft", CubeListBuilder.create().texOffs(64, 12).mirror().addBox(-2.0167F, 4.3274F, -6.1715F, 4.0F, 6.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offset(0.4906F, -4.7511F, -13.2238F));

		PartDefinition leftbacklegsection_r1 = SectionBackLeft.addOrReplaceChild("leftbacklegsection_r1", CubeListBuilder.create().texOffs(64, 4).mirror().addBox(-2.0F, -6.0F, -1.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.0167F, 10.0267F, -5.1253F, -0.3054F, 0.0F, 0.0F));

		PartDefinition leftbacklegsection_r2 = SectionBackLeft.addOrReplaceChild("leftbacklegsection_r2", CubeListBuilder.create().texOffs(39, 49).mirror().addBox(-1.0F, -10.0F, -2.0F, 5.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.4667F, 4.8274F, -5.1715F, -0.4363F, 0.0F, 0.0F));

		PartDefinition LeftFrontLeg = main.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create().texOffs(0, 52).mirror().addBox(-2.8688F, -3.7321F, -9.0084F, 4.0F, 4.0F, 10.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(4.1343F, -5.5763F, 10.3697F, 0.0F, -0.4363F, 0.0F));

		PartDefinition leftfrontleg_r1 = LeftFrontLeg.addOrReplaceChild("leftfrontleg_r1", CubeListBuilder.create().texOffs(96, 10).mirror().addBox(-1.0F, -6.0F, -2.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.8688F, 0.0F, -13.4725F, -0.5236F, 0.0F, 0.0F));

		PartDefinition SectionFrontLeft = LeftFrontLeg.addOrReplaceChild("SectionFrontLeft", CubeListBuilder.create().texOffs(64, 12).mirror().addBox(-2.0167F, 5.3274F, -7.1715F, 4.0F, 6.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offset(-0.9021F, -5.7511F, -11.9782F));

		PartDefinition sectionfrontleg_r1 = SectionFrontLeft.addOrReplaceChild("sectionfrontleg_r1", CubeListBuilder.create().texOffs(64, 4).mirror().addBox(-2.0F, -6.0F, -1.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.0167F, 11.0267F, -6.1253F, -0.3054F, 0.0F, 0.0F));

		PartDefinition sectionfrontleg_r2 = SectionFrontLeft.addOrReplaceChild("sectionfrontleg_r2", CubeListBuilder.create().texOffs(39, 49).mirror().addBox(-1.0F, -10.0F, -2.0F, 5.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.4667F, 5.8274F, -6.1715F, -0.4363F, 0.0F, 0.0F));

		PartDefinition RightFrontLeg = main.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create().texOffs(0, 52).addBox(-1.1312F, -3.7321F, -9.0084F, 4.0F, 4.0F, 10.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-4.1343F, -5.5763F, 10.3697F, 0.0F, 0.4363F, 0.0F));

		PartDefinition rightfrontleg_r1 = RightFrontLeg.addOrReplaceChild("rightfrontleg_r1", CubeListBuilder.create().texOffs(96, 10).addBox(-3.0F, -6.0F, -2.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8688F, 0.0F, -13.4725F, -0.5236F, 0.0F, 0.0F));

		PartDefinition SectionFrontRight = RightFrontLeg.addOrReplaceChild("SectionFrontRight", CubeListBuilder.create().texOffs(64, 12).addBox(-1.9833F, 5.3274F, -7.1715F, 4.0F, 6.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.9021F, -5.7511F, -11.9782F));

		PartDefinition sectionfrontleg_r3 = SectionFrontRight.addOrReplaceChild("sectionfrontleg_r3", CubeListBuilder.create().texOffs(64, 4).addBox(-2.0F, -6.0F, -1.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0167F, 11.0267F, -6.1253F, -0.3054F, 0.0F, 0.0F));

		PartDefinition sectionfrontleg_r4 = SectionFrontRight.addOrReplaceChild("sectionfrontleg_r4", CubeListBuilder.create().texOffs(39, 49).addBox(-4.0F, -10.0F, -2.0F, 5.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4667F, 5.8274F, -6.1715F, -0.4363F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTick) {
		this.swimAmount = entity.getSwimAmount(partialTick);
		super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		boolean flag = entity.getFallFlyingTicks() > 4;
		boolean flag1 = entity.isVisuallySwimming();
		this.Head.yRot = netHeadYaw * (float) (Math.PI / 180.0);
		if (flag) {
			this.Head.xRot = (float) (-Math.PI / 4);
		} else if (this.swimAmount > 0.0F) {
			if (flag1) {
				this.Head.xRot = this.rotlerpRad(this.swimAmount, this.Head.xRot, (float) (-Math.PI / 4));
			} else {
				this.Head.xRot = this.rotlerpRad(this.swimAmount, this.Head.xRot, headPitch * (float) (Math.PI / 180.0));
			}
		} else {
			this.Head.xRot = headPitch * (float) (Math.PI / 180.0);
		}
		setupAttackAnimation(entity,ageInTicks);
		NerubianCap.get(entity).ifPresent(nerubianCap -> {
			this.animate(nerubianCap.idle,ScarabAnim.idle,ageInTicks,1.0F);
			this.animate(nerubianCap.crouching,ScarabAnim.crouch,ageInTicks,1.0F);
			this.animate(nerubianCap.attack,ScarabAnim.attack,ageInTicks,1.0F);
			//this.animate(nerubianCap.use,ScarabAnim.use,ageInTicks,1.0F);
			this.animate(nerubianCap.block,ScarabAnim.block,ageInTicks,1.0F);

		});

		
		this.animateWalk(ScarabAnim.move,limbSwing,limbSwingAmount,2.0F,1.0F);
	}

	protected void setupAttackAnimation(T livingEntity, float ageInTicks) {
		if (!(this.attackTime <= 0.0F)) {
			HumanoidArm humanoidarm = HumanoidArm.RIGHT;
			ModelPart modelpart = this.RightArm;
			float f = this.attackTime;
			this.truemain.yRot = Mth.sin(Mth.sqrt(f) * (float) (Math.PI * 2)) * 0.2F;
			if (humanoidarm == HumanoidArm.LEFT) {
				this.truemain.yRot *= -1.0F;
			}

			this.RightArm.z = Mth.sin(this.truemain.yRot) * 5.0F;
			this.RightArm.x = -Mth.cos(this.truemain.yRot) * 5.0F;
			this.RightArm.yRot = this.RightArm.yRot + this.truemain.yRot;
			f = 1.0F - this.attackTime;
			f *= f;
			f *= f;
			f = 1.0F - f;
			float f1 = Mth.sin(f * (float) Math.PI);
			float f2 = Mth.sin(this.attackTime * (float) Math.PI) * -(this.Head.xRot - 0.7F) * 0.75F;
			modelpart.xRot -= f1 * 1.2F + f2;
			modelpart.yRot = modelpart.yRot + this.truemain.yRot * 2.0F;
			modelpart.zRot = modelpart.zRot + Mth.sin(this.attackTime * (float) Math.PI) * -0.4F;
		}
	}

	protected float rotlerpRad(float angle, float maxAngle, float mul) {
		float f = (mul - maxAngle) % (float) (Math.PI * 2);
		if (f < (float) -Math.PI) {
			f += (float) (Math.PI * 2);
		}

		if (f >= (float) Math.PI) {
			f -= (float) (Math.PI * 2);
		}

		return maxAngle + angle * f;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		truemain.render(poseStack, buffer, packedLight, packedOverlay, color);
	}

	public void translateToHand(PoseStack p_102855_,boolean isLeft) {
		this.rotate(p_102855_,this.Torso);
		if(isLeft){
			this.rotate(p_102855_,this.LeftArm);
			this.LeftHand.translateAndRotate(p_102855_);
		}else {
			this.rotate(p_102855_,this.RightArm);
			this.RightHand.translateAndRotate(p_102855_);

		}
	}

	public void rotate(PoseStack poseStack,ModelPart part) {
		//poseStack.translate(part.x / 16.0F, part.y / 16.0F, part.z / 16.0F);
		if (part.xRot != 0.0F || part.yRot != 0.0F || part.zRot != 0.0F) {
			poseStack.mulPose(new Quaternionf().rotationZYX(part.zRot, part.yRot, part.xRot));
		}

		if (part.xScale != 1.0F || part.yScale != 1.0F || part.zScale != 1.0F) {
			poseStack.scale(part.xScale, part.yScale, part.zScale);
		}
	}
	public ModelPart getHead(){
		return this.Head;
	}
	@Override
	public ModelPart root() {
		return truemain;
	}
}