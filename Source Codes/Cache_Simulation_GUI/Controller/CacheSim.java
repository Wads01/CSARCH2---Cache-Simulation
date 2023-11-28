package Controller;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import Model.*;

/**
 * The CacheSim class is responsible for managing the cache simulation program.
 * It initializes the cache and memory, sets program parameters, and executes the simulation.
 */
public class CacheSim {
    private static CacheSim cacheSim;
    private Cache cache;
    private Memory memory;
    private int blockSize;
    private int setSize;
    private int mmSize;
    private int programFlow;


    /**
     * Gets the singleton instance of CacheSim. If it doesn't exist, creates a new instance.
     *
     * @return The CacheSim instance.
     */
    public static CacheSim GetCacheSim(){
        if (cacheSim == null)
            cacheSim = new CacheSim();

        return cacheSim;
    }

    /**
     * Starts the specified program flow for cache simulation.
     */
    public void startProgramFlow(){
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
    
        for (int reps = 0; reps < 4; reps++){
            for (int i = 0; i < sequenceLength; i++)
                memory.toCache(cache, i);
        }
    }

    /**
     * Simulates a random memory access sequence containing 4n blocks
     */
    private void randomSequence(){
        Random random = new Random();
        int numCacheBlocks = 4 * cache.getNumBlocks();
    
        for (int i = 0; i < 32 * 4; i++)
            memory.toCache(cache, random.nextInt(numCacheBlocks));
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

            for (int i = 0; i < 2; i++){
                for (int j = 1; j < numCacheBlocks; j++)
                    memory.toCache(cache, j);
            }
            
            for (int k = numCacheBlocks; k < numCacheBlocks*2; k++)
                memory.toCache(cache, k);
        }
    }

    /**
     * Initializes the cache and memory with the specified parameters.
     */
    public void InitializeCache_and_Memory(){
        cache = new Cache(blockSize, setSize);
        memory = new Memory(mmSize);
    }

    /**
     * Gets the current cache instance.
     *
     * @return The Cache instance.
     */
    public Cache GetCache(){
        return cache;
    }

    /**
     * Sets the block size for the cache.
     *
     * @param blockSize The block size to set.
     */
    public void SetBlockSize(int blockSize){
        this.blockSize = blockSize;
    }

    /**
     * Sets the set size for the cache.
     *
     * @param setSize The set size to set.
     */
    public void SetSetSize(int setSize){
        this.setSize = setSize;
    }

    /**
     * Sets the main memory size for the simulation.
     *
     * @param mmSize The main memory size to set.
     */
    public void SetMMSize(int mmSize){
        this.mmSize = mmSize;
    }

    /**
     * Sets the program flow for the cache simulation.
     *
     * @param programFlow The program flow to set.
     */
    public void SetProgramFlow(int programFlow){
        this.programFlow = programFlow;
    }

    /**
     * Gets the block size for the cache.
     *
     * @return The block size.
     */
    public int GetBlockSize(){
        return blockSize;
    }

    /**
     * Gets the set size for the cache.
     *
     * @return The set size.
     */
    public int GetSetSize(){
        return setSize;
    }

    /**
     * Gets the main memory size for the simulation.
     *
     * @return The main memory size.
     */
    public int GetMMSize(){
        return mmSize;
    }

    /**
     * Generates a text file containing details of the cache simulation.
     */
    public void generateTextFile(){
        String[] programs = {"Sequential Sequence", "Random Sequence", "Mid-Repeat Blocks"};
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("cachedetails.txt"))){
            writer.write("Type of Cache Memory: 8-way BSA\n");
            writer.write("Replacement Algorithm: Random (random.nextInt(setSize))\n");
            writer.write("Read Policy: Non-Load Through\n");
            writer.write("Program Flow: " + programs[programFlow - 1] + "\n");
            writer.write("Cache Memory Size: 512 words or 32 blocks\n");
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
