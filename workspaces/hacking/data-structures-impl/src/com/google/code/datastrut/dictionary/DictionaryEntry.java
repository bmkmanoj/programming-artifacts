package com.google.code.datastrut.dictionary;

public class DictionaryEntry<Key, Value> {

  private final Key key;
  private final Value value;

  public DictionaryEntry(Key newKey, Value newValue) {
    this.key = newKey;
    this.value = newValue;
  }

  public Key getKey() {
    return key;
  }

  public Value getValue() {
    return value;
  }
}
