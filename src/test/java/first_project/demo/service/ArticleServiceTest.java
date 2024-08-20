package first_project.demo.service;

import first_project.demo.dto.ArticleForm;
import first_project.demo.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        //예상
        Article article1 = new Article(1L, "HD현대일렉트릭", "10주 매수");
        Article article2 = new Article(2L, "SK하이닉스", "20주 매수");
        Article article3 = new Article(3L, "실리콘투", "30주 매수");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(article1, article2, article3));

        //실제
        List<Article> articles = articleService.index();

        //비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success() {
        //예상
        Long id = 1L;
        Article expected = new Article(id, "HD현대일렉트릭", "10주 매수");
        //실제
        Article article = articleService.show(id);
        //비교
        assertEquals(expected.toString(), article.toString());

    }

    @Test
    void show_fail_존재하지_않는_id_입력() {
        //존재하지 않는 id 입력
        //예상
        Long id = -1L;
        Article expected = null;

        //실제
        Article article = articleService.show(id);

        //비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional //데이터가 수정, 삭제, 추가될 수 있는 경우에는 트렌젝션으로 롤백하기
    void create_success_title과_content만_있는_dto() {
        //예상
        String title = "LG전자";
        String content = "100주 매도";
        ArticleForm dto = new ArticleForm(title, content);
        Article expected = new Article(4L, title, content);

        //실제
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_success() {}
    @Test
    @Transactional
    void update_fial_존재하지_않는_id의_dto_입력() {}


    @Test
    @Transactional
    void delete_success(){}

    @Test
    @Transactional
    void delete_fail_존재하지_않는_id_입력() {}


}