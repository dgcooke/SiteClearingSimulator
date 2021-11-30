package com.smartphonedev.bulldozer;

import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CommandTest {

    @Test
    protected void isTurnLeftCommandReturnsTrueIfPassedAnLCharOrLeftString() throws InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        //given
        final var commandClass = Class.forName("com.smartphonedev.bulldozer.Command");
        final var commandInstance = (Command) commandClass.newInstance();

        //when
        final var isTurnLeftCommand = commandClass.getDeclaredMethod("isTurnLeftCommand", Optional.class);
        isTurnLeftCommand.setAccessible(true);
        final var singleResult = (Boolean) isTurnLeftCommand.invoke(commandInstance,Optional.of("L"));
        final var lowerSingleResult = (Boolean) isTurnLeftCommand.invoke(commandInstance,Optional.of("l"));
        final var stringResult = (Boolean) isTurnLeftCommand.invoke(commandInstance,Optional.of("Left"));
        final var wrongResult = (Boolean) isTurnLeftCommand.invoke(commandInstance,Optional.of("Joke"));

        //then
        assertThat(singleResult).isEqualTo(Boolean.TRUE);
        assertThat(lowerSingleResult).isEqualTo(Boolean.TRUE);
        assertThat(stringResult).isEqualTo(Boolean.TRUE);
        assertThat(wrongResult).isEqualTo(Boolean.FALSE);
    }

    @Test
    protected void isTurnRightCommandReturnsTrueIfPassedAnLCharOrRightString() throws InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        //given
        final var commandClass = Class.forName("com.smartphonedev.bulldozer.Command");
        final var commandInstance = (Command) commandClass.newInstance();

        //when
        final var isTurnRightCommand = commandClass.getDeclaredMethod("isTurnRightCommand", Optional.class);
        isTurnRightCommand.setAccessible(true);
        final var singleResult = (Boolean) isTurnRightCommand.invoke(commandInstance,Optional.of("R"));
        final var lowerSingleResult = (Boolean) isTurnRightCommand.invoke(commandInstance,Optional.of("r"));
        final var stringResult = (Boolean) isTurnRightCommand.invoke(commandInstance,Optional.of("Right"));
        final var wrongResult = (Boolean) isTurnRightCommand.invoke(commandInstance,Optional.of("Joke"));

        //then
        assertThat(singleResult).isEqualTo(Boolean.TRUE);
        assertThat(lowerSingleResult).isEqualTo(Boolean.TRUE);
        assertThat(stringResult).isEqualTo(Boolean.TRUE);
        assertThat(wrongResult).isEqualTo(Boolean.FALSE);
    }

    @Test
    protected void isAdvanceCommandReturnsTrueIfPassedAnLCharOrAdvancedString() throws InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        //given
        final var commandClass = Class.forName("com.smartphonedev.bulldozer.Command");
        final var commandInstance = (Command) commandClass.newInstance();

        //when
        final var isAdvanceCommand = commandClass.getDeclaredMethod("isAdvanceCommand", Optional.class);
        isAdvanceCommand.setAccessible(true);

        final var singleResult = (Boolean) isAdvanceCommand.invoke(commandInstance,Optional.of("A"));
        final var lowerSingleResult = (Boolean) isAdvanceCommand.invoke(commandInstance,Optional.of("a"));
        final var stringResult = (Boolean) isAdvanceCommand.invoke(commandInstance,Optional.of("advance"));
        final var wrongResult = (Boolean) isAdvanceCommand.invoke(commandInstance,Optional.of("invalid"));

        //then
        assertThat(singleResult).isEqualTo(Boolean.TRUE);
        assertThat(lowerSingleResult).isEqualTo(Boolean.TRUE);
        assertThat(stringResult).isEqualTo(Boolean.TRUE);
        assertThat(wrongResult).isEqualTo(Boolean.FALSE);
    }

}