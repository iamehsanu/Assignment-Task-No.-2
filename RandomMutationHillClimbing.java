
package com.company;

import java.util.*;
import java.io.*;

public class RandomMutationHillClimbing {

    //The 2-D matrix to store the graph
    private int[][] ModuledependecyGraph;
    //The No. of Iterations algo is to be run on the dataSet
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

    //Question No.1
    public boolean isValidMDG() {
        int noOfNodes=ModuledependecyGraph.length;
        //To check if the matix representation of MDG is Square or not
        for(int i=0;i<noOfNodes;i++)
        {
            if(ModuledependecyGraph[i].length!=noOfNodes)
            {
                return false;
            }
        }
        //To check if the MDG has non (1 or 0 value)
        for(int i=0;i<noOfNodes;i++)
        {
            for(int j=0;j<this.ModuledependecyGraph[i].length;j++)
            {
                if(this.ModuledependecyGraph[i][j]!=0 && this.ModuledependecyGraph[i][j]!=1)
                {
                    return false;
                }
            }
        }
        //To check if the MDG is bi-directional or not
        for(int i=0;i<noOfNodes;i++)
        {
            for(int j=0;j<this.ModuledependecyGraph[i].length;j++)
            {
                if(this.ModuledependecyGraph[i][j]!= this.ModuledependecyGraph[j][i])
                {
                    return false;
                }
            }
        }

        return true;
    }

    //Question No.2
    //Initial Clustering Arrangement with each Module in Separate Cluster
    public Vector<Integer> getInitialStartingPoint()
    {
        Vector<Integer> startingPoint=new Vector<Integer>();
        for (int i=1;i<=this.ModuledependecyGraph.length;i++)
        {
            startingPoint.add(i);
        }
        return startingPoint;
    }

    //Question No.3
    //To get the Fitness Value of the Clustering based on the EVM Fitness given in Manual
    public int getFitnessValue(Vector<Integer> clusters)
    {
        int evm=0;
        int c1,c2;
        for (int i=0;i<this.ModuledependecyGraph.length-1;i++)
        {
            for(int j=i+1;j<this.ModuledependecyGraph.length;j++)
            {
                c1=clusters.get(i);
                c2=clusters.get(j);
                if (c1==c2)
                {
                    evm=evm+2*(this.ModuledependecyGraph[i][j])-1;
                }
            }
        }

        return evm;
    }
    //To get a random number between the range (lower:inclusive,upper:exclusive)
    public static int UI(int lower,int upper)
    {
        Random random = new Random();
        random.setSeed(System.nanoTime());
        return random.nextInt(upper - lower) + lower;
    }

    //Question No. 4
    //To change the Given cluster randomly and return a new Clustering Arrangement
    public Vector<Integer> smallChangeOperator(Vector<Integer> clusters) {

        int index=UI(0,this.ModuledependecyGraph.length);
        int currentClusterval=clusters.get(index);
        int newclusterval=UI(1,this.ModuledependecyGraph.length+1);
        while(newclusterval==currentClusterval)
        {
            newclusterval=UI(1,this.ModuledependecyGraph.length+1);
        }
        clusters.set(index,newclusterval);
        return clusters;
    }

    //Question No. 5
    //Munch Algorithm on the Given Module Dependency Graph
    public Vector<Integer> AlgorithmExecution()
    {
        if(isValidMDG()==false)
        {
            throw new IllegalArgumentException("MDG in In-Valid Format");
        }
        Vector<Integer> bestClusterArrangement=getInitialStartingPoint();
        Vector<Integer> newClusterArrangement;
        int oldfitness,newfitness;
        for(int i=0;i<this.noofIterations;i++)
        {
            newClusterArrangement=smallChangeOperator(bestClusterArrangement);
            oldfitness=getFitnessValue(bestClusterArrangement);
            newfitness=getFitnessValue(newClusterArrangement);
            if(newfitness>oldfitness)
            {
                bestClusterArrangement=newClusterArrangement;
            }
        }
            return bestClusterArrangement;
    }

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
                for (int i=0;i<words.length;i++)
                {
                    int Value = Integer.parseInt(words[i]);
                }
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
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("MDG given in Non-Numeric Format",e);
        }


    }



    public static void main(String[] args)
    {
        try {
            int[][] mdg = ReadMDGfromFile("C:\\Users\\DELL\\IdeaProjects\\untitled\\src\\com\\company\\file.txt");
            RandomMutationHillClimbing obj = new RandomMutationHillClimbing(30000, mdg);
            Vector<Integer> res = obj.AlgorithmExecution();
            for (int i : res) {
                System.out.print(i + " ");
            }
            System.out.print("\nThe Resultings Cluster's Fitness Value is " + obj.getFitnessValue(res));
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("OOPs !!  Something wrong happened : ");
        }
    }


}



