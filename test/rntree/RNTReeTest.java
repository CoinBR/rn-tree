/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rntree;

import java.lang.reflect.InvocationTargetException;
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
    
    private RNTree tree;
    static private MakePublicHelper mp;
            
    public RNTReeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        mp = new MakePublicHelper();
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
  
    
    public RNTree createManualBinTree_ToSimpleLeftRotate01(){
        RNTree ref = new RNTree();
        
        Node n32 = ref.insert(32);
        
        Node n31 = n32.setNewLeft(31);
        Node n35 = n32.setNewRight(35);
        
        Node n33 = n35.setNewLeft(33);
        Node n36 = n35.setNewRight(36);
        
        Node n38 = n36.setNewRight(38);
        
        return ref;
    }
    
    public RNTree createManualBinTree_ToSimpleRightRotate01(){
        RNTree ref = new RNTree();
        
        Node n50 = ref.insert(50);
        
        Node n20 = n50.setNewLeft(20);
        Node n70 = n50.setNewRight(70);
        
        Node n10 = n20.setNewLeft(10);
        Node n30 = n20.setNewRight(30);
        
        Node n5 = n10.setNewLeft(5);
   
        return ref;
    }
    
    public RNTree createManualRNTree01(){
        RNTree t = new RNTree();
        
        Node root = t.insert(30);
        root.paintBlack();
        
        Node n20 = root.setNewLeft(20);
        n20.paintBlack();
        
        Node n50 = root.setNewRight(50);
        n50.paintRed();
        
        Node n40 = n50.setNewLeft(40);
        n40.paintBlack();
        
        Node n60 = n50.setNewRight(60);
        n60.paintBlack();
        
        Node n70 = n60.setNewRight(70);
        n70.paintRed();
        
        return t;
    }
    
    public RNTree createRNTree01(){
        RNTree t = new RNTree();
        t.insert(30);
        t.insert(20);
        t.insert(50);
        t.insert(40);
        t.insert(60);
        t.insert(70);
        return t;
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
    

    public RNTree createManualRNTree02(){
        RNTree t = new RNTree();
        
        Node root = t.insert(30);
        root.paintBlack();
        
        Node n13 = root.setNewLeft(13);
        n13.paintBlack();
        
        Node n8 = n13.setNewLeft(8);
        n8.paintBlack();
        
        Node n23 = n13.setNewRight(23);
        n23.paintBlack();
        
        Node n25 = n23.setNewRight(25);
        n25.paintRed();
        
        Node n53 = root.setNewRight(53);
        n53.paintBlack();
        
        Node n43 = n53.setNewLeft(43);
        n43.paintBlack();
        
        Node n83 = n53.setNewRight(83);
        n83.paintRed();        
        
        Node n63 = n83.setNewLeft(63);
        n63.paintBlack();
        
        Node n93 = n83.setNewRight(93);
        n93.paintBlack();
        
        Node n96 = n93.setNewRight(96);
        n96.paintRed();
        
        return t;        
    }    
    
    public RNTree createRNTree02(){
        RNTree t = new RNTree();
 
        t.insert(30);
        
        t.insert(13);
        t.insert(53);
        
        t.insert(8);
        t.insert(23);
        t.insert(43);
        t.insert(83);
        
        t.insert(25);
        t.insert(63);
        t.insert(93);
        
        t.insert(96);
        
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
    public void testFindFamily(){
        tree = this.createBinTree01();
        
        Family f = tree.findFamily(90);
        
	assert(f.getMain() == tree.findNode(90));
        assert(f.getParent() == tree.findNode(75));
        assert(f.getBrother() == tree.findNode(60));
        assert(f.getGrandParent() == tree.findNode(50));
        assert(f.getUncle() == tree.findNode(25));
    }    
    
    @org.junit.Test()
    public void testInsertRN_DoNothingCase(){
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
    
    @org.junit.Test()
    public void testInsertRN_Case1_01(){
        
        this.tree = this.createRNTree01();
        RNTree ref = this.createManualRNTree01();
        // tree.print(); ref.print();
        assert(tree.toString().equals(ref.toString()));
    }
    
    
    @org.junit.Test()
    public void testInsertRN_Case1_02(){
        
        this.tree = this.createRNTree02();
        RNTree ref = this.createManualRNTree02();
        // tree.print(); ref.print();
        assert(tree.toString().equals(ref.toString()));
    }

    @org.junit.Test()
    public void testSimpleLeftRotation_01() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        
        this.tree = this.createManualBinTree_ToSimpleLeftRotate01();
        RNTree ref = this.createManualBinTree_ToSimpleLeftRotate01();
               
        Node n35 = ref.findNode(35);
        Node n32 = ref.findNode(32);
        Node n33 = ref.findNode(33);
        
        n32.setRight(n33);
        n35.setLeft(n32);
        
        Node aboveRoot = (Node) mp.get(ref, "aboveRoot");
        aboveRoot.setRight(n35);
        mp.invoke(this.tree, "rotate", false, 35);
        
        // tree.print(); ref.print();
        assert(tree.toString().equals(ref.toString()));
    }       
    
    @org.junit.Test()
    public void testSimpleRightRotation_01() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        
        this.tree = this.createManualBinTree_ToSimpleRightRotate01();
        RNTree ref = this.createManualBinTree_ToSimpleRightRotate01();
               
        Node n20 = ref.findNode(20);
        Node n50 = ref.findNode(50);
        Node n30 = ref.findNode(30);
        
        n50.setLeft(n30);
        n20.setRight(n50);
        
        Node aboveRoot = (Node) mp.get(ref, "aboveRoot");
        aboveRoot.setRight(n20);
        mp.invoke(this.tree, "rotate", true, 20);

        // tree.print(); ref.print();
        assert(tree.toString().equals(ref.toString()));
    }       
    
}
