# Data Structure and Algorithm in Kotlin

Classical data structure and algorithm practice in Kotlin.

- [Data Structure](#Data-Structure)
    - [Map](#map)
        - [HashMap](#HashMap)
    
## Data Structure

### List
    singly linked list
    doubly linked list

### Array

### Map

#### HashMap
HashMap has a key and value. Basic hash bin `node`, used for most entries, which is normaly an array-based implementation. HashMap hashes the key and save it in a node table (Node<K, V>[] table), which is an array. key-hash is the index of that table.

So, if `no hash collisions`

- HashMap.get(key) = `Search is O(1)`
- HashMap.remove(key) = `Deletion is O(1)`
- HashMap.put(key, value) = `Insertion is O(1)`

But,

- HashMap has memory overhead, because how it holds the node is array, it might lead to have so many unused place
- The order of nodes, is based on hash, not insertion order
- A HashMap should not be 75%, if it gets close, it gets `resize and rehash = O(N)`

If has `hash collisions`

- Search can be O(N), since HashMap.get(key) always check from first node to the next, until find the right one

`Visual Example`
    
    // hash collisions, if we look for nodec, O(N)
    [HASH1] nodea -> nodeb -> nodec 
    
    // no collisions, if we look for nodec, O(1)
    [HASH1] nodea
    [HASH2] nodeb
    [HASH3] nodec

#### TreeMap


## HashTable

## Algorithm

[BACK](#Data-Structure-and-Algorithm-in-Kotlin)
