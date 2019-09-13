/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.Scanner;

/**
 *
 * @author marra
 */
public class Chess {
    
    /**
     * @param args the command line arguments
     */
 /*   public static void main(String[] args) {
        BoardControl board = new BoardControl();
        Pieces pieces = new Pieces(board);
        char ch = ' ';
        char turn = board.getTurn();
        
        board.setAllPieces();
        board.boardPrint();
        
        // TEST FOR movePiece
        Movement oldLocation = new Movement();
        Movement newLocation = new Movement();
        boolean check;
        boolean cancelCheck;
        boolean pawnCheck;
        boolean kingCheck;
        
        do{
            do{
                oldLocation = pieces.choosePiece();
                check = board.isColorValid(turn, oldLocation);
                if(check == true){
                    check = pieces.isChoiceValid(oldLocation);
                }

                if(check == true){
                    newLocation = pieces.chooseDestination(oldLocation.getPieceData());
                    cancelCheck = board.isMoveCanceled(oldLocation, newLocation);
                    if(cancelCheck == true){
                        System.out.println("The move was canceled ");
                        check = false;
                    }
                    else{
                        check = board.isMoveValid(newLocation);
                        check = pieces.isDestinationValid(oldLocation, newLocation);
                        if(check == true){
                            pawnCheck = pieces.pawnPromotionCheck(newLocation);
                            if(pawnCheck == true){
                                pieces.pawnPromote(newLocation);
                            }
                            pieces.movePiece(oldLocation, newLocation);
                        }       
                    }
                }
            }while(check != true);

            board.boardPrint();
            
            turn = board.turnChange(turn);
            board.setTurn(turn);
            
            kingCheck = board.isKingCaptured();
        }while(kingCheck != true);   
        
    }
    */
}

/*      // TEST FOR setPieceData
        Movement move = new Movement();
        move.setColumn('d');
        move.setRow(5);
        move.pieceData.setColor('W');
        move.pieceData.setPiece('N');
        board.setPieceData(move);
        board.boardPrint();
        */

// tested functions 

/*       // TEST FOR chooseDestination
        Movement move = new Movement();
        move.pieceData.setColor('B');
        move.pieceData.setPiece('K');
        move = pieces.chooseDestination(move.getPieceData());
        
        System.out.println("Chosen data: " );
        System.out.println("Column: " + move.getColumn());
        System.out.println("Row: " + move.getRow());
        System.out.println("Color: " + move.getPieceData().getColor());
        System.out.println("Piece: " + move.getPieceData().getPiece());
        */ 

 /*      // TEST FOR choosePiece() method
        Movement chosen = pieces.choosePiece();
        System.out.println("Chosen piece data: " );
        System.out.println("Column: " + chosen.getColumn());
        System.out.println("Row: " + chosen.getRow()+1);
        System.out.println("Color: " + chosen.getPieceData().getColor());
        System.out.println("Piece: " + chosen.getPieceData().getPiece());
        */

// board.printUpsideDown();

/*
        char turn = 'B';
        System.out.println("Before turnChange: " + turn);
        turn = board.turnChange(turn);
        System.out.println("After turnCHange: " + turn);
   */

/*      // Print the read value 
        System.out.println("Give me a char input");
        Scanner sc = new Scanner(System.in); 
        char c = sc.next().charAt(0); 
        System.out.println("given char is "+c); 
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter any number: ");
        int num = scan.nextInt();
        scan.close();
        System.out.println("The number entered by user: "+num);
        */