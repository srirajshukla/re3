import java.util.UUID;

class ListNode{
    ListNode next;
    ListNode prev;
    Order order;
    
    public ListNode(ListNode prev, ListNode next, Order order){
        this.prev = prev;
        this.next = next;
        this.order = order;
    }
}

public class DoubleLinkedList {
    ListNode head;
    ListNode tail;

    public DoubleLinkedList(){
        head = null;
        tail = null;
    }

    public ListNode add(Order order){
        ListNode newNode = new ListNode(null, null, order);

        if(head == null){
            head = newNode;
            tail = newNode;
        }
        else{
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }

        return newNode;
    }

    public void remove(UUID orderID){
        ListNode curr = head;
        while(curr != null){
            if(curr.order.isEqual(orderID)){
                if(curr == head){
                    head = curr.next;
                }
                if(curr == tail){
                    tail = curr.prev;
                }
                if(curr.prev != null){
                    curr.prev.next = curr.next;
                }
                if(curr.next != null){
                    curr.next.prev = curr.prev;
                }
                break;
            }
            curr = curr.next;
        }
    }

    public void print(){
        ListNode curr = head;
        while(curr != null){
            System.out.print(curr.order + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
