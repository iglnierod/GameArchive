/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.game.dao;

import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.filter.GameFilter;
import java.util.ArrayList;

/**
 *
 * @author iglnierod
 */
public interface GameDAO {
    public ArrayList<Game> search(String inputText, GameFilter filter);
    
    public String post(String url, String body);
    
    public ArrayList<Game> parse(String jsonResponse);
}
