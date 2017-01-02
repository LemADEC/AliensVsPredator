package org.avp;

import com.arisux.mdxlib.lib.game.Game;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTab
{
    public static CreativeTabs tabMain        = new CreativeTabs("main")
                                              {
                                                  @Override
                                                  public Item getTabIconItem()
                                                  {
                                                      return AliensVsPredator.items().helmTitanium;
                                                  }

                                                  public String getTranslatedTabLabel()
                                                  {
                                                      return "Main";
                                                  }
                                              };
    public static CreativeTabs tabBlocks      = new CreativeTabs("blocks")
                                              {
                                                  @Override
                                                  public Item getTabIconItem()
                                                  {
                                                      return Game.getItem(AliensVsPredator.blocks().blockEngineerShipColumn1);
                                                  }

                                                  public String getTranslatedTabLabel()
                                                  {
                                                      return "Blocks";
                                                  }
                                              };
    public static CreativeTabs tabEntities    = new CreativeTabs("summoners")
                                              {
                                                  @Override
                                                  public Item getTabIconItem()
                                                  {
                                                      return AliensVsPredator.items().itemSummonerFacehugger;
                                                  }

                                                  public String getTranslatedTabLabel()
                                                  {
                                                      return "Summoners";
                                                  }
                                              };
    public static CreativeTabs tabGunParts    = new CreativeTabs("gunparts")
                                              {
                                                  @Override
                                                  public Item getTabIconItem()
                                                  {
                                                      return AliensVsPredator.items().itemM41A;
                                                  }

                                                  public String getTranslatedTabLabel()
                                                  {
                                                      return "Gun Components";
                                                  }
                                              };
    public static CreativeTabs tabRecipeItems = new CreativeTabs("recipeitems")
                                              {
                                                  @Override
                                                  public Item getTabIconItem()
                                                  {
                                                      return AliensVsPredator.items().itemLedDisplay;
                                                  }

                                                  public String getTranslatedTabLabel()
                                                  {
                                                      return "Recipe Items";
                                                  }
                                              };
}
