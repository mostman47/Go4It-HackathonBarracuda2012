/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.barracuda;

import java.util.ArrayList;
import java.util.List;

/**
 * COPYRIGHT (C) 2012 Nam Phan. All Rights Reserved.
 * Solves CS homework assignment #
 * @author Mr.Nam
 */
public class Snack {
    public static boolean random;
    public static int id;
    public static int exitLow;
    public static int exitHigh;
    public static boolean isTopBottom;
    public static Cell head;
    public static Cell tail;
    public static Cell next;
    public static Cell head_jump;
    public static Cell tail_jump;
    
    public static void setDefault()
    {
        random = false;
        id = -1;
        exitLow = 0;
        exitHigh = 6;
        isTopBottom = true;
        head = null;
        tail = null;
        next = null;
        head_jump = null;
        tail_jump = null;
    }

    public static Cell searchNear(List<Integer> offer, Cell[][] map) {
        ArrayList<Cell> list = new ArrayList<>();
        if(isTopBottom)
        {
            if(head.getY() - 1 > exitLow) 
                head_jump = map[head.getY() - 2][head.getX()];
            if(tail.getY() + 1 < exitHigh) 
                tail_jump = map[tail.getY() + 2][tail.getX()];
            
            if(head.getY()>exitLow)
            {
                list = addNearSearch(head.getX(), head.getY() - 1, list, map, isTopBottom);
            }
            if(tail.getY() < exitHigh)
            {
                list = addNearSearch(tail.getX(), tail.getY() + 1, list, map, isTopBottom);
            }
        }
        else
        {
            if(head.getX() - 1 > exitLow) {
                head_jump = map[head.getY() ][head.getX() - 2];
            }
            if(tail.getX() + 1 < exitHigh) {
                tail_jump = map[tail.getY() ][tail.getX() + 2];
            }            
            
            if(head.getX()>exitLow)
            {
                list = addNearSearch(head.getX() - 1, head.getY(), list, map, isTopBottom);
            }
            if(tail.getX() < exitHigh)
            {
                list = addNearSearch(tail.getX() + 1, tail.getY(), list, map, isTopBottom);
            }
        }
        if(head_jump!=null && offer.contains(head_jump.getValue())) {tail_jump = null; return head_jump;}
        else head_jump = null;
        if(tail_jump!=null && offer.contains(tail_jump.getValue())) {head_jump = null; return tail_jump;}
        else tail_jump = null;
        
        if(!list.isEmpty())
        {
            for(Cell c : list) {
                if (offer.contains(c.getValue())) {
                    next = c;
                return c;
            }
            } 
        }
        return null;
    }
    private static ArrayList<Cell> addNearSearch(int x, int y, ArrayList<Cell> list,Cell[][] map, boolean isTB)
    {
        if(isTB)
        {
            int new_x = x - 1;
            if (new_x >= exitLow && new_x <= exitHigh) {
                list.add(map[y][new_x]);
            }
            new_x = x;
            if (new_x >= exitLow && new_x <= exitHigh) {
                list.add(map[y][new_x]);
            }
            new_x = x + 1;
            if (new_x >= exitLow && new_x <= exitHigh) {
                list.add(map[y][new_x]);
            }
        }
        else
        {
            int new_y = y - 1;
            if (new_y >= exitLow && new_y <= exitHigh) {
                list.add(map[new_y][x]);
            }
            new_y = y;
            if (new_y >= exitLow && new_y <= exitHigh) {
                list.add(map[new_y][x]);
            }
            new_y = y + 1;
            if (new_y >= exitLow && new_y <= exitHigh) {
                list.add(map[new_y][x]);
            }
        }
        return list;
        
    }
    

    public static void setNext(Cell[][] map) {
        if(head_jump != null || tail_jump!=null) return;
        if(isTopBottom)
        {
            
            if(next.getY() == head.getY() - 1) { 
                if(head.getY() - 1 > exitLow && map[head.getY() - 2][head.getX()].getIsowned() == 1) {
                    head = map[head.getY() - 2][head.getX()];
                }
                else {
                    head = next;
                }
            }
            else {
                if(tail.getY() + 1 < exitHigh && map[tail.getY() + 2][tail.getX()].getIsowned() == 1) {
                    tail = map[tail.getY() + 2][tail.getX()];
                }
                else {
                    tail = next;
                }
            }
        }
        else
        {
            if(next.getX() == head.getX() - 1) {
                if(head.getX() - 1 > exitLow && map[head.getY()][head.getX() - 2].getIsowned() == 1) {
                    head = map[head.getY()][head.getX() - 2];
                }
                else {
                    head = next;
                }
            }
            else {
                if(tail.getX() + 1 < exitHigh && map[tail.getY()][tail.getX() + 2].getIsowned() == 1) {
                    tail = map[tail.getY()][tail.getX() + 2];
                }
                else {
                    tail = next;
                }
            }
        }
    }

    public static Cell getEval() {
        if(head_jump != null) {next = head_jump; head_jump = null;}
        if(tail_jump != null) {next = tail_jump; tail_jump = null;}
        return next;
    }
            
}
