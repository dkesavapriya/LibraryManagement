package com.library.entity;

import javax.persistence.*;  // <-- use javax, not jakarta

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    public Member() {}
    public Member(String name) { this.name = name; }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Member [id=" + id + ", name=" + name + "]";
    }
}
