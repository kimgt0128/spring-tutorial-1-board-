package first_project.demo.api;

import first_project.demo.dto.CommentDto;
import first_project.demo.entity.Comment;
import first_project.demo.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        //서비스에게 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long articleId, @RequestBody CommentDto commentDto) {
        // 서비스에게 위임
        CommentDto createdDto = commentService.create(articleId, commentDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    //댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        CommentDto updatedDto = commentService.update(id, commentDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
    //댓글 삭제
    @Transactional
    @DeleteMapping("api/comments/{id}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable Long id) {
        CommentDto deleted = commentService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }

}
