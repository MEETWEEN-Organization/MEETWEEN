package meetween.backend.global.config.replication;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomReplicaKeys {

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    private final List<DataSourceKey> replicaDataSourceKeys;
    private final int size;

    public RandomReplicaKeys() {
        this.replicaDataSourceKeys = List.copyOf(DataSourceKey.getReplicas());
        this.size = replicaDataSourceKeys.size();
    }

    public DataSourceKey nextKey() {
        int currentDataSourceIndex = random.nextInt(size);
        return replicaDataSourceKeys.get(currentDataSourceIndex);
    }
}
