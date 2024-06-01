package first_project.demo.controller.dto;

import first_project.demo.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

//롬복을 통한 코드 리팩토링
@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title;
    private String content;



    public Article toEntity() {
        return new Article(null, title, content);
    }
}
