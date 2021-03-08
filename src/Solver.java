import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// Μπορείτε επίσης να φτιάξετε μια βοηθητική κλάση, έστω Solver το όνομά της,
// στην οποία θα βρίσκεται η μέθοδος main και άλλες βοηθητικές μέθοδοι
// (π.χ., ανάγνωση παζλ από αρχείο, υλοποίηση του αλγορίθμου επίλυσης, κλπ).
public class Solver {
    public static void main(String[] args) {
        // Το πρόγραμμα θα δέχεται ως είσοδο (από τη γραμμή εντολών, μέσω του ορίσματος της μεθόδου main)
        // το όνομα του αρχείου και τον αύξοντα αριθμό της δομής δεδομένων που θα χρησιμοποιηθεί.
        // Υποθέτοντας ότι η κλάση που περιλαμβάνει τη μέθοδο main ονομάζεται Solver, η παρακάτω κλήση:
        //     >>> java Solver.class p01.txt 2
        // θα εκτελεί το πρόγραμμά σας με σκοπό να λύσει το παζλ που περιγράφεται στο αρχείο p01.txt,
        // χρησιμοποιώντας ως δομή δεδομένων ουρά υλοποιημένη με την ArrayList.
        if (args.length != 2) {
            Solver.callError("Invalid script invocation");
        }

        int method = Integer.parseInt(args[1]);
        if (method < 1 || method > 4) {
            Solver.callError("Invalid option " + method + " provided");
        }

        int[][] inputBoard = new int[9][9];
        String filename = "boards" + File.separator + args[0];

        try {
            // Το πρόγραμμά σας θα διαβάζει από αρχείο την αρχική διάταξη των αριθμών του Sudoku,
            // δηλαδή έναν πίνακα διαστάσεων 9x9
            Scanner scanner = new Scanner(new File(filename));

            for (int i = 0; i < inputBoard.length; i++) {
                String line = scanner.nextLine();
                inputBoard[i] = Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            Sudoku initialBoard = new Sudoku(inputBoard);
            initialBoard.printSudoku();

            solveWithMethod(method, initialBoard);
        } catch (FileNotFoundException e) {
            Solver.callError("File '" + args[0] + "' not found");
        }
    }

    private static void solveWithMethod(int method, Sudoku initialBoard) {
        // Το πρόγραμμά σας θα πρέπει να λύσει παζλ Sudoku με τέσσερις (4) διαφορετικές δομές δεδομένων,
        // ειδικότερα χρησιμοποιώντας:
        //   - Δομή δεδομένων 1: Στοίβα υλοποιημένη με την κλάση ArrayList της Java
        //   - Δομή δεδομένων 2: Ουρά υλοποιημένη με την κλάση ArrayList της Java
        //   - Δομή δεδομένων 3: Στοίβα υλοποιημένη με την κλάση Stack της Java
        //   - Δομή δεδομένων 4: Ουρά υλοποιημένη με την κλάση LinkedList της Java
        QueueOrStack statesList = switch (method) {
            case 1 -> new ArrayListStack();
            case 2 -> new ArrayListQueue();
            case 3 -> new StackStack();
            case 4 -> new LinkedListQueue();
            default -> throw new IllegalStateException("Unexpected value: " + method);
        };

        // Αρχικά εισάγουμε στη στοίβα ή στην ουρά την αρχική κατάσταση του sudoku.
        statesList.push(initialBoard);

        // Στη συνέχεια εκτελούμε επαναληπτικά τα παρακάτω βήματα:
        while (!statesList.isEmpty()) {
            // Αφαιρούμε από τη στοίβα ή από την ουρά το πρώτο αντικείμενο.
            Sudoku popped = statesList.pop();

            // Για το πρώτο ελεύθερο κελί του Sudoku (με κάποια σειρά, π.χ. από πάνω προς τα κάτω
            // και στη συνέχεια από αριστερά προς τα δεξιά), για κάθε αριθμό που επιτρέπεται να μπει
            // στο κελί αυτό σύμφωνα με τους κανόνες του Sudoku:
            for (int digit = 1; digit <= 9; digit++) {
                // Δημιουργούμε ένα νέο αντικείμενο.
                Sudoku current = new Sudoku(popped.getBoard());

                // Έλεγχος αν το τρέχον digit μπορεί να μπει στο πρώτο άδειο κελί
                if (!current.canFillDigit(digit)) {
                    continue;
                }

                current.fillDigit(digit);

                // Εάν το αντικείμενο αυτό είναι ένα συμπληρωμένο Sudoku,
                // το παζλ λύθηκε και εμφανίζουμε τον συμπληρωμένο πίνακα
                // στην οθόνη. Το πρόγραμμα τερματίζει.
                if (current.isSolved()) {
                    System.out.println("\nSudoku is solved!\n");
                    current.printSudoku();
                    System.exit(0);
                } else {
                    // Αλλιώς, προσθέτουμε το αντικείμενο στην ουρά ή στην στοίβα.
                    statesList.push(current);
                }
            }
        }

        // Εάν η ουρά ή η στοίβα αδειάσει χωρίς να βρεθεί λύση, το Sudoku δεν είχε λύση.
        System.out.println("Sudoku cannot be solved!");
    }

    private static void callError(String err) {
        System.out.println("ERROR: " + err);
        System.out.println("Usage: java Solver.class [filename] [method]");
        System.out.println("    filename: (p01.txt - p10.txt) initializes the board");
        System.out.println("    method: (1-4) provides the Solver method");
        System.out.println("        1. ArrayList (Stack)");
        System.out.println("        2. ArrayList (Queue)");
        System.out.println("        3. Stack");
        System.out.println("        4. LinkedList");
        System.out.println("Example: java Solver.class p01.txt 2");

        System.exit(1);
    }
}
