import java.util.ArrayList;
import java.util.List;

public class Tree {
    private Human root;

    public Tree(Human root) {
        this.root = root;
    }

    public void addHuman(Human human, Human father, Human mother) {
        if (father != null) {
            father.addChild(human);
        }
        if (mother != null) {
            mother.addChild(human);
        }
        if (father == null && mother == null) {
            root = human;
        }
    }

    public Human findHumanByName(String name) {
        if (root == null) {
            return null;
        }
        return root.findHumanByName(name);
    }

    public List<Human> getChildren(Human human) {
        if (human == null) {
            return new ArrayList<>();
        }
        return human.getChildren();
    }

    public Human getRoot() { // добавил метод getRoot()
        return root;
    }
}