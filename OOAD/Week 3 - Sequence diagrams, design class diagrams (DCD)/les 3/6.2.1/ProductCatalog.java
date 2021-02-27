import java.util.List;

public class ProductCatalog{
    private List<ProductSpecification> products;

    public ProductCatalog(){
        //silence is golden 
    }

    public ProductSpecification getSpecification(String itemID){
        return new ProductSpecification();
    }
}