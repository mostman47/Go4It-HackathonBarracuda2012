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
public class Bot4 implements Bot {

    public GameState gs;
    public Mymap map;

    public Bot4(GameState gs) {
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
        int min1 = 14;
        int min2 = 15;
        int max1 = 15;
        int max2 = 16;
        int min = min2;
        int max = max2;
        if(gs.opponent_id == 6)
        {
            min = min1;
            max = min1;
        }
        if (offer.isEmpty()) {
            return 0;
        }
        if (gs.turn == 0) {
            return 15;
        }
        if (!map.mine.isEmpty()) {
            Cell cell = Snack.searchNear(offer, map.map);
            if (cell != null) {
                //return normal_bid(gs.owned_squares.get(gs.idx).size());
                //if(gs.credits == 55) return 13;
                return gs.credits < min?gs.credits:min + (int)(Math.random() * ((max - min) + 1));
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
