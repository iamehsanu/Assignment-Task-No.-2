
package com.company;

import java.util.*;
import java.io.*;

public class RandomMutationHillClimbing {


    //Pathname="C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\com\\company\\file.txt"
    public static List<int[]> ReadMDGfromFile(String Pathname)
    {
        try {
            //Pathname: will Correspond to the input file in the running system
            File myObj = new File(Pathname);
            Scanner myReader = new Scanner(myObj);
            List<int[]> mdg = new ArrayList<int[]>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] words = data.split(" ");
                int[] values = Arrays.stream(words)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                mdg.add(values);
            }
            myReader.close();
            return mdg;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        return null;
    }

    public static void main(String[] args)
    {
        List<int[]> mdg= ReadMDGfromFile("C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\com\\company\\file.txt");

        if (mdg!=null) {
            for (int[] eachRow : mdg) {
                System.out.println(Arrays.toString(eachRow));
            }
        }

    }

}



