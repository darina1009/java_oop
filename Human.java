import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Human {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Human father;
    private Human mother;
    private List<Human> children;

    public Human(String name, String surname, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.children = new ArrayList<>();
    }

    public void addChild(Human child) {
        children.add(child);
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public List<Human> getChildren() {
        return children;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Human findHumanByName(String name) {
        if (this.name.equals(name)) {
            return this;
        }
        for (Human child : children) {
            Human found = child.findHumanByName(name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name + " " + surname + " (" + birthDate + ")";
    }
}