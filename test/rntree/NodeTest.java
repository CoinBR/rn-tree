/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rntree;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class NodeTest {
    
    Node node;
    
    public NodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        node = new Node(50);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test paint and invertColor
     */
    @org.junit.Test
    public void testPaint() {
        node.paintRed();
        assert(node.isRed());
        assert(!node.isBlack());
        
        node.paintBlack();
        assert(!node.isRed());
        assert(node.isBlack());
               
        node.paintBlack();
        assert(!node.isRed());
        assert(node.isBlack());
        
        node.paintRed();
        assert(node.isRed());
        assert(!node.isBlack()); 
        
       node.invertColor();
       assert(!node.isRed());
       assert(node.isBlack());
       
       node.invertColor();
       assert(node.isRed());
       assert(!node.isBlack());       
    }

    @org.junit.Test
    public void testGetSetNode(){
        Node lChild = new Node(3);
        Node rChild = new Node(7);
        node.setLeft(lChild);
        node.setRight(rChild);
        assert(node.getLeft() == lChild);
        assert(node.getRight() == rChild);
    }
    
    @org.junit.Test
    public void testSetNewNode(){
        node.setNewLeft(2);
        node.setNewRight(8);
        assert(node.getLeft().getElement() == 2);
        assert(node.getRight().getElement() == 8);
    }

    @org.junit.Test
    public void testHasChilds(){
        assert(!node.hasChilds());
        node.setNewLeft(1);
        assert(node.hasChilds());        
        node.setNewRight(40);
        assert(node.hasChilds());
        Node one = node.getLeft();
        assert(!one.hasChilds());
    }    
    

    @org.junit.Test
    public void testHasLeft(){
        assert(!node.hasLeft());
        node.setNewLeft(1);
        assert(node.hasLeft());        
        Node n40 = node.setNewRight(40);
        assert(!n40.hasLeft());
        Node n33 = n40.setNewLeft(33);
        assert(n40.hasLeft());
        assert(!n33.hasLeft()); // Empty node
        assert(!n33.getLeft().hasLeft()); // null
    } 
    

    @org.junit.Test
    public void testHasRight(){
        assert(!node.hasRight());
        node.setNewLeft(1);
        assert(!node.hasRight());        
        Node n40 = node.setNewRight(40);
        assert(!n40.hasRight());
        Node n33 = n40.setNewRight(33);
        assert(n40.hasRight());
        assert(!n33.hasRight()); // Empty node
        assert(!n33.getRight().hasRight()); // null
    }
    
    
}
