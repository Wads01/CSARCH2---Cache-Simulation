# CSARCH2---Cache-Simulation
## [Demo Video](https://www.youtube.com/watch?v=3pEerq1uPlE)

## Test Cases Input

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/15976dbc-8c9c-4e62-abb2-23ac747d170d" />
</p

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/5e7b0887-9d26-487e-b25b-ec615e84a5da" />
</p

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/15976dbc-8c9c-4e62-abb2-23ac747d170d" />
</p

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/15976dbc-8c9c-4e62-abb2-23ac747d170d" />
</p
 


![image](https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/37cdce57-0551-444d-8847-996317f1bdf0)
![image](https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/3dd30297-040f-4d6a-8e58-688a6c76d6d2)
![image](https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/f1d65994-70fd-4b87-bcba-59bec9fe178c)

### Sequential Sequence
```python
# Simulates a sequential memory access sequence up to 2n cache block and repeats the sequence four times
    private void sequentialSequence(){
        int numCacheBlocks = cache.getNumBlocks();
        int sequenceLength = 2 * numCacheBlocks;
    
        for (int reps = 0; reps < 4; reps++){
            for (int i = 0; i < sequenceLength; i++)
                memory.toCache(cache, i);
        }
    }
```
### Random
```python
#  Simulates a random memory access sequence containing 4n blocks
    private void randomSequence(){
        Random random = new Random();
        int numCacheBlocks = 4 * cache.getNumBlocks();
    
        for (int i = 0; i < 32 * 4; i++)
            memory.toCache(cache, random.nextInt(numCacheBlocks));
    }
```

After providing all the inputs, we end up with this screen with the details for our Random Sequence Test Case:
<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/98384276/35313679-ad15-49d7-8508-5879cec5989d" />
</p>

Since this test case has a random sequence containing 4*32 blocks, there is no telling what number comes next. Also, we will fill out the set first, before using our random replacement algorithm. For our first one here, we have (111), when we modulo it by 4, we get 3. So we put it at Set 3 and at index 1. Update the cache misses and the total memory access time.
<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/98384276/e683ce09-ca4d-4de9-b996-66db1ad4d075" />
</p>

Next we get (42), when we modulo it by 4, we get 2. So we put it at Set 2 and at index 1. Update the cache misses and the total memory access time.
<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/98384276/0986844c-249e-4dfd-ad28-c6ade4463061" />
</p>

So we keep doing this until the set where the next value is going to be placed is already full which will cause us to use our random replacement algorithm. In this next one, we encounter our first cache hit. We can see here that in Set 1, (125) is at index 1. 
<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/98384276/0da95e6d-b55f-4401-84af-a1f91028b4b0" />
</p>

And our next value also happens to be (125). Since it is a cache hit, we do nothing and increment our cache hits and our total memory access time.
<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/98384276/09e51455-6e4f-4ec4-b6ef-3a665bda1421" />
</p>

We then encounter our first instance wherein we will use our replacement algorithm. Our next data is (2), so we put it in Set 2. However, as you can see below, the entire set is already filled.
<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/98384276/f77e7075-55ed-49e8-b313-28e266dc1b81" />
</p>

So we use the replacement algorithm, in which it returned 1. So we replaced (6) and put (2) now at index 1. Update the cache misses and the total memory access time.
<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/98384276/320472a5-3819-475e-bb5d-4fec343dcda8" />
</p>

We then reach a point where the replacement algorithm is now used every time since all the sets are always full. After doing all the necessary replacements, we now have our final sets of values.
<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/98384276/472dfe42-a342-4679-8f25-f97581f55e0d" />
</p>


Then we also print out our final cache details:<br>
Total Access Count = 128; Since our number of cache blocks is 32 and the Random sequence is 4x32 blocks.<be>

Cache Hits = 29<br>

Cache Misses = 99<br>

Miss Penalty = 52ns; Since our block size was 5 words. And the formula is (1+(blocksize*10)+1)<br>

Average Memory Access Time = 42.48ns<br>
>; We get this by first calculating our hit rate and our miss rate. Which is 29/128 and 99/128 respectively.<br>
>; cacheAccessTime is 10 and missPenalty is 52ns as calculated before.<br>
>; And then we use the formula: hitRate x cacheAccessTime + missRate x missPenalty<br>
>; ((29/128) x 10 + (99/128) x 52) = 42.48ns<br>

Total Memory Access Time = 5438.0ns<br>
>; We use the formula: cacheHits x cacheAccessTime + cacheMisses x missPenalty<br>
>; cacheAccessTime is 10 and missPenalty is 52ns as calculated before.<br>
>; (29 x 10 + 99 x 52) = 5438.0ns<br>
 <p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/98384276/6d2647c7-73e6-4901-8b00-543f2d4854b1" />
</p>


### Mid-Repeat
```python
# Simulates a memory access sequence with mid-repeat blocks which starts at block 0,
# then repeat the sequence in the middle two times up to n-1 blocks,
# after which continue up to 2n
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
```
