import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

public class IngredientTest {

    private Ingredient ingredient;

    String expectedIngredientName = "tabasco";
    private final float expectedIngredientPrice = 11.05F;

    // Создаем новый объект класса "Ingredient"
    @Before
    public void createNewIngredient() {
        ingredient = new Ingredient(IngredientType.SAUCE, expectedIngredientName, expectedIngredientPrice);
    }

    // Проверка получения цены ингредиента
    @Test
    public void getIngredientPrice() {
        float actualIngredientPrice = ingredient.getPrice();
        Assert.assertEquals("Неверная цена ингредиента", expectedIngredientPrice, actualIngredientPrice, 0.001);
    }

    // Проверка получения названия ингредиента
    @Test
    public void getIngredientName() {
        String actualIngredientName = ingredient.getName();
        Assert.assertEquals("Неверное название ингредиента", expectedIngredientName, actualIngredientName);
    }

    // Проверка получения типа ингредиента (соус/начинка)
    @Test
    public void getIngredientType() {
        IngredientType expectedTypeSauce = IngredientType.SAUCE;
        IngredientType actualTypeSauce = ingredient.getType();
        Assert.assertEquals("Неверный тип ингредиента", expectedTypeSauce, actualTypeSauce);
    }

    // Негативный тест на получение типа ингредиента (ожидаем неправильный тип)
    @Test
    public void getIngredientTypeInvalid() {
        IngredientType expectedTypeFilling = IngredientType.FILLING;
        IngredientType actualTypeSauce = ingredient.getType();
        Assert.assertNotEquals("Ингредиент не должен быть начинкой.", expectedTypeFilling, actualTypeSauce);
    }
}