package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest {
    private JejuProductDao jejuProductDao;
    private HallaProductDao hallaProductDao;
    @Before
    public void setup() {
        jejuProductDao= new JejuProductDao();
        hallaProductDao = new HallaProductDao();
    }

    @Test
    public void jejuGet() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = jejuProductDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }
    @Test
    public void jejuAdd() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        product.setTitle("가나다라");
        product.setPrice(111111);
        Long id = jejuProductDao.insert(product);

        Product insertProduct = jejuProductDao.get(id);
        assertEquals(id, insertProduct.getId());
        assertEquals(product.getTitle(), insertProduct.getTitle());
        assertEquals(product.getPrice(), insertProduct.getPrice());
    }

    @Test
    public void hallaGet() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = hallaProductDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }
    @Test
    public void hallaAdd() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        product.setTitle("가나다라");
        product.setPrice(111111);
        Long id = hallaProductDao.insert(product);

        Product insertProduct = hallaProductDao.get(id);
        assertEquals(id, insertProduct.getId());
        assertEquals(product.getTitle(), insertProduct.getTitle());
        assertEquals(product.getPrice(), insertProduct.getPrice());
    }
}
