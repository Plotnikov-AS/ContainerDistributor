public class Container {
    public static int maxBoxes;

    private int containerNumber;
    private Box[] boxesInContainer = new Box[maxBoxes];

    public Container (int containerNumber){
        this.containerNumber = containerNumber;
    }

    public void fillContainer (int index, Box box){
        boxesInContainer[index] = box;
    }

    public Box getBoxInContainer (int boxIndex){
        return boxesInContainer[boxIndex];
    }

    public int getContainerNumber(){ return containerNumber; }

    public Box[] getBoxesInContainer(){ return boxesInContainer; }
}
