package com.smartphonedev.bulldozer;

import com.smartphonedev.site.Direction;
import com.smartphonedev.site.Position;
import java.util.Optional;

public final class TurnRightCommand extends Command{
    public Optional<Position> performAction(Position position)
    {
        var currentDirection = switch (position.direction()) {
            case EAST -> Direction.SOUTH;
            case NORTH -> Direction.EAST;
            case WEST -> Direction.NORTH;
            case SOUTH -> Direction.WEST;
        };

        return Optional.of(new Position(position.row(), position.column(), currentDirection));
    }

    @Override
    public String toString() {
        return "turn right";
    }
}
