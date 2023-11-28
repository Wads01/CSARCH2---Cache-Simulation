package Model;

import java.util.Random;
import java.util.Arrays;

/**
 * The CacheSet class represents a set within a cache, containing multiple blocks.
 * It provides methods for reading, writing, and managing the contents of the set.
 */
public class CacheSet{
    private final int[] blocks;
    private static final Random rand = new Random();

    /**
     * Constructor for the CacheSet class. Initializes the set with the specified set size.
     * Fill the blocks with -1 to represent an empty block.
     * 
     * @param setSize The number of blocks in the set.
     */
    public CacheSet(int setSize){
        blocks = new int[setSize];
        Arrays.fill(blocks, -1);
    }

    /**
     * Checks if the set is full or if all blocks in the set are occupied.
     *
     * @return true if the set is full, false otherwise.
     */
    public boolean isFull(){
        for (int data : blocks){
            if (data == -1)
                return false;
        }
        return true;
    }

    /**
     * Reads data from the set and checks if the target data is present.
     *
     * @param target The data to be searched for in the set.
     * @return true if the target data is found, false otherwise.
     */
    public boolean read(int target){
        for (int data : blocks){
            if (data == target)
                return true;
        }
        return false;
    }

    /**
     * Writes data to the set if there is space (set is not full).
     *
     * @param data The data to be written to the set.
     */
    public void write(int data){
        if(!isFull()){
            for (int i = 0; i < blocks.length; i++){
                if (blocks[i] == -1){
                    blocks[i] = data;
                    break;
                }
            }
        }   
    }

    /**
     * Replaces a randomly chosen block in the set with new data.
     *
     * @param data The data to be placed in the set.
     */
    public void replace(int data){
        int randomIndex = rand.nextInt(blocks.length); //0-7
        blocks[randomIndex] = data;
    }

    /**
     * Retrieves the array of blocks within the set.
     *
     * @return The array of blocks within the set.
     */
    public int[] getBlocks(){
        return blocks;
    }
}