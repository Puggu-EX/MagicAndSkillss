package org.puggu.magicandskills.ability.experience;

public class ExperienceUtil {
    public static int calculateTotalXP(int level, float progress) {
        int totalXP = 0;

        if (level <= 16) {
            totalXP = (level * level + 6 * level);
        } else if (level <= 31) {
            totalXP = (5 * level * level - 81 * level + 720) / 2;
        } else {
            totalXP = (9 * level * level - 325 * level + 44440) / 2;
        }

        totalXP += Math.round(progress * getXPToNextLevel(level));
        return totalXP;
    }

    public static int getXPToNextLevel(int level) {
        if (level <= 16) {
            return 2 * level + 7;
        } else if (level <= 31) {
            return 5 * level - 38;
        } else {
            return 9 * level - 158;
        }
    }
}
