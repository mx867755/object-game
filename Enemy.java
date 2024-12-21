public class Enemy extends Character {
    private int isBoss;

    public Enemy(int row, int col){
        super(row, col, "*", " ", 50);
    }

    public int getIsBoss(){return this.isBoss;}

    public void setIsBoss(int isBoss){this.isBoss = isBoss;}

    public void jabHit(int blockBuff){
        this.health -= 6 + 3 * blockBuff;
    }

    public void heavyHit(int blockBuff){
        this.health -= 16 + 8 * blockBuff;
    }
}
