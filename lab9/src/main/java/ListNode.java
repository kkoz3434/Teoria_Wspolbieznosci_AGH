import java.util.Objects;
import java.util.concurrent.Semaphore;

public class ListNode {
    String nodeName;
    Semaphore semaphore;
    ListNode next;

    public ListNode(String nodeName) {
        this.nodeName = nodeName;
        this.semaphore = new Semaphore(1);
        this.next =null;
    }
    void unlock(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void lock(){
        semaphore.release();
    }
    @Override
    public boolean equals(Object o){
        ListNode node = (ListNode) o;
        return Objects.equals(node.nodeName, this.nodeName);
    }

    @Override
    public String toString(){
        return nodeName;
    }
}
