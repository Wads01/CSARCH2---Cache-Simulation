package Model;

import java.math.*;
import java.util.*;

/**
 * The Cache class represents a cache system with multiple sets.
 * It provides methods to simulate cache operations, and also calculate performance metrics.
 */
public class Cache{
    private ArrayList<CacheSet> cacheSets;
    private int numBlocks = 32; //blocks
    private int blockSize;
    private int numSets;
    private int cacheHits = 0;
    private int cacheMisses = 0;
    private int setSize;

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

        cacheSets = new ArrayList<>();
        for (int i = 0; i < numSets; i++)
            cacheSets.add(new CacheSet(setSize));
    }

    /**
     * Reads data from the cache and updates the cache states.
     * Checks if the target data is present in the cache.
     *
     * @param data The data to be read from the cache.
     * @return true if the target data is found in the cache, false otherwise.
     */
    public boolean read(int data){
        int setIndex = data % numSets;
        if (cacheSets.get(setIndex).read(data)){
            cacheHits++;
            return true;
        }
        else{
            cacheMisses++;
            return false;
        }
    }

    /**
     * Writes data to the cache and updates the cache states.
     *
     * @param data The data to be written to the cache.
     */
    public void write(int data){
        int setIndex = data % numSets;

        if (cacheSets.get(setIndex).isFull()){
            cacheSets.get(setIndex).replace(data);
        }
        else
            cacheSets.get(setIndex).write(data);
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
     * Retrieves the list of cache sets.
     *
     * @return The list of cache sets.
     */
    public ArrayList<CacheSet> getCacheSet(){
        return cacheSets;
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
     * Retrieves the total number of cache sets.
     *
     * @return The total number of cache sets.
     */
    public int getNumSets(){
        return numSets;
    }

    /**
     * Retrieves the set size or associativity of the cache.
     *
     * @return The set size or associativity of the cache.
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
