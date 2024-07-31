import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static Tree tree;
    private static Research research;

    public void run() { // добавил метод run()
        Human root = createHuman("root", "root", LocalDate.of(1900, 1, 1));
        tree = new Tree(root);
        research = new Research();

        boolean exit = false;
        while (!exit) {
            System.out.println("1. Добавить человека");
            System.out.println("2. Найти человека по имени");
            System.out.println("3. Вывести всех детей выбранного человека");
            System.out.println("4. Вывести всех потомков выбранного человека");
            System.out.println("5. Вывести всех предков выбранного человека");
            System.out.println("6. Вывести всех людей, родившихся в выбранном году");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addHuman();
                    break;
                case 2:
                    findHumanByName();
                    break;
                case 3:
                    showChildren();
                    break;
                case 4:
                    showDescendants();
                    break;
                case 5:
                    showAncestors();
                    break;
                case 6:
                    showPeopleBornInYear();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private static void addHuman() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите фамилию: ");
        String surname = scanner.nextLine();
        System.out.print("Введите дату рождения (дд.мм.гггг): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine(), formatter);

        System.out.print("Введите имя отца или 'none': ");
        String fatherName = scanner.nextLine();
        Human father = fatherName.equals("none") ? null : findHumanByName(fatherName);

        System.out.print("Введите имя матери или 'none': ");
        String motherName = scanner.nextLine();
        Human mother = motherName.equals("none") ? null : findHumanByName(motherName);

        Human human = createHuman(name, surname, birthDate);
        tree.addHuman(human, father, mother);
        System.out.println("Человек добавлен");
    }

    private static void findHumanByName() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        Human human = findHumanByName(name);
        if (human == null) {
            System.out.println("Человек не найден");
        } else {
            System.out.println(human);
        }
    }

    private static void showChildren() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        Human human = findHumanByName(name);
        if (human == null) {
            System.out.println("Человек не найден");
        } else {
            List<Human> children = tree.getChildren(human);
            if (children.isEmpty()) {
                System.out.println("Дети отсутствуют");
            } else {
                System.out.println("Дети:");
                for (Human child : children) {
                    System.out.println(child);
                }
            }
        }
    }

    private static void showDescendants() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        Human human = findHumanByName(name);
        if (human == null) {
            System.out.println("Человек не найден");
        } else {
            List<Human> descendants = research.findAllDescendants(human);
            if (descendants.size() == 1) {
                System.out.println("Потомки отсутствуют");
            } else {
                System.out.println("Потомки:");
                for (Human descendant : descendants.subList(1, descendants.size())) {
                    System.out.println(descendant);
                }
            }
        }
    }

    private static void showAncestors() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        Human human = findHumanByName(name);
        if (human == null) {
            System.out.println("Человек не найден");
        } else {
            List<Human> ancestors = research.findAllAncestors(human);
            if (ancestors.isEmpty()) {
                System.out.println("Предки отсутствуют");
            } else {
                System.out.println("Предки:");
                for (Human ancestor : ancestors) {
                    System.out.println(ancestor);
                }
            }
        }
    }

    private static void showPeopleBornInYear() {
        System.out.print("Введите год: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        List<Human> people = research.findAllPeopleBornInYear(tree, year);
        if (people.isEmpty()) {
            System.out.println("Люди не найдены");
        } else {
            System.out.println("Люди, родившиеся в " + year + " году:");
            for (Human person : people) {
                System.out.println(person);
            }
        }
    }

    private static Human createHuman(String name, String surname, LocalDate birthDate) {
        return new Human(name, surname, birthDate);
    }

    private static Human findHumanByName(String name) {
        return tree.findHumanByName(name);
    }
}