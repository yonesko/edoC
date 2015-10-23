package gleb;

/**
 * Created by Glebushka on 11.10.2015.
 */
public class EvenGenerator extends IntGenerator {
    private Integer val = new Integer(0);

    @Override
    synchronized public int next() {
        val++; Thread.currentThread().yield();
        ++val;
        return val;
    }
}
