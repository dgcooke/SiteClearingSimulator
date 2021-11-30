package com.smartphonedev.bulldozer;

import com.smartphonedev.site.Direction;
import com.smartphonedev.site.Position;
import com.smartphonedev.site.Site;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TurnRightCommandTest
{
    @Test
    protected void performActionResultsInCorrectProposedPosition()
    {
        //given
        final var siteList = Arrays.asList("ooorrrrooo", "ooorrrrooo", "roorrororo", "ororrrroor");
        final var site = Site.configureSite(siteList);
        final var firstPosition = new Position(3,3, Direction.EAST);
        final var secondPosition = new Position(3,3, Direction.SOUTH);
        final var thirdPosition = new Position(3,3, Direction.WEST);
        final var fourthPosition = new Position(3,3, Direction.NORTH);

        //when
        final var command = new TurnRightCommand();
        final var firstProposedPosition = command.performAction(firstPosition);
        final var secondProposedPosition = command.performAction(secondPosition);
        final var thirdProposedPosition = command.performAction(thirdPosition);
        final var fourthProposedPosition = command.performAction(fourthPosition);

        //then
        assertThat(firstProposedPosition).isPresent();
        assertThat(firstProposedPosition.get().direction()).isEqualTo(Direction.SOUTH);

        assertThat(secondProposedPosition).isPresent();
        assertThat(secondProposedPosition.get().direction()).isEqualTo(Direction.WEST);

        assertThat(thirdProposedPosition).isPresent();
        assertThat(thirdProposedPosition.get().direction()).isEqualTo(Direction.NORTH);

        assertThat(fourthProposedPosition).isPresent();
        assertThat(fourthProposedPosition.get().direction()).isEqualTo(Direction.EAST);
    }
}