public class World {
    String[][] boardArr;
    public World (String[][] boardArr){
        this.boardArr = boardArr;
    }

    public String[][] getBoardArr() {
        return this.boardArr;
    }

    public void setBoardArr(String[][] boardArr) {
        this.boardArr = boardArr;
    }
}