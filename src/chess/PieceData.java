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
public class PieceData {
    public char piece = ' ';
    public char color = ' ';
    
    public void setPiece(char piece){
        this.piece = piece;
    }
    
    public void setColor(char color){
        this.color = color;
    }
    
    public char getPiece(){
        return piece;
    }
    
    public char getColor(){
        return color;
    }
}
