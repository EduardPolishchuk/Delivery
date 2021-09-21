package ua.training.model.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ReceiptDao;
import ua.training.model.entity.Receipt;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReceiptServiceImplTest {

    private Receipt receipt;
    private User user;
    private List<Receipt> list;

    @Mock
    private DaoFactory daoFactoryMock;
    @Mock
    private ReceiptDao receiptDaoMock;
    @InjectMocks
    private  ReceiptServiceImpl receiptServiceInstance;


    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(daoFactoryMock.createReceiptDao()).thenReturn(receiptDaoMock);
        user = User.builder().id(2).build();
        list = new ArrayList<>();
        receipt = Receipt.builder()
                .paid(true)
                .id(2L)
                .price(new BigDecimal(200))
                .build();
    list.add(receipt);
    }

    @Test
    void shouldReturnReceiptByID(){
        Mockito.when(receiptDaoMock.findById(receipt.getId()))
                .thenReturn(Optional.of(receipt));

        Optional<Receipt> optionalReceipt = receiptServiceInstance.findById(receipt.getId());
        Assertions.assertTrue(optionalReceipt.isPresent());
        Assertions.assertEquals(receipt, optionalReceipt.get());

    }

    @Test
    void shouldReturnReceiptList(){
        Mockito.when(receiptDaoMock.findAll()).thenReturn(list);
        Assertions.assertTrue(receiptServiceInstance.findAll().contains(receipt));
    }

    @Test
    void shouldCreateReceiptAndReturnTrue(){
        Mockito.when(receiptDaoMock.create(receipt)).thenReturn(true);
        Assertions.assertTrue(receiptServiceInstance.create(receipt));
    }

    @Test
    void shouldUpdateReceiptAndReturnTrue(){
        Mockito.when(receiptDaoMock.update(receipt)).thenReturn(true);
        Assertions.assertTrue(receiptServiceInstance.update(receipt));
    }

    @Test
    void shouldReturnUserReceiptList(){
        Mockito.when(receiptDaoMock.findUserReceipts(user,false)).thenReturn(list);
        Mockito.when(receiptDaoMock.findUserReceipts(user,true)).thenReturn(list);
        Assertions.assertTrue(receiptServiceInstance.findUserReceipts(user, true).contains(receipt));
        Assertions.assertTrue(receiptServiceInstance.findUserReceipts(user, false).contains(receipt));
    }

    @Test
    void shouldReturnTrue(){
        Mockito.when(receiptDaoMock.userPaysReceipt(user, receipt)).thenReturn(true);
        Assertions.assertTrue(receiptServiceInstance.userPaysReceipt(user,receipt));
    }
}
