import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

public class IngredientTest {
    private Ingredient ingredient;
    String expectedIngredientName = "tabasco";
    private final float expectedIngredientPrice = 11.05F;


    //Создаем новый объект класса "Ingredient"
    @Before
    public void createNewIngredient() {
        ingredient = new Ingredient(IngredientType.SAUCE, expectedIngredientName, expectedIngredientPrice);
    }

    //Проверка получения цены ингридиента
    @Test
    public void getIngredientPrice() {
        float actualIngredientPrice = ingredient.getPrice();

        Assert.assertEquals(expectedIngredientPrice, actualIngredientPrice, 0);
        System.out.println(actualIngredientPrice);
    }

    //Проверка получения названия ингридиента
    @Test
    public void getIngredientName() {
        String actualIngredientName = ingredient.getName();

        Assert.assertEquals(expectedIngredientName, actualIngredientName);

    }

    //Проверка получения типа ингредиента (соус/начинка)
    @Test
    public void getIngredientType() {
        IngredientType expectedTypeSauce = IngredientType.SAUCE;
        IngredientType actualTypeSauce = ingredient.getType();

        Assert.assertEquals(expectedTypeSauce, actualTypeSauce);
    }

    //Негативный тест на получение типа ингредиента
    @Test
    public void getIngredientTypeInvalid() {
        IngredientType expectedTypeFilling = IngredientType.FILLING;
        IngredientType actualTypeSauce = ingredient.getType();

        Assert.assertEquals(expectedTypeFilling, actualTypeSauce);
    }
}