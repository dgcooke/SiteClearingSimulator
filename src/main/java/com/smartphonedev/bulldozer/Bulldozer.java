package com.smartphonedev.bulldozer;

import com.smartphonedev.site.Direction;
import com.smartphonedev.site.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bulldozer
{
    private static final int HOME_COLUMN = -1;
    private static final int HOME_ROW = 0;

    private List<Command> listOfCommands;
    private Position position;
    public Bulldozer()
    {
        listOfCommands = new ArrayList<>();
        position = new Position(HOME_ROW,HOME_COLUMN, Direction.EAST);
    }

    public boolean processCommand(Optional<? extends Command> command)
    {
        if(command.isPresent())
        {
            var passedCommand = command.get();
            var proposedPosition = passedCommand.performAction(position);
            if(proposedPosition.isPresent())
            {
                listOfCommands.add(passedCommand);
                position = proposedPosition.get();
                return true;
            }
        }

        return false;
    }


    public void printCommands() {
        listOfCommands.stream().forEach(command -> System.out.print(command+ ", "));
        System.out.println("quit\n");
    }
}
