/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rntree;

/**
 *
 * @author User
 */
public class Family {
    private Node main;
    private Boolean isRight;
    private Node parent;

    public Family(Node main, Boolean isRight, Node parent) {
        this.main = main;
        this.isRight = isRight;
        this.parent = parent;
    }

    public Node getMain() {
        return main;
    }

    public void setMain(Node main) {
        this.main = main;
    }

    public Boolean isRight() {
        return isRight;
    }
    
    public Boolean isLeft() {
        return !this.isRight();
    }
    
    public Boolean getPosition(){
        return isRight;
    }

    public void setPositionToRight() {
        this.setPosition(true);
    }    
    public void setPositionToLeft() {
        this.setPosition(false);
    }
    
    public void setPosition(Boolean isRight) {
        this.isRight = isRight;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    
    
    
}
