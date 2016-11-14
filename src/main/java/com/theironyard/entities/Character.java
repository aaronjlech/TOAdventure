package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by michaelplott on 11/11/16.
 */
@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String filename;

    @Column(nullable = false)
    String filename2;

    @Column(nullable = false)
    String filename3;

    @Column(nullable = false)
    int checkpoint;

    @Column(nullable = false)
    int score;

    @Column(nullable = false)
    int money;

    @Column(nullable = false)
    int level;

    @ManyToOne
    User user;

    public Character() {
    }

    public Character(String filename, String filename2, String filename3, int checkpoint, int score, int money, int level, User user) {
        this.filename = filename;
        this.filename2 = filename2;
        this.filename3 = filename3;
        this.checkpoint = checkpoint;
        this.score = score;
        this.money = money;
        this.level = level;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename2() {
        return filename2;
    }

    public void setFilename2(String filename2) {
        this.filename2 = filename2;
    }

    public String getFilename3() {
        return filename3;
    }

    public void setFilename3(String filename3) {
        this.filename3 = filename3;
    }

    public int getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(int checkpoint) {
        this.checkpoint = checkpoint;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
