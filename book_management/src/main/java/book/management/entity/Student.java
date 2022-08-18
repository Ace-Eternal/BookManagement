package book.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor//?
public class Student {
    int sid;
    String name;
    String sex;
    int grade;

    public Student(String name, String sex, int grade) {
        this.name = name;
        this.sex = sex;
        this.grade = grade;
    }
}
//报错原因：用了final String name 和final String sex.这样的话调用构造方法就会调用只有final的，而name，sex都是String，不知道哪个对哪个
//Cause: org.apache.ibatis.executor.result.ResultMapException: Error attempting to get column 'sex' from result set.  Cause: java.sql.SQLDataException: Cannot determine