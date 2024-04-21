package org.example.lab3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String commentTitle;
    private String commentBody;
    @ManyToOne()
    private Product whichProductCommented;
    @ManyToOne()
    private User commentOwner;
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Comment> replies;
    @ManyToOne()
    private Comment parentComment;
    private float rating;
    @ManyToOne
    private Cart chat;

    public Comment(String commentTitle, String commentBody, float rating) {
        this.commentTitle = commentTitle;
        this.commentBody = commentBody;
        this.rating = rating;
    }

    public Comment(String commentTitle, String commentBody, Product whichProductCommented, User commentOwner, float rating) {
        this.commentTitle = commentTitle;
        this.commentBody = commentBody;
        this.whichProductCommented = whichProductCommented;
        this.commentOwner = commentOwner;
        this.rating = rating;
    }

    public Comment(String commentTitle, String commentBody, User commentOwner, Comment parentComment, float rating) {
        this.commentTitle = commentTitle;
        this.commentBody = commentBody;
        this.commentOwner = commentOwner;
        this.parentComment = parentComment;
        this.rating = rating;
    }

    public String genText() {
        String result = "Rating: "  + rating + "\nReview: " + commentBody;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
