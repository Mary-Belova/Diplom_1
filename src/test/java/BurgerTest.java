import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    Burger burger = new Burger();
    @Mock
    Ingredient sauce;
    @Mock
    Ingredient filling;
    @Mock
    Bun bun;

    @Test
    public void setBunsTest() {
        Bun bunForTest = new Bun("name", 10F);
        burger.setBuns(bunForTest);

        Assert.assertEquals(bunForTest, burger.bun);

    }

    @Test
    public void addIngredientTest() {
        int bAdd = burger.ingredients.size() + 1;
        burger.addIngredient(sauce);
        int aAdd = burger.ingredients.size();
        Assert.assertEquals(bAdd, aAdd);
    }

    @Test
    public void removeIngredientTest() {
        int bRemove = burger.ingredients.size();
        burger.addIngredient(sauce);
        burger.removeIngredient(0);
        int aRemove = burger.ingredients.size();
        Assert.assertEquals(bRemove, aRemove);
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(1, 0);
        Assert.assertEquals(filling, burger.ingredients.get(0));
    }

    @Test
    public void getPriceTest() {
        Mockito.when(bun.getPrice()).thenReturn(20F);
        Mockito.when(sauce.getPrice()).thenReturn(60F);

        burger.setBuns(bun);
        burger.addIngredient(sauce);

        float expected = 100F;
        Assert.assertEquals(expected, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptTest() {
        String expected = String.format("(==== black bun ====)%n"
                + "= sauce sour cream =%n"
                + "(==== black bun ====)%n"
                + "%n"
                + "Price: 900,000000%n");

        burger.addIngredient(sauce);
        burger.setBuns(bun);

        Mockito.when(bun.getPrice()).thenReturn(300F);
        Mockito.when(sauce.getPrice()).thenReturn(300F);
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(sauce.getName()).thenReturn("sour cream");
        Mockito.when(sauce.getType()).thenReturn(IngredientType.SAUCE);


        Assert.assertEquals(expected, burger.getReceipt());
    }
}