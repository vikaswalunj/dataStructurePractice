package practice.SystemDesign;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IteratorImplementation implements Iterator<Integer>{

	    private List<Integer> examples;  //ArrayList<Integer> will be set here
	    private int          index;

	    public IteratorImplementation(List<Integer> examples) {
	        this.examples = examples;
	        index = 0;
	    }

	    @Override
	    public Integer next() {
	        if(hasNext()) {
	            return examples.get(index++);
	        } else {
	            throw new NoSuchElementException("There are no elements size = " + examples.size());
	        }
	    }

	    @Override
	    public boolean hasNext() {
	        return !(examples.size() == index);
	    }

	    @Override
	    public void remove() {
	        if(index <= 0) {
	            throw new IllegalStateException("You can't delete element before first next() method call");
	        }
	        examples.remove(--index);
	    }
}
