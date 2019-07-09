package pl.insert.proxy;

public class RealDog implements Dog {


    private String name;

    public RealDog (String name){
        this.name=name;
    }

    @Override
    public void execute() {
        System.out.println("Dog name is: " + this.name);
    }
}
