package first_project.demo.service;

import first_project.demo.dto.ArticleForm;
import first_project.demo.entity.Article;
import first_project.demo.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service //서비스 객체를 스프링부트에 생성
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        log.info(articleRepository.findAll().toString());
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        log.info("Creating article {}", article);
        return articleRepository.save(article); // Save the article and let DB handle ID generation
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity(); //수정용 엔티티 생성
        Article target = articleRepository.findById(id).orElse(null); //대상 엔티티 찾기
        if (target == null) {
            log.info("잘못된 요청! id: {}, article: {}", id, dto);
            return null;
        }
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;

    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null) {
            log.info("잘못된 삭제 요청! id: {}", id);
            return null;
        }
        articleRepository.delete(target);
        return target;
    }
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //dtos를 entity로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        //entity를 DB에 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        //강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패")
        );

        //결괏값 반환
        log.info(articleList.toString());
        return articleList;
    }
}
