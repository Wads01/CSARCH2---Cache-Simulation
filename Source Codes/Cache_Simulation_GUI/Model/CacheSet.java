package Model;

import java.util.Arrays;

/**
 * The CacheSet class represents a set within a cache, containing multiple blocks.
 * It provides methods for reading, writing, and managing the contents of the set.
 */
public class CacheSet{
    private int[] blocks;

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
     * Creates a deep copy of the CacheSet.
     *
     * @return A new CacheSet with the same size and contents as the original.
     */
    public CacheSet CopyCacheSet(){
        CacheSet copiedSet = new CacheSet(this.blocks.length);
        System.arraycopy(this.blocks, 0, copiedSet.blocks, 0, this.blocks.length);
        return copiedSet;
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
     * Replaces the data at the specified index in the set.
     *
     * @param data         The new data to be placed in the set.
     * @param randomIndex  The index at which the data will be replaced.
     */
    public void replace(int data, int randomIndex){
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