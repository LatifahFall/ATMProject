package packagee;
public class Controller {
    private static Controller instance = new Controller();
    private DatabaseManager db;
    private Account current_user; // For example, the logged-in user
    private int userId;
    private String operator;
    private double amount;
    private String iden;
    private String pincode;
    // Prevent instantiation elsewhere
    private Controller() {
        db = new DatabaseManager();
    }
    //!!!!!!!
    public Account getcurrent_user(){   //To be removed ( it's just to display it in cmd)
        return current_user;
    }
    public boolean authenticate(int id) throws Exception{
        return db.authenticate(id);
    }
    public boolean authenticate(int id,String password) throws Exception{
        return db.authenticate(id,password);
    }
    public boolean transferExists(String iden,String pincode) throws Exception{
        return db.transferExists(iden,pincode);
    }

    // setters
    public void setUserId(int id){
        this.userId=id;
    }
    public void setCurrentUser() throws Exception{
        current_user=new Account(userId,db.getFname(userId),db.getLname(userId),db.getPhone(userId),db.getEmail(userId),db.getCIN(userId),db.getPassword(userId),db.getBalance(userId));
    }
    public void setOperator(String op){
        operator=op;
    }
    public void setAmount(double amount){
        this.amount=amount;
    }
    public void setIden(String iden){
        this.iden=iden;
    }
    public void setPincode(String pincode){
        this.pincode=pincode;
    }
    //getters
    public int getUserId(){
        return userId;
    }
    public String getIden(){
        return iden;
    }
    public String getPincode(){
        return pincode;
    }
    public String getOperator(){
        return operator;
    }
    public double getAmount(){
        return amount;
    }
    public double getBalance(){
        return current_user.getBalance();
    }
    //Database fcts
    public boolean deposit(double amount) throws Exception{
         return db.deposit(current_user.deposit(amount));
    }
    public boolean withdraw(double amount)throws Exception{
        if(getCurrentUserBalance()>=amount) return db.withdraw(current_user.withdraw(amount));
        else return false;
    }
    public boolean topup(double amount,String operator,String phone)throws Exception{
        if(getCurrentUserBalance()>=amount) return db.topup(current_user.topup(amount,operator,phone));
        else return false;
    }
    public boolean transfer(double amount,String phone,String iden,String pincode)throws Exception{
        if(getCurrentUserBalance()>=amount) return db.transfer(current_user.transfer(amount,phone,iden,pincode));
        else return false;
    }
    public boolean changePassword(String password) throws Exception{
        return db.changePassword(getCurrentUserId(),password);
    }
    public static Controller getInstance() {
        return instance;
    }
    public int getCurrentUserId() {
        return current_user.getId();
    }
    public String getCurrentUserEmail() {
        return current_user.getEmail();
    }
    public String getCurrentUserName() {
        return current_user.getName();
    }
    public double getCurrentUserBalance() {
        return current_user.getBalance();
    }
    public String getCurrentUserPhone() {
        return current_user.getPhone();
    }
    public void loadRecentTransactions() throws Exception{
        Transaction[] trs = db.getRecentTransactions(current_user.getId());
        current_user.loadTransactions(trs);
    }
    public Transaction [] getCurrentUserTransactions(){
        return current_user.getTransactions();
    }

}

