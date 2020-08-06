package meet_eat.data.predicate.chrono;

import java.time.chrono.ChronoLocalDateTime;
import java.util.function.BiFunction;

public enum ChronoLocalDateTimeOperation implements BiFunction<ChronoLocalDateTime<?>, ChronoLocalDateTime<?>, Boolean> {

    /**
     * The before operation.
     *
     * @see ChronoLocalDateTime#isBefore(ChronoLocalDateTime)
     */
    BEFORE {
        @Override
        public Boolean apply(ChronoLocalDateTime<?> chronoLocalDateTimeArgument, ChronoLocalDateTime<?> chronoLocalDateTimeBase) {
            return chronoLocalDateTimeBase.isBefore(chronoLocalDateTimeArgument);
        }
    },

    /**
     * The equal operation.
     *
     * @see ChronoLocalDateTime#equals(Object)
     */
    EQUAL {
        @Override
        public Boolean apply(ChronoLocalDateTime<?> chronoLocalDateTimeArgument, ChronoLocalDateTime<?> chronoLocalDateTimeBase) {
            return chronoLocalDateTimeBase.isEqual(chronoLocalDateTimeArgument);
        }
    },

    /**
     * The after operation.
     *
     * @see ChronoLocalDateTime#isAfter(ChronoLocalDateTime)
     */
    AFTER {
        @Override
        public Boolean apply(ChronoLocalDateTime<?> chronoLocalDateTimeArgument, ChronoLocalDateTime<?> chronoLocalDateTimeBase) {
            return chronoLocalDateTimeBase.isAfter(chronoLocalDateTimeArgument);
        }
    };
}
