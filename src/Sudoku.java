import java.awt.Point;
import java.util.Arrays;

// Για την περιγραφή του πίνακα του Sudoku μπορείτε να δημιουργήσετε μια κλάση με όνομα Sudoku,
// η οποία θα περιέχει ως μεταβλητή κλάσης (μεταξύ άλλων) έναν διδιάστατο πίνακα ακεραίων,
// διαστάσεων 9x9, καθώς επίσης και όποιες μεθόδους κρίνετε εσείς απαραίτητες.
// Στον πίνακα θα τοποθετείτε τους αριθμούς από τα αντίστοιχα κελιά του Sudoku,
// με το 0 να υποδηλώνει το κενό κελί.
public class Sudoku {
    private static final int SIZE = 9;

    private int[][] board;
    private Point cell;

    public Sudoku(int[][] initialBoard) {
        this.board = new int[SIZE][];
        for (int i = 0; i < SIZE; i++) {
            // Αντιγραφή του δισδυάστατου πίνακα που δώθηκε ως όρισμα
            // στη μεταβλητή κλάσης board
            this.board[i] = Arrays.copyOf(initialBoard[i], initialBoard[i].length);
        }

        this.findNextEmptyCell();
    }

    public int[][] getBoard() {
        return this.board;
    }

    // Έλεγχος για την μη ύπαρξη ψηφίου 0, που σηματοδοτεί την λύση του Sudoku
    public boolean isSolved() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.board[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    // Εντοπισμός του επόμενου άδειου κελιού (με τιμή 0)
    public void findNextEmptyCell() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.board[i][j] == 0) {
                    this.cell = new Point(i, j);
                }
            }
        }
    }

    // Έλεγχος αν το δωθέν ψηφίο μπορεί να μπει στην πρώτη διαθέσιμη κενή θέση
    // εξετάζοντας τις προϋποθέσεις του Sukoku
    public boolean canFillDigit(int digit) {
        return !this.isInRow(digit) && !this.isInCol(digit) && !this.isInSquare(digit);
    }

    // έλεγχος αν το ψηφίο υπάρχει ήδη στη γραμμή
    private boolean isInRow(int digit) {
        for (int i = 0; i < SIZE; i++) {
            if (this.board[this.cell.x][i] == digit) {
                return true;
            }
        }

        return false;
    }

    // έλεγχος αν το ψηφίο υπάρχει ήδη στη στήλη
    private boolean isInCol(int digit) {
        for (int i = 0; i < SIZE; i++) {
            if (this.board[i][this.cell.y] == digit) {
                return true;
            }
        }

        return false;
    }

    // έλεγχος αν το ψηφίο υπάρχει ήδη στο 3x3 κουτάκι που εξετάζουμε
    private boolean isInSquare(int digit) {
        int r = this.cell.x - this.cell.x % 3;
        int c = this.cell.y - this.cell.y % 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (this.board[i][j] == digit) {
                    return true;
                }
            }
        }

        return false;
    }

    public void fillDigit(int digit) {
        this.board[this.cell.x][this.cell.y] = digit;
    }

    public void printSudoku() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.board[i][j] == 0) {
                    System.out.print("_");
                } else {
                    System.out.print(this.board[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
