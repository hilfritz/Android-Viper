package com.hilfritz.android.viper.data.cartRepository;

import com.hilfritz.android.viper.data.sephoraApi.pojo.products.Product;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hilfritz Camallere on 18/8/17.
 */
public class CartManagerTest {
    CartManager cartManager;

    @Before
    public void setUp() throws Exception {
        cartManager = new CartManagerImpl();
    }
    @After
    public void teardown() throws Exception {
        cartManager.clear();
    }

    public ArrayList<Product> getSampleProducts(){
        //ADD 3 ITEMS BY DEFAULT
        Product p1 = new Product(1l,"lipstick", "makeup");
        Product p2 = new Product(2l,"foundation", "makeup");
        Product p3 = new Product(3l,"dont know", "maskara");
        ArrayList<Product> list = new ArrayList<Product>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        return list;
    }

    @Test
    public void getProductsInCartTest() {
        //init
        final ArrayList<Product> sampleProducts = getSampleProducts();
        cartManager.getProductsInCart().addAll(sampleProducts);

        //act

        //assert
        assertTrue(cartManager.getProductsInCart()!=null);
        assertTrue(cartManager.getProductsInCart().size()==sampleProducts.size());
    }

    @Test
    public void getProductByIdTest() {

        //init
        final ArrayList<Product> sampleProducts = getSampleProducts();
        Product productToFind = new Product(5l,"findMe", "missing");
        sampleProducts.add(productToFind);
        cartManager.getProductsInCart().addAll(sampleProducts);

        //act

        //assert
        Product searchedProduct = cartManager.getProductById(productToFind.getId());
        assertTrue(searchedProduct.getId() == productToFind.getId());
    }

    @Test
    public void removeProductByIdTest() {
        //init
        final ArrayList<Product> sampleProducts = getSampleProducts();
        int originalCount = sampleProducts.size();
        cartManager.getProductsInCart().addAll(sampleProducts);

        //act
        Product removed = cartManager.removeProductById(1l);
        Product removed2 = cartManager.removeProductById(11l);//expected not to be removed


        //assert
        int expectedCount = 2;
        assertTrue(removed!=null);
        assertTrue(cartManager.getCartSize()==expectedCount);
        assertTrue(removed2==null);

    }

    @Test
    public void insertProductToCartTest() {
        //init
        final ArrayList<Product> sampleProducts = getSampleProducts();
        Product productToInsert = new Product(5l,"insert me", "new");

        //act
        Product insertedProduct = cartManager.insertProductToCart(productToInsert);
        Product insertedProduct2 = cartManager.insertProductToCart(productToInsert);

        //assert
        assertTrue(insertedProduct!=null);
        assertTrue(insertedProduct2==null);
    }

    @Test
    public void getCartSizeTest() {
        //init
        final ArrayList<Product> sampleProducts = getSampleProducts();
        cartManager.getProductsInCart().addAll(sampleProducts);

        //assert
        assertTrue(sampleProducts.size()==cartManager.getCartSize());
    }

    @Test
    public void clearTest() {
        //init
        final ArrayList<Product> sampleProducts = getSampleProducts();
        cartManager.getProductsInCart().addAll(sampleProducts);

        //act
        cartManager.clear();

        //assert
        assertTrue(sampleProducts.size()!=cartManager.getCartSize());
        assertTrue(cartManager.getCartSize()==0);
    }

}