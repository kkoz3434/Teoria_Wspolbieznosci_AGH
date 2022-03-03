import java.util.Objects;
import java.util.concurrent.Semaphore;

public class MyLinkedList {
    Semaphore listSemaphore;
    ListNode guard;

    public MyLinkedList() {
        guard = new ListNode("Guard");
        listSemaphore = new Semaphore(1);
    }

    public boolean contains(String toFind) {
        ListNode current = guard;
        boolean result = false;

        current.lock();

        while (current.next != null && !result) {
            current.next.lock();
            if (current.nodeName.equals(toFind)) {
                result = true;
                //System.out.println("List contains: " + toFind);
            }
            current.semaphore.release();
            current = current.next;
        }
        current.unlock();
        return result;
    }

    public boolean remove(String toRemove) {
        ListNode previous = guard;
        previous.lock();
        ListNode current = previous.next;

        boolean result = false;

        while (current != null) {
            current.lock();
            if (current.nodeName.equals(toRemove)) {
                result = true;
                previous.next = current.next;
                current.unlock();
                System.out.println("Removed: " + toRemove);
                break;
            }
            previous.unlock();
            previous = current;
            current = current.next;
        }

        previous.unlock();

        return result;
    }

    public boolean add(String toAdd) {
        ListNode current = guard;
        current.lock();

        while (current.next != null) {
            current.next.lock();
            current.unlock();
            current = current.next;
        }
        ListNode newNode = new ListNode(toAdd);
        current.next = newNode;
        newNode.next = null;
        current.unlock();
        System.out.println("  Added: " + toAdd);

        return true;
    }

    public void printList() {
        ListNode current = guard;
        System.out.print("| ");
        current.lock();
        while (current.next != null) {
            current.next.lock();
            System.out.print(current.next + " | ");
            current.unlock();
            current = current.next;
        }
        System.out.println();
        current.unlock();
    }

    public int countElements() {
        ListNode current = guard;
        current.lock();
        int counter = 0;
        while (current.next != null) {
            current.next.lock();
            counter++;
            current.unlock();
            current = current.next;
        }
        current.unlock();
        return counter;
    }
}

