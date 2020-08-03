package meet_eat.data.predicate.string;

import java.util.function.BiFunction;

public enum StringOperation implements BiFunction<String, String, Boolean> {

    CONTAIN {
        @Override
        public Boolean apply(String stringFst, String stringSnd) {
            return stringFst.contains(stringSnd);
        }
    },
    NOT_CONTAIN {
        @Override
        public Boolean apply(String stringFst, String stringSnd) {
            return !stringFst.contains(stringSnd);
        }
    },
    EQUAL {
        @Override
        public Boolean apply(String stringFst, String stringSnd) {
            return stringFst.equals(stringSnd);
        }
    },
    STARTS_WITH {
        @Override
        public Boolean apply(String stringFst, String stringSnd) {
            return stringFst.startsWith(stringSnd);
        }
    },
    ENDS_WITH {
        @Override
        public Boolean apply(String stringFst, String stringSnd) {
            return stringFst.endsWith(stringSnd);
        }
    };
}
