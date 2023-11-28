package Model;

import java.util.Arrays;


/**
 * The Memory class represents the main memory of the cache simulation system.
 * It provides methods to manage memory blocks, add new blocks, and interact with the cache.
 */
public class Memory {
    private final int[] blocks;

    /**
     * Constructor for the Memory class. Initializes the memory with the specified size.
     * Fill the blocks with -1 to represent an empty block.
     * 
     * @param mmSize The size of the main memory in blocks.
     */
    public Memory(int mmSize) {
        this.blocks = new int[mmSize];
        Arrays.fill(blocks, -1);
    }

    /**
     * Adds a new block to the memory if there is space (memory is not full).
     *
     * @param data The data to be added to the memory as a new block.
     */
    public void addBlock(int data){
        if (!isFull()){
            for (int i = 0; i < blocks.length; i++){
                if (blocks[i] == -1) {
                    blocks[i] = data;
                    break;
                }
            }
        }
    }

    /**
     * Transfers data from the memory to the cache if the data is not already present in the cache.
     *
     * @param cache The cache to interact with.
     * @param data  The data to be transferred to the cache.
     */
    public void toCache(Cache cache, int data){
        if (cache.read(data))
            return;
        else
            cache.write(data);
    }

    /**
     * Checks if the memory is full, i.e., if all blocks in the memory are occupied.
     *
     * @return true if the memory is full, false otherwise.
     */
    private boolean isFull(){
        for (int data : blocks){
            if (data == -1)
                return false;
        }
        return true;
    }

    /**
     * Retrieves the array of blocks within the memory.
     *
     * @return The array of blocks within the memory.
     */
    public int[] getBlocks(){
        return blocks;
    }
}
