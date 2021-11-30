package com.smartphonedev.site;

public final class RockyTerrain extends Terrain
{

    @Override
    protected void clear() {
        cleared = true;
        fuelConsumed = fuelConsumed + 2;
    }

    @Override
    public void enterBlock() {
        if(cleared)
        {
            visit();
        }else
        {
            clear();
        }
    }

    @Override
    public String print()
    {
        return "r ";
    }
}
