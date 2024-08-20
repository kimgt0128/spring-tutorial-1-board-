package first_project.demo.ioc;

import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

class ChefTest {

    @Test
    void 돈가스_요리하기() {
        //준비
        Chef chef = new Chef();
        String menu = "돈까스";
        //수행
        String food = chef.cook(menu);

        //예상
        String expected = "한돈 등심으로 만든 돈까스";

        //검증
        assertEquals(expected, food);
        System.out.println(food);
    }


}