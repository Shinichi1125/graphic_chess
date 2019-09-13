/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author marra
 */
public class BoardControl {
    char turn = 'W'; 
    int ROW = 8;
    int COLUMN = 8;
    PieceData board[][] = new PieceData[ROW][COLUMN];
    
    public BoardControl(){
	for(int row = 0; row < ROW; row++){
	    for(int column = 0; column < COLUMN; column++){
                this.board[row][column] = new PieceData();
                this.board[row][column].setPiece(' ');
	        this.board[row][column].setColor(' ');
	    }
	}
    }
    
    // TESTED //
    public void setAllPieces(){
        System.out.println("Setting the pieces in places");
        for(int row = 0; row < ROW; row++){
            for(int column = 0; column < COLUMN; column++){
	        if(row < 2){
	            board[row][column].setColor('W');
		    if(row == 0){
	    	        if(column == 0 || column == COLUMN-1){
	    	            board[row][column].setPiece('R');
		        }
                    
        		if(column == 1 || column == COLUMN-2){
		            board[row][column].setPiece('N');
	                }
                    
    	                if(column == 2 || column == COLUMN-3){
		    	    board[row][column].setPiece('B');
	                }
                    
 		        if(column == 3){
		            board[row][column].setPiece('Q');
		        }
                    
      		        if(column == 4){
		    	    board[row][column].setPiece('K');
		        }
	            }
                
	            if(row == 1){
	                board[row][column].setPiece('P');
                    }	
	        }
			
	        if(row >= 2 && row <= 5){
                    board[row][column].setPiece(' ');
                    board[row][column].setColor(' ');
	        }
			
	        if(row > 5){
	    	    board[row][column].setColor('B');
		    if(row == ROW-1){
                        if(column == 0 || column == COLUMN-1){
                            board[row][column].setPiece('R');
		        }
                    
    		        if(column == 1 || column == COLUMN-2){
		            board[row][column].setPiece('N');
	                }
                    
    	                if(column == 2 || column == COLUMN-3){
		            board[row][column].setPiece('B');
		        }
		        if(column == 3){
		            board[row][column].setPiece('Q');
		        }
    		        if(column == 4){
		    	    board[row][column].setPiece('K');
		        }
	            }
                
	            if(row == ROW-2){
	    	        board[row][column].setPiece('P');
                    }	
	        }	
            }	
        }
    }

    // TESTED
    public void boardPrint(){
        System.out.println("Printing out the board\n");

        // Print out the alphabets (a-h) above and below the board 
        System.out.print(" ");
        for(char alphabet = 'a'; alphabet < 'a' + COLUMN; alphabet++){
            System.out.print("   " + alphabet +  " ");
        }

        System.out.print("\n");

        for(int row = ROW - 1; row >= 0; row--){
            System.out.print("  ");
            for(int i = 0; i < COLUMN; i++){
                System.out.print("-----");
            }
            System.out.print("\n");
            System.out.print((row + 1) + " "); // Print the row number as well
            for(int column = 0; column < COLUMN; column++){
                System.out.print("| " + board[row][column].getColor() + board[row][column].getPiece() + " ");
            }
            System.out.print("| ");
            System.out.print((row + 1));  // Print the row number as well
            System.out.print("\n");
        }

        System.out.print("  ");
        for(int i = 0; i < COLUMN; i++){
            System.out.print("-----");
        }
        System.out.print("\n");

        System.out.print("  ");
        for(char alphabet = 'a'; alphabet < 'a' + COLUMN; alphabet++){
            System.out.print("   " + alphabet + " ");
        }

        System.out.print("\n");
    }

    // TESTED //
    public void printUpsideDown()
    {
        System.out.print("\nPrinting out the board (upside down))\n\n");

        // Print out the alphabets (a-h) above and below the board 
        System.out.print("  ");
        for(char alphabet = 'h'; alphabet > 'h' - COLUMN; alphabet--)
        {
            System.out.print("   " + alphabet + " ");
        }

        System.out.print("\n");

        for(int row = 0; row < ROW; row++)
        {
            System.out.print("  ");
            for(int i = 0; i < COLUMN; i++)
            {
                System.out.print("-----");
            }
            System.out.print("\n");
            System.out.print(row + 1); // Print the row number as well

            for(int column = COLUMN - 1; column >= 0; column--)
            {
                System.out.print("| " + board[row][column].getColor() + board[row][column].getPiece() + " ");
            }
            System.out.print("| ");
            System.out.print(row + 1);  // Print the row number as well
            System.out.print("\n");
        }

        System.out.print("  ");
        for(int i = 0; i < COLUMN; i++)
        {
            System.out.print("-----");
        }
        System.out.print("\n");

        System.out.print("  ");
        for(char alphabet = 'h'; alphabet > 'h' - COLUMN; alphabet--)
        {
            System.out.print("   " + alphabet + " ");
        }

        System.out.print("\n");
    }
    
    public char getTurn(){
        return turn;
    }
    
    public void setTurn(char turn){
        this.turn = turn;
    }
    
    // TESTED // 
    public PieceData getPieceData(int row, int columnNum){
        boolean check = true; 
        PieceData piece = new PieceData();
        
        // if it's out of boundary, false (column)
        if(columnNum <= 0 || columnNum > 8){
                check = false;
        }
        // if it's out of boundary, false (row)
        if(row <= 0 || row > 8){
                check = false;
        }
        
        // store the piece data only if the choice is within the range of board
        if(check == true){
            piece = board[row-1][columnNum-1];
        }
        
        return piece;
    }
    
