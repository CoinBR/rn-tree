/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rntree;

import rntree.exceptions.NotImplementedException;

/**
 *
 * @author User
 */
public class Node {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[45m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    
    private RNTree tree;
    private Node right;
    private Node left;
    private Integer element;
    private Boolean _isRed; // False = Black, True = Red

    public Node(Integer element) {
       
        
       // empty/null leafs are black      
       if(element == null) { this._isRed = false; }
       
       // non-empty leafs should be initialized
       else{ this.placeElement(element); }      
    }      
        
    // When it is a actual element
    // Assign Nodes with null elements at its children
    //  the children will point to null left and right values (not nodes)
    //
    // This method is also used as a helper, in the Tree's insert method
    //  when assiging a value to a dull Node (not pointing to children nodes)
    //  it will make this an actual Node, with the new value, and its children will be Nodes
    public Node placeElement(Integer element){
        this.element = element;
        this.left = new Node(null);          
        this.right = new Node(null);

        this.element = element;
        this._isRed = true;
        
        return this;
    } 
    
    // When deleting a node, copy the attributes of a replacement node to it
    public Node swap(Node repNode){
        this.setElement(repNode.getElement());
        // this.setLeft(repNode.getLeft());
        // this.setRight(repNode.getRight());
        
        return this;
    }
 
    public void paint(Boolean color){
        if (color){
            this.paintRed();
        }
        else {
            this.paintBlack();
        }
    }
    
    public void paintRed(){
        this._isRed = true;
    }
    
    public void paintBlack(){
        this._isRed = false;
    }
    
    public void invertColor(){
        this._isRed = !this._isRed;
    }
    
    public Boolean isRed(){
        return this._isRed;
    }
    
    public Boolean isBlack(){
        return !this._isRed;
    }

    public Node getRight() {
        return right;
    }
    
    public String getText() {
        String s = this.element == null ? "□" : this.element.toString();
        s += ":";
        s += this.isRed() ? "R" : "B";
        return s;
        
        /*
        String s = this.isRed() ? ANSI_RED_BACKGROUND : ANSI_BLACK_BACKGROUND;
        s += this.element == null ? "□" : this.element.toString(); 
        s += ANSI_RESET;
        return s;
        */
    }

    public Node setRight(Node right) {
        this.right = right;
        return this.getRight();
    }

    public Node getLeft() {
        return left;
    }


    public Node setLeft(Node left) {
        this.left = left;
        return this.getLeft();
    }
    
    public Node setNewLeft(Integer element){
        return this.setLeft(new Node(element));
    }
    
    public Node setNewRight(Integer element){
        return this.setRight(new Node(element));
    } 
    
    // Set a Left or Right son based on "position"
    public Node setSon(Node son, Boolean position){
        return (position) ? this.setRight(son) : this.setLeft(son);
    }

    public Node getSon(Boolean position){
        return (position) ? this.getRight() : this.getLeft();
    }
    
    public Boolean isSonOf(Node parent){
        return parent.getLeft() == this || parent.getRight() == this;
    }    
    
    public Boolean isParentOf(Node son){
        return son.isSonOf(this);
    }
        
    public Boolean hasLeft(){
        return this.hasRelative(this.left);
    }  
    
    public Boolean hasRight(){
        return this.hasRelative(this.right);
    }
 
    private Boolean hasRelative(Node relative){
        return relative != null && relative.getElement() != null;
    }
    
    public Integer getElement() {
        return element;
    }
    
    public void setElement(Integer element) {
        this.element = element;
    }
    
    public Boolean hasChilds(){
        Node l = this.left != null ? this.left : new Node(null);
        Node r = this.right != null ? this.right : new Node(null);
            return l.getElement() != null || r.getElement() != null;
    }
    
    public String toString(){
        return "[Node " + this.getText() + "]";
    }
    
    public void print(){
        System.out.println(this);
    }
   
       
}
