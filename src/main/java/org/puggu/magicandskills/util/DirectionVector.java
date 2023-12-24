package org.puggu.magicandskills.util;

import org.bukkit.util.Vector;

public class DirectionVector {
    public static Vector getDirectionVector(float pitch, float yaw) {
        Vector direction = new Vector();

        double pitchRadians = Math.toRadians(-pitch);
        double yawRadians = Math.toRadians(-yaw);

        direction.setY(Math.sin(pitchRadians));
        double h = Math.cos(pitchRadians);
        direction.setX(h * Math.sin(yawRadians));
        direction.setZ(h * Math.cos(yawRadians));

        return direction;
    }
}

