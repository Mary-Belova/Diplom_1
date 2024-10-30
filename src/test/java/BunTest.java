import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;


public class BunTest {
    private final String nameBunTest = "Test";
    private final float priceBunTest = 15.0001F;
    private Bun bun;

    //Создаем новый объект класса "Bun", в качестве аргументов передаем название и цену
    @Before
    public void createNewBun() {
        bun = new Bun(nameBunTest, priceBunTest);
    }


    //Проверка метода получения названия булочки
    @Test
    public void getNameBun() {
        String actualNameBun = bun.getName();

        Assert.assertEquals(nameBunTest, actualNameBun);
    }

    //Проверка метода получения цены булочки, в методе Assert используется дельта, т.к. у переменной "price" тип данных "float"
    @Test
    public void getPriceBun() {
        float actualPriceBun = bun.getPrice();

        Assert.assertEquals(priceBunTest, actualPriceBun, 0);

    }
}