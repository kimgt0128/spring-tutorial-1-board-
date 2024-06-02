package first_project.demo.repository;

import first_project.demo.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    //ArrayList<Articles>로 받기 위해 오버라이딩하기
    @Override
    ArrayList<Article> findAll();
}
