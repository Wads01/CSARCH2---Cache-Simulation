package Model;

import java.util.*;

/**
 * The CacheState class represents the state of the cache at a specific point in a cache simulation.
 * It includes information such as cache sets, access counts, total access time, set index, and block index.
 */
public class CacheState{
    public List<CacheSet> cacheSets;
    public int cacheHits;
    public int cacheMisses;
    public double totalAccessTime;
    public int setIndex;
    public int randBlockIndex;
    public int data;

    /**
     * Constructor for the CacheState class. Creates a new CacheState object with the specified parameters.
     *
     * @param cacheSets        The list of cache sets representing the state of the cache.
     * @param cacheHits        The number of cache hits in the cache state.
     * @param cacheMisses      The number of cache misses in the cache state.
     * @param setSize          The size of each set in the cache.
     * @param totalAccessTime  The total access time in the cache state.
     * @param setIndex         The index of the cache set in the cache state.
     * @param randBlockIndex   The index of the randomly selected block in the cache set during a replace operation.
     * @param data             The data being processed in the cache state.
     */
    public CacheState(List<CacheSet> cacheSets, int cacheHits, int cacheMisses, int setSize,
                      double totalAccessTime, int setIndex, int randBlockIndex, int data){
        this.cacheSets = new ArrayList<>();
        for (CacheSet originalSet : cacheSets)
            this.cacheSets.add(originalSet.CopyCacheSet());
    
        this.cacheHits = cacheHits;
        this.cacheMisses = cacheMisses;
        this.totalAccessTime = totalAccessTime;
        this.setIndex = setIndex;
        this.randBlockIndex = randBlockIndex;
        this.data = data;
    }
}
