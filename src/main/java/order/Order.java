package order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private List<String> ingredients;
    private String name;
    private String message;
    private boolean success;

    public Order(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}