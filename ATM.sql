create database ATM;
use ATM;
create table Accounts(
id INT PRIMARY KEY AUTO_INCREMENT,
fname varchar(50) NOT NULL,
lname varchar(100) NOT NULL,
phone varchar(15) NOT NULL,
email varchar(100) NOT NULL,
CIN varchar(20) UNIQUE NOT NULL,
password varchar(255) NOT NULL,
balance DECIMAL(12,2) DEFAULT 0.00);
create table Transactions(
id INT PRIMARY KEY AUTO_INCREMENT,
ttimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
amount DECIMAL(12,2) NOT NULL,
account INT NOT NULL,
type ENUM('transfer', 'deposit', 'withdrawal', 'topup') NOT NULL,
operator ENUM('INWI','IAM','ORANGE'),
phone varchar(100),
iden varchar(50),
pincode varchar(255),
killtime TIMESTAMP,
FOREIGN KEY (account) REFERENCES Accounts(id));
ALTER TABLE Transactions ADD COLUMN state BOOLEAN DEFAULT TRUE;
insert into accounts values(235779,"Sara","LILIA","0654327007","saralilia@gmail.com","G92241884","saralilia",99.88);
insert into accounts values(235778,"Ali","BEN","0987657777","aliben@gmail.com","R5488884","aliben",400000.88);
insert into Transactions values(null,"2024-12-28 22:00:00",20,235778,'transfer',null,'saralilia@gmail.com','234777190','5822',"2025-01-04 22:00:00",1);
insert into Transactions values(null,CURRENT_TIMESTAMP,20,235778,'topup','orange','0987657777',null,null,null,null);
insert into Transactions values(null,CURRENT_TIMESTAMP,200,235779,'topup','inwi','0654327007',null,null,null,null);
insert into Transactions values(null,CURRENT_TIMESTAMP,2000,235779,'deposit',null,null,null,null,null,null);
insert into Transactions values(null,"2024-12-26 22:00:00",1000,235778,'transfer',null,'aliben@gmail.com','964300421','6333',"2025-01-02 22:00:00",0);