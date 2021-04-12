package election_system_model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.function.IntFunction;

public class Set<T> implements Collection<T> {
	private Vector<T> collection;
	
	public Set() {
		collection=new Vector<T>();
	}

	@Override
	public int size() {
		return collection.size();
	}

	@Override
	public boolean isEmpty() {
		if(collection.size()==0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		return collection.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		Iterator<T> t=collection.iterator();
		return t;
	}

	@Override
	public Object[] toArray() {
		int size= collection.size();
		Object[] temp= new Object[size];
		for(int i=0;i<size;i++) {
			temp[i]= collection.get(i);
		}
		return temp;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return collection.toArray(a);
	}

	@Override
	public boolean add(T e) {
		for(int i=0;i<collection.size();i++) {
			if(collection.get(i).equals(e)) {
				return false;
			}
		}
		collection.add(e);
		return true;
	}

	@Override
	public boolean remove(Object o) {
		for(int i=0;i<collection.size();i++) {
			if(collection.get(i).equals(o)) {
				collection.remove(o);
				return true;	
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		
		return collection.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		Object[] c2=c.toArray();
		for(int i=0;i<c.size();i++) {
			if(!(collection.get(i).equals(c2[i]))) {
				collection.add((T) c2[i]); 
				return true;
			}
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return collection.removeAll(c);
		
	}

	@Override
	public boolean retainAll(Collection<?> c) {
	return	collection.retainAll(c);
		
	}

	@Override
	public void clear() {
		collection.clear();
		
	}

	public Vector<T> getCollection() {
		return collection;
	}

	public T getAt(int i) {
		for(int j=0;j<collection.size();j++) {
			if(j==i) {
				return collection.get(i);
			}
		}return null;
	}
}