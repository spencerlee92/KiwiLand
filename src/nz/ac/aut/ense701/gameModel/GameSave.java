/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class represents gamesaves table 
 *  
 * @author Chaitanya Varma
 * @version May 2017
 */
public class GameSave {
    
    private int gameSaveId;
    private String playerName;
    private String saveName;
    private int level;
    private Date saveDate;
    private static Database db = new Database();
    
    public void setGameSaveId(int gameSaveId)
    {
        this.gameSaveId = gameSaveId;
    }
    
    public int getGameSaveId()
    {
        return this.gameSaveId;
    }
    
    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }
    
    public String getPlayerName()
    {
        return this.playerName;
    }
    
    public void setSaveName(String saveName)
    {
        this.saveName = saveName;
    }
    
    public String getSaveName()
    {
        return this.saveName;
    }
    
    public void setLevel(int level)
    {
        this.level = level;
    }
    
    public int getLevel()
    {
        return this.level;
    }
    
    public void setSaveDate(Date saveDate)
    {
        this.saveDate = saveDate;
    }
    
    public Date getSaveDate()
    {
        return this.saveDate;
    }
    
    /**
     * Saves a game to the database
     * @param occupant occupant type like Kiwi or Rat etc
     */
    public boolean save()
    {
        boolean saveStatus = db.saveGame(this.playerName, this.saveName, this.level, this.saveDate);
        return saveStatus;
    }
    
    /**
     * gets all saved games of a player
     * @param playerName player name for which saved games will be returned
     */
    public static ArrayList<GameSave> getAllGameSaves(String playerName)
    {
        ArrayList<GameSave> gameSaves = db.getAllGameSaves(playerName);
        return gameSaves;
    }
    
    /**
     * gets last saved game of a player
     * @param playerName player name for which last saved game will be returned
     */
    public static GameSave getLastGameSave(String playerName)
    {
        GameSave gameSave = db.getLastGameSave(playerName);
        return gameSave;
    }
    
    public static boolean deleteSave(int save)
    {
        return db.deleteGameSave(save);
    }
}