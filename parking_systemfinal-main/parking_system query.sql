create database parking_system
GO
use parking_system
GO
create table operator(
username varchar(20) unique,--add unique
password varchar(8) unique,--add unique
shift_time int	
)
drop table operator
GO
create table customers (
entry_id nvarchar(10) unique,
plate_number varchar(10) unique , --maybe need to use unique constraint
transaction_date datetime,
slot int default 0,
exit_transaction datetime,
customer_payment decimal default 0.0

)
insert into customers(entry_id,plate_number) values('123','abc')
drop table customers
Go
create table spots(
spot int unique, --add unique
spot_free varchar(10)	
)
drop table spots
Go
create table payment(
shift_order int unique,
shifts_payment decimal
)
drop table payment
Go

  update spots 
  set spot_free = 'free' where spot_free ='notFree'

 
UPDATE s
SET s.spot_free = 'free'  -- Set the new value for the 'spot_free' column
FROM spots s
INNER JOIN customers c ON s.spot = c.slot
