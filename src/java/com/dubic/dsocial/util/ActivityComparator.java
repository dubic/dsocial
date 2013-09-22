/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.util;

import com.dubic.dsocial.models.IsocialActivity;
import java.util.Comparator;

/**
 *
 * @author dubic
 */
public class ActivityComparator implements Comparator<IsocialActivity> {
    public ActivityComparator(){
    }

    public int compare(IsocialActivity a1, IsocialActivity a2) {
        int val = a1.getPostedTime().compareTo(a2.getPostedTime());
        return -1 * val;
    }

    
}
