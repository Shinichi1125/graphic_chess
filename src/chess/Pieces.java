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
public class Pieces {
    BoardControl board;

    public Pieces(BoardControl copyBoard){
        this.board = copyBoard;
    }
    
    public int convertToInt(char column){
        int converted = column - 96;
        return converted;
    }
    
    public char convertToChar(int columnNum){
        char converted = (char)(columnNum + 96);
        return converted;
    }

     // TESTED   //
    public Movement choosePiece(){
        Movement move = new Movement();
        char column;
        int columnNum;
        int row;
        PieceData pieceData = new PieceData(); 

        System.out.println(board.getTurn() + ", it's your turn");
        System.out.println("Choose which piece to move: ");
        
        System.out.print("Column(alphabet): ");
        Scanner sc = new Scanner(System.in);      
        column = sc.next().charAt(0); 
        
        System.out.print("Row(number): ");
        sc = new Scanner(System.in);
        row = sc.nextInt();
        
        column = Character.toLowerCase(column);  // convert to lowercase
        columnNum = convertToInt(column);        // convert to number
        
        move.setColumn(column);
        move.setRow(row);
        
        pieceData = board.getPieceData(row, columnNum);
        move.setPieceData(pieceData);
        
     //   sc.close();   // close scanner after the usage
                        // NOTE: That is the case only if you are dealing with files
                        // when you are reading from keyboard there is no need to close
        
        return move; 
    }
    
    public Movement guiChoosePiece(int row, int columnNum){
        Movement move = new Movement();
        PieceData pieceData = new PieceData();   
        char column = convertToChar(columnNum);
        
        move.setRow(row);
        move.setColumn(column);
        
        pieceData = board.getPieceData(row, columnNum);
        move.setPieceData(pieceData);
        
        return move;
    }
    
    // TESTED 
    public boolean isChoiceValid(Movement move)
    {
        boolean check = true;
        int row;
        char column;
        int columnNum;
        PieceData piece = new PieceData(); 
        
        row = move.getRow();
        column = move.getColumn();
        columnNum = convertToInt(column);
        
        // if it's out of boundary, false (column)
        if(columnNum <= 0 || columnNum > 8){
            check = false;
        }
        // if it's out of boundary, false (row)
        if(row <= 0 || row > 8){
            check = false;
        }
        
        if(check != false){
            // if there is no piece in the chosen square, false
            piece = board.getPieceData(row, columnNum);
            if(piece.getPiece() == ' '){
                check = false;
            }
        }
        
        if(check == false){
            System.out.println("Invalid choice");
        }

        return check;
    }

    // TESTED 
    public Movement chooseDestination(PieceData pieceData)
    {
        Movement move = new Movement();
        char column;
        int row;
        String str;
        
        System.out.println("Which square to move the piece to?  ");
        System.out.print("Column(alphabet): ");
        Scanner sc = new Scanner(System.in);     
        str = sc.next();
    
        column = str.charAt(0); 
        column = Character.toLowerCase(column);  // convert to lowercase

        System.out.print("Row(number): ");
        sc = new Scanner(System.in);
        row = sc.nextInt();  
        
        move.setColumn(column);
        move.setRow(row);
        move.setPieceData(pieceData);

        return move; 
    }
    
    public Movement guiChooseDestination(int row, int columnNum, PieceData pieceData){
        Movement move = new Movement();
     //   PieceData pieceData = new PieceData();   
        char column = convertToChar(columnNum);
        
        move.setRow(row);
        move.setColumn(column);
        
        move.setPieceData(pieceData);
        
        return move; 
    }
    
    // TESTED // 
    public void movePiece(Movement oldLocation, Movement move)
    {
        // updating the oldLocation with whitespace
        PieceData piece = new PieceData();
        piece.setPiece(' ');
        piece.setColor(' ');
        oldLocation.setPieceData(piece);
        board.setPieceData(oldLocation);
        
        // move the piece to the chosen destination
        board.setPieceData(move);
    }

