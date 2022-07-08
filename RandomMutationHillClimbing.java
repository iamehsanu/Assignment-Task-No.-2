
package com.company;

import java.util.*;
import java.io.*;

public class RandomMutationHillClimbing {

    //The 2-D matrix to store the graph
    private int[][] ModuledependecyGraph;
    //The Iterations algo is to be run on the dataSet
    private int noofIterations;

    //Default Constructor Not allowed
    public RandomMutationHillClimbing()
    {
        throw new IllegalArgumentException("Non-Parameterized Constructor not allowed");
    }
    //Parameterized Constructor
    public RandomMutationHillClimbing(int Iterationsnum,int[][] mdg)
    {
        this.ModuledependecyGraph=mdg;
        this.noofIterations=Iterationsnum;
    }


    //Pathname="C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\com\\company\\file.txt"
    //To read the MDG from the file
    public static int[][] ReadMDGfromFile(String Pathname)
    {
        try {
            //Pathname: will Correspond to the input file in the running system
            File file = new File(Pathname);
            Scanner myReader = new Scanner(file);
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
            int arr[][];
            arr=new int[mdg.size()][];
            int i=0;
            for(int[] row:mdg)
            {
                arr[i++]=row;
            }
            return arr;
        } catch (FileNotFoundException e) {

            throw new IllegalArgumentException("File Does not exist",e);

        }
    }



    public static void main(String[] args)
    {

        int [][] mdg = ReadMDGfromFile("C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\com\\company\\file.txt");
        RandomMutationHillClimbing obj =new RandomMutationHillClimbing(10,mdg);


    }


}



