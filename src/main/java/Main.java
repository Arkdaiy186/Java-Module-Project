import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // ваш код начнется здесь
        // вы не должны ограничиваться только классом Main и можете создавать свои классы по необходимости
        int maxCar = 3;
        ArrayList<Automobile> automobileArrayList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < maxCar; i++) {
            System.out.println(String.format("Введите название автомобиля №%d", i + 1));
            String name = " ";
            while (true) {
                if (scanner.hasNextInt()) {
                    System.out.println("Название автомобиля должно быть строкой");
                    scanner.next();
                } else {
                    name = scanner.next();
                    break;
                }
            }
            System.out.println(String.format("Введите скорость автомобиля №%d, в диапазоне от 0 до 250", i + 1));
            int speed = -1;
            while (speed < 0 || speed > 250) {
                try {
                    speed = scanner.nextInt();
                    if (speed < 0 || speed > 250) {
                        System.out.println("Введите корректное число входящее в диапазон");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Введите число");
                    scanner.nextLine();
                }
            }
            automobileArrayList.add(new Automobile(name, speed));
        }
        Race race = new Race(automobileArrayList);
        race.winnerIdentification();
        System.out.println("Победитель гонки " + race.getWinnerName() + " скорость, которого составляла " + race.getWinnerSpeed() + " км/ч");
    }
}

class Race {
    ArrayList<Automobile> automobiles;
    String winnerName = "";
    int winnerSpeed = 0;
    int distance = 0;
    int time = 24;

    public Race(ArrayList<Automobile> automobiles) {
        this.automobiles = automobiles;
    }
    public void winnerIdentification() {
        if (automobiles.isEmpty()) {
            System.out.println("Автомобилей нет в гонке");
        }
        Automobile leader = automobiles.get(0);
        for (Automobile automobile : automobiles) {
            if(automobile.speed > leader.speed) {
                leader = automobile;
            }
        }
        distance = time * leader.speed;
        winnerName = leader.name;
        winnerSpeed = leader.speed;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public int getWinnerSpeed() {
        return winnerSpeed;
    }
}

class Automobile {
    String name;
    int speed;

    public Automobile(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }
}