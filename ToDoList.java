import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ToDoList {

    Scanner scanner = new Scanner(System.in);
    private List<Task> toDoLists = new ArrayList<>();
    private String savedTasksFileName="";
    private String savedTasksFileLocation="/Users/victorbrown/Desktop/algorithmsco/ToDoListApp/savedTasks";

    public List<Task> getToDoLists(){
        return toDoLists;
    }

    public void viewTasks(List<Task> todoLists){
        System.out.println("You have "+ todoLists.size()+ " tasks");
        System.out.printf("%-5s %-20s %-20s %-15s%n"," ","Name", "Description", "Completed");
        for(int i=0; i< todoLists.size(); i++){
            Task task = todoLists.get(i);
            System.out.printf("%-5d %-20s %-20s %-15s%n",i+1, task.getName(), task.getDescription(), task.getIsCompleted()? "true":"false");
        }
    }

    public void addTask(){
        System.out.println("Enter task Title");
        String title = scanner.nextLine();
        System.out.println("Enter task Description");
        String description = scanner.nextLine();
        boolean isCompleted = false;
        toDoLists.add(new Task(title, description, isCompleted));
        System.out.println("Task Added Successfully!");
        
    }

    public void loadList(File tasksFile){
        try(BufferedReader loadedFile = new BufferedReader(new FileReader(tasksFile))){
            toDoLists.clear();
            while(loadedFile.readLine() != null){
                String line =loadedFile.readLine();
                String[] parts= line.split(",");
                if(parts.length == 3){
                    String name = parts[0];
                    String description = parts[1];
                    Boolean isCompleted = Boolean.parseBoolean(parts[2]);
                    toDoLists.add(new Task(name, description, isCompleted));
                }
            }
        }catch(Exception e){
            System.out.println("Could not read File:" + e.getMessage());
        }
    }

    public void deleteTask(){
        
        viewTasks(toDoLists);

        System.out.println("Enter task Nnumber to Delete");

        Scanner scanner = new Scanner(System.in);

        int taskNumber = scanner.nextInt();

        toDoLists.remove(taskNumber-1);
        System.out.println("Task" + taskNumber +  " deleted succesfully");

        
    }

    

    public void saveList(String savedTasksFileName){
        File savedTask = new File("savedTasks"+ savedTasksFileName+".txt");
       try(BufferedWriter saveFile = new BufferedWriter(new FileWriter(savedTask))){
        for(Task task: toDoLists){
            saveFile.write(task.getName() + "," + task.getDescription()+","+task.getIsCompleted());
            saveFile.newLine();
        }
       }catch(Exception e) {
            System.out.println("unable to save file");

       } 
       
       File savedTaskListFile = new File("savedTasksList.txt");
       try(BufferedWriter listFileWriter = new BufferedWriter(new FileWriter(savedTaskListFile, true))){
            listFileWriter.write(savedTasksFileName);
            listFileWriter.newLine();
       }catch(Exception e){
            System.out.println("Error updating task list: " + e.getMessage());
       }
    }

    public void sortArrayByCompletion(List<Task> taskArray){
        if(taskArray.isEmpty()){
            System.out.println("No tasks to sort");
        }else{
            Collections.sort(taskArray, Comparator.comparing(Task::getIsCompleted));
            System.out.println("Tasks are sorted by completion status successfully");
            viewTasks(taskArray);
        }
    }

   

    public void sortArrayByName(List<Task> taskArray){
        if(taskArray.isEmpty()){
            System.out.println("No tasks to sort");
        }else{
            Collections.sort(taskArray, Comparator.comparing(Task::getName));
            System.out.println("Tasks are sorted by name successfully");
            viewTasks(taskArray);
        }

    }

    

    
}
