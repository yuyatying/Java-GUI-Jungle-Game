package model;

public enum Pieces {
    Trapped(0),Rat(1),Cat(2),Dog(3),Wolf(4),Leopard(5),Tiger(6),Lion(7),
        Elephant(8),RatInRiver(9);
    private final int Rank;
    private Pieces(int Rank){
        this.Rank=Rank;
    }
    public int getRank(){
        return this.Rank;
    }
}