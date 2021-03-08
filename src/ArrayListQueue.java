import java.util.ArrayList;

/**
 * Κλάση ArrayListQueue για την Δομή δεδομένων 2
 * Κατασκευάζει ουρά υλοποιημένη με την κλάση ArrayList της Java
 * και υλοποιεί τη διεπαφή QueueOrStack, εκθέτοντας τις κοινές μεθόδους:
 *  - pop για εξαγωγή από την αρχή της ουράς (dequeue)
 *  - push για εισαγωγή στην απόληξη της ουράς (enqueue)
 *  - και isEmpty για έλεγχο αν η ουρά είναι άδεια
 */
public class ArrayListQueue implements QueueOrStack {
    private ArrayList<Sudoku> elements = new ArrayList<>();

    public ArrayListQueue() {
        System.out.println("Method 2: Queue implemented with ArrayList");
    }

    // μέθοδος dequeue
    public Sudoku pop() {
        if (elements.isEmpty()) {
            return null;
        }

        return elements.remove(0);
    }

    // μέθοδος enqueue
    public void push(Sudoku element) {
        elements.add(new Sudoku(element.getBoard()));
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}

