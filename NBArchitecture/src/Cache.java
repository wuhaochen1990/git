//cache using random strategy just for data
public class Cache {

	//the max address of the cache
	public static final int MAX_ADDRESS=99;
	
	//cache address and content
	public static final int[] cache_address = new int[MAX_ADDRESS+1];
	public static final int[] cache_content = new int[MAX_ADDRESS+1];
	
	//cache miss flag
	public static int miss = 0;

	//judge whether it has a miss
	public static void isMiss(int memoryAddress){
		int index;
		index = memoryAddress % 100;
		if(cache_address[index] == memoryAddress){
			//cache hit
			miss = 1;
		}else{
			//cache miss
			miss = 0;
		}
	}
	//cache hit operation
	public static int cacheHit(int memoryAddress){
		if(miss == 1){
			//cache hit
			int index;
			index = memoryAddress % 100;
			return cache_content[index];
		}else{
			return 0;
		}
	}
	
	//cache miss, replace the cache with new memory content
	public static void cacheMiss(int memoryAddress){
		if(miss == 0){
			int index;
			index = memoryAddress % 100;
			cache_address[index] = memoryAddress;
			cache_content[index] = Memory.getDataFromMemory(memoryAddress);
		}
	}
	//cache write back
	public static void cacheWrite(int memoryAddress, int content){
		int index = memoryAddress % 100;
		cache_address[index] = memoryAddress;
		cache_content[index] = content;
	}
	
}
