# 1. Create a new database called company_db.

```sql
create database company_db;
```

# 2. Select the company_db database for use.

```sql
use company_db;
```

# 3. Create a table named skills with columns: skill_id (auto-incremented primary key), skill_name (varchar, not null), and category (varchar).

```sql
create table skills(skill_id int identity(1,1) primary key,skill_name varchar(255) not null,category varchar(255));
```

# 4. Show all databases available on the SQL server.

```sql
select name from sys.databases;
```

# 5. Show all tables in the company_db database.

```sql
select * from information_schema.tables;
```

# 6. Rename the employee table to staff.

```sql
exec sp_rename 'employee','staff';
```

# 7. Insert a new record into the employee table for an employee named "Alice Green" with email "alice.green@company.com", hire date "2024-01-10", salary 62000.00, dept_id 4, and gender "Female".

```sql
insert into employee(first_name, email, hire_date, salary, dept_id, gender) values('Alice Green', 'alice.green@company.com', '2024-01-10', 62000.00, 4, 'Female');
```

# 8. Insert multiple records into the project table: "Mobile App" (budget 60000.00, dept_id 2) and "Training Program" (budget 25000.00, dept_id 1).

```sql
insert into project (project_name, budget, dept_id) values ('Mobile App', 60000.00, 4),('Training program', 25000.00, 1);
```

# 9. Add a new department "Sales" located in "Boston" to the department table.

```sql
insert into department_table(dept_name,location) values('sales','boston');
```

# 10. Insert a record into the employee table with only first_name ("Tom") and email ("tom@company.com"), leaving other fields as default or NULL.

```sql
insert into employee(first_name,email) values ('Tom','tom@company.com');
```

# 11. Retrieve all records from the employee table.

```sql
select * from employee;
```

# 12. Select only the emp_id, first_name, and salary columns from the employee table. Display emp_id as "Employee ID" first_name as "Name", and email as "Email Address" from the employee table.

```sql
select emp_id as 'Employee ID',first_name as 'Name' ,salary as 'Salary' , email as 'Email address' from employee;
```

# 13. Retrieve all employees hired after January 1, 2023.

```sql
select * from employee where hire_date > '2023-01-01';
```

# 14. List all projects with a budget greater than 40000.00, ordered by budget in descending order.

```sql
select * from project where budget > 40000 order by budget desc;
```

# 15. Show the distinct locations from the department table.

```sql
select distinct(location) from department;
```

# 16. Add a new column phone_number (varchar, 15 characters) to the employee table after the email column.

```sql
alter table employee add column phone_number varchar(15);
-- Ms sql server ignores column orders
```

# 17. Update the salary of "John Doe" to 65000.00 in the employee table.

```sql
update employee set salary = 65000.00 where first_name = 'John doe';
```

# 18. Set the gender of all employees in the IT department (dept_id 2) to "Other".

```sql
update employee set gender = 'Other' where dept_id = 2;
```

# 19. Drop the phone_number column from the employee table.

```sql
alter table employee drop column phone_number;
```

# 20. Retrieve employees with a salary between 60000 and 80000.

```sql
select * from employee where salary between 6000 and 80000;
```

# 21. List employees whose first_name starts with "J".

```sql
select * from employee where first_name like 'J%';
```

# 22. Find all projects where the dept_id is either 1 or 2.

```sql
select * from department where dept_id = 1 or dept_id = 2;
```

# 23. Show employees whose email is not NULL.

```sql
select * from employee where email is not null;
```

# 24. Retrieve departments that are not located in "New York" or "Chicago".

```sql
select * from department where location != 'New York' and location != 'Chicago' ;
```

# 25. List employees hired in the year 2023 using the hire_date column.

```sql
select * from employee where year(hire_date) = 2023;
```

# 26. Calculate the total salary of all employees.

```sql
select sum(salary) from employee;
```

# 27. Find the average budget of all projects.

```sql
select avg(budget) from project;
```

# 28. Determine the highest salary in the employee table. 

```sql
select max(salary) from employee;
```

# 29. Count the number of employees in the IT department (dept_id 2).

```sql
select count(*) from employee where dept_id = 2;
```

# 30. Find the minimum budget among all projects.

```sql
select min(budget) from project;
```

# 31. Retrieve all employees along with their department names.

```sql
select e.*,d.dept_name from employee as e inner join department as d on e.dept_id = d.dept_id;
```

# 32. List all departments and the number of employees in each, even if a department has no employees.

```sql
select d.dept_id, d.dept_name, d.location, count(e.emp_id) as employee_count from department as d left join employee as e on e.dept_id = d.dept_id group by d.dept_id, d.dept_name, d.location;
```

# 33. Show all projects along with the department names they belong to.

```sql
select p.*,d.dept_name from project as p inner join department as d on p.dept_id = d.dept_id;
```

# 34. Find employees who work in departments located in "San Francisco".

```sql
select e.* from employee as e inner join department as d on e.dept_id = d.dept_id where d.location = 'San Francisco';
```

# 35. List departments that have no projects assigned to them.

```sql
select * from department where dept_id not in (select distinct(dept_id) from project);
```

# 36. Concatenate first_name and last_name of employees with a space between them as "Full Name".

```sql
select first_name+' '+last_name as [Full name] from employee;
```

# 37. Convert the dept_name column in the department table to uppercase.

```sql
update department set dept_name = upper(dept_name);
```

# 38. Extract the first 3 characters of each employee’s email.

```sql
select substring(email,1,3) from employee;
```

# 39. Calculate the absolute value of -50000.

```sql
select abs(-50000);
```

# 40. Round the average salary of employees to 2 decimal places.

```sql
update employee set salary = round(salary,2);
```

# 41. Limit the employee list to the first 3 records, ordered by hire_date descending.

```sql
select top 3 * from employee order by hire_date desc;
```

# 42. Retrieve the second page of employees (records 4-6) when paginating with 3 records per page, ordered by emp_id.

```sql
select * from employee order by emp_id offset 3 rows fetch next 3 rows only;
```

# 43. Use IF to classify employee salaries as "High" if >= 70000, otherwise "Low".

```sql
select case 
    when salary>= 70000 then 'High'
    else 'Low'
end as salary_category from employee;
```

# 44. Use CASE to categorize project budgets: "Large" (>= 60000), "Medium" (>= 40000), "Small" (< 40000).

```sql
select case 
    when budget>= 60000 then 'Large'
    when budget>= 40000 then 'Medium'
    else 'Small'
end as project_category from project;
```

# 45. Find the total budget for projects in each department, grouped by dept_id.

```sql
select d.dept_name,sum(p.budget) as total_budget from project as p inner join department as d on p.dept_id = d.dept_id group by d.dept_name;
```

# 46. Create a query to find the employee with the longest first_name using LENGTH.

```sql
select top 1 first_name from employee order by len(first_name) desc;
```

# 47. Retrieve all employees whose hire date is within the last 90 days from the current date (March 22, 2025).

```sql
select * from employee where hire_date >= dateadd(day,-90,'2025-03-22');
```

# 48. Delete all employees with a salary less than 60000.

```sql
delete from employee where salary<60000;
```

# 49. Drop the project table from the database.

```sql
drop table project;
```

# 50. Backup & Restoration: We have provided a backup file. Please restore the database using this backup and verify the storage details after restoration. Once verified please delete the DB.

* Right click on database
* Select restore database
* Select file
* Restore















