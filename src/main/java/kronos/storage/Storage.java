package kronos.storage;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import kronos.tasks.Deadline;
import kronos.tasks.Event;
import kronos.tasks.Task;
import kronos.tasks.ToDo;

/**
 * Handles the loading and saving of tasks to a file.
 */
public class Storage {

    private java.nio.file.Path dataPath = java.nio.file.Paths.get("data", "tasks.csv");

    /**
     * Saves the current tasks to a csv file.
     * @param taskList The list of tasks to save.
     */
    public void saveTasks(List<Task> taskList) {

        try {
            // Create directories if it doesn't exist
            java.nio.file.Files.createDirectories(dataPath.getParent());

            // Write the tasks into the file
            try (FileWriter writer = new FileWriter(dataPath.toFile())) {

                // Write the tasks into the file
                for (Task task : taskList) {
                    writer.write(task.toDataString() + "\n");
                }

                // Flush the writer to ensure all data is written
                writer.flush();
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Extracts tags from the given components array starting from the specified index.
     * @param components The array of string components.
     * @return A list of extracted tags.
     */
    private List<String> extractTags(String[] components) {
        List<String> tags = new ArrayList<>();
        for (String component : components) {
            if (component.startsWith("#")) {
                tags.add(component);
            }
        }
        return tags;
    }

    /**
     * Extracts task fields from the given components array.
     * @param components The array of string components.
     * @return A list of extracted task fields.
     */
    private List<String> extractTaskComponents(String[] components) {
        List<String> fields = new ArrayList<>();
        for (String component : components) {
            if (!component.startsWith("#")) {
                fields.add(component);
            }
        }
        return fields;
    }

    /**
     * Creates a Task object from a line in the csv file.
     * @param line The line from the csv file.
     * @return The created Task object.
     */
    private Task createTask(String line) {

        // Extract and dissect the lines according to the CSV format
        String[] components = line.split(",");

        // Extract tags and fields from the components
        List<String> tags = extractTags(components);
        List<String> fields = extractTaskComponents(components);

        // Extract the individual fields
        String taskType = fields.get(0);
        String description = fields.get(1);
        boolean isDone = fields.get(2).equals("1");

        // Initialise the actual tasks
        Task task = null;

        switch (taskType) {
        case "TD":
            task = new ToDo(description);
            break;
        case "D":
            LocalDate byDate = LocalDate.parse(fields.get(3));
            task = new Deadline(description, byDate);
            break;
        case "E":
            LocalDate startDate = LocalDate.parse(fields.get(3));
            LocalDate endDate = LocalDate.parse(fields.get(4));
            task = new Event(description, startDate, endDate);
            break;
        default:
            System.out.println("Unknown task type: " + taskType);
        }

        if (task != null) {
            task.setCompletionStatus(isDone);
        } else {
            System.out.println("Error creating task: " + line);
        }

        if (!tags.isEmpty()) {
            task.addTags(tags.toArray(new String[0]));
        }

        return task;
    }

    /**
     * Loads saved tasks into the task list.
     * @return A list of loaded tasks.
     */
    public List<Task> loadTasks() {

        List<Task> loadedTasks = new ArrayList<>();

        try {
            // Create directories and file if they don't exist
            if (!java.nio.file.Files.exists(dataPath)) {
                java.nio.file.Files.createDirectories(dataPath.getParent());
                java.nio.file.Files.createFile(dataPath);
            }

            java.util.Scanner scanner = new java.util.Scanner(dataPath.toFile());

            // Read each line from the csv file
            while (scanner.hasNextLine()) {

                // Create a task from the line
                Task task = createTask(scanner.nextLine());

                // Load the task into the list
                loadedTasks.add(task);
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            loadedTasks = new ArrayList<Task>();
        }

        return loadedTasks;
    }

}
