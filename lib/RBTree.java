
/**
 * RedBlack Tree Node
 * This node will contain the price level and a Double Linked List
 * reference to all the orders at that price level.
 */
class RedBlackNode<T, OrderType extends Comparable<T>> {
    /** Possible color for this node */
    public static final int BLACK = 0;
    /** Possible color for this node */
    public static final int RED = 1;
	// the key of each node
	public T key;

    // the reference to the double linked list
    public DoubleLinkedList<OrderType> orders;

    /** Parent of node */
    RedBlackNode<T, OrderType> parent;
    /** Left child */
    RedBlackNode<T, OrderType> left;
    /** Right child */
    RedBlackNode<T, OrderType> right;
    // the number of elements to the left of each node
    public int numLeft = 0;
    // the number of elements to the right of each node
    public int numRight = 0;
    // the color of a node
    public int color;

    RedBlackNode(){
        color = BLACK;
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;
        orders = new DoubleLinkedList<OrderType>();
    }

	// Constructor which sets key to the argument.
	RedBlackNode(T key){
        this();
        this.key = key;
	}
}

public class RBTree<OrderType> {
    
}
