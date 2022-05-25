package orderbook;


/**
 * RedBlack Tree Node
 * This node will contain the price level and a Double Linked List
 * references to all the orders at that price level.
 */
public class PriceNode<T extends Comparable<T>, OrderType extends Comparable<OrderType>> {
    /** Possible color for this node */
    public static final int BLACK = 0;
    /** Possible color for this node */
    public static final int RED = 1;
	// the key of each node
	public T key;

    // the reference to the double linked list
    public DoubleLinkedList orders;

    /** Parent of node */
    PriceNode<T, OrderType> parent;
    /** Left child */
    PriceNode<T, OrderType> left;
    /** Right child */
    PriceNode<T, OrderType> right;
    // the color of a node
    public int color;

    PriceNode(){
        color = BLACK;
        parent = null;
        left = null;
        right = null;
        orders = new DoubleLinkedList();
    }

	// Constructor which sets key to the argument.
	PriceNode(T key){
        this();
        this.key = key;
	}

    @Override
    public String toString(){
        return "\n\t\tPrice: " + this.key + " [ \n" + this.orders.toString() + " \t\t ]";
    }
}
