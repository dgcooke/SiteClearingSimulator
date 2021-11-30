package com.smartphonedev.simulator;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SimulationBootStrapperTest
{

    @Test
    protected void bootStrapFailsIfMapIsNotProvided()
    {
        //given
        final var strap =  new SimulationBootStrapper(null);
        //when
        //then
        assertThat(strap.getSite()).isEmpty();
    }

    @Test
    protected void startSimulationCreatesSiteIfPassedValidInput()
    {
        //given
        var details = "ooorrrrooo\nooorrrrooo\nroorrororo\nororrrroor\n";
        var stream = new ByteArrayInputStream(details.getBytes(StandardCharsets.UTF_8));

        //when
        final var strap =  new SimulationBootStrapper(null);
        strap.startSimulation(stream);

        //then
        assertThat(strap.getSite()).isPresent();
    }

    @Test
    protected void startSimulationCreatesNoSiteIfPassedInvalidInput()
    {
        //given
        var details = "ooorrrrooo\nooorrrrooo\nroorrororo\nororrrror\n";
        var stream = new ByteArrayInputStream(details.getBytes(StandardCharsets.UTF_8));

        //when
        final var strap =  new SimulationBootStrapper(null);
        strap.startSimulation(stream);

        //then
        assertThat(strap.getSite()).isEmpty();
    }
}