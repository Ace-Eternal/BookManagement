package book.management.entity;

import lombok.Data;

@Data
public class Borrow {
    int id;
    Student student;
    Book book;
}
