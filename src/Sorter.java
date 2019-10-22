import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Scanner;

public class Sorter {
    public static void main(String[] args) {
        Container.maxBoxes = 27;
        Truck.maxContainers = 12;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество ящиков с гуманитарной помощью: ");
        int boxesCount = scanner.nextInt();
        Box[] boxes = new Box[boxesCount]; // Объявляем массив с ящиками

        int containersCount = (int) Math.ceil((double) boxesCount / Container.maxBoxes);
        Container[] containers = new Container[containersCount]; // Объявляем массив с контейнерами
        System.out.println("Необходимо контейнеров: " + containersCount);

        int trucksCount = (int) Math.ceil((double) containersCount / Truck.maxContainers);
        Truck[] trucks = new Truck[trucksCount]; // Объявляем массив с грузовиками
        System.out.println("Необходимо грузовиков: " + trucksCount);


        //Создаем объекты ящики
        for (int i = 1; i <= boxesCount; i++) {
            boxes[i - 1] = generateNewBox(i);
        }

        //Создадим объекты контейнеры
        for (int i = 1; i <= containersCount; i++) {
            containers[i - 1] = generateNewContainer(i);
        }

        //Наполним контейнеры ящиками
        for (int containerIndex = 0; containerIndex < containersCount; containerIndex++) {
            int numberOfLastBoxInContainer = containers[containerIndex].getContainerNumber() * Container.maxBoxes; // Номер последнего ящика в контейнере
            int packingBoxIndex = numberOfLastBoxInContainer - Container.maxBoxes; // Номер текущего ящика, подлежащего упаковке в контейнер
            for (int i = 0; i < Container.maxBoxes; i++) {
                containers[containerIndex].fillContainer(i, boxes[packingBoxIndex]);
                if (packingBoxIndex < boxesCount - 1) {
                    packingBoxIndex++;
                } else break;
            }
        }

        //Создадим объекты грузовики
        for (int i = 1; i <= trucksCount; i++) {
            trucks[i - 1] = generateNewTruck(i);
        }

        //Наполним грузовики контейнерами
        for (int truckIndex = 0; truckIndex < trucksCount; truckIndex++) {
            int numberOfLastContainerInTruck = trucks[truckIndex].getTruckNumber() * Truck.maxContainers; // Номер последнего контейнера в грузовике
            int packingContainerIndex = numberOfLastContainerInTruck - Truck.maxContainers; // Номер текущего контейнера, подлежащего упаковке в грузовик
            for (int i = 0; i < Truck.maxContainers; i++) {
                trucks[truckIndex].fillTruck(i, containers[packingContainerIndex]);
                if (packingContainerIndex < containersCount - 1) {
                    packingContainerIndex++;
                } else break;
            }
        }

        //ВЫВОД
        for (int truckIndex = 0; truckIndex < trucksCount; truckIndex++){
            System.out.println("Грузовик №" + trucks[truckIndex].getTruckNumber() + ":");
            try {
                for (int containerIndex = 0; containerIndex < Truck.maxContainers; containerIndex++) {
                    System.out.println("\tКонтейнер №" + trucks[truckIndex].getContainersInTruck()[containerIndex].getContainerNumber() + ":");
                    for (int boxIndex = 0; boxIndex < Container.maxBoxes; boxIndex++) {
                        System.out.println("\t\tЯщик №" + trucks[truckIndex].getContainersInTruck()[containerIndex].getBoxInContainer(boxIndex).getBoxNumber() + ";");
                    }
                }
            }catch (NullPointerException e){}
        }
    }

    //Генераторы
    public static Box generateNewBox (int boxNumber){
        Box newBox = new Box(boxNumber);
        return newBox;
    }

    public static Container generateNewContainer (int containerNumber){
        Container newContainer = new Container(containerNumber);
        return newContainer;
    }

    public static Truck generateNewTruck (int truckNumber){
        Truck newTruck = new Truck(truckNumber);
        return newTruck;
    }
}
