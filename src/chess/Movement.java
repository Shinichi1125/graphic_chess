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
public class Movement {
    char column;
    int row;
    PieceData pieceData = new PieceData();
    
    public char getColumn(){
        return column;
    }
    
    public int getRow(){
        return row;
    }
    
    public void setColumn(char column){
        this.column = column;
    }
    
    public void setRow(int row){
        this.row = row;
    }
    
    public PieceData getPieceData(){
        return pieceData;
    }
    
    public void setPieceData(PieceData input){
        this.pieceData.setColor(input.getColor());
        this.pieceData.setPiece(input.getPiece());
    }
    
}
