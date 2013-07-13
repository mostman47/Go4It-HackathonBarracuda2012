/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barracuda;

import java.util.List;

/**
 *
 * @author Mr.Nam
 */
public interface Bot {
    public int bid(List<Integer> offer);

    public int choose(List<Integer> offer);
}
