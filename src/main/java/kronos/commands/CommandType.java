package kronos.commands;

import kronos.exceptions.KronosException;

/**
 * Represents the different types of commands.
 */
public enum CommandType {
    ADD,
    DELETE,
    EXIT,
    MARK,
    UNMARK,
    FIND,
    LIST,
    TAG,
    UNTAG;

    /**
     * Converts a string keyword to its corresponding CommandType.
     * @param keyword The string representation of the command type.
     * @return The CommandType corresponding to the keyword.
     * @throws KronosException If the keyword does not match any CommandType.
     */
    public static CommandType fromString(String keyword) throws KronosException {
        if (keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event")) {
            return ADD;
        } else if (keyword.equals("delete")) {
            return DELETE;
        } else if (keyword.equals("bye")) {
            return EXIT;
        } else if (keyword.equals("mark")) {
            return MARK;
        } else if (keyword.equals("unmark")) {
            return UNMARK;
        } else if (keyword.equals("find")) {
            return FIND;
        } else if (keyword.equals("list")) {
            return LIST;
        } else if (keyword.equals("tag")) {
            return TAG;
        } else if (keyword.equals("untag")) {
            return UNTAG;
        } else {
            throw new KronosException("I can't help you with that command!");
        }
    }
}

