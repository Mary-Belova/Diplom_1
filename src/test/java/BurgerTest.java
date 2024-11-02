import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockCheese;

    @Mock
    private Ingredient mockCutlet;

    @Mock
    private Ingredient mockSauce;

    // Создание нового бургера
    @Before
    public void createNewBurger() {
        burger = new Burger();
    }

    // Тест на проверку добавления булочек в бургер
    @Test
    public void setBunsTest() {
        burger.setBuns(mockBun);
        Bun actualBun = burger.bun;
        assertEquals("Ожидаем добавление булочки", mockBun, actualBun);
    }

    // Тест на проверку добавления одного ингредиента
    @Test
    public void addOneIngredientTest() {
        burger.addIngredient(mockCutlet);
        assertEquals("Ожидаем, что в бургер будет 1 ингредиент", 1, burger.ingredients.size());
        assertEquals("Первый ингредиент должен быть cutlet", mockCutlet, burger.ingredients.get(0));
    }

    // Тест на проверку добавления двух ингредиентов
    @Test
    public void addTwoIngredientTest() {
        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockCheese);
        assertEquals("Ожидаем, что в бургер будет 2 ингредиента", 2, burger.ingredients.size());
        assertEquals("Первый ингредиент должен быть cutlet", mockCutlet, burger.ingredients.get(0));
        assertEquals("Второй ингредиент должен быть cheese", mockCheese, burger.ingredients.get(1));
    }

    // Тест на удаление ингредиента
    @Test
    public void removeIngredientTest() {
        burger.addIngredient(mockCutlet);
        burger.removeIngredient(0);
        assertEquals("Ожидаем, что после удаления ингредиента, ингредиентов будет 0", 0, burger.ingredients.size());
    }

    // Тест на перемещение ингредиентов
    @Test
    public void moveIngredient() {
        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockSauce);
        burger.moveIngredient(0, 1);
        assertEquals("Первый ингредиент должен быть sauce", mockSauce, burger.ingredients.get(0));
        assertEquals("Второй ингредиент должен быть cutlet", mockCutlet, burger.ingredients.get(1));
    }

    // Тест на получение стоимости бургера, 2 булочки + 2 ингредиента
    @Test
    public void getPriceTest() {
        burger.setBuns(mockBun);
        Mockito.when(mockBun.getPrice()).thenReturn(20.0f);
        burger.addIngredient(mockCheese);
        Mockito.when(mockCheese.getPrice()).thenReturn(25.0f);
        burger.addIngredient(mockCutlet);
        Mockito.when(mockCutlet.getPrice()).thenReturn(30.0f);

        float expectedPrice = mockBun.getPrice() * 2 + mockCheese.getPrice() + mockCutlet.getPrice();
        float actualPrice = burger.getPrice();

        assertEquals("Неверный расчет стоимости бургера", expectedPrice, actualPrice, 0.001); // Убедитесь, что дельта соответствует вашим требованиям
    }

    // Тест метода формирования чека
    @Test
    public void getReceipt() {
        burger.setBuns(mockBun);
        Mockito.when(mockBun.getPrice()).thenReturn(20.0f);
        Mockito.when(mockBun.getName()).thenReturn("CosmoBurger");
        burger.addIngredient(mockCutlet);
        Mockito.when(mockCutlet.getName()).thenReturn("steak");
        Mockito.when(mockCutlet.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(mockCutlet.getPrice()).thenReturn(50.0f);

        burger.addIngredient(mockSauce);
        Mockito.when(mockSauce.getName()).thenReturn("chili");
        Mockito.when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(mockSauce.getPrice()).thenReturn(10.0f);

        String expectedReceipt = String.format("(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                mockBun.getName(),
                mockCutlet.getType().toString().toLowerCase(), mockCutlet.getName(),
                mockSauce.getType().toString().toLowerCase(), mockSauce.getName(),
                mockBun.getName(),
                burger.getPrice());

        String actualReceipt = burger.getReceipt();

        Assert.assertEquals("Некорректный формат чека", expectedReceipt, actualReceipt);
    }
}