    // TESTED  //  
    public void setPieceData(Movement move){
        int row = move.getRow();
        char column = move.getColumn();
        int columnNum = convertToInt(column);
        
        PieceData piece = move.getPieceData();
        
        board[row - 1][columnNum -1].setPiece(piece.getPiece());
	board[row - 1][columnNum - 1].setColor(piece.getColor());
    }
    
    public int convertToInt(char column){
        int converted = column - 96;
        return converted;
    }
    
    public int convertXToColumnNum(int x){
        int columnNum = 0;
        
        if(x >= 100 && x < 150){
            columnNum = 1;
        }
        if(x >= 150 && x < 200){
            columnNum = 2;
        }
        if(x >= 200 && x < 250){
            columnNum = 3;
        }
        if(x >= 250 && x < 300){
            columnNum = 4;
        }
        if(x >= 300 && x < 350){
            columnNum = 5;
        }
        if(x >= 350 && x < 400){
            columnNum = 6;
        }
        if(x >= 400 && x < 450){
            columnNum = 7;
        }
        if(x >= 450 && x < 500){
            columnNum = 8;
        }
        return columnNum;
    }
    
    public int convertYToRow(int y){
        int row = 0;
        
        if(y >= 100 && y < 150){
            row = 8;
        }
        if(y >= 150 && y < 200){
            row = 7;
        }
        if(y >= 200 && y < 250){
            row = 6;
        }
        if(y >= 250 && y < 300){
            row = 5;
        }
        if(y >= 300 && y < 350){
            row = 4;
        }
        if(y >= 350 && y < 400){
            row = 3;
        }
        if(y >= 400 && y < 450){
            row = 2;
        }
        if(y >= 450 && y < 500){
            row = 1;
        }
        return row;
    }
    
    // TESTED // 
    public char turnChange(char turn)
    {
        int W = Character.compare('W', turn); 
        int B = Character.compare('B', turn);
        
        // if the turn equals W
        if(W == 0){
            turn = 'B';
        }
        else if(B == 0){
            turn = 'W';
        }

        return turn;
    }

    // TESTED
    boolean isColorValid(char turn, Movement move)
    {
        boolean result;
        PieceData pieceData = new PieceData();
        char color;

        pieceData = move.getPieceData();
        color = pieceData.getColor();

        if(color == turn){
            result = true;
        }
        else{
            result = false;
        }

        if(result == false){
            System.out.println("You should pick a piece of your own color");
        }

        return result;
    }
    
    // TESTED
    boolean isMoveCanceled(Movement oldLocation, Movement move)
    {
        boolean result;

        int row1 = oldLocation.getRow();
        int column1 = oldLocation.getColumn();

        int row2 = move.getRow();
        char column2 = move.getColumn();

        if(row1 == row2 && column1 == column2){
            result = true;
        }
        else{
            result = false;
        }

        return result;
    }
    
    // TESTED
    boolean isMoveValid(Movement move)
    {
        boolean check = true;
        int row;
        char column;
        int columnNum;
        PieceData pieceData = new PieceData(); 
        char piece;

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

        pieceData = move.getPieceData();
        piece = pieceData.getPiece();
        // if it doesn't match any of the pieces that exist, false
        if(piece != 'P' && piece != 'N' && piece != 'B' &&
           piece != 'R' && piece != 'Q' && piece != 'K')
        {
                check = false;
        }

        if(check == false){
            System.out.println("Invalid movement");
        }

        return check;
    }
    
    // TESTED 
    boolean isKingCaptured()
    {
        boolean result = false;
        int king_count = 0;

        for(int row = 0; row < ROW; row++){
            for(int column = 0; column < COLUMN; column++){
                if(board[row][column].getPiece() == 'K'){
                    king_count++;
                }
            }
        }

        if(king_count != 2){
            result = true;
            System.out.println("The king has been captured");
        }

        return result; 
    }
}


/*

    // experimental function
void add_piece(void)
{
	move_t move;
	bool check;
	char c;
	
	do
	{
		printf("Do you want to add a piece? (White:W, Black:B, Nope: N)  ");
		scanf(" %c",&c);
		c = toupper(c);
	}while(c != 'W' && c != 'B' && c != 'N');
	
	if(c != 'N')
	{
		do    // Ask the user to choose where and which piece to put
    	{
	    	printf("\nMake your move: ");
		    move = ask_movement();
		    check = is_move_valid(move);
    	}
	    while(check != true);	
	    
	    move.piece_data.color = c;
	
    	put_piece(move);   // Put the piece onto the designated location
	
    	board_print();
	}
	
	
}

// for test purpose
void put_kings(void)
{
	board[7][4].piece = 'K';
	board[7][4].color = 'B';
	
	board[0][4].piece = 'K';
	board[0][4].color = 'W';
}
*/
    

/*
move_t ask_movement(void)
{
	move_t move;
	
	scanf(" %c %d %c", &move.column, &move.row, &move.piece_data.piece);
	
	move.column = tolower(move.column);    // in case the user enter an uppercase character
	move.column = move.column - 96;      // Convert the alphabet into number
	
	move.piece_data.piece = toupper(move.piece_data.piece); // in case the user enter a lowercase character
		
	return move;
}
    */