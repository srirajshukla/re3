class ListNode<OrderType>{
    int key;
    ListNode<OrderType> next;
    ListNode<OrderType> prev;
    OrderType value;
    
    public ListNode(int key, ListNode<OrderType> prev, ListNode<OrderType> next, OrderType value){
        this.key = key;
        this.prev = prev;
        this.next = next;
        this.value = value;
    }
}

public class DoubleLinkedList<OrderType> {
    ListNode<OrderType> head;
    ListNode<OrderType> tail;

    public DoubleLinkedList(){
        head = null;
        tail = null;
    }

    public ListNode<OrderType> add(int key){
        ListNode<OrderType> newNode = new ListNode<OrderType>(key, null, null, null);

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

    public void remove(int key){
        ListNode<OrderType> curr = head;
        while(curr != null){
            if(curr.key == key){
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
        ListNode<OrderType> curr = head;
        while(curr != null){
            System.out.print(curr.key + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
