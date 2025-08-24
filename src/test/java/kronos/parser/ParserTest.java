package kronos.parser;

import org.junit.jupiter.api.Test;
import kronos.commands.Command;
import kronos.commands.AddCommand;
import kronos.commands.ListCommand;
import kronos.commands.DeleteCommand;
import kronos.commands.MarkCommand;
import kronos.commands.UnmarkCommand;
import kronos.commands.ExitCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void ValidRequestTest() {

        String request = "todo Run";
        Parser parser = new Parser();

        Command command = parser.parse(request);
        assertTrue(command instanceof AddCommand);

        request = "list";
        command = parser.parse(request);
        assertTrue(command instanceof ListCommand);

        request = "delete 1";
        command = parser.parse(request);
        assertTrue(command instanceof DeleteCommand);

        request = "mark 1";
        command = parser.parse(request);
        assertTrue(command instanceof MarkCommand);

        request = "unmark 1";
        command = parser.parse(request);
        assertTrue(command instanceof UnmarkCommand);

        request = "bye";
        command = parser.parse(request);
        assertTrue(command instanceof ExitCommand);
    }


    @Test
    public void InvalidRequestTest() {

        String request = "Lorem Ipsum";
        Parser parser = new Parser();

        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(request);
        });

    }
}
