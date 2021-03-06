package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import GU.info.Models;

public class ModelTest extends ModelBase {

    private IModelCustom model;

    public ModelTest() {

        model = AdvancedModelLoader.loadModel(Models.CRYSTAL_ITEM);
    }

    public void render() {

        model.renderAll();
    }
}