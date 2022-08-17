package book.management.mapper;

import book.management.entity.Book;
import book.management.entity.Student;
import org.apache.ibatis.annotations.Insert;

public interface Mapper {
    @Insert("insert into student(name, sex, grade) VALUES (#{name},#{sex},#{grade})")
    int addStudent(Student student);
    @Insert("insert into book(title, `desc`, price) VALUES (#{title},#{desc},#{price})")
    int addBook(Book book);
//    desc是关键字，自动加上了``

}
