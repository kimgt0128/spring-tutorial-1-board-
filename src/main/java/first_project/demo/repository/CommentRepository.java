package first_project.demo.repository;

import first_project.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글 조회(네이티브 쿼리로 작성)
    @Query(value = "SELECT * " +
            "FROM board.comment" +
            " WHERE article_id = :articleId",
            nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    // 특정 닉네임의 모든 댓글 조회(XML로 작성)
    @Query(name = "findByNickname", nativeQuery = true)
    List<Comment> findByNickname(String nickname);
}
