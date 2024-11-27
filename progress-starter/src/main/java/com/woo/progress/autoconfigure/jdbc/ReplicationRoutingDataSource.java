package com.woo.progress.autoconfigure.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    private enum Type {
        READ, WRITE
    }

    private ReplicationRoutingDataSource() {
    }

    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("### Transactional: name('{}'), read-only('{}')",
                TransactionSynchronizationManager.getCurrentTransactionName(),
                TransactionSynchronizationManager.isCurrentTransactionReadOnly()
        );

        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? Type.READ : Type.WRITE;
    }

    public static ReplicationRoutingDataSource of(final DataSource writeDataSource, final DataSource readDataSource) {

        Map<Object, Object> datasourceMap = new HashMap<>();
        datasourceMap.put(Type.WRITE, writeDataSource);
        datasourceMap.put(Type.READ, readDataSource);

        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();
        routingDataSource.setTargetDataSources(datasourceMap);
        routingDataSource.setDefaultTargetDataSource(readDataSource);

        return routingDataSource;
    }
}
