package org.avp;

import java.util.ArrayList;
import java.util.Arrays;

import org.avp.dimension.BiomeGenLV;
import org.avp.dimension.varda.BiomeGenVarda;
import org.avp.entities.EntityAPC;
import org.avp.entities.EntityAcidPool;
import org.avp.entities.EntityAcidProjectile;
import org.avp.entities.EntityFlame;
import org.avp.entities.EntityGrenade;
import org.avp.entities.EntityLaserMine;
import org.avp.entities.EntityLiquidLatexPool;
import org.avp.entities.EntityMechanism;
import org.avp.entities.EntityMedpod;
import org.avp.entities.EntityNuke;
import org.avp.entities.EntityPlasma;
import org.avp.entities.EntityShuriken;
import org.avp.entities.EntitySmartDisc;
import org.avp.entities.EntitySpear;
import org.avp.entities.EntitySupplyChute;
import org.avp.entities.EntitySupplyChuteMarines;
import org.avp.entities.EntitySupplyChuteSeegson;
import org.avp.entities.EntityTurret;
import org.avp.entities.mob.EntityAethon;
import org.avp.entities.mob.EntityAqua;
import org.avp.entities.mob.EntityChestburster;
import org.avp.entities.mob.EntityCombatSynthetic;
import org.avp.entities.mob.EntityCrusher;
import org.avp.entities.mob.EntityDeacon;
import org.avp.entities.mob.EntityDeaconShark;
import org.avp.entities.mob.EntityDrone;
import org.avp.entities.mob.EntityEngineer;
import org.avp.entities.mob.EntityFacehugger;
import org.avp.entities.mob.EntityGooMutant;
import org.avp.entities.mob.EntityHammerpede;
import org.avp.entities.mob.EntityMarine;
import org.avp.entities.mob.EntityOvamorph;
import org.avp.entities.mob.EntityPraetorian;
import org.avp.entities.mob.EntityPredalien;
import org.avp.entities.mob.EntityQueen;
import org.avp.entities.mob.EntityRoyalFacehugger;
import org.avp.entities.mob.EntityRunnerDrone;
import org.avp.entities.mob.EntityRunnerWarrior;
import org.avp.entities.mob.EntitySpaceJockey;
import org.avp.entities.mob.EntitySpitter;
import org.avp.entities.mob.EntityTrilobite;
import org.avp.entities.mob.EntityUltramorph;
import org.avp.entities.mob.EntityWarrior;
import org.avp.entities.mob.EntityYautja;
import org.avp.entities.mob.EntityYautjaBerserker;
import org.avp.entities.tile.TileEntityAmpule;
import org.avp.entities.tile.TileEntityAssembler;
import org.avp.entities.tile.TileEntityBlastdoor;
import org.avp.entities.tile.TileEntityCryostasisTube;
import org.avp.entities.tile.TileEntityGunLocker;
import org.avp.entities.tile.TileEntityHiveResin;
import org.avp.entities.tile.TileEntityLightPanel;
import org.avp.entities.tile.TileEntityLocker;
import org.avp.entities.tile.TileEntityMedpod;
import org.avp.entities.tile.TileEntityNegativeTransformer;
import org.avp.entities.tile.TileEntityRedstoneEmitter;
import org.avp.entities.tile.TileEntityRedstoneFluxGenerator;
import org.avp.entities.tile.TileEntityPowercell;
import org.avp.entities.tile.TileEntityPowerline;
import org.avp.entities.tile.TileEntityRedstoneSensor;
import org.avp.entities.tile.TileEntityRepulsionGenerator;
import org.avp.entities.tile.TileEntitySatelliteDish;
import org.avp.entities.tile.TileEntitySatelliteModem;
import org.avp.entities.tile.TileEntitySkull;
import org.avp.entities.tile.TileEntitySolarPanel;
import org.avp.entities.tile.TileEntityStasisMechanism;
import org.avp.entities.tile.TileEntitySupplyCrate;
import org.avp.entities.tile.TileEntityTransformer;
import org.avp.entities.tile.TileEntityTurret;
import org.avp.entities.tile.TileEntityWorkstation;

