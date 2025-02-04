import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ToDoListUI {
    
    
    ToDoList toDoList = new ToDoList();
    List<File> savedTaskArray = new ArrayList<>();
    List<Task> taskArray = toDoList.getToDoLists();
    Scanner scanner = new Scanner(System.in);


    public void showMenu(){
        System.out.println("Welcome to To-Do List App!");
        System.out.println("Enter 'L' to load session or 'N' to start a new one");
        
        String act = scanner.nextLine() ;

        boolean loadedFile = false;

        String userFile=" ";

        while(true){
          
        if(act.equalsIgnoreCase("l")){
            System.out.println("Existing Files..... :");
            for(int i =0; i <savedTaskArray.size(); i++ ){
                System.out.println(i +". "+savedTaskArray.get(i).getName());
            }
            System.out.printf("\n");
            System.out.println("whats the name of your file");
            userFile = scanner.nextLine();

            for( File file: savedTaskArray){
                if(file.getName().equalsIgnoreCase(userFile)){
                    toDoList.loadList(file);
                    loadedFile = true;
                    System.out.println("Your file has beeen loaded successfully");
                }else{
                    System.out.println("The name you have enterd does not match any existing file");
                }
            }
        }

        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Update task");
        System.out.println("4. Delete task");
        System.out.println("5. Sort tasks by name");
        System.out.println("7. Exit");

        
        
        try{
            int action = scanner.nextInt();

            switch(action){
                case 1:
                toDoList.addTask();
                break;

                case 2:
                toDoList.viewTasks(taskArray);
                break;

                case 3:
                updateTask();
                System.out.println("Task updated successfully");
                break;

                case 4:
                toDoList.deleteTask();
                System.out.println("Task deleted successfully");
                break;

                case 5:
                toDoList.sortArrayByName(taskArray);
                break;

                case 6:
                toDoList.sortArrayByCompletion(taskArray);
                break;

                case 7:
                if(!loadedFile){
                    System.out.println("Enter name of your To DO List");
                    String fileName = scanner.next();
                    toDoList.saveList(fileName);
                }else{
                    toDoList.saveList(userFile);
                }
                
                
                System.out.println("Saved and Exiting...");
                scanner.close();
                return;
                default:
                System.out.println("Invalid Otion");
                break;
    
            }

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
        
        
        

    }
    }

    public static void main(String[] args){

        new ToDoListUI().showMenu();    

    }

    public void updateTask(){
        System.out.println("Enter task number to update");
        int taskNumber = scanner.nextInt();

        if (taskNumber < 1 || taskNumber > taskArray.size()) {
            System.out.println("Invalid task number.");
            return;
        }else {
            System.out.println("1. Update title");
        System.out.println("2. Update description");
        System.out.println("3. Mark as Completed/Uncompleted");
        System.out.println("4. Exit Update Mode");
        
        int action = scanner.nextInt();

        try{

            switch(action){
                case 1:
                scanner.nextLine();
                System.out.println("Enter new tile");
                String newTitle = scanner.nextLine();
                taskArray.get(taskNumber-1).setName(newTitle);
                break;
                
                case 2:
                scanner.nextLine();
                System.out.println("Enter new description");
                String newDescription = scanner.nextLine();
                taskArray.get(taskNumber-1).setDescription(newDescription);
                break;
                
                case 3:
                boolean completed = taskArray.get(taskNumber -1).getIsCompleted()? false:true;
                taskArray.get(taskNumber -1).setCompleted(completed);
                boolean complete = taskArray.get(taskNumber -1).getIsCompleted();
                System.out.println("Task " + taskNumber + "is " + (complete ? "completed" : "uncompleted"));
                break;

                case 4:
                System.out.println("Exiting.....");
                return;

                default:
                System.out.println("Invalid selection");
                break;
            }
        }catch(Exception e){
            System.out.println("Invalid task number");
            scanner.nextLine();
        }

        }
        

    }

    

    
}