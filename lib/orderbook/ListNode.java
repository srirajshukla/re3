package orderbook;


public class ListNode{
    ListNode next;
    ListNode prev;
    public Order order;
    
    public ListNode(ListNode prev, ListNode next, Order order){
        this.prev = prev;
        this.next = next;
        this.order = order;
    }

    @Override
    public String toString(){
        return order.toString();
    }
}