package com.smartphonedev.bulldozer;

import com.smartphonedev.site.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class AdvanceCommandTest {

    @Test
    protected void performActionFailsIfPassedInvalidInput()
    {
        //given
        var siteList = Arrays.asList("ooorrrrooo", "ooorrrrooo", "roorrororo", "ororrrroor");
        var site = Site.configureSite(siteList);
        var badPosition = new Position(3,5, Direction.EAST);
        var anotherBadPosition = new Position(3,5, Direction.SOUTH);

        //when
        var command = new AdvanceCommand(5);
        var proposedPosition = command.performAction(badPosition);
        var anotherProposedPosition = command.performAction(anotherBadPosition);

        //then
        assertThat(proposedPosition).isEmpty();
        assertThat(anotherProposedPosition).isEmpty();
    }

    @Test
    protected void performActionReturnsPlainTerrainObjectIfPassedValidInput()
    {
        //given
        final var siteList = Arrays.asList("ooorrrrooo", "ooorrrrooo", "roorrororo", "ororrrroor");
        final var site = Site.configureSite(siteList);
        final var position = new Position(0,0, Direction.EAST);
        final var siteInstance = site.get();
        final Class<?> siteClass = siteInstance.getClass();
        try
        {

            final var getTerrainAtPosition = siteClass.getDeclaredMethod("getTerrainAtPosition", Position.class);
            getTerrainAtPosition.setAccessible(true);

            //when
            site.ifPresent(s -> s.printSiteMap());
            final var command = new AdvanceCommand(2);
            final var proposedPosition = command.performAction(position);
            final var result = (PlainTerrain) getTerrainAtPosition.invoke(siteInstance, proposedPosition.get());

            //then
            assertThat(proposedPosition).isPresent();
            assertThat(result).isInstanceOf(PlainTerrain.class);

        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException exception)
        {
            fail();
        }
    }

    @Test
    protected void performActionReturnsRockyTerrainObjectIfPassedValidInput()
    {
        //given
        final var siteList = Arrays.asList("ooorrrrooo", "ooorrrrooo", "roorrororo", "ororrrroor");
        final var site = Site.configureSite(siteList);
        final var position = new Position(0,0, Direction.EAST);

        final var siteInstance = site.get();
        final Class<?> siteClass = siteInstance.getClass();
        try
        {
            final var getTerrainAtPosition = siteClass.getDeclaredMethod("getTerrainAtPosition", Position.class);
            getTerrainAtPosition.setAccessible(true);

            //when
            final var command = new AdvanceCommand(3);
            final var proposedPosition = command.performAction(position);
            final var result = (RockyTerrain) getTerrainAtPosition.invoke(siteInstance, proposedPosition.get());

            //then
            assertThat(proposedPosition).isPresent();
            assertThat(result).isInstanceOf(RockyTerrain.class);

        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException exception)
        {
            fail();
        }
    }


    @Test
    protected void performActionResultsInCorrectFuelUsageReportEastDirection()
    {
        //given
        final var siteList = Arrays.asList("ooorrrrooo", "ooorrrrooo", "roorrororo", "ororrrroor");
        final var site = Site.configureSite(siteList);
        final var position = new Position(0,-1, Direction.EAST);


        //when
        final var command = new AdvanceCommand(6);
        final var proposedPosition = command.performAction(position);
        final var result = site.get().calculateFuelUsaga();

        //then
        assertThat(result).isEqualTo(9);
    }

    @Test
    protected void performActionResultsInCorrectFuelUsageReportSouthDirection()
    {
        //given
        final var siteList = Arrays.asList("ooorrrrooo", "ooorrrrooo", "roorrororo", "ororrrroor");
        final var site = Site.configureSite(siteList);
        final var position = new Position(0,0, Direction.SOUTH);


        //when
        final var moveCommand = new AdvanceCommand(3);
        final var proposedPosition = moveCommand.performAction(position);
        final var result = site.get().calculateFuelUsaga();

        //then
        assertThat(result).isEqualTo(4);
    }



}