package first_project.demo.service;

import first_project.demo.dto.CommentDto;
import first_project.demo.entity.Article;
import first_project.demo.entity.Comment;
import first_project.demo.repository.ArticleRepository;
import first_project.demo.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {


        // stream 문법으로 조회, 변환, 반환 모두
        return commentRepository.findByArticleId(articleId).stream()
                .map(CommentDto::createCommentDto)
                .collect(Collectors.toList());
    }
    @Transactional //DB의 변경이 일어날 수 있으므로 트랜잭션처리
    public CommentDto create(Long articleId, CommentDto commentDto) {
        //게시글 조회 및 예외 처리
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("대상 게시글이 없습니다."));
        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(commentDto, article);
        // 댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);
        //DTO로 변경하여 반환
        return commentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto commentDto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("대상 댓글이 없습니다."));

        //댓글 갱신
        target.patch(commentDto);

        //DB로 갱신
        Comment updated = commentRepository.save(target);

        //댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long id) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));
        //DB에서 댓글 삭제
        commentRepository.delete(target);

        //삭제 댓글을 DTO로 반환
        return CommentDto.createCommentDto(target);
    }
}
