package in.co.hayden.WebScraper.Product;

import java.util.LinkedList;

class BSTNode {
    LinkedList<Product> products;
    double comparisonDetails;
    BSTNode left, right;

    public BSTNode(Product product) {
        this.products = new LinkedList<>();
        this.products.add(product);
        this.comparisonDetails = convertComparisonDetails(product.getProductComparisonDetails());
        this.left = this.right = null;
    }

    private double convertComparisonDetails(String comparisonDetails) {
        try {
            return Double.parseDouble(comparisonDetails);
        } catch (NumberFormatException e) {
            // Handle the case where the comparison details cannot be parsed as a double
            // You can provide a default value or throw an exception based on your
            // requirements
            return 0.0; // Default value
        }
    }

}

public class BestDealBST {
    private BSTNode root;

    public BestDealBST() {
        this.root = null;
    }

    public void insert(Product product) {
        root = insert(root, product);
    }

    private BSTNode insert(BSTNode node, Product product) {
        if (node == null) {
            return new BSTNode(product);
        }

        double productValue = convertComparisonDetails(product.getProductComparisonDetails());
        if (productValue == node.comparisonDetails) {
            // Same comparison details, add to the linked list in the node
            node.products.add(product);
        } else if (productValue < node.comparisonDetails) {
            node.left = insert(node.left, product);
        } else {
            node.right = insert(node.right, product);
        }

        return node;
    }

    private double convertComparisonDetails(String comparisonDetails) {
        try {
            return Double.parseDouble(comparisonDetails);
        } catch (NumberFormatException e) {
            // Handle the case where the comparison details cannot be parsed as a double
            // You can provide a default value or throw an exception based on your
            // requirements
            return 0.0; // Default value
        }
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(BSTNode root) {
        if (root != null) {
            inorder(root.left);

            // Print information for each product in the linked list
            for (Product product : root.products) {
                System.out.println(product.getProductName() + " - " + root.comparisonDetails);
            }

            inorder(root.right);
        }
    }

    public Product getBestDeal() {
        BSTNode leftmostNode = getLeftmostNode(root);
        if (leftmostNode != null && !leftmostNode.products.isEmpty()) {
            return leftmostNode.products.getFirst();
        }
        return null;
    }

    private BSTNode getLeftmostNode(BSTNode node) {
        if (node == null || node.left == null) {
            return node;
        }
        return getLeftmostNode(node.left);
    }

    public void clear() {
        root = null;
    }

    // Other BST operations can be added as needed

    public static void main(String[] args) {
    }
}
