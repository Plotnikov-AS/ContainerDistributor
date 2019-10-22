public class Truck {
    public static int maxContainers;

    private int truckNumber;
    private Container[] containersInTruck = new Container[maxContainers];

    public Truck(int truckNumber){
        this.truckNumber = truckNumber;
    }

    public void fillTruck (int truckIndex, Container container){
        containersInTruck[truckIndex] = container;
    }

    public int getTruckNumber(){ return truckNumber; }

    public Container[] getContainersInTruck (){
        return containersInTruck;
    }
}
