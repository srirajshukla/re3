
/**
 * RedBlack Tree Node
 * This node will contain the price level and a Double Linked List
 * references to all the orders at that price level.
 */
class PriceNode<T, OrderType extends Comparable<T>> {
    /** Possible color for this node */
    public static final int BLACK = 0;
    /** Possible color for this node */
    public static final int RED = 1;
	// the key of each node
	public T key;

    // the reference to the double linked list
    public DoubleLinkedList<OrderType> orders;

    /** Parent of node */
    PriceNode<T, OrderType> parent;
    /** Left child */
    PriceNode<T, OrderType> left;
    /** Right child */
    PriceNode<T, OrderType> right;
    // the number of elements to the left of each node
    public int numLeft = 0;
    // the number of elements to the right of each node
    public int numRight = 0;
    // the color of a node
    public int color;

    PriceNode(){
        color = BLACK;
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;
        orders = new DoubleLinkedList<>();
    }

	// Constructor which sets key to the argument.
	PriceNode(T key){
        this();
        this.key = key;
	}
}

public class OrderTree<OrderType> {
}
