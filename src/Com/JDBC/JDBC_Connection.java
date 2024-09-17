package Com.JDBC;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class JDBC_Connection {
    public static void main(String[] args) throws Exception {
        System.out.println("Data Base Connection and Some Operation..");
        System.out.println("Have added Jar file of my sql drive which is you have to downLoad form browser..");
        System.out.println("Path For JAR..File-ProjectStructure-Libraries-add Jar");
/*
Some Common JDBC classes and Interface---
DriverManager
Conncetion
Statement
PreparedStatement
ResultSet
 */
 //Date base Connection And Crud Operation....

        //user and Pass of MySql
        String userName = "root";
        String pass = "root";
        //Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Establish a connection--
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDataBase", userName, pass);
        System.out.println("Connection made with Data Base Successfully...");
        //myDataBase is a database name


        //Create a Statement
        try {
            Statement st = conn.createStatement();
            String createTable="Create table if not exists employee(id int primary key,name varchar(50),age int , salary double)";
            st.executeQuery(createTable);
            System.out.println("Table created successfully...");
        }catch (SQLException e){
            System.out.println("Error during Creating table :"+e.getMessage());
        }

        //Insert value operation
        int empId=15;
        String EmpName="Shiva";
        int age=39;
        double Sal=310000;
     // PreparedStatement ps= conn.prepareStatement("Insert into employee values(1,'Ram',20,20000)");
        //Or
       // PreparedStatement ps= conn.prepareStatement("Insert into employee values('"+empId+"','"+EmpName+"','"+age+"','"+Sal+"')");
        //or
         PreparedStatement ps= conn.prepareStatement("Insert into employee values(?,?,?,?)");
         ps.setInt(1,empId);
         ps.setString(2,EmpName);
         ps.setInt(3,age);
         ps.setDouble(4,Sal);
        //This is the best way genaraly  we use for taking user input and set index wise
       int i= ps.executeUpdate();
       if(i>0){
           System.out.println("Value Inserted success");
       }else {
           System.out.println("Failed to Insert...");
       }

       //Update operation
        String name1="Rakesh";
       int age1=30;
       int id1=1;
       PreparedStatement ps1= conn.prepareStatement("Update employee set name=?,age=? where id=?");
       ps1.setString(1,name1);
       ps1.setInt(2,age1);
       ps1.setInt(3,id1);
     int cn=  ps1.executeUpdate();
       if(cn>0){
           System.out.println("Value updated successfully!!");
       }else{
           System.out.println("Failed to updated....");
       }

       //Delete Operation---
        int empId1=5;
       PreparedStatement ps2= conn.prepareStatement("Delete from employee where id=?");
       ps2.setInt(1,empId1);
       int res=ps2.executeUpdate();
       if(res>0){
           System.out.println("Column Deleted...");
       }else{
           System.out.println("Failed to Delete....");
       }

       //Fetch data from table---

        PreparedStatement ps3= conn.prepareStatement("Select * from employee");
         ResultSet res1= ps3.executeQuery();

         //next()-- this is method of resultSet that will checks column availbility---
          while (res1.next()){
              System.out.println("Names are---"+res1.getString("name"));
              System.out.println("Salaries are---"+res1.getDouble("salary"));
          }
      conn.close();
      }
    }
