package com.example.h_mamytov.newflickrphotogallery;

import android.util.Log;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;


public final class BlockingLifoQueue<T> implements BlockingQueue<T> {

    private static final String TAG = "BlockingLifoQueue";

    // we add and remove only from the end of the queue
    private final BlockingDeque<T> deque;

    public BlockingLifoQueue() {
        deque = new LinkedBlockingDeque<T>();
    }

    public boolean add(T e) {
        deque.addLast(e);
        Log.i(TAG, "in add method");
        return true;
    }

    public boolean contains(Object o){
        return deque.contains(o);
    }

    public int drainTo(Collection<? super T> c) {
        return deque.drainTo(c);
    }

    public int drainTo(Collection<? super T> c, int maxElements) {
        return deque.drainTo(c,maxElements);
    }

    public boolean offer(T e) {
        Log.i(TAG, "in offer method");
        return deque.offerFirst(e);
    }

    public boolean offer(T e, long timeout, TimeUnit unit)
            throws InterruptedException {
        Log.i(TAG, "in offer3 method");
        return deque.offerLast(e,timeout,unit);
    }

    public T poll(long timeout, TimeUnit unit) throws InterruptedException {
        Log.i(TAG, "in poll method");
        return deque.pollLast(timeout, unit);
    }

    public void put(T e) throws InterruptedException {
        Log.i(TAG, "in put method");
        deque.putFirst(e);
    }

    public int remainingCapacity() {
        return deque.size();
    }

    public boolean remove(Object o) {
        return deque.remove(o);
    }

    public T take() throws InterruptedException {
        Log.i(TAG, "in take method");
        return deque.takeFirst();
    }

    public T element()
    {
        if (deque.isEmpty()) {
            throw new NoSuchElementException("empty stack");
        }

        return deque.pollLast();
    }

    public T peek() {
        Log.i(TAG, "in peek method");
        return deque.peekLast();
    }

    public T poll() {
        Log.i(TAG, "in poll2 method");
        return deque.pollLast();
    }

    public T remove() {
        if (deque.isEmpty()) {
            throw new NoSuchElementException("empty stack");
        }

        return deque.pollLast();
    }

    public boolean addAll(Collection<? extends T> c) {
        for (T e : c) { deque.add(e);
        }
        return true;
    }

    public void clear() {
        deque.clear();
    }

    public boolean containsAll(Collection<?> c) {
        return deque.containsAll(c);
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public Iterator<T> iterator() {
        return deque.descendingIterator();
    }

    public boolean removeAll(Collection<?> c) {
        return deque.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return deque.retainAll(c);
    }

    public int size() {
        return deque.size();
    }

    public Object[] toArray() {
        return deque.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return deque.toArray(a);
    }
}
