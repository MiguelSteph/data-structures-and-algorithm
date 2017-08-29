package com.symboltables;

import java.util.Collection;
import java.util.Set;

/**
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 */
public interface I_TreeMapCustom<K, V> extends Iterable<K> {

    /**
     * Associates the specified value with the specified key in this map.
     * 
     * @param key
     *            key with which the specified value is to be associated
     * @param value
     *            value to be associated with the specified key
     * @return The specified value
     * @exception java.lang.NullPointerException
     *                if the specified key or value is null
     */
    V put(K key, V val);

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * TreeMap contains no mapping for the key.
     * 
     * @param key
     *            the key whose associated value is to be returned
     * 
     * @return the value to which the specified key is mapped, or null if this
     *         TreeMap contains no mapping for the key.
     *
     * @exception java.lang.NullPointerException
     *                if the specified key is null
     */
    V get(K key);
    
    /**
     * Returns true if this TreeMap contains a mapping for the specified key.
     * 
     * @param key
     *            key whose presence in this TreeMap is to be tested
     * 
     * @return true if this TreeMap contains a mapping for the specified key
     * 
     * @exception java.lang.NullPointerException
     *                if the specified key is null.
     */
    boolean containsKey(K key);

    /**
     * Returns true if this TreeMap maps one or more keys to the specified
     * value.
     * 
     * @param value
     *            value whose presence in this TreeMap is to be tested
     * 
     * @return true if this TreeMap maps one or more keys to the specified
     *         value.
     * 
     * @exception java.lang.NullPointerException
     *                if the specified value is null.
     */
    boolean containsValue(V val);

    /**
     * Returns the least key greater than or equal to the given key, or null if
     * there is no such key.
     * 
     * @exception java.lang.NullPointerException
     *                if the specified key is null
     */
    K ceilingKey(K key);

    /**
     * Returns the greatest key less than or equal to the given key, or null if
     * there is no such key.
     * 
     * @exception java.lang.NullPointerException
     *                if the specified key is null
     */
    K floorKey(K key);


    /**
     * Returns a Set view of the keys contained in this TreeMap.
     */
    Set<K> keySet();

    /**
     * Returns a Collection view of the values contained in this TreeMap.
     */
    Collection<V> values();

    /**
     * Removes the mapping for a key from this TreeMap if it is present
     * 
     * @param key
     *            key whose mapping is to be removed from the TreeMap
     *
     * @return the previous value associated with the given key, or null if
     *         there was no mapping for key.
     *
     * @exception java.lang.NullPointerException
     *                if the specified key is null
     */
    V remove(K key);

    /**
     * Returns the number of key-value mappings in this TreeMap
     * 
     * @return the number of key-value mappings in this TreeMap
     */
    int size();
    
    /**
     * Returns true if this TreeMap contains no key-value mappings.
     * 
     * @return true if this TreeMap contains no key-value mappings
     */
    boolean isEmpty();
    
    /**
     * Removes all of the mappings from this TreeMap. The TreeMap will
     * be empty after this call returns.
     */
    void clear();

}
