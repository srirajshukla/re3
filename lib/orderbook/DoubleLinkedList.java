package orderbook;

import java.util.UUID;

public class DoubleLinkedList {
    public ListNode head;
    public ListNode tail;

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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        ListNode curr = head;
        while(curr != null){
            sb.append("\t\t\t\t");
            sb.append(curr);
            sb.append(", \n");
            curr = curr.next;
        }

        return sb.toString();
    }
}
