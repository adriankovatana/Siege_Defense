package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;

public class UnitDeployer extends Group{
    protected UnitDeployerCell cell1;
    protected UnitDeployerCell cell2;
    protected UnitDeployerCell cell3;
    protected UnitDeployerCell cell4;

    public UnitDeployer(){
        cell1 = new UnitDeployerCell(Gdx.graphics.getWidth()*0.0555f,
                Gdx.graphics.getHeight()*0.0625f);
        cell2 = new UnitDeployerCell(Gdx.graphics.getWidth()*0.3055f,
                Gdx.graphics.getHeight()*0.0625f);
        cell3 = new UnitDeployerCell(Gdx.graphics.getWidth()*0.5555f,
                Gdx.graphics.getHeight()*0.0625f);
        cell4 = new UnitDeployerCell(Gdx.graphics.getWidth()*0.8055f,
                Gdx.graphics.getHeight()*0.0625f);
        this.addActor(cell1);
        this.addActor(cell2);
        this.addActor(cell3);
        this.addActor(cell4);
    }
}
