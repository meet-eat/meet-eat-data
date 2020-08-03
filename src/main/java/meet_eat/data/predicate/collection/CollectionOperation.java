package meet_eat.data.predicate.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.function.BiFunction;

public enum CollectionOperation implements BiFunction<Collection<?>, Collection<?>, Boolean> {

    CONTAIN_ALL {
        @Override
        public Boolean apply(Collection<?> collectionFst, Collection<?> collectionSnd) {
            return collectionSnd.containsAll(collectionFst);
        }
    },
    CONTAIN_ANY {
        @Override
        public Boolean apply(Collection<?> collectionFst, Collection<?> collectionSnd) {
            collectionSnd.removeAll(collectionFst);
            return !collectionSnd.isEmpty();
        }
    },
    CONTAIN_NONE {
        @Override
        public Boolean apply(Collection<?> collectionFst, Collection<?> collectionSnd) {
            return Collections.disjoint(collectionFst, collectionSnd);
        }
    };
}
