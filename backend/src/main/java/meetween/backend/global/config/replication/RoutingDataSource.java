package meetween.backend.global.config.replication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static meetween.backend.global.config.replication.DataSourceKey.REPLICA_1;
import static meetween.backend.global.config.replication.DataSourceKey.SOURCE;

public class RoutingDataSource extends AbstractRoutingDataSource {

    private static final Logger log = LoggerFactory.getLogger(RoutingDataSource.class);
    private final RandomReplicaKeys randomReplicaKeys = new RandomReplicaKeys();

    @Override
    protected Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

        if (isReadOnly) {
            return randomReplicaKeys.nextKey();
        }

        return SOURCE;
    }
}
