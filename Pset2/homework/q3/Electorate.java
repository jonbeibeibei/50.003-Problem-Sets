class Electorate{
  private boolean voted;

  public Electorate(){
    voted = false;
  }

  public void castVote(Candidate candidate){
    if(!voted)
      candidate.incVoteCount();
    voted = true;
  }
}

