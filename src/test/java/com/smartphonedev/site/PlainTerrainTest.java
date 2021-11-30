package com.smartphonedev.site;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlainTerrainTest {

    @Test
    protected void rockyTerrainShouldIncrementFuelConsumedWhenEnterBlockCalled()
    {
        //given
        var plainTerrain = new PlainTerrain();

        //when
        plainTerrain.enterBlock();

        //then
        assertThat(plainTerrain).isNotNull();
        assertThat(plainTerrain.getFuelConsumed()).isEqualTo(1);
    }

    @Test
    protected void plainTerrainShouldIncrementFuelConsumedCorrectlyWhenEnterBlockCalledMultipleTimes()
    {
        //given
        var plainTerrain = new PlainTerrain();

        //when
        plainTerrain.enterBlock();
        plainTerrain.enterBlock();
        plainTerrain.enterBlock();
        plainTerrain.enterBlock();

        //then
        assertThat(plainTerrain).isNotNull();
        assertThat(plainTerrain.getFuelConsumed()).isEqualTo(4);
    }

}