package bootcamps.turkcell.common.utilities.formats;

import bootcamps.turkcell.common.events.Event;

import java.text.MessageFormat;

public class Information {
    public static class Consume {
        public static <T extends Event> String log(T event) {
            return MessageFormat.format("Consumed: {0} --> ({1})", event.getClass().getSimpleName(), event);
        }
    }

    public static class Produce {
        public static <T extends Event> String log(T event, String topics) {
            return MessageFormat.format("Produce: {{0}} --> {1}", topics, event.toString());
        }
    }

}
