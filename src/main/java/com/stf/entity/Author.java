package com.stf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Fill in your real first name")
    @Size(min = 2, max = 20)
//    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Fill in your real last name")
    @Size(min = 2, max = 20)
    private String lastName;

    @NotBlank(message = "Fill in your nickname")
    private String nickName;

    @Email
    @NotBlank(message = "Fill in your E-mail")
//    @Pattern(regexp = "^(.+)@(\\\\S+)$",message = "E-mail format is incorrect")
    @Basic(fetch = FetchType.LAZY)
    private String email;

    @NotNull(message = "Fill in the age you have")
    @Min(value = 12, message = "Minimum age is 12")
    private Integer age;

    @NotBlank(message = "Fill in your phone number")
//    @Pattern(regexp = "^\\\\d{9}$",message = "Phone number format is incorrect")
    @Basic(fetch = FetchType.LAZY)
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    @Basic(fetch = FetchType.LAZY)
    private Date signDate;

    /**
     * 使用OneToOne注解
     * 建立Author和Wallet的关系:
     * 1.注解在Author中是因为Author是关系的维护方
     * 2.需要添加cascade来使Wallet持久化:
     * -PERSIST: 级联保存(不添加PERSIST的话，则无法保存Wallet)
     * -MERGE: 级联更新(不添加MERGE的话更新Author的Wallet，他的wallet并不会更新)
     * -REMOVE: 级联删除(添加REMOVE后删除Author的话，他的Wallet也会连带着被删除)
     * 3.optional = false:
     * 添加optional = false的意思是这个对象不能为空(Wallet)
     * 4.这里也可以添加fetch(懒加载)
     * 使用JoinColumn注解:
     * 1.可以在括号中设置外键的名字
     * 2.可以在括号中设置外键为其他的field，可以不是id
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Wallet wallet;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Article> article = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Topic> topics = new ArrayList<>();

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }


    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
