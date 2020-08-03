package meet_eat.data.predicate.chrono;

import java.time.chrono.ChronoLocalDateTime;
import java.util.function.BiFunction;

public enum ChronoLocalDateTimeOperation implements BiFunction<ChronoLocalDateTime<?>, ChronoLocalDateTime<?>, Boolean> {

    BEFORE {
        @Override
        public Boolean apply(ChronoLocalDateTime<?> chronoLocalDateTimeFst, ChronoLocalDateTime<?> chronoLocalDateTimeSnd) {
            return chronoLocalDateTimeFst.isBefore(chronoLocalDateTimeSnd);
        }
    },
    EQUAL {
        @Override
        public Boolean apply(ChronoLocalDateTime<?> chronoLocalDateTimeFst, ChronoLocalDateTime<?> chronoLocalDateTimeSnd) {
            return chronoLocalDateTimeFst.isEqual(chronoLocalDateTimeSnd);
        }
    },
    AFTER {
        @Override
        public Boolean apply(ChronoLocalDateTime<?> chronoLocalDateTimeFst, ChronoLocalDateTime<?> chronoLocalDateTimeSnd) {
            return chronoLocalDateTimeFst.isAfter(chronoLocalDateTimeSnd);
        }
    };
}
