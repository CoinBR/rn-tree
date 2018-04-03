/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package rntree;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import rntree.exceptions.*;


/**
 *
 * @author User
 */
public class RNTree {

    private Node aboveRoot;
    private Node doubleBlack;

    public RNTree() {
        this.aboveRoot = new Node(Integer.MIN_VALUE);
    }
    
    public Boolean isEmpty(){
        return this.aboveRoot.getRight().getElement() == null;
    }

    public Node getRoot(){
        if (this.isEmpty()){
            throw new IllegalStateException("The tree is empty");
        }
        return this.aboveRoot.getRight();
    }
    
    public Node insert(Integer element){
        if (element == null) { 
            throw new IllegalArgumentException("Cannot insert a NULL value in tree"); 
        }
     
        return this.insert(this.aboveRoot, element); 
    }
    
    
    private Node insert(Node ancestor, Integer element){
          
        Node next = (element <= ancestor.getElement()) ? ancestor.getLeft() : ancestor.getRight();
                
        // if its a null/dull/empty leaf, place the element there
        if (next.getElement() == null) {
            Node placedNode = next.placeElement(element);
            this.balance(this.findFamily(element));
            return placedNode;
        }
        return this.insert(next, element);
    }   
    
    private void balance(Family fam){
            Node node = fam.getMain();
            
            this.getRoot().paintBlack();
            
            // Sons of root have an unreal uncle. Ignore it
            if(fam.getGrandParent() != this.aboveRoot
                    && fam.getUncle() != null
                    && node != this.getRoot()
                    && fam.getParent().isRed()){               
                // case 1
                if(fam.getUncle().isRed()){
                    fam.getGrandParent().invertColor();
                    fam.getParent().invertColor();
                    fam.getUncle().invertColor();

                    this.balance(fam.getGrandParent());
                } 
                // cases 2 and 3 (Black Uncle)
                else{
                    Family rotationBase = fam.isTriangle() ? fam : this.findFamily(fam.getParent());
                    this.rotate(!fam.isRight(), rotationBase);
                    // case 3
                    if (fam.isLine()){
                        fam.getParent().invertColor();
                        fam.getGrandParent().invertColor();
                    }
                    Node nextToCheck = rotationBase.getMain().getSon(!fam.isRight());
                    System.out.println(nextToCheck.getElement());
                    if (nextToCheck.getElement() != null) this.balance(nextToCheck);
                }
            }
    }
    
    private void balance(Node base){
        this.balance(this.findFamily(base));
    }
    
    
    private void rotate(Boolean toRight, Node node){
        this.rotate(toRight, node.getElement());
    }
    
    private void rotate(Boolean toRight, Integer element){
        this.rotate(toRight, this.findFamily(element));
    }
    
    private void rotate(Boolean toRight, Family fam){
        Boolean direction = toRight;
        Boolean toLeft = !toRight;
        
        Node main = fam.getMain();
        Node keep = main.getSon(direction);
        Node oldTop = fam.getParent();
        
        // If grandpa is aboveRoot, its child/son will always be on right side
        Boolean grandPaSonDirection = oldTop == getRoot() ? true : direction;
        fam.getGrandParent().setSon(main, grandPaSonDirection);
        
        main.setSon(oldTop, direction);        
        oldTop.setSon(keep, !direction);
                
        Node greater = this.getNodeWithGreaterElement(oldTop, keep);
        Node smaller = this.getNodeWithSmallerElement(oldTop, keep);       
        
        if(        (toLeft && greater == oldTop)
                || (toRight && smaller == oldTop))
        {
            oldTop.swap(keep);
        }
    }
 
    
    private Node getNodeWithGreaterElement(Node na, Node nb){
         Integer a = na.getElement() == null ? Integer.MIN_VALUE : na.getElement();
         Integer b = nb.getElement() == null ? Integer.MIN_VALUE : nb.getElement();
         return a > b ? na : nb;
    }
    
    private Node getNodeWithSmallerElement(Node na, Node nb){
         return this.getNodeWithGreaterElement(na, nb) == na ? nb : na;
    }    
    
    // Returns a "struct" with relevant family members
    private Family findFamily(Node ancestor, Integer element){
        Node next;
        Boolean position;
        
        if (element <= ancestor.getElement()){
            next = ancestor.getLeft();
            position = false;
        }
        else{
            next = ancestor.getRight();
            position = true;
        }
        if (next.getElement() == null) { 
            throw new IllegalArgumentException(
                    "There is no Node with this element in the tree");
        }
        if (next.getElement() == element){
            Family parentFam;
            Node parent, grandParent, uncle, brother;
            parent = grandParent = uncle = brother = null;
            Boolean isTriangle = null;
            
            if (ancestor != this.aboveRoot){ 
                parentFam = this.findFamily(ancestor.getElement());
                parent = ancestor;
                brother = parent.getSon(!position);
                grandParent = parentFam.getParent() == null ? this.aboveRoot : parentFam.getParent();                
                uncle = grandParent.getSon(!parentFam.getPosition());
                isTriangle = position != parentFam.isRight();
            }                  
            return new Family(next, position, parent, grandParent, brother, uncle, isTriangle);
        }
        return this.findFamily(next, element);    
    }
    
