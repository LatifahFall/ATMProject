package packagee;
class Deposit extends Transaction{
    public Deposit(int account,double amount){
        super(account,amount);
        //this.withdrawal(); where the account's balance will be increases
    }
    public Deposit(int id,int account,double amount,long ttimestamp){
        super(id,account,amount,ttimestamp);
        //this.withdrawal(); where the account's balance will be increases
    }
    public String getType(){
        return "DEPOSIT";
    }
    public String toString(){
        return super.toString()+" Type: Deposit .";
    }
    public static void main(String args[]){
        Deposit dep=new Deposit(1,2222);
        System.out.println(dep);


    }
}