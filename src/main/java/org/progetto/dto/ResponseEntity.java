package org.progetto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseEntity <T>{

    @Singular
    List<String> messages;
    T payload;

    public ResponseEntity(String message) {
        this.addMessage(message);
    }

    public ResponseEntity(String message, T payload) {
        this.addMessage(message);
        this.payload = payload;
    }

    public ResponseEntity<T> addMessage(String msg) {
        if (messages == null)
            messages = new ArrayList<>();
        messages.add(msg);
        return this;
    }

    public ResponseEntity<T> withPayload(T payload) {
        this.payload = payload;
        return this;
    }
}