    // to be tested
    public boolean isDestinationValid(Movement oldLocation, Movement move)
    {
        boolean check = true;
   
        int oldRow = oldLocation.getRow();
        char oldColumn = oldLocation.getColumn();
        int oldColumnNum = convertToInt(oldColumn);

        PieceData movePieceData = new PieceData();
        movePieceData = oldLocation.getPieceData();
 
        int moveRow = move.getRow();
        char moveColumn = move.getColumn();
        int moveColumnNum = convertToInt(moveColumn);
        char movePiece = movePieceData.getPiece();
        
        if(oldRow == moveRow && oldColumnNum == moveColumnNum){

        }
        else{
            // if it's out of boundary, false
            if(moveColumnNum <= 0 || moveColumnNum > 8){
                check = false;
            }
            // if it's out of boundary, false
            if(moveRow <= 0 || moveRow > 8){
                check = false;
            }

            // check if Pawn's movement is valid	
            if(movePiece == 'P'){
                boolean result = checkPawnMovement(oldLocation, move);
                if(result == false){
                    check = false;
                }
            }
            
            // check if Rook's movement is valid
            if(movePiece == 'R'){
                boolean result = checkVerticalHorizontal(oldLocation, move);
                if(result == false){
                    check = false;
                }
            }
        
            // check if King's movement is valid
            if(movePiece == 'K'){
                boolean result = checkKingMovement(oldLocation, move);
                if(result == false){
                    check = false;
                }
            }
    
            // check if Knight's movement is valid
            if(movePiece == 'N'){
                boolean result = checkKnightMovement(oldLocation, move);
                if(result == false){
                    check = false;
                }	
            }
        
            // check if Bishop's movement is valid
            if(movePiece == 'B'){	
                boolean result = checkDiagonal(oldLocation, move);
                if(result == false){
                    check = false;
                }	
            }
        
            // check if Queen's movement is valid
            if(movePiece == 'Q'){
                boolean resultVH;
                boolean resultD;
                resultVH = checkVerticalHorizontal(oldLocation, move);
                resultD = checkDiagonal(oldLocation, move);
                // if the movement is none of valid vertical, 
                // horizontal or diagonal movements, return false
                if(resultVH == false && resultD == false){
                    check = false;
                }	
            }
        
            if(check == false){
                System.out.println("Invalid destination");
            }
        }

        return check;
    }
    
    // TESTED
    public boolean checkPawnMovement(Movement oldLocation, Movement move){
        boolean check = true;
        PieceData pieceData = new PieceData();
        pieceData = oldLocation.getPieceData();
        
        char color;
        color = pieceData.getColor();
        
        int row1 = oldLocation.getRow();
        int row2 = move.getRow();
        
        char column = oldLocation.getColumn();
        int columnNum1 = convertToInt(column);
        column = move.getColumn();
        int columnNum2 = convertToInt(column);
        
        pieceData = board.getPieceData(row2, columnNum2); // get the piece info of destination
        char pieceDest = pieceData.getPiece();
        char colorDest = pieceData.getColor();
        
        pieceData = move.getPieceData();
        char colorMove = pieceData.getColor();

        if(color == 'W'){
            if(row1 == 2)  // (opening position) let the pawn move forward by 2 squares as well 
            {
                if((row1 == row2-1 && columnNum1 == columnNum2 && pieceDest == ' ') || 
                 (row1 == row2-2 && columnNum1 == columnNum2 && pieceDest == ' ') || 
                  (colorDest != colorMove && colorDest != ' ' && row1 == row2-1 && 
                     ((columnNum1 == columnNum2-1) || (columnNum1 == columnNum2+1)))){}
                else{
                    check = false;
                }
            }
            else    // let the pawn proceed only by 1 square
            {  // if the movement is just by one square on the same column and the pieceData 
                // of the destination is ' ', valid movement
                if((row1 == row2-1 && columnNum1 == columnNum2 && pieceDest == ' ') || 
                    (colorDest != colorMove && colorDest != ' ' && row1 == row2-1 && 
                     ((columnNum1 == columnNum2-1) || (columnNum1 == columnNum2+1)))){}
                // if there is the opponent's pawn on one of the adjacent columns, ok to move
                // diagonally and take the enemy's pawn
                else{
                    check = false;
                }
            }
        }
        
        if(color == 'B'){
            if(row1 == 7){
                if((row1 == row2+1 && columnNum1 == columnNum2 && pieceDest == ' ') || 
                 (row1 == row2+2 && columnNum1 == columnNum2 && pieceDest == ' ')|| 
                  (colorDest != colorMove && colorDest != ' ' && row1 == row2+1 && 
                     ((columnNum1 == columnNum2-1) || (columnNum1 == columnNum2+1)))){}
                else{
                    check = false;
                }
            }
            else{
                if((row1 == row2+1 && columnNum1 == columnNum2 && pieceDest == ' ') || 
                    (colorDest != colorMove && colorDest != ' ' && row1 == row2+1 && 
                     ((columnNum1 == columnNum2-1) || (columnNum1 == columnNum2+1)))){}
                else{
                    check = false;
                }
            }
        }   
        
        return check;
    }

