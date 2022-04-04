import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
/** 
 * @author Francisco Javier Serrano 
 */ 
public class sodokuSolver {
    public static void main(String[] args) { 
        sodokuSolver.startSolver();
    }
    /**
        @param puzzle
        @param col
        @param row
        @param potentialValue
        @return */
    public static boolean isSafe(int[][] puzzle, int col, int row, int potentialValue) {
        for (int i=0; i< puzzle.length;i++) { 
            if (puzzle[i][col]== potentialValue)
                return false; 
        }                                                 // check column
            
        for (int j=0;j<puzzle.length;j++) {
            if (puzzle[row][j]== potentialValue)
            return false;
        }                                                   //check row
         
        int squareRoot=(int)Math.sqrt(puzzle.length);
        int boxRowStart=row-(row%squareRoot);                             
        int boxColStart=col-(col%squareRoot); 
        for (int k=boxRowStart; k<(boxRowStart+squareRoot);k++) {             //check box that the cell belongs to
            for (int l= boxColStart;l<(boxColStart+squareRoot); l++) { 
                if (puzzle[k][l]== potentialValue)
                    return false;
            } 
        } 
        return true;                                                                        // no conflict, operation is safe to do 
    }
    
    /**  */
    public static void startSolver() { 
            int [][]puzzleBoard= sodokuSolver.to2DArr(); 
            if(sodokuSolver.solver(puzzleBoard,puzzleBoard.length)) { // if solution exists, print it
                for (int i = 0; i < puzzleBoard.length; i++) {
                    for (int j = 0; j < puzzleBoard.length; j++) {
                        System.out.print(puzzleBoard[i][j]);
                        System.out.print(" ");
                    }
                    System.out.print("\n");
         
                    if ((i + 1) % (int)Math.sqrt(puzzleBoard.length) == 0)
                    {
                        System.out.print("");
                    }
                }
            }  
    }
    /**
        @param puzzle
        @param potentialValue
        @return */
    public static boolean solver(int[][] puzzle, int size) { 
        boolean done=true;
        int col=-1,row=-1;
        for(int i =0; i<puzzle.length;i++) {                            //search for empty cell
            for (int j=0;j<puzzle.length;j++) { 
                if(puzzle[i][j]==0) {          
                    row=i;
                    col=j;
                    done=false;
                    break;                                              //get out of inner loop
                }
            } // end of inner loop
            if(!done)                                                   //break out of outer loop and inner loop when you scan board and see an empty cell
                break;  
        }//end of outer loop
        if(done)                                                        //check if done after every search you do, return true when the entire board is filled 
            return true;
        for(int potentialValue=1;potentialValue<=size;potentialValue++) { 
            if (isSafe(puzzle, col, row, potentialValue)) { 
                puzzle[row][col]=potentialValue;
                if (solver(puzzle, size))
                    return true; 
                else 
                    puzzle[row][col]=0;
            }
        }
        return false;   
    }
    /** Accepts the Puzzle Text and file and converts the file into a 2-D array to pass onto the solver. 
        Prints out the board before it is solved
        @return */
    public static int[][] to2DArr() { 
        int[][] matrix = null;
        try { 
            BufferedReader buffer = new BufferedReader(new FileReader("Puzzle.txt"));
            String line;
            int row = 0;
            int size = 0;
            while ((line = buffer.readLine()) != null) {
                String[] vals = line.trim().split("\\s+");
                if (matrix == null) {
                    size = vals.length;
                    matrix = new int[size][size];
                }
                for (int col = 0; col < size; col++) {
                    matrix[row][col] = Integer.parseInt(vals[col]);
                }
                row++;
            }
            buffer.close();
        } catch (IOException j) {    }
        return matrix;
    } 
}
