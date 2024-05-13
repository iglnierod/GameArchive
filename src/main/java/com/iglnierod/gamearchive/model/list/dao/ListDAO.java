/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.iglnierod.gamearchive.model.list.dao;

import com.iglnierod.gamearchive.model.client.Client;
import com.iglnierod.gamearchive.model.list.List;
import java.util.ArrayList;

/**
 *
 * @author iglnierod
 */
public interface ListDAO {

    ArrayList<List> getAll();

    ArrayList<List> getAll(Client client);
    
    boolean create(List list);

    public void update(List list);

    public void removeGame(int listId, int gameId);

    public void delete(List list);
    
}
