/*
 * Copyright 2013 Jive Software, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.jivesoftware.os.jive.utils.row.column.value.store.inmemory;

import com.jivesoftware.os.jive.utils.row.column.value.store.api.RowColumnValueStore;
import com.jivesoftware.os.jive.utils.row.column.value.store.api.RowColumnValueStoreMarshaller;
import com.jivesoftware.os.jive.utils.row.column.value.store.api.SetOfSortedMapsImplInitializer;
import com.jivesoftware.os.jive.utils.row.column.value.store.api.timestamper.Timestamper;
import java.io.IOException;


/**
 *
 * @author jonathan.colt
 */
public class InMemorySetOfSortedMapsImplInitializer implements SetOfSortedMapsImplInitializer<RuntimeException> {

    @Override
    public <T, R, C, V> RowColumnValueStore<T, R, C, V, RuntimeException> initialize(
            String tableNameSpace, String tableName, String columnFamilyName,
            RowColumnValueStoreMarshaller<T, R, C, V> marshaller, Timestamper timestamper) throws IOException {
        return new RowColumnValueStoreImpl<>();
    }

    @Override
    public <T, R, C, V> RowColumnValueStore<T, R, C, V, RuntimeException> initialize(
            String tableNameSpace, String tableName, String columnFamilyName, int ttlInSeconds,
            int minVersions, int maxVersions,
            RowColumnValueStoreMarshaller<T, R, C, V> marshaller, Timestamper timestamper) throws IOException {
        return new RowColumnValueStoreImpl<>();
    }
}