import com.arisux.mdxlib.MDX;
import com.arisux.mdxlib.lib.game.Game;
import com.arisux.mdxlib.lib.game.IInitEvent;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public class EntityHandler implements IInitEvent
{
    public static final EntityHandler instance = new EntityHandler();
    private static int                entityId = 0;

    @Override
    public void init(FMLInitializationEvent event)
    {
        this.registerTileEntities();
        this.registerEntities();
        this.registerLivingEntities();
        this.registerRemappedEntities();
        this.registerSpawns();
    }

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityTurret.class, "tileEntityTurret");
        GameRegistry.registerTileEntity(TileEntityWorkstation.class, "tileEntityWorkstation");
        GameRegistry.registerTileEntity(TileEntityHiveResin.class, "tileEntityBlockHive");
        GameRegistry.registerTileEntity(TileEntityAssembler.class, "tileEntityAssembler");
        GameRegistry.registerTileEntity(TileEntityStasisMechanism.class, "tileStasisMechanism");
        GameRegistry.registerTileEntity(TileEntityRepulsionGenerator.class, "tileEntityGenerator");
        GameRegistry.registerTileEntity(TileEntityPowerline.class, "tileEntityPowerline");
        GameRegistry.registerTileEntity(TileEntityBlastdoor.class, "tileEntityBlastdoor");
        GameRegistry.registerTileEntity(TileEntityCryostasisTube.class, "tileEntityCryostasisTube");
        GameRegistry.registerTileEntity(TileEntityLightPanel.class, "tileEntityLightPanel");
        GameRegistry.registerTileEntity(TileEntitySatelliteModem.class, "tileEntitySatelliteModem");
        GameRegistry.registerTileEntity(TileEntitySatelliteDish.class, "tileEntitySatelliteDish");
        GameRegistry.registerTileEntity(TileEntityTransformer.class, "tileEntityTransformer");
        GameRegistry.registerTileEntity(TileEntityNegativeTransformer.class, "tileEntityNegativeTransformer");
        GameRegistry.registerTileEntity(TileEntityRedstoneSensor.class, "tileEntityR2PConverter");
        GameRegistry.registerTileEntity(TileEntityRedstoneEmitter.class, "tileEntityP2RConverter");
        GameRegistry.registerTileEntity(TileEntityPowercell.class, "tileEntityPowercell");
        GameRegistry.registerTileEntity(TileEntityAmpule.class, "tileEntityAmpule");
        GameRegistry.registerTileEntity(TileEntityLocker.class, "tileEntityLocker");
        GameRegistry.registerTileEntity(TileEntityGunLocker.class, "tileEntityGunLocker");
        GameRegistry.registerTileEntity(TileEntityMedpod.class, "tileEntityMedpod");
        GameRegistry.registerTileEntity(TileEntitySupplyCrate.class, "tileEntitySupplyCrate");
        GameRegistry.registerTileEntity(TileEntitySolarPanel.class, "tile.avp.solarpanel");
        GameRegistry.registerTileEntity(TileEntitySkull.class, "tile.avp.skull");
        GameRegistry.registerTileEntity(TileEntityRedstoneFluxGenerator.class, "tile.avp.redstonefluxgenerator");
    }

    private void registerRemappedEntities()
    {
        //Global Entity Identity Remapping
        MDX.registerRemappedEntity(EntityRunnerDrone.class, "RunnerDrone");
        MDX.registerRemappedEntity(EntityRunnerWarrior.class, "RunnerWarrior");
        MDX.registerRemappedEntity(EntityDrone.class, "Drone");
        MDX.registerRemappedEntity(EntityWarrior.class, "Warrior");
        MDX.registerRemappedEntity(EntitySpitter.class, "Spitter");
        MDX.registerRemappedEntity(EntityCrusher.class, "Crusher");
        MDX.registerRemappedEntity(EntityPraetorian.class, "Praetorian");
        MDX.registerRemappedEntity(EntityMarine.class, "Marine");
        MDX.registerRemappedEntity(EntityYautja.class, "Yautja");
        MDX.registerRemappedEntity(EntityQueen.class, "Queen");
        MDX.registerRemappedEntity(EntityFacehugger.class, "Facehugger");
        MDX.registerRemappedEntity(EntityChestburster.class, "Chestbuster");
        MDX.registerRemappedEntity(EntityOvamorph.class, "Ovamorph");
        MDX.registerRemappedEntity(EntityRoyalFacehugger.class, "RoyalFacehugger");
        MDX.registerRemappedEntity(EntityAqua.class, "AquaAlien");
        MDX.registerRemappedEntity(EntityPredalien.class, "Predalien");
        MDX.registerRemappedEntity(EntityCombatSynthetic.class, "CombatSynthetic");
        MDX.registerRemappedEntity(EntityDeacon.class, "Protomorph");
        MDX.registerRemappedEntity(EntityHammerpede.class, "Hammerpede");
        MDX.registerRemappedEntity(EntityTrilobite.class, "Trilobite");
        MDX.registerRemappedEntity(EntitySpaceJockey.class, "SpaceJockey");
        MDX.registerRemappedEntity(EntityEngineer.class, "Engineer");
        MDX.registerRemappedEntity(EntityYautjaBerserker.class, "YautjaBerserker");
        MDX.registerRemappedEntity(EntityDeaconShark.class, "DeaconShark");

        //Mod Entity Identity Remapping
        MDX.registerRemappedEntity(EntityDeacon.class, "avp.Protomorph");
    }

    private void registerEntities()
    {
        Game.register(EntitySpear.class, "Spear", entityId++, AliensVsPredator.instance(), 250, 4, true);
        Game.register(EntityLaserMine.class, "ProximityMine", entityId++, AliensVsPredator.instance(), 250, 8, true);
        Game.register(EntityPlasma.class, "Plasma", entityId++, AliensVsPredator.instance(), 250, 4, true);
        Game.register(EntityGrenade.class, "Grenade", entityId++, AliensVsPredator.instance(), 250, 4, true);
        Game.register(EntityFlame.class, "Flamethrower", entityId++, AliensVsPredator.instance(), 250, 4, true);
        Game.register(EntityAcidPool.class, "AcidPool", entityId++, AliensVsPredator.instance(), 250, 16, true);
        Game.register(EntityLiquidLatexPool.class, "LiquidLatexPool", entityId++, AliensVsPredator.instance(), 250, 16, true);
        Game.register(EntityAcidProjectile.class, "AcidSpit", entityId++, AliensVsPredator.instance(), 250, 4, true);
        Game.register(EntitySmartDisc.class, "EntityDisc", entityId++, AliensVsPredator.instance(), 250, 4, true);
        Game.register(EntityShuriken.class, "EntityShuriken", entityId++, AliensVsPredator.instance(), 250, 4, true);
        Game.register(EntityTurret.class, "EntityTurret", entityId++, AliensVsPredator.instance(), 250, 8, true);
        Game.register(EntityNuke.class, "Nuke", entityId++, AliensVsPredator.instance(), 250, 4, true);
        Game.register(EntityAPC.class, "APC", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityMechanism.class, "MECHANISM", entityId++, AliensVsPredator.instance(), 250, 16, true);
        Game.register(EntityMedpod.class, "Medpod", entityId++, AliensVsPredator.instance(), 250, 16, true);
        Game.register(EntitySupplyChute.class, "SupplyChute", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntitySupplyChuteMarines.class, "SupplyChuteMarines", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntitySupplyChuteSeegson.class, "SupplyChuteSeegson", entityId++, AliensVsPredator.instance(), 250, 1, true);
    }

    private void registerLivingEntities()
    {
        Game.register(EntityRunnerDrone.class, "RunnerDrone", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityRunnerWarrior.class, "RunnerWarrior", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityDrone.class, "Drone", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityWarrior.class, "Warrior", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntitySpitter.class, "Spitter", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityCrusher.class, "Crusher", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityPraetorian.class, "Praetorian", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityMarine.class, "Marine", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityYautja.class, "Yautja", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityQueen.class, "Queen", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityFacehugger.class, "Facehugger", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityChestburster.class, "Chestbuster", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityOvamorph.class, "Ovamorph", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityRoyalFacehugger.class, "RoyalFacehugger", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityAqua.class, "AquaAlien", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityPredalien.class, "Predalien", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityCombatSynthetic.class, "CombatSynthetic", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityDeacon.class, "Deacon", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityHammerpede.class, "Hammerpede", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityTrilobite.class, "Trilobite", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntitySpaceJockey.class, "SpaceJockey", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityEngineer.class, "Engineer", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityYautjaBerserker.class, "YautjaBerserker", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityDeaconShark.class, "DeaconShark", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityUltramorph.class, "Ultramorph", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityGooMutant.class, "GooMutant", entityId++, AliensVsPredator.instance(), 250, 1, true);
        Game.register(EntityAethon.class, "Aethon", entityId++, AliensVsPredator.instance(), 250, 1, true);
    }

    private void registerSpawns()
    {
        if (AliensVsPredator.settings().areAutoSpawnsEnabled())
        {
            BiomeGenBase[] xenomorphBiomes = this.getFilteredBiomeArray(BiomeGenBase.birchForest, BiomeGenBase.birchForestHills, BiomeGenBase.coldBeach, BiomeGenBase.coldTaiga, BiomeGenBase.coldTaigaHills, BiomeGenBase.desertHills, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.extremeHillsPlus, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.iceMountains, BiomeGenBase.icePlains, BiomeGenBase.jungle, BiomeGenBase.jungleEdge, BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.roofedForest, BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenLV.acheron);

            BiomeGenBase[] predatorBiomes = this.getFilteredBiomeArray(BiomeGenBase.birchForest, BiomeGenBase.birchForestHills, BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.extremeHillsPlus, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.icePlains, BiomeGenBase.jungle, BiomeGenBase.jungleEdge, BiomeGenBase.jungleHills, BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenVarda.vardaBadlands);

            BiomeGenBase[] engineerBiomes = this.getFilteredBiomeArray(BiomeGenBase.iceMountains, BiomeGenBase.coldTaigaHills, BiomeGenBase.coldTaiga, BiomeGenBase.extremeHills, BiomeGenVarda.vardaBadlands, BiomeGenLV.acheron, BiomeGenVarda.vardaForest);

            BiomeGenBase[] aquaXenomorphBiomes = this.getFilteredBiomeArray(BiomeGenBase.river, BiomeGenBase.beach, BiomeGenBase.stoneBeach);

            if (AliensVsPredator.settings().shouldEvolvedXenomorphsSpawn())
            {
                EntityRegistry.addSpawn(EntityAqua.class, 5, 1, 2, EnumCreatureType.monster, aquaXenomorphBiomes);
                EntityRegistry.addSpawn(EntityDrone.class, 50, 1, 3, EnumCreatureType.monster, xenomorphBiomes);
                EntityRegistry.addSpawn(EntityWarrior.class, 20, 1, 3, EnumCreatureType.monster, xenomorphBiomes);
                EntityRegistry.addSpawn(EntityPraetorian.class, 5, 1, 2, EnumCreatureType.monster, xenomorphBiomes);
                EntityRegistry.addSpawn(EntityChestburster.class, 5, 1, 3, EnumCreatureType.monster, xenomorphBiomes);
                EntityRegistry.addSpawn(EntityFacehugger.class, 5, 1, 2, EnumCreatureType.monster, xenomorphBiomes);
            }
            else
            {
                EntityRegistry.addSpawn(EntityFacehugger.class, 30, 1, 2, EnumCreatureType.monster, xenomorphBiomes);
            }

            EntityRegistry.addSpawn(EntityYautja.class, 1, 1, 1, EnumCreatureType.monster, predatorBiomes);
            EntityRegistry.addSpawn(EntityYautjaBerserker.class, 1, 0, 1, EnumCreatureType.monster, predatorBiomes);
            EntityRegistry.addSpawn(EntityMarine.class, 2, 1, 1, EnumCreatureType.creature, new BiomeGenBase[] { BiomeGenBase.swampland, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.plains
            });
            EntityRegistry.addSpawn(EntityEngineer.class, 5, 1, 1, EnumCreatureType.monster, engineerBiomes);
            EntityRegistry.addSpawn(EntitySpaceJockey.class, 2, 1, 1, EnumCreatureType.monster, engineerBiomes);
        }
    }

    public BiomeGenBase[] getFilteredBiomeArray(BiomeGenBase... biomes)
    {
        ArrayList<BiomeGenBase> biomeList = new ArrayList<BiomeGenBase>(Arrays.asList(biomes));

        if (!AliensVsPredator.settings().areOverworldSpawnsEnabled())
        {
            biomeList = this.clearVanillaBiomes(biomeList);
        }

        return (BiomeGenBase[]) Arrays.copyOf(biomeList.toArray(), biomeList.toArray().length, BiomeGenBase[].class);
    }

    private ArrayList<BiomeGenBase> clearVanillaBiomes(ArrayList<BiomeGenBase> biomeList)
    {
        ArrayList<BiomeGenBase> newList = new ArrayList<BiomeGenBase>(biomeList);

        for (BiomeGenBase biome : biomeList)
        {
            if (!(biome instanceof BiomeGenLV))
            {
                newList.remove(biome);
            }
        }

        return newList;
    }
}
