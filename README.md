# CSARCH2---Cache-Simulation
## [Demo Video](https://www.youtube.com/watch?v=3pEerq1uPlE)

## Test Cases Input

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/15976dbc-8c9c-4e62-abb2-23ac747d170d" />
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/79b676b2-1859-445d-9201-857ddc949c64" />
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/1479e26a-e2ec-4cba-b8b8-4c81e307e080" />
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/bea876e3-72fa-488d-a754-e66a1042da5c" />
</p



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

When the cache is empty, it is represented by the value -1. Because 0%4 equals 0, we can write the value to set 0. Furthermore, if the stack is empty, the item will be stored at the lowest available cache block. It's worth noting that when the cache is full, we must replace an old value with the new value we want to save. To identify which cache index to employ, a replacement algorithm is required.

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/bbdceac7-8c60-4ddb-bce5-a0c52d690b9c" />
</p

In case that the cache is full, we will use the random replacement algorithm.
```
 public void replace(int data, int randomIndex){
        blocks[randomIndex] = data;
    }
```

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/b7b62a46-3545-4732-952a-22413390e3e9" />
</p

Based on the output of the random algorithm, the data value is placed in cache block 7, which belongs to the set of 32%4=0. 

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/b86ad3c1-900a-49a6-8276-530b4954373b" />
</p

In instances where the replacement value generated at random fails to match the index of the cache hit, the corresponding block value remains unaltered. Instead, the system proceeds to execute a read operation from the cache memory.

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/9e27f5af-3bc5-4d50-80a9-4da56c015c8c" />
</p

This is the final output of the 8-way (Random Replacement algorithm) - Sequential Sequence

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/43bf8ecf-2892-407f-b98e-edf074298dd6" />
</p

Then we also print out our final cache details:<br>
Total Access Count = 256; Since our number of cache blocks is 32 and the Random sequence is 4x64 blocks.<be>

Cache Hits = 45<br>

Cache Misses = 211<br>

Miss Penalty = 52ns; Since our block size was 5 words. And the formula is (1+(blocksize*10)+1)<br>

Average Memory Access Time = 44.62ns<br>
>; We get this by first calculating our hit rate and our miss rate. Which is 45/256 and 211/256 respectively.<br>
>; cacheAccessTime is 10 and missPenalty is 52ns as calculated before.<br>
>; And then we use the formula: hitRate x cacheAccessTime + missRate x missPenalty<br>
>; ((45/256) x 10 + (211/256) x 52) = 44.62ns<br>

Total Memory Access Time = 11422.0ns<br>
>; We use the formula: cacheHits x cacheAccessTime + cacheMisses x missPenalty<br>
>; cacheAccessTime is 10 and missPenalty is 52ns as calculated before.<br>
>; (45 x 10 + 211 x 52) = 5438.0ns<br>
 <p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/130729389/d6f263e0-f426-4d5e-ae89-e9fcc554275e" />
</p>



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
After providing the input for the test case, we then get this screen including the details for our Mid Repeat Test Cases:

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/106811460/1735b098-ca08-40e3-8a9b-435e169d213b" />
</p>

For this test case, we will start at block 0, then it will repeat the sequence in the middle 2 times until n-1 blocks, which will continue on to 2n. This sequence will then be repeated for 4 times. We then proceed to fill up the first set, starting at (0). Modulo it by 4 which will give us 0. We will put this in Set 0 and in index 1. We then update the cache misses and total memory access time.

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/106811460/e6dce526-962d-4502-975f-13fb0ce06c67" />
</p>

The sequence will continue from (0) to (31) where we modulo it again by 4 to determine its designated set which will get us 3 then we will put it in Set 3 and in Index 8. Update the Cache Miss and total memory access time.

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/106811460/fc8c713b-f068-4ad6-83cf-758f5163077d" />
</p>

Since this test case is a mid-repeat sequence, we will then go back to (1) to repeat the middle sequence for the second time until n-1 blocks. Modulo by 4 we get 1 so we will put it in Set 1 and since 1 exists in the Set, we will just update the Cache Hit and the total memory access time.

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/106811460/1d2c9973-f4e3-4f82-9bc8-fde84dd029c8" />
</p>

After repeating the sequence in the middle for the second time, we will then proceed to (32) until (63). We modulo (32) by 4 to get the set location and we will get 0. We now put it in Set 0 and we will replace 24 in index 7 with (32). We then update the Cache Miss and total memory access time.

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/106811460/f0a9656f-99b4-42ad-a3d0-6472944f0e87" />
</p>

### Final Result of the Cache Memory View for Mid Repeat Test Case
After the replacement algorithm reaches 2n, the sequence will be repeated 4 times and it will come to a point where we will get the following final cache values:

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/106811460/519b2cf9-83a6-4a11-87f8-e916ae995d8c" />
</p>

### The final cache details would be:

Total Access Count = 380<br>

Cache Hits = 110<br>

Cache Misses = 270<br>

Miss Penalty = 52ns<br>

Average Memory Access Time = 39.84ns<br>
>; We get this by first calculating our hit rate and our miss rate. Which is 110/380 and 270/380 respectively.<br>
>; cacheAccessTime is 10 and missPenalty is 52ns as calculated before.<br>
>; And then we use the formula: hitRate x cacheAccessTime + missRate x missPenalty<br>
>; ((110/380) x 10 + (270/380) x 52) = 39.84ns<br>

Total Memory Access Time = 15140.0ns<br>
>; We use the formula: cacheHits x cacheAccessTime + cacheMisses x missPenalty<br>
>; cacheAccessTime is 10 and missPenalty is 52ns as calculated before.<br>
>; (110 x 10 + 270 x 52) = 15140.0ns<br>

<p align="center">
  <img src="https://github.com/Wads01/CSARCH2---Cache-Simulation/assets/106811460/b59e9153-82e3-458c-b1f4-56df6a46fec1" />
</p>
