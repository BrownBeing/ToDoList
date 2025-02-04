public  class Task {
 
    private String name;
    private String description;
    private boolean isCompleted;
 
    public Task(String name, String description, Boolean isCompleted){
        this.name = name;
        this.description =description;
        this.isCompleted= isCompleted;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public boolean getIsCompleted(){
        return isCompleted;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setCompleted(boolean isCompleted){
        this.isCompleted = isCompleted;
    }


}
