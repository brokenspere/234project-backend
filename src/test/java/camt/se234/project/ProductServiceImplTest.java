package camt.se234.project;
import camt.se234.project.dao.ProductDao;
import camt.se234.project.entity.Product;
import camt.se234.project.service.ProductService;
import camt.se234.project.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    ProductDao productDao;
    ProductServiceImpl productService;


    @Before
    public void setup(){

        productDao = mock(ProductDao.class);
        productService = new ProductServiceImpl() ;
        productService.setProductDao(productDao);
        }

     @Test
    public void testGetAllproductWithMock(){
        List<Product> mockProduct = new ArrayList<>();
        mockProduct.add(new Product("123","Carrot","See som","xxx",50));
        mockProduct.add(new Product("124 ","Orange","See som","xxx",60));
        mockProduct.add(new Product("125","Grape","See muang","xxx",80));
        when(productDao.getProducts()).thenReturn(mockProduct);
        assertThat(productService.getAllProducts(),hasItems(new Product("123","Carrot","See som","xxx",50),
                new Product("124 ","Orange","See som","xxx",60),new Product("125","Grape","See muang","xxx",80)));
     }

    @Test
    public void testGetAvailableWithMock(){
        List<Product> mockProduct = new ArrayList<>();
        mockProduct.add(new Product("123","Carrot","See som","xxx",50));
        mockProduct.add(new Product("124 ","Orange","See som","xxx",0));
        mockProduct.add(new Product("125","Grape","See muang","xxx",80));
        when(productDao.getProducts()).thenReturn(mockProduct);
        assertThat(productService.getAvailableProducts(),hasItems(new Product("123","Carrot","See som","xxx",50),
                new Product("125","Grape","See muang","xxx",80)));
    }
    @Test
    public void testGetUnavailableWithMock(){
        List<Product> mockProduct = new ArrayList<>();
        mockProduct.add(new Product("123","Carrot","See som","xxx",50));
        mockProduct.add(new Product("124 ","Orange","See som","xxx",0));
        mockProduct.add(new Product("125","Grape","See muang","xxx",80));
        when(productDao.getProducts()).thenReturn(mockProduct);

        assertThat(productService.getUnavailableProductSize(), is(1));



    }


    }