    public boolean pawnPromotionCheck(Movement move){
        boolean result = false;
        PieceData pieceData = new PieceData();
        pieceData = move.getPieceData();
        char movePiece = pieceData.getPiece();
        char moveColor = pieceData.getColor();
        int moveRow = move.getRow();

        if(movePiece == 'P' && moveColor == 'W' && moveRow == 8){
            result = true;
        }

        if(movePiece == 'P' && moveColor == 'B' && moveRow == 1){
            result = true;
        }

        return result;
    }

    public Movement pawnPromote(Movement move){
        char promotion;
        PieceData pieceData = new PieceData();
        pieceData = move.getPieceData();
        do
        {
            System.out.println("This pawn should be promoted to Q, B, N, or R ");
            Scanner sc = new Scanner(System.in);     
            promotion = sc.next().charAt(0);
    
            promotion = Character.toUpperCase(promotion);  // convert to lowercase
        }while(promotion != 'Q'&& promotion != 'B'&& promotion != 'N'&& promotion != 'R');
        
        pieceData.setPiece(promotion);
        move.setPieceData(pieceData);

        return move;
    }

    public boolean checkKingMovement(Movement oldLocation, Movement move)
    {
        boolean check = true;

        int oldRow = oldLocation.getRow();
        char oldColumn = oldLocation.getColumn();
        int oldColumnNum = convertToInt(oldColumn);

        PieceData movePieceData = new PieceData();
        movePieceData = oldLocation.getPieceData();
        char moveColor;
        moveColor = movePieceData.getColor();
        int moveRow = move.getRow();
        char moveColumn = move.getColumn();
        int moveColumnNum = convertToInt(moveColumn);
        
        PieceData boardPieceData = new PieceData();
        boardPieceData = board.getPieceData(moveRow, moveColumnNum);

        if(oldRow - moveRow < 2 && moveRow - oldRow < 2 && 
                oldColumnNum - moveColumnNum < 2 && moveColumnNum - oldColumnNum < 2
            && boardPieceData.getColor() != moveColor){}
        else{
            check = false;
        }

        return check;
    }

