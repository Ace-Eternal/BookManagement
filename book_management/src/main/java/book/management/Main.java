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
                System.out.println("3.请输入需要执行的操作（按任意其他键退出）");
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
}