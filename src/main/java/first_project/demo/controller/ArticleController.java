package first_project.demo.controller;

import first_project.demo.controller.dto.ArticleForm;
import first_project.demo.entity.Article;
import first_project.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    //스프링부트가 자체적으로 객체를 만들어 주기 때문에 new 선언 없이 가능
    @Autowired //스프링부트가 미리 생성해놓은 객체를 가져다가 연결
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());
        //1. Dto를 Entity로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());

        //2. Repository에게 Entity를 DB안에 저장하게 함!!
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }
}
