package com.smartphonedev.site;

abstract class Terrain
{
    protected Integer fuelConsumed = 0;
    protected boolean cleared = false;
    abstract protected void clear();

    protected void visit()
    {
        fuelConsumed++;
    }

    public final Integer getFuelConsumed(){return fuelConsumed;}

    abstract public void enterBlock();

    abstract public String print();


}
