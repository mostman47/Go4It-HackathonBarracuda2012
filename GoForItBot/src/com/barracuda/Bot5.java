/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barracuda;

import java.util.List;

/**
 * COPYRIGHT (C) 2012 Nam Phan. All Rights Reserved. Solves CS homework
 * assignment #
 *
 * @author Mr.Nam
 */
public class Bot5 implements Bot {

    public GameState gs;
    public Mymap map;
    public Bot5(GameState gs) {
        if (Snack.id == -1 || Snack.id != gs.id) {
            Snack.setDefault();
            Snack.id = gs.id;
            Snack.isTopBottom = gs.idx == 1 ? true : false;
        }
        this.gs = gs;
        map = new Mymap(gs);
    }

    @Override
    public int bid(List<Integer> offer) {
        if (offer.isEmpty()) {
            return 0;
        }
        if (gs.turn == 0) {
            return 7;
        }
        if (!map.mine.isEmpty()) {
            if(map.mine.size() >= 2 )
            {
               Snack.random = true;
                int random = 1 + (int)(Math.random() * ((5 - 1) + 1));
                return gs.credits < random?gs.credits:random;
            }
            Cell cell = Snack.searchNear(offer, map.map);
            if (cell != null) {
                //return normal_bid(gs.owned_squares.get(gs.idx).size());
                //if(gs.credits == 55) return 13;
                if(map.mine.size() == 6) return 25;
                return gs.credits < 15?gs.credits:15 + (int)(Math.random() * ((16 - 15) + 1));
            } else {
                return 0;
            }
        } else {
            return 15;
        }
    }

    @Override
    public int choose(List<Integer> offer) {
        if (offer.isEmpty()) {
            return 0;
        }
        if(Snack.random)
        {
            if(map.isTopBottom)
            {
                if (Snack.head.getY() > (6 - Snack.tail.getY()))
                {
                    for(int i : offer)
                    {
                        Cell c = map.findbyValue(i);
                        if(c.getY() <= Snack.head.getY() && Math.abs(c.getX()-Snack.head.getX()) < 2) 
                            return i;
                    }
                }
                else
                {
                    for(int i : offer)
                    {
                        Cell c = map.findbyValue(i);
                        if(c.getY() >= Snack.tail.getY() && Math.abs(c.getX()-Snack.tail.getX()) < 2) 
                            return i;
                    }
                }
            }
            else
            {
                if (Snack.head.getX() > (6 - Snack.tail.getX()))
                {
                    for(int i : offer)
                    {
                        Cell c = map.findbyValue(i);
                        if(c.getX() <= Snack.head.getX() && Math.abs(c.getY()-Snack.head.getY()) < 2) 
                            return i;
                    }
                }
                else
                {
                    for(int i : offer)
                    {
                        Cell c = map.findbyValue(i);
                        if(c.getX() >= Snack.tail.getX() && Math.abs(c.getY()-Snack.tail.getY()) < 2) 
                            return i;
                    }
                }
            }
            int index = 0 + (int)(Math.random() * ((offer.size() - 1) + 1));
            return offer.get(index);
        }
        if (map.mine.isEmpty()) {
            Cell cell;
            if (map.enemy.isEmpty()) {
                cell = map.findbyValue(map.getGoodFirstOffer(offer));
            } else {
                cell = map.getCellInEmptyLine_Middle(offer);
            }
            Snack.head = cell;
            Snack.tail = cell;

            return cell.getValue();

        } else {
            Snack.setNext(map.map);
            return Snack.getEval().getValue();
        }
        //return getNeedNumb(offer);
    }

//    public int normal_bid(int n) {
//        int credit;
//        switch (n) {
//            case 0:
//                credit = 13;
//                break;
//            case 1:
//            case 2:
//                credit = 15;
//                break;
//            case 3:
//            case 4:
//            case 5:
//                credit = 14;
//                break;
//            default:
//                credit = 13;
//        }
//        return credit;
//    }
    private int getNeedNumb(List<Integer> offer) {
        Cell cell = map.onD1(offer);
        if (cell != null) {
            return cell.getValue();
        }
        return 0;
    }
}