    public Family findFamily(Integer element){
        return this.findFamily(this.aboveRoot, element);
    }        
    
    
    public Family findFamily(Node node){
        return this.findFamily(node.getElement());
    }
    
    public Node findNode(Integer element){
        return this.findFamily(element).getMain();
    }
   
    
    // When removing a node, find/get the Node which will replace it
    private Node findReplacementNode(Node toDel){
        if (!toDel.hasChilds()) { 
            return toDel;
        }
        if (!toDel.hasRight()){
            return toDel.getLeft();
        }
        
        Node next = toDel.getRight();         
        while(next.getLeft().getElement() != null){
            next = next.getLeft();
        }
        return next;        
    }
    
    private void removeLeaf(Family fam){
        Node parent = fam.getMain() == this.getRoot() ? this.aboveRoot : fam.getParent();
        parent.setSon(new Node(null), fam.getPosition());
    }    
        
    public void remove(Integer element){
        Family delFam = this.findFamily(element);
        Node delNode = delFam.getMain();
        Node repNode = this.findReplacementNode(delFam.getMain());
        Family repFam = this.findFamily(repNode.getElement());
             
        if (!repNode.hasChilds()) { // or !delNode.hasChilds() 
            this.removeLeaf(repFam);
        }        
        else{
            if (repNode.hasRight()){ delNode.setRight(repNode.getRight()); }
            if (repNode.hasLeft()){ delNode.setRight(repNode.getLeft()); }
        }
        delNode.swap(repNode);
    }
    
 /*   public Node remove(Integer element){
        Family toDel = this.findFamily(element); 
        Node repNode = this.findReplacementNode(toDel.getMain());
        Family actuallyRemoved;
         // no childs
        if (repNode.getElement() == null) {
            actuallyRemoved = toDel;
        }
        else {
            actuallyRemoved = this.findFamily(repNode.getElement());
            toDel.getMain().swap(repNode);
        }
        System.out.println(toDel.getMain().getText());
        Node actualReplacement = repNode != null && repNode.hasChilds() ? repNode : new Node(null);
        // Remove from tree
        actuallyRemoved.getParent().setSon(actualReplacement, actuallyRemoved.getPosition()); 
        
        return toDel.getMain();        
    }*/


    

    public void print(){
        System.out.print(this.toString());
    }
       
    @Override
    public String toString()
        {
            List<List<String>> lines = new ArrayList<List<String>>();

            List<Node> level = new ArrayList<Node>();
            List<Node> next = new ArrayList<Node>();
            
            String out = "";

            Node root = this.getRoot();
            level.add(root);
            int nn = 1;

            int widest = 0;

            while (nn != 0) {
                List<String> line = new ArrayList<String>();

                nn = 0;

                for (Node n : level) {
                    if (n == null) {
                        line.add(null);

                        next.add(null);
                        next.add(null);
                    } else {
                        String aa = n.getText();
                        line.add(aa);
                                                
                        if (aa.length() > widest) widest = aa.length();
                                                
                        next.add(n.getLeft());
                        next.add(n.getRight());

                        if (n.getLeft() != null) nn++;
                        if (n.getRight() != null) nn++;
                    }
                }

                if (widest % 2 == 1) widest++;

                lines.add(line);

                List<Node> tmp = level;
                level = next;
                next = tmp;
                next.clear();
            }

            int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
            for (int i = 0; i < lines.size(); i++) {
                List<String> line = lines.get(i);
                int hpw = (int) Math.floor(perpiece / 2f) - 1;

                if (i > 0) {
                    for (int j = 0; j < line.size(); j++) {

                        // split node
                        char c = ' ';
                        if (j % 2 == 1) {
                            if (line.get(j - 1) != null) {
                                c = (line.get(j) != null) ? '┴' : '┘';
                            } else {
                                if (j < line.size() && line.get(j) != null) c = '└';
                            }
                        }
                        out += c; //System.out.print(c);

                        // lines and spaces
                        if (line.get(j) == null) {
                            for (int k = 0; k < perpiece - 1; k++) {
                                out += " ";  // System.out.print(" ");
                            }
                        } else {

                            for (int k = 0; k < hpw; k++) {
                                out += j % 2 == 0 ? " " : "─"; // System.out.print(j % 2 == 0 ? " " : "─");
                            }
                            out += j % 2 == 0 ? "┌" : "┐"; // System.out.print(j % 2 == 0 ? "┌" : "┐");
                            for (int k = 0; k < hpw; k++) {
                                out += j % 2 == 0 ? "─" : " "; // System.out.print(j % 2 == 0 ? "─" : " ");
                            }
                        }
                    }
                    out += "\n"; // System.out.println();
                }

                // print line of numbers
                for (int j = 0; j < line.size(); j++) {

                    String f = line.get(j);
                    if (f == null) f = "";
                    int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                    int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                    // a number
                    for (int k = 0; k < gap1; k++) {
                        out += " "; // System.out.print(" ");
                    }
                    out += f; // System.out.print(f);
                    for (int k = 0; k < gap2; k++) {
                        out += " "; // System.out.print(" ");
                    }
                }
                out += "\n"; // System.out.println();

                perpiece /= 2;
            }
            
            return out;
        }
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
