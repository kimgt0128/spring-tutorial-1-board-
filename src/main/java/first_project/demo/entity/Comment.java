package first_project.demo.entity;

import first_project.demo.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "comment", uniqueConstraints = {
        @UniqueConstraint(name = "unique_nickname_body", columnNames = {"nickname", "body"})
})


public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id를 자동 생성
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto commentDto, Article article) {
        //예외 발생: 게시글이 없는 경우


        // 예외 발생: articleId가 다른 경우
        if(commentDto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못 되었습니다.");


        // 엔티티 생성, 반환
        return new Comment(
                null,
                article,
                commentDto.getNickname(),
                commentDto.getBody()
        );
}

    public void patch(CommentDto commentDto) {
        //예외 발생: Id가 다른 경우

        //객체를 갱신
        if (commentDto.getNickname() != null)
            this.nickname = commentDto.getNickname();
        if (commentDto.getBody() != null)
            this.body = commentDto.getBody();
    }
}
