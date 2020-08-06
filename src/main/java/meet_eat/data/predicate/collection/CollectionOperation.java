package meet_eat.data.predicate.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.function.BiFunction;

public enum CollectionOperation implements BiFunction<Collection<?>, Collection<?>, Boolean> {

    CONTAIN_ALL {
        @Override
        public Boolean apply(Collection<?> collectionArgument, Collection<?> collectionBase) {
            if (collectionArgument.isEmpty()) {
                return true;
            }
            return collectionBase.containsAll(collectionArgument);
        }
    },

    CONTAIN_ANY {
        @Override
        public Boolean apply(Collection<?> collectionArgument, Collection<?> collectionBase) {
            if (collectionArgument.isEmpty()) {
                return true;
            }
            int sizeBeforeRemoval = collectionBase.size();
            collectionBase.removeAll(collectionArgument);
            int sizeAfterRemoval = collectionBase.size();
            return sizeBeforeRemoval != sizeAfterRemoval;
        }
    },

    CONTAIN_NONE {
        @Override
        public Boolean apply(Collection<?> collectionArgument, Collection<?> collectionBase) {
            return Collections.disjoint(collectionArgument, collectionBase);
        }
    };
}
