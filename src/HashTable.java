public class HashTable {
    private DataItem[] hashArray; //array holds hash table
    private int arraySize;
    private DataItem nonItem; //for deleted items

    public HashTable(int size) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1); //deleted item key is -1
    }

    public void displayTable() {
        System.out.println("Table: ");
        for(int i=0; i<arraySize; i++) {
            if(hashArray[i] != null) 
                System.out.println(hashArray[i].getKey() + " ");
            else
                System.out.println("** ");
        }
        System.out.println();
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }

    // FIND //
    public DataItem find(int key) {
        int hashVal = hashFunc(key);

        while(hashArray[hashVal] != null) {
            if(hashArray[hashVal].getKey() == key){ //found
                return hashArray[hashVal];
            }
            hashVal++;
            hashVal = hashVal % arraySize;
        }
        return null; //not found
    } //end of find()

    // INSERT //
    public void insert(DataItem item) { //Insert a DataItem
        int key = item.getKey();
        int hashVal = hashFunc(key);

        while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            hashVal++;
            hashVal = hashVal % arraySize; //wrap around if needed
        }
        hashArray[hashVal] = item;
    } //end insert()

    // Quadratic Probe insert //
    public void insertQuadraticProbe(DataItem item) {//Insert a DataItem with Quadratic Probing
        int key = item.getKey();
        int hashVal = hashFunc(key);
        int step = 1;

        while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            hashVal = hashVal + (step * step);
            step++;
            hashVal %= arraySize;//wrap if necessary
        }
        hashArray[hashVal] = item;
    }//End of insertQuadraticProbe()

    // DELETE //
    public DataItem delete(int key) {
        int hashVal = hashFunc(key);

        while(hashArray[hashVal] != null) {
            if(hashArray[hashVal].getKey() == key){
                DataItem temp = hashArray[hashVal]; //save the item to be returned
                hashArray[hashVal] = nonItem; //replace item with -1 (delete it)
                return temp; //return DataItem that was deleted
            }
            hashVal++;
            hashVal = hashVal % arraySize; //wrap if needed
        }
        return null; //not found
    }//end of delete()
}
