import java.util.*;

class Main{

  public static void main(String[] args)
  {
    IO io = IO.getInstance();

    int numElectorates = 5;
    Electorate[] electorates = new Electorate[numElectorates];
    for(int i =0; i < numElectorates; i++){
      electorates[i] = new Electorate();
    }

    int numCandidates = 2;
    HashMap<String,Candidate> candidates = new HashMap<String,Candidate>();
    char name = 'A';
    for(int i =0; i< numCandidates; i++)
    {
      candidates.put(Character.toString(name),
          new Candidate(Character.toString(name)));
      name++;
    }


    for(Electorate electorate: electorates){
      String candName;
      while(true){
        candName = io.getCandidate();
        if(candidates.get(candName)!=null)
          break;
        else
          io.printOutput("Please input a valid candidate name!", true);
      }
      electorate.castVote(candidates.get(candName));
    }

    int maxVotes = 0;
    for(Candidate candidate: candidates.values()){
      if(candidate.getVoteCount() > maxVotes)
        maxVotes = candidate.getVoteCount();
    }

    ArrayList<Candidate> winners = new ArrayList<Candidate>();

    for(Candidate candidate: candidates.values()){
      if(candidate.getVoteCount() == maxVotes )
        winners.add(candidate);
    }

    if(winners.size() > 1){
      io.printOutput("The winners are ",true);
      for(Candidate winner: winners){
        io.printOutput(winner.getName(),true);
      }
      io.printOutput("with " + maxVotes + " votes.");

    }
    else{
      io.printOutput("The winner is " + winners.get(0).getName() + " with " + maxVotes + " votes.");
    }

    io.close();

  }

}
