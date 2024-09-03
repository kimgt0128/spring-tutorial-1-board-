package first_project.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import first_project.demo.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    @JsonProperty("article_id")
    private Long articleId;
    private String nickname;
    private String body;


    public static CommentDto createCommentDto(Comment c) {
        return new CommentDto(
                c.getArticle().getId(),
                c.getNickname(),
                c.getBody()
        );
    }
}
