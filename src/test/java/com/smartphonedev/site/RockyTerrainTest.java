package com.smartphonedev.site;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RockyTerrainTest {

    @Test
    protected void rockyTerrainShouldIncrementFuelConsumedWhenEnterBlockCalled()
    {
        //given
        var rockyTerrain = new RockyTerrain();

        //when
        rockyTerrain.enterBlock();

        //then
        assertThat(rockyTerrain).isNotNull();
        assertThat(rockyTerrain.getFuelConsumed()).isEqualTo(2);
    }

    @Test
    protected void rockyTerrainShouldIncrementFuelConsumedCorrectlyWhenEnterBlockCalledMultipleTimes()
    {
        //given
        var rockyTerrain = new RockyTerrain();

        //when
        rockyTerrain.enterBlock();
        rockyTerrain.enterBlock();
        rockyTerrain.enterBlock();
        rockyTerrain.enterBlock();

        //then
        assertThat(rockyTerrain).isNotNull();
        assertThat(rockyTerrain.getFuelConsumed()).isEqualTo(5);
    }

}