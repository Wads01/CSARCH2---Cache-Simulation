package Model;

import java.math.*;
import java.util.*;

/**
 * The Cache class represents a cache system with multiple sets.
 * It provides methods to simulate cache operations, and also calculate performance metrics.
 */
public class Cache{
    private static final Random rand = new Random();
    private ArrayList<CacheSet> cacheSets;
    private ArrayList<CacheState> cacheStates;
    private int numBlocks = 32; //blocks
    private int blockSize;
    private int numSets;
    private int cacheHits = 0;
    private int cacheMisses = 0;
    private int setSize;
    private int randBlockIndex;
    private int data;

    /**
     * Constructor for the Cache class. Initializes the cache with the specified block size and set size.
     *
     * @param blockSize The size of each block in the cache.
     * @param setSize   The number of blocks in each set.
     */
    public Cache(int blockSize, int setSize){
        this.setSize = setSize;
        this.blockSize = blockSize;
        numSets = (int) Math.ceil((double) numBlocks / setSize);

        cacheStates = new ArrayList<>();
        cacheSets = new ArrayList<>();
        for (int i = 0; i < numSets; i++)
            cacheSets.add(new CacheSet(setSize));
    }

    /**
     * Reads data from the cache for the specified memory address.
     *
     * @param data The memory address to read from.
     * @return true if the data is found in the cache (cache hit), false if not (cache miss).
     */
    public boolean read(int data){
        this.data = data;
        int setIndex = data % numSets;
        if (cacheSets.get(setIndex).read(data)){
            cacheHits++;
            cacheStateChanged();
            return true;
        }
        else{
            cacheMisses++;
            return false;
        }
    }

    /**
     * Writes data to the cache for the specified memory address.
     *
     * @param data The memory address to write to.
     */
    public void write(int data){
        int setIndex = data % numSets;

        if (cacheSets.get(setIndex).isFull()){
            int randomIndex = rand.nextInt(setSize);
            this.randBlockIndex = randomIndex;
            cacheSets.get(setIndex).replace(data, randomIndex);
        }
        else{
            this.randBlockIndex = -1;
            cacheSets.get(setIndex).write(data);
        }

        cacheStateChanged();
    }

    /**
     * Calculates the miss penalty for cache misses (NLT).
     *
     * @return The calculated miss penalty in nanoseconds.
     */
    public int calcMissPenalty(){
        return 1 + (10 * blockSize) + 1;
    }

    /**
     * Calculates the average Memory Access Time (MAT) based on hit rate and miss rate.
     *
     * @return The calculated average MAT in nanoseconds.
     */
    public double calcAvgMAT(){
        int missPenalty = calcMissPenalty();
        int cacheAccessTime = 10;
        int totalAccessCount = cacheHits + cacheMisses;
        double hitRate = (double) cacheHits/totalAccessCount;
        double missRate = (double) cacheMisses/totalAccessCount;
        double avgMAT = hitRate * cacheAccessTime + missRate * missPenalty;

        return round(avgMAT, 2);
    }

    /**
     * Calculates the total Memory Access Time (MAT) based on cache hits and misses.
     *
     * @return The calculated total MAT in nanoseconds.
     */
    public double calcTotalMAT(){
        int missPenalty = calcMissPenalty();
        int cacheAccessTime = 10;
        double totalMAT = cacheHits * cacheAccessTime + cacheMisses * missPenalty;

        return round(totalMAT, 2);
    }

    /**
     * Retrieves the list of cache sets within the cache.
     *
     * @return The list of cache sets.
     */
    public ArrayList<CacheSet> getCacheSet(){
        return cacheSets;
    }

    /**
     * Retrieves the list of cache states.
     *
     * @return The list of cache states.
     */
    public ArrayList<CacheState> getCacheStates(){
        return cacheStates;
    }

    /**
     * Retrieves the total number of blocks in the cache.
     *
     * @return The total number of blocks in the cache.
     */
    public int getNumBlocks(){
        return numBlocks;
    }

    /**
     * Retrieves the number of cache hits.
     *
     * @return The number of cache hits.
     */
    public int getCacheHits(){
        return cacheHits;
    }

    /**
     * Retrieves the number of cache misses.
     *
     * @return The number of cache misses.
     */
    public int getCacheMisses(){
        return cacheMisses;
    }

    /**
     * Retrieves the total number of sets in the cache.
     *
     * @return The total number of sets in the cache.
     */
    public int getNumSets(){
        return numSets;
    }

    /**
     * Retrieves the set size of the cache.
     *
     * @return The set size of the cache.
     */
    public int getSetSize(){
        return setSize;
    }

     /**
     * Generates a string representation of the cache details, including set contents and performance metrics.
     *
     * @return A string with cache details.
     */
    public String getCacheDetails(){
        StringBuilder details = new StringBuilder();
    
        details.append("Cache Contents:\n");
    
        for (int i = 0; i < numSets; i++){
            details.append("Set ").append(i).append(":\n");
            CacheSet set = cacheSets.get(i);
            for (int j = 0; j < setSize; j++)
                details.append("  Block ").append(j).append(": ").append(set.getBlocks()[j]).append("\n");
    
            details.append("\n");
        }
    
        int total = cacheHits + cacheMisses;
        details.append("Total Access Count: ").append(total).append("\n");
        details.append("Cache Hits: ").append(cacheHits).append("\n");
        details.append("Cache Misses: ").append(cacheMisses).append("\n");
        details.append("Miss Penalty: ").append(calcMissPenalty()).append("ns\n");
        details.append("Average MAT: ").append(calcAvgMAT()).append("\n");
        details.append("Total MAT: ").append(calcTotalMAT()).append("\n");
    
        return details.toString();
    }

    /**
     * Updates the cache state and adds a new CacheState object to the list of cache states.
     */
    public void cacheStateChanged(){
        int setIndex = data % numSets;
        double totalAccessTime = calcTotalMAT();
        CacheState cacheState = new CacheState(cacheSets, cacheHits, cacheMisses, setSize,
                                               totalAccessTime, setIndex, randBlockIndex, data);
        cacheStates.add(cacheState);
    }

    /**
     * Rounds a double value to the specified number of decimal places.
     *
     * @param value  The value to be rounded.
     * @param places The number of decimal places to round to.
     * @return The rounded value.
     */
    private double round(double value, int places){
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
