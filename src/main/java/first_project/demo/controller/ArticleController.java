package first_project.demo.controller;

import first_project.demo.dto.ArticleForm;
import first_project.demo.dto.CommentDto;
import first_project.demo.entity.Article;
import first_project.demo.repository.ArticleRepository;
import first_project.demo.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j //로깅을 위한 어노테이션!
public class ArticleController {
    //스프링부트가 자체적으로 객체를 만들어 주기 때문에 new 선언 없이 가능
    @Autowired //스프링부트가 미리 생성해놓은 객체를 가져다가 연결
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

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

        //redirect추가하기 -> 작성한 게시글 id 페이지로 이동
        return "redirect:/articles/" + saved.getId();
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id: " + id);

        //1 - id로 데이터를 가져옴!
        Article articleEneity = articleRepository.findById(id).orElse(null); //값을 찾지 못하면 null반환
        List<CommentDto> commentDtos = commentService.comments(id);
        //2 - 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEneity);
        model.addAttribute("commentDtos", commentDtos);
        //3 - 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        //1 - 모든 Articles을 가져온다.
        //ArrayList의 상위 타입인 List로 받기(ArrayList로 받아도 상관 없음.) - Java.utill라이브러리 상속 관계 공부하기
        //<interface>Iterable <- <interface>Collection <- <interface>List <- <class>ArrayList
        List<Article> articleEntityList = articleRepository.findAll();

        //2 - 가져온 Article 묶음을 View로 전달한다.
        model.addAttribute("articleList", articleEntityList);

        //3 - 뷰 페이지를 설계한다.  
        return "articles/index"; //articles/index.mustache파일안에 View페이지가 설정되도록 작성
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        //수정할 데이터를 가져오기
        Article articleEntity =  articleRepository.findById(id).orElse(null);

        //모델에 데이터 등록!
        model.addAttribute("article", articleEntity);

        //뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());

        Article articleEntity = form.toEntity();

        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if(target != null) {
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes) {
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        if(target != null) {
            articleRepository.delete(target);
            redirectAttributes.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        }
        return "redirect:/articles";
    }
}

