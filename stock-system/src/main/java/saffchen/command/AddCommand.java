package saffchen.command;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saffchen.database.FileConnection;
import saffchen.product.Product;
import saffchen.utils.FileStorageUtils;
import saffchen.utils.FileUtils;

import java.util.*;

public class AddCommand implements Command {
    private Exit exit;

    public void setExit(Exit exit) {
        this.exit = exit;
    }

    private static final Logger LOGGER
            = LoggerFactory.getLogger(AddCommand.class);
    static ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
            .configure()
            .buildValidatorFactory();
    static Validator validator = validatorFactory.getValidator();

    @Override
    public String getInfo() {
        return "Write an \"add_product\" if you want to additional product";
    }

    public Product addNewProduct() throws Exception {
        LOGGER.info(" --- ADD_PRODUCT --- ");
        Product product = null;
        System.out.println("*** ADDING A PRODUCT ***");
        boolean isValidProduct = true;
        while (isValidProduct) {
            System.out.println("Введите продукт или exit для того, чтобы выйти в главное меню");
            System.out.print("Укажите название продукта: ");
            String title = new Scanner(System.in).nextLine().trim();
            if (title.equals("exit")) {
                setExit(new ExitFromCommandMenu());
                exit.doExit();
            }
            System.out.print("Укажите описание продукта: ");
            String description = new Scanner(System.in).nextLine();
            System.out.print("Укажите цену продукта: ");
            Double price;
            try {
                price = new Scanner(System.in).nextDouble();
            } catch (Exception e) {
                System.out.println("Вы ввели некорректное значение цены продукта. Пожалуйста заполните продукт заново.");
                continue;
            }
            System.out.print("Введите теги : ");
            String[] tags = new Scanner(System.in).nextLine().trim().split(",");
            System.out.print("Укажите категорию продукта: ");
            String category = new Scanner(System.in).nextLine().trim();
            System.out.print("Укажите количество продукта: ");
            Integer count;
            try {
                count = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.out.println("Вы ввели некорректное значение количества продукта. Пожалуйста заполните продукт заново.");
                continue;
            }
            System.out.println("Доступные склады: \n" + FileUtils.getSatelliteString());
            System.out.print("Укажите склад на котором хранится продукт: ");
            String satellite = new Scanner(System.in).next().toUpperCase(Locale.ROOT);

            product = new Product(title, description, price, List.of(tags), category,
                    count, satellite);
            LOGGER.info(" --- ADD_PRODUCT --- {{}}", product);
            isValidProduct = false;
            Set<ConstraintViolation<Product>> violations = validator.validate(product);
            for (ConstraintViolation<Product> warning : violations) {
                System.out.println(warning.getMessage());
                warning.getMessage();
                isValidProduct = true;
            }
        }
        return product;
    }

    @Override
    public void doCommand() throws Exception {
        FileConnection fileConnection = FileConnection.getInstance("stock_import_csv.csv");
        FileStorageUtils fileStorageUtils = new FileStorageUtils(fileConnection);
        try {
            fileStorageUtils.addProduct(addNewProduct());
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
    }
}