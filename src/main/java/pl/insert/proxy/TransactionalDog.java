package pl.insert.proxy;

public class TransactionalDog implements Dog {

    private final Dog realDog;

    public TransactionalDog(Dog realDog){
        this.realDog = realDog;
    }


    @Override
    public void execute() {

        System.out.println("trsactuion begin");

            realDog.execute();

        System.out.println("trsactuion end");



    }
}
