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
        return (this.element == null) ? "□" : this.element.toString();
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
        return this.left.getElement() != null || this.right.getElement() != null;
    }
    
    public String toString(){
        return this.toString("");
    }
    
    private String toString(String oldMargin){
        String margin = oldMargin + "   ";
        String s = "";
       
        if (this.hasRight()){
            s += margin + this.getRight().toString(margin);
            s += margin;
        }
                  
        s += this.getElement().toString() + "\n";

        if (this.hasLeft()){
            s += margin + this.getLeft().toString(margin);
        }        
        
        return s;
    }
    
    
    
    
   
}
