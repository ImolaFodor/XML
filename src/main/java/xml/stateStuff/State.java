package xml.stateStuff;



public class State {
    private String state;

    public State(){

    }

    public State(String state){
        this.state = state.trim();
    }

    public String getState(){
        return this.state;
    }

    public void setState(String state){
        this.state = state;
    }
}