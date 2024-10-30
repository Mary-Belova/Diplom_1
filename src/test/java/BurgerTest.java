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

    //Создание нового бургера
    @Before
    public void createNewBurger() {
        burger = new Burger();
    }

    //Тест на проверку добавления булочек в бургер
    @Test
    public void setBunsTest() {

        burger.setBuns(mockBun);
        Bun actualBun = burger.bun;
        assertEquals(mockBun, actualBun);

        System.out.println("Ожидаем добавление булочки: " + mockBun);
        System.out.println("Фактически добавлена булочка: " + actualBun);
    }

    //Тест на проверку добавления одного ингридиента
    @Test
    public void addOneIngredientTest() {

        burger.addIngredient(mockCutlet);
        assertEquals(1, burger.ingredients.size());
        assertEquals(mockCutlet, burger.ingredients.get(0));

        System.out.println("Ожидаем 1 ингредиент, фактически добавлено: " + burger.ingredients.size());
        System.out.println("В бургер добавлен: " + burger.ingredients.get(0));

    }

    //Тест на проверку добавления двух ингридиентов
    @Test
    public void addTwoIngredientTest() {

        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockCheese);
        assertEquals(2, burger.ingredients.size());
        assertEquals(mockCutlet, burger.ingredients.get(0));
        assertEquals(mockCheese, burger.ingredients.get(1));

        System.out.println("Ожидаем 2 ингредиента, фактически добавлено: " + burger.ingredients.size());
        System.out.println("В бургер добавлены: " + burger.ingredients.get(0) +", " + burger.ingredients.get(1));

    }

    //Тест на удаление ингридиента
    @Test
    public void removeIngredientTest() {

        burger.addIngredient(mockCutlet);
        System.out.println("В бургер добавлен один ингредиент, итого ингредиентов: " + burger.ingredients.size());
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
        System.out.println("Удалили ингредиент из бургера, итого ингредиентов: " + burger.ingredients.size());
    }

    //Тест на перемещение ингридиентов
    @Test
    public void moveIngredient() {

        burger.addIngredient(mockCutlet);
        burger.addIngredient(mockSauce);

        System.out.println("Первый ингридиент: " + burger.ingredients.get(0) + ", второй ингридиент: "+ burger.ingredients.get(1));

        burger.moveIngredient(0, 1);
        assertEquals(mockSauce, burger.ingredients.get(0));
        assertEquals(mockCutlet, burger.ingredients.get(1));

    }

    //Тест на получение стоимости бургера, 2 булочки + 2 ингридиента. Используем мокито и явно указываем стоимость каждого ингридиента
    @Test
    public void getPriceTest() {

        burger.setBuns(mockBun);
        Mockito.when(mockBun.getPrice()).thenReturn(20.0f);
        burger.addIngredient(mockCheese);
        Mockito.when(mockCheese.getPrice()).thenReturn(25.0f);
        burger.addIngredient(mockCutlet);
        Mockito.when( mockCutlet.getPrice()).thenReturn(30.0f);

        float expectedPrice = mockBun.getPrice() * 2 + mockCheese.getPrice() +mockCutlet.getPrice();
        float actualPrice = burger.getPrice();

        assertEquals("Неверный расчет стоимости бургера", expectedPrice, actualPrice, 0);

        System.out.println("Ожидаемая стоимость бургера: "+ expectedPrice + " руб., фактическая стоимость: " + actualPrice + " руб.");
    }
    //Тест метода формирования чека. Используем мокито и явно указываем аргументы ингридиентов бургера
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
        System.out.println("Ожидаемый формат чека: \n " + expectedReceipt + "\n Фактический формат чека: \n " + actualReceipt);
    }

}