package first_project.demo.repository;

import first_project.demo.entity.Article;
import first_project.demo.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest //JPA와 연동한 테스트
class CommentRepositoryTest {
    @Autowired CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticle() {
        //Case1: 4번 게시글의 모든 댓글 조회
        {
            //입력 데이터 준비
            Long articleId = 4L;
            //실제 수행 과정
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            //수행 결과 예상
            Article article = new Article(4L, "삼성바이오로직스", "400주 매수");
            Comment a = new Comment(4L, article, "Park", "굳");
            Comment b = new Comment(5L,  article, "James", "저도 살게요");
            List<Comment> expected = Arrays.asList(a, b);

            //검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력하는 검증");
        }
        //Case2: 1번 게시글의 모든 댓글 조회
        {
            //입력 데이터 준비
            Long articleId = 1L;


            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            //예상
            Article article = new Article(1L, "HD현대일렉트릭", "10주 매수");
            Comment a = new Comment(1L, article, "James", "저도 샀어요");
            Comment b = new Comment(2L, article, "Kate", "가즈아");
            Comment c = new Comment(3L, article, "Alice", "전 팔게요");
            List<Comment> expected = Arrays.asList(a, b, c);

            //실제
            assertEquals(expected.toString(), comments.toString());
        }
        //Case 3: 9번 게시글의 모든 댓글을 조회
        {

        }
        //Case4: -1번 댓글을 조회했을 때 에러 케이스 발생

    }


    @Test
    @DisplayName("특정 닉네임의 모든 댓글을 조회")
    void findByNickname() {
        /* Case1: "Kim"의 모든 댓글을 조회 */
        {
            //입력 데이터 준비
            String nickname = "James";

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            //예상하기
            Article article1 = new Article(1L, "HD현대일렉트릭", "10주 매수");
            Article article5 = new Article(4L, "삼성바이오로직스", "400주 매수");
            Article article8 = new Article(6L, "구글", "60주 매수");

            Comment a = new Comment(1L, article1, nickname, "저도 샀어요");
            Comment b = new Comment(5L, article5, nickname, "저도 살게요");
            Comment c = new Comment(8L, article8, nickname, "감삼니다!");
            List<Comment> expected = Arrays.asList(a, b, c);

            //검증
            comments.sort(Comparator.comparing(Comment::getId));
            expected.sort(Comparator.comparing(Comment::getId));
            assertEquals(expected.toString(), comments.toString(), "James의 모든 댓글을 클릭");


        }
    }
}