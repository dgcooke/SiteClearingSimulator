package com.smartphonedev.bulldozer;

import com.smartphonedev.site.Position;
import java.util.Optional;

public sealed class Command permits TurnLeftCommand, TurnRightCommand, AdvanceCommand
{
    private static final String TURN_LEFT_COMMAND = "left";
    private static final String TURN_RIGHT_COMMAND = "right";
    private static final String ADVANCE_COMMAND = "advance";

    protected Command()
    {
    }

    public Optional<Position> performAction(Position position)
    {
        throw new RuntimeException("This needs to be executed from a derived class");
    }

    static public final Optional<Command> parseCommand(String commandString)
    {
        if(commandString != null)
        {
            var commandComponents = commandString.split(" ");
            if(commandComponents.length == 1)
            {
                final var input = Optional.of(commandComponents[0].trim());
                if(isTurnLeftCommand(input))
                {
                    return Optional.of(new TurnLeftCommand());

                }else if(isTurnRightCommand(input))
                {
                    return Optional.of(new TurnRightCommand());
                }
            }else if(commandComponents.length == 2)
            {
                try
                {
                    if(isAdvanceCommand(Optional.of(commandComponents[0].trim())))
                    {
                        var spacesCount = Integer.valueOf(commandComponents[1]);
                        return Optional.of(new AdvanceCommand(spacesCount));
                    }
                }catch (NumberFormatException nfe)
                {
                    return Optional.empty();
                }
            }
        }
        return Optional.empty();
    }

    private static boolean isAdvanceCommand(Optional<String> input)
    {
        if(input.isPresent() && input.get().length() == 1)
        {
            return input.get().chars().mapToObj(c -> (char) c)
                    .map(Character::toLowerCase)
                    .filter(c -> c.compareTo('a') == 0)
                    .toList().size() == 1;
        }else if(input.isPresent())
        {
            return input.get().toLowerCase().compareTo(ADVANCE_COMMAND) == 0;
        }

        return false;
    }

    private static boolean isTurnRightCommand(Optional<String> input)
    {
        if(input.isPresent() && input.get().length() == 1)
        {
            return input.get().chars().mapToObj(c -> (char) c)
                    .map(Character::toLowerCase)
                    .filter(c -> c.compareTo('r') == 0)
                    .toList().size() == 1;
        }else if(input.isPresent())
        {
            return input.get().toLowerCase().compareTo(TURN_RIGHT_COMMAND) == 0;
        }

        return false;
    }

    private static boolean isTurnLeftCommand(Optional<String> input)
    {
        if(input.isPresent() && input.get().length() == 1)
        {
            return input.get().chars().mapToObj(c -> (char) c)
                .map(Character::toLowerCase)
                .filter(c -> c.compareTo('l') == 0)
                .toList().size() == 1;
        }else if(input.isPresent())
        {
            return input.get().toLowerCase().compareTo(TURN_LEFT_COMMAND) == 0;
        }

        return false;
    }
}
