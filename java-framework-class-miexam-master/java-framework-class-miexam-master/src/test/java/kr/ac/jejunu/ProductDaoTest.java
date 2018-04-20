package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.security.AccessControlContext;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest {
    private ProductDao productDao;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        productDao = applicationContext.getBean("getProductDao", ProductDao.class);
    }

    @Test
    public void jejuGet() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = productDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }
    @Test
    public void jejuAdd() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        product.setTitle("가나다라");
        product.setPrice(111111);
        Long id = productDao.insert(product);

        Product insertProduct = productDao.get(id);
        assertEquals(id, insertProduct.getId());
        assertEquals(product.getTitle(), insertProduct.getTitle());
        assertEquals(product.getPrice(), insertProduct.getPrice());
    }

//    @Test
//    public void hallaGet() throws SQLException, ClassNotFoundException {
//        Long id = 1L;
//        String title = "제주감귤";
//        Integer price = 15000;
//
//        Product product = hallaProductDao.get(id);
//        assertEquals(id, product.getId());
//        assertEquals(title, product.getTitle());
//        assertEquals(price, product.getPrice());
//    }
//    @Test
//    public void hallaAdd() throws SQLException, ClassNotFoundException {
//        Product product = new Product();
//        product.setTitle("가나다라");
//        product.setPrice(111111);
//        Long id = hallaProductDao.insert(product);
//
//        Product insertProduct = hallaProductDao.get(id);
//        assertEquals(id, insertProduct.getId());
//        assertEquals(product.getTitle(), insertProduct.getTitle());
//        assertEquals(product.getPrice(), insertProduct.getPrice());
//    }
}
