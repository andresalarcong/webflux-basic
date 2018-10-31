package co.alarcon.fenix.service.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UpdateMessageDTO {
 

    @NotBlank
    @Size(max = 256)
    private String text;

    @JsonIgnore
    private final LocalDateTime editedAt = LocalDateTime.now();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getEditedAt() {
        return editedAt;
    }
}
