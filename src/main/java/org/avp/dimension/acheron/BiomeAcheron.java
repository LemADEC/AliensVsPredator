package org.avp.dimension.acheron;

import org.avp.AliensVsPredator;
import org.avp.dimension.BiomeGenLV;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeAcheron extends BiomeGenLV
{
    public BiomeAcheron(int biomeId)
    {
        super(biomeId);
        this.topBlock = AliensVsPredator.blocks().terrainUniDirt;
        this.fillerBlock = AliensVsPredator.blocks().terrainUniStone;
        this.setBiomeName(AliensVsPredator.properties().DIMENSION_NAME_ACHERON);
        this.setHeight(new BiomeGenBase.Height(0.1F, 0.4F));
        this.setTemperatureRainfall(0.7F, 0.1F);
        this.setDisableRain();
        this.theBiomeDecorator = new BiomeDecoratorAcheron(this);
    }

    @Override
    public BiomeAcheron setColor(int color)
    {
        this.func_150557_a(color, false);
        return this;
    }
}
