package beans;

import entities.Watch;

import java.util.List;

public class WatchBean {
    private List<Watch> watches;
    private Watch watch;

    public List<Watch> getWatches() {
        return watches;
    }

    public void setWatches(List<Watch> watches) {
        this.watches = watches;
    }

    public Watch getWatch() {
        return watch;
    }

    public void setWatch(Watch watch) {
        this.watch = watch;
    }
}
