package io.stuart.ext.collections;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BoundedConcurrentLinkedQueue<E> extends ConcurrentLinkedQueue<E> {

    private static final long serialVersionUID = -7195600488213610106L;

    private final int capacity;

    public BoundedConcurrentLinkedQueue(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean add(E element) {
        if (capacity > 0 && size() >= capacity) {
            // poll the first element
            super.poll();
        }

        // add to the last
        return super.add(element);
    }

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        boolean result = false;

        for (E element : elements) {
            result = add(element);
        }

        return result;
    }

    @Override
    public boolean offer(E element) {
        if (capacity > 0 && size() >= capacity) {
            // poll the first element
            super.poll();
        }

        // add to the last
        return super.offer(element);
    }

    @Override
    public E poll() {
        return super.poll();
    }

    @Override
    public E peek() {
        return super.peek();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public int size() {
        return super.size();
    }

    public int addExt(E element) {
        // poll count
        int count = 0;

        if (capacity > 0 && size() >= capacity) {
            // poll the first element
            super.poll();
            // poll count + 1
            count = 1;
        }

        // add to the last
        if (super.add(element)) {
            // return poll count
            return count;
        } else {
            // return failed
            return -1;
        }
    }

    public int addAllExt(Collection<? extends E> elements) {
        int count = 0;

        for (E element : elements) {
            int result = addExt(element);

            if (result != -1) {
                count += result;
            }
        }

        return count;
    }

    public int offerExt(E element) {
        // poll count
        int count = 0;

        if (capacity > 0 && size() >= capacity) {
            // poll the first element
            super.poll();
            // poll count + 1
            count = 1;
        }

        // add to the last
        if (super.offer(element)) {
            // return poll count
            return count;
        } else {
            // return failed
            return -1;
        }
    }

}
