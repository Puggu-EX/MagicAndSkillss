package org.puggu.magicandskills.clicks;

public class PlayerClick {
    CastClick click;
    long time;

    public PlayerClick(CastClick click, long i) {
        this.click = click;
        time = i;
    }

    public CastClick getClick() {
        return click;
    }
    public long getTime() {
        return time;
    }
}