    public boolean checkKnightMovement(Movement oldLocation, Movement move)
    {
        boolean check = true;
        
        int oldRow = oldLocation.getRow();
        char oldColumn = oldLocation.getColumn();
        int oldColumnNum = convertToInt(oldColumn);

        PieceData movePieceData = new PieceData();
        movePieceData = oldLocation.getPieceData();
        char moveColor;
        moveColor = movePieceData.getColor();
        int moveRow = move.getRow();
        char moveColumn = move.getColumn();
        int moveColumnNum = convertToInt(moveColumn);
        
        PieceData boardPieceData = new PieceData();
        boardPieceData = board.getPieceData(moveRow, moveColumnNum);
   
        char boardPieceDataColor = boardPieceData.getColor();
        
        if((moveRow-oldRow == 2 && moveColumnNum-oldColumnNum==1 && boardPieceDataColor != moveColor)
        || (moveRow-oldRow == 2 && oldColumnNum-moveColumnNum == 1 && boardPieceDataColor != moveColor)
        || (oldRow-moveRow == 2 && moveColumnNum-oldColumnNum == 1 && boardPieceDataColor != moveColor)
        || (oldRow-moveRow == 2 && oldColumnNum-moveColumnNum == 1 && boardPieceDataColor != moveColor) 
        || (oldRow-moveRow == 2 && moveColumnNum-oldColumnNum == 2 && boardPieceDataColor != moveColor)
        || (moveRow-oldRow == 1 && oldColumnNum-moveColumnNum == 2 && boardPieceDataColor != moveColor)
        || (oldRow-moveRow == 1 && moveColumnNum-oldColumnNum == 2 && boardPieceDataColor != moveColor)
        || (oldRow-moveRow == 1 && oldColumnNum-moveColumnNum == 2 && boardPieceDataColor != moveColor)){}
        else{
            check = false;
        }
    
        return check;
    }

    public boolean checkVerticalHorizontal(Movement oldLocation, Movement move)
    {
        boolean check = true;
        
        int oldRow = oldLocation.getRow();
        char oldColumn = oldLocation.getColumn();
        int oldColumnNum = convertToInt(oldColumn);

        PieceData movePieceData = new PieceData();
        movePieceData = oldLocation.getPieceData();
        char moveColor;
        moveColor = movePieceData.getColor();
        int moveRow = move.getRow();
        char moveColumn = move.getColumn();
        int moveColumnNum = convertToInt(moveColumn);
        
        PieceData boardPieceData = new PieceData();
        boardPieceData = board.getPieceData(moveRow, moveColumnNum);
        
        char boardPieceDataColor = boardPieceData.getColor();

        // check if the movement is horizontal or vertical
        // and destination is not occupied by another piece of the same color
        if((oldRow == moveRow || oldColumnNum == moveColumnNum) && boardPieceDataColor != moveColor)
        {}
        else{
            check = false;
        }

        int blocked = 0;
        int columnDistance; 

        // save the distance of the horizontal movement (to the right)
        columnDistance = moveColumnNum-oldColumnNum;
     
        PieceData VHPieceData = new PieceData();   
     
        if(columnDistance >= 2){
            for(int i = 1; i <= columnDistance; i++){
                // if there is the opponent's piece that blocks the way, return false 
                VHPieceData = board.getPieceData(moveRow, oldColumnNum+i);
                if(VHPieceData.getColor() != ' '){
                    blocked = i;
                }
                if(blocked > 0 && blocked < columnDistance){
                    check = false; 
                }
            }
        }

        blocked = 0;	
        
        columnDistance = oldColumnNum - moveColumnNum;
  
        if(columnDistance >= 2){
            for(int i = 1; i <= columnDistance; i++){
                VHPieceData = board.getPieceData(moveRow, oldColumnNum-i);
                if(VHPieceData.getColor() != ' '){
                    blocked = i;
                }
                if(blocked > 0 && blocked < columnDistance){
                    check = false;
                }
            }
        }

        blocked = 0;	
        
        int rowDistance;
        rowDistance = moveRow-oldRow;
        
        if(rowDistance >= 2){
            for(int i = 1; i <= rowDistance; i++){
                VHPieceData = board.getPieceData(oldRow+i, moveColumnNum);
                if(VHPieceData.getColor() != ' '){
                    blocked = i;
                }
                if(blocked > 0 && blocked < rowDistance){
                    System.out.println("Obstacle detected ");
                    check = false; 
                }
            }
        }

        blocked = 0;	
        
        rowDistance = oldRow-moveRow;
   
        if(rowDistance >=2){
            for(int i = 1; i <= rowDistance; i++){
                VHPieceData = board.getPieceData(oldRow-i, moveColumnNum);
                if(VHPieceData.getColor() != ' '){
                    blocked = i;
                }
                if(blocked > 0 && blocked < rowDistance){
                    check = false; 
                }
            }
        }
    
        return check;
    }
    
