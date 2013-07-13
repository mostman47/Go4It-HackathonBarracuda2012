/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barracuda;

/**
 * COPYRIGHT (C) 2012 Nam Phan. All Rights Reserved.
 * Solves CS homework assignment #
 * @author Mr.Nam
 */
public class Cell {
    private Integer value;
    private Integer isowned; //0 available, 1 owned, 2 not owned
    private int x;
    private int y;
    public Cell(Integer value, Integer isowned)
    {
        this.value = value;
        this.isowned = isowned;
    }
    public Cell(int x, int y, Integer value, Integer isowned){
        this.value = value;
        this.isowned = isowned;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Integer getValue() {
        return value;
    }

    public void setIsowned(Integer isowned) {
        this.isowned = isowned;
    }

   

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getIsowned() {
        return isowned;
    }

    @Override
    public String toString() {
        return x + " " + y +" " + value;
    }
    
}
