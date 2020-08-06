package meet_eat.data.predicate.numeric;

import java.util.function.BiFunction;

public enum DoubleOperation implements BiFunction<Double, Double, Boolean> {

    GREATER {
        @Override
        public Boolean apply(Double aDoubleFst, Double aDoubleSnd) {
            return Double.compare(aDoubleFst, aDoubleSnd) > 0;
        }
    },

    EQUAL {
        @Override
        public Boolean apply(Double aDoubleFst, Double aDoubleSnd) {
            return Double.compare(aDoubleFst, aDoubleSnd) == 0;
        }
    },

    LESS {
        @Override
        public Boolean apply(Double aDoubleFst, Double aDoubleSnd) {
            return Double.compare(aDoubleFst, aDoubleSnd) < 0;
        }
    };
}