    public boolean checkDiagonal(Movement oldLocation, Movement move){
        boolean check = true;
        
        int oldRow = oldLocation.getRow();
        char oldColumn = oldLocation.getColumn();
        int oldColumnNum = convertToInt(oldColumn);

        PieceData movePieceData = new PieceData();
        movePieceData = oldLocation.getPieceData();
        char moveColor;
        moveColor = movePieceData.getColor();
        int moveRow = move.getRow();
        char moveColumn = move.getColumn();
        int moveColumnNum = convertToInt(moveColumn);
        
        PieceData boardPieceData = new PieceData();
        boardPieceData = board.getPieceData(moveRow, moveColumnNum);
        
        if(((moveColumnNum-oldColumnNum == moveRow-oldRow) || ((moveColumnNum-oldColumnNum)+(moveRow-oldRow) == 0))
            && (boardPieceData.getColor() != moveColor))
        {}
        else{
            check = false;
        }
        
        // check if there is an obstacle on the way (right up)
        int distance;
        int blocked = 0;
        
        PieceData diagonalPieceData = new PieceData();  
        
        if(oldRow < moveRow && oldColumnNum < moveColumnNum){	 
            distance = moveColumnNum - oldColumnNum;
            if(distance >= 2){
                for(int i = 1; i <= distance; i++)
                {
                    diagonalPieceData = board.getPieceData(oldRow+i, oldColumnNum+i);
                    if(diagonalPieceData.getColor() != ' '){
                        blocked = i;
                    }

                    if(blocked > 0 && blocked < distance){
                        check = false;
                    }
                }
            }
        }
        
        // check if there is an obstacle on the way (left down)
        blocked = 0;
        if(moveRow < oldRow && moveColumnNum < oldColumnNum){
            distance = oldColumnNum - moveColumnNum;
            if(distance >= 2){
                for(int i = 1; i <= distance; i++){
                    diagonalPieceData = board.getPieceData(oldRow-i, oldColumnNum-i);
                    if(diagonalPieceData.getColor() != ' '){
                        blocked = i;
                    }

                    if(blocked > 0 && blocked < distance){
                        check = false;
                    }
                }
            }
        }
        
        // check if there is an obstacle on the way (right down)
        blocked = 0;
        if(moveRow < oldRow && oldColumnNum < moveColumnNum){
            distance = moveColumnNum - oldColumnNum;
            if(distance >= 2){
                for(int i = 1; i <= distance; i++){
                    diagonalPieceData = board.getPieceData(oldRow-i, oldColumnNum+i);
                    if(diagonalPieceData.getColor() != ' '){
                        blocked = i;
                    }

                    if(blocked > 0 && blocked < distance){
                        check = false;
                    }
                }
            }
        }
        
        // check if there is an obstacle on the way (left up)
        blocked = 0;
        if(oldRow < moveRow && moveColumnNum < oldColumnNum){
            distance = oldColumnNum - moveColumnNum;
            if(distance >= 2){
                for(int i = 1; i <= distance; i++){
                    diagonalPieceData = board.getPieceData(oldRow+i, oldColumnNum-i);
                    if(diagonalPieceData.getColor() != ' '){
                            blocked = i;
                    }

                    if(blocked > 0 && blocked < distance){
                            check = false;
                    }    
                }
            }
        }

        return check;
    }
}

/*
    void put_piece(move_t put)
    {
            board[put.row-1][put.column-1].piece = put.piece_data.piece;
            board[put.row-1][put.column-1].color = put.piece_data.color;
    }
*/
    