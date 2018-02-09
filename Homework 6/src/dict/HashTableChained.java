/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

    /**
     *  Place any data fields here.
     **/
    private DList[] table;
    private int size;
    private int N;


    /**
     *  Construct a new empty hash table intended to hold roughly sizeEstimate
     *  entries.  (The precise number of buckets is up to you, but we recommend
     *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
     **/

    public HashTableChained(int sizeEstimate) {
        int raw = (int) (sizeEstimate * 1.4);
        if (isPrime(raw)) table = new DList[raw];
        else{
            while (!isPrime(raw)){
                raw++;
            }
            table = new DList[raw];
        }
        size = 0;
        N = raw;
        for (int i = 0; i < N; i++){
            table[i] = new DList();
        }
        // Your solution here.
    }

    private boolean isPrime(int num){
        if (num < 2 || num % 2 == 0) return false;
        for (int i = 2; i * i < num; i++){
            if (num % i == 0) return false;
        }
        return true;
    }
    /**
     *  Construct a new empty hash table with a default size.  Say, a prime in
     *  the neighborhood of 100.
     **/

    public HashTableChained() {
        table = new DList[101];
        size = 0;
        N = 101;
        // Your solution here.
    }

    /**
     *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
     *  to a value in the range 0...(size of hash table) - 1.
     *
     *  This function should have package protection (so we can test it), and
     *  should be used by insert, find, and remove.
     **/

    int compFunction(int code) {
        // Replace the following line with your solution.
        return mod(mod((997 * code + 991), 16908799), N);
    }

    private static int mod(int input, int modNum){
        if (input % modNum < 0){
            return input % modNum + modNum;
        }else {
            return input % modNum;
        }
    }

    /**
     *  Returns the number of entries stored in the dictionary.  Entries with
     *  the same key (or even the same key and value) each still count as
     *  a separate entry.
     *  @return number of entries in the dictionary.
     **/

    public int size() {
        // Replace the following line with your solution.
        return size;
    }

    /**
     *  Tests if the dictionary is empty.
     *
     *  @return true if the dictionary has no entries; false otherwise.
     **/

    public boolean isEmpty() {
        // Replace the following line with your solution.
        return size == 0;
    }

    /**
     *  Create a new Entry object referencing the input key and associated value,
     *  and insert the entry into the dictionary.  Return a reference to the new
     *  entry.  Multiple entries with the same key (or even the same key and
     *  value) can coexist in the dictionary.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the key by which the entry can be retrieved.
     *  @param value an arbitrary object.
     *  @return an entry containing the key and value.
     **/

    public Entry insert(Object key, Object value) {
        Entry inserted = new Entry();
        inserted.key = key;
        inserted.value = value;
        int loc = compFunction(key.hashCode());
        table[loc].insertBack(inserted);
        size++;
        // Replace the following line with your solution.
        return inserted;
    }

    /**
     *  Search for an entry with the specified key.  If such an entry is found,
     *  return it; otherwise return null.  If several entries have the specified
     *  key, choose one arbitrarily and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     **/

    public Entry find(Object key) {
        int loc = compFunction(key.hashCode());
        DList dest_list = table[loc];
        DListNode cur = dest_list.front();
        if (cur == null) return null;
        else {
            return (Entry) cur.item;
        }
        // Replace the following line with your solution.
    }

    /**
     *  Remove an entry with the specified key.  If such an entry is found,
     *  remove it from the table and return it; otherwise return null.
     *  If several entries have the specified key, choose one arbitrarily, then
     *  remove and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     */

    public Entry remove(Object key) {
        int loc = compFunction(key.hashCode());
        size--;
        if (find(key) == null) return null;
        else{
            Entry res = (Entry) table[loc].front().item;
            table[loc].remove(table[loc].front());
            return res;
        }
        // Replace the following line with your solution.
    }

    /**
     *  Remove all entries from the dictionary.
     */
    public void makeEmpty() {
        for (int i = 0; i < table.length; i++){
            table[i] = new DList();
        }
        size = 0;
        // Your solution here.
    }

    public String collisions() {
        String collisions = new String("(Bucket:Collisions):");
        int numCollisions = 0;
        for (int i = 0; i < N; i++) {
            if (table[i].length() > 1) {
                collisions = collisions + ",("+i+":"+ (table[i].length()-1)+")";
                numCollisions += table[i].length()-1;
            } else {
                collisions = collisions + ",("+i+":0)";
            }
        }
        collisions += " Number of collisions: " + numCollisions;
        return collisions;
    }

    public static void main(String[] args) {
        System.out.println("Constructuing new HashTable");
        HashTableChained hash1 = new HashTableChained(10);
        System.out.println(hash1.N);
        hash1.insert(1,1);
        hash1.insert(2,1);
        hash1.insert(3,1);
        hash1.insert(4,1);
        hash1.insert(5,1);
        System.out.println("After adding keys 1,2,3,4,5, Collisions are:"+ hash1.collisions());
        hash1.insert(1,1);
        hash1.insert(2,1);
        hash1.insert(3,1);
        hash1.insert(4,1);
        hash1.insert(5,1);
        System.out.println("After adding keys 1,2,3,4,5, Collisions are:"+ hash1.collisions());
        hash1.insert(6,1);
        hash1.insert(7,3);
        hash1.insert(8,1);
        hash1.insert(9,1);
        hash1.insert(10,1);
        System.out.println("After adding keys 6,7,8,9,10 Collisions are:"+ hash1.collisions());
        hash1.remove(1);
        hash1.remove(2);
        hash1.remove(3);
        hash1.remove(4);
        hash1.remove(5);
        System.out.println("After removing keys 1,2,3,4,5 Collisions are:"+ hash1.collisions());
        System.out.println("hash1.entries is:"+hash1.size);
        System.out.println("key 2 has value: "+ hash1.find(2).value);
        System.out.println("key 7 has value: "+ hash1.find(7).value);
        System.out.println("find key 11: "+ hash1.find(11));
        System.out.println("isEmpty?"+ hash1.isEmpty());
        hash1.makeEmpty();
        System.out.println("Removing all entries");
        System.out.println("isEmpty?"+ hash1.isEmpty());



    }
}