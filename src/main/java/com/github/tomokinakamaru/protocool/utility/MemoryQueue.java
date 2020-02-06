package com.github.tomokinakamaru.protocool.utility;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MemoryQueue<E> implements Queue<E> {

  private final LinkedList<E> elements = new LinkedList<>();

  private final Set<E> queuedElements = new HashSet<>();

  public MemoryQueue(Collection<? extends E> c) {
    addAll(c);
  }

  @Override
  public int size() {
    return elements.size();
  }

  @Override
  public boolean isEmpty() {
    return elements.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return elements.contains(o);
  }

  @Override
  public Iterator<E> iterator() {
    return elements.iterator();
  }

  @Override
  public Object[] toArray() {
    return elements.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return elements.toArray(a);
  }

  @Override
  public boolean add(E t) {
    if (queuedElements.add(t)) {
      return elements.add(t);
    }
    return false;
  }

  @Override
  public boolean remove(Object o) {
    return elements.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return elements.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    boolean changed = false;
    for (E e : c) {
      if (add(e)) {
        changed = true;
      }
    }
    return changed;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return elements.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return elements.retainAll(c);
  }

  @Override
  public void clear() {
    elements.clear();
  }

  @Override
  public boolean offer(E t) {
    return add(t);
  }

  @Override
  public E remove() {
    return elements.remove();
  }

  @Override
  public E poll() {
    return elements.poll();
  }

  @Override
  public E element() {
    return elements.element();
  }

  @Override
  public E peek() {
    return elements.peek();
  }
}
