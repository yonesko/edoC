package gleb;

/**
 * Created by Glebushka on 11.10.2015.
 */
public abstract class IntGenerator {
    private boolean canceled = false;

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public abstract int next();
}
