class Candidate{

  private String name;
  private int voteCount;

  public Candidate(String name){
    this.name = name;
    this.voteCount = 0;
  }

  //Getters and setters
  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public int getVoteCount(){
    return voteCount;
  }

  public void incVoteCount(){
    voteCount++;
  }

}
