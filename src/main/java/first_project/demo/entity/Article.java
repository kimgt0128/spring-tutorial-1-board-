package first_project.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.ToString;

@Entity //DB가 해당 객체를 인식가능!
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {
    @Id //대표값을 지정! Like a 주민번호
    @GeneratedValue // 1 2 3, ..., key를 자동 생성 어노테이션!
    private Long id;

    @Column
    private String title;
    @Column
    private String content;

}
