import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath = "data/tasks.csv";

    /**
     * Saves the current tasks to a csv file
     * @throws java.io.IOException
     */
    public void saveTasks(List<Task> taskList) {
        
        // Create or open the file in the given file path
        File file = new File(filePath);

        // Write the tasks into the file
        try (FileWriter writer = new FileWriter(file)) {
            
            // Create a new file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            // Write the tasks into the file
            for (Task task : taskList) {
                writer.write(task.toDataString() + "\n");
            }

            // Flush the writer to ensure all data is written
            writer.flush();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads saved tasks 
     * @return A list of loaded tasks
     */
    public List<Task> loadTasks() {
        
        List<Task> loadedTasks = new ArrayList<>();

        // Create or open the file in the given file path
        File file = new File(filePath);

        // Read the tasks from the file
        try (java.util.Scanner scanner = new java.util.Scanner(file)) {

            // If a file doesn't exist, return an empty list
            if (!file.exists()) {
                return new ArrayList<Task>();
            }

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

        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            loadedTasks = new ArrayList<Task>();
        } 

        return loadedTasks;
    }
    
}
