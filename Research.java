import java.util.ArrayList;
import java.util.List;

public class Research {
    public List<Human> findAllDescendants(Human human) {
        List<Human> descendants = new ArrayList<>();
        descendants.add(human);
        for (Human child : human.getChildren()) {
            descendants.addAll(findAllDescendants(child));
        }
        return descendants;
    }

    public List<Human> findAllAncestors(Human human) {
        List<Human> ancestors = new ArrayList<>();
        Human parent = human.getFather();
        while (parent != null) {
            ancestors.add(parent);
            parent = parent.getFather();
        }
        parent = human.getMother();
        while (parent != null) {
            ancestors.add(parent);
            parent = parent.getMother();
        }
        return ancestors;
    }

    public List<Human> findAllPeopleBornInYear(Tree tree, int year) {
        List<Human> people = new ArrayList<>();
        findAllPeopleBornInYear(tree.getRoot(), year, people); // изменил на tree.getRoot()
        return people;
    }

    private void findAllPeopleBornInYear(Human human, int year, List<Human> people) {
        if (human.getBirthDate().getYear() == year) {
            people.add(human);
        }
        for (Human child : human.getChildren()) {
            findAllPeopleBornInYear(child, year, people);
        }
    }
}