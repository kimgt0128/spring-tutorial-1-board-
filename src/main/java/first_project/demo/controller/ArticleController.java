package first_project.demo.controller;

import first_project.demo.controller.dto.ArticleForm;
import first_project.demo.entity.Article;
import first_project.demo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j //로깅을 위한 어노테이션!
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
        //System.out.println(form.toString()); - 실제 서버에서는 성능 저하 때문에 print대신 로깅을 사용한다.
        //println대신 로깅으로 대체
        log.info(form.toString());
        //1. Dto를 Entity로 변환
        Article article = form.toEntity();
        //System.out.println(article.toString()); -> 로깅으로 대체
        log.info(article.toString());

        //2. Repository에게 Entity를 DB안에 저장하게 함!!
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString()); -> 로깅으로 대체
        log.info(saved.toString());
        return "";
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id: " + id);

        //1 - id로 데이터를 가져옴!
        Article articleEneity = articleRepository.findById(id).orElse(null); //값을 찾지 못하면 null반환
        //2 - 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEneity);
        //3 - 보여줄 페이지를 설정
        return "articles/show";
    }
}
