import java.io.IOException;
import java.util.Scanner;
/** 
 * @author Francisco Javier Serrano 
 */ 
public class sodokuSolver {
    /**
        @param puzzle
        @param col
        @param row
        @param potentialValue
        @return */
    public static boolean isSafe(int[][] puzzle, int col, int row, int potentialValue) {
        for (int i=0; i< puzzle.length;i++)                                                 // check column
            if (puzzle[i][col]== potentialValue)
                return false; 
        for (int j=0;j<puzzle.length;j++)                                                   //check row
            if (puzzle[row][j]== potentialValue)
                return false;
        int boxRowStart=row-(row%Math.sqrt(puzzle.length));                             
        int boxColStart=col-(col%Math.sqrt(puzzle.length)); 
        for (int k=boxRowStart; k<(boxRowStart+Math.sqrt(puzzle.length));k++) {             //check box that the cell belongs to
            for (int l= boxColStart;l<boxColStart+Math.sqrt(puzzle.length); l++) { 
                if (puzzle[k][l]== potentialValue)
                    return false;
            } 
        } 
        return true;                                                                        // no conflict, operation is safe to do 
    }
    public static void main(String[] args) { 
        int[][] puzzleBoard= to2DArr();

    }
    /**
     * 
     * @param puzzle
     * @param col
     * @param row
     * @param potentialValue
     * @return
     */
    public static boolean solver(int[][] puzzle, int size) { 
        boolean done=true;
        int col=-1,row=-1;
        for(int i =0; i<puzzle.length;i++) {                    //search for empty cell
            for (int j=0;j<puzzle.length;j++) { 
                if(puzzleBoard[i][j]==0) {          
                    row=i;
                    col=j;
                    done=false;
                    break;                                      //get out of inner loop
                }
            } // end of inner loop
            if(!done)                                           //break out of outer loop and inner loop when you scan board and see an empty cell
                break;  
        }//end of outer loop
        if(done)                                                //check if done after every search you do, return true when the entire board is filled 
            return done;
        for(int potentialValue=1;potentialValue<=size;potentialValue++) { 
            if (isSafe(puzzle, col, row, potentialValue)) { 
                puzzle[row][col]=potentialValue;
                if (solver(puzzle, size))
                    return true; 
                else 
                    puzzle[row][col]=0;
            }
        } // end of loop
        return false;   
    }
    /** Accepts the Puzzle Text and file and converts the file into a 2-D array to pass onto the solver. 
        Prints out the board before it is solved
        @return */
    public static int[][] to2DArr() throws IOException { 
        //we will use a file named problem 
        // how do i read from a text file? i use a scanner object to go line by line but i might also need to have clear the buffer each time you read a new int 
        File puzzle= new File("..\\Sodoku\\Puzzle.txt");
        int puzzleSize= sodokuSolver.countLines(puzzle);
        Scanner nextCell= new Scanner(puzzle);

        int[][] puzzleBoard=new int[puzzleSize][puzzleSize];
        while (nextCell.hasNext()){ 
            for (int i =0;i<puzzleSize;i++) {
                for (int j=i+1;j<puzzleSize;j++) { 
                    if (nextCell.hasNextInt()) {
                        puzzle[i][j]=nextCell.nextInt();
                        System.out.print(puzzle[i][j]);
                    }
                    else  
                        nextCell.next();     
                }
                System.out.print("\n");
            }
        }  
    }
    public static int countLines(File puzzle) throws IOException {
        try{ 
        int rowAndcol=0;
        Scanner reader= new Scanner (new File("..\\Sodoku\\Puzzle.txt"));
        while(reader.hasNext())
            if (reader.next()=='\n')
                rowAndcol++;
        return rowAndcol;

        } catch (IOException e){ 
            return -1; 
        }finally { 
            reader.close();
        }  
    }
    public static boolean writeToFile() throws IOException{ 
        try { 
            
            return false;
        }catch(IOException e) { 

        }finally { 
            writer.close();
        }
        

    }
}
