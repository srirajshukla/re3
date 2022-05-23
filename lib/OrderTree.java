
/**
 * RedBlack Tree Node
 * This node will contain the price level and a Double Linked List
 * references to all the orders at that price level.
 */
class PriceNode<T extends Comparable<T>, OrderType extends Comparable<OrderType>> {
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
    // the color of a node
    public int color;

    PriceNode(){
        color = BLACK;
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

public class OrderTree<OrderType extends Comparable<OrderType>> {
    private PriceNode<Integer, OrderType> nil = new PriceNode<>();
    PriceNode<Integer, OrderType> root = nil;

    public OrderTree(){
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }

    private void leftRotate(PriceNode<Integer, OrderType> x){
        PriceNode<Integer, OrderType> y = x.right;
        x.right = y.left;

        if (!isNil(y.left)){
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (isNil(x.parent)) {
            root = y;
        }

        else if (x.parent.left == x){
            x.parent.left = y;
        }

        else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rightRotate(PriceNode<Integer, OrderType> y){
        PriceNode<Integer, OrderType> x = y.left;
        y.left = x.right;

        if (!isNil(x.right)){
            x.right.parent = y;
        }
        x.parent = y.parent;

        // y.parent is nil
        if (isNil(y.parent))
            root = x;

        // y is a right child of its parent.
        else if (y.parent.right == y)
            y.parent.right = x;

        // y is a left child of its parent.
        else
            y.parent.left = x;
        x.right = y;

        y.parent = x;
    }

    public void insert(Integer price){
        insert(new PriceNode<>(price));
    }

    private void insert(PriceNode<Integer, OrderType> z){
        PriceNode<Integer, OrderType> y = nil;
        PriceNode<Integer, OrderType> x = root;


        // While we haven't reached the end of the tree keep
        // trying to figure out where z should go
        while (!isNil(x)){
            y = x;

            // if z.key is < than the current key, go left
            if (z.key.compareTo(x.key) < 0){
                x = x.left;
            }

            // else z.key >= x.key so go right.
            else{
                x = x.right;
            }
        }
        // y will hold z's parent
        z.parent = y;

        // Depending on the value of y.key, put z as the left or
        // right child of y
        if (isNil(y))
            root = z;
        else if (z.key.compareTo(y.key) < 0)
            y.left = z;
        else
            y.right = z;

        // Initialize z's children to nil and z's color to red
        z.left = nil;
        z.right = nil;
        z.color = PriceNode.RED;

        // Call insertFixup(z)
        insertFixup(z);

    }

    private void insertFixup(PriceNode<Integer,OrderType> z) {
        PriceNode<Integer, OrderType> y = nil;

        while (z.parent.color == PriceNode.RED){

            // If z's parent is the left child of its parent.
            if (z.parent == z.parent.parent.left){

                // Initialize y to z 's cousin
                y = z.parent.parent.right;

                // Case 1: if y is red...recolor
                if (y.color == PriceNode.RED){
                    z.parent.color = PriceNode.BLACK;
                    y.color = PriceNode.BLACK;
                    z.parent.parent.color = PriceNode.RED;
                    z = z.parent.parent;
                }
                // Case 2: if y is black & z is a right child
                else if (z == z.parent.right){

                    // leftRotate around z's parent
                    z = z.parent;
                    leftRotate(z);
                }

                // Case 3: else y is black & z is a left child
                else{
                    // recolor and rotate round z's grandpa
                    z.parent.color = PriceNode.BLACK;
                    z.parent.parent.color = PriceNode.RED;
                    rightRotate(z.parent.parent);
                }
            }

            // If z's parent is the right child of its parent.
            else{
                // Initialize y to z's cousin
                y = z.parent.parent.left;

                // Case 1: if y is red...recolor
                if (y.color == PriceNode.RED){
                    z.parent.color = PriceNode.BLACK;
                    y.color = PriceNode.BLACK;
                    z.parent.parent.color = PriceNode.RED;
                    z = z.parent.parent;
                }

                // Case 2: if y is black and z is a left child
                else if (z == z.parent.left){
                    // rightRotate around z's parent
                    z = z.parent;
                    rightRotate(z);
                }
                // Case 3: if y  is black and z is a right child
                else{
                    // recolor and rotate around z's grandpa
                    z.parent.color = PriceNode.BLACK;
                    z.parent.parent.color = PriceNode.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        // Color root black at all times
        root.color = PriceNode.BLACK;
    }


    private boolean isNil(PriceNode<Integer,OrderType> node) {
        return node == nil;
    }

}
