package com.smartphonedev.bulldozer;

import com.smartphonedev.site.Direction;
import com.smartphonedev.site.Position;
import com.smartphonedev.site.Site;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BulldozerTest {

    @Test
    protected void processCommandShouldReturnTrueIfValidCommandPassed()
    {
        //given
        var siteList = Arrays.asList("ooorrrrooo", "ooorrrrooo", "roorrororo", "ororrrroor");
        var site = Site.configureSite(siteList);
        final var bulldozer = new Bulldozer();
        final var position = new Position(0, -1, Direction.EAST);

        //when
        final var command = new AdvanceCommand(3);
        final var result = bulldozer.processCommand(Optional.of(command));

        //then
        assertThat(result).isTrue();
    }

    @Test
    protected void processCommandShouldReturnFalseIfInvalidCommandPassed()
    {
        //given
        var siteList = Arrays.asList("ooorrrrooo", "ooorrrrooo", "roorrororo", "ororrrroor");
        var site = Site.configureSite(siteList);
        final var bulldozer = new Bulldozer();

        //when
        final var command = new AdvanceCommand(13);
        final var result = bulldozer.processCommand(Optional.ofNullable(command));

        //then
        assertThat(result).isFalse();
    }

    @Test
    protected void processCommandShouldReturnFalseIfNullCommandPassed()
    {
        //given
        var siteList = Arrays.asList("ooorrrrooo", "ooorrrrooo", "roorrororo", "ororrrroor");
        var site = Site.configureSite(siteList);
        final var bulldozer = new Bulldozer();

        //when
        final var command = (Command) null;
        final var result = bulldozer.processCommand(Optional.ofNullable(command));

        //then
        assertThat(result).isFalse();
    }
}