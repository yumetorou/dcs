package com.wf.dcs.app.util;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author ddevera
 */
public final class UserUtil {

    public static User getCurrentUser() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        User user = null;
        if (ctx != null && ctx.getAuthentication() != null) {
            if (ctx.getAuthentication().getPrincipal() instanceof User) {
                user = (User) ctx.getAuthentication().getPrincipal();
            }
        }
        return user;
    }

    public static String getCurrentUserName() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        String username = null;
        if (ctx != null && ctx.getAuthentication() != null) {
            if (ctx.getAuthentication().getPrincipal() instanceof User) {
                User user = (User) ctx.getAuthentication().getPrincipal();
                username = user.getUsername();
            } else if (ctx.getAuthentication().getPrincipal() instanceof UserDetails) {
                UserDetails user = (UserDetails) ctx.getAuthentication().getPrincipal();
                username = user.getUsername();
            }
        }
        return username;
    }
}
