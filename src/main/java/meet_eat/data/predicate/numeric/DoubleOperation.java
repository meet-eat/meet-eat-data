package meet_eat.data.predicate.numeric;

import java.util.function.BiFunction;

public enum DoubleOperation implements BiFunction<Double, Double, Boolean> {

    GREATER {
        @Override
        public Boolean apply(Double doubleArgument, Double doubleBase) {
            return Double.compare(doubleBase, doubleArgument) > 0;
        }
    },

    EQUAL {
        @Override
        public Boolean apply(Double doubleArgument, Double doubleBase) {
            return Double.compare(doubleBase, doubleArgument) == 0;
        }
    },

    LESS {
        @Override
        public Boolean apply(Double doubleArgument, Double doubleBase) {
            return Double.compare(doubleBase, doubleArgument) < 0;
        }
    };
}
