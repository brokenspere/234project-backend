package camt.se234.project;
import camt.se234.project.dao.OrderDao;
import camt.se234.project.entity.Product;
import camt.se234.project.entity.SaleOrder;
import camt.se234.project.entity.SaleTransaction;
import camt.se234.project.service.SaleOrderServiceImpl;
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

public class SaleOrderServiceImpTest {
    OrderDao orderDao;
   SaleOrderServiceImpl saleOrderService;

    @Before
    public void setup(){
        orderDao = mock(OrderDao.class);
        saleOrderService = new SaleOrderServiceImpl();
        saleOrderService.setOrderDao(orderDao);
    }

    @Test
    public void testGetSaleOrderWithMock(){
        List<SaleTransaction> transactions = new ArrayList<>();
        transactions.add(new SaleTransaction("5555",new SaleOrder("352",transactions),new Product("123","Carrot","look like carrot","xxx",5), 10));
        List<SaleOrder> saleOrders = new ArrayList<>();
        saleOrders.add(new SaleOrder("352",transactions));
        when(orderDao.getOrders()).thenReturn(saleOrders);
        assertThat(saleOrderService.getSaleOrders(),hasItem(new SaleOrder("352",transactions)));

    }

    @Test
    public void testAverageSaleOderWithMock(){
        List<SaleTransaction> transactions = new ArrayList<>();
        List<SaleTransaction> transactions2 = new ArrayList<>();
        transactions.add(new SaleTransaction("5555",new SaleOrder("352",transactions),new Product("123","Carrot","look like carrot","xxx",5), 10));
        transactions2.add(new SaleTransaction("5556", new SaleOrder("353",transactions2),new Product("125","Bee","look like carrot","xxx",5), 10));
        List<SaleOrder> saleOrders = new ArrayList<>();
        saleOrders.add(new SaleOrder("352",transactions));
        saleOrders.add(new SaleOrder("353",transactions2));
        when(orderDao.getOrders()).thenReturn(saleOrders);
        assertThat(saleOrderService.getAverageSaleOrderPrice(),is(50.0));

    }

}
