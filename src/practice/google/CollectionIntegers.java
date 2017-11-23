package practice.google;

import java.util.ArrayList;
import java.util.HashMap;


public class CollectionIntegers {

	HashMap<String, Object> collectionOfIntegers = new HashMap<String, Object>();
	
	CollectionIntegers()
	{
		collectionOfIntegers.put("List", new ArrayList<Integer>());
		collectionOfIntegers.put("Add", 0);
		collectionOfIntegers.put("Multiply", 1);
	}
	
	void append(int x)
	{
		ArrayList<Integer> list = (ArrayList<Integer>) collectionOfIntegers.get("List");
		Integer addResult = (Integer) collectionOfIntegers.get("Add");
		Integer multiplyResult = (Integer) collectionOfIntegers.get("Multiply");

		list.add(x);
		addResult += x;
		multiplyResult *= x;

		collectionOfIntegers.put("List", list);
		collectionOfIntegers.put("Add", addResult);
		collectionOfIntegers.put("Multiply", multiplyResult);
	}
	
	int get(int idx)
	{
		ArrayList<Integer> list = (ArrayList<Integer>) collectionOfIntegers.get("List");
		
		return list.get(idx);
	}
	
	void add_to_all(int x)
	{
		Integer addResult = (Integer) collectionOfIntegers.get("Add");
		addResult += x;
		collectionOfIntegers.put("Add", addResult);
	}

	void multiply_to_all(int x)
	{
		Integer multiplyResult = (Integer) collectionOfIntegers.get("Multiply");
		multiplyResult *= x;
		collectionOfIntegers.put("Add", multiplyResult);
	}
}
