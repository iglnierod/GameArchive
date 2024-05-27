/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.community.dao;

import com.iglnierod.gamearchive.model.community.Activity;
import java.util.ArrayList;

/**
 *
 * @author iglnierod
 */
public interface CommunityDAO {
    ArrayList<Activity> getLatest();
    
    ArrayList<Activity> getLatest(String username);
}
