/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

//import java.awt.Canvas;
//import java.awt.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author marra
 */


public class guiChess extends Application {
        int row = 0;
        int columnNum = 0;
        BoardControl board = new BoardControl();
        Pieces pieces = new Pieces(board);
        PieceData pieceData = new PieceData();
        Movement oldLocation = new Movement();
        Movement newLocation = new Movement();
        int status = 0;
        
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene s = new Scene(root, 600, 600, Color.GREY);
        Canvas canvas = new Canvas(590, 590);
        GraphicsContext gc = canvas.getGraphicsContext2D();
         
        char turn = board.getTurn();
        
        // initialize text based chess's board and pieces
        board.setAllPieces();
        board.boardPrint();

        // print the graphic version of the board and pieces based on the text objects' info
        guiPrintBoard(gc);
        
        // dealing with mouse clicks 
        s.setOnMousePressed((MouseEvent mouseEvent) -> {
            // when choosing which piece to move
            if(status == 0){

                int x = (int)mouseEvent.getX();
                int y = (int)mouseEvent.getY();

                columnNum = board.convertXToColumnNum(x);
                row = board.convertYToRow(y); 

                oldLocation = pieces.guiChoosePiece(row, columnNum);
                pieceData = oldLocation.getPieceData();   

                status = 1;
            }
            // when choosing the destination to move the piece to
            else if(status == 1){

                int x = (int)mouseEvent.getX();
                int y = (int)mouseEvent.getY();

                columnNum = board.convertXToColumnNum(x);
                row = board.convertYToRow(y); 

                newLocation = pieces.guiChooseDestination(row, columnNum, oldLocation.getPieceData());
                pieces.movePiece(oldLocation, newLocation);

                board.boardPrint();
                status = 0;

                guiPrintBoard(gc);
            }
        });
     
        root.getChildren().add(canvas);      
    
        primaryStage.setTitle("GUI Chess");
        primaryStage.setScene(s);
        primaryStage.show(); 
        
    }
    
    public void guiPrintBoard(GraphicsContext gc){
        int width = 50;
        int height = 50;
        // print the board (no piece information)
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i % 2 == 0){
                    if(j % 2 == 0){
                        gc.setFill(Color.LIGHTYELLOW);
                    }
                    else{
                        gc.setFill(Color.CHOCOLATE);
                    }
                }
                else{
                    if(j % 2 == 1){
                        gc.setFill(Color.LIGHTYELLOW);
                    }
                    else{
                        gc.setFill(Color.CHOCOLATE);
                    }
                }
                int x = i + 100 + (i * height); 
                int y = j + 100 + (j * width);
                gc.fillRect(x, y, width, height);
            }   
        }  

        // print the graphic version of the board and pieces based on the text objects' info
        int count = 0;
        for(int i = 7; i >= 0; i--){
            for(int j = 0; j < 8; j++){
                Image img = new Image("graphics/blackPawn.png");
                pieceData = board.getPieceData(i+1, j+1);  // get the piece data 
                char c = pieceData.getPiece();

                // set black pieces
                if(pieceData.getPiece() == 'P' && pieceData.getColor() == 'B'){
                    img = new Image("graphics/blackPawn.png");
                }
                if(pieceData.getPiece() == 'R' && pieceData.getColor() == 'B'){
                    img = new Image("graphics/blackRook.png");
                }
                if(pieceData.getPiece() == 'N' && pieceData.getColor() == 'B'){
                    img = new Image("graphics/blackKnight.png");
                }
                if(pieceData.getPiece() == 'B' && pieceData.getColor() == 'B'){
                    img = new Image("graphics/blackBishop.png");
                }
                if(pieceData.getPiece() == 'Q' && pieceData.getColor() == 'B'){
                    img = new Image("graphics/blackQueen.png");
                }
                if(pieceData.getPiece() == 'K' && pieceData.getColor() == 'B'){
                    img = new Image("graphics/blackKing.png");
                }
                // set white pieces
                if(pieceData.getPiece() == 'P' && pieceData.getColor() == 'W'){
                    img = new Image("graphics/whitePawn.png");
                }
                if(pieceData.getPiece() == 'R' && pieceData.getColor() == 'W'){
                    img = new Image("graphics/whiteRook.png");
                }
                if(pieceData.getPiece() == 'N' && pieceData.getColor() == 'W'){
                    img = new Image("graphics/whiteKnight.png");
                }
                if(pieceData.getPiece() == 'B' && pieceData.getColor() == 'W'){
                    img = new Image("graphics/whiteBishop.png");
                }
                if(pieceData.getPiece() == 'Q' && pieceData.getColor() == 'W'){
                    img = new Image("graphics/whiteQueen.png");
                }
                if(pieceData.getPiece() == 'K' && pieceData.getColor() == 'W'){
                    img = new Image("graphics/whiteKing.png");
                }
                int x = 100 + (j * width) + j;
                int y = 100 + (count * height) + count;

                if(c != ' '){
                    gc.drawImage(img, x, y, width, height);
                }   
            }
            count++;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   
}


 
        
 /*       // print the black pieces (other than pawns)
        for(int column = 0; column < 8; column++){
            Image img = new Image("graphics/blackPawn.png");
            if(column == 0 || column == 7){
                img = new Image("graphics/blackRook.png");
            }
            if(column == 1 || column == 6){
                img = new Image("graphics/blackKnight.png");
            }
            if(column == 2 || column == 5){
                img = new Image("graphics/blackBishop.png");
            }
            if(column == 3){
                img = new Image("graphics/blackQueen.png");
            }
            if(column == 4){
                img = new Image("graphics/blackKing.png");
            }
            int x = column + 100 + (column * height);
            gc.drawImage(img, x, 100, width, height);
        }
        
        // print the black pawns
        for(int column = 0; column < 8; column++){
            Image img = new Image("graphics/blackPawn.png");
            int x = column + 100 + (column * height);  
            gc.drawImage(img, x, 150, width, height);
        }
        
        // print the white pawns
        for(int column = 0; column < 8; column++){
            Image img = new Image("graphics/whitePawn.png");
            int x = column + 100 + (column * height);
            int y = 405;
            gc.drawImage(img, x, y, width, height);
        }
        
        // print the white pieces (other than pawns) 
        for(int column = 0; column < 8; column++){
            Image img = new Image("graphics/whitePawn.png");
            if(column == 0 || column == 7){
                img = new Image("graphics/whiteRook.png");
            }
            if(column == 1 || column == 6){
                img = new Image("graphics/whiteKnight.png");
            }
            if(column == 2 || column == 5){
                img = new Image("graphics/whiteBishop.png");
            }
            if(column == 3){
                img = new Image("graphics/whiteQueen.png");
            }
            if(column == 4){
                img = new Image("graphics/whiteKing.png");
            }
            int x = column + 100 + (column * height);
            int y = 455;
            gc.drawImage(img, x, y, width, height);
        }
        
       
        */

