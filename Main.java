package loading;

import serializable.GameProgress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        String zipArr = "C:\\Games\\saveGames\\ZipArr.zip";
        String strGameOne = "C:\\Games\\saveGames\\gameOne.dat";
        openZip(zipArr, strGameOne);
        GameProgress gameProgress1 = openProgress(strGameOne);
        System.out.println(gameProgress1);
    }

    public static void openZip(String string, String path) {
        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(string))) {
            ZipEntry entry;
            String name;
            while ((entry = zip.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zip.read(); c != -1; c = zip.read()) {
                    fout.write(c);
                }
                fout.flush();
                zip.closeEntry();
                fout.close();
            }
            } catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }

    public static GameProgress openProgress(String string) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(string);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

             gameProgress = (GameProgress) ois.readObject();

        }  catch (Exception e) {
            e.printStackTrace();
        }
        return gameProgress;
    }
}

