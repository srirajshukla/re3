
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

    public void remove(PriceNode<Integer, OrderType> v){
        PriceNode<Integer, OrderType> z = search(v.key);

        PriceNode<Integer, OrderType> x = nil;
        PriceNode<Integer, OrderType> y = nil;

        if (isNil(z.left) || isNil(z.right)) {
            y = z;
        } else {
            y = treeSuccessor(z);
        }

        if (!isNil(y.left)){
            x = y.left;
        } else{
            x = y.right;
        }

        // link x's parent to y's parent
        x.parent = y.parent;

        // If y's parent is nil, then x is the root
        if (isNil(y.parent))
            root = x;

        // else if y is a left child, set x to be y's left sibling
        else if (!isNil(y.parent.left) && y.parent.left == y)
            y.parent.left = x;

        // else if y is a right child, set x to be y's right sibling
        else if (!isNil(y.parent.right) && y.parent.right == y)
            y.parent.right = x;

        // if y != z, transfer y's satellite data into z.
        if (y != z){
            z.key = y.key;
        }

        // If y's color is black, it is a violation of the
        // PriceNode properties so call removeFixup()
        if (y.color == PriceNode.BLACK)
            removeFixup(x);
    }

    private void removeFixup(PriceNode<Integer,OrderType> x) {
        PriceNode<Integer, OrderType> w;

        // While we haven't fixed the tree completely...
        while (x != root && x.color == PriceNode.BLACK){

            // if x is its parent's left child
            if (x == x.parent.left){

                // set w = x's sibling
                w = x.parent.right;

                // Case 1, w's color is red.
                if (w.color == PriceNode.RED){
                    w.color = PriceNode.BLACK;
                    x.parent.color = PriceNode.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }

                // Case 2, both of w's children are black
                if (w.left.color == PriceNode.BLACK &&
                        w.right.color == PriceNode.BLACK){
                    w.color = PriceNode.RED;
                    x = x.parent;
                }
                // Case 3 / Case 4
                else{
                    // Case 3, w's right child is black
                    if (w.right.color == PriceNode.BLACK){
                        w.left.color = PriceNode.BLACK;
                        w.color = PriceNode.RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    // Case 4, w = black, w.right = red
                    w.color = x.parent.color;
                    x.parent.color = PriceNode.BLACK;
                    w.right.color = PriceNode.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            }
            // if x is its parent's right child
            else{

                // set w to x's sibling
                w = x.parent.left;

                // Case 1, w's color is red
                if (w.color == PriceNode.RED){
                    w.color = PriceNode.BLACK;
                    x.parent.color = PriceNode.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }

                // Case 2, both of w's children are black
                if (w.right.color == PriceNode.BLACK &&
                        w.left.color == PriceNode.BLACK){
                    w.color = PriceNode.RED;
                    x = x.parent;
                }

                // Case 3 / Case 4
                else{
                    // Case 3, w's left child is black
                    if (w.left.color == PriceNode.BLACK){
                        w.right.color = PriceNode.BLACK;
                        w.color = PriceNode.RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }

                    // Case 4, w = black, and w.left = red
                    w.color = x.parent.color;
                    x.parent.color = PriceNode.BLACK;
                    w.left.color = PriceNode.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }// end while

        // set x to black to ensure there is no violation of
        // RedBlack tree Properties
        x.color = PriceNode.BLACK;
    }

    public PriceNode<Integer,OrderType> treeSuccessor(PriceNode<Integer,OrderType> x) {
        if (!isNil(x.left))
            return treeMinimum(x.right);

        PriceNode<Integer, OrderType> y = x.parent;

        while(!isNil(y) && x == y.right){
            x = y;
            y = y.parent;
        }
        return y;
    }

    public PriceNode<Integer,OrderType> treeMinimum(PriceNode<Integer,OrderType> node) {
        while(!isNil(node.left))
            node = node.left;
        return node;
    }

    public PriceNode<Integer,OrderType> treeMaximum(PriceNode<Integer,OrderType> node) {
        while(!isNil(node.right))
            node = node.right;
        return node;
    }

    public PriceNode<Integer,OrderType> search(Integer key) {
        PriceNode<Integer, OrderType> current = root;
        while(!isNil(current)){
            if (current.key.equals(key)){
                return current;
            } else if (current.key.compareTo(key)<0) {
                current = current.right;
            } else{
                current = current.left;
            }
        }
        return null;
    }

    private boolean isNil(PriceNode<Integer,OrderType> node) {
        return node == nil;
    }
}
