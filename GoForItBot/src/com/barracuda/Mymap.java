/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barracuda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * COPYRIGHT (C) 2012 Nam Phan. All Rights Reserved. Solves CS homework
 * assignment #
 *
 * @author Mr.Nam
 */
public class Mymap {

    private GameState gs;
    public int size;
    public Cell[][] map;
    private int idx;
    public ArrayList<Integer> enemy;
    public ArrayList<Integer> mine;
    private ArrayList<ArrayList<Integer>> board;
    public boolean isTopBottom;

    public Mymap(GameState gs) {
        this.idx = gs.idx;
        this.board = gs.board;
        this.gs = gs;
        this.size = gs.board.size();
        this.map = new Cell[size][size];
        this.mine = gs.owned_squares.get(idx);
        this.enemy = gs.owned_squares.get(idx == 0 ? 1 : 0);
        updateMap();
    }

    private void updateMap() {
        int x = 0;
        int y = 0;
        for (ArrayList<Integer> row : board) {
            for (Integer c : row) {
                Cell cell = makeCellWithCondWithCor(c, x, y);
                map[y][x] = cell;
                x++;
            }
            y++;
            x = 0;
        }

    }

    public Cell makeCellWithCond(int key) {
        if (mine.contains(key)) {
            return new Cell(key, 1);
        } else if (enemy.contains(key)) {
            return new Cell(key, 2);
        } else {
            return new Cell(key, 0);
        }
    }

    public Cell makeCellWithCondWithCor(int key, int x, int y) {
        if (mine.contains(key)) {
            return new Cell(x, y, key, 1);
        } else if (enemy.contains(key)) {
            return new Cell(x, y, key, 2);
        } else {
            return new Cell(x, y, key, 0);
        }
    }

    public Cell findbyValue(Integer i) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (map[y][x].getValue() == i) {
                    return map[y][x];
                }
            }
        }
        return null;
    }

    public Cell onD1(List<Integer> offer) {
        for (Integer i : offer) {
            Cell cell = findbyValue(i);
            if (cell != null && cell.getX() == cell.getY()) {
                return cell;
            }
        }
        return null;
    }

    public Cell getCellInEmptyLine_Middle(List<Integer> offer) {
        int x = 3;
        int y = 3;
        Cell tmp = null;
        int minavg = size * size;
        List<Integer> tmpOffer = new ArrayList<>();
        for (Integer i : offer) {
            tmpOffer.add(i);
        }

        for (Integer i : offer) {
            Cell cell = getCellInEmptyLine(tmpOffer);
            if (cell == null) {
                return findbyValue(offer.get(0));
            }
            int tmpavg = Math.abs((cell.getX() - x) * (cell.getY() - y));
            if (tmpavg < minavg) {
                tmp = cell;
                minavg = tmpavg;
                tmpOffer.remove(tmp.getValue());
            }
        }
        return tmp;
    }

    public Cell getInRangeWithPoint(int x, int y, List<Integer> offer) {
        Cell tmp = null;
        int minavg = size;
        for (Integer i : offer) {
            Cell cell = findbyValue(i);
            int tmpavg = (Math.abs(cell.getX() - x) + Math.abs(cell.getY() - y)) / 2;
            if (tmpavg < minavg) {
                tmp = cell;
            }
        }
        return tmp;
    }

    public Cell getCellInEmptyLine(List<Integer> offer) {
        for (int i : offer) {
            Cell tmp = findbyValue(i);
            boolean isgood = true;
            if (isTopBottom) {
                int x = tmp.getX();
                for (int y = 0; y < 7; y++) {
                    if (map[y][x].getIsowned() > 0) {
                        isgood = false;
                        break;
                    }
                }

            } else {
                int y = tmp.getY();
                for (int x = 0; x < 7; x++) {
                    if (map[y][x].getIsowned() > 0) {
                        isgood = false;
                        break;
                    }
                }
            }
            if (isgood) {
                return tmp;
            }
        }
        return null;
    }

    public Cell getCellNearEnemy(List<Integer> offer) {
        Cell eCell = findbyValue(enemy.get(0));
        int x = eCell.getX();
        int y = eCell.getY();
        Cell tmp = null;
        int minavg = size * size;
        List<Integer> tmpOffer = new ArrayList<>();
        for (Integer i : offer) {
            tmpOffer.add(i);
        }

        for (Integer i : offer) {
            Cell cell = getCellInEmptyLine(tmpOffer);
            if (cell == null) {
                return findbyValue(offer.get(0));
            }
            int tmpavg = Math.abs((cell.getX() - x) * (cell.getY() - y));
            if (tmpavg < minavg) {
                tmp = cell;
                minavg = tmpavg;
                tmpOffer.remove(tmp.getValue());
            }
        }
        return tmp;
    }

    public int getGoodFirstOffer(List<Integer> offer) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (!isTopBottom) {
            for (ArrayList<Integer> row : board) {
                ArrayList<Integer> array = new ArrayList<>();
                for (Integer i : row) {
                    array.add(i);
                }
                list.add(array);
            }
        } else {
            ArrayList<Integer> column;
            for (int i = 0; i < size; i++) {
                column = new ArrayList<>();
                for (List<Integer> row : board) {
                    column.add(row.get(i));
                }
                list.add(column);
            }
        }
        ArrayList<Integer> rate = new ArrayList<Integer>();
        rate.add(-1);
        rate.add(-1);
        rate.add(-1);
        rate.add(-1);
        rate.add(-1);
        rate.add(-1);
        rate.add(-1);

        int count = 0;
        for (ArrayList<Integer> e : list) {
            Integer point = 0;
            Collections.sort(e);
            if (0 >= e.get(0) && e.get(0) <= 6) {
                point++;
            }
            if (7 >= e.get(1) && e.get(1) <= 13) {
                point++;
            }
            if (14 >= e.get(2) && e.get(2) <= 20) {
                point++;
            }
            if (21 >= e.get(3) && e.get(3) <= 27) {
                point++;
            }
            if (28 >= e.get(4) && e.get(4) <= 34) {
                point++;
            }
            if (35 >= e.get(5) && e.get(5) <= 41) {
                point++;
            }
            if (42 >= e.get(6) && e.get(6) <= 49) {
                point++;
            }
            rate.set(count, point);
            count++;
        }
        //System.out.println("rate" + rate.toString());
        Integer point = 7;
        Integer result = -1;
        while (result == -1) {
            //System.out.println(rate.contains(point));
            if (rate.contains(point)) {
                ArrayList<Integer> e = list.get(rate.lastIndexOf(point));
                //System.out.println(e.toString());
                rate.set(rate.lastIndexOf(point), -1);
                //System.out.println("Cel:" + findbyValue(0).toString());
                for (int i : e) {
                    if (offer.contains(i)) {
                        Cell c = findbyValue(i);
                            if (c != null && c.getX() != 0 && c.getX() != 6 && c.getY() !=0 && c.getY() != 6) {
                                result = i;
                                break;
                            }
                        }
                    }
            } else {
                point--;
            }
        }

        return result==-1?offer.get(0):result;
    }
}
