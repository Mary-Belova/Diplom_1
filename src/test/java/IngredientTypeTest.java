import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private final IngredientType expectedType;
    private final Ingredient ingredient;

    // Создаем конструктор для параметров теста
    public IngredientTypeTest(IngredientType expectedType, Ingredient actualIngredient) {
        this.expectedType = expectedType;
        this.ingredient = actualIngredient;
    }

    // Параметры теста (2 позитивных и 1 негативный)
    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {IngredientType.SAUCE, new Ingredient(IngredientType.SAUCE, "tabasco", 60.0f)},
                {IngredientType.FILLING, new Ingredient(IngredientType.FILLING, "pickles", 200.0f)},
                // Негативный кейс, ожидаем тип "Filling", а получаем тип "Sauce"
                {IngredientType.FILLING, new Ingredient(IngredientType.SAUCE, "pickles", 200.0f)}
        };
    }

    @Test
    public void paramTestIngredientType() {
        Assert.assertEquals("Некорректный тип ингредиента", expectedType, ingredient.getType());
    }
}