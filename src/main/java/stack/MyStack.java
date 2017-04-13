package stack;

import java.util.ArrayList;
import java.util.List;

public class MyStack {
    private List content = new ArrayList();
    private int size;

    public int size() {
        return size;
    }

    public void add(Object o) {
        this.size++;
        this.content.add(o);
    }

    public Object pop() {
        if (this.size == 0) {
            throw new IllegalStateException();
        }

        Object o = this.content.get(--size);
        this.content.remove(size);
        return o;
    }
}
