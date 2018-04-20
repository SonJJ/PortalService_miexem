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
        productDao = applicationContext.getBean("productDao", ProductDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = productDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }
    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Long id = testInsertProduct(product);

        Product insertProduct = productDao.get(id);
        assertEquals(id, insertProduct.getId());
        assertEquals(product.getTitle(), insertProduct.getTitle());
        assertEquals(product.getPrice(), insertProduct.getPrice());
    }
    @Test
    public void update() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Long id = testInsertProduct(product);


        product.setId(id);
        product.setTitle("나나나나");
        product.setPrice(222222);
        product = productDao.update(product);

        Product updatedProduct = productDao.get(id);
        assertEquals(product.getId(), updatedProduct.getId());
        assertEquals(product.getTitle(), updatedProduct.getTitle());
        assertEquals(product.getPrice(), updatedProduct.getPrice());
    }
    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Long id = testInsertProduct(product);

        productDao.delete(id);

        Product deletedProduct = productDao.get(id);
        assertEquals(null, deletedProduct);

    }
    private Long testInsertProduct(Product product) throws ClassNotFoundException, SQLException {
        product.setTitle("가나다라");
        product.setPrice(111111);
        return productDao.insert(product);
    }

}
// 9번 10번 함께해버림.