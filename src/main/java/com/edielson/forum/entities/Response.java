package com.edielson.forum.entities;

import java.io.Serializable;
import java.time.Instant;

import com.edielson.forum.entities.enums.StatusTopic;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Response implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String message;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    @ManyToOne
    @JoinColumn(name = "author_id")
	private User author;

    private Boolean solved;

    public void setMessage(String message) {
        this.message = message;
    }

    public void updateSolved() {
        if (topic.getStatus() == StatusTopic.SOLUCIONADO || topic.getStatus() == StatusTopic.FECHADO) {
            this.solved = true;
        } else{
            this.solved = false;
        }
    }
}