package com.jivesoftware.os.jive.utils.map.store;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.jivesoftware.os.jive.utils.map.store.api.KeyValueStoreException;
import com.jivesoftware.os.jive.utils.map.store.api.ParitionedKeyValueStore;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public abstract class VariableKeySizeFileBackMapStore<K, V> implements ParitionedKeyValueStore<K, V> {

    private final int[] keySizeThresholds;
    private final FileBackMapStore<K, V>[] mapStores;

    @SuppressWarnings("unchecked")
    public VariableKeySizeFileBackMapStore(String basePathToPartitions, int[] keySizeThresholds, int payloadSize, int initialPageCapacity,
        int concurrency, V returnWhenGetReturnsNull) {

        this.keySizeThresholds = keySizeThresholds;
        this.mapStores = new FileBackMapStore[keySizeThresholds.length];
        for (int i = 0; i < keySizeThresholds.length; i++) {
            Preconditions.checkArgument(i == 0 || keySizeThresholds[i] > keySizeThresholds[i - 1], "Thresholds must be monotonically increasing");

            final int keySize = keySizeThresholds[i];
            String pathToPartitions = new File(basePathToPartitions, String.valueOf(keySize)).getAbsolutePath();
            mapStores[i] = new FileBackMapStore<K, V>(pathToPartitions, keySize, payloadSize, initialPageCapacity, concurrency, returnWhenGetReturnsNull) {
                @Override
                public String keyPartition(K key) {
                    return VariableKeySizeFileBackMapStore.this.keyPartition(key);
                }

                @Override
                public Iterable<String> keyPartitions() {
                    return VariableKeySizeFileBackMapStore.this.keyPartitions();
                }

                @Override
                public byte[] keyBytes(K key) {
                    byte[] keyBytes = VariableKeySizeFileBackMapStore.this.keyBytes(key);
                    byte[] padded = new byte[keySize];
                    System.arraycopy(keyBytes, 0, padded, 0, keyBytes.length);
                    return padded;
                }

                @Override
                public byte[] valueBytes(V value) {
                    return VariableKeySizeFileBackMapStore.this.valueBytes(value);
                }

                @Override
                public K bytesKey(byte[] bytes, int offset) {
                    return VariableKeySizeFileBackMapStore.this.bytesKey(bytes, offset);
                }

                @Override
                public V bytesValue(K key, byte[] bytes, int offset) {
                    return VariableKeySizeFileBackMapStore.this.bytesValue(key, bytes, offset);
                }
            };
        }
    }

    private FileBackMapStore<K, V> getMapStore(int keyLength) {
        for (int i = 0; i < keySizeThresholds.length; i++) {
            if (keySizeThresholds[i] >= keyLength) {
                return mapStores[i];
            }
        }
        throw new IndexOutOfBoundsException("Key is too long");
    }

    protected abstract int keyLength(K key);

    @Override
    public void add(K key, V value) throws KeyValueStoreException {
        getMapStore(keyLength(key)).add(key, value);
    }

    @Override
    public void remove(K key) throws KeyValueStoreException {
        getMapStore(keyLength(key)).remove(key);
    }

    @Override
    public V get(K key) throws KeyValueStoreException {
        return getMapStore(keyLength(key)).get(key);
    }

    @Override
    public V getUnsafe(K key) throws KeyValueStoreException {
        return getMapStore(keyLength(key)).getUnsafe(key);
    }

    @Override
    public long estimatedMaxNumberOfKeys() {
        long estimate = 0;
        for (FileBackMapStore<K, V> mapStore : mapStores) {
            estimate += mapStore.estimatedMaxNumberOfKeys();
        }
        return estimate;
    }

    public long sizeInBytes() throws IOException {
        long sizeInBytes = 0;
        for (FileBackMapStore<K, V> mapStore : mapStores) {
            sizeInBytes += mapStore.sizeInBytes();
        }
        return sizeInBytes;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        List<Iterator<Entry<K, V>>> iterators = Lists.newArrayListWithCapacity(mapStores.length);
        for (FileBackMapStore<K, V> mapStore : mapStores) {
            iterators.add(mapStore.iterator());
        }
        return Iterators.concat(iterators.iterator());
    }
}