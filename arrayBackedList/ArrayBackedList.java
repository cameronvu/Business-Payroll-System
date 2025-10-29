package arrayBackedList;

/**
 * The ArrayBackedList class simulates many similar functions of traditional
 * Java arrays. ArrayBackedList objects are able to store any non-primitive
 * type of data. Do to it's simplicity, the ArrayBackedList class does not
 * have the ability to remove or re-arrange elements and may only retrieve 
 * general information about the current object, add objects to the array, 
 * and update the length of the list if specified conditions are met. This class
 * contains fields pertaining to the initial desired length of the list, also 
 * called increment, the index or the position, the size or number of objects
 * being stored in the array, and finally, a primitive array of objects. 
 */

public class ArrayBackedList {

	private Object arr[];
	private int increment;
	private int index;
	private int size;
	
    /**
     * Constructor that initializes the non-static field, arr, as an array of 
     * a desired size. If the desired size is zero or negative, the size
     * defaults to one.
     * 
     * @param increment A primitive integer that represents the desired size of
     * 					the current object's array
     */
	public ArrayBackedList(int increment) {
        if (increment < 1) 
        	increment = 1; 
    	/* initializes a new array of type Object for the specified length */
        arr = new Object[increment];
    	this.increment = increment;
    	index = 0;
    	size = 0;
    }
	
	/**
	 * Creates a new array with the updated amount of indexes. The method also
	 * copies the current values stored in the current array to the 
	 * returned array so no data is lost.
	 * 
	 * @return An array of updated indexes containing the current object's 
	 *         stored data
	 */
	public Object[] newArray() {
		/* declares a new array of type Object and initializes it as an 
		 * array of Objects with a length equal to the length of the current 
		 * object's array plus the value of increment */
		Object[] newArr = new Object[arr.length + increment];
		/* iterates through the length of the current object's array and 
		 * updates the values in the new array to match */
		for (int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i];
		}
		return newArr;
	}

    /**
     * Stores the object, newElement, in the first unused index of the current
     * object's non-static array, arr. The method updates the size of the array
     * by the static field, increment, if there is no additional space to 
     * add an element when the method is called.
     * 
     * @param newElement An object that is to be added to the current object's
     * 					 non-static field, arr
     * @return true if the element to be added is not null; false otherwise
     */
	public boolean add(Object newElement) {
        if (newElement != null) {
        	/* checks if the addition of the object will be longer than
        	 * the length of the current object's array */
        	if (index >= arr.length) {
        		/* calls to the newArray() method */
        		arr = newArray();
        	} 
        	/* sets the next open value equal to the element to be stored */
        	arr[index] = newElement;
        	index++;
        	size++;
        	return true;
        }
        return false;
    }

    /**
     * Returns the size, or the amount of elements currently being stored 
     * in the current object's arr field.
     * 
     * @return A primitive integer value storing the amount of times that
     *         true was returned by the add() method
     */
	public int getSize() {
		return size;
    }

	/**
	 * Returns the capacity of the array, arr, which is how many total elements
	 * the array can store.
	 * 
	 * @return A primitive integer representing the capacity of arr
	 */
    public int getCapacity() {
        return arr.length;
    }

    /**
     * Returns a reference to the object that is being stored at a desired
     * position.
     * 
     * @param pos A primitive integer representing the desired position of the 
     * 			  object to be returned
     * @return A reference to the object stored in position, pos, of the current
     * 		   object's arr field
     */
    public Object get(int pos) {
        /* ensures that the specified position is valid */
    	if (pos >= 0 && pos < arr.length) {
        	return arr[pos];
        } else {
        	return null;
        }
    }
    
    

}
