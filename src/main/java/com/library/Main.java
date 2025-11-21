package com.library;

import com.library.dao.BookDAO;
import com.library.dao.MemberDao;
import com.library.dao.IssueRecordDao;
import com.library.entity.Book;
import com.library.entity.Member;
import com.library.entity.IssueRecord;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookDAO bookDao = new BookDAO();
        MemberDao memberDao = new MemberDao();
        IssueRecordDao issueDao = new IssueRecordDao();
        Scanner sc = new Scanner(System.in);

        int choice = 0;

        while (choice != 8) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Books");
            System.out.println("6. List Members");
            System.out.println("7. List Issue Records");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: {
                    System.out.print("Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    bookDao.saveBook(new Book(title, author));
                    break;
                }
                case 2: {
                    System.out.print("Member Name: ");
                    String name = sc.nextLine();
                    memberDao.saveMember(new Member(name));
                    break;
                }
                case 3: {
                    System.out.print("Book ID: ");
                    int bookId = sc.nextInt();
                    System.out.print("Member ID: ");
                    int memberId = sc.nextInt();
                    Book book = bookDao.getBook(bookId);
                    Member member = memberDao.getMember(memberId);
                    if (book != null && member != null && book.isAvailable()) {
                        book.setAvailable(false);
                        bookDao.updateBook(book);
                        issueDao.saveIssueRecord(new IssueRecord(book, member, new Date()));
                        System.out.println("Book issued successfully.");
                    } else {
                        System.out.println("Invalid book/member or book unavailable.");
                    }
                    break;
                }
                case 4: {
                    System.out.print("Issue Record ID: ");
                    int id = sc.nextInt();
                    IssueRecord record = issueDao.getIssueRecord(id);
                    if (record != null && record.getReturnDate() == null) {
                        record.setReturnDate(new Date());
                        issueDao.updateIssueRecord(record);
                        Book b = record.getBook();
                        b.setAvailable(true);
                        bookDao.updateBook(b);
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Invalid record or book already returned.");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Books:");
                    for (Book b : bookDao.getAllBooks()) {
                        System.out.println(b);
                    }
                    break;
                }
                case 6: {
                    System.out.println("Members:");
                    for (Member m : memberDao.getAllMembers()) {
                        System.out.println(m);
                    }
                    break;
                }
                case 7: {
                    System.out.println("Issue Records:");
                    for (IssueRecord ir : issueDao.getAllIssueRecords()) {
                        System.out.println(ir);
                    }
                    break;
                }
                case 8: {
                    System.out.println("Exiting...");
                    break;
                }
                default: {
                    System.out.println("Invalid choice!");
                    break;
                }
            }
        }

        sc.close();
    }
}
