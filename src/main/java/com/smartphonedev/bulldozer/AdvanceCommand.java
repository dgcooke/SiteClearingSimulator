package com.smartphonedev.bulldozer;

import com.smartphonedev.site.Position;
import com.smartphonedev.site.Site;
import java.util.Optional;

public final class AdvanceCommand extends Command
{
    private static final int MOVE_BY_COUNT = 1;
    private final Integer spacesToMove;
    public AdvanceCommand(Integer spaces)
    {
        spacesToMove = spaces;
    }

    public Optional<Position> performAction(Position position)
    {
        var currentPosition = Optional.of(position);
        boolean continueProcessing = true;
        var actionCounter = 0;
        while((actionCounter < spacesToMove) && (continueProcessing))
        {
            currentPosition = incrementPosition(currentPosition.get());
            if(currentPosition.isEmpty())
            {
                continueProcessing = false;
            }
            actionCounter++;
        }
        return currentPosition;
    }

    private Optional<Position> incrementPosition(Position position) {
        var proposedPosition = switch (position.direction()) {
            case EAST -> new Position(position.row(), position.column() + MOVE_BY_COUNT, position.direction());
            case NORTH -> new Position(position.row() - MOVE_BY_COUNT, position.column(), position.direction());
            case WEST -> new Position(position.row(), position.column() - MOVE_BY_COUNT, position.direction());
            case SOUTH -> new Position(position.row() + MOVE_BY_COUNT, position.column(), position.direction());
        };

        var site = Site.getInstance();
        if (site.map(s -> s.validatePosition(proposedPosition)).orElseGet(() -> false))
        {
            return Optional.of(proposedPosition);
        } else
        {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return "advance " + spacesToMove;
    }
}
