package kronos.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import kronos.commands.AddCommand;
import kronos.commands.Command;
import kronos.commands.DeleteCommand;
import kronos.commands.ExitCommand;
import kronos.commands.ListCommand;
import kronos.commands.MarkCommand;
import kronos.commands.UnmarkCommand;

public class ParserTest {


    @Test
    public void validRequestTest() {

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
    public void invalidRequestTest() {

        String request = "Lorem Ipsum";
        Parser parser = new Parser();

        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(request);
        });

    }
}
