/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barracuda;

import java.util.ArrayList;
import java.util.List;

/**
 * COPYRIGHT (C) 2012 Nam Phan. All Rights Reserved. Solves CS homework
 * assignment #
 *
 * @author Mr.Nam
 */
public class Bot1 implements Bot{

    private int id;
    private GameState gs;
    private int[][] win_scale;

    public Bot1(int id, GameState gs) {
        this.id = id;
        this.gs = gs;
        win_scale = new int[2][gs.board.size()];
        updateWinScale(gs);
        
    }

    @Override
    public int bid(List<Integer> offer) {
        if (offer.isEmpty()) {
            return 0;
        }
        if (!isNeededRow(offer)) {
            return 0;
        }
        if(gs.credits > 16)
        return 2;
        else return gs.credits;

    }

    @Override
    public int choose(List<Integer> offer) {
        if (offer.isEmpty()) {
            return 0;
        }
        return getNeedNumb(offer);
    }

    private void updateWinScale(GameState gs) {
        int idx = gs.idx;
        ArrayList<Integer> owned = gs.owned_squares.get(idx);
        ArrayList<ArrayList<Integer>> board = gs.board;
        int count = 0;
        for (ArrayList<Integer> row : board) {
            int key_n = row.get(count);
            win_scale[0][count] = key_n;
            if (owned.contains(key_n)) {
                win_scale[1][count] = 1;
            }
            else {
                win_scale[1][count] = 0;
            }
            count++;
        }

    }

    private boolean isNeededRow(List<Integer> offer) {
        if (getNeedNumb(offer)>=0)
            return true;
        return false;
    }

    private int getNeedNumb(List<Integer> offer) {
        for(int i = 0; i < win_scale[0].length; i++)
        {
            if(offer.contains(win_scale[0][i]) && win_scale[1][i] == 0)
                return win_scale[0][i];
        }
        return -1;
    }
    
    
}
