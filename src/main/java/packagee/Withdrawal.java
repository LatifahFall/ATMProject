package packagee;
class Withdrawal extends Transaction{
    public Withdrawal(int account,double amount){
        super(account,amount);
    }
    public Withdrawal(int id,int account,double amount,long ttimestamp){
        super(id,account,amount,ttimestamp);
    }
    public String getType(){
        return "WITHDRAWAL";
    }
    public String toString(){
        return super.toString()+" Type: Withdrawal .";
    }
    public static void main(String args[]){
        Withdrawal wit=new Withdrawal(1,2222);
        System.out.println(wit);
    }
}
