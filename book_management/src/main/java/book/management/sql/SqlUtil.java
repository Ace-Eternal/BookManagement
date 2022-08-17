package book.management.sql;

import book.management.mapper.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.function.Consumer;

public class SqlUtil {
    public SqlUtil() {}
    private static SqlSessionFactory sqlSessionFactory;
    static{
        try {
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void doSqlWork(Consumer<Mapper> consumer){
        try(SqlSession session=sqlSessionFactory.openSession(true);){
            Mapper mapper=session.getMapper(Mapper.class);
            consumer.accept(mapper);
        }


    }
}
