package first_project.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.ToString;

@Entity //DB가 해당 객체를 인식가능!
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {
    @Id //대표값을 지정! Like a 주민번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id를 자동 생성
    private Long id;

    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null) {
            this.title = article.title;
        }
        if (article.content != null) {
            this.content = article.content;
        }
    }
}
