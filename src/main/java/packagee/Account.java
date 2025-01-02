package packagee;
class Account{
    private int id;
    private String fname,lname,phone,email,cin,password;
    private double balance;
    private Transaction[] transactions; //haven't used them really
    public Account(int id,String fname,String lname,String phone,String email,String cin,String password,double balance){
        this.id=id;
        this.fname=fname;
        this.lname=lname;
        this.phone=phone;
        this.email=email;
        this.cin=cin;
        this.password=password;
        this.balance=balance;
        this.transactions=new Transaction[0];
    }
    public void loadTransactions(Transaction [] trs){
        for(int i=0;i<trs.length;i++){
            addTransaction(trs[i]);
        }
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return lname+" "+fname;
    }
    public String getFname(){
        return fname;
    }
    public String getLname(){
        return lname;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }
    public String getCIN(){
        return cin;
    }
    public String getPassword(){
        return password;
    }
    public double getBalance(){
        return balance;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public String toString(){
        String temp="";
        String str="Account number : "+id+"\n\tClient: "+fname+" "+lname+"\n\tPhone: "+phone+"\n\tEmail: "+email+"\n\tCIN: "+cin+"\n\tBalance: "+balance;
        str+="\n\tTransactions: "+transactions.length;
        for(int i=0;i<transactions.length;i++){
            str+="\n\t\t"+transactions[i].toString();
        }
        return str;
    }
    private void addTransaction(Transaction transaction){
        Transaction[] temp=new Transaction[(transactions.length+1)];
        System.arraycopy(transactions,0,temp,0,transactions.length);
        temp[transactions.length]=transaction;
        transactions=temp;
    }
    public Deposit deposit(double amount){
        Deposit dp=new Deposit(id,amount);
        this.addTransaction(dp);
        this.balance=balance+amount;
        return dp;
    }
    public Withdrawal withdraw(double amount){
        Withdrawal wt=new Withdrawal(id,amount);
        this.addTransaction(wt);
        this.balance=balance-amount;
        return wt;
    }
    public Transfer transfer(double amount,String phone,String iden,String pincode){
        Transfer tf=new Transfer(id,amount,phone,iden,pincode);
        this.addTransaction(tf);
        this.balance=balance-amount;
        return tf;

    }
    public TopUp topup(double amount,String operator,String phone){
        TopUp tp=new TopUp(id,amount,operator,phone);
        this.addTransaction(tp);
        this.balance=balance-amount;
        return tp;
    }

    /*public static void main(String args[]){
        Account tr=new Account(1,"Fatima","ELKSAKASS","06292924134","fatimaelksakass@gamil.com","EE977343","GIBBERISH",43.07);
        System.out.println(tr);
        Transaction trr=new Transaction(1,2200.4);
        tr.addTransaction(trr);
        System.out.println(tr);
    }*/
}


