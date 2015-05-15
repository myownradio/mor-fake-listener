package service.entities;

/**
 * Created by Roman on 15.05.2015.
 */
public class Item<T> {

    private T i;

    public Item(T i) {
        this.i = i;
    }

    public T getI() {
        return i;
    }

    public void setI(T i) {
        this.i = i;
    }
}
