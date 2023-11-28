package Controller;

import Model.*;
import java.util.*;
import java.io.*;

/**
 * The CacheSimulator class represents a terminal-based cache simulation program.
 * It allows users to configure cache parameters, choose program flow, and view simulation details.
 */
public class CacheSimulator{
    private static final Scanner s = new Scanner(System.in);
    private final int programFlow;
    public Cache cache;
    public Memory memory;
    
    /**
     * Constructor for the CacheSimulator class.
     * Initializes the cache and memory based on user input and executes the selected program flow.
     */
    public CacheSimulator(){
        int choice;
        System.out.println("8-WAY BSA: Random Replacement Algorithm");
        int blockSize = acceptInput("Enter Valid Block Size 2^(1-5 Words): ", 1, 5);
        int setSize = acceptInput("Enter Set Size (1-8 Block/s): ", 1, 8);
        choice = acceptInput("Enter Main Memory Size Unit (1)Words | (2)Blocks: ", 1, 2);
        int mmSize;
        if (choice == 1){
            int mmSizeExponent = acceptInput("Enter Number of Words 2^(1-20): ", 1, 20);
            mmSize = (int) (Math.pow(2, mmSizeExponent) / blockSize);
        }
        else
            mmSize = acceptInput("Enter The Number of Blocks(1-32768): ", 1, 32768);
        System.out.println();

        System.out.println("Choose Program Flow: ");
        System.out.println("(1) Sequential Sequence");
        System.out.println("(2) Random Sequence");
        System.out.println("(3) Mid-Repeat Blocks");
        
        programFlow = acceptInput("Choice: ", 1, 3);
        System.out.println();

        cache = new Cache(blockSize, setSize);
        memory = new Memory(mmSize);

        getProgramFlow(programFlow);

        printProgramDetails(cache, mmSize, blockSize, setSize, programFlow);

        choice = acceptInput("Would you like to generate a text file of these details? (1)Yes | (2)No: ", 1, 2);
        if (choice == 1)
            generateTextFile(cache, mmSize, blockSize, setSize, programFlow);
    }

    /**
     * Accepts user input within a specified range.
     *
     * @param prompt The prompt to display to the user.
     * @param low    The lower bound of the acceptable range.
     * @param high   The upper bound of the acceptable range.
     * @return The user's valid input within the specified range.
     */
    private int acceptInput(String prompt, int low, int high){
        int store = -1;

        System.out.print(prompt);
        do{
            try{
                store = s.nextInt();
                s.nextLine();
                if (store < low || store > high)
                    System.out.print("Invalid Input! Input -> ");
            }
            catch(InputMismatchException e){
                s.nextLine();
                System.out.print("Invalid Input! Input -> ");
            }
        }while(store < low || store > high);

        return store;
    }

    /**
     * Executes the specified program flow based on user input.
     *
     * @param programFlow The chosen program flow.
     */
    private void getProgramFlow(int programFlow){
        switch (programFlow) {
            case 1:
                sequentialSequence();
                break;
            case 2:
                randomSequence();
                break;
            case 3:
                midRepeatBlocks();
        }
    }

    /**
     * Simulates a sequential memory access sequence
     * up to 2n cache block and repeats the sequence four times
     */
    private void sequentialSequence(){
        int numCacheBlocks = cache.getNumBlocks();
        int sequenceLength = 2 * numCacheBlocks;
        System.out.println(sequenceLength);
    
        for (int reps = 0; reps < 4; reps++){
            for (int i = 0; i < sequenceLength; i++){
                 System.out.print(i + " ");
                memory.toCache(cache, i);
            }
            System.out.println();
        }
    }

    /**
     * Simulates a random memory access sequence containing 4n blocks
     */
    private void randomSequence(){
        Random random = new Random();
        int numCacheBlocks = 4 * cache.getNumBlocks();
    
        for (int i = 0; i < cache.getNumBlocks() * 4; i++){
            int rand = random.nextInt(numCacheBlocks);
            System.out.print(rand + " ");
            memory.toCache(cache, rand);
        }
        System.out.println();
    }

    /**
     * Simulates a memory access sequence with mid-repeat blocks which
     * starts at block 0, then repeat the sequence in the middle two times up to n-1 blocks, after
     * which continue up to 2n.
     */
    private void midRepeatBlocks(){
        int numCacheBlocks = cache.getNumBlocks();
        for (int reps = 0; reps < 4; reps++){

            memory.toCache(cache, 0);
            System.out.println(0);

            for (int i = 0; i < 2; i++){
                for (int j = 1; j < numCacheBlocks; j++){
                    memory.toCache(cache, j);
                    System.out.print(j + " ");
                }
                System.out.println();
            }
            
            for (int k = numCacheBlocks; k < numCacheBlocks*2; k++){
                memory.toCache(cache, k);
                System.out.print(k + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints the details of the cache simulation program to the console.
     *
     * @param cache       The cache object.
     * @param mmSize      The size of the main memory.
     * @param blockSize   The size of each block in the cache.
     * @param setSize     The number of blocks in a set.
     * @param programFlow The chosen program flow.
     */
    private void printProgramDetails(Cache cache, int mmSize, int blockSize, int setSize, int programFlow){
        String[] programs = {"Sequential Sequence", "Random Sequence", "Mid-Repeat Blocks"};
        System.out.println("Type of Cache Memory: 8-way BSA");
        System.out.println("Replacement Algorithm: Random (random.nextInt(setSize))");
        System.out.println("Read Policy: Non-Load Through");
        System.out.println("Program Flow: " + programs[programFlow - 1]);
        System.out.println("Cache Memory Size: 512 words or 32 blocks");
        System.out.println("Cache Line Size: 16 words");
        System.out.println("Main Memory Size: " + mmSize + " blocks or " + mmSize * blockSize + " words");
        System.out.println("Block Size: " + blockSize + " words");
        System.out.println("Set Size: " + setSize + " blocks");
        System.out.println();
        System.out.println(cache.getCacheDetails());
    }

    /**
     * Generates a text file containing details of the cache simulation program.
     *
     * @param cache       The cache object.
     * @param mmSize      The size of the main memory.
     * @param blockSize   The size of each block in the cache.
     * @param setSize     The number of blocks in a set.
     * @param programFlow The chosen program flow.
     */
    private void generateTextFile(Cache cache, int mmSize, int blockSize, int setSize, int programFlow){
        String[] programs = {"Sequential Sequence", "Random Sequence", "Mid-Repeat Blocks"};
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("cachedetails.txt"))){
            writer.write("Type of Cache Memory: 8-way BSA\n");
            writer.write("Replacement Algorithm: Random (random.nextInt(setSize))\n");
            writer.write("Read Policy: Non-Load Through\n");
            System.out.println("Program Flow: " + programs[programFlow - 1]);
            writer.write("Cache Memory Size: 512 words or 32 blocks\n");
            writer.write("Cache Line Size: 16 words\n");
            writer.write("Main Memory Size: " + mmSize + " blocks or " + mmSize * blockSize + " words\n");
            writer.write("Block Size: " + blockSize + " words\n");
            writer.write("Set Size: " + setSize + " blocks\n\n");
            writer.write(cache.getCacheDetails());
            writer.close();
    
            System.out.println("Cache details written to 'cachedetails.txt'.");
        }
        catch (IOException e){
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}