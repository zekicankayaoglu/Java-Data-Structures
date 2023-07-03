package LDLinkedList;
import java.util.AbstractList;
import java.util.List;

public class LDLinkedList<E> extends AbstractList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;
    private int delete_counter;

    public LDLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.delete_counter = 0;
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private boolean lazily_deleted;

        public Node(E data) {
            this.data = data;
            this.next = null;
            this.lazily_deleted = false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current = head;
        int currentIndex = 0;
        while (currentIndex < index) {
            
            currentIndex++;
            current = current.next;
        }
        if(current == null || current.lazily_deleted == true) return null;
        return current.data;
    }
    public boolean checkLazily(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current = head;
        int currentIndex = 0;
        while (currentIndex != index) {
            current = current.next;
            currentIndex++;
        }
        return current.lazily_deleted;
    }
    @Override
    public boolean add(E data) {
        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current = head;
        Node<E> previous = null;
        int currentIndex = 0;

        while (currentIndex != index) {
            previous = current;
            current = current.next;
            currentIndex++;
        }

        current.lazily_deleted = true;
        delete_counter++;
        if (delete_counter == 2) {
            delete();
            delete_counter = 0;
        }

        return current.data;
    }

    private void delete() {
        Node<E> current = head;
        Node<E> previous = null;

        while (current != null) {
            if (current.lazily_deleted) {
                if (previous == null) {
                    head = current.next;
                    current = head;
                } else {
                    previous.next = current.next;
                    current = current.next;
                }
                size--;
            } else {
                previous = current;
                current = current.next;
            }
        }
    }
}

