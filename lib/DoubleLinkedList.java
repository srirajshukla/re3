class ListNode{
    int key;
    ListNode next;
    ListNode prev;
    public ListNode(int key, ListNode prev, ListNode next){
        this.key = key;
        this.prev = prev;
        this.next = next;
    }
}

public class DoubleLinkedList {
    ListNode head;
    ListNode tail;

    public DoubleLinkedList(){
        head = null;
        tail = null;
    }

    public ListNode add(int key){
        ListNode newNode = new ListNode(key, null, null);

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
        ListNode curr = head;
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
        ListNode curr = head;
        while(curr != null){
            System.out.print(curr.key + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
