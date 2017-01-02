package org.avp.dimension.varda;

import org.avp.AliensVsPredator;
import org.avp.dimension.BiomeGenLV;
import org.avp.entities.mob.EntityDeacon;
import org.avp.entities.mob.EntityDeaconShark;
import org.avp.entities.mob.EntityEngineer;
import org.avp.entities.mob.EntityHammerpede;
import org.avp.entities.mob.EntityTrilobite;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenVarda extends BiomeGenLV
{
    public static BiomeGenVarda vardaBadlands = new BiomeGenVarda(AliensVsPredator.settings().biomeIdVardaBadlands()).setColor(0x353825);
    public static BiomeGenVarda vardaForest   = new BiomeVardaForest(AliensVsPredator.settings().biomeIdVardaForest()).setColor(0x353825);

    public BiomeGenVarda(int biomeId)
    {
        super(biomeId);
        this.topBlock = AliensVsPredator.blocks().terrainUniDirt;
        this.fillerBlock = AliensVsPredator.blocks().terrainUniStone;
        this.setBiomeName(AliensVsPredator.properties().BIOME_NAME_VARDA_BADLANDS);
        this.setHeight(new BiomeGenBase.Height(1.0F, 8.0F));
        this.setTemperatureRainfall(0.7F, 0.1F);
        this.setDisableRain();
        this.theBiomeDecorator = new BiomeDecoratorVarda(this);
        this.waterColorMultiplier = 0xFFFF66;
        this.addVardaSpawns();
    }
    
    public void addVardaSpawns()
    {
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableWaterCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityDeaconShark.class, 10, 1, 1));
        this.spawnableCaveCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHammerpede.class, 10, 1, 3));
        this.spawnableCaveCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityDeacon.class, 1, 0, 1));
        this.spawnableCaveCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityEngineer.class, 1, 0, 1));
        this.spawnableCaveCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityTrilobite.class, 1, 0, 1));
    }

    @Override
    public BiomeGenVarda setColor(int color)
    {
        this.func_150557_a(color, false);
        return this;
    }
}
