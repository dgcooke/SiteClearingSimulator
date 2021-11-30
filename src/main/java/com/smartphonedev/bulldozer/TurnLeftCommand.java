package com.smartphonedev.bulldozer;

import com.smartphonedev.site.Direction;
import com.smartphonedev.site.Position;
import java.util.Optional;

public final class TurnLeftCommand extends Command
{
    public Optional<Position> performAction(Position position)
    {
        var currentDirection = switch (position.direction()) {
            case EAST -> Direction.NORTH;
            case NORTH -> Direction.WEST;
            case WEST -> Direction.SOUTH;
            case SOUTH -> Direction.EAST;
        };
        return Optional.of(new Position(position.row(), position.column(), currentDirection));
    }

    @Override
    public String toString() {
        return "turn left";
    }
}
