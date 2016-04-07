class Player{

  private String token;

  public Player(String token){
    this.token = token;
  }

  public boolean playPiece(Board board, int row, int col){
    return board.setPiece(token, row, col);
  }

  public String getToken(){
    return token;
  }

  public String toString(){
    return "Player " + token;
  }

}
