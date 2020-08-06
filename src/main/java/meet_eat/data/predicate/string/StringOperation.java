package meet_eat.data.predicate.string;

import java.util.function.BiFunction;

public enum StringOperation implements BiFunction<String, String, Boolean> {

    CONTAIN {
        @Override
        public Boolean apply(String stringArgument, String stringBase) {
            return stringBase.contains(stringArgument);
        }
    },

    NOT_CONTAIN {
        @Override
        public Boolean apply(String stringArgument, String stringBase) {
            return !stringBase.contains(stringArgument);
        }
    },

    EQUAL {
        @Override
        public Boolean apply(String stringArgument, String stringBase) {
            return stringBase.equals(stringArgument);
        }
    },

    STARTS_WITH {
        @Override
        public Boolean apply(String stringArgument, String stringBase) {
            return stringBase.startsWith(stringArgument);
        }
    },

    ENDS_WITH {
        @Override
        public Boolean apply(String stringArgument, String stringBase) {
            return stringBase.endsWith(stringArgument);
        }
    };
}
