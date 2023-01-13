package com.example.demoApi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "comments")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "text", length = 199)
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    @JoinColumn(name = "content_id")
    @ManyToOne
    private Content content;
    @Column(name = "create_date")
    private Date createDate;

    public Comment() {
    }

    public Comment(Long id, String text, User user, Content content, Date createDate) {
        this.id = id;
        this.text = text;
        this.author = user;
        this.content = content;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}


