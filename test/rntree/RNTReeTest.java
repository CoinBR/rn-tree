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
public class RNTReeTest {
    
    RNTree tree;
    
    public RNTReeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tree = new RNTree();
    }
    
    @After
    public void tearDown() {
    }
    
    public RNTree createBinTree01(){
        RNTree t = new RNTree();
        t.insert(50);
        
        t.insert(25);
        t.insert(20);
        
        t.insert(75);
        t.insert(60);
        t.insert(90);
        
        return t;
    }    
    public RNTree createBinTree02(){
        RNTree t = createBinTree01();
        t.insert(27); 
        t.insert(30); 
        t.insert(33);     
        return t;
    }
    public RNTree createBinTree03(){
        RNTree t = createBinTree02();
        t.insert(29);     
        return t;
    }
    public RNTree createBinTree04(){
        RNTree t = createBinTree03();
        t.insert(63);
        return t;
    }

    @org.junit.Test
    public void testIsEmpty() {
       assert(tree.isEmpty());
       
       tree.insert(1);
       assert(!tree.isEmpty());
       
       tree.remove(1);
       assert(tree.isEmpty());
       
       tree.insert(1);
       assert(!tree.isEmpty());       
       
       tree.insert(2);
       assert(!tree.isEmpty());
       
       tree.insert(3);
       assert(!tree.isEmpty());
       
       tree.remove(3);
       assert(!tree.isEmpty());
       
       tree.remove(2);
       assert(!tree.isEmpty());   
              
       tree.remove(1);
       assert(tree.isEmpty());
    }  
    
    @org.junit.Test
    public void testGetRoot(){
        tree = this.createBinTree01();
        assert(tree.getRoot().getElement() == 50);
    }
    
    @org.junit.Test
    public void testInsertBin01() {
        tree = this.createBinTree01();
        Node root = tree.getRoot();

        assert(root.getLeft().getElement() == 25);
        assert(root.getLeft().getLeft().getElement() == 20);
        assert(root.getLeft().getRight().getElement() == null);
        
        assert(root.getRight().getElement() == 75);
        assert(root.getRight().getLeft().getElement() == 60);
        assert(root.getRight().getRight().getElement() == 90);       
    }
    
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testInsertInvalidBin01() {
        tree.insert(null);
    }
    
    @org.junit.Test
    public void testFindNode(){
        tree = this.createBinTree01();
        Node n25 = new Node(25);
        Node n20 = new Node(20);
        
        Node n75 = new Node(75);
        Node n60 = new Node(60);
        Node n90 = new Node(90);
        
        n25.setLeft(n20);
        
        n75.setLeft(n60);
        n75.setRight(n90);
        
        assert(tree.findNode(25).getElement() == n25.getElement());
        assert(tree.findNode(20).getElement() == 20);
        assert(tree.findNode(75).getElement() == n75.getElement());
        assert(tree.findNode(60).getElement() == 60);
        assert(tree.findNode(90).getElement() == 90);    
    }
    
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testFindInvalidNode01(){
        tree = this.createBinTree01();
        tree.findNode(27);
    }  
    
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testFindInvalidNode02(){
        tree = this.createBinTree01();
        tree.findNode(68);
    }    
    
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testFindInvalidNode03(){
        tree = this.createBinTree01();
        tree.findNode(200);
    }
    
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testRemove01(){
        tree = this.createBinTree01();
        Integer element = 90;
        tree.remove(element);
        tree.findNode(element);
    }     
    
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testRemove02(){
        tree = this.createBinTree01();
        Integer element = 20;
        tree.remove(element);
        tree.findNode(element);
    }      
    
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testRemove03(){
        tree = this.createBinTree01();
        Integer element = 25;
        tree.remove(element);
        tree.findNode(element);
    }     
    
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testRemove04(){
        tree = this.createBinTree01();
        Integer element = 75;
        tree.remove(element);
        tree.findNode(element);
    }  
    
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testRemove05(){
        tree = this.createBinTree01();
        Integer element = 50;
        tree.remove(element);
        tree.findNode(element);
    }   

    @org.junit.Test()
    public void testRemove_NoChild(){
        tree = this.createBinTree01();
        RNTree ref = this.createBinTree01();

        tree.remove(60);
        ref.getRoot().getRight().setLeft(new Node(null));
        assert(tree.toString().equals(ref.toString()));
        
        tree.remove(20);
        ref.getRoot().getLeft().setLeft(new Node(null));
        assert(tree.toString().equals(ref.toString()));
        
        tree.remove(25);
        ref.getRoot().setLeft(new Node(null));
        assert(tree.toString().equals(ref.toString()));
        
        tree.remove(90);
        ref.getRoot().getRight().setRight(new Node(null));
        assert(tree.toString().equals(ref.toString()));
        
        tree.remove(75);
        ref.getRoot().setRight(new Node(null));
        assert(tree.toString().equals(ref.toString()));                  
    } 
    
    
    @org.junit.Test()
    public void testRemove_HasChildsFarAway(){
        tree = this.createBinTree03();
        RNTree ref = this.createBinTree03();
        
        tree.remove(27);
        ref.findNode(27).setElement(29);
        ref.findNode(30).setLeft(new Node(null));
        assert(tree.toString().equals(ref.toString()));
        
        tree.remove(50);
        ref.findNode(75).setLeft(new Node(null));        
        ref.findNode(50).setElement(60);
        assert(tree.toString().equals(ref.toString())); 
                        
    }   
    
    @org.junit.Test()
    public void testRemove_HasChildsNearSwap(){
        tree = this.createBinTree03();
        RNTree ref = this.createBinTree03();
 
        tree.remove(25);
        Node n30 = ref.findNode(25).setRight(ref.findNode(30));
        Node n25 = ref.findNode(25);
        n25.setElement(27);
        n25.setRight(n30);
        //ref.findNode(25).setLeft(new Node(null));
        assert(tree.toString().equals(ref.toString()));                                  
    } 
    
    @org.junit.Test()
    public void testInsert_Case1_01(){
        tree.insert(3);
        tree.insert(5);
        
        
        RNTree ref = new RNTree();
        Node n3 = ref.insert(3);
        n3.paintBlack();
        Node n5 = n3.setNewRight(5);
        n5.paintRed();

        // tree.print(); ref.print();
        assert(tree.toString().equals(ref.toString()));
    }
}
