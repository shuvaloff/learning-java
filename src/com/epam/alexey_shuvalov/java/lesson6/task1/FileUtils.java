package com.epam.alexey_shuvalov.java.lesson6.task1;

import com.epam.alexey_shuvalov.java.lesson6.task1.model.CulinaryVegetable;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Shuvalov
 *
 */
public class FileUtils {

    public static boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.isFile() && file.exists();
    }

    public static void serializeObjectsToFile(List<CulinaryVegetable> ingredients, String filePath) {
        try (ObjectOutput output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)));)
        {
            for (CulinaryVegetable ingredient : ingredients) {
                output.writeObject(ingredient);
            }
            output.close();
        } catch (IOException ex) {
        }
    }

    public static void serializeObjectToFile(CulinaryVegetable culinaryVegetable, String filePath) {
        try (ObjectOutput output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath, true))) 
            {   @Override
                protected void writeStreamHeader() throws IOException {
                    reset();
                }
            }) 
        {
            output.writeObject(culinaryVegetable);
            output.close();
        } catch (IOException ex) {
        }
    }

    public static List<CulinaryVegetable> deserializeObjectsFromFile(String inputFile) {
        List<CulinaryVegetable> ingredients = new ArrayList();
        try (ObjectInput input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(inputFile)));) 
        {
            while (true) {
                ingredients.add((CulinaryVegetable) input.readObject());
            }
        } catch (EOFException ex) {
            return ingredients;
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
}
