package gameModel;

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
    private int userId;
    private String saveName;
    private int level;
    private Date saveDate;
    private static Database db = new Database();

    public void setGameSaveId(int gameSaveId) {
        this.gameSaveId = gameSaveId;
    }

    public int getGameSaveId() {
        return this.gameSaveId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getSaveName() {
        return this.saveName;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public Date getSaveDate() {
        return this.saveDate;
    }

    /**
     * Saves a game to the database
     *
     * @param occupant occupant type like Kiwi or Rat etc
     */
    public boolean save() {
        boolean saveStatus = db.saveGame(this.userId, this.saveName, this.level, this.saveDate);
        return saveStatus;
    }

    /**
     * gets all saved games of a player
     */
    public static ArrayList<GameSave> getAllGameSaves(int userId) {
        ArrayList<GameSave> gameSaves = db.getAllGameSaves(userId);
        return gameSaves;
    }

    /**
     * deletes a game save
     *
     * @param id save id
     */
    public static boolean deleteSave(int id) {
        return db.deleteGameSave(id);
    }

    /**
     * updates a game save
     *
     * @param id save id
     * @param level game level
     * @date current date
     */
    public static boolean updateGame(int id, int level, Date date) {
        boolean saveStatus = db.updateGame(id, level, date);
        return saveStatus;
    }
}
