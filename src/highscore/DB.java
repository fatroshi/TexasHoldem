/**
 * This class is used for creating a local database file, sng to it and also reading from it.
 * The database is used for storing the game high score.
 */

package highscore;
import org.omg.CORBA.OBJECT_NOT_EXIST;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farhad Atroshi
 * creates a bin file and saves highscorelist object
 */
public class DB {
    private String dbName;
    private HighScoreList highScoreList = new HighScoreList();


    public DB(String dbName){
        this.dbName  = "./" + dbName;
        dbConnect();
    }

    /**
     * Check if we could find/open file
     * If the file does not exist, then create one.
     */
    private void dbConnect(){
        if(!openDb()){              // True if DB (dbName) does not exist
            // Created new DB
            createDb();
            // Open DB
            openDb();
        }
    }

    /**
     * Open db, return true if db exists
     * @return
     */
    private boolean openDb(){
        boolean openDb = true;
        try{
            //Check if DB exists
            FileInputStream fis = new FileInputStream(this.dbName);

            // Check if DB is not empty
            File file = new File(this.dbName);
            if(file.length() != 0){
                // Get data from file if not empty
                setData(fis);
            }

        }catch (FileNotFoundException e){
            // DB does not exist
            System.out.println("Db: " + this.dbName + " does not exits.");
            openDb = false;
        }
        return openDb;
    }

    /**
     * Create db file
     * @return
     */
    private boolean createDb(){
        boolean creteDb = true;
        try {
            FileOutputStream fos = new FileOutputStream(this.dbName);
            try{
                fos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            // File could not be created
            System.out.println(dbName + " DB could not be created... ");
            e.printStackTrace();
        }
        return creteDb;
    }

    /**
     * Get data from db, and set dataBooks
     * @param fis
     */
    private void setData(FileInputStream fis){
        try {
            // Create object stream from file fis
            ObjectInputStream ois = new ObjectInputStream(fis);

            try {
                // Store data in array from db
                this.highScoreList = (HighScoreList) ois.readObject();

            }catch (ClassNotFoundException e){
                // Class HighScoreList not found in file
                System.out.println("Set data: Class HighScoreList not found");
                e.printStackTrace();
            }

        }catch (IOException e){
            // End of file
            System.out.println("Set data: End of file exception");
            e.printStackTrace();
        }
    }

    /**
     * Get data (highscorelist)
     * @return
     */
    public HighScoreList getData(){
        return highScoreList;
    }

    /**
     * Insert/save the new collection to db
     * @param highscorelist
     */
    public void insert(HighScoreList highscorelist)  {
        try {
            FileOutputStream fos = new FileOutputStream(this.dbName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(highscorelist);
            oos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}

