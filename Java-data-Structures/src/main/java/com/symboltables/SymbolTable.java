package com.symboltables;

/**
 * Symbol table is an important data structure created and maintained by
 * compilers in order to store information about the occurrence of various
 * entities such as variable names, function names, objects, classes,
 * interfaces, etc. Symbol table is used by both the analysis and the synthesis
 * parts of a compiler. A symbol table may serve the following purposes
 * depending upon the language in hand: - To store the names of all entities in
 * a structured form at one place. - To verify if a variable has been declared.
 * - To implement type checking, by verifying assignments and expressions in the
 * source code are semantically correct. - To determine the scope of a name
 * (scope resolution).
 * 
 * A symbol table can be implemented in one of the following ways: - Linear
 * (sorted or unsorted) list - Binary Search Tree - Hash table
 * 
 * 
 * {@link https://www.tutorialspoint.com/compiler_design/compiler_design_symbol_table.htm}
 * 
 * @author STEPHANE MIGUEL KAKANAKOU (Skakanakou@gmail.com)
 *
 * @param <Key>
 * @param <Value>
 */

public interface SymbolTable<Key, Value> extends Iterable<Key> {

    /**
     * Associates the specified value with the specified key in this
     * SymbolTable. If the SymbolTable previously contained a mapping for the
     * given key, the old value is replaced by the specified value.
     * 
     * @param key
     *            key with which the specified value is to be associated
     * @param value
     *            value to be associated with the specified key
     * 
     * @exception java.lang.NullPointerException
     *                if the specified key or value is null
     */
    void put(Key key, Value value);

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * SymbolTable contains no mapping for the key.
     * 
     * @param key
     *            the key whose associated value is to be returned
     * 
     * @return the value to which the specified key is mapped, or null if this
     *         SymbolTable contains no mapping for the key.
     *
     * @exception java.lang.NullPointerException
     *                if the specified key is null
     */
    Value get(Key key);

    /**
     * Removes the mapping for a key from this SymbolTable if it is present
     * 
     * @param key
     *            key whose mapping is to be removed from the SymbolTable
     *
     * @return the previous value associated with the given key, or null if
     *         there was no mapping for key.
     *
     * @exception java.lang.NullPointerException
     *                if the specified key is null
     */
    Value delete(Key key);

    /**
     * Removes all of the mappings from this SymbolTable. The SymbolTable will
     * be empty after this call returns.
     */
    void clear();

    /**
     * Returns true if this SymbolTable contains a mapping for the specified
     * key.
     * 
     * @param key
     *            key whose presence in this SymbolTable is to be tested
     * 
     * @return true if this SymbolTable contains a mapping for the specified key
     * 
     * @exception java.lang.NullPointerException
     *                if the specified key is null.
     */
    boolean containsKey(Key key);

    /**
     * Returns true if this SymbolTable maps one or more keys to the specified value.
     * 
     * @param value value whose presence in this SymbolTable is to be tested
     * 
     * @return true if this SymbolTable maps one or more keys to the specified value.
     * 
     * @exception java.lang.NullPointerException
     *                if the specified value is null.
     */
    boolean containsValue(Value value);

    /**
     * Returns true if this SymbolTable contains no key-value mappings.
     * 
     * @return true if this SymbolTable contains no key-value mappings
     */
    boolean isEmpty();

    /**
     * Returns the number of key-value mappings in this SymbolTable
     * 
     * @return the number of key-value mappings in this SymbolTable
     */
    int size();

}
