package meet_eat.data.predicate.chrono;

import java.time.chrono.ChronoLocalDateTime;
import java.util.function.BiFunction;

public enum ChronoLocalDateTimeOperation implements BiFunction<ChronoLocalDateTime<?>, ChronoLocalDateTime<?>, Boolean> {

    BEFORE {
        @Override
        public Boolean apply(ChronoLocalDateTime<?> chronoLocalDateTimeArgument, ChronoLocalDateTime<?> chronoLocalDateTimeBase) {
            return chronoLocalDateTimeBase.isBefore(chronoLocalDateTimeArgument);
        }
    },

    EQUAL {
        @Override
        public Boolean apply(ChronoLocalDateTime<?> chronoLocalDateTimeArgument, ChronoLocalDateTime<?> chronoLocalDateTimeBase) {
            return chronoLocalDateTimeBase.isEqual(chronoLocalDateTimeArgument);
        }
    },

    AFTER {
        @Override
        public Boolean apply(ChronoLocalDateTime<?> chronoLocalDateTimeArgument, ChronoLocalDateTime<?> chronoLocalDateTimeBase) {
            return chronoLocalDateTimeBase.isAfter(chronoLocalDateTimeArgument);
        }
    };
}
