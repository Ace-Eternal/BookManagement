package book.management.mapper;

import book.management.entity.Book;
import book.management.entity.Borrow;
import book.management.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface Mapper {
    @Insert("insert into student(name, sex, grade) VALUES (#{name},#{sex},#{grade})")
    int addStudent(Student student);
    @Insert("insert into book(title, `desc`, price) VALUES (#{title},#{desc},#{price})")
    int addBook(Book book);
//    desc是关键字，自动加上了``
    @Insert("insert into borrow(sid, bid) VALUES (#{sid},#{bid})")
    int addBorrow(@Param("sid") int sid, @Param("bid") int bid);
//    因为有2个参数，所以要加注解说明哪个对哪个
    @Results({
            @Result(column = "id" ,property = "id",id=true),
            @Result(column = "sid",property = "student",one=@One(select = "getStudentBySid")),
            @Result(column = "bid",property = "book",one=@One(select = "getBookByBid")),
    })
    @Select("select * from borrow")
    List<Borrow> getBorrowList();
    @Select("select * from student where sid=#{sid}")
    Student getStudentBySid(int sid);
    @Select("select * from book where bid=#{bid}")
    Book getBookByBid(int bid);
    @Select("select * from student")
    List<Student> getStudentList();
    @Select("select * from book")
    List<Book> getBookList();
}
