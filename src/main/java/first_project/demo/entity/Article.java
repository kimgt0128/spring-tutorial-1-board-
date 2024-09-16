package first_project.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity //DB가 해당 객체를 인식가능!
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude={"comments"})
@Getter
public class Article {
    @Id //대표값을 지정! Like a 주민번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id를 자동 생성
    private Long id;

    @Column
    private String title;
    @Column
    private String content;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void patch(Article article) {
        if (article.title != null) {
            this.title = article.title;
        }
        if (article.content != null) {
            this.content = article.content;
        }
    }
}
