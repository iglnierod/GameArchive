/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.game.dao;

import com.iglnierod.gamearchive.model.game.Game;
import com.iglnierod.gamearchive.model.game.filter.GameFilter;
import com.iglnierod.gamearchive.model.list.List;
import java.util.ArrayList;

/**
 *
 * @author iglnierod
 */
public interface GameDAO {
    public ArrayList<Game> search(String inputText, GameFilter filter);
    
    public String post(String url, String body);
    
    public ArrayList<Game> parse(String jsonResponse);
    
    public Game getAllInformation(int gameId);
    
    public Game parseAll(String jsonResponse);
    
    public void saveGame(Game game);
    
    public boolean addToList(Game game, List list);
    
    public ArrayList<Game> getGamesInList(int listId);
}
