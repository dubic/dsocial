/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.dsocial.util;

import java.util.Date;

/**
 *
 * @author dubic
 */
public class ActivityUtils {

    public static String convertPostedTime(Long postedTime) {
        long now = new Date().getTime();
        long postedTimeDiff = now - postedTime;
        long secs = postedTimeDiff / 1000;
        int mins = (int) (secs / 60);
        int hrs = mins / 60;
        int days = hrs / 24;
        int years = days / 365;
        StringBuilder sb = new StringBuilder();
        if (days > 0 && years > 0) {
            sb.append("about ").append(years).append(" year(s), ").append(days).append(" day(s) ago");
            return sb.toString();
        }
        if (years > 0) {
            sb.append("about ").append(years).append(" year(s) ago");
            return sb.toString();
        }
        if (days > 0) {
            sb.append("about ").append(days).append(" day(s) ago");
            return sb.toString();
        }
        if (hrs > 0) {
            sb.append("about ").append(hrs).append(" hour(s) ago");
            return sb.toString();
        }
        if (mins > 0) {
            sb.append("about ").append(mins).append(" minute(s) ago");
            return sb.toString();
        }
        sb.append("just now");
        return sb.toString();
    } 
    

//    public static List<IsocialActivity> combineActivities(List<IsocialActivity> userGroupActivities, List<IsocialActivity> userConnectionsActivities) {
//        List<IsocialActivity> alist = new ArrayList<IsocialActivity>();
//        for (IsocialActivity isocialActivity : alist) {
//            Collections.
//        }
//    }
}
