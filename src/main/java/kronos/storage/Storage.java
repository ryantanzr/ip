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

                // Extract and dissect the lines according to the CSV format
                String line = scanner.nextLine();
                String[] components = line.split(",");

                // Extract the individdual components
                String taskType = components[0];
                String description = components[1];
                boolean isDone = components[2].equals("1");

                // Initialise the actual tasks
                Task task = null;

                switch (taskType) {
                case "TD":
                    task = new ToDo(description);
                    break;
                case "D":
                    LocalDate byDate = LocalDate.parse(components[3]);
                    task = new Deadline(description, byDate);
                    break;
                case "E":
                    LocalDate startDate = LocalDate.parse(components[3]);
                    LocalDate endDate = LocalDate.parse(components[4]);
                    task = new Event(description, startDate, endDate);
                    break;
                default:
                    System.out.println("Unknown task type: " + taskType);
                    continue;
                }

                // Set the completion status of the task
                if (task != null) {
                    task.setCompletionStatus(isDone);
                    loadedTasks.add(task);
                }
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            loadedTasks = new ArrayList<Task>();
        }

        return loadedTasks;
    }

}
