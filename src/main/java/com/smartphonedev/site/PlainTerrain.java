package com.smartphonedev.site;

public final class PlainTerrain extends Terrain
{
    @Override
    protected void clear()
    {
        cleared = true;
        visit();
    }

    @Override
    public void enterBlock()
    {
        if(!cleared)
        {
            clear();
        }else
        {
            visit();
        }

    }

    @Override
    public String print()
    {
        return "o ";
    }
}
