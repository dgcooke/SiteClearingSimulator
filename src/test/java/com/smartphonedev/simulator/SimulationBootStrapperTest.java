package com.smartphonedev.simulator;

import com.smartphonedev.exceptions.InvalidMapException;
import com.smartphonedev.exceptions.MissingMapException;
import com.smartphonedev.exceptions.SimulationException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SimulationBootStrapperTest
{

    @Test
    @Disabled
    protected void bootStrapperFailsIfMapIsNotProvided()
    {
        //given
        Executable executable = () -> new SimulationBootStrapper(null);
        //when
        //then
        assertThrows(MissingMapException.class ,executable);
    }

    @Mock
    InputStream inputStream;

    @Mock
    BufferedReader bufferedReader;

    @Test
    @Disabled
    protected void bootStrapFailsIfEveryRowDoesNotHaveTheSameNumberOfTiles() throws IOException, MissingMapException {

        //given
        var args = new String[]{"mysite.txt"};
        var simulatorBootstrap = new SimulationBootStrapper(args);
        when(bufferedReader.readLine()).thenReturn("oooooo").thenReturn("rrrrrrrr");

        //when
        Executable executable = () -> simulatorBootstrap.startSimulation(inputStream);

        //then
        assertThrows(InvalidMapException.class ,executable);

    }


}