package net.helix.pendulum.model;

import net.helix.pendulum.storage.Persistable;
import net.helix.pendulum.utils.Serializer;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by paul on 5/6/17.
 */

 /**
 * The StateDiff model class is an implementation of the <code> Persistable </code> interface.
 */
public class StateDiff implements Persistable {
    public Map<Hash, Long> state;

    public byte[] bytes() {
        return state.entrySet().parallelStream()
                .map(entry -> ArrayUtils.addAll(entry.getKey().bytes(), Serializer.serialize(entry.getValue())))
                .reduce(ArrayUtils::addAll)
                .orElse(new byte[0]);
    }
    public void read(byte[] bytes) {
        int i;
        state = new HashMap<>();
        if(bytes != null) {
            for (i = 0; i < bytes.length; i += Hash.SIZE_IN_BYTES + Long.BYTES) {
                state.put(HashFactory.ADDRESS.create(bytes, i, Hash.SIZE_IN_BYTES),
                        Serializer.getLong(Arrays.copyOfRange(bytes, i + Hash.SIZE_IN_BYTES, i + Hash.SIZE_IN_BYTES + Long.BYTES)));
            }
        }
    }

    @Override
    public byte[] metadata() {
        return new byte[0];
    }

    @Override
    public void readMetadata(byte[] bytes) {
        // Does nothing
    }

    @Override
    public boolean merge() {
        return false;
    }
}
