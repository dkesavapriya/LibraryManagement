package com.library.entity;

import javax.persistence.*;  // <-- use javax, not jakarta
import java.util.Date;

@Entity
@Table(name = "issue_records")
public class IssueRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @Column(name="issue_date")
    @Temporal(TemporalType.DATE)
    private Date issueDate;

    @Column(name="return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    public IssueRecord() {}

    public IssueRecord(Book book, Member member, Date issueDate) {
        this.book = book;
        this.member = member;
        this.issueDate = issueDate;
    }

    public int getId() { return id; }
    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public Date getIssueDate() { return issueDate; }
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return "IssueRecord [id=" + id +
               ", book=" + book.getTitle() +
               ", member=" + member.getName() +
               ", issueDate=" + issueDate +
               ", returnDate=" + returnDate + "]";
    }
}
