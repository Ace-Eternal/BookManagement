package book.management;

import book.management.entity.Book;

import book.management.entity.Student;
import book.management.mapper.Mapper;
import book.management.sql.SqlUtil;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;

@Log
public class Main {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in);) {
            LogManager manager=LogManager.getLogManager();
            manager.readConfiguration(Resources.getResourceAsStream("logging.properties"));
            while (true) {
                System.out.println("********************");
                System.out.println("1.录入学生信息");
                System.out.println("2.录入书籍信息");
                System.out.println("3.添加借阅信息");
                System.out.println("4.查询所有借阅信息");
                System.out.println("5.查询所有学生信息");
                System.out.println("6.查询所有书籍信息");
                System.out.println("7.请输入需要执行的操作（按任意其他键退出）");
                int input;
                try {
                    input = scanner.nextInt();
                } catch (Exception e) {
                    return;
                }
                scanner.nextLine();//清楚换行符
                switch (input) {
                    case 1:
                        addStudent(scanner);
                        break;
                    case 2:
                        addBook(scanner);
                        break;
                    case 3:
                        addBorrow(scanner);
                        break;
                    case 4:
                        getBorrowList();
                        break;
                    case 5:
                        getStudentList();
                        break;
                    case 6:
                        getBookList();
                        break;
                    default:
                        return;

                }


            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void addStudent(Scanner scanner){
        System.out.println("*****************");
        System.out.println("请输入学生姓名");
        String name=scanner.nextLine();
        System.out.println("请输入学生性别（男/女）");
        String sex=scanner.nextLine();
        System.out.println("请输入学生年级（1/2/3/4）");
        String grade=scanner.nextLine();
        try{
            Integer.parseInt(grade);
        }catch (Exception e){
            System.out.println("非法输入，请重新输入年级（1/2/3/4）");
            grade=scanner.nextLine();
        }
        Student student=new Student(name,sex,Integer.parseInt(grade));
        SqlUtil.doSqlWork(mapper -> {
            int i=mapper.addStudent(student);
            if(i>=0){
                System.out.println("学生数据录入成功");
                log.info("新增一条学生信息"+student);
            }else{
                System.out.println("学生数据录入失败");
            }
        });

    }

    private static void addBook(Scanner scanner){
        System.out.println("*****************");
        System.out.println("请输入书籍名称");
        String title=scanner.nextLine();
        System.out.println("请输入书籍简介");
        String desc=scanner.nextLine();
        System.out.println("请输入书籍价格");
        String price=scanner.nextLine();
        try{
            Double.parseDouble(price);
        }catch (Exception e){
            System.out.println("非法输入，请重新输入书籍价格");
            price=scanner.nextLine();
        }
        Book book=new Book(title,desc,Double.parseDouble(price));
        SqlUtil.doSqlWork(mapper -> {
            int i=mapper.addBook(book);
            if(i>=0){
                System.out.println("书籍数据录入成功");
                log.info("新增一条书籍信息"+book);
            }else{
                System.out.println("书籍数据录入失败");
            }
        });

    }
    private static void addBorrow(Scanner scanner){
        System.out.println("*****************");
        System.out.println("请输入学生sid");
        int sid=Integer.parseInt(scanner.nextLine());
        System.out.println("请输入书籍bid");
        int bid=Integer.parseInt(scanner.nextLine());
        SqlUtil.doSqlWork(mapper -> {
            int i=mapper.addBorrow(sid,bid);
            if(i>0){
                System.out.println("添加借阅信息成功");
            }else{
                System.out.println("添加借阅信息失败");
            }
        });

    }
    private static void getBorrowList(){
        System.out.println("*****************");
        SqlUtil.doSqlWork(mapper -> {
            mapper.getBorrowList().forEach(borrow -> {
                System.out.println(borrow.getStudent().getName()+"——>"+borrow.getBook().getTitle());
            });
        });
    }
    private static void getStudentList(){
        System.out.println("*****************");
        SqlUtil.doSqlWork(mapper -> {
            mapper.getStudentList().forEach(student -> {
                System.out.println("学号："+student.getSid()+" 姓名："+student.getName()+" 性别："+student.getSex()+" 年级："+student.getGrade());
            });
        });
    }
    private static void getBookList(){
        System.out.println("*****************");
        SqlUtil.doSqlWork(mapper -> {
            mapper.getBookList().forEach(book -> {
                System.out.println("书籍编号："+book.getBid()+" 书名："+book.getTitle()+" 简介："+book.getDesc()+" 价格："+book.getPrice());
            });
        });

    }
}