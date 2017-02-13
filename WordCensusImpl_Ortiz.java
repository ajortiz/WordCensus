package census;

import java.util.*;

public class WordCensusImpl_Ortiz implements WordCensus 
{
	//data members
	private HashMap <String, Integer> wordToCountMap = new HashMap<String, Integer>();
	private List<String> sortedWordListByFrequency;
	//private Araist<String> listOfAllValuesFromWordToCountMap;
	private boolean isSorted;
	
	public WordCensusImpl_Ortiz(List<String> wordList)
	{
		this.isSorted=false;
		for(int i = 0; i < wordList.size(); i++)
		{
			incrementCount(wordList.get(i));
		}
		
		calculateSortedWordListByFrequency();
	}
	 
	private void calculateSortedWordListByFrequency() 
	{
		// TODO Auto-generated method stub
		this.sortedWordListByFrequency = new ArrayList<String>(wordToCountMap.keySet());
		Collections.sort(this.sortedWordListByFrequency, new Comparator<String>() {
			public int compare (String s1, String s2) {
				Integer value1 = wordToCountMap.get(s1);
				Integer value2 = wordToCountMap.get(s2);
				if (value2.compareTo(value1)==0){
					return s1.compareTo(s2);
				}
				else {
					return value2.compareTo(value1);
				}
			}
		});
		this.isSorted=true;
		
		
		//get all keys into new array list,
		/*run through list until find highest value, hold on to key-----(key, Value)
		 * then at the end of going through put that value in sortedWordListByFrequency
		 * remove that value,
		 * run through again
//		 */
//		String key = "";
//		int maxValue;
//		
//		wordToCountMap.keySet().removeAll(listOfAllValuesFromWordToCountMap.keySet());
//		target.putAll(tmp);
//		for(int i = 0; i < wordToCountMap.size(); i++)
//		{
//			
//			
//		}
//			
//		sortedWordListByFrequency = jhjh;
	}

	@Override
	public int getCount(String word) 
	{
		word = word.toLowerCase();
		if(wordToCountMap.get(word)==null)
		{
			return 0;
		}
		return wordToCountMap.get(word);
		
	}
	
	private void incrementCount(String word)
	{
		//get value of key in map, increment, put it back
		word = word.toLowerCase();
		if(!wordToCountMap.containsKey(word))
		{
			wordToCountMap.put(word, 0);
		}
		assert wordToCountMap.containsKey(word);
		
		int old_count = wordToCountMap.get(word);
		int new_count = old_count + 1;
		wordToCountMap.put(word, new_count);
		 //wordListToIntMap.replace(word, getCount(word)+1);
	}
	//part of pre: i > 0
	//part of post: i < getDistinctWordCount() ==> getCount(getWordWithRank(i)) >= getCount(getWordWithRank(i + 1))"
	@Override
	public String getWordWithRank(int i) 
	{
		
		assert i > 0;
		if (!isSorted) {
			calculateSortedWordListByFrequency();
		}
		return this.sortedWordListByFrequency.get(i-1);
	}

	@Override
	public int getDistinctWordCount() 
	{
		
		return wordToCountMap.size();
	}
}